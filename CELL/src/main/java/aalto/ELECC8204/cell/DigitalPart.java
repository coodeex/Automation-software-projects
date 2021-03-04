/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aalto.ELECC8204.cell;

import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;
import com.jme3.asset.AssetManager;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import com.jme3.math.FastMath;
import com.jme3.bounding.BoundingBox;


/**
 *
 * @author ssierla
 */
abstract public class DigitalPart {
    public Vector3f location;
    public Vector3f startLocation;
    public Vector3f targetLocation;
    public Vector3f assemblyLocation = new Vector3f(0,0,0);
    public int approachAttempt = 0;
    private RobotArm robotArm;
    public String legoColor;
    LegoState state = LegoState.ToBeFetched;
    
    protected Node node = new Node();
    protected Geometry geom;
    public boolean assembled = false;
    boolean moving = false;
    String id;
    static float step = 1.5f;
    boolean bottomLayer = false;
    Box box;
    boolean connectUpward = true;
    public DigitalTwin twin;
    private float xExtent;
    private float yExtent;
    private float zExtent;
    public String typeName;
    protected boolean orientationZero; // if true, no need to rotate
    protected Vector3f orientation = new Vector3f(0,0,0); // in this vector, 1 = HALF_PI
    protected String rotationAxis = " "; // "x", "y" or "z" if orientationZero is false

    // debugging method to be overridden
    public void setMarkers() {

    }
    
    abstract public boolean isRectangle();

    public boolean isLego() {
        return false;
    }
    
    public float getXextent() {
        return xExtent;
    }
    
    public float getYextent() {
        return yExtent;
    }

    public float getZextent() {
        return zExtent;
    }
    
    // this is what we add to the part location to get the robot arm gripper target coordinate
    // this is the default implementation for legos, so other kinds of parts should override this
    public Vector3f getGripperOffset() {
//        return new Vector3f(0, 2*Main.dim, 0);
        return new Vector3f(0, getYextent()+Main.dim, 0);
    }    
    
    public boolean northSouthOrientation() {
        return false;
    }
    
    public void connectArm(RobotArm arm) {
        robotArm = arm;
        arm.attachLego(this);
    }
    
    public void disconnectArm() {
        robotArm.detachLego();
        node.setLocalTranslation(location);
        if(northSouthOrientation()) {
            node.rotate(0, FastMath.HALF_PI, 0);
        }
        if(!isLego() && (!orientationZero)) {
//            node.rotate(0, FastMath.HALF_PI * orientation, 0);
            node.rotate(orientation.getX(), orientation.getY(), orientation.getZ());
        }        
        robotArm = null;
    }

    public void disconnectArm2() {
        robotArm.detachLego();
        robotArm = null;
    }    

    public void setDimensions() {
        BoundingBox b = (BoundingBox) node.getWorldBound();
        xExtent = b.getXExtent();
        yExtent = b.getYExtent();
        zExtent = b.getZExtent();
//        System.out.println(yExtent);
    }
    
    // this is actually the superclass constructor that does more than set the material
    public void setMaterial(String color, AssetManager assetManager, Spatial g) {
        legoColor = color;
        Material mat = new Material(assetManager, "Common/MatDefs/Light/Lighting.j3md");
        mat.setBoolean("UseMaterialColors",true);

        mat.setFloat("Shininess", 12f);  // [0,128]
        ColorRGBA c;
//        Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
        if(color.equals("blue")) {
            c = ColorRGBA.Blue;
        } else if (color.equals("pink")) {
            c = ColorRGBA.Pink;
        } else if (color.equals("yellow")) {
            c = ColorRGBA.Yellow;
        } else if (color.equals("green")) {
            c = ColorRGBA.Green;
        } else if (color.equals("red")) {
            c = ColorRGBA.Red;
        } else {
            c = ColorRGBA.DarkGray;
        }
        
        mat.setColor("Diffuse",c);
        mat.setColor("Specular",c);
        g.setMaterial(mat);
        
    }
    

    
    // moves towards target location and sets "moving" to false when it reached the location
    public void move() {
        float xDistance = targetLocation.getX() - location.getX();
        float yDistance = targetLocation.getY() - location.getY();
        float zDistance = targetLocation.getZ() - location.getZ();
        
        boolean xReady = false;
        boolean yReady = false;
        boolean zReady = false;
        
        float x;
        float y;
        float z;

        if (xDistance > step ) {
            x = step;
        } else if ((-1 * xDistance) > step) {
            x = -1 * step;
        } else {
            xReady = true;
            x = xDistance;
        }
        
        if (yDistance > step ) {
            y = step;
        } else if ((-1 * yDistance) > step) {
            y = -1 * step;
        } else {
            yReady = true;
            y = yDistance;
        }
                
        if (zDistance > step ) {
            z = step;
        } else if ((-1 * zDistance) > step) {
            z = -1 * step;
        } else {
            zReady = true;
            z = zDistance;
        }
        
        Vector3f offset = new Vector3f(x,y,z);
        if(robotArm == null) {
            translate(location.add(offset));
        } else {
            location = location.add(offset);
            robotArm.getNode().move(offset);
//            robotArm.checkCollision();
        }
//        Main.helloText.setText(targetLocation.toString() + " " + location.toString());
        if((yReady && xReady) && zReady) {
            moving = false;
        }
    }

//    abstract public Lego createDigitalTwin();
    
    abstract public Vector3f getConnectionPoint(String pointName);

    abstract public Vector3f getConnectionPointTop();
    
    public Vector3f getCurrentLocationTop() {
        return node.getLocalTranslation().add(0, 2*Main.dim, 0);
    }

    public Vector3f getWorldTranslation() {
        return node.getWorldTranslation();
    }
    
    // translates targetLocation
    abstract public void translateConnectionPoint(String pointName);
    
    public void translate(Vector3f v) {
        location = v;
        node.setLocalTranslation(location);
    }
    
    public void translateTarget(Vector3f v) {
//        System.out.print("Setting target for " + id + " ");        
        targetLocation = v;
//        System.out.println(v.toString());
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aalto.ELECC8204.cell;

import com.jme3.asset.AssetManager;
import com.jme3.bounding.BoundingBox;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Box;
import com.jme3.scene.shape.Sphere;
import com.jme3.bounding.BoundingSphere;
import com.jme3.collision.CollisionResult;
import com.jme3.collision.CollisionResults;

/**
 *
 * @author hp
 */
public class RobotArm {
    private Vector3f targetLocation;
    static public float step = 0.2f;

    private Geometry geomBox;
    private Geometry geomSphere;
    private Geometry geomBox2;
    private Geometry geomSphere2;
    private Node node; // this has as childred all the geometries/nodes of the robot
    private Node node1Joint;
    private Node node2Joints;
    private BoundingSphere bound;
    private BoundingBox bound2;
    private BoundingBox bound2b;
    private BoundingBox bound3;
    float radius = Main.dim * 1.9f;
    private Box box;
    private Box box2;
    private DigitalPart lego;
    public static int maxRotationStep = 10;
    int rotationStep = 0;
    private float rotX;
    private float rotY;
    private float rotZ;
    private float cumulRotX;
    private float cumulRotY;
    private float cumulRotZ;
    boolean disableCollisions = false;
    private Box xArmBox;
    private Box yArmBox;
    private Box zArmBox;
    private Box mastBox;
    private Geometry xArmGeom;
    private Geometry yArmGeom;
    private Geometry zArmGeom;
    private Geometry mastGeom;
    private float targetY;
    private boolean rotatingZ;
    private boolean rotatedUp = false;
    public DigitalTwin twin;
    public String connectionPoint; // null is top
//    Quaternion initRotationNode1Joint;
//    Quaternion initRotationNode2Joints;    

    public void detachLego() {
        lego = null;
    }
    
    public boolean legoAttached() {
        if (lego != null) {
            return true;
        } else {
            return false;
        }
    }
    
    public void checkCollision() {
/*        if (disableCollisions) {
            return;
        }*/
        Vector3f v = geomSphere.getWorldTranslation();
//            Main.helloText.setText(v.toString());                    
        bound = new BoundingSphere(radius, v);
//        Main.markerGeom.setLocalTranslation(v);
        CollisionResults results = new CollisionResults();
        twin.node.collideWith(bound, results);
        if (results.size() > 0) {
            // how to react when a collision was detected
            CollisionResult closest  = results.getClosestCollision();
            System.out.print("Robot sphere local/world translation: ");
            System.out.print(geomSphere.getLocalTranslation());
            System.out.print(" / ");
            System.out.println(geomSphere.getWorldTranslation());
            System.out.println("2What was hit? " + closest.getGeometry().getName() );
            System.out.println("2Where was it hit? " + closest.getContactPoint() );
            System.out.println("2Distance? " + closest.getDistance() );
//            geomSphere.setLocalTranslation(geomSphere.worldToLocal(closest.getContactPoint(), null));
            twin.collisionDetected();
        }
        
        bound2 = new BoundingBox(geomBox.getWorldTranslation(), box.xExtent, box.yExtent, box.zExtent);
        CollisionResults results2 = new CollisionResults();
        twin.node.collideWith(bound2, results2);
        if (results2.size() > 0) {
            // how to react when a collision was detected
            CollisionResult closest2  = results2.getClosestCollision();
            System.out.print("Robot box local/world translation: ");
            System.out.print(geomBox.getLocalTranslation());
            System.out.print(" / ");
            System.out.println(geomBox.getWorldTranslation());
            System.out.println("3What was hit? " + closest2.getGeometry().getName() );
            System.out.println("3Where was it hit? " + closest2.getContactPoint() );
            System.out.println("3Distance? " + closest2.getDistance() );
            twin.collisionDetected();
        }
        
        bound2b = new BoundingBox(yArmGeom.getWorldTranslation(), yArmBox.xExtent, yArmBox.yExtent, yArmBox.zExtent);
        CollisionResults results2b = new CollisionResults();
        twin.node.collideWith(bound2b, results2b);
        if (results2b.size() > 0) {
            // how to react when a collision was detected
            CollisionResult closest2b  = results2b.getClosestCollision();
            System.out.print("Robot box local/world translation: ");
            System.out.print(yArmGeom.getLocalTranslation());
            System.out.print(" / ");
            System.out.println(yArmGeom.getWorldTranslation());
            System.out.println("3What was hit? " + closest2b.getGeometry().getName() );
            System.out.println("3Where was it hit? " + closest2b.getContactPoint() );
            System.out.println("3Distance? " + closest2b.getDistance() );
            twin.collisionDetected();
        }
        
        float x;
        float z;
 //       System.out.println(lego.box.xExtent);
        if(lego.northSouthOrientation()) {
            x = currentWidthFactor() * lego.box.xExtent*0.9f;
            z = lego.box.zExtent*0.8f;            
        } else {
            x = currentWidthFactor() * lego.box.xExtent*0.9f;
            z = lego.box.zExtent*0.8f;
        }
            bound3 = new BoundingBox(lego.geom.getWorldTranslation(), x, lego.box.yExtent*0.8f, z);
//        Main.markerGeom.setLocalTranslation(lego.geom.getWorldTranslation().add(new Vector3f(-1*z, 0, 0)));

        CollisionResults results3 = new CollisionResults();
        twin.node.collideWith(bound3, results3);
        if (results3.size() > 0) {
            // how to react when a collision was detected
            CollisionResult closest3  = results3.getClosestCollision();
            System.out.print("Lego box local/world translation: ");
            System.out.print(lego.geom.getLocalTranslation());
            System.out.print(" / ");
            System.out.println(lego.geom.getWorldTranslation());
            System.out.println("4What was hit? " + closest3.getGeometry().getName() );
            System.out.println("4Where was it hit? " + closest3.getContactPoint() );
            System.out.println("4Distance? " + closest3.getDistance() );
            twin.collisionDetected();
        }
    }
    
    public String worldTranslation() {
        return geomBox.getWorldTranslation().toString() + " " + geomSphere.getWorldTranslation().toString();
    }
/*
    public void gotoReverseMotionStart() {
        twinLego.translate(twinLego.assemblyLocation.add(new Vector3f(0, -6.0f * Main.dim, 0))); 
        Main.assemblyArm.moveToLego(twinLego.location);
    }
    
    public void gotoReverseMotionStart(Lego l) {
        twinLego = l;
        gotoReverseMotionStart();
    }
 */   
    public RobotArm(String name, String color, AssetManager assetManager, Node root, float x, float z) {
        node = new Node();
        root.attachChild(node);
        new Vector3f(0, 3.0f, 3.0f);
        box = new Box(Main.dim*0.7f, Main.dim*2.0f, Main.dim*0.7f);
        Sphere sphere = new Sphere(100, 100, radius);
        geomBox = new Geometry("Box", box);
        geomSphere = new Geometry("Sphere", sphere);
        
        box2 = new Box(Main.dim*0.85f, Main.dim*3.0f, Main.dim*0.85f);
        Sphere sphere2 = new Sphere(100, 100, radius);
        geomBox2 = new Geometry("Box", box2);
        geomSphere2 = new Geometry("Sphere", sphere2);        
        
        Material mat = new Material(assetManager, "Common/MatDefs/Light/Lighting.j3md");
        mat.setBoolean("UseMaterialColors",true);

        mat.setFloat("Shininess", 12f);  // [0,128]
        ColorRGBA c;

        if(color.equals("orange")) {
            c = ColorRGBA.Orange;
        } else if (color.equals("magenta")) {
            c = ColorRGBA.Magenta;
        } else {
            c = ColorRGBA.Green;
        }
        
        mat.setColor("Diffuse",c);
        mat.setColor("Specular",c);

        geomBox.setMaterial(mat);
        geomSphere.setMaterial(mat);
        geomBox.setLocalTranslation(new Vector3f(0, -1.0f*Main.dim, 0));
//        geomSphere.setLocalTranslation(new Vector3f(0, -3.0f*Main.dim,0));

        geomBox2.setMaterial(mat);
        geomSphere2.setMaterial(mat);
        geomBox2.setLocalTranslation(new Vector3f(0, -3.0f*Main.dim, 0));
//        geomSphere2.setLocalTranslation(new Vector3f(0, 2.0f*Main.dim,0));        


        node1Joint = new Node();
        node1Joint.attachChild(geomBox);
        node1Joint.attachChild(geomSphere);
        node1Joint.setLocalTranslation(new Vector3f(0, -6.0f*Main.dim,0));
        
        node2Joints = new Node();
        node2Joints.attachChild(geomBox2);
        node2Joints.attachChild(geomSphere2);
        node2Joints.attachChild(node1Joint);
        node.attachChild(node2Joints);
        node2Joints.setLocalTranslation(new Vector3f(0, 2.0f*Main.dim,0));
        
        zArmBox = new Box(Main.dim, Main.dim, Main.dim*100f);
        zArmGeom = new Geometry("Box", zArmBox);
        zArmGeom.setMaterial(mat);
        node.attachChild(zArmGeom);
        zArmGeom.setLocalTranslation(-40f*Main.dim, Main.dim * 30f, -40f*Main.dim);
        
        xArmBox = new Box(90f*Main.dim, Main.dim, Main.dim);
        xArmGeom = new Geometry("Box", xArmBox);
        xArmGeom.setMaterial(mat);
        node.attachChild(xArmGeom);
        xArmGeom.setLocalTranslation(30f*Main.dim, Main.dim * 30f, 0);
        
        yArmBox = new Box(Main.dim, 30f*Main.dim, Main.dim);
        yArmGeom = new Geometry("Box", yArmBox);
        yArmGeom.setMaterial(mat);
        node.attachChild(yArmGeom);
        yArmGeom.setLocalTranslation(-35f*Main.dim, Main.dim * 30f, 0);

        mastBox = new Box(Main.dim, 30f*Main.dim, Main.dim);
        mastGeom = new Geometry("Box", mastBox);
        mastGeom.setMaterial(mat);
        node.attachChild(mastGeom);
        mastGeom.setLocalTranslation(-40f*Main.dim, 0, -50f*Main.dim);

        node2Joints.setLocalTranslation(yArmGeom.getLocalTranslation().add(new Vector3f(0, -32*Main.dim+radius, 0)));
        
        node.setLocalTranslation(new Vector3f(x-40f*Main.dim, 0, z+40f*Main.dim));
//        initRotationNode1Joint = node1Joint.getLocalRotation();
//        initRotationNode2Joints = node2Joints.getLocalRotation();
        
//        node2Joints.rotate(FastMath.HALF_PI, 0, 0);
//        node1Joint.rotate(FastMath.HALF_PI, 0, 0);
    }

    public void initMove(Vector3f target, DigitalTwin d) {
        twin = d;
        targetLocation = target;
    }
    
    // moves towards target location and returns false when it reached the location
    public boolean move() {
        Vector3f location = getToolTipLocation();
        Main.markerGeom2.setLocalTranslation(location);
        float xDistance = targetLocation.getX() - location.getX();
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

                
        if (zDistance > step ) {
            z = step;
        } else if ((-1 * zDistance) > step) {
            z = -1 * step;
        } else {
            zReady = true;
            z = zDistance;
        }
        
        // we start going down only when we are directly above the target location - otherwise we go to the max height
        if(!(zReady && xReady) && !DigitalTwin.connectingUpward) {
            targetY = 4;
        } else {
            targetY = targetLocation.getY();
        }

        float yDistance = targetY - location.getY();        
        if (yDistance > step ) {
            y = step;
        } else if ((-1 * yDistance) > step) {
            y = -1 * step;
        } else {
            yReady = true;
            y = yDistance;
        }
        zArmGeom.setLocalTranslation(zArmGeom.getLocalTranslation().add(new Vector3f(0, 0, 0.5f*z)));
        xArmGeom.setLocalTranslation(xArmGeom.getLocalTranslation().add(new Vector3f(0, 0, z)));
        yArmGeom.setLocalTranslation(yArmGeom.getLocalTranslation().add(new Vector3f(x, y, z)));
        node2Joints.setLocalTranslation(node2Joints.getLocalTranslation().add(new Vector3f(x, y, z)));        
      
        if((yReady && xReady) && zReady) {
            return false; //i.e. not moving anymore
        } else {
            return true;
        }
    }    
    
    private Vector3f getToolTipLocation() {
        if(connectionPoint != null) {
//            Main.helloText.setText("Z");                   
            if(connectionPoint.equalsIgnoreCase("z")) {

                return geomBox.getWorldTranslation().add(new Vector3f(0, 0, -2f*Main.dim));                
            }
        }
        if (rotatedUp) {
//            Main.helloText.setText("Up");                   
            return geomBox.getWorldTranslation().add(new Vector3f(0, 3f*Main.dim, 0));            
        } else {
//            Main.helloText.setText("Down");                   

            return geomBox.getWorldTranslation().add(new Vector3f(0, -1f*Main.dim, 0));
        }
    }
    
    public void moveToLego(Vector3f legoInterface) {
        node2Joints.setLocalTranslation(legoInterface);
    }
 
    public void rotateBack() {
//        node1Joint.setLocalRotation(initRotationNode1Joint);
//        node2Joints.setLocalRotation(initRotationNode2Joints);
        rotatedUp = false;
//        Main.helloText.setText(Float.toString(cumulRotX-FastMath.HALF_PI) + "    " + Float.toString(cumulRotZ-FastMath.HALF_PI));

        node2Joints.rotate(-1 * cumulRotX, 0, -1 * cumulRotZ);
        node1Joint.rotate(-1 * cumulRotX, 0, -1 * cumulRotZ);        
        
        node2Joints.rotate(0, -1 * cumulRotY, 0);
        cumulRotY = 0;
//        node1Joint.rotate(0, -1 * cumulRotY, 0);        
        


//                node2Joints.rotate(FastMath.HALF_PI, 0, 0);
//        node1Joint.rotate(-1*FastMath.HALF_PI, 0, 0);        
        //step = 0.02f;
    }
    
    public float currentWidthFactor() {
        float angle;
        if (rotatingZ) {
            angle = cumulRotZ;
        } else {
            angle = cumulRotX;
        }
        if(lego.northSouthOrientation()) {
            return (float) (Math.cos(angle)*lego.box.zExtent + (float) Math.sin(angle)*lego.box.yExtent)/lego.box.zExtent;            
        } else {
            return (float) (Math.cos(angle)*lego.box.xExtent + (float) Math.sin(angle)*lego.box.yExtent)/lego.box.xExtent;
        }
    }
    
    
    // this can be called several times in the virtual assembly of a lego. Thus we do not init the Y-rotation
    // Y rotation is initialized only when we call rotate back
    public void initRotate(DigitalTwin d) {
        twin = d;
        cumulRotX = 0;
//        cumulRotY = 0;
        cumulRotZ = 0;
        rotationStep = 0;
    }

    public boolean rotate(Vector3f v) {
        return rotate(v, true);
    }
    
    public boolean rotate(Vector3f v, boolean bothJoints) {
        rotX = v.getX() /maxRotationStep;
        rotY = v.getY() /maxRotationStep;
        rotZ = v.getZ() /maxRotationStep;
        
        boolean yRotation = false;
        if(Math.abs(v.getY()) > 0.001) {
            yRotation = true;
        }
        
        if((rotZ > 0.1) || (rotZ < -0.1)) {
            rotatingZ = true;
        } else {
            rotatingZ = false;
        }
        
        rotationStep += 1;
        if (rotationStep > maxRotationStep) {
            rotationStep = 0;
            if(!yRotation) {
                rotatedUp = true;
            }
            return true;
        }
        
        cumulRotX += rotX;
        cumulRotY += rotY;
        cumulRotZ += rotZ;

        if(bothJoints) {
            node2Joints.rotate(rotX, rotY, rotZ);
        }

        node1Joint.rotate(rotX, 0, rotZ);        
/*        if(yRotation) {
            node1Joint.rotate(0, -1 * rotY, 0);
        }*/
        return false;

    }
/*    
    public void attachLegoTwin(Geometry g) {
        node1Joint.attachChild(g);
    }
*/    
    public void attachLego(DigitalPart l) {
        lego = l;
        node1Joint.attachChild(lego.node);
    }

    public Node getMasterNode() {
        return node;
    }
    
    public Node getNode() {
        return node1Joint;
    }
}

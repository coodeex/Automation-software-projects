/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aalto.ELECC8204.cell;

import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;
import com.jme3.asset.AssetManager;
import com.jme3.math.FastMath;
import com.jme3.math.Vector3f;
import com.jme3.scene.shape.Cylinder;
/**
 *
 * @author ssierla
 */
public class Rectangle extends DigitalPart {
    // rectangle has connection point B in the center and A and C at each end
    // orientation "north" means that C point to the direction of the negative Z axis
    // "east" means that C points to positive X axis
    // "south" means that C points to positive Z axis
    // "west" means that C points to negative X axis
    public String orientation;
    // x and z are set to -1, 0 or 1 depending on the orientation
    float x = 0;
    float z = 0;
    
//    private Node digitalTwin = new Node();
//    Geometry twinGeom;
    
    public boolean northSouthOrientation() {
        if(orientation.equals("north") || (orientation.equals("south"))) {
            return true;
        } else {
            return false;
        }
    }
    
/*    public Rectangle() {
        // create dummy rectangle
    }*/
    
    public Rectangle(String name, String color, String orient, AssetManager assetManager) {
        orientation = orient;

//        if(orient.equals("north") || (orient.equals("south"))) {
//            box = new Box(2*Main.dim, Main.dim, 4*Main.dim);
//        } else {
            box = new Box(4*Main.dim, Main.dim, 2*Main.dim);
//        }
        geom = new Geometry("Box", box);
        node.attachChild(geom);
         setDimensions();
 /*       if(orient.equals("north") || (orient.equals("south"))) {
            for(int i = 0; i<8; i++) {
                Cylinder c = new Cylinder(20, 20, Main.dim/2, Main.dim/2, true);
                Geometry g = new Geometry("C", c);
                g.rotate(FastMath.HALF_PI, 0, 0);
                g.setLocalTranslation(Main.dim * (1 - 2*(i%2)), Main.dim, Main.dim * (3 - 2*(i/2)));
                super.setMaterial(color, assetManager, g);
                node.attachChild(g);
            }
        } else { */
            for(int i = 0; i<8; i++) {
                Cylinder c = new Cylinder(20, 20, Main.dim/2, Main.dim/2, true);
                Geometry g = new Geometry("C", c);
                g.rotate(FastMath.HALF_PI, 0, 0);
                g.setLocalTranslation(Main.dim * (3f - 2*(i/2)), Main.dim, Main.dim * (1 - 2*(i%2)));
                super.setMaterial(color, assetManager, g);
                node.attachChild(g);
            }
//        }
        
        
        super.setMaterial(color, assetManager, geom);
//        Main.pivot.attachChild(node);
        
        id = name;
        
        if(orientation.equals("north")) {
            x=0;
            z=-1;
            location = new Vector3f(0,0,0);
        } else if(orientation.equals("east")) {
            x=1;
            z=0;
            location = new Vector3f(0,0,0);
        } else if(orientation.equals("south")) {
            x=0;
            z=1;
            location = new Vector3f(0,0,0);
        } else if(orientation.equals("west")) {
            x=-1;
            z=0;
            location = new Vector3f(0,0,0);
        }
        
/*        // create digital twin
        Box box2;
        if(orient.equals("north") || (orient.equals("south"))) {
            box2 = new Box(2*Main.dim, Main.dim, 4*Main.dim);
        } else {
            box2 = new Box(4*Main.dim, Main.dim, 2*Main.dim);
        }
        
        twinGeom = new Geometry("Box2", box2);
        super.setMaterial("red", assetManager, twinGeom);
        digitalTwin.attachChild(twinGeom);
 
        if(orient.equals("north") || (orient.equals("south"))) {
            for(int i = 0; i<8; i++) {
                Cylinder c = new Cylinder(20, 20, Main.dim/2, Main.dim/2, true);
                Geometry g = new Geometry("C", c);
                g.rotate(FastMath.HALF_PI, 0, 0);
                g.setLocalTranslation(Main.dim * (1 - 2*(i%2)), Main.dim, Main.dim * (3 - 2*(i/2)));
                super.setMaterial(color, assetManager, g);
                digitalTwin.attachChild(g);
            }
        } else {
            for(int i = 0; i<8; i++) {
                Cylinder c = new Cylinder(20, 20, Main.dim/2, Main.dim/2, true);
                Geometry g = new Geometry("C", c);
                g.rotate(FastMath.HALF_PI, 0, 0);
                g.setLocalTranslation(Main.dim * (3f - 2*(i/2)), Main.dim, Main.dim * (1 - 2*(i%2)));
                super.setMaterial(color, assetManager, g);
                digitalTwin.attachChild(g);
            }
        }
 */       
        
    }
    
/*       // returns the digital twin
    public Rectangle(Node twin, Vector3f assemblyLoc) {
        node = twin;
//        geom = twinGeom;
        assemblyLocation = assemblyLoc;
//        Main.pivot.attachChild(digitalTwin);
    }

    public Rectangle createDigitalTwin() {
        return new Rectangle(digitalTwin, assemblyLocation);
    }
    
    public void putToMap() {
        Main.map.put(id,this);
    }
 */   
    public Vector3f getConnectionPointTop() {
        return getConnectionPoint("topB");
    }
    
    public Vector3f getConnectionPoint(String pointName) {
        // the "location" is at "bottomB"

        if (pointName.equals("topA")) {
            return targetLocation.add(-2*x*Main.dim, 2*Main.dim, -2*z*Main.dim);
        } else if (pointName.equals("topB")) {
            return targetLocation.add(0*x*Main.dim, 2*Main.dim, 0*z*Main.dim);
        } else if (pointName.equals("topC")) {
            return targetLocation.add(2*x*Main.dim, 2*Main.dim, 2*z*Main.dim);
        } else if (pointName.equals("bottomA")) {
            connectUpward = false;
            return targetLocation.add(-2*x*Main.dim, 0, -2*z*Main.dim);
        } else if (pointName.equals("bottomB")) {
            connectUpward = false;
            return targetLocation.add(0*x*Main.dim, 0, 0*z*Main.dim);
        } else if (pointName.equals("bottomC")) {
            connectUpward = false;            
            return targetLocation.add(2*x*Main.dim, 0, 2*z*Main.dim);
        } 
        return null;
    }
    
    public boolean isRectangle() {
        return true;
    }
    
      public void translateConnectionPoint(String pointName) {
        if (pointName.equals("topA")) {
            targetLocation = targetLocation.add(2*x*Main.dim, -2*Main.dim, 2*z*Main.dim);
        } else if (pointName.equals("topB")) {
            targetLocation = targetLocation.add(0*x*Main.dim, -2*Main.dim, 0*z*Main.dim);
        } else if (pointName.equals("topC")) {
            targetLocation = targetLocation.add(-2*x*Main.dim, -2*Main.dim, -2*z*Main.dim);
        } else if (pointName.equals("bottomA")) {
            targetLocation = targetLocation.add(2*x*Main.dim, 0, 2*z*Main.dim);
        } else if (pointName.equals("bottomB")) {
            targetLocation = targetLocation.add(0*x*Main.dim, 0, 0*z*Main.dim);
        } else if (pointName.equals("bottomC")) {
            targetLocation = targetLocation.add(-2*x*Main.dim, 0, -2*z*Main.dim);
        } 
        
        /*
        
                if (pointName.equals("topA")) {
            translate(location.add(2*x*Main.dim, -2*Main.dim, 2*z*Main.dim));
        } else if (pointName.equals("topB")) {
            translate(location.add(0*x*Main.dim, -2*Main.dim, 0*z*Main.dim));
        } else if (pointName.equals("topC")) {
            translate(location.add(-2*x*Main.dim, -2*Main.dim, -2*z*Main.dim));
        } else if (pointName.equals("bottomA")) {
            System.out.println("Hello");
            translate(location.add(2*x*Main.dim, 0, 2*z*Main.dim));
        } else if (pointName.equals("bottomB")) {
            translate(location.add(0*x*Main.dim, 0, 0*z*Main.dim));
        } else if (pointName.equals("bottomC")) {
            translate(location.add(-2*x*Main.dim, 0, -2*z*Main.dim));
        } 
        */

    }
      
    public boolean isLego() {
        return true;
    }
}
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
public class Square extends DigitalPart {
//    private Geometry digitalTwin;
    public Square() {
        // create dummy square
    }
    
    public Square(String name, String color, AssetManager assetManager) {
        box = new Box(2*Main.dim, Main.dim, 2*Main.dim);
        geom = new Geometry("Box", box);
        super.setMaterial(color, assetManager, geom);
        node.attachChild(geom);
        setDimensions();        
        for(int i = 0; i<4; i++) {
            Cylinder c = new Cylinder(20, 20, Main.dim/2, Main.dim/2, true);
            Geometry g = new Geometry("C", c);
            g.rotate(FastMath.HALF_PI, 0, 0);
            g.setLocalTranslation(Main.dim * (1 - 2*(i/2)), Main.dim, Main.dim * (1 - 2*(i%2)));
            super.setMaterial(color, assetManager, g);
            node.attachChild(g);
        }
        
//        Main.pivot.attachChild(node);
        location = new Vector3f(0,0,0);
        id = name;

        
  /*      // create digital twin
        Box box2 = new Box(2*Main.dim, Main.dim, 2*Main.dim);
        digitalTwin = new Geometry("Box2", box2);
        super.setMaterial("red", assetManager, digitalTwin);*/
    }
/*    
    // returns the digital twin
    public Square(Geometry twin, Vector3f assemblyLoc) {
        geom = twin;
        assemblyLocation = assemblyLoc;
    }
    
    public Square createDigitalTwin() {
        return new Square(digitalTwin, assemblyLocation);
    }

    public void putToMap() {
        Main.map.put(id,this);
    }
  */  
    public Vector3f getConnectionPointTop() {
        return getConnectionPoint("top");
    }
    
    public Vector3f getConnectionPoint(String pointName) {
        if (pointName.equals("top")) {
            return targetLocation.add(0, 2*Main.dim, 0);
        } else {
            return targetLocation;
        }
    }
    
    public void translateConnectionPoint(String pointName) {
        // if we are connecting the bottom, no need to translate since the "location" is at the bottom
        if(pointName.equals("top")) {
            targetLocation = targetLocation.add(0, -2*Main.dim, 0);
        }
    }
    
    public boolean isRectangle() {
        return false;
    }

    public boolean isLego() {
        return true;
    }    
}

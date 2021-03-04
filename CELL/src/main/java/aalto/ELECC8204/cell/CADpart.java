/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aalto.ELECC8204.cell;

import com.jme3.asset.AssetManager;
import com.jme3.math.FastMath;
import com.jme3.math.Vector3f;
import com.jme3.scene.Spatial;

import java.util.HashMap;

/**
 *
 * @author ssierla
 */
public class CADpart extends DigitalPart {
    private Spatial spatial;
    
    private static HashMap<String,Vector3f> map = new HashMap<String,Vector3f>();

    // the key is the concatenation of the "typeName" given in the constructor and "paramName"
    public String getKey3dParam(String paramName) {
        return typeName + paramName;
    }
    
    // these are parameters related to the type of CAD part (i.e. AML system unit class) rather than the instance
    public static void add3dParam(String key3dParam, String coords) {
        String words[] = coords.split(",");
        Vector3f v = new Vector3f(Float.parseFloat(words[0]), Float.parseFloat(words[1]), Float.parseFloat(words[2]));
        map.put(key3dParam, v);
//        System.out.println(v.toString());
    }

    public static Vector3f get3dParam(String key3dParam) {
        return map.get(key3dParam);
    }
    
    // one instance of this class corresponds to an instance of a AML system unit class. "typeName" is the name of the system unit class
    public CADpart(String typeName, String name, String color, AssetManager assetManager, String orient) {
        String words[] = orient.split(",");
        orientationZero = true;
        for (int i=0; i<3; i++) {
            if (Integer.parseInt(words[i])!= 0)         {
                orientationZero = false;
                if(i==0) {
                    rotationAxis = "x";
                } else if(i==1) {
                    rotationAxis = "y";
                } else if(i==2) {
                    rotationAxis = "z";
                }
            }
        }
        orientation = new Vector3f(FastMath.HALF_PI * Float.parseFloat(words[0]), FastMath.HALF_PI * Float.parseFloat(words[1]), FastMath.HALF_PI * Float.parseFloat(words[2]));
        
        this.typeName = typeName;
        id = name;
        if (typeName.equals("FacePlateBackType")) {
            spatial = assetManager.loadModel("Models/Faceplate_back.j3o");
        } else if (typeName.equals("BoltAngularType")) {
            spatial = assetManager.loadModel("Models/Bolt_angular.j3o");            
        } else if (typeName.equals("BoltType")) {
            spatial = assetManager.loadModel("Models/Bolt.j3o");
        } else if (typeName.equals("ShaftType")) {
            spatial = assetManager.loadModel("Models/Shaft.j3o");
        } else if (typeName.equals("PendulumType")) {
            spatial = assetManager.loadModel("Models/Pendulum.j3o");
        } else if (typeName.equals("FacePlateFrontType")) {
            spatial = assetManager.loadModel("Models/FaceplateFront.j3o");
        } else if (typeName.equals("printer")) {
            spatial = assetManager.loadModel("Models/Printer3D.j3o");
        }
//        Vector3f v1 = spatial.getLocalTranslation();
        spatial.center();
//        Vector3f v2 = spatial.getWorldBound().getCenter().subtract(v1);
//        System.out.println(v2.toString());
        super.setMaterial(color, assetManager, spatial);
        node.attachChild(spatial);
        setDimensions();
    }
    
    // one instance of this class corresponds to an instance of a AML system unit class. "typeName" is the name of the system unit class
    public CADpart(String typeName, String name, String color, AssetManager assetManager, String orient, String path) {
        String words[] = orient.split(",");
        orientationZero = true;
        for (int i=0; i<3; i++) {
            if (Integer.parseInt(words[i])!= 0)         {
                orientationZero = false;
                if(i==0) {
                    rotationAxis = "x";
                } else if(i==1) {
                    rotationAxis = "y";
                } else if(i==2) {
                    rotationAxis = "z";
                }
            }
        }
        orientation = new Vector3f(FastMath.HALF_PI * Float.parseFloat(words[0]), FastMath.HALF_PI * Float.parseFloat(words[1]), FastMath.HALF_PI * Float.parseFloat(words[2]));
        
        this.typeName = typeName;
        id = name;
        spatial = assetManager.loadModel(path);
        spatial.center();
        super.setMaterial(color, assetManager, spatial);
        node.attachChild(spatial);
        setDimensions();
    }
    
    public void translateConnectionPoint(String pointName) {
        Vector3f v = CADpart.get3dParam(getKey3dParam(pointName));
        targetLocation = targetLocation.add(v);
    }    

    public Vector3f getConnectionPointTop() {
        return null;
    }
    
    public Vector3f getConnectionPoint(String pointName) {
        Vector3f v = CADpart.get3dParam(getKey3dParam(pointName));
        v.setY(getYextent());
        return targetLocation.add(v);
    }

    public boolean isRectangle() {
        return false;
    }
    
}

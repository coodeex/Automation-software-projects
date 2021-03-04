/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aalto.ELECC8204.cell;

import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Box;

/**
 *
 * @author hp
 */
public class AssemblyStation extends ProductionResource {
    public RobotArm assemblyArm;
    private Box box;
    private Geometry geom;
    Cell cell;
    private final float yExtent = Main.dim*30;
    private float surfaceHeight;
    float x;
    float z;
    private float legoSpacingX = 10*Main.dim;
    private float legoSpacingZ = 10*Main.dim;    
    
    public AssemblyStation(AssetManager assetManager, Node rootNode, Cell c, float xOffset, float zOffset) {
        x = xOffset;
        z = zOffset;
        assemblyArm = new RobotArm("assemblyArm", "orange", assetManager, rootNode, x, z);
        cell = c;
        
        ColorRGBA color = ColorRGBA.LightGray;
        
        Material mat = new Material(assetManager, "Common/MatDefs/Light/Lighting.j3md");
        mat.setBoolean("UseMaterialColors",true);

        mat.setFloat("Shininess", 12f);  // [0,128]


        mat.setColor("Diffuse",color);
        mat.setColor("Specular",color);

//        Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
//        mat.setColor("Color", color);
        
        box = new Box(100*Main.dim, yExtent, 50*Main.dim);
        geom = new Geometry("Box", box);
        geom.setMaterial(mat);
        rootNode.attachChild(geom);
        surfaceHeight = Cell.floorHeight + yExtent*2;
        geom.setLocalTranslation(x, Cell.floorHeight + yExtent, z);
    }
    
    public float getSurfaceHeight() {
        return surfaceHeight;
    }
    
    public void setSlotSpacing(float xSpacing, float zSpacing) {
        legoSpacingX = xSpacing;
        legoSpacingZ = zSpacing;
    }

    public Vector3f slotPosition(int slot, float yExtent) {
        int rowSize = (int)((80*Main.dim)/legoSpacingX);
        int rowIndex = slot % rowSize;
        float xOffset = (rowIndex-1) * legoSpacingX;
        int columnIndex = slot / rowSize;
        float zOffset = (columnIndex + 2) * legoSpacingZ;        
        return new Vector3f(x + xOffset, surfaceHeight+yExtent, z + zOffset - 40*Main.dim); // yExtent for legos: 3*Main.dim
    }    
    
    // y coordinate should be ignored
    public Vector3f getLoadingLocation() {
        return new Vector3f(x+30*Main.dim, 0, z-110*Main.dim);
    }
}

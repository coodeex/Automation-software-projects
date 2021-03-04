/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aalto.ELECC8204.cell;

import com.jme3.math.Vector3f;
import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Box;
import java.util.ArrayList;

/**
 *
 * @author hp
 */
public class LegoBuffer extends ProductionResource {
    private Box box;
    private Geometry geom;
    Cell cell;
    private final float yExtent = Main.dim*30;
    private float surfaceHeight;
    ArrayList<DigitalPart> legos = new ArrayList<DigitalPart>(500);
    float x;
    float z;
//    float legoSpacing = Main.dim*10;
    private float legoSpacingX = 10*Main.dim;
    private float legoSpacingZ = 10*Main.dim;
    int rowSize;
    int columnSize;

    public void setSlotSpacing(float xSpacing, float zSpacing) {
        legoSpacingX = xSpacing;
        legoSpacingZ = zSpacing;
    }
    
    // x coordinate of the lego relative to the center of the buffer
    private float xCoord(int index) {
        int rowIndex = index % rowSize;
        return (rowIndex - rowSize/2) * legoSpacingX;
    }

    // z coordinate of the lego relative to the center of the buffer
    private float zCoord(int index) {
        int columnIndex = index / rowSize;
        return (columnIndex - columnSize/2) * legoSpacingZ;
    }
    
    private Vector3f getLegoLocation(int index) {
        return new Vector3f(x+xCoord(index), surfaceHeight + legos.get(index).getYextent(), z+zCoord(index));
    }
    
    public DigitalPart giveLego(String color, String partType) {
//                                System.out.println(partType);
        DigitalPart lego = null;
        for(int i=0; i<(rowSize*columnSize); i++) {
            lego = legos.get(i);
            if(lego != null) {
                if (partType!= null) {
                    if(!partType.equalsIgnoreCase(lego.typeName)) {
                        continue;
                    }
                }
                if(lego.legoColor.equals(color)) {
                    lego.location = getLegoLocation(i);
                    legos.set(i, null);
                    return lego;
                }
            }
        }        
        return null;
    }

    // y coordinate should be ignored
    public Vector3f getLoadingLocation() {
        return new Vector3f(x, 0, z+90*Main.dim);
    }
    
    public LegoBuffer(String legoType, AssetManager assetManager, Node rootNode, Cell c, float xOffset, float zOffset, int rowSize, int columnSize) {
        this.rowSize = rowSize;
        this.columnSize = columnSize;
        x = xOffset;
        z = zOffset;
        cell = c;
        
        ColorRGBA color = ColorRGBA.LightGray;
                
        Material mat = new Material(assetManager, "Common/MatDefs/Light/Lighting.j3md");
        mat.setBoolean("UseMaterialColors",true);

        mat.setFloat("Shininess", 12f);  // [0,128]


        mat.setColor("Diffuse",color);
        mat.setColor("Specular",color);

//        Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
//        mat.setColor("Color", color);
        
        box = new Box(80*Main.dim, yExtent, 40*Main.dim);
        geom = new Geometry("Box", box);
        geom.setMaterial(mat);
        rootNode.attachChild(geom);
        surfaceHeight = Cell.floorHeight + yExtent*2;
        geom.setLocalTranslation(x, Cell.floorHeight + yExtent, z);
        
        String colorLego = "red";
        if((legoType.equals("rectangle")) || (legoType.equals("square"))) {
            for (int i = 0; i<(rowSize*columnSize/4); i++) {
                for (int j=0; j<4; j++) {
                    if(j==0) {
                        colorLego = "yellow";
                    } else if (j==1) {
                        colorLego = "blue";
                    } else if (j==2) {
                        colorLego = "pink";
                    } else {
                        colorLego = "green";
                    }
                    DigitalPart lego;
                    if(legoType.equals("rectangle")) {
                        lego = (DigitalPart) new Rectangle("rect" + Integer.toString(i*4+j), colorLego, "east", assetManager);
                    } else if (legoType.equals("square")) { // change this default if there are new kinds of legos added later
                        lego = (DigitalPart) new Square("square" + Integer.toString(i*4+j), colorLego, assetManager);
                    } else {
                        lego = null;
                    }
                    if(lego != null) {
                        legos.add(lego);
                        rootNode.attachChild(lego.node);
                    }
                }
            }
        } else if (legoType.equalsIgnoreCase("cranfield")) {
            legoSpacingX = 20*Main.dim;
            legoSpacingZ = 20*Main.dim;
            String partType = " ";
            for (int i = 0; i<(rowSize*columnSize/6); i++) {
                for (int j=0; j<6; j++) {
                    if(j==0) {
                        //partType = "faceplateback";
                        partType = "FacePlateBackType";
                        colorLego = "pink";
                    } else if (j==1) {
                        //partType = "boltangular";
                        partType = "BoltAngularType";
                        colorLego = "green";
                    } else if (j==2) {
                        //partType = "shaft";
                        partType = "ShaftType";
                        colorLego = "green";
                    } else if(j==3) {
                        colorLego = "pink";
                        //partType = "faceplatefront";
                        partType = "FacePlateFrontType";
                    } else if (j==4) {
                        colorLego = "green";
                        //partType = "bolt";
                        partType = "BoltType";
                    } else if (j==5) {
                        colorLego = "yellow";
                        //partType = "pendulum";
                        partType = "PendulumType";
                    }
                    DigitalPart lego;
                    lego = (DigitalPart) new CADpart(partType, "CADpart" + Integer.toString(i*4+j), colorLego, assetManager, "0,0,0");

                    if(lego != null) {
                        legos.add(lego);
                        rootNode.attachChild(lego.node);
                    }
                }
            }
        }
            
        for(int i=0; i<(rowSize*columnSize); i++) {
            legos.get(i).node.setLocalTranslation(getLegoLocation(i));
        }
    }
    
    public float getSurfaceHeight() {
        return surfaceHeight;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aalto.ELECC8204.cell;

import com.jme3.math.ColorRGBA;
import com.jme3.scene.VertexBuffer;
import com.jme3.scene.Mesh;
import com.jme3.asset.AssetManager;
import com.jme3.material.Material;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import java.util.ArrayList;
import com.jme3.util.BufferUtils;

/**
 *
 * @author hp
 */
public class Cell {
    ArrayList<AssemblyStation> assemblies = new ArrayList<AssemblyStation>(20);
    ArrayList<MobileRobot> mobiles = new ArrayList<MobileRobot>(20);    
    ArrayList<LegoBuffer> buffers = new ArrayList<LegoBuffer>(20);
    int numAssemblies = 0;
    int numBuffers = 0;
    int numMobiles = 0;
    AssetManager manager;
    Node root;
    Node node = new Node();
    public static float floorHeight = -15;
    
    public void attachNodeToRoot(boolean attach) {
        if (attach) {
            root.attachChild(node);
        } else {
            root.detachChild(node);
        }
    }
    
    public Cell(AssetManager assetManager, Node rootNode) {
        manager = assetManager;
        root = rootNode;
        node.attachChild(createFloor());
    }
    
    public void execute() {
        mobiles.get(0).execute();
    }
    
    public LegoBuffer getRectangleBuffer() {
        return buffers.get(0);
    }

    public LegoBuffer getSquareBuffer() {
        return buffers.get(1);
    }

    public MobileRobot getMobileRobot() {
        return mobiles.get(0);
    }

    public AssemblyStation getAssemblyStation() {
        return assemblies.get(0);
    }
    
    public void addAssemblyStation(float x, float z) {
        assemblies.add(new AssemblyStation(manager, root, this, x, z));
        numAssemblies++;
    }

    public void addMobileRobot(float x, float z) {
        mobiles.add(new MobileRobot(manager, node, this, x, z));
        numMobiles++;
    }
    
    public void addLegoBuffer(String legoType, float x, float z, int rowSize, int columnSize) {
        buffers.add(new LegoBuffer(legoType, manager, node, this, x, z, rowSize, columnSize));
        numBuffers++;        
    }
    
    public AssemblyStation requestAssemblyStation() {
        return assemblies.get(0);
    }
    
    public Geometry createFloor() {
        Mesh m = new Mesh();

        float floorSize = 200; 

        // Vertex positions in space
        Vector3f [] vertices = new Vector3f[4];
        vertices[0] = new Vector3f(-1 * floorSize, floorHeight,-1 * floorSize);
        vertices[1] = new Vector3f(floorSize, floorHeight,-1 * floorSize);
        vertices[2] = new Vector3f(-1 * floorSize, floorHeight,floorSize);
        vertices[3] = new Vector3f(floorSize, floorHeight, floorSize);

        // Indexes. We define the order in which mesh should be constructed
        short[] indexes = {2, 3, 1, 1, 0, 2};

        // Setting buffers
        m.setBuffer(VertexBuffer.Type.Position, 3, BufferUtils.createFloatBuffer(vertices));
        m.setBuffer(VertexBuffer.Type.Index, 1, BufferUtils.createShortBuffer(indexes));
        m.updateBound();

        // *************************************************************************
        // First mesh uses one solid color
        // *************************************************************************

        // Creating a geometry, and apply a single color material to it
        Geometry geom = new Geometry("OurMesh", m);
        Material mat = new Material(manager, "Common/MatDefs/Misc/Unshaded.j3md");
        mat.setColor("Color", ColorRGBA.DarkGray);
        geom.setMaterial(mat);

        // Attaching our geometry to the root node.
        return geom;        
    }
}


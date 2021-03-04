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
import java.util.concurrent.ArrayBlockingQueue;

/**
 *
 * @author ssierla
 */
public class MobileRobot {
    private float xPos;
    private float xTarget;
    private float zPos;
    private float zTarget;    
    private float step = 0.9f;
    private Node node = new Node();
    public RobotArm arm;
    private Box box;
    private Geometry geom;
    Cell cell;
    private final float yExtent = Main.dim*25;
    private float surfaceHeight;
    private int phase = 0;
    private ArrayBlockingQueue<DigitalPart> queue = new ArrayBlockingQueue<DigitalPart>(1000);
    private DigitalPart[] next10Legos = new DigitalPart[10];
    private int numOnBoard;
    private int index;
    private boolean takingLego = false;
    private Trajectory trajectory;
    private DigitalPart lego; // the lego being handled
    private float maxHeight = 4;
    private boolean gotoLego = false;
    private boolean trajectoryMotion = false;
    private boolean legoMove = false;
    private float legoSpacing = Main.dim*10;
    Vector3f trajectoryPoint;
    public boolean start = false;
    
    public void execute() {
        if(!start) {
            return;
        }
        if ((phase == 0) || (phase == 2) || (phase == 4)) { // goto rect buffer (phase 0) or square buffer (phase 2) or assembly station (phase 4)
            if(!move()) {
                if (phase == 0) {
                    phase = 1;
                } else if (phase == 2) {
                    phase = 3;
                } else {
                    phase = 5;
                }
                index = 0;
                if(phase != 1) {
                    return; // we poll the queue only once in the work cycle of the mobile robot i.e. when we are at the rect buffer
                }
                for (numOnBoard = 0; numOnBoard<10; numOnBoard++) {
                    if(queue.isEmpty()) {
			break;
                    }
                    next10Legos[numOnBoard] = queue.poll();
                }
            }
        } else if ((phase == 1) || (phase == 3) || (phase ==5)) { // pickup legos from rect buffer (phase 1= or square buffer (phase 3) or place them on assembly buffer (phase 5)
            if(gotoLego) { // moving to lego start position
                gotoLego = arm.move();

                if(!gotoLego) {
                    lego.connectArm(arm);
//                    lego.node.setLocalTranslation(0, -4.0f * Main.dim, 0);                    
                    lego.node.setLocalTranslation(0, -3.0f * Main.dim - lego.getYextent(), 0);                    
                    legoMove = true;
                    trajectoryMotion = true;  
                }
                return; // we do not go on to do any of the other motions that apply to the case that we have gripped a lego
            }
            
            if(trajectoryMotion) {
                if(legoMove) {
                    legoMove = arm.move();
                    return;
                }
                
                trajectoryPoint = trajectory.nextPoint();

                if(trajectoryPoint == null) { // we are done with this lego
                    trajectoryMotion = false;
                    lego.location = lego.node.getWorldTranslation();
                    trajectoryMotion = false;
                    lego.disconnectArm2();

                    node.attachChild(lego.node);
                    Vector3f offset = lego.node.getLocalTranslation().subtract(lego.node.getWorldTranslation());
                    lego.node.setLocalTranslation(offset.add(slotPosition(index)).add(new Vector3f(0, -6*Main.dim, 0)));
                    takingLego = false;
                    if(phase == 5) {
                        lego.state = LegoState.AtAssembly;     
                        lego.twin.receivePhysicalLego(lego.id);
                        node.detachChild(lego.node);
                    } else {
                        lego.state = LegoState.OnBoard;
                    }
                } else {
                    arm.initMove(trajectoryPoint, null);
                    legoMove = true;
                    return;
                }
            }            
            
            if(!takingLego) {
//                RobotArm.step = 0.9f;
                while(index<10) {
                    if(next10Legos[index]==null) {
                        index++;
                        continue;
                    }
                    if((phase == 5) && (next10Legos[index].state == LegoState.OnBoard)) {                    
                        break; // now next10Legos[index] is the rectangle lego that we want to pick                        
                    }
                    if(next10Legos[index].state != LegoState.ToBeFetched) {
                        index++;
                        continue;
                    }
                    if(((phase == 1) && (next10Legos[index].isRectangle())) || ((phase == 3) && (!next10Legos[index].isRectangle()))) {
                        break; // now next10Legos[index] is the  lego that we want to pick
                    }
                    if((phase == 1) && (next10Legos[index].isLego() == false)) {
                        break; // at the moment we assume that all non-lego parts are picked up in phase 1
                    }
                    index++;
                }

                if(index>=10) {
                    index = 0;
                    if (phase == 1) {
                        if((Main.product == 0) || (Main.product == 1)) {
                            phase = 2;
                            initMove(cell.getSquareBuffer().getLoadingLocation());                        
                        } else {
                        phase = 4;
                        initMove(cell.getAssemblyStation().getLoadingLocation());                                     
                        }
                    } else if (phase == 3) {
                        phase = 4;
                        initMove(cell.getAssemblyStation().getLoadingLocation());         
                    } else {
                        phase = 0;
                        initMove(cell.getRectangleBuffer().getLoadingLocation());                        
                    }
                    return;
                }

                Vector3f startLoc = next10Legos[index].startLocation;
                Vector3f assemblyLoc = next10Legos[index].assemblyLocation;
                DigitalTwin t = next10Legos[index].twin;
                String identity = next10Legos[index].id;
                Vector3f gripperOffset = new Vector3f(0, next10Legos[index].getYextent()+Main.dim, 0);
//                System.out.print(Integer.toString(index) + next10Legos[index].legoColor + "  ");
                if (phase == 1) {
                    next10Legos[index] = cell.getRectangleBuffer().giveLego(next10Legos[index].legoColor, next10Legos[index].typeName);

                } else if (phase == 3) {
                    next10Legos[index] = cell.getSquareBuffer().giveLego(next10Legos[index].legoColor, next10Legos[index].typeName);                    
                }

                lego = next10Legos[index];
//                                System.out.println(next10Legos[index].legoColor);
                if ((phase ==1) || (phase == 3)) {
                    lego.startLocation = startLoc;
                    lego.assemblyLocation = assemblyLoc;
                    lego.twin = t;
                    lego.id = identity;
                }
                trajectory = new Trajectory();
                lego.moving = false;
                if(phase == 5) {
//                    assemblyArm.initMove(lego.startLocation.add(lego.getGripperOffset()), this);
//                    arm.initMove(slotPosition(index).add(new Vector3f(0, 2.0f*Main.dim, 0)), null);
                    arm.initMove(slotPosition(index).add(gripperOffset), null);
                    gotoLego = true;
                    Vector3f v0 = new Vector3f(slotPosition(index));
                    v0.setY(maxHeight);
                    trajectory.addPoint(v0);
/*                    Vector3f v1 = new Vector3f(slotPosition(index));
                    v1.setY(maxHeight);
                    trajectory.addPoint(v1);*/
                    Vector3f v2 = new Vector3f(lego.startLocation);
                    v2.setY(maxHeight);
                    trajectory.addPoint(v2);
                    trajectory.addPoint(lego.startLocation);                    
                } else {
                    arm.initMove(lego.location.add(gripperOffset), null);
//                    arm.initMove(lego.location.add(new Vector3f(0, 2.0f*Main.dim, 0)), null);
                    gotoLego = true;
                    Vector3f v0 = new Vector3f(lego.location);
                    v0.setY(maxHeight);
                    trajectory.addPoint(v0);
                    Vector3f v1 = new Vector3f(lego.location);
                    v1.setY(maxHeight);
                    trajectory.addPoint(v1);
                    Vector3f v2 = new Vector3f(slotPosition(index));
                    v2.setY(maxHeight);
                    trajectory.addPoint(v2);
                    trajectory.addPoint(slotPosition(index));
                }

                trajectory.initTrajectory();
                trajectoryMotion = false;  
                takingLego = true;
            }
        }
    }

    private Vector3f slotPosition(int slot) {
        int rowSize = 5;
        int columnSize = 2;
        int rowIndex = slot % rowSize;
        float xOffset = (rowIndex - rowSize/2) * legoSpacing;
        int columnIndex = slot / rowSize;
        float zOffset = (columnIndex - columnSize/2) * legoSpacing;        
//        return new Vector3f(xPos + xOffset, surfaceHeight+3*Main.dim, zPos + zOffset);
        return new Vector3f(xPos + xOffset, surfaceHeight+next10Legos[index].getYextent()+6*Main.dim, zPos + zOffset);
    }
    
    public void addToQueue(DigitalPart l) {
        queue.add(l);		
    }
    
    public MobileRobot(AssetManager assetManager, Node rootNode, Cell c, float x, float z) {
        arm = new RobotArm("arm", "magenta", assetManager, rootNode, x+35*Main.dim, z);
        node.attachChild(arm.getMasterNode());
        cell = c;
        xPos = x;
        zPos = z;
        ColorRGBA color = ColorRGBA.LightGray;
        
        Material mat = new Material(assetManager, "Common/MatDefs/Light/Lighting.j3md");
        mat.setBoolean("UseMaterialColors",true);

        mat.setFloat("Shininess", 12f);  // [0,128]


        mat.setColor("Diffuse",color);
        mat.setColor("Specular",color);

//        Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
//        mat.setColor("Color", color);
        
        box = new Box(50*Main.dim, yExtent, 40*Main.dim);
        geom = new Geometry("Box", box);
        geom.setMaterial(mat);
        node.attachChild(geom);
        rootNode.attachChild(node);
        surfaceHeight = Cell.floorHeight + yExtent*2;
        geom.setLocalTranslation(x, Cell.floorHeight + yExtent, z);
        initMove(cell.getRectangleBuffer().getLoadingLocation());
    }    
    
    public void initMove(Vector3f destination) {
        xTarget = destination.getX();
        zTarget = destination.getZ();        
    }
    
    public boolean move() {
        float xDistance = xTarget - xPos;
        float zDistance = zTarget - zPos;
        
        boolean xReady = false;
        boolean zReady = false;
        
        float x;
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

        node.setLocalTranslation(node.getLocalTranslation().add(new Vector3f(x, 0, z)));
        xPos+=x;
        zPos+=z;
//                  Main.helloText.setText(Float.toString(zDistance));
        if(xReady && zReady) {
            return false; //i.e. not moving anymore
        } else {
            return true;
        }
    }    
    
}

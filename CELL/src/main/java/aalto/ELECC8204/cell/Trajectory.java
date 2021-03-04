package aalto.ELECC8204.cell;

import java.util.ArrayList;
import com.jme3.math.Vector3f;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ssierla
 */
public class Trajectory {
    ArrayList<Vector3f> points;
    boolean[] rotate = new boolean[100]; // instead of doing a linear motion to the next point, you do a rotation
    int index;
    int size;
    
    public Trajectory() {
        points = new ArrayList<Vector3f>(20);
        index = 0;
    }
    
    public void addPoint(Vector3f v) {
        points.add(v);
        rotate[index] = false;
        index++;
    }

    public void addRotateMove(Vector3f v) {
        points.add(v);
        rotate[index] = true;
        index++;
    }
    
    public void initTrajectory() {
        index = 0;
        size = points.size();
    }
    /*
    public boolean atFirstPoint() {
        if (index == 0) {
            return true;
        } else {
            return false;
        }
    }*/
    
    public Vector3f nextPoint() {
        if (index >= size) {
            return null;
        }
        index++;
        return points.get(index-1);
    }
    
    public boolean rotationMotion() {
        return rotate[index-1];
    }
}
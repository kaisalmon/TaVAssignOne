/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tavassignone;

/**
 *
 * @author Martina
 */
public class SpeedTorqueObj {
   private double speed;
   private double torque;
    
    public SpeedTorqueObj(double torque, double speed) {
        this.speed = speed;
        this.torque = torque;
    }
    
       public double getSpeed(){
        return speed;
    }
    
    public double getTorque(){
        return torque;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tavassignone;

/**
 *
 * @author Kai
 */
public class SpeedTorque {
    private float speed;
    private float torque;  
    
    public static final byte start_delimiter = 9; //8 bit delimiter
    public static final byte torque_delimiter = 10;//7 bit delimiter
    public static final byte speed_dist_delimiter = 11;//7 bit delimiter

    public SpeedTorque(float speed, float torque) {
        this.speed = speed;
        this.torque = torque;
    }
    
    
}

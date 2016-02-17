/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import tavassignone.Car;
import tavassignone.SpeedTorqueObj;

/**
 *
 * @author Kai
 */
class CarInterface {

    static SpeedTorqueObj receiveData() {    	
    	Car car = new Car();
    	SpeedTorqueObj data = new SpeedTorqueObj(car.getSpeed(), car.getTorque());
        return data;
    }

    static void send(double torque, double ir, double uv) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

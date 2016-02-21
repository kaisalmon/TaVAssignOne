/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import tav.Car;
import tav.SensorData;
import tav.SpeedTorque;
import tav.SpeedTorqueObj;
import static org.mockito.Mockito.*;

/**
 *
 * @author Kai
 */
class CarInterface {

    static SpeedTorqueObj receiveData() throws IOException {
        Car car = new Car();
        ByteArrayOutputStream stream = car.getSpeedTorque();
        if (SpeedTorque.isValidStream(stream)){
            return SpeedTorque.readSpeedTorque(stream);
        } else {
            SpeedTorqueObj invalid = new SpeedTorqueObj(-1, -1);
            return invalid;
        }
    }

    static void send(double torque, double ir, double uv) throws IOException {
        Car car = new Car();
        SensorData data = new SensorData(torque, ir, uv);
        ByteArrayOutputStream s = SensorData.getSensorData(data);
        System.out.println("Here: "+s.toByteArray());
        
        if(SensorData.isValidStream(s)){
            car.recieveData(s);
        } else {
            System.out.println("Invalid Stream");
        }
        
    }
   
}

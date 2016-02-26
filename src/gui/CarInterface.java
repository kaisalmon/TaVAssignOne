/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import tavassignone.Car;
import tavassignone.SensorData;
import tavassignone.SpeedTorque;
import tavassignone.SpeedTorqueObj;

/**
 *
 * @author Kai, Kristiyan & Martina
 */
class CarInterface {
	
	static Car dummyCar;
	
	public static void setCar(Car car){
		dummyCar = car;
	}

        /*
        Summary:
                Returns an object with the car's speed and angle values.
        
        tc0:    A valid packet is received
        tc1:    An incomplete packet or a packet of invalid length is received
        tc2:    A corrupt packet is received
        */
    static SpeedTorqueObj receiveData() throws Exception {
    	
    	ByteArrayOutputStream stream = dummyCar.getSpeedTorque();
    	if (SpeedTorque.isValidStream(stream)){
    		return SpeedTorque.readSpeedTorque(stream);
    	} else {
    		SpeedTorqueObj invalid = new SpeedTorqueObj(-1, -1);
    		return invalid;
    	}
    	

    }
    /*
    Summary:
            Originally contained the functionality in sendValid. Another method
            was created in order to test previously untested functionality.
    
    tc0: The sensor values result in a data packet of the correct length and 
         with correct delimiters.
    */
    static void send(double torque, double ir, double uv) throws Exception {
    	SensorData data = new SensorData(torque, ir, uv);
    	ByteArrayOutputStream s = SensorData.getSensorData(data);
        
        sendValid(s);
        
        
    	
    }
    
    /*
    Summary: Method written to separate untested functionality from the original
            send method. If the stream is valid, it gets sent to the car. If not,
            the stream is set to null before it's sent. The possibility of getting
            null is handled by the car.(?)
    Pre-condition:
            sendValid receives a stream.
    Post-condition:
            Either the original stream is sent, or the car receives a stream of 
            value null.
    tc0: The stream is corrupt
    tc1: The stream is empty
    tc2: The stream is valid
    */
    public static void sendValid(ByteArrayOutputStream stream) {
        if(SensorData.isValidStream(stream)){
    		dummyCar.receiveData(stream);
                byte[] array = stream.toByteArray();
                System.out.println("Sent data: "+ Arrays.toString(array));
    	} else {
    		stream.reset();
    		dummyCar.receiveData(stream);
                System.out.println("Empty stream sent");
    	}
        
    }
   
}

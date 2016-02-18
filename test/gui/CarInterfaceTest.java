/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.junit.Test;

import static org.junit.Assert.*;
import tavassignone.Car;
import tavassignone.SpeedTorqueObj;
import static org.mockito.Mockito.*;

/**
 *
 * @author Kai
 */
public class CarInterfaceTest {
    
    public CarInterfaceTest() {
    }
    
    /**
     * Test of receiveData method, of class CarInterface.
     */
    @Test
    public void testReceiveData() {
    	
    	SpeedTorqueObj test2 = mock(SpeedTorqueObj.class);
    	when(test2.getSpeed()).thenReturn((double) 12);
    	when(test2.getTorque()).thenReturn((double) 13);
    	
        System.out.println("receiveData");
        
        double expResultSpeed = test2.getSpeed();
        double expResultTorque = test2.getTorque();
    	
        double resultOne = CarInterface.receiveData().getSpeed();
        double resultTwo = CarInterface.receiveData().getTorque();
        
        assertEquals(expResultSpeed,0.1, resultOne);
        assertEquals(expResultTorque,0.1, resultTwo);
  
    }

    /**
     * Test of send method, of class CarInterface.
     */
    @Test
    public void testSend() throws IOException {
    	boolean result;
    	boolean expResult[] = {true, false, true};
    	
    	Car car = mock(Car.class);
   
    	//Test case 0: check if correct torque was sent
    	for(int i = 0; i < 3; i++){
    		
    		CarInterface.send(i, 0, 0);
    		
    		if(i != 1){
    			when(car.checkTorque()).thenReturn((double) i);
    		} else {
    			when(car.checkTorque()).thenReturn((double) i+1);
    		}
    		
    		if(car.checkTorque() == i){
        		result = true;
        	} else {
        		result = false;
        	}
    		
    		assertEquals(expResult[i], result);	
    	}
    	
    	//Text case 1: check if correct IR was sent
    	for(int i = 0; i < 3; i++){
    		
    		CarInterface.send(0, i, 0);
    		
    		if(i != 1){
    			when(car.checkIR()).thenReturn((double) i);
    		} else {
    			when(car.checkIR()).thenReturn((double) i+1);
    		}
    		
    		if(car.checkIR() == i){
        		result = true;
        	} else {
        		result = false;
        	}
    		
    		assertEquals(expResult[i], result);	
    	}
    	
    	//Test case 2: check if correct UV was sent
    	for(int i = 0; i < 3; i++){
    		
    		CarInterface.send(0, 0, i);
    		
    		if(i != 1){
    			when(car.checkUV()).thenReturn((double) i);
    		} else {
    			when(car.checkUV()).thenReturn((double) i+1);
    		}
    		
    		if(car.checkUV() == i){
        		result = true;
        	} else {
        		result = false;
        	}
    		
    		assertEquals(expResult[i], result);	
    	}
    	
    }
    
}

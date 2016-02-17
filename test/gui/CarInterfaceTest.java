/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import org.junit.Test;

import static org.junit.Assert.*;
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
    public void testSend() {
        System.out.println("send");
        double torque = 0.0;
        double ir = 0.0;
        double uv = 0.0;
        CarInterface.send(torque, ir, uv);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
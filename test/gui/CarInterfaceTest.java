/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import org.junit.Test;
import static org.junit.Assert.*;
import tavassignone.SpeedTorqueObj;

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
        System.out.println("receiveData");
        SpeedTorqueObj expResult = null;
        SpeedTorqueObj result = CarInterface.receiveData();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
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

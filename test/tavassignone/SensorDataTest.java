/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tavassignone;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Kai
 */
public class SensorDataTest {

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    /**
     * Test of getSensorData method, of class SensorData.
     */
    @Test
    public void testGetSensorData() throws IOException {
        System.out.println("getSensorData");
        SensorData sensorData = new SensorData(0.5d, 23d, 7.25d);
        
        byte[] expResult = {0x1,0x3,0x4};
        ByteArrayOutputStream result = SensorData.getSensorData(sensorData);
        
        byte[] resultBytes = result.toByteArray();
        
        if(resultBytes.length != expResult.length)
            fail("The test case result had incorrect length");
        else{
            for(int i = 0; i < resultBytes.length; i++)
                assertEquals(expResult[i], resultBytes[i]);   
        }
    }

    /**
     * Test of isValidStream method, of class SensorData.
     */
    @Test
    public void testIsValidStream() {
        System.out.println("isValidStream");
        ByteArrayOutputStream stream = null;
        boolean expResult = false;
        boolean result = SensorData.isValidStream(stream);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}

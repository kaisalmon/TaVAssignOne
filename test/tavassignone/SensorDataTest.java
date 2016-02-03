/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tavassignone;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Kai
 */
public class SensorDataTest {
    /**
     * Test of getSensorData method, of class SensorData.
     */
    @Test
    public void testGetSensorData() throws IOException {
        System.out.println("getSensorData");
        SensorData sensorData = new SensorData(0.5d, 23d, 7.25d);
        
        byte[] expResult = {0x3f000000,0x41b80000,0x40e80000};
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

    /**
     * Test of has_even_bits method, of class SensorData.
     */
    @Test
    public void testHas_even_bits() {
        System.out.println("has_even_bits");
        
        String text = "1100000110011101010111011000101011011000011111111111111111111110"; //42 1 bits
        double d = Double.longBitsToDouble(new BigInteger(text, 2).longValue());
        boolean expResult = true;
        boolean result = SensorData.has_even_bits(d);
        assertEquals(expResult, result);
        
        text = "1100000110011101010111011000101011011000011111111111111111111111"; //43 1 bits
        d = Double.longBitsToDouble(new BigInteger(text, 2).longValue());
        expResult = false;
        result = SensorData.has_even_bits(d);
        assertEquals(expResult, result);
    }

    
}

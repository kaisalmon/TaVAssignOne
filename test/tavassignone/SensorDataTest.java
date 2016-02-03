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
        SensorData sensorData = new SensorData(0.5d, 213d, 7.25d);
        
        byte[] expResult = {1,1,1};
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
    public void testIsValidStream() throws IOException {
        String labels[] = {
            "Valid Stream",
            "Insufficent Length",
            "Incorrect Start Delimeter",
            "Incorrect Torque CheckBit",
            "Incorrect Torque Delimeter",
            "Incorrect IR CheckBit",
            "Incorrect IR Delimeter",
            "Incorrect Ultra CheckBit",
            "Incorrect Ultra Delimeter",
        };
        byte[][] tc = {//array of test cases
            {5, 63, -32, 0, 0, 0, 0, 0, 0, 6, 64, 8, 0, 0, 0, 0, 0, 0, -121, 64, 29, 0, 0, 0, 0, 0, 0, 8},
            {5, 63, -32, 0, 0, 0, 0, 0},
            {105, 63, -32, 0, 0, 0, 0, 0, 0, 6, 64, 8, 0, 0, 0, 0, 0, 0, -121, 64, 29, 0, 0, 0, 0, 0, 0, 8},
            {5, 63, -32, 0, 0, 0, 0, 0, 0, -122, 64, 8, 0, 0, 0, 0, 0, 0, -121, 64, 29, 0, 0, 0, 0, 0, 0, 8},
            {5, 63, -32, 0, 0, 0, 0, 0, 0, 16, 64, 8, 0, 0, 0, 0, 0, 0, -121, 64, 29, 0, 0, 0, 0, 0, 0, 8},
            {5, 63, -32, 0, 0, 0, 0, 0, 0, 6, 64, 8, 0, 0, 0, 0, 0, 0, 7, 64, 29, 0, 0, 0, 0, 0, 0, 8},
            {5, 63, -32, 0, 0, 0, 0, 0, 0, 6, 64, 8, 0, 0, 0, 0, 0, 0, 110, 64, 29, 0, 0, 0, 0, 0, 0, 8},
            {5, 63, -32, 0, 0, 0, 0, 0, 0, 6, 64, 8, 0, 0, 0, 0, 0, 0, -121, 64, 29, 0, 0, 0, 0, 0, 0, -120},
            {5, 63, -32, 0, 0, 0, 0, 0, 0, 6, 64, 8, 0, 0, 0, 0, 0, 0, -121, 64, 29, 0, 0, 0, 0, 0, 0, 14},
        };
        boolean results[] = { //array of expected results
            true,
            false,
            false,
            false,
            false,
            false,
            false,
            false
        };

        for(int i = 0; i<8;i++){
            System.out.println("isValidStream - tc"+i+": "+labels[i]);
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            stream.write(tc[i]);
            boolean expResult = results[i];
            boolean result = SensorData.isValidStream(stream);
            assertEquals(expResult, result);
        }
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

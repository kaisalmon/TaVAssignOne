/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tavassignone;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Kai
 */
public class SpeedTorqueTest {
    
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
            "Incorrect Speed CheckBit",
            "Incorrect Speed Delimeter",
            "Valid Stream with extra bits",
        };
        byte[][] tc = {//array of test cases
            {9, 63, -32, 0, 0, 0, 0, 0, 0, -118, 64, 8, 0, 0, 0, 0, 0, 0, 11},
            {9, 63, -32, 0, 0, 0, 0, 0, 0, -118},
            {5, 63, -32, 0, 0, 0, 0, 0, 0, -118, 64, 8, 0, 0, 0, 0, 0, 0, 11},
            {9, 63, -32, 0, 0, 0, 0, 0, 0, 10, 64, 8, 0, 0, 0, 0, 0, 0, 11},
            {9, 63, -32, 0, 0, 0, 0, 0, 0, 23, 64, 8, 0, 0, 0, 0, 0, 0, 11},
            {9, 63, -32, 0, 0, 0, 0, 0, 0, -118, 64, 8, 0, 0, 0, 0, 0, 0, -117},
            {9, 63, -32, 0, 0, 0, 0, 0, 0, -118, 64, 8, 0, 0, 0, 0, 0, 0, 53},
            {9, 63, -32, 0, 0, 0, 0, 0, 0, -118, 64, 8, 0, 0, 0, 0, 0, 0, 11,1,1,1,1,1},
        };
        boolean results[] = { //array of expected results
            true,
            false,
            false,
            false,
            false,
            false,
            false,
            true
        };

        for(int i = 0; i<8;i++){
            System.out.println("isValidStream - tc"+i+": "+labels[i]);
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            stream.write(tc[i]);
            boolean expResult = results[i];
            boolean result = SpeedTorque.isValidStream(stream);
            assertEquals(expResult, result);
        }
    }
}

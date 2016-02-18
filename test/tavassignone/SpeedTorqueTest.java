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
 * @author Kai & Martina
 */
public class SpeedTorqueTest {
    
    /**
     * Test of readSpeedTorque method, of class SpeedTorque.
     */
    @Test
    public void testReadSpeedTorque() throws IOException {
        /* TC 0 */{
            System.out.println("readSpeedTorque - tc0: Empty stream");
            byte[] b = {};
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            stream.write(b);
            SpeedTorque st = new SpeedTorque();
            SpeedTorqueObj stoResult = st.readSpeedTorque(stream);
            SpeedTorqueObj stoExp = new SpeedTorqueObj(-1, -1);
            assertEquals(stoExp.getSpeed(), stoResult.getSpeed(), 0);
            assertEquals(stoExp.getTorque(), stoResult.getTorque(), 0);
        }
        /* TC 1 */{
        System.out.println("readSpeedTorque - tc1: No full packages");    
        byte[] b = {-32, 0, 0, 0, 0, 0, 0, 10, 64, 8, 0, 0, 0, 0, 0, 0,
                        0, 0, 0, 0, 0, -118, 64, 8, 0, 0, 0, 0, 0, 0, 16, 50,
                        0, 0, 0};
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            stream.write(b);
            SpeedTorque st = new SpeedTorque();
            SpeedTorqueObj stoResult = st.readSpeedTorque(stream);
            SpeedTorqueObj stoExp = new SpeedTorqueObj(-1, -1);
            assertEquals(stoExp.getSpeed(), stoResult.getSpeed(), 0);
            assertEquals(stoExp.getTorque(), stoResult.getTorque(), 0);
        }
        /* TC 2 */{
            System.out.println("readSpeedTorque - tc2: All packets corrupt");
            byte[] b = {9, 63, -32, 0, 0, 0, 0, 0, 0, 10, 64, 8, 0, 0, 0, 0, 0, 0, 11,
                        9, 63, -32, 0, 0, 0, 0, 0, 0, -118, 64, 8, 0, 0, 0, 0, 0, 0, -117};
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            stream.write(b);
            SpeedTorque st = new SpeedTorque();
            SpeedTorqueObj stoResult = st.readSpeedTorque(stream);
            SpeedTorqueObj stoExp = new SpeedTorqueObj(-1, -1);
            assertEquals(stoExp.getSpeed(), stoResult.getSpeed(), 0);
            assertEquals(stoExp.getTorque(), stoResult.getTorque(), 0);
        }
        /* TC 3 */{
            System.out.println("readSpeedTorque - tc3: First packet corrupt");
            byte[] b = {9, 63, -32, 0, 0, 0, 0, 0, 0, 10, 64, 8, 0, 0, 0, 0, 0, 0, 11,
                        9, 63, -32, 0, 0, 0, 0, 0, 0, -118, 64, 8, 0, 0, 0, 0, 0, 0, 11};
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            stream.write(b);
            SpeedTorque st = new SpeedTorque();
            SpeedTorqueObj stoResult = st.readSpeedTorque(stream);
            SpeedTorqueObj stoExp = new SpeedTorqueObj(0.5, 3);
            assertEquals(stoExp.getSpeed(), stoResult.getSpeed(), 0);
            assertEquals(stoExp.getTorq-ue(), stoResult.getTorque(), 0);
        }
        /* TC 4 */{
            System.out.println("readSpeedTorque - tc4: First packet not corrupt");
            byte[] b = {9, 63, -32, 0, 0, 0, 0, 0, 0, -118, 64, 8, 0, 0, 0, 0, 0, 0, 11,
                        9, 63, -32, 0, 0, 0, 0, 0, 0, 10, 64, 8, 0, 0, 0, 0, 0, 0, 11};
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            stream.write(b);
            SpeedTorque st = new SpeedTorque();
            SpeedTorqueObj stoResult = st.readSpeedTorque(stream);
            SpeedTorqueObj stoExp = new SpeedTorqueObj(0.5, 3);
            assertEquals(stoExp.getSpeed(), stoResult.getSpeed(), 0);
            assertEquals(stoExp.getTorque(), stoResult.getTorque(), 0);
        }
         /* TC 5 */{
        System.out.println("readSpeedTorque - tc5: One start derimiter found, no full packet");    
        byte[] b = {9, 63, -32, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8, 0, 0, 0, 0, 0, 0,
                        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 16, 50,
                        0, 0, 0};
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            stream.write(b);
            SpeedTorque st = new SpeedTorque();
            SpeedTorqueObj stoResult = st.readSpeedTorque(stream);
            SpeedTorqueObj stoExp = new SpeedTorqueObj(-1, -1);
            assertEquals(stoExp.getSpeed(), stoResult.getSpeed(), 0);
            assertEquals(stoExp.getTorque(), stoResult.getTorque(), 0);
    }
    
    }
      /**
     * Test of isValidStream method, of class SpeedTorque.
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

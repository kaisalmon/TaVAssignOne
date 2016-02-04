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
public class ReadWriteBitsTest {
    
    public ReadWriteBitsTest() {
    }

    /**
     * Test of writeBits method, of class ReadWriteBits.
     */
    @Test
    public void testWriteBits() throws IOException {
        /*TC 0 */{
            System.out.println("writeBits - tc0");
            int n = 0;
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            stream.write(new byte[]{});
            int expResult = 1;
            int result = ReadWriteBits.writeBits(n, stream);
            assertEquals(expResult, result);
        }
         /*TC 1 */{
            System.out.println("writeBits - tc1");
            int n = -1;
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            stream.write(new byte[]{1,2,3});
            int expResult = 1;
            int result = ReadWriteBits.writeBits(n, stream);
            assertEquals(expResult, result);
        }
         
        /*TC 2 */{
            System.out.println("writeBits - tc2");
            int n = 0;
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            stream.write(new byte[]{1,2,3});
            int expResult = 1;
            int result = ReadWriteBits.writeBits(n, stream);
            assertEquals(expResult, result);
        }
        
         /*TC 3 */{
            System.out.println("writeBits - tc3");
            int n = 6;
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            stream.write(new byte[]{1,2,3});
            int expResult = 1;
            int result = ReadWriteBits.writeBits(n, stream);
            assertEquals(expResult, result);
        }
         /*TC 4 */{
            System.out.println("writeBits - tc4");
            int n = 2;
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            stream.write(new byte[]{1,2,3});
            int expResult = 0;
            int result = ReadWriteBits.writeBits(n, stream);
            assertEquals(expResult, result);
        }
        
         /*TC 5 */{
            System.out.println("writeBits - tc5");
            int n = 3;
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            stream.write(new byte[]{1,2,3});
            int expResult = 0;
            int result = ReadWriteBits.writeBits(n, stream);
            assertEquals(expResult, result);
        }
        
    }
    
}


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

    /**
     * Test of writeBits method, of class ReadWriteBits.
     */
    @Test
    public void testWriteBits() {
        /*TC 0 */{
            System.out.println("writeBits - tc0");
            int n = 0;
            String string = "";
            int expResult = 1;
            ReadWriteBits rwBits = new ReadWriteBits();
            int result = rwBits.writeBits(n, string);
            assertEquals(expResult, result);
            assertEquals(rwBits.buffer, "");
        }
         /*TC 1 */{
            System.out.println("writeBits - tc1");
            int n = -1;
            String string = "123";
            int expResult = 1;
            ReadWriteBits rwBits = new ReadWriteBits();
            int result = rwBits.writeBits(n, string);
            assertEquals(expResult, result);
            assertEquals(rwBits.buffer, "");
        }
         
        /*TC 2 */{
            System.out.println("writeBits - tc2");
            int n = 0;
            String string = "123";
            int expResult = 1;
             ReadWriteBits rwBits = new ReadWriteBits();
            int result = rwBits.writeBits(n, string);
            assertEquals(expResult, result);
            assertEquals(rwBits.buffer, "");
        }
        
         /*TC 3 */{
            System.out.println("writeBits - tc3");
            int n = 6;
            String string = "101";
            int expResult = 1;
             ReadWriteBits rwBits = new ReadWriteBits();
            int result = rwBits.writeBits(n, string);
            assertEquals(expResult, result);
            assertEquals(rwBits.buffer, "");
        }
         /*TC 4 */{
            System.out.println("writeBits - tc4");
            int n = 2;
            String string = "101";
            int expResult = 0;
            ReadWriteBits rwBits = new ReadWriteBits();
            int result = rwBits.writeBits(n, string);
            assertEquals(expResult, result);
            assertEquals(rwBits.buffer, "10");
            //assertEquals(rwBits.buffer.get(0).byteValue(), 1);
            //assertEquals(rwBits.buffer.get(1).byteValue(), 2);
        }
        
         /*TC 5 */{
            System.out.println("writeBits - tc5");
            int n = 3;
            String string = "101";
            int expResult = 0;
            ReadWriteBits rwBits = new ReadWriteBits();
            int result = rwBits.writeBits(n, string);
            assertEquals(expResult, result);
            assertEquals(rwBits.buffer, "123");
        }
        
    }

    /**
     * Test of readBits method, of class ReadWriteBits.
     * Add values to buffer.
     */
    @Test
    public void testReadBits() {
        /*TC 0 */{
            System.out.println("readBits - tc0");
            int n = -1;
            ReadWriteBits rwBits = new ReadWriteBits();
            ReadData result = rwBits.readBits(n);
            ReadData expResult = new ReadData(1, "");
            assertEquals(result, expResult);
            
        }
        /*TC 1 */{
            System.out.println("readBits - tc1");
            int n = 0;
            ReadWriteBits rwBits = new ReadWriteBits();
            ReadData result = rwBits.readBits(n);
            ReadData expResult = new ReadData(1, "");
            assertEquals(result, expResult);
        }
        /*TC 2 */{
            System.out.println("readBits - tc2");
            int n = 3;
            ReadWriteBits rwBits = new ReadWriteBits();
            rwBits.buffer = "";
            ReadData result = rwBits.readBits(n);
            ReadData expResult = new ReadData(1, "");
            assertEquals(result, expResult);
        }
        /*TC 3 */{
            System.out.println("readBits - tc3");
            int n = 3; 
            ReadWriteBits rwBits = new ReadWriteBits();
            rwBits.buffer = "100110";
            ReadData result = rwBits.readBits(n);
            ReadData expResult = new ReadData(0, "100");
            assertEquals(result, expResult);
        }
        /*TC 4 */{
            System.out.println("readBits - tc4");
            int n = 3;    
            ReadWriteBits rwBits = new ReadWriteBits();
            rwBits.buffer = "10";
            ReadData result = rwBits.readBits(n);
            ReadData expResult = new ReadData(1, "");
            assertEquals(result, expResult);
        }
    }
    
}


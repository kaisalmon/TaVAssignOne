package tavassignone;
import java.io.*;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;


/**
 *
 * @author Holtza
 */
public class ReadWriteBits {
    public String buffer;
    
    /**
     * Description: Appends first n number of bits from bitstream 'stream' into the
     *              end of the input buffer bitstream.
     * Pre-condition: n is non-negative, s is not empty and contains at least n bits
     * Post-condition: All n bits have been inserted at the end of the input buffer
     *                  bitstream. 0 is returned if successful. If not, 1 is returned.
     * Test cases:
     * tc0: The length of 's' is 0.
     * tc1: The length of 's' is > 0 and 'n' < 0.
     * tc2: The length of 's' is > 0 and 'n' == 0.
     * tc3: The length of 's' is > 0 and 'n' > 0.
     * tc4: The length of 's' is > 0, 'n' > 0, and 'n' = length of 's'.
     * tc5: The length of 's' is > 0, 'n' > 0, and 'n' < length of 's'.
     */
    public int writeBits(int n, String string){
        return 0;
    }
	
    /**
     * Description: Removes 'n' bits from the beginning of the output buffer stream.
     * Pre-condition: n is non-negative and smaller than or equal to the length of
     *				  the output buffer stream.
     * Post-condition: 'n' bits are removed from the buffer, and an object containing
     * 					an error code (0 if successful, 1 otherwise) and the removed
     *					bits are returned.
     * Test cases:
     * tc0: 'n' < 0
     * tc1: 'n' == 0
     * tc2: 'n' > 0 && buffer length == 0
     * tc3: 'n' > 0 && buffer length > 0
     * tc4: 'n' > 0 && buffer length > 0 && buffer length < 'n'
     */
     public ReadData readBits(int n){
             ReadData data = new ReadData(0, "101");
             return data;
     }
}

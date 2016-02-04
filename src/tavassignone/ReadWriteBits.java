import java.io.*;
import java.nio.ByteBuffer;
import java.util.Arrays;

/**
 *
 * @author Holtza
 */
public class ReadWriteBits {
    
    /**
     * Description: Appends first n number of bits from bitstream 'stream' into the
     *              end of the input buffer bitstream.
     * Pre-condition: n is non-negative, s is not empty and contains at least n bits
     * Post-condition: All n bits have been inserted at the end of the input buffer
     *                  bitstream. 0 is returned if successful. If not, 1 is returned.
     * Test cases:
     * tc0: The length of 's' is 0.
     * tc1: The length of 's' is > 0 and 'n' < 0.
     * tc2: The length of 's' is > 0 and 'n' = 0.
     * tc3: The length of 's' is > 0 and 'n' > 0.
     * tc4: The length of 's' is > 0, 'n' > 0, and 'n' < length of 's'.
     */
    public static int writeBits(int n, ByteArrayOutputStream stream){
        return 0;
    }
}

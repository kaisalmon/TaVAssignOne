
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

    public String buffer = "";

    /**
     * Description: Appends first n number of bits from bitstream 'stream' into
     * the end of the input buffer bitstream. 
     * Pre-condition: n is non-negative, s is not empty and contains at least n bits 
     * Post-condition: All n bits have been inserted at the end of the input buffer 
     * bitstream. 0 is returned if successful. If not, 1 is returned. 
     * Test cases: 
     * tc0: The length of 's' is 0. 
     * tc1: The length of 's' is > 0 and 'n' < 0.
     * tc2: The length of 's' is > 0 and 'n' == 0. 
     * tc3: The length of 's' is > 0 and 'n' > 0. 
     * tc4: The length of 's' is > 0, 'n' > 0, and 'n' = length of 's'. 
     * tc5: The length of 's' is > 0, 'n' > 0, and 'n' < length of 's'.
     */
    public int writeBits(int n, String stream) throws IOException{
        String temp = "";
        if(stream.length() != 0 && n > 0 && stream.length() >= n){
            
             for(int i = 0; i < n; i++){
                 temp += stream.charAt(i);
             }
 
             buffer = temp;
             
            return 0;
            
        } else {
           return 1;
        }
    }

    /**
     * Description: Removes 'n' bits from the beginning of the output buffer
     * stream. 
     * Pre-condition: n is non-negative and smaller than or equal to the
     * length of the output buffer stream. 
     * Post-condition: 'n' bits are removed from the buffer, and an object 
     * containing an error code (0 if successful,
     * 1 otherwise) and the removed bits are returned. 
     * Test cases: 
     * tc0: 'n' < 0
     * tc1: 'n' == 0
     * tc2: 'n' > 0 && buffer length == 0 
     * tc3: 'n' > 0 && buffer length > 0 
     * tc4: 'n' > 0 && buffer length > 0 && buffer length < 'n'
     */
    public ReadData readBits(int n) {
        int result = 0;
        String read = "";

        //Satisfies test tc0 & tc1
        if (n <= 0) {
            result = 1;
        } else 
            
            for(int i = 0; i < n; i++){
                //Satisfies tc2 and tc4
                if (buffer.equals("")){
                    result = 1;
                    read = "";
                    break;
                }
                
                //Satisfies tc3
                read += buffer.substring(0, 1);
                buffer = buffer.substring(1);
                
            }

        
        ReadData data = new ReadData(result, read);
        return data;
    }
}

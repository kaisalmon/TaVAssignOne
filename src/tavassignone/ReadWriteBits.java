
package tavassignone;

import java.io.*;

/**
 *
 * @author Martina & Kristiyan
 */
public class ReadWriteBits {
    
    public String buffer = "";

    /**
     * Description: Appends first n number of bits from a bitstream (represented
     * as a string) to the end of the buffer. 
     * Pre-condition: n is non-negative, stream is not empty and contains at least n bits 
     * Post-condition: All n bits have been inserted at the end of the buffer.
     * 0 is returned if successful. If not, 1 is returned. 
     * Test cases: 
     * tc0: Stream is empty.
     * tc1: Stream is not empty and 'n' < 0.
     * tc2: Stream is not empty and 'n' == 0. 
     * tc3: Stream is not empty and 'n' > 0. 
     * tc4: Stream is not empty , 'n' > 0, and stream contains 'n' bits 
     * tc5: Stream is not empty, 'n' > 0, and stream contains less than 'n' bits
     * @param n
     * @param stream
     * @return 
     * @throws java.io.IOException
     */
    public int writeBits(int n, String stream) throws IOException{
        String temp = "";
        
        //Satisfies tc4 & tc5
        if(stream.length() != 0 && n > 0 && stream.length() >= n){
            
             for(int i = 0; i < n; i++){
                 temp += stream.charAt(i);
             }
 
             buffer = temp;
             
            return 0;
        
        } else {
            //Satisfies tc0-tc3
           return 1;
        }
    }

    /**
     * Description: Removes 'n' bits from the beginning of buffer (represented
     * as a string). 
     * Pre-condition: n is non-negative and the buffer contains n bits or more.
     * Post-condition: n bits are removed from the buffer, and an object 
     * containing an error code (0 if successful,
     * 1 otherwise) and the read bits are returned. 
     * Test cases: 
     * tc0: 'n' < 0
     * tc1: 'n' == 0
     * tc2: 'n' > 0 and buffer is empty 
     * tc3: 'n' > 0 and buffer is not empty
     * tc4: 'n' > 0 and buffer is not empty and buffer contains less than n bits
     * @param n
     * @return 
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

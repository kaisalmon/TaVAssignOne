
package tavassignone;

import java.io.*;
import java.nio.ByteBuffer;
import java.util.Arrays;
/**
 * Object returned by readBits in class ReadWriteBits.
 * 
 * @author Martina
 */
public class ReadData {
    
    int n;
    String string;
    
    public ReadData(int n, String string){
        this.n = n;
        this.string = string;
}
    
}

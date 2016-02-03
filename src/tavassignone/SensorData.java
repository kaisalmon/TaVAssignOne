/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tavassignone;
import java.io.*;
import java.nio.ByteBuffer;
import java.util.Arrays;
/**
 *
 * @author Kai
 */
public class SensorData {
    public static final byte start_delimiter = 5; //8 bit delimiter
    public static final byte torque_delimiter = 6;//7 bit delimiter
    public static final byte ultra_dist_delimiter = 7;//7 bit delimiter
    public static final byte ir_dist_delimiter = 8;//7 bit delimiter
    
    double torque;
    double ultra_dist;
    double ir_dist;
   
    

    public SensorData(double torque, double ultra_dist, double ir_dist) {
        this.torque = torque;
        this.ultra_dist = ultra_dist;
        this.ir_dist = ir_dist;
    }
    
    /**
     * Description: 
     * Converts a SensorData object into a bitstream.
     * 
     * Pre-Condition: 
     * sensorData is an object with 3 8byte double precision floating point values.
     * 
     * Post-Condition:
     * The returned value is a 28byte byte stream in the following format:
     * [8bit start_delimiter][8byte torque double][check bit for torque]
     * [7bit torque delimiter][8byte ultra_dist double][check bit for ultra_dist]
     * [7bit ultra_dist delimiter][8byte ir_dist double][check bit for ir_dist]
     * [7bit ir_dist delimiter]
     * 
     * The check bit is 1 if there are an odd number of 1-bits in the corresponding double, and 
     * otherwise 0;
     * 
     * Test Cases:
     * tc0: an arbitrary sensor_data object returns the correct, pre-calculated, stream;
     */
    public static ByteArrayOutputStream getSensorData(SensorData sensorData) throws IOException{
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        byte[] bytes = new byte[28];
        bytes[0] = start_delimiter;
        System.arraycopy(toByteArray(sensorData.torque), 0, bytes, 1, 8);
        bytes[9] = torque_delimiter;
        if(has_even_bits(sensorData.torque))
            bytes[9] += Math.pow(2, 7);
        
        System.arraycopy(toByteArray(sensorData.ir_dist), 0, bytes, 10, 8);
        bytes[18] = ir_dist_delimiter;
        if(has_even_bits(sensorData.ir_dist))
            bytes[18] += Math.pow(2, 7);
        
        System.arraycopy(toByteArray(sensorData.ultra_dist), 0, bytes, 19, 8);
        bytes[27] = ultra_dist_delimiter;
        if(has_even_bits(sensorData.ultra_dist))
            bytes[27] += Math.pow(2, 7);
        
        stream.write(bytes);
        return stream;
    }
    
    /**
     * Description: 
     * Checks if a given ByteArrayOutputStream is in a valid format.
     * 
     * Pre-Condition: 
     * A non-null ByteArrayOutputStream is given.
     * 
     * Post-Condition:
     * Returns true if the stream is valid, and otherwise returns false;
     * 
     * Test Cases:
     * tc0: stream with invalid length
     * tc1: stream with correct number of bits, which does not contain the start delimiter in the correct position
     * tc2: stream with correct number of bits, which does not contain the torque delimiter in the correct position
     * tc3: stream with correct number of bits, which does not contain the ultra_dist delimiter in the correct position
     * tc4: stream with correct number of bits, which does not contain the ir_dist delimiter in the correct position
     * tc5: stream with correct number of bits, which does not contain the correct torque check bit with the correct value
     * tc6: stream with correct number of bits, which does not contain the correct ultra_dist check bit with the correct value
     * tc7: stream with correct number of bits, which does not contain the correct ir_dist check bit with the correct value
     * tc8: valid stream
     */
    public static boolean isValidStream(ByteArrayOutputStream stream){
        return false;
    }
    
    
    
    /**
     * Description: 
     * Counts the bits in a double
     * 
     * Pre-Condition: 
     * d is a double
     * 
     * Post-Condition:
     * The check bit is 1 if there are an odd number of 1-bits in the corresponding double, and 
     * otherwise 0;
     * 
     * Test Cases:
     * tc0: a double with an even number of bits;
     * tc1: a double with an odd number of bits;
     */
    public static boolean has_even_bits(double d){
        int evenBits = 0;
        byte[] bytes = toByteArray(d);
        for(int i = 0; i < 8; i++){
            if(bytes[i]%2 == 0)
                evenBits += Integer.bitCount(bytes[i]);
        }
        return evenBits % 2 == 0;
    }
    
    /**
     * Helper function to convert doubles to arrays
     */    
    public static byte[] toByteArray(double value) {
        byte[] bytes = new byte[8];
        ByteBuffer.wrap(bytes).putDouble(value);
        return bytes;
    }

    /**
     * Helper function to convert arrays to doubles
     */
    public static double toDouble(byte[] bytes) {
        return ByteBuffer.wrap(bytes).getDouble();
    }
    
}

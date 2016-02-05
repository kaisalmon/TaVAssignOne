/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tavassignone;

import java.io.*;
import java.nio.ByteBuffer;
import java.util.Arrays;
import static tavassignone.SensorData.has_even_bits;
import static tavassignone.SensorData.toDouble;

/**
 *
 * @author Kai & Martina
 */
public class SpeedTorque {



    public static final byte start_delimiter = 9; //8 bit delimiter
    public static final byte torque_delimiter = 10;//7 bit delimiter
    public static final byte speed_delimiter = 11;//7 bit delimiter
    
    public static final int packetLength = 19; //the length of a full packet

   
    
    /**
     * Description: Given a stream of bytes, searches for the delimiter 
     * specifying the beginning of the 
     * next packet (ignores the bits until there), reads the bits of a whole
     * packet, runs the error detection mechanism to make sure that the packet 
     * is not corrupted and if so, puts the values for speed and torque in an 
     * object and returns its reference. Otherwise, it searches for the next 
     * packet until it finds an uncorrupted packet.
     * 
     * Pre-condition: 'stream' is a ByteArrayOutputStream with one or more packets
     * Post-condition: Returns reference to object with speed and torque if the
     *                  data could be extracted, otherwise a reference to an object
     *                  with value -1 for speed and torque is returned.
     * Test cases:
     * tc0: stream is empty
     * tc1: stream does not contain any full packets
     * tc2: all packets are corrupt
     * tc3: first packet is corrupt, non-corrupt packet later in stream
     * tc4: first packet is not corrupt
     * tc5: startdelimiter found but no full packets in stream
     * @param stream
     * @return 
     */
    public SpeedTorqueObj readSpeedTorque (ByteArrayOutputStream stream){
        double speed = 0;
        double torque = 0;
        int startindex = 0;
        int index = 0;
        boolean validPacketFound = false;
        
        byte[] original = stream.toByteArray();
        byte[] packet = new byte[packetLength];
        
        while(!validPacketFound){
            
            index = packetStartAt(startindex, original);
            
            if (index == -1){
                System.out.println("No packet found.");
                SpeedTorqueObj obj = new SpeedTorqueObj(-1, -1);
                return obj;
            }
            
            System.arraycopy(original, index, packet, 0, packetLength);
            
            startindex ++; //increment in case we need to search for a new start delimiter
            
            ByteArrayOutputStream packetStream = new ByteArrayOutputStream(packetLength);
            try{
            packetStream.write(packet);
            }catch (IOException e){
            }
            
            boolean valid = isValidStream(packetStream); //check if valid stream
            
            if(valid){
                validPacketFound = true;
            }else{
                packet = new byte[packetLength]; //empty array to fill it with new packet
            }
        }
        
        byte[] torqueBytes = new byte[8];
        byte[] speedBytes = new byte[8];
        System.arraycopy(packet, 1, torqueBytes, 0, 8);
        System.arraycopy(packet, 10, speedBytes, 0, 8);
            
        torque = toDouble(torqueBytes);
        speed = toDouble(speedBytes);
        
        SpeedTorqueObj obj = new SpeedTorqueObj(torque, speed);
        return obj;
        
    }
    
    /**
     * Helper function to readSpeedTorque.
     * Start looking at index startindex in byte array b, and returns the index
     * of the first occurence of a start delimiter.
     * 
     * If the array is not long enough to contain a full packet, or if no start 
     * delimiter is found, a negative int is returned.
     * 
     * @param startindex
     * @param b
     * @return 
     */
    public int packetStartAt(int startindex, byte[] b){
        //readSpeedTorque tc 0: stream is empty
        //also readSpeedTorque tc 1: no full packets (when stream ends without
        //finding a full packet)
        if (b.length < packetLength){
            System.out.println("No full packet found");
            return -1;
        }
        
        //Start looking from start index, until end of stream
        //readSpeedTorque tc1
        for (int i = startindex; i < b.length-1; i++){
            //if a start delimiter is found, return the index
            if (b[i] == start_delimiter) return i;
        }
        
        //If no start delimiter is found, return -1
        return -1;
    }

    /**
     * Description: Checks if a given ByteArrayOutputStream is in a valid format.
     *
     * Pre-Condition: A non-null ByteArrayOutputStream is given.
     *
     * Post-Condition: Returns true if the stream is valid, and otherwise returns false;
     *
     * Test Cases: 
     * tc0: stream with invalid length 
     * tc1: stream with correct number of bits, which does not contain the
     * start delimiter in the correct position 
     * tc2: stream with correct number of bits, which does not contain the
     * ir_dist delimiter in the correct position 
     * tc3: stream with correct number of bits, which does not contain the
     * correct torque check bit with the correct value 
     * tc4: stream with correct number of bits, which does not contain
     * the correct ultra_dist check bit with the correct value 
     * tc5: valid stream tc6: valid stream with extra bits
     */
    public static boolean isValidStream(ByteArrayOutputStream stream) {
        boolean checker[] = {false, false};
        byte[] result = stream.toByteArray();
        byte[] torque = new byte[8];
        byte[] speed = new byte[8];

        if (result.length >= 19 && result[0] == start_delimiter) {

            System.arraycopy(result, 1, torque, 0, 8);
            System.arraycopy(result, 10, speed, 0, 8);

            if (!has_even_bits(toDouble(torque))) {
                if (result[9] == torque_delimiter - Math.pow(2, 7)) {
                    checker[0] = true;
                }
            } else {
                if (result[9] == torque_delimiter) {
                    checker[0] = true;
                }
            }
            if (!has_even_bits(toDouble(speed))) {
                if (result[18] == speed_delimiter - Math.pow(2, 7)) {
                    checker[1] = true;
                }
            } else {
                if (result[18] == speed_delimiter) {
                    checker[1] = true;
                }
            }
        }
        System.out.println(Arrays.toString(checker));
        return checker[0] == true && checker[1] == true;
    }
    
        /**
     * Helper function to convert arrays to doubles
     */
    public static double toDouble(byte[] bytes) {
        return ByteBuffer.wrap(bytes).getDouble();
    }

}

//From car, generate bitstreams of data
//Called in CarInterface

package tavassignone;

import static org.mockito.Mockito.*;
import java.io.*;

import java.nio.ByteBuffer;

public class Car {
	
	private SpeedTorque data;
	//private ByteArrayOutputStream stream;
	private double torque;
	private double ir;
	private double uv;
        
        public static final byte start_delimiter = 9; //8 bit delimiter
        public static final byte angle_delimiter = 10;//7 bit delimiter
        public static final byte speed_delimiter = 11;//7 bit delimiter
    
        public static final int packetLength = 19; //the length of a full packet
	
	public Car(){
		
		   /*ByteArrayOutputStream stream = new ByteArrayOutputStream();
		   SpeedTorqueObj dummy = new SpeedTorqueObj(12, 13);
		   SpeedTorque data = mock(SpeedTorque.class);
		   when(data.readSpeedTorque(stream)).thenReturn(dummy);
		   
		   this.stream = stream;
		   this.data = data;*/
	}
        /*
        Takes double values for angle and speed, and generates a ByteArrayOutputStream.
        */
        public ByteArrayOutputStream generateStream(double angle, double speed) throws IOException {
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            byte[] bytes = new byte[19];
            bytes[0] = start_delimiter;
            System.arraycopy(toByteArray(angle), 0, bytes, 1, 8);
            bytes[9] = angle_delimiter;
            if(!has_even_bits(angle))
                bytes[9] += Math.pow(2, 7);
            
            System.arraycopy(toByteArray(speed), 0, bytes, 10, 8);
            bytes[18] = speed_delimiter;
            if(!has_even_bits(speed))
                bytes[18] += Math.pow(2, 7);
            
            stream.write(bytes);
            return stream;
        }
        
        /**
     * Description: 
     *  TAKEN FROM SensorData
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
     * TAKEN FROM SensorData
     */    
    public static byte[] toByteArray(double value) {
        byte[] bytes = new byte[8];
        ByteBuffer.wrap(bytes).putDouble(value);
        return bytes;
    }
     
        
	/*public double getSpeed(){
	   return data.readSpeedTorque(stream).getSpeed();
	}
	
	public double getTorque(){
		return data.readSpeedTorque(stream).getTorque();
	}
	
	public void recieveTorque(double torque){
		this.torque = torque;
	}
	
	public void recieveIrDist(double ir){
		this.ir = ir;
	}
	
	public void recieveUvDist(double uv){
		this.uv = uv;
	}
	
	public double checkTorque(){
		return torque;
	}
	
	public double checkIR(){
		return ir;
	}
	
	public double checkUV(){
		return uv;
	}*/
	
}

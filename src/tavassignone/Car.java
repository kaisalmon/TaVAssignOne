//From car, generate bitstreams of data
//Called in CarInterface

package tavassignone;

import static org.mockito.Mockito.*;
import java.io.*;

import java.nio.ByteBuffer;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class Car {
	
	ByteArrayOutputStream stream;
	
	public Car(){
		
	}
	
	public void setStream(ByteArrayOutputStream stream){
		this.stream = stream;
	}

	public ByteArrayOutputStream recieveData(ByteArrayOutputStream sensorData) {
		return sensorData;
	}
	

	public ByteArrayOutputStream getSpeedTorque() throws IOException {
		/*
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		
		byte[] bytes = {9, 63, -16, 0, 0, 0, 0, 0, 0, 10, 64, 20, 0, 0, 0, 0, 0, 0, (byte) (11 - Math.pow(2, 7))};
		
		stream.write(bytes);
		*/
		
		return stream;
	}

	
}

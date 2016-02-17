package tavassignone;

import static org.mockito.Mockito.*;

import java.io.ByteArrayOutputStream;

public class Car {
	
	private SpeedTorque data;
	private ByteArrayOutputStream stream;
	
	public Car(){
		
		   ByteArrayOutputStream stream = new ByteArrayOutputStream();
		   SpeedTorqueObj dummy = new SpeedTorqueObj(12, 13);
		   SpeedTorque data = mock(SpeedTorque.class);
		   when(data.readSpeedTorque(stream)).thenReturn(dummy);
		   
		   this.stream = stream;
		   this.data = data;
	}

	public double getSpeed(){
	   return data.readSpeedTorque(stream).getSpeed();
	}
	
	public double getTorque(){
		return data.readSpeedTorque(stream).getTorque();
	}
}

package tavassignone;

import static org.mockito.Mockito.*;

import java.io.ByteArrayOutputStream;

public class Car {
	
	private SpeedTorque data;
	private ByteArrayOutputStream stream;
	private double torque;
	private double ir;
	private double uv;
	
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
	}
	
}

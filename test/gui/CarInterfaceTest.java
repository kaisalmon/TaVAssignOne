/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import org.junit.Assert;

import org.junit.Test;
import org.mockito.Answers;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.junit.Assert.*;
import tavassignone.Car;
import tavassignone.SensorData;
import tavassignone.SpeedTorqueObj;
import static org.mockito.Mockito.*;

/**
 *
 * @author Kai, Kristiyan & Martina
 */
public class CarInterfaceTest {
    
    public CarInterfaceTest() {
    }
    
    /**
     * Test of receiveData method, of class CarInterface.
     * @throws IOException 
     */
    @Test
    public void testReceiveData() throws Exception {
    	
    	ByteArrayOutputStream stream = new ByteArrayOutputStream();
    	byte[][] bytes = {{9, 63, -16, 0, 0, 0, 0, 0, 0, 10, 64, 20, 0, 0, 0, 0, 0, 0, (byte) (11 - Math.pow(2, 7))},
    			          {9, 63, -16, 0, 0, 0, 0, 0, 0, 10, 64, 20, 0, 0, 0, 0, 0, 0},
    			          {5, 63, -16, 0, 0, 0, 0, 0, 0, 10, 64, 20, 0, 0, 0, 0, 0, 0, (byte) (11 - Math.pow(2, 7))}};
    	
    	Car car = new Car();
    	CarInterface.setCar(car);
    
    	//tc 0: valid stream
    	
    	stream.write(bytes[0]);
    	car.setStream(stream);
    	
    	SpeedTorqueObj test2 = mock(SpeedTorqueObj.class);
    	when(test2.getSpeed()).thenReturn((double) 5);
    	when(test2.getTorque()).thenReturn((double) 1);
    	
        System.out.println("receiveData");
        
        double expResultSpeed = test2.getSpeed();
        double expResultTorque = test2.getTorque();
    	
        double resultOne = CarInterface.receiveData().getSpeed();
        double resultTwo = CarInterface.receiveData().getTorque();
        
        assertEquals(expResultSpeed, resultOne, 0.1);
        assertEquals(expResultTorque, resultTwo, 0.1);
        
        stream.reset();;
        
        //tc 1: Invalid length / incomplete packet
        
        stream.write(bytes[1]);
        car.setStream(stream);
        
        SpeedTorqueObj test = mock(SpeedTorqueObj.class);
        when(test.getSpeed()).thenReturn((double) -1);
        when(test.getTorque()).thenReturn((double) -1);
        
        expResultSpeed = test.getSpeed();
        expResultTorque = test.getTorque();

        resultOne = CarInterface.receiveData().getSpeed();
        resultTwo = CarInterface.receiveData().getTorque();
        
        assertEquals(expResultSpeed, resultOne, 0.1);
        assertEquals(expResultTorque, resultTwo, 0.1);
        
        stream.close();
        
        //tc 2: Invalid start delimiter // corrupt packet
        
        stream.write(bytes[2]);
        car.setStream(stream);
        
        SpeedTorqueObj test3 = mock(SpeedTorqueObj.class);
        when(test3.getSpeed()).thenReturn((double) -1);
        when(test3.getTorque()).thenReturn((double) -1);
        
        expResultSpeed = test3.getSpeed();
        expResultTorque = test3.getTorque();
        
        resultOne = CarInterface.receiveData().getSpeed();
        resultTwo = CarInterface.receiveData().getTorque();
        
        assertEquals(expResultSpeed, resultOne, 0.1);
        assertEquals(expResultTorque, resultTwo, 0.1);
        
    }
    
	@Test
    public void testSend() throws Exception {
    
    	ByteArrayOutputStream stream = new ByteArrayOutputStream();
    	Car car = new Car();
    	CarInterface.setCar(car);
    
        /*TC 0: valid stream*/{
   
    	byte[][] sensorData = {{5, 63, -32, 0, 0, 0, 0, 0, 0, -122, 63, -32, 0, 0, 0, 0, 0, 0, -121, 64, 29, 0, 0, 0, 0, 0, 0, -120},
    			               {5, 63, -32, 0, 0, 0, 0, 0, 0, -122, 63, -32, 0, 0, 0, 0, 0, 0}
    	};
    	stream.write(sensorData[0]);
    	
    	Car test = mock(Car.class);
    	when(test.recieveData(stream)).thenReturn(stream);
    	
    	CarInterface.send(0.5d, 0.5d, 7.25d);
    	
    	ByteArrayOutputStream expResult = test.recieveData(stream);
    	ByteArrayOutputStream result = car.recieveData(stream);
    	
    	assertEquals(expResult, result);
    	
    	stream.close();
    }
    }
    
    @Test
    public void testSendValid() throws IOException {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
    	Car car = new Car();
    	CarInterface.setCar(car);
        
        /*TC 0: Corrupt stream*/{
        System.out.println("TC0: Corrupt");
        byte[] sensorData = {5, 63, -32, 0, 0, 0, 0, 0, 0, -122, 63, -32, 0, 0, 0, 0, 0, 0, -121, 64, 29, 0, 0, 0, 0, 0, 0, 10};
        stream.write(sensorData);
        CarInterface.sendValid(stream);
        
        byte[] expResult = {};
        byte[] result = car.recieveData(stream).toByteArray();
        Assert.assertArrayEquals(expResult, result);
        
    }
        
        /*TC 1: Empty stream*/{
        System.out.println("TC1: Empty");
     
        CarInterface.sendValid(stream);
        
        byte[] expResult = {};
        byte[] result = car.recieveData(stream).toByteArray();
        Assert.assertArrayEquals(expResult, result);
        
    }
   
        
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tavassignone;

import java.io.IOException;
import java.util.Arrays;

/**
 *
 * @author Kai
 */
public class TavAssignOne {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        SensorData sensorData = new SensorData(0.5d, 23d, 7.25d);
        byte[] bytes= SensorData.getSensorData(sensorData).toByteArray();
        System.out.println(Arrays.toString(bytes));
    }
    
}

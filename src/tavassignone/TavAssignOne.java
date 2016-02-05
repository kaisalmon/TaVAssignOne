/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tavassignone;

import java.io.IOException;

/**
 *
 * @author Kai
 */
public class TavAssignOne {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        SensorData sensorData = new SensorData(0.5d, 3d, 7.25d);
        byte[] bytes= SensorData.getSensorData(sensorData).toByteArray();
        int i = 0;
        for(byte b:bytes){
            System.out.print(Byte.toString(b)+", ");
            if(i==0 || i == 8 || i == 9 || i == 17 ||i == 18 || i == 26 || i == 27)
                System.out.println();
            i++;
        }
    }
    
}

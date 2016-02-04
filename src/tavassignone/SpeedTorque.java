/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tavassignone;

import java.io.ByteArrayOutputStream;
import java.util.Arrays;
import static tavassignone.SensorData.has_even_bits;
import static tavassignone.SensorData.toDouble;

/**
 *
 * @author Kai
 */
public class SpeedTorque {

    private float speed;
    private float torque;

    public static final byte start_delimiter = 9; //8 bit delimiter
    public static final byte torque_delimiter = 10;//7 bit delimiter
    public static final byte speed_delimiter = 11;//7 bit delimiter

    public SpeedTorque(float speed, float torque) {
        this.speed = speed;
        this.torque = torque;
    }

    /**
     * Description: Checks if a given ByteArrayOutputStream is in a valid format.
     *
     * Pre-Condition: A non-null ByteArrayOutputStream is given.
     *
     * Post-Condition: Returns true if the stream is valid, and otherwise returns false;
     *
     * Test Cases: tc0: stream with invalid length tc1: stream with correct number of bits, which does not contain the
     * start delimiter in the correct position tc2: stream with correct number of bits, which does not contain the
     * ir_dist delimiter in the correct position tc3: stream with correct number of bits, which does not contain the
     * correct torque check bit with the correct value tc4: stream with correct number of bits, which does not contain
     * the correct ultra_dist check bit with the correct value tc5: valid stream tc6: valid stream with extra bits
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

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.*;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import tavassignone.SpeedTorque;
import tavassignone.SpeedTorqueObj;

/**
 *
 * @author Kai
 */
public class Gui extends JFrame {
    final JSpinner outIR, outSonar, outTorque, inSpeed, inAngle;
    
    public Gui() throws HeadlessException {
        super("Tav Phase 2");
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400, 300);
        this.setResizable(false);
        this.setLayout(new GridLayout(5,1,15,15));
        outTorque = this.createRow("Torque", true);
        outIR = this.createRow("IR", true);
        outSonar = this.createRow("Sonar", true);
        //JButton btn = new JButton("Send");
        //this.add(btn);
        inSpeed = this.createRow("Speed", false);
        inAngle = this.createRow("Angle", false);
        this.revalidate();
       // btn.addActionListener((ActionEvent ae) -> {
       //     outIR.setValue(0);
       // });
        Timer receive;
        receive = new Timer(1000, (ActionEvent ae) -> {
            try{
            SpeedTorqueObj speedAngle = CarInterface.receiveData();
            inSpeed.setValue(speedAngle.getSpeed());
            inAngle.setValue(speedAngle.getTorque());
            }catch(Exception e){}
        });
        receive.start();
        
        Timer send;
        send = new Timer(2000, (ActionEvent ae) -> {
            try{
            CarInterface.send((Double)outTorque.getValue(), (Double)outIR.getValue(), (Double)outSonar.getValue());
            }catch (Exception e){}
        });
        send.start();
    }
    
    final public JSpinner createRow(String string, boolean enabled){
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        JLabel label = new JLabel(string,  SwingConstants.RIGHT);
        label.setPreferredSize(new Dimension(80,80));
        label.setAlignmentX(1);
        JSpinner field = new JSpinner(new SpinnerNumberModel(0.0,-1000.0 ,1000.0,0.1));
        field.setEnabled(enabled);
        panel.add(label, BorderLayout.WEST);
        panel.add(field);
        this.add(panel);
        return field;
    }
    
    
    public static void main(String args[]){
        new Gui();
    }
    
}

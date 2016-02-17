/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

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
import javax.swing.SwingConstants;

/**
 *
 * @author Kai
 */
public class Gui extends JFrame{
    final JSpinner outIR, outUV, outTorque, inSpeed, inAngle;
    
    public Gui() throws HeadlessException {
        super("Tav Phase 2");
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400, 300);
        this.setResizable(false);
        this.setLayout(new GridLayout(6,1,15,15));
        outTorque = this.createRow("Torque", true);
        outIR = this.createRow("IR", true);
        outUV = this.createRow("UV", true);
        JButton btn = new JButton("Send");
        this.add(btn);
        inSpeed = this.createRow("Speed", false);
        inAngle = this.createRow("Angle", false);
        this.revalidate();
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                outIR.setValue(0);
            }
        });
    }
    
    final public JSpinner createRow(String string, boolean enabled){
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        JLabel label = new JLabel(string,  SwingConstants.RIGHT);
        label.setPreferredSize(new Dimension(80,80));
        label.setAlignmentX(1);
        JSpinner field = new JSpinner();
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

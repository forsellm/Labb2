package carFiles;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import CarModelTree.*;
/*
* This class represents the Controller part in the MVC pattern.
* It's responsibilities is to listen to the View and responds in a appropriate manner by
* modifying the model state and the updating the view.
 */

public class CarController implements ActionListener{

    // member fields:

    // The delay (ms) corresponds to 20 updates a sec (hz)
    private final int delay = 50;
    // The timer is started with an listener (see below) that executes the statements
    // each step between delays.
    private Timer timer = new Timer(delay, new TimerListener());

    // The frame that represents this instance View of the MVC pattern
    CarView frame;
    // A list of cars, modify if needed
    CarModel model;
    int gasAmount;

    public CarController(CarView frame, CarModel model){
        this.frame = frame;
        this.model = model;
        ConnectButtons();
    }


    public void startTimer(){
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }


    /* Each step the TimerListener moves all the cars in the list and tells the
    * view to update its images. Change this method to your needs.
    * */
    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            model.moveCars(frame.getHeightCarArea());
        }
    }


    private class ButtonListener implements ActionListener{
            public void actionPerformed(ActionEvent e) {
            }
    }

    private void ConnectButtons(){
        frame.gasSpinner.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                gasAmount = (int) ((JSpinner) e.getSource()).getValue();
            }
        });

        frame.gasButton.addActionListener(new ButtonListener() {
            @Override
            public void actionPerformed(ActionEvent e)

            {
                model.gas(gasAmount);
            }
        });

        frame.brakeButton.addActionListener(new ButtonListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.brake(gasAmount);
            }
        });
        frame.turboOnButton.addActionListener(new ButtonListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.turboOn();
            }
        });
        frame.turboOffButton.addActionListener(new ButtonListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.turboOff();
            }
        });
        frame.stopButton.addActionListener(new ButtonListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.stopAll();
            }
        });

        frame.startButton.addActionListener(new ButtonListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.startAll();
            }
        });
        frame.liftBedButton.addActionListener(new ButtonListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.liftTruckBed();
            }
        });
        frame.lowerBedButton.addActionListener(new ButtonListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.lowerTruckBed();
            }
        });
        frame.addCarButton.addActionListener(new ButtonListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.createRandomVehicle();
            }
        });
        frame.removeCarButton.addActionListener(new ButtonListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.removeVehicle();
            }
        });
    }
}


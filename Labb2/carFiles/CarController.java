package carFiles;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

import CarModelTree.*;
/*
* This class represents the Controller part in the MVC pattern.
* It's responsibilities is to listen to the View and responds in a appropriate manner by
* modifying the model state and the updating the view.
 */

public class CarController {
    // member fields:

    // The delay (ms) corresponds to 20 updates a sec (hz)
    private final int delay = 50;
    // The timer is started with an listener (see below) that executes the statements
    // each step between delays.
    private Timer timer = new Timer(delay, new TimerListener());

    // The frame that represents this instance View of the MVC pattern
    CarView frame;
    // A list of cars, modify if needed

    ArrayList<Vehicle> vehicles= new ArrayList<>();

    //methods:

    public static void main(String[] args) {
        // Instance of this class
        CarController cc = new CarController();


        cc.vehicles.add(new Volvo240());
        cc.vehicles.add(new Scania(Color.cyan, 75));
        cc.vehicles.add(new Saab95());
        cc.setSpaceBetweenVehicles();


        // Start a new view and send a reference of self
        cc.frame = new CarView("CarSim 1.0", cc);

        // Start the timer
        cc.timer.start();
    }


    /* Each step the TimerListener moves all the cars in the list and tells the
    * view to update its images. Change this method to your needs.
    * */
    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            for (Vehicle vehicle : vehicles) {

                frame.drawPanel.currentCar(vehicle);
                vehicle.move();
                int x = (int) Math.round(vehicle.getPosition().getX());
                int y = (int) Math.round(vehicle.getPosition().getY());
                frame.drawPanel.moveit(vehicle, x, y);
                // repaint() calls the paintComponent method of the panel
                frame.drawPanel.repaint();
                invertDirectionIfNecessary(vehicle);


            }
        }
    }

    // Calls the gas method for each car once
    void gas(int amount) {
        double gas = ((double) amount) / 100;
        for (Vehicle vehicle : vehicles) {
            vehicle.gas(gas);

        }
    }
    public void brake(int amount) {
        double brake = ((double) amount) / 100;
        for (Vehicle vehicle : vehicles) {
            vehicle.brake(brake);
        }   
    }
    public void stopAll(){
        for (Vehicle vehicle : vehicles) {
            vehicle.stopEngine();
        }
    }
    public void startAll(){
        for (Vehicle vehicle : vehicles) {
            vehicle.startEngine();
        }
    }

    public void turboOff(){
        for (Vehicle s:vehicles){
            if(s.getClass().equals(Saab95.class))
                ((Saab95) s).setTurboOff();
        }
    }

    public void turboOn(){
        for (Vehicle s:vehicles) {
            if (s.getClass().equals(Saab95.class)){
                ((Saab95) s).setTurboOn();
            }
        }
    }

    public void liftTruckBed(){
        for (Vehicle s:vehicles){
            if(s.getClass().equals(Scania.class))
                ((Scania) s).raiseTruckBed();
        }
    }
    public void lowerTruckBed(){
        for (Vehicle s:vehicles){
            if(s.getClass().equals(Scania.class))
                ((Scania) s).lowerTruckBed();
        }
    }
    void invertDirectionIfNecessary(Vehicle car){
        double yPos = car.getPosition().getY();
        int yDelta = car.getFacingDirection().getYDelta();
        if ((yPos+ (frame.drawPanel.getImageHeight(car)) +yDelta >= frame.drawPanel.getHeight()) || (yPos + yDelta <= 0)){
            car.invertDirection();
        }
        
    }

    void setSpaceBetweenVehicles(){
        double x=0.0;

        for (Vehicle v: vehicles) {
            v.setPosition(new Point2D.Double(x, 0.0));
            x += 200.0;
        }

    }




   /*
    void changeDirectionToInverse(){
        if (invertDirectionNecessary()) {
            //kod som inverterar riktning
        }
    }

    boolean invertDirectionNecessary(Vehicle c) {
        return (invertDirectionAxisTest((int)Math.round(c.getPosition().getY()), frame.drawPanel.getHeight(),c.getFacingDirection().getYDelta()) ||
                invertDirectionAxisTest((int)Math.round(c.getPosition().getX()), frame.drawPanel.getWidth(), c.getFacingDirection().getXDelta()));
    }

    boolean invertDirectionAxisTest(int coordinate, int maxCoordinate, int delta){
        return ((coordinate + delta >= maxCoordinate) || (coordinate) + delta <= 0);
    }
    */





}


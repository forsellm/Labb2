package carFiles;

import CarModelTree.*;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.List;
import java.util.ArrayList;

public class CarModel {
    List<VehicleInterface> vehicles = new ArrayList<>();
    List<Turbo> turboVehicles = new ArrayList<>();
    List<TruckBed> truckBedVehicles = new ArrayList<>();
    VehicleFactory vehicleFactory;
    public CarModel(VehicleFactory vehicleFactory){
        this.vehicleFactory = vehicleFactory;
    }

    // Calls the gas method for each car once
    void gas(int amount) {
        double gas = ((double) amount) / 100;
        for (VehicleInterface vehicle : vehicles) {
            vehicle.gas(gas);

        }
    }
    public void brake(int amount) {
        double brake = ((double) amount) / 100;
        for (VehicleInterface vehicle : vehicles) {
            vehicle.brake(brake);
        }
    }
    public void stopAll(){
        for (VehicleInterface vehicle : vehicles) {
            vehicle.stopEngine();
        }
    }
    public void startAll(){
        for (VehicleInterface vehicle : vehicles) {
            vehicle.startEngine();
        }
    }

    public void turboOff(){
        for (Turbo s:turboVehicles){
            s.setTurboOff();
        }
    }

    public void turboOn(){
        for (Turbo s:turboVehicles) {
                s.setTurboOn();
        }
    }

    public void liftTruckBed(){
        for (TruckBed s:truckBedVehicles){
            s.raiseTruckBed();
        }
    }
    public void lowerTruckBed(){
        for (TruckBed s:truckBedVehicles){
            s.lowerTruckBed();
        }
    }
    //Här använde vi car.getCarImage.getHeight för att dynamiskt kunna ändra när bilen ska vända. Nu flyttar vi bilderna ut ur
    //bilklasserna till vyn, och modellen får absolut inte bero på vyn så vi hårdkodar måtten till 60 hög
    void invertDirectionIfNecessary(VehicleInterface car, int frameHeight){
        double yPos = car.getPosition().getY();
        int yDelta = car.getFacingDirection().getYDelta();
        if ((yPos+ 60 +yDelta >= frameHeight || (yPos + yDelta <= 0))){
            car.invertDirection();
        }
    }

    void setSpaceBetweenVehicles(){
        double x=0.0;

        for (VehicleInterface v: vehicles) {
            v.setPosition(new Point2D.Double(x, v.getPosition().getY()));
            x += 100.0;
        }

    }

    void moveCars(int frameHeight){
        for (VehicleInterface vehicle : vehicles) {
            // frame.drawPanel.currentCar(vehicle);
            vehicle.move();
            int x = (int) Math.round(vehicle.getPosition().getX());
            int y = (int) Math.round(vehicle.getPosition().getY());
           // frame.drawPanel.moveit(vehicle, x, y);
            // repaint() calls the paintComponent method of the panel
           // frame.drawPanel.repaint();
            invertDirectionIfNecessary(vehicle,frameHeight);
            notifyListeners();
        }
    }

    private void notifyListeners(){
        ArrayList<VehicleInterface> vehicleCopy = new ArrayList<>(vehicles);
        for (AnimateListener l : listeners)
            l.actOnUpdate(vehicleCopy);
    }

    private List<AnimateListener> listeners = new ArrayList<>();

    public void addListener(AnimateListener l){
        listeners.add(l);
    }

    public void addVehicle(VehicleInterface v){
        vehicles.add(v);
        setSpaceBetweenVehicles();
    }
    public void addVehicle(Turbo v){
        vehicles.add(v);
        turboVehicles.add(v);
        setSpaceBetweenVehicles();
    }
    public void addVehicle(TruckBed v){
        vehicles.add(v);
        truckBedVehicles.add(v);
        setSpaceBetweenVehicles();
    }

    public void createRandomVehicle() {
        if (vehicles.size() < 10) {
            int i = (int) (Math.random() * 100) % 3;
            if (i==0){
                addVehicle(vehicleFactory.createVolvo240());
            } else if (i==1){
                addVehicle(vehicleFactory.createSaab95());
            } else if (i==2){
                addVehicle(vehicleFactory.createScania(Color.red, 75));
            }
        }
    }
    public void removeVehicle(){
        if (vehicles.size()>0){
            VehicleInterface temp = vehicles.get(vehicles.size()-1);
            vehicles.remove(vehicles.size()-1);
            if (temp instanceof Turbo){
                turboVehicles.remove(temp);
            }
            if (temp instanceof TruckBed){
                truckBedVehicles.remove(temp);
            }
        }
        notifyListeners();
    }
}

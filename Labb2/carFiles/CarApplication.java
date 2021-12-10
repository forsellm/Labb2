package carFiles;

import CarModelTree.Vehicle;
import CarModelTree.Volvo240;
import carFiles.VehicleFactory;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CarApplication {

    public static void main(String[] args) {

        VehicleFactory vehicleFactory = new VehicleFactory();
        CarView frame = new CarView("CarSim 1.0");
        CarModel model = new CarModel(vehicleFactory);

        model.addVehicle(vehicleFactory.createSaab95());
        model.addVehicle(vehicleFactory.createVolvo240());
        model.addVehicle(vehicleFactory.createScania(Color.cyan, 75));
        model.addListener(frame.drawPanel);
        model.addListener(frame.speedTable);
        CarController cc = new CarController(frame, model);


        // Start a new view and send a reference of self

        // Start the timer
        cc.startTimer();

    }

}

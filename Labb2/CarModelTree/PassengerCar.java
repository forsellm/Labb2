package VehicleFiles;

import java.awt.*;

public abstract class PassengerCar extends Vehicle {


    /**
     * Class constructor, sets color, engine power, model name and number och doors
     * checks if engine power is positive, else initiates it to zero
     *
     * @param color       the color of this car
     * @param enginePower the engine power of this car
     * @param modelName   the model name of this car
     * @param nrDoors     the number of doors on this car
     */
    public PassengerCar(Color color, int enginePower, String modelName, int nrDoors) {
        super(color, enginePower, modelName, nrDoors);
    }

    /**
     * Calculates the speed factor for this car
     * @return speed factor
     */
    protected abstract double speedFactor();

}

package CarModelTree;

import java.awt.*;

/**
 * Represents a car of type Truck
 * @author Cecilia Nyberg, Maximilian Forsell, Lucas Edeslätt
 */
public abstract class Truck extends Vehicle {

    /**
     * boolean stating whether the truck bed is raised
     */
    private boolean isTruckBedRaised;

    /**
     * Class constructor, sets color, engine power, model name and number och doors
     * checks if engine power is positive, else initiates it to zero
     *
     * @param color       the color of this car
     * @param enginePower the engine power of this car
     * @param modelName   the model name for this truck
     * @param nrDoors     the number of doors for this car
     */
    public Truck(Color color, int enginePower, String modelName, int nrDoors) {
        super(color, enginePower, modelName, nrDoors);
        this.isTruckBedRaised = false;
    }

    /**
     * Gets speed factor for this truck
     * @return speed factor
     */
    @Override
    protected double speedFactor() {
        return this.getEnginePower()*0.005;
    }

    /**
     * Raises the truck bed bas long as it is not already raised and the truck is stationary
     * @return boolean stating whether the truck bed was raised
     */
    public boolean raiseTruckBed(){
        if (this.getCurrentSpeed()==0 && !isTruckBedRaised){
            isTruckBedRaised=true;
            return true;
        } else {
            System.out.println("Could not mark truck bed as raised");
            return false;
        }
    }

    /**
     * Lowers the truck bed as long as it is not already lowered and the truck is stationary
     * @return boolean stating whether the truck bed was raised
     */
    public boolean lowerTruckBed() {
        if (this.getCurrentSpeed() == 0 && isTruckBedRaised) {
            isTruckBedRaised=false;
            return true;
        } else {
            System.out.println("Could not mark truck bed as lowered");
            return false;
        }
    }

    /**
     * Calls incrementSpeed, catches RuntimeException if input exception occurred
     * @param amount the amount to increase speed with
     * @return boolean stating whether the car was able to gas
     */
    @Override
    public boolean gas(double amount){
        boolean tempStatus = false;
        if (!getTruckBedStatus()){
           tempStatus = super.gas(amount);
        }else{
            System.out.println("Could not gas");
        }
        return tempStatus;
    }


    /**
     * Gets status of truck bed
     * @return if the truck bed is lowered or raised
     */
    public boolean getTruckBedStatus(){
        return isTruckBedRaised;
    }

}

;
package CarModelTree;

import java.awt.*;

/**
 * Represents a car of type Truck
 * @author Cecilia Nyberg, Maximilian Forsell, Lucas Edeslätt
 */
public abstract class Truck extends Vehicle {

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
     */
    public void raiseTruckBed(){
        if (this.getCurrentSpeed()==0 && !isTruckBedRaised){
                isTruckBedRaised=true;
        } else {System.out.println("Could not mark truck bed as raised");}
    }

    /**
     * Lowers the truck bed as long as it is not already lowered and the truck is stationary
     */
    public void lowerTruckBed() {
        if (this.getCurrentSpeed() == 0 && isTruckBedRaised) {
                isTruckBedRaised=false;
        } else {
            System.out.println("Could not mark truck bed as lowered");
        }
    }

    /**
     * Calls incrementSpeed, catches RuntimeException if input exception occurred
     * @param amount the amount to increase speed with
     */
    @Override
    public void gas(double amount){
        if (!isTruckBedRaised){
            super.gas(amount);
        }else{
            System.out.println("Could not gas");
        }
    }

    /**
     * Sets the status of the truck bed, true for raised, false for lowered.
     * @param setAs the new status of the truck bed
     */
    public void setTruckBedRaised(boolean setAs){
        this.isTruckBedRaised = setAs;
    }

    /**
     * Gets status of truck bed
     * @return if the truck bed is lowered or raised
     */
    public boolean getTruckBedStatus(){
        if (isTruckBedRaised){
            return true;
        }else{
            return false;
        }
    }

}

;
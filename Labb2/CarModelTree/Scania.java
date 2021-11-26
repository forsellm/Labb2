package CarModelTree;

import java.awt.*;

public class Scania extends Truck{
    /**
     * Maximum Truck bed angle for this car
     */
    private double truckBedAngleMax = 70;

    /**
     * Current truck bed angle for this car
     */
    private double currentTruckBedAngle;

    /**
     * Class constructor, sets color, engine power, model name and number och doors
     * checks if engine power is positive, else initiates it to zero
     *
     * @param color the color of this car
     * @param enginePower the color of this car
     */
    public Scania(Color color, int enginePower) {
        super(color, enginePower, "Scania", 2);
        currentTruckBedAngle = 0;
    }

    /**
     * Set max angle for the truck bed
     * @param maxAngle max angle for this Scania
     */
    public void setMaxAngle(double maxAngle){
        this.truckBedAngleMax = maxAngle;
    }

    /**
     * Raise the truck bed by a specified amount of degrees, assuming target angle is lower than the
     * max angle and that the truck is stationary
     * @param raiseByDegrees how many degrees the truck bed should be raised by
     */
    public void raiseTruckBed(double raiseByDegrees){
        if (this.getCurrentSpeed()==0 && this.getCurrentAngle()+raiseByDegrees <= truckBedAngleMax) {
            currentTruckBedAngle += raiseByDegrees;
            setTruckBedRaised(true);
        }else {
            System.out.println("Could not raise truck bed");
        }
    }

    /**
     * Lowers the truck bed by a specified amount of degrees, assuming target angle is not lower than
     * 0 degrees and that the truck is stationary
     * @param lowerByDegrees how many degrees the truck bed should be lowered by
     */
    public void lowerTruckBed(double lowerByDegrees) {
        if (this.getCurrentSpeed() == 0 && 0 <= (this.getCurrentAngle() - lowerByDegrees)) {
                currentTruckBedAngle -= lowerByDegrees;
                if(currentTruckBedAngle == 0){
                    setTruckBedRaised(false);
                }
        } else {
            System.out.println("Could not lower truck bed");
        }
    }
    /**
     * @return currentTruckBedAngle the current angle of the truck bed
     */
    public double getCurrentAngle(){
        return this.currentTruckBedAngle;
    }

    /**
     * @return truckBedAngleMax the max angle of the truck bed
     */
    public double getMaxAngle(){ return this.truckBedAngleMax; }
}

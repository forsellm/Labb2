package CarModelTree;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Scania extends Truck {
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
     * @param color       the color of this car
     * @param enginePower the color of this car
     */
    public Scania(Color color, int enginePower) {
        super(color, enginePower, "Scania", 2);
        currentTruckBedAngle = 0;
    }

    /**
     * Raise the truck bed by a specified amount of degrees, assuming target angle is lower than the
     * max angle and that the truck is stationary
     *
     * @param raiseByDegrees how many degrees the truck bed should be raised by
     * @return true if truck bed raised successfully
     */
    public boolean raiseTruckBed(double raiseByDegrees) {
        if (this.getCurrentSpeed() == 0 && this.getCurrentAngle() + raiseByDegrees <= truckBedAngleMax) {
            currentTruckBedAngle += raiseByDegrees;
            super.raiseTruckBed();
            return true;
        } else {
            System.out.println("Could not raise truck bed");
            return false;
        }
    }

    /**
     * Lowers the truck bed by a specified amount of degrees, assuming target angle is not lower than
     * 0 degrees and that the truck is stationary
     *
     * @param lowerByDegrees how many degrees the truck bed should be lowered by
     * @return true if truck bed successfully lowered
     */
    public boolean lowerTruckBed(double lowerByDegrees) {
        if (this.getCurrentSpeed() == 0 && 0 <= (this.getCurrentAngle() - lowerByDegrees)) {
            currentTruckBedAngle -= lowerByDegrees;
            if (currentTruckBedAngle == 0) {
                super.lowerTruckBed();
            }
            return true;
        } else {
            System.out.println("Could not lower truck bed");
            return false;
        }
    }

    /**
     * @return currentTruckBedAngle the current angle of the truck bed
     */
    public double getCurrentAngle() {
        return this.currentTruckBedAngle;
    }
    
}

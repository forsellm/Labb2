package VehicleFiles;

import java.awt.*;
import java.awt.geom.Point2D;

/**
 * Represents a car.
 * A car can be of different types
 * @author Cecilia Nyberg, Maximilian Forsell, Lucas Edeslätt
 */
public abstract class Vehicle implements Movable{

    /**
     * Engine power of this car
     */
    private double enginePower;

    /**
     * Current speed of this car
     */
    private double currentSpeed;

    /**
     * Color of this car
     */
    private Color color;

    /**
     * Model name of this car, this can't possibly change
     */
    private final String modelName;

    /**
     * Number of doors on this car, as long as the car isn't heavily modified
     */
    private final int nrDoors;

    /**
     * The direction this car is facing
     */
    private Directions facingDirection;

    /**
     * The current x- and y-coordinates for this car
     */
    private Point2D.Double coordinates = new Point2D.Double(0.0,0.0);

    /**
     * Class constructor, sets color, engine power, model name and number och doors
     * checks if engine power is positive, else initiates it to zero
     * @param color the color of this car
     * @param enginePower the engine power of this car
     * @param modelName the model name of this car
     * @param nrDoors the number of doors on this car
     */
    public Vehicle(Color color, int enginePower, String modelName, int nrDoors){
        if (0 < enginePower && enginePower < Double.POSITIVE_INFINITY){
            this.enginePower = enginePower;
        }
        this.color = color;
        this.modelName = modelName;
        this.nrDoors = nrDoors;
        this.facingDirection = Directions.NORTH;
        stopEngine();
    }

    /**
     * Gets number of doors on this car
     * @return numbers of doors
     */
    public int getNrDoors(){
        return nrDoors;
    }

    /**
     * Gets engine power of this car
     * @return engine power
     */
    public double getEnginePower(){
        return enginePower;
    }

    /**
     * Gets current speed of this car
     * @return current speed
     */
    public double getCurrentSpeed(){
        return currentSpeed;
    }

    /**
     * Gets color of this car
     * @return color of this car
     */
    public Color getColor(){
        return color;
    }

    /**
     * Sets color of this car
     * @param clr the color to be set
     */
    public void setColor(Color clr){
        color = clr;
    }

    /**
     * Starts engine, initiates current speed to 0.1
     */
    public void startEngine(){
        currentSpeed = 0.1;
    }

    /**
     *Stops engine, sets current speed to 0
     */
    public void stopEngine(){
        currentSpeed = 0;
    }

    /**
     * Calculates the speed factor for this car
     * @return speed factor
     */
    protected abstract double speedFactor();

    /**
     * Increases current speed, no higher than enginePower
     * @param amount the amount to increase with
     */
    private void incrementSpeed(double amount){
        currentSpeed = Math.min(getCurrentSpeed() + speedFactor() * amount,enginePower);
    }

    /**
     * Decreases current speed, no lower than 0
     * @param amount the amount to decrease with
     */
    private void decrementSpeed(double amount){
        currentSpeed = Math.max(getCurrentSpeed() - speedFactor() * amount,0);
    }

    /**
     * Moves this car straight forward in it's facing direction
     */
    public void move(){
        coordinates = new Point2D.Double(coordinates.x+facingDirection.getXDelta()*currentSpeed,
                coordinates.y+facingDirection.getYDelta()*currentSpeed);
    }

    /**
     * Turns this car 90 degrees left
     */
    public void turnLeft(){
        this.facingDirection = this.facingDirection.getCounterClockwiseDirection();
    }

    /**
     * Turns this car 90 degrees right
     */
    public void turnRight() {
        this.facingDirection = this.facingDirection.getClockwiseDirection();
    }

    /**
     * Calls incrementSpeed, catches RuntimeException if input exception occurred
     * @param amount the amount to increase speed with
     * @return boolean stating whether the car was able to gas
     */
    public boolean gas(double amount){
        if (amountIsOk(amount)){
            incrementSpeed(amount);
        }
        return amountIsOk(amount);
    }

    /**
     * @param amount amount to determine whether it is inside the interval of 0 and 1
     * @return a boolean that determines if the value was inside the accepted interval of 0 and 1
     */
    private boolean amountIsOk(double amount) {
        if (amount>=0 && amount <= 1) {
            return true;
        }else {
            System.out.println(amount + " is outside the accepted interval");
            return false;
        }
    }

    /**
     * Calls decrementSpeed, catches RuntimeException if value is greater than 0 and less than 1
     * @param amount the amount decrease speed with
     * @return boolean stating whether the car was able to brake
     */
    public boolean brake(double amount){
        if (amountIsOk(amount)){
            decrementSpeed(amount);
        }
        return amountIsOk(amount);
    }

    /**
     * Sets the cars position with a Point variable
     * @param coordinates
     */
    public void setPosition(Point2D.Double coordinates){
        this.coordinates=coordinates;
    }

    /**
     * Gets the position of the car
     * @return coordinates using a Point
     */
    public Point2D.Double getPosition(){
        return coordinates;
    }

    public Directions getFacingDirection(){
        return facingDirection;
    }

    public Point2D.Double getCoordinates(){
        return coordinates;
    }
}

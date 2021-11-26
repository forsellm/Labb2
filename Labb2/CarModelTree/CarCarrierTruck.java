package CarModelTree;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class CarCarrierTruck extends Truck{
    /**
     * The cars loaded on this truck
     */
    private ArrayList<PassengerCar> cars = new ArrayList<>();
    /**
     * Max number of loaded cars
     */
    private final int maxCapacity = 10;

    /**
     * Class constructor, sets color, engine power, model name and number och doors
     * checks if engine power is positive, else initiates it to zero
     * @param color       the color of this car
     * @param enginePower the engine power of this car
     */
    public CarCarrierTruck(Color color, int enginePower) {
        super(color, enginePower, "CarTransport", 2);
    }

    /**
     * Loads a car on the truck, as long as the truck has capacity, the truck bed is raised so
     * the car can enter, and the car and truck is close to each other
     * @param car the car to be loaded onto the truck
     */
    public void addCar(PassengerCar car){
        if (cars.size()<maxCapacity && getTruckBedStatus() && isClose(car)) {
            cars.add(car);
            car.setPosition(this.getPosition());
        } else {
            System.out.println("Something went wrong when loading the truck");
        }
    }

    /**
     * Checks if a car and truck are close to each other
     * @param car the car to test distance to
     * @return if car is close
     */
    private boolean isClose(PassengerCar car){
        if (distance(car)<=1){
            return true;
        }else{
            return false;
        }
    }

    /**
     * Calculates the distance between the truck and a car
     * @param car to test distance between
     * @return distance
     */
    private double distance(PassengerCar car){
        return Math.sqrt(Math.pow((this.getPosition().x-car.getPosition().x),2)+Math.pow((this.getPosition().y-car.getPosition().y),2));
    }

    /**
     * Unload the last car to be loaded, removing it from the list
     */
    public void removeCar(){
        if (getTruckBedStatus() && this.getNumberLoadedCars()>0){
            cars.get(cars.size()-1).setPosition(new Point2D.Double(this.getPosition().x-1,this.getPosition().y-1));
            cars.remove(cars.size()-1);
        }else {
            System.out.println("Truck bed is closed");
        }
    }

    /**
     * Moves this car straight forward in it's facing direction,
     * sets all cars in the list to the same position as this car
     */
    @Override
    public void move(){
        super.move();
        for (PassengerCar c: cars){
            c.setPosition(this.getPosition());
        }
    }

    public int getNumberLoadedCars(){
        return cars.size();
    }
}

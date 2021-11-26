package CarModelTree;

import java.util.ArrayList;
import java.util.Collection;

public class ServiceStation<T extends Vehicle> {
    /**
     * Creates a list of all the cars in the service station. All cars have to be of the same type or a
     * subtype to the declared car type
     */
    private Collection<T> carsInService = new ArrayList<>();

    /**
     * Max number of cars that fit at the service station at the same time
     */
    private int maxCars;

    /**
     * Class constructor, sets max number of cars that fit in the service station
     * @param maxCars Max cars in the station at the same time
     */
    public ServiceStation(int maxCars){
        this.maxCars=maxCars;
    }

    /**
     * Adds a vehicle to a list of all cars current in the service station, as long as the vehicle
     * type is the declared car type or a subtype of it.
     * @param c the car that is being added to the Service Station
     */
    void addCar(T c){
        if (carsInService.size()<maxCars){
            carsInService.add(c);
        }else{
            System.out.println("The service station is full");
        }

    }
    /**
    * Removes the vehicle in index 0 of the list of cars, and returns that car
     * @return the car in index 0 of the list
    */
    public T removeCar(T car){
       carsInService.remove(car);
       return car;
    }

    /**
     * Gets the amount of cars in service
     * @return the amount of cars in service
     */
    public int getNumberCarsInService(){
        return carsInService.size();
    }



}


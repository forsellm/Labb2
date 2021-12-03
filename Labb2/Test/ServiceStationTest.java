package Test;

import CarModelTree.*;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ServiceStationTest {
    @Test
    public void testServiceStationCapacity(){
        Vehicle car1 = new Volvo240();
        Vehicle car2 = new Volvo240();
        Vehicle car3 = new Saab95();
        Vehicle car4 = new Saab95();
        ServiceStation<Vehicle> service1 = new ServiceStation<>(2);
        service1.addCar(car1);
        service1.addCar(car2);
        service1.addCar(car3);
        service1.addCar(car4);
        assertEquals(service1.getNumberCarsInService(), 2);
    }
    @Test
    public void testRemoveCarServiceStation(){
        Vehicle car1 = new Volvo240();
        ServiceStation<Vehicle> station = new ServiceStation<>(8);
        station.addCar(car1);
        station.removeCar(car1);
        assertEquals(station.getNumberCarsInService(), 0);
    }
}

package CarModelTree;

import org.junit.*;

import static org.junit.Assert.* ;
import java.awt.*;
import java.awt.geom.Point2D;

public class VehicleTest {
    @Test
    public void testNrDoors() {
        Vehicle s = new Saab95();
        assertTrue(s.getNrDoors()==2);
    }
    @Test
    public void testColor() {
        Saab95 p = new Saab95();
        p.setColor(Color.red);
        assertTrue(p.getColor() == Color.red) ;
    }
    @Test
    public void testTurnLeft(){
        Volvo240 v = new Volvo240();
        v.turnLeft();
        assertTrue(v.getFacingDirection() == Directions.WEST);
    }
    @Test
    public void testTurnRight(){
        Volvo240 v = new Volvo240();
        v.turnRight();
        assertTrue(v.getFacingDirection() == Directions.EAST) ;
    }

    @Test
    public void testGas(){
        Saab95 p = new Saab95();
        p.startEngine();
        double temp = p.getCurrentSpeed();
        p.gas(0.5);
        assertTrue(p.getCurrentSpeed()>temp||p.getEnginePower()==p.getCurrentSpeed());
    }
    @Test
    public void testBrake(){
        Saab95 s = new Saab95();
        s.startEngine();
        s.gas(0.5);
        double temp = s.getCurrentSpeed();
        s.brake(0.5);
        assertTrue(s.getCurrentSpeed() < temp);

    }
    @Test
    public void testMoveRight(){
        Vehicle v = new Volvo240();
        v.startEngine();
        for (int i = 0; i < 20; i++){
            v.gas(1);
        }
        double tempy = v.getCoordinates().y;
        v.move();
        assertTrue(v.getCoordinates().y == tempy+v.getCurrentSpeed());
        v.turnRight();
        double tempx = v.getCoordinates().x;
        v.move();
        assertTrue(v.getCoordinates().x == tempx+v.getCurrentSpeed());
        tempy = v.getCoordinates().y;
        v.turnRight();
        v.move();
        assertTrue(v.getCoordinates().y == tempy-v.getCurrentSpeed());
        tempx = v.getCoordinates().x;
        v.turnRight();
        v.move();
        assertTrue(v.getCoordinates().x == tempx-v.getCurrentSpeed());
        tempy = v.getCoordinates().y;
        v.turnRight();
        v.move();
        assertTrue(v.getCoordinates().y == tempy+v.getCurrentSpeed());
    }
    @Test
    public void testMoveLeft(){
        Vehicle v = new Volvo240();
        v.startEngine();
        for (int i = 0; i < 20; i++){
            v.gas(1);
        }
        double tempy = v.getCoordinates().y;
        v.move();
        assertTrue(v.getCoordinates().y == tempy+v.getCurrentSpeed());
        v.turnLeft();
        double tempx = v.getCoordinates().x;
        v.move();
        assertTrue(v.getCoordinates().x == tempx-v.getCurrentSpeed());
        tempy = v.getCoordinates().y;
        v.turnLeft();
        v.move();
        assertTrue(v.getCoordinates().y == tempy-v.getCurrentSpeed());
        tempx = v.getCoordinates().x;
        v.turnLeft();
        v.move();
        assertTrue(v.getCoordinates().x == tempx+v.getCurrentSpeed());
        tempy = v.getCoordinates().y;
        v.turnLeft();
        v.move();
        assertTrue(v.getCoordinates().y == tempy+v.getCurrentSpeed());
    }

    @Test
    public void testSpeedUnderMax(){
        Volvo240 v = new Volvo240();
        v.startEngine();
        for (int i = 0; i <= 100; i++) {
            v.gas(1);
        }
        assertTrue(v.getCurrentSpeed()== v.getEnginePower());

    }

    @Test
    public void testStartEngine(){
        Volvo240 v = new Volvo240();
        v.startEngine();
        assertTrue(v.getCurrentSpeed() == 0.1);
    }
    @Test
    public void testStopEngine(){
        Volvo240 v = new Volvo240();
        v.startEngine();
        v.stopEngine();
        assertTrue(v.getCurrentSpeed() == 0);
    }

    @Test
    public void testSetTurboOn(){
        Saab95 s = new Saab95();
        s.setTurboOn();
        assertTrue(s.getTurboStatus()==true);
    }

    @Test
    public void testSetTurboOf(){
        Saab95 s = new Saab95();
        s.setTurboOn();
        s.setTurboOff();
        assertFalse(s.getTurboStatus()==true);
    }

    @Test
    public void testInitialSpeed(){
        Vehicle c = new Volvo240();
        c.startEngine();
        assertTrue(c.getCurrentSpeed()==0.1);
    }


    @Test
    public void testGasOutSideInterval(){
        Saab95 s = new Saab95();
        s.startEngine();
        double temp = s.getCurrentSpeed();
        try {
            s.gas(2);
        }catch (RuntimeException e){}
        assertTrue(temp==s.getCurrentSpeed());
    }
    @Test
    public void testBreakOutSideInterval(){
        Saab95 s = new Saab95();
        s.startEngine();
        s.gas(1);
        double temp = s.getCurrentSpeed();
        try {
            s.brake(2);
        }catch (RuntimeException e){}
        assertTrue(temp==s.getCurrentSpeed());
    }
    @Test
    public void testRaiseTruckBed(){
        Scania s = new Scania(Color.black, 100);
        double temp = s.getCurrentAngle();
        s.raiseTruckBed(10);
        assertTrue(temp + 10 == s.getCurrentAngle());
    }
    @Test
    public void testMoveCarInCarCarrierTruck(){
        CarCarrierTruck cct = new CarCarrierTruck(Color.red, 200);
        Volvo240 v = new Volvo240();
        cct.raiseTruckBed();
        cct.addCar(v);
        cct.lowerTruckBed();
        cct.startEngine();
        cct.gas(1);
        cct.move();
        assertEquals(cct.getPosition(), v.getPosition());
    }

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
    public void testSetMaxScaniaAngle(){
        Scania scania1 = new Scania(Color.red, 100);
        scania1.setMaxAngle(85);
        assertTrue(scania1.getMaxAngle()== 85);

    }
    @Test
    public void testLowerTruckBed(){
        Scania s = new Scania(Color.black, 100);
        s.raiseTruckBed(10);
        double temp = s.getCurrentAngle();
        s.lowerTruckBed(10);
        assertTrue(temp - 10 == s.getCurrentAngle());
    }
    @Test
    public void testCarCarrierTruckRemoveCar(){
        CarCarrierTruck c = new CarCarrierTruck(Color.black, 100);
        c.raiseTruckBed();
        Volvo240 v = new Volvo240();
        c.addCar(v);
        c.removeCar();
        assertEquals(c.getNumberLoadedCars(), 0);

    }
    @Test
    public void testRemoveCarServiceStation(){
        Vehicle car1 = new Volvo240();
        ServiceStation<Vehicle> station = new ServiceStation<>(8);
        station.addCar(car1);
        station.removeCar(car1);
        assertEquals(station.getNumberCarsInService(), 0);
    }

    @Test
    public void testCarCarrierLoadingProximityFalse(){
        CarCarrierTruck c = new CarCarrierTruck(Color.black, 100);
        Volvo240 v = new Volvo240();
        Point2D.Double p = new Point2D.Double(100, 100);
        c.setPosition(p);
        c.addCar(v);
        assertEquals(c.getNumberLoadedCars(),0);
    }

    @Test
    public void testRaiseTruckBedElse(){
        Scania s = new Scania(Color.black, 100);
        s.startEngine();
        s.gas(0.5);
        s.raiseTruckBed();
        assertFalse(s.getTruckBedStatus());
    }
    @Test
    public void testLowerTruckBedElse(){
        Scania s = new Scania(Color.black, 10);
        s.lowerTruckBed();
        assert!(s.getTruckBedStatus());
    }

    @Test
    public void testCarCarrierUnloadWhenLoweredBed(){
        CarCarrierTruck c = new CarCarrierTruck(Color.black, 100);
        Volvo240 v = new Volvo240();
        c.raiseTruckBed();
        c.addCar(v);
        c.lowerTruckBed();
        c.removeCar();
        assertEquals(c.getNumberLoadedCars(), 1);

    }

    @Test
    public void testGasWhenTruckBedRaised(){
        Scania s = new Scania(Color.black, 10);
        s.raiseTruckBed();
        s.startEngine();
        s.gas(0.5);
        assertTrue(s.getCurrentSpeed()==0.1);
    }

    @Test
    public void testRaiseTruckBedElseScania(){
        Scania s = new Scania(Color.black, 10);
        s.startEngine();
        s.gas(0.5);
        s.raiseTruckBed(10);
        assertTrue(s.getCurrentAngle()==0);
    }
    @Test
    public void testLowerTruckBedElseScania(){
        Scania s = new Scania(Color.black, 10);
        s.lowerTruckBed(10);
        assertTrue(s.getCurrentAngle()==0);
    }

    @Test
    public void testIsCloseFalse(){
        Volvo240 v = new Volvo240();
        CarCarrierTruck cct = new CarCarrierTruck(Color.black, 100);
        v.setPosition(new Point2D.Double(100, 100));
        cct.setPosition(new Point2D.Double(1,1));
        cct.raiseTruckBed();
        cct.addCar(v);
        System.out.println();
        assertEquals(cct.getNumberLoadedCars(),0);

    }

    @Test
    public void testCarCarrierTruckMoveRight(){
        Vehicle v = new CarCarrierTruck(Color.cyan,100);
        v.startEngine();
        for (int i = 0; i < 20; i++){
            v.gas(1);
        }
        double tempy = v.getCoordinates().y;
        v.move();
        assertTrue(v.getCoordinates().y == tempy+v.getCurrentSpeed());
        v.turnRight();
        double tempx = v.getCoordinates().x;
        v.move();
        assertTrue(v.getCoordinates().x == tempx+v.getCurrentSpeed());
        tempy = v.getCoordinates().y;
        v.turnRight();
        v.move();
        assertTrue(v.getCoordinates().y == tempy-v.getCurrentSpeed());
        tempx = v.getCoordinates().x;
        v.turnRight();
        v.move();
        assertTrue(v.getCoordinates().x == tempx-v.getCurrentSpeed());
        tempy = v.getCoordinates().y;
        v.turnRight();
        v.move();
        assertTrue(v.getCoordinates().y == tempy+v.getCurrentSpeed());
    }

}




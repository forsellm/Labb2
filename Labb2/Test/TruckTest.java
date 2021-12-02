package Test;

import VehicleFiles.CarCarrierTruck;
import VehicleFiles.Scania;
import VehicleFiles.Vehicle;
import VehicleFiles.Volvo240;
import org.junit.Test;

import java.awt.*;
import java.awt.geom.Point2D;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

public class TruckTest {
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
        s.gas(0);
        assertTrue(s.getCurrentSpeed()==0.0);
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

package Test;

import VehicleFiles.Directions;
import VehicleFiles.Saab95;
import VehicleFiles.Vehicle;
import VehicleFiles.Volvo240;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PassangerCarTest {
    @Test
    public void testNrDoors() {
        Vehicle s = new Saab95();
        assertTrue(s.getNrDoors()==2);
    }
    @Test
    public void testColor() {
        Saab95 p = new Saab95();
        p.setColor(Color.red);
        assertTrue(p.getColor() == Color.red);
    }
    @Test
    public void testTurnLeft(){
        Volvo240 v = new Volvo240();
        v.turnLeft();
        assertTrue(v.getFacingDirection() == Directions.WEST) ;
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
}

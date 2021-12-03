package CarModelTree;

import carFiles.DrawPanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

/**
 * Represents a car of type Volvo240
 * @author Cecilia Nyberg, Maximilian Forsell, Lucas Edeslätt
 */
public class Volvo240 extends PassengerCar{


    /**
     * The trim factor of these Volvos
     */
    private final static double trimFactor = 1.25;

    /**
     * Creates a Volvo with specific color, engine power, model name and number of doors
     * and tries to read and set an image of the car
     */
    public Volvo240(){
        super(Color.black, 100, "Volvo240",4);
    }

    /**
     * Calculates speed factor for this Volvo
     * @return speed factor
     */
    @Override
    public double speedFactor(){
        return this.getEnginePower() * 0.01 * trimFactor;
    }

}

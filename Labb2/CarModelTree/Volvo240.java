package VehicleFiles;

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
        try {
            // You can remove the "pics" part if running outside of IntelliJ and
            // everything is in the same main folder.
            // volvoImage = ImageIO.read(new File("Volvo240.jpg"));

            // Rememember to rightclick src New -> Package -> name: pics -> MOVE *.jpg to pics.
            // if you are starting in IntelliJ.
            setImage(ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Volvo240.jpg")));
        } catch (IOException ex)
        {
            ex.printStackTrace();
        }
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

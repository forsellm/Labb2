package carFiles;
import CarModelTree.Saab95;
import CarModelTree.Scania;
import CarModelTree.Vehicle;
import CarModelTree.Volvo240;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import javax.imageio.ImageIO;
import javax.swing.*;

// This panel represent the animated part of the view with the car images.
// This is a test comment

public class DrawPanel extends JPanel{

    // Just a single image, TODO: Generalize

    BufferedImage volvoImage;
    BufferedImage saabImage;
    BufferedImage scaniaImage;

    HashMap<Class, BufferedImage> vehiclesWithImage = new HashMap<>();
    HashMap<Vehicle, Point> vehiclePointHashMap = new HashMap<>();

    // To keep track of a singel cars position
    Point tempPoint = new Point();


    // TODO: Make this genereal for all cars
    void moveit(Vehicle v, int x, int y){
        tempPoint.x = x;
        tempPoint.y = y;
    }

    // Initializes the panel and reads the images
    public DrawPanel(int x, int y) {
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(x, y));
        this.setBackground(Color.green);
        // Print an error message in case file is not found with a try/catch block
        try {
            // You can remove the "pics" part if running outside of IntelliJ and
            // everything is in the same main folder.
            // volvoImage = ImageIO.read(new File("Volvo240.jpg"));

            // Rememember to rightclick src New -> Package -> name: pics -> MOVE *.jpg to pics.
            // if you are starting in IntelliJ.

            volvoImage = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Volvo240.jpg"));
            vehiclesWithImage.put(new Volvo240().getClass(), volvoImage);
            scaniaImage = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Scania.jpg"));
           vehiclesWithImage.put(new Scania(Color.cyan, 20).getClass(),scaniaImage);
            saabImage = ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Saab95.jpg"));
           vehiclesWithImage.put(new Saab95().getClass(), saabImage);

        } catch (IOException ex)
        {
            ex.printStackTrace();
        }

    }

    // This method is called each time the panel updates/refreshes/repaints itself
    // TODO: Change to suit your needs.
    //@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (Vehicle v : vehiclePointHashMap.keySet()) {
            g.drawImage(vehiclesWithImage.get(v.getClass()), vehiclePointHashMap.get(v).x, vehiclePointHashMap.get(v).y, null);

             // see javadoc for more info on the parameters
        }
    }

    public int getImageHeight(Vehicle v){
        return (vehiclesWithImage.get(v.getClass()).getHeight());
    }

    public void currentCar(Vehicle vehicle){
        this.currentCarClass= vehicle.getClass();
    }


}

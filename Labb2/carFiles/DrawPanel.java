package carFiles;
import CarModelTree.Saab95;
import CarModelTree.Scania;
import CarModelTree.Vehicle;
import CarModelTree.Volvo240;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import javax.imageio.ImageIO;
import javax.swing.*;

// This panel represent the animated part of the view with the car images.
// This is a test comment

public class DrawPanel extends JPanel implements AnimateListener{

    // Just a single image, TODO: Generalize

    BufferedImage volvoImage;
    BufferedImage saabImage;
    BufferedImage scaniaImage;

    HashMap<Class, BufferedImage> vehiclesWithImage = new HashMap<>();
    HashMap<Vehicle, Point> vehiclePointHashMap = new HashMap<>();

    // To keep track of a singel cars position
    private ArrayList<VehicleInterface> vehicleCopy = new ArrayList<>();
    private HashMap<Class, BufferedImage> classWithImage = new HashMap<>();
  //  SpeedTable speedtable = new SpeedTable(150, this.getHeight());


    // TODO: Make this genereal for all cars
    /*
    void moveit(Vehicle v, int x, int y){
        vehiclePointHashMap.put(v, new Point(x,y));
    }
     */
    // Initializes the panel and reads the images
    public DrawPanel(int x, int y) {
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(x, y));
        this.setBackground(Color.green);
        loadImages();



        //this.add(speedtable);

    }

    private void loadImages(){
        try {
            classWithImage.put(Saab95.class, ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Saab95.jpg")));
            classWithImage.put(Volvo240.class, ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Volvo240.jpg")));
            classWithImage.put(Scania.class, ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Scania.jpg")));
        } catch (IOException ex){
            ex.printStackTrace();
        }
    }

    // This method is called each time the panel updates/refreshes/repaints itself
    // TODO: Change to suit your needs.
    //@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (VehicleInterface vehicle : vehicleCopy) {
            g.drawImage(classWithImage.get(vehicle.getClass()), (int)Math.round(vehicle.getPosition().getX()), (int)Math.round(vehicle.getPosition().getY()), null);
        }
        // see javadoc for more info on the parameters
    }

    public void actOnUpdate(ArrayList<VehicleInterface> vehicleCopy) {
        this.vehicleCopy = vehicleCopy;
        repaint();
    }
}
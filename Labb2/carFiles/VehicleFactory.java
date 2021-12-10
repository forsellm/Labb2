package carFiles;

import CarModelTree.Saab95;
import CarModelTree.Scania;
import CarModelTree.Volvo240;

import java.awt.Color;

public class VehicleFactory {
    public Volvo240 createVolvo240(){
        return new Volvo240();
    }
    public Saab95 createSaab95(){
        return new Saab95();
    }
    public Scania createScania(Color color, int enginepower){
        return new Scania(color,enginepower);
    }

}

package carFiles;

import CarModelTree.VehicleInterface;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class SpeedTable extends JPanel implements AnimateListener{
    private ArrayList<VehicleInterface> vehicleCopy = new ArrayList<>();
    private ArrayList<JLabel> labelArrayList = new ArrayList<>();


    public SpeedTable(int x, int y){
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(x, y));
        this.setBackground(Color.CYAN);

        //this.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 25));
    }

    private void createLabels(){
        labelArrayList.clear();
        this.removeAll();
        for (VehicleInterface vehicle : vehicleCopy) {
            JLabel label = new JLabel();
            labelArrayList.add(label);
            this.add(label);
        }
    }

    private void correctsLabelAmount() {
        if (labelArrayList.size() != vehicleCopy.size()) {
            createLabels();
        }
    }

    protected void paintComponent(Graphics g) {
        correctsLabelAmount();
        //this.removeAll();
        super.paintComponent(g);
        int i = 0;
        for (VehicleInterface vehicle: vehicleCopy) {
            labelArrayList.get(i).setText("Position "+(i+1)+" "+vehicle.getModelName()+" : "+(double)Math.round(vehicle.getCurrentSpeed()*100)/100);
            i++;
        }
    }

    @Override
    public void actOnUpdate(ArrayList<VehicleInterface> vehicleList) {
        this.vehicleCopy = vehicleList;
        repaint();
    }


}

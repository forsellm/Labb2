package CarModelTree;
import java.awt.geom.Point2D;
public interface VehicleInterface {
    void move();
    void turnLeft();
    void turnRight();
    boolean gas(double gas);
    boolean brake(double brake);
    void startEngine();
    void stopEngine();
    void setPosition(Point2D.Double position);
    Point2D getPosition();
    Directions getFacingDirection();
    void invertDirection();
    double getCurrentSpeed();
    String getModelName();
}

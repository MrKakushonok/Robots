package gui;
import java.awt.*;
import java.awt.geom.Point2D;

/**
 * Simplest implementation of a game entity for <see>GameVisualiser</see>
 * Has an ability to:
 *  - teleport
 *  - be drawn(has simple square model)
 * To create entity with different sprite:
 *  extend your class of <code>GameObject</code> and override draw method
 */
public class GameObject {
    String name;
    volatile Point2D.Double position;
    volatile double angle;
    boolean hasCollision; // not implemented
    double collisionRadius; // not implemented

    public GameObject(){
        this(0, 0, 0);
    }

    public GameObject(double x, double y){
        this(x, y, 0);
    }

    public GameObject(double x, double y, double ang){
        position = new Point2D.Double(x, y);
        angle = ang;
    }

    public void teleport(double x, double y, double ang){
        position.x = x;
        position.y = y;
        angle = ang;
    }

    public void draw(Graphics2D graphic){
        graphic.setColor(Color.BLACK);
        graphic.drawOval((int)position.x, (int)position.y, 10, 10);
        graphic.setColor(Color.WHITE);
        graphic.fillOval((int)position.x, (int)position.y, 10, 10);
    }

    public void update(){

    }
}

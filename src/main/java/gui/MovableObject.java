package gui;

import java.awt.*;
import java.awt.geom.Point2D;

/**
 * Extension of a <code>GameObject</code>
 * With implementation of a simple linear movement
 */

public class MovableObject extends GameObject{
    double velocity;  //pix per tick
    Point2D.Double destination;
    boolean isMoving;

    public MovableObject(){
        super();
        velocity = 0;
    }
    public MovableObject(double x, double y, double ang, double vel){
        super(x, y, ang);
        velocity = vel;
        setMovement(true);
    }

    public void move() {
        if(position.distance(destination) < velocity){ return; }
        position.x = position.x + velocity * Math.cos(angle);
        position.y = position.y + velocity * Math.sin(angle);
    }

    public void setDestination(double x, double y){
        angle = Util.angleTo(position.x, position.y, x, y);
        destination = new Point2D.Double(x, y);
    }

    public void changeVelocity(double vel){
        velocity = vel;
    }

    public void toggleMovement(){
        if(isMoving){ isMoving = false; }
        if(!isMoving){ isMoving = true; }
    }

    public void setMovement(boolean m){
        isMoving = m;
    }

    protected void drawDestination(Graphics2D graphic){
        int destinationCenterX = Util.round(destination.x);
        int destinationCenterY = Util.round(destination.y);
        graphic.setColor(Color.GREEN);
        graphic.fillOval(destinationCenterX - 3, destinationCenterY - 3, 6, 6);
        graphic.setColor(Color.BLACK);
        graphic.drawOval(destinationCenterX - 3, destinationCenterY - 3, 6, 6);
    }

    @Override
    public void update(){
        if(isMoving){ move(); }
    }

    @Override
    public void draw(Graphics2D graphic){
        super.draw(graphic);
        drawDestination(graphic);
    }
}

package gui;

import java.awt.*;
import java.awt.geom.AffineTransform;

/**
 * Default player object with a simple custom model
 */
public class RobotObject extends VehicleObject{

    public RobotObject(){
        super();
    }

    public RobotObject(double x, double y, double ang, double vel, double angVel){
        super(x, y, ang, vel, angVel);
    }

    @Override
    public void draw(Graphics2D graphic) {
        drawDestination(graphic);
        int robotCenterX = Util.round(position.x);
        int robotCenterY = Util.round(position.y);
        AffineTransform t = AffineTransform.getRotateInstance(angle, robotCenterX, robotCenterY);
        graphic.setTransform(t);
        graphic.setColor(Color.MAGENTA);
        graphic.fillOval(robotCenterX - 16, robotCenterY - 6, 32, 12);
        graphic.setColor(Color.BLACK);
        graphic.drawOval(robotCenterX - 16, robotCenterY - 6, 32, 12);
        graphic.setColor(Color.WHITE);
        graphic.fillOval(robotCenterX + 6, robotCenterY - 3, 6, 6);
        graphic.setColor(Color.BLACK);
        graphic.drawOval(robotCenterX + 6, robotCenterY - 3, 6, 6);
    }
}

package gui;

import java.awt.geom.Point2D;

/**
 * Extension of a <code>MovableObject</code>
 * With restriction of an angular movement in a single tick
 */

public class VehicleObject extends MovableObject{
    double angularVelocity;  // radians per tick

    public VehicleObject(){
        super();
        angularVelocity = 0;
    }

    public VehicleObject(double x, double y, double ang, double vel, double angVel){
        super(x, y, ang, vel);
        angularVelocity = angVel;
    }

    @Override
    public void setDestination(double x, double y){
        destination = new Point2D.Double(x, y);
    }

    @Override
    public void move(){
        double angDiff = Util.angleDifference(angle, Util.angleTo(position.x, position.y, destination.x, destination.y));
        if(Math.abs(angDiff) > angularVelocity) angle = angle + Math.signum(angDiff)*angularVelocity;
        super.move();
    }
}

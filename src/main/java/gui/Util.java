package gui;

public class Util {
    static double angleTo(double fromX, double fromY, double toX, double toY)
    {
        double diffX = toX - fromX;
        double diffY = toY - fromY;

        return asNormalizedRadians(Math.atan2(diffY, diffX));
    }

    static double asNormalizedRadians(double angle)
    {
        while (angle < 0)
        {
            angle += 2*Math.PI;
        }
        while (angle >= 2*Math.PI)
        {
            angle -= 2*Math.PI;
        }
        return angle;
    }

    static int round(double value)
    {
        return (int)(value + 0.5);
    }

    static double angleDifference(double angle1, double angle2){
        double diff = asNormalizedRadians(angle2) - asNormalizedRadians(angle1);
        if(Math.abs(diff) < Math.PI) return diff;
        if(diff >= 0) return diff - 2*Math.PI;
        return 2*Math.PI + diff;
    }
}

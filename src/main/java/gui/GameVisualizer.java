package gui;

import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JPanel;

public class GameVisualizer extends JPanel
{
    private final List<GameObject> gameObjects = new ArrayList<>(0);
    private final RobotObject playerObject = new RobotObject(100, 100, 0, 1, 0.01);

    
    private static Timer initTimer() 
    {
        return new Timer("events generator", true);
    }
    
    public GameVisualizer()
    {
        Timer m_timer = initTimer();
        m_timer.schedule(new TimerTask()
        {
            @Override
            public void run()
            {
                onRedrawEvent();
            }
        }, 0, 50);
        m_timer.schedule(new TimerTask()
        {
            @Override
            public void run()
            {
                update();
            }
        }, 0, 10);
        addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                setTargetPosition(e.getPoint());
                repaint();
            }
        });
        playerObject.setDestination(150, 150);
        gameObjects.add(playerObject);
        setDoubleBuffered(true);
    }

    public void update(){
        for(GameObject entity : gameObjects){
            entity.update();
        }
    }

    protected void setTargetPosition(Point p)
    {
        playerObject.setDestination(p.x, p.y);
    }
    
    protected void onRedrawEvent()
    {
        EventQueue.invokeLater(this::repaint);
    }

    @Override
    public void paint(Graphics g)
    {
        super.paint(g);
        Graphics2D g2d = (Graphics2D)g; 
        for(GameObject entity : gameObjects){
            entity.draw(g2d);
        }
    }
}

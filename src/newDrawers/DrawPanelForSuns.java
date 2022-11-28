package newDrawers;

import newDrawers.lineAndPixelDrawers.BresenhamLineDrawer;
import newDrawers.lineAndPixelDrawers.GraphicsPixelDrawer;
import newDrawers.lineAndPixelDrawers.interfaces.LineDrawer;
import newDrawers.lineAndPixelDrawers.interfaces.PixelDrawer;
import oldDrawers.ArrowDrawer;
import points.RealPoint;
import points.ScreenConverter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class DrawPanelForSuns extends JPanel
{
    private final ScreenConverter sc;
    private final ArrayList<SunDrawer> suns = new ArrayList<>();
    private SunDrawer currentSun;

    private double aCorr = 0d;
    private int l = 0;

    public DrawPanelForSuns()
    {
        sc = new ScreenConverter(0, 0, 800, 600, 800, 600);
        this.addMouseListener(new MouseAction());
    }

    @Override
    protected void paintComponent(Graphics gs)
    {
        super.paintComponent(gs);
        sc.setSh(getHeight());
        sc.setSw(getWidth());
        Graphics2D gr = (Graphics2D) gs;
        PixelDrawer pd = new GraphicsPixelDrawer(gr);
//        LineDrawer line = new BresenhamLineDrawer(pd, new RealPoint(10, 20), new RealPoint(500, 100), sc, Color.black);
//        line.drawLine();
        SunDrawer sun = new SunDrawer(new RealPoint(100, 200), 50, 10, aCorr, l, Color.ORANGE);
        sun.drawSun(pd, sc);
    }

    class MouseAction extends MouseAdapter
    {
        @Override
        public void mouseClicked(MouseEvent e)
        {
            if (e.getButton() == MouseEvent.BUTTON1)
            {
                aCorr += 0.01;
                repaint();
            }
            if (e.getButton() == MouseEvent.BUTTON3)
            {
                l++;
                repaint();
            }
        }
    }
}

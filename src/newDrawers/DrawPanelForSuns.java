package newDrawers;

import newDrawers.lineAndPixelDrawers.GraphicsPixelDrawer;
import newDrawers.lineAndPixelDrawers.interfaces.PixelDrawer;
import points.RealPoint;
import points.ScreenConverter;
import points.ScreenPoint;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

public class DrawPanelForSuns extends JPanel
{
    private final ScreenConverter sc;
    private final ArrayList<SunDrawer> suns;
    private RealPoint currCP, currRP, currLP;
    private int currN;
    private double currA;
    private int numOfSuns;
    private int keyPointNum;
    private int keyPointID;
    private boolean flagShow = false;

    public DrawPanelForSuns()
    {
        sc = new ScreenConverter(0, 0, 800, 600, 800, 600);
        suns = new ArrayList<>();
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

        if (keyPointNum == 2)
        {
            new SunDrawer(currCP, distanceBetweenRP(currCP, currRP), 0, 0, 0, Color.ORANGE).drawSun(pd, sc);
        }
        else if (keyPointNum == 3)
        {
            new SunDrawer(currCP, distanceBetweenRP(currCP, currRP), currN, currA,
                    distanceBetweenRP(currCP, currLP) - distanceBetweenRP(currCP, currRP),
                    Color.ORANGE).drawSun(pd, sc);
        }

        for (SunDrawer sunDrawer : suns)
        {
            sunDrawer.drawSun(pd, sc);
        }

        if (flagShow)
        {
            if (keyPointNum > 0)
            {
                ScreenPoint tempSP1 = sc.r2s(new RealPoint(currCP.getX() - 5, currCP.getY() - 5));
                ScreenPoint tempSP2 = sc.r2s(new RealPoint(currCP.getX() + 5, currCP.getY() + 5));
                for (int i = tempSP1.getC(); i < tempSP2.getC(); i++)
                {
                    for (int j = tempSP1.getR(); j < tempSP2.getR(); j++)
                    {
                        pd.drawPixel(i, j, Color.blue);
                    }
                }
                if (keyPointNum > 1)
                {
                    tempSP1 = sc.r2s(new RealPoint(currRP.getX() - 5, currRP.getY() - 5));
                    tempSP2 = sc.r2s(new RealPoint(currRP.getX() + 5, currRP.getY() + 5));
                    for (int i = tempSP1.getC(); i < tempSP2.getC(); i++)
                    {
                        for (int j = tempSP1.getR(); j < tempSP2.getR(); j++)
                        {
                            pd.drawPixel(i, j, Color.blue);
                        }
                    }
                    if (keyPointNum > 2)
                    {
                        tempSP1 = sc.r2s(new RealPoint(currLP.getX() - 5, currLP.getY() - 5));
                        tempSP2 = sc.r2s(new RealPoint(currLP.getX() + 5, currLP.getY() + 5));
                        for (int i = tempSP1.getC(); i < tempSP2.getC(); i++)
                        {
                            for (int j = tempSP1.getR(); j < tempSP2.getR(); j++)
                            {
                                pd.drawPixel(i, j, Color.blue);
                            }
                        }
                    }
                }
            }
        }
    }

    class MouseAction extends MouseAdapter
    {
        @Override
        public void mouseClicked(MouseEvent e)
        {
            if (e.getButton() == MouseEvent.BUTTON1)
            {
                RealPoint tempRP = sc.s2r(new ScreenPoint(e.getX(), e.getY()));
                if (keyPointNum == 0)
                {
                    flagShow = true;
                    numOfSuns++;
                    currN = 10;
                    currA = 0;
                    currCP = sc.s2r(new ScreenPoint(e.getX(), e.getY()));
                    keyPointNum++;
                    repaint();
                }
                else if (keyPointNum == 1)
                {
                    currRP = sc.s2r(new ScreenPoint(e.getX(), e.getY()));
                    keyPointNum++;
                    repaint();
                }
                else if (keyPointNum == 2)
                {
                    currLP = sc.s2r(new ScreenPoint(e.getX(), e.getY()));
                    keyPointNum++;
                    repaint();
                }
            }
            if (e.getButton() == MouseEvent.BUTTON3)
            {
                if (keyPointNum == 3)
                {
                    suns.add(new SunDrawer(currCP, distanceBetweenRP(currCP, currRP), currN, currA,
                            distanceBetweenRP(currCP, currLP) - distanceBetweenRP(currCP, currRP), Color.ORANGE));
                    flagShow = false;
                    keyPointNum = 0;
                    repaint();
                }
            }
        }

        @Override
        public void mousePressed(MouseEvent e)
        {
            RealPoint tempRP = sc.s2r(new ScreenPoint(e.getX(), e.getY()));
        }
    }

    class MouseMotion extends MouseMotionAdapter
    {
        @Override
        public void mouseDragged(MouseEvent e)
        {
            RealPoint tempRP = sc.s2r(new ScreenPoint(e.getX(), e.getY()));
        }
    }

    private double distanceBetweenRP(RealPoint p1, RealPoint p2)
    {
        return Math.sqrt(Math.pow(p1.getX() - p2.getX(), 2) + Math.pow(p1.getY() - p2.getY(), 2));
    }
}

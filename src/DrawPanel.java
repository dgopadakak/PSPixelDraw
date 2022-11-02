import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

public class DrawPanel extends JPanel
{
    private int keyPointNum = 0;
    private int keyPointID = -1;
    private boolean flagShow;
    private final ArrayList<ArrowDrawer> arrowDrawerArrayList;
    private int numOfArrows = 0;

    private int currX1B, currY1B, currX2B, currY2B, currXC, currYC;

    public DrawPanel()
    {
        super();
        this.addMouseListener(new MouseAction());
        this.addMouseMotionListener(new MouseMotion());
        arrowDrawerArrayList = new ArrayList<>();
    }

    @Override
    public void paintComponent(Graphics gs)
    {
        super.paintComponent(gs);
        Graphics2D gr = (Graphics2D) gs;
        PixelDrawer pd = new GraphicsPixelDrawer(gr);

        PixelDrawer flag = new GraphicsPixelDrawer(gr);

        if (flagShow)
        {
            if (keyPointNum == 1)
            {
                for (int i = -5; i < 4; i++)
                {
                    for (int j = -5; j < 4; j++)
                    {
                        flag.drawPixel(currX1B + i, currY1B + j, Color.blue);
                    }
                }
            }
            if (keyPointNum == 2)
            {
                for (int i = -5; i < 4; i++)
                {
                    for (int j = -5; j < 4; j++)
                    {
                        flag.drawPixel(currX1B + i, currY1B + j, Color.blue);
                        flag.drawPixel(currX2B + i, currY2B + j, Color.blue);
                    }
                }
            }
            if (keyPointNum == 3)
            {
                for (int i = -5; i < 4; i++)
                {
                    for (int j = -5; j < 4; j++)
                    {
                        flag.drawPixel(currX1B + i, currY1B + j, Color.blue);
                        flag.drawPixel(currX2B + i, currY2B + j, Color.blue);
                        flag.drawPixel(currXC + i, currYC + j, Color.blue);
                    }
                }
            }
        }

        if (keyPointNum == 3 && arrowDrawerArrayList.size() < numOfArrows)
        {
            ArrowDrawer arrowDrawer = new ArrowDrawer(/*pd,*/ currX1B, currY1B, currX2B, currY2B, currXC, currYC, Color.black);
            arrowDrawer.drawArrow(pd);
        }

        for (ArrowDrawer arrowDrawer : arrowDrawerArrayList)
        {
            arrowDrawer.drawArrow(pd);
        }
    }

    class MouseAction extends MouseAdapter
    {
        @Override
        public void mouseClicked(MouseEvent e)
        {
            if (e.getButton() == MouseEvent.BUTTON1)
            {
                if (keyPointNum == 0)
                {
                    numOfArrows++;
                    flagShow = true;
                    currX1B = e.getX();
                    currY1B = e.getY();
                    keyPointNum++;
                    repaint();
                }
                else if (keyPointNum == 1)
                {
                    currX2B = e.getX();
                    currY2B = e.getY();
                    keyPointNum++;
                    repaint();
                }
                else if (keyPointNum == 2)
                {
                    currXC = e.getX();
                    currYC = e.getY();
                    keyPointNum++;
                    repaint();
                }
            }
            else if (e.getButton() == MouseEvent.BUTTON3)
            {
                if (keyPointNum == 3)
                {
                    arrowDrawerArrayList.add(new ArrowDrawer( currX1B, currY1B, currX2B, currY2B, currXC, currYC, Color.black));
                    flagShow = false;
                    keyPointNum = 0;
                    repaint();
                }
            }
        }

        @Override
        public void mousePressed(MouseEvent e)
        {
            int x = e.getX();
            int y = e.getY();
            if (x-4 < currX1B && currX1B < x+4 && y-4 < currY1B && currY1B < y+4)
            {
                keyPointID = 1;
            }
            else if (x-4 < currX2B && currX2B < x+4 && y-4 < currY2B && currY2B < y+4)
            {
                keyPointID = 2;
            }
            else if (x-4 < currXC && currXC < x+4 && y-4 < currYC && currYC < y+4)
            {
                keyPointID = 3;
            }
            else
            {
                keyPointID = -1;
            }
        }
    }

    class MouseMotion extends MouseMotionAdapter
    {
        @Override
        public void mouseDragged(MouseEvent e)
        {
            if (keyPointID == 1)
            {
                currX1B = e.getX();
                currY1B = e.getY();
                repaint();
            }
            else if (keyPointID == 2)
            {
                currX2B = e.getX();
                currY2B = e.getY();
                repaint();
            }
            else if (keyPointID == 3)
            {
                currXC = e.getX();
                currYC = e.getY();
                repaint();
            }
        }
    }
}

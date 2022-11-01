import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

public class DrawPanel extends JPanel
{
    private int keyPointNum = 0;
    private int keyPointID = -1;
    private boolean flagShow = true;

    private int currX1B, currY1B, currX2B, currY2B, currXC, currYC;

    public DrawPanel()
    {
        super();
        this.addMouseListener(new MouseAction());
        //this.addMouseMotionListener(new MouseMotion());
    }

    @Override
    public void paintComponent(Graphics gs)
    {
        super.paintComponent(gs);
        Graphics2D gr = (Graphics2D) gs;
        PixelDrawer pd = new GraphicsPixelDrawer(gr);

        if (flagShow)
        {

        }
//        ArrowDrawer arrowDrawer = new ArrowDrawer(pd, 10, 10, 30, 100, 20, 80, Color.black);
//        arrowDrawer.drawArrow();


//        LineDrawer ld1 = new VuLineDrawer(pd, 40, 80, 100, 300, Color.blue);
//        ld1.drawLine();
//
//        LineDrawer ld2 = new VuLineDrawer(pd, 60, 80, 120, 300, Color.BLACK);
//        ld2.drawLine();
//
//
//        LineDrawer ld3 = new BresenhamLineDrawer(pd, 100, 80, 160, 300, Color.blue);
//        ld3.drawLine();
//
//        LineDrawer ld4 = new BresenhamLineDrawer(pd, 120, 80, 180, 300, Color.BLACK);
//        ld4.drawLine();
//
//
//        LineDrawer ld5 = new DDALineDrawer(pd, 160, 80, 220, 300, Color.blue);
//        ld5.drawLine();
//
//        LineDrawer ld6 = new DDALineDrawer(pd, 180, 80, 240, 300, Color.BLACK);
//        ld6.drawLine();
    }

    class MouseAction extends MouseAdapter
    {
        @Override
        public void mouseClicked(MouseEvent e)
        {
            // Щелкните левую кнопку мыши
            if (e.getButton() == MouseEvent.BUTTON1)
            {
                if (keyPointNum == 0)
                {
                    currX1B = e.getX();
                    currY1B = e.getY();
                    keyPointNum++;
                }
                else if (keyPointNum == 1)
                {
                    currX2B = e.getX();
                    currY2B = e.getY();
                    keyPointNum++;
                }
                else if (keyPointNum == 2)
                {
                    currXC = e.getX();
                    currYC = e.getY();
                    keyPointNum++;
                    repaint();
                }

//                if (keyPointNum < 3) {
//                    double x = e.getX();
//                    double y = e.getY();
//                    keyPointP[keyPointNum] = new Point2D.Double(x, y);
//                    keyPointE[keyPointNum] = new Ellipse2D.Double(x - 4, y - 4, 8, 8);
//                    keyPointNum++;
//                    repaint();
//                }
            } // Щелкните правой кнопкой мыши
            else if (e.getButton() == MouseEvent.BUTTON3)
            {
                flagShow = false; // скрыть силу синей помощи, но на самом деле не удаляет
                repaint();
            }
        }

//        @Override
//        public void mousePressed(MouseEvent e)
//        {
//            // Нажмите мышь, чтобы определить, является ли она ключевой точкой.
//            for (int i = 0; i < keyPointNum; i++)
//            {
//                if (keyPointE[i].contains((Point2D) e.getPoint()))
//                {
//                    keyPointID = i;
//                    break;
//                }
//                else
//                {
//                    keyPointID = -1;
//                }
//            }
//        }
    }

//    class MouseMotion extends MouseMotionAdapter
//    {
//        @Override
//        public void mouseDragged(MouseEvent e)
//        {
//            // Клавиша перетаскивания мыши
//            if (keyPointID != -1)
//            {
//                double x = e.getX();
//                double y = e.getY();
//                keyPointP[keyPointID] = new Point2D.Double(x, y);
//                keyPointE[keyPointID] = new Ellipse2D.Double(x - 4, y - 4, 8, 8);
//                repaint();
//            }
//        }
//    }
}

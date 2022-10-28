import javax.swing.*;
import java.awt.*;

public class DrawPanel extends JPanel
{
    @Override
    public void paint(Graphics g)
    {
        Graphics2D gr = (Graphics2D) g;

        PixelDrawer pd = new GraphicsPixelDrawer(gr);

        LineDrawer ld1 = new VuLineDrawer();
        ld1.drawLine(pd, 40, 80, 100, 300, Color.blue);

        LineDrawer ld2 = new VuLineDrawer();
        ld2.drawLine(pd, 60, 80, 120, 300, Color.BLACK);


        LineDrawer ld3 = new BresenhamLineDrawer();
        ld3.drawLine(pd, 100, 80, 160, 300, Color.blue);

        LineDrawer ld4 = new BresenhamLineDrawer();
        ld4.drawLine(pd, 120, 80, 180, 300, Color.BLACK);


        LineDrawer ld5 = new DDALineDrawer();
        ld5.drawLine(pd, 160, 80, 220, 300, Color.blue);

        LineDrawer ld6 = new DDALineDrawer();
        ld6.drawLine(pd, 180, 80, 240, 300, Color.BLACK);
    }
}

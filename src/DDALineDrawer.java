import java.awt.*;

public class DDALineDrawer implements LineDrawer
{
    private final PixelDrawer pd;
    private int x1, x2, y1, y2;
    private final Color c;

    public DDALineDrawer(PixelDrawer pd, int x1, int y1, int x2, int y2, Color c)
    {
        this.pd = pd;
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
        this.c = c;
    }

    public void setX1(int x1) {
        this.x1 = x1;
    }

    public void setX2(int x2) {
        this.x2 = x2;
    }

    public void setY1(int y1) {
        this.y1 = y1;
    }

    public void setY2(int y2) {
        this.y2 = y2;
    }

    @Override
    public void drawLine()
    {
        int dx = x2 - x1;
        int dy = y2 - y1;
        double D = Math.max(Math.abs(dx), Math.abs(dy));

        double stepY = (double) dy / D;
        double stepX = (double) dx / D;

        for (int i = 0; i <= D; i++)
        {
            pd.drawPixel(x1 + (int) (stepX * i), y1 + (int) (stepY * i), c);
        }
    }
}

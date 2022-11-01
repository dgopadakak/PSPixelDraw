import java.awt.*;

public class VuLineDrawer implements LineDrawer
{
    private final PixelDrawer pd;
    private int x1, x2, y1, y2;
    private final Color c;

    public VuLineDrawer(PixelDrawer pd, int x1, int y1, int x2, int y2, Color c)
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
        if (x2 < x1) {
            x1 += x2;
            x2 = x1 - x2;
            x1 -= x2;
            y1 += y2;
            y2 = y1 - y2;
            y1 -= y2;
        }
        int dx = x2 - x1;
        int dy = y2 - y1;
        //Горизонтальные и вертикальные линии не нуждаются в сглаживании
        if (dx == 0 || dy == 0)
        {
            LineDrawer ld = new DDALineDrawer(pd, x1, y1, x2, y2, c);
            ld.drawLine();
            return;
        }
        float gradient = 0;
        if (dx > dy)
        {
            gradient = (float) dy / dx;
            float intery = y1 + gradient;
            pd.drawPixel(x1, y1, c);
            for (int x = x1; x < x2; ++x)
            {
                pd.drawPixel(x, (int) intery, new Color(c.getRed(), c.getGreen(), c.getBlue(),
                        (int) (255 - fractionalPart(intery) * 255)));
                pd.drawPixel(x, (int)intery + 1,
                        new Color(c.getRed(), c.getGreen(), c.getBlue(), (int) (fractionalPart(intery) * 255)));
                intery += gradient;
            }
            pd.drawPixel(x2, y2, c);
        }
        else
        {
            gradient = (float) dx / dy;
            float interx = x1 + gradient;
            pd.drawPixel(x1, y1, c);
            for (int y = y1; y < y2; ++y)
            {
                pd.drawPixel((int)interx, y,
                        new Color(c.getRed(), c.getGreen(), c.getBlue(), (int) (255 - fractionalPart(interx) * 255)));
                pd.drawPixel((int)interx + 1, y,
                        new Color(c.getRed(), c.getGreen(), c.getBlue(), (int) (fractionalPart(interx) * 255)));
                interx += gradient;
            }
            pd.drawPixel(x2, y2, c);
        }
    }

    private float fractionalPart(float x)
    {
        int tmp = (int) x;
        return x - tmp; //вернёт дробную часть числа
    }
}

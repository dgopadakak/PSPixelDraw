package newDrawers;

import newDrawers.lineAndPixelDrawers.BresenhamLineDrawer;
import newDrawers.lineAndPixelDrawers.interfaces.LineDrawer;
import newDrawers.lineAndPixelDrawers.interfaces.PixelDrawer;
import points.RealPoint;
import points.ScreenConverter;
import points.ScreenPoint;

import java.awt.*;

public class SunDrawer
{
    private final RealPoint rpc;
    private int r, n, l;
    private double aCorr;
    private final Color c;

    public SunDrawer(RealPoint rpc, int r, int n, double aCorr, int l, Color c)
    {
        this.rpc = rpc;
        this.r = r;
        this.n = n;
        this.aCorr = aCorr;
        this.l = l;
        this.c = c;
    }

    public void drawSun(PixelDrawer pd, ScreenConverter sc)
    {
        ScreenPoint spc = sc.r2s(rpc);
        ScreenPoint tempScreenPointForRadius1 = sc.r2s(new RealPoint(rpc.getX(), rpc.getY() + r));
        int screenRadius1 = tempScreenPointForRadius1.getR() - spc.getR();      // по вертикали
        ScreenPoint tempScreenPointForRadius2 = sc.r2s(new RealPoint(rpc.getX() + r, rpc.getY()));
        int screenRadius2 = tempScreenPointForRadius2.getC() - spc.getC();      // по горизонтали
        drawFilledCircle(pd, spc, screenRadius1, screenRadius2);
        drawRays(pd, sc);
    }

    private void drawFilledCircle(PixelDrawer pd, ScreenPoint spc, int screenRadius1, int screenRadius2)
    {
        for (int i = spc.getC() - screenRadius2; i <= spc.getC() + screenRadius2; i++)      // по горизонтали
        {
            for (int j = spc.getR() - screenRadius1; j <= spc.getR() + screenRadius1; j++)      // по вертикали
            {
                if (Math.pow(i - spc.getC(), 2) / Math.pow(screenRadius2, 2)
                        + Math.pow(j - spc.getR(), 2) / Math.pow(screenRadius1, 2) <= 1)        // формула эллипса
                {
                    pd.drawPixel(i, j, c);
                }
            }
        }
    }

    private void drawRays(PixelDrawer pd, ScreenConverter sc)
    {
        double da = 2*Math.PI / n;
        for (int i = 0; i < n; i++)
        {
            double a = i * da + aCorr;
            double x1 = r*Math.cos(a) + rpc.getX();
            double y1 = r*Math.sin(a) + rpc.getY();
            double x2 = (r+l)*Math.cos(a) + rpc.getX();
            double y2 = (r+l)*Math.sin(a) + rpc.getY();
            LineDrawer line = new BresenhamLineDrawer(pd, new RealPoint(x1, y1), new RealPoint(x2, y2), sc, c);
            line.drawLine();
        }
    }
}

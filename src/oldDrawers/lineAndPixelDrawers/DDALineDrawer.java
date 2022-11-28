package oldDrawers.lineAndPixelDrawers;

import oldDrawers.lineAndPixelDrawers.interfaces.LineDrawer;
import oldDrawers.lineAndPixelDrawers.interfaces.PixelDrawer;

import java.awt.*;

public record DDALineDrawer(PixelDrawer pd, int x1, int y1, int x2, int y2, Color c) implements LineDrawer
{
    @Override
    public void drawLine()
    {
        int dx = x2 - x1;
        int dy = y2 - y1;
        double D = Math.max(Math.abs(dx), Math.abs(dy));

        double stepY = (double) dy / D;
        double stepX = (double) dx / D;

        for (int i = 0; i <= D; i++) {
            pd.drawPixel(x1 + (int) (stepX * i), y1 + (int) (stepY * i), c);
        }
    }
}

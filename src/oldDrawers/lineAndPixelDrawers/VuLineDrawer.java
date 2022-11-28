package oldDrawers.lineAndPixelDrawers;

import oldDrawers.lineAndPixelDrawers.interfaces.LineDrawer;
import oldDrawers.lineAndPixelDrawers.interfaces.PixelDrawer;

import java.awt.*;

public record VuLineDrawer(PixelDrawer pd, int x1, int y1, int x2, int y2, Color c) implements LineDrawer
{
    @Override
    public void drawLine() {
        int nx1 = x1;
        int nx2 = x2;
        int ny1 = y1;
        int ny2 = y2;
        if (nx2 < nx1) {
            nx1 += nx2;
            nx2 = nx1 - nx2;
            nx1 -= nx2;
            ny1 += ny2;
            ny2 = ny1 - ny2;
            ny1 -= ny2;
        }
        int dx = nx2 - nx1;
        int dy = ny2 - ny1;
        //Горизонтальные и вертикальные линии не нуждаются в сглаживании
        if (dx == 0 || dy == 0) {
            LineDrawer ld = new DDALineDrawer(pd, nx1, ny1, nx2, ny2, c);
            ld.drawLine();
            return;
        }
        float gradient = 0;
        if (dx > dy) {
            gradient = (float) dy / dx;
            float intery = ny1 + gradient;
            pd.drawPixel(nx1, ny1, c);
            for (int x = nx1; x < nx2; ++x) {
                pd.drawPixel(x, (int) intery, new Color(c.getRed(), c.getGreen(), c.getBlue(),
                        (int) (255 - fractionalPart(intery) * 255)));
                pd.drawPixel(x, (int) intery + 1,
                        new Color(c.getRed(), c.getGreen(), c.getBlue(), (int) (fractionalPart(intery) * 255)));
                intery += gradient;
            }
            pd.drawPixel(nx2, ny2, c);
        } else {
            gradient = (float) dx / dy;
            float interx = nx1 + gradient;
            pd.drawPixel(nx1, ny1, c);
            for (int y = ny1; y < ny2; ++y) {
                pd.drawPixel((int) interx, y,
                        new Color(c.getRed(), c.getGreen(), c.getBlue(), (int) (255 - fractionalPart(interx) * 255)));
                pd.drawPixel((int) interx + 1, y,
                        new Color(c.getRed(), c.getGreen(), c.getBlue(), (int) (fractionalPart(interx) * 255)));
                interx += gradient;
            }
            pd.drawPixel(nx2, ny2, c);
        }
    }

    private float fractionalPart(float x) {
        int tmp = (int) x;
        return x - tmp; //вернёт дробную часть числа
    }
}

package newDrawers.lineAndPixelDrawers;

import newDrawers.lineAndPixelDrawers.interfaces.LineDrawer;
import newDrawers.lineAndPixelDrawers.interfaces.PixelDrawer;
import points.RealPoint;
import points.ScreenConverter;
import points.ScreenPoint;

import java.awt.*;

public record BresenhamLineDrawer(PixelDrawer pd, RealPoint p1, RealPoint p2, ScreenConverter sc, Color c) implements LineDrawer
{
    @Override
    public void drawLine()
    {
        ScreenPoint sp1 = sc.r2s(p1);
        ScreenPoint sp2 = sc.r2s(p2);
        int x, y, dx, dy, incx, incy, pdx, pdy, es, el, err;

        dx = sp2.getC() - sp1.getC();//проекция на ось икс
        dy = sp2.getR() - sp1.getR();//проекция на ось игрек

        incx = Integer.compare(dx, 0);  // возвращает 0, если аргумент (x) равен нулю; -1, если x < 0 и 1, если x > 0.
        /*
         * Определяем, в какую сторону нужно будет сдвигаться. Если dx < 0, т.е. отрезок идёт
         * справа налево по иксу, то incx будет равен -1.
         * Это будет использоваться в цикле постороения.
         */
        incy = Integer.compare(dy, 0);  // возвращает 0, если аргумент (x) равен нулю; -1, если x < 0 и 1, если x > 0.
        /*
         * Аналогично. Если рисуем отрезок снизу вверх -
         * это будет отрицательный сдвиг для y (иначе - положительный).
         */

        if (dx < 0) dx = -dx;//далее мы будем сравнивать: "if (dx < dy)"
        if (dy < 0) dy = -dy;//поэтому необходимо сделать dx = |dx|; dy = |dy|
        //эти две строчки можно записать и так: dx = Math.abs(dx); dy = Math.abs(dy);

        if (dx > dy)
        //определяем наклон отрезка:
        {
            /*
             * Если dx > dy, то значит отрезок "вытянут" вдоль оси икс, т.е. он скорее длинный, чем высокий.
             * Значит в цикле нужно будет идти по икс (строчка el = dx;), значит "протягивать" прямую по иксу
             * надо в соответствии с тем, слева направо и справа налево она идёт (pdx = incx;), при этом
             * по y сдвиг такой отсутствует.
             */
            pdx = incx;
            pdy = 0;
            es = dy;
            el = dx;
        } else//случай, когда прямая скорее "высокая", чем длинная, т.е. вытянута по оси y
        {
            pdx = 0;
            pdy = incy;
            es = dx;
            el = dy;//тогда в цикле будем двигаться по y
        }

        x = sp1.getC();
        y = sp1.getR();
        err = el / 2;
        pd.drawPixel(x, y, c);  // ставим первую точку
        //все последующие точки возможно надо сдвигать, поэтому первую ставим вне цикла

        for (int t = 0; t < el; t++)//идём по всем точкам, начиная со второй и до последней
        {
            err -= es;
            if (err < 0) {
                err += el;
                x += incx;  // сдвинуть прямую (сместить вверх или вниз, если цикл проходит по иксам)
                y += incy;  // или сместить влево-вправо, если цикл проходит по y
            } else {
                x += pdx;   // продолжить тянуть прямую дальше, т.е. сдвинуть влево или вправо, если
                y += pdy;   // цикл идёт по иксу; сдвинуть вверх или вниз, если по y
            }

            pd.drawPixel(x, y, c);
        }
    }
}

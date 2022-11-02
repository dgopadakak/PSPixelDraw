import java.awt.*;

public class ArrowDrawer
{
    private int x1b, x2b, y1b, y2b, xcL, ycL, xcR, ycR;
    private final Color c;

    public ArrowDrawer(int x1b, int y1b, int x2b, int y2b, int xcL, int ycL, Color c)
    {
        this.x1b = x1b;
        this.x2b = x2b;
        this.y1b = y1b;
        this.y2b = y2b;
        this.xcL = xcL;
        this.ycL = ycL;
        this.c = c;
    }

    public void drawArrow(PixelDrawer pd)
    {
        LineDrawer body = new DDALineDrawer(pd, x1b, y1b, x2b, y2b, c);
        LineDrawer cursorL = new DDALineDrawer(pd, x2b, y2b, xcL, ycL, c);
        findRightCursorLine();
        LineDrawer cursorR = new DDALineDrawer(pd, x2b, y2b, xcR, ycR, c);
        body.drawLine();
        cursorL.drawLine();
        cursorR.drawLine();
    }

    private void findRightCursorLine()
    {
        double A1, B1, C1, A2, B2, C2, ox, oy;//Используются для вычислений.
        A1=y2b-y1b;//Постоянные из уравнения прямой BC.
        B1=x1b-x2b;
        C1=y1b*(x2b-x1b)-x1b*(y2b-y1b);
        A2=-B1;//Постоянные из уравнения прямой a.
        B2=A1;
        C2=B1*xcL-A1*ycL;
        ox=(B1*C2-B2*C1)/(A1*B2-A2*B1);//Координаты точки О.
        oy=(C1*A2-C2*A1)/(A1*B2-A2*B1);
        xcR= (int) (2*ox-xcL);//Координаты точки D.
        ycR= (int) (2*oy-ycL);
    }
}

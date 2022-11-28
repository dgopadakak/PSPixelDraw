import newDrawers.DrawPanelForSuns;

import javax.swing.*;

public class Main
{
    public static void main(String[] args)
    {
        JFrame frame = new JFrame();
        //DrawPanelForArrows panel = new DrawPanelForArrows();
        DrawPanelForSuns panel = new DrawPanelForSuns();
        frame.add(panel);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setVisible(true);
    }
}


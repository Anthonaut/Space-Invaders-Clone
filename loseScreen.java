import java.awt.Color;
import java.awt.Graphics;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;


public class loseScreen extends GraphicsObject{
    private Color loseScreenColor;



    public loseScreen(double x,double y) {
        super(x, y);
        this.loseScreenColor = Color.MAGENTA;

    }

    @Override
    public void draw(Graphics g) {
        // Screen Color
        g.setColor(loseScreenColor);

        // "You suck"



        // Lose screen
        g.fillRect((int) 0, (int) 0, 600, 400);


    }


}

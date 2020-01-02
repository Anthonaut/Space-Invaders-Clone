import java.awt.Color;
import java.awt.Graphics;

public class winScreen extends GraphicsObject{
    private Color winScreenColor;

    public winScreen(double x,double y) {
        super(x, y);
        this.winScreenColor = Color.GREEN;
    }

    @Override
    public void draw(Graphics g) {
        // Screen Color
        g.setColor(winScreenColor);

        // Win screen
        g.fillRect((int) 0, (int) 0, 600, 400);
    }
}
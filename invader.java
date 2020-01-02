import java.awt.Color;
import java.awt.Graphics;

public class invader extends GraphicsObject {
    private Color invaderColor;


    public invader(double x, double y) {
        super(x, y);
        this.speed_x = 3;
        this.speed_y = 10;
        this.invaderColor = Color.BLACK;
    }

    @Override
    public void draw(Graphics g) {
        // invader color
        g.setColor(invaderColor);

        g.fillRect((int) this.x, (int) this.y, 20, 20);
    }
}

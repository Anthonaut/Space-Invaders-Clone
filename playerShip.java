import java.awt.Color;
import java.awt.Graphics;

public class playerShip extends GraphicsObject{
    private Color shipColor;

    public playerShip(double x, double y) {
        super(x, y);
        this.shipColor = Color.PINK;
        this.speed_x = 10;
    }

    @Override
    public void draw(Graphics g) {
        // Player ship Color
        g.setColor(shipColor);

        // Draw the player ship
        g.fillRect((int) this.x, (int) this.y, 20, 20);
    }
}

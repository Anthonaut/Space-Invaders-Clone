import java.awt.Color;
import java.awt.Graphics;

public class alienShip extends GraphicsObject{
    private Color shipPaint;
    public int dx;
    private int dy;
    public int aShipWidth;
    boolean isVisible;

    public alienShip(double x, double y, int dx, int dy) {
        super(x, y);
        this.shipPaint = new Color(250, 0 ,0);
        this.speed_x = 0.75;
        this.speed_y = 10;
        this.dx = dx;
        this.dy = dy;
        this.isVisible = true;
    }


    /* Draw the Player's ship
     *
     * @param g The Graphics for the JPanel
     */
    @Override
    public void draw(Graphics g) {
        // Ship color
        g.setColor(shipPaint);

        // Draw the alien ship
        int [] xc1 = {(int) x, (int) x + (dx / 2), (int) x + dx, (int) x + dx, (int) x + (2 * dx / 3), (int) x + (2 * dx / 3), (int) x + (5 * dx / 9), (int) x + (5 * dx / 9), (int) x + (4 * dx / 9), (int) x + (4 * dx / 9), (int) x + (dx / 3), (int) x + (dx / 3), (int) x};
        int [] yc1 = {(int) y, (int) y - dy, (int) y, (int) y + ( 3 * dy / 4), (int) y + (3 * dy / 4), (int) y + dy, (int) y + dy, (int) y + (3 * dy / 4), (int) y + (3 * dy / 4), (int) y + dy, (int) y + dy, (int) y + (3 * dy / 4), (int) y + (3 * dy / 4)};
        g.fillPolygon(xc1, yc1, 13);
    }

}

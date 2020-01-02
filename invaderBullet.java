import java.awt.Color;
import java.awt.Graphics;

public class invaderBullet extends GraphicsObject {
    private Color bulletColor;
    private int width;
    //private static String bulletVisible;

    public invaderBullet(double x, double y) {
        super(x, y);
        this.bulletColor = Color.RED;
        this.speed_y = -10;
        this.width = 2;
        //bulletVisible = "N";
    }

    /*public static String getBulletVisible() {
        return bulletVisible;
    }*/

    @Override
    public void draw(Graphics g) {
        // bullet color
        g.setColor(this.bulletColor);

        // draw the bullet
        g.fillRect((int) this.x, (int) this.y, width, 10);
    }
}
import java.awt.image.BufferedImage;

public class Obstacle {
    // the record the position
    private int x;
    private int y;

    // current type of obstacle
    private int type;

    // to show current obstacle
    private BufferedImage show = null;
    // current bg-img
    private BackGround bg = null;

    public Obstacle(int x, int y, int type, BackGround bg) {
        this.x = x;
        this.y = y;
        this.type = type;
        this.bg = bg;
        show = StaticValue.obstacles.get(type);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getType() {
        return type;
    }

    public BufferedImage getShow() {
        return show;
    }
}

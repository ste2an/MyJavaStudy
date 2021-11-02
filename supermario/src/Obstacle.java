import java.awt.image.BufferedImage;

public class Obstacle implements Runnable {
    // the record the position
    private int x;
    private int y;

    // current type of obstacle
    private int type;

    // to show current obstacle
    private BufferedImage show = null;
    // current bg-img
    private BackGround bg = null;

    // 实现旗子下落的多线程操作
    private Thread thread = new Thread(this);

    public Obstacle(int x, int y, int type, BackGround bg) {
        this.x = x;
        this.y = y;
        this.type = type;
        this.bg = bg;
        show = StaticValue.obstacles.get(type);
        if(type == 8){
            thread.start();
        }
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

    @Override
    public void run() {
        while(true){
            // 先判断mario是否到达旗子
            if(this.bg.isReach()){
                // 判断旗子是否落到地上
                if(this.y < 374){
                    // 如果旗子的坐标还没有到地面，则让其下落
                    this.y += 5;
                }else{
                    this.bg.setBase(true);
                }

            }
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

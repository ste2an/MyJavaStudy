import java.awt.image.BufferedImage;

public class Enemy implements Runnable {
    // coordinate
    private int x, y;
    // the type of enemy : mushroom , flower ...
    private int type;
    // 1 -- mushroom
    // 2 -- flower
    private boolean face_to = true;
    // true -- move left/ up
    // false -- move right/ down

    //目前出现是哪个图像
    private BufferedImage show;

    // 背景类用于切换场景
    private BackGround bg;

    //控制flower的上下运动
    private int max_up = 0;
    private int max_down = 0;

    //thread
    private Thread thread = new Thread(this);

    // toggle the pics of mushroom to mimic to moving
    private int image_type = 0;

    //constructor for the mushroom
    public Enemy(int x, int y, int type, boolean face_to, BackGround bg) {
        this.x = x;
        this.y = y;
        this.type = type;
        this.face_to = face_to;
        this.bg = bg;
        show = StaticValue.mushrooms.get(0);
        thread.start();
    }


    // constructor for the flower
    public Enemy(int x, int y, int type, boolean face_to, BackGround bg, int max_up, int max_down) {
        this.x = x;
        this.y = y;
        this.type = type;
        this.face_to = face_to;
        this.bg = bg;
        this.max_up = max_up;
        this.max_down = max_down;
        show = StaticValue.flower.get(0);
        thread.start();
    }

    //dead method of enemy
    public void death() {
        show = StaticValue.mushrooms.get(2);
        // 如果敌人死亡，把敌人从当前bg中移除
        this.bg.getEnemies().remove(this);
    }

    @Override
    public void run() {
        while (true) {
            // whether the enemy is mushroom
            if (type == 1) {
                if (face_to == true) {
                    // move left
                    this.x -= 2;
                } else {
                    this.x += 2;
                }
                image_type = image_type == 1 ? 0 : 1;
                // 切换图片索引
                show = StaticValue.mushrooms.get(image_type);
            }
            // whether the enemy could move left or right
            boolean canLeft = true;
            boolean canRight = true;

            // collision detect
            for (int i = 0; i < bg.getObstaclesList().size(); i++) {
                Obstacle ob1 = bg.getObstaclesList().get(i);

                // 判断是否可以向右
                if (ob1.getX() == this.x + 36 && (ob1.getY() + 65 > this.y && ob1.getY() - 35 < this.y)) {
                    canRight = false;
                }

                if (ob1.getX() == this.x - 36 && (ob1.getY() + 65 > this.y && ob1.getY() - 35 < this.y)) {
                    canLeft = false;
                }
            }// end of collision

            // 是否向左走并且碰到了左侧障碍 || 走到了屏幕最左边
            if (face_to && !canLeft || this.x == 0) {
                face_to = false;
            }
            if ((!face_to && !canRight) || this.x == 764) {
                face_to = true;
            }

            // whether the enemy is flower
            if (type == 2) {
                if (face_to == true) {
                    // move upwards
                    this.y -= 2;
                } else {
                    this.y += 2;
                }
                image_type = image_type == 1 ? 0 : 1;
                // 切换图片索引

                // 判断食人花是否达到极限位置，到达上极限则向下
                if (face_to && (this.y == max_up)) {
                    face_to = false;
                }

                if (!face_to && (this.y == max_down)) {
                    face_to = true;
                }

                show = StaticValue.flower.get(image_type);
            }

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }// end of while

    }// end of run


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public BufferedImage getShow() {
        return show;
    }

    public int getType() {
        return type;
    }

}// end of the class

import java.awt.image.BufferedImage;

public class Mario implements Runnable {

    // the position of mairo
    private int x;
    private int y;

    // 表示mario当前的状态
    private String status;
    // 当前状态下对应的图片
    private BufferedImage show = null;

    // 获取障碍物的信息
    private BackGround backGround = new BackGround();

    private Thread thread = null;

    // speed of mario (move and jump)
    private int xSpeed;
    private int ySpeed;

    // 获得mario的运动图像(toggle between 2 pics)
    private int index;

    // 上升时间
    private int upTime = 0;

    // 判断mario是否走到城堡门口
    private boolean reachCastle;

    //判断mario是否死亡
    private boolean isDeath = false;

    public Mario() {
    }

    public Mario(int x, int y) {
        this.x = x;
        this.y = y;
        show = StaticValue.standRight;
        // the initial position of mario

        this.status = "stand--right";
        thread = new Thread(this);
        // 传入了mario类，mario类继承了 runnable类，是一个自定义线程对象，在新建mario类的时候会实现线程

        thread.start();
    }

    // mario runs towards left
    public void leftMove() {
        //改变速度
        xSpeed = -5;

        // 判断mario是否碰到旗子，是的话mario无法移动
        if (backGround.isReach()) {
            xSpeed = 0;
        }

        // to check whether mario is in the air (jumping)
        if (status.indexOf("jump") != -1) {
            status = "jump--left";
        } else {
            status = "move--left";
        }
    }

    // mario runs towards right
    public void rightMove() {
        //改变速度
        xSpeed = 5;

        // 判断mario是否碰到旗子，是的话mario无法移动
        if (backGround.isReach()) {
            xSpeed = 0;
        }

        // to check whether mario is in the air (jumping)
        if (status.indexOf("jump") != -1) {
            status = "jump--right";
        } else {
            status = "move--right";
        }
    }

    public void leftStop() {
        xSpeed = 0;
        if (status.indexOf("jump") != -1) {
            status = "jump--left";
        } else {
            status = "stop--left";
        }
    }

    public void rightStop() {
        xSpeed = 0;
        if (status.indexOf("jump") != -1) {
            status = "jump--right";
        } else {
            status = "stop--right";
        }
    }

    public void jump() {
        // 判断是否是跳跃状态
        if (status.indexOf("jump") == -1) {
            // 判断方向
            if (status.indexOf("left") != -1) {
                status = "jump--left";
            } else {
                status = "jump--right";
            }
            ySpeed = -10;
            upTime = 7;
        }

        // 判断mario是否碰到旗子，是的话mario无法移动
        if (backGround.isReach()) {
            ySpeed = 0;
        }
    }

    public void down() {
        // 判断是否是跳跃状态
        if (status.indexOf("left") != -1) {
            status = "jump--left";
        } else {
            status = "jump--right";
        }
        ySpeed = 10;
    }

    public void death(){
        isDeath = true;
    }

    @Override
    public void run() {
        while (true) {

            // 判断是否在障碍物上
            boolean onObstacle = false;
            // 是否可以向右走
            boolean canRight = true;
            // 是否可以往左走
            boolean canLeft = true;

            // 判断是否走到了最后一个场景
            if (backGround.isFlagpole() && this.x >= 500) {
                this.backGround.setReach(true);

                // 判断旗子是否下落完成
                if (this.backGround.isBase()) {
                    status = "move--right";
                    if (x < 690) {
                        // mario 还没有移动到城堡
                        x += 5;
                    } else {
                        // mario 已经移动到了城堡
                        this.reachCastle = true;
                    }
                } else {
                    if (y < 395) {
                        // mario 还在空中，需要下落
                        xSpeed = 0;
                        this.y += 5;
                        status = "jump--right";
                    }

                    if (y > 395) {
                        this.y = 395;
                        status = "stop--right";
                    }
                }

            } else {
                //遍历障碍物
                for (int i = 0; i < backGround.getObstaclesList().size(); i++) {
                    Obstacle ob = backGround.getObstaclesList().get(i);

                    //whether standing on the brick
                    if (ob.getY() == this.y + 25 && (ob.getX() > this.x - 30 && ob.getX() < this.x + 25)) {
                        onObstacle = true;
                    }

                    // touch the brick
                    if ((ob.getY() >= this.y - 30 && ob.getY() <= this.y - 20) && (ob.getX() > this.x - 30 && ob.getX() < this.x + 25)) {
                        //判断顶到的砖块类型
                        if (ob.getType() == 0) {
                            backGround.getObstaclesList().remove(ob);
                        }
                        // once mario break a brick, immediately fall
                        upTime = 0;
                    }

                    //右侧是否有障碍物
                    if (ob.getX() == this.x + 25 && (ob.getY() > this.y - 30 && ob.getY() < this.y + 25)) {
                        canRight = false;
                    }
                    //左侧是否有障碍物
                    if (ob.getX() == this.x - 30 && (ob.getY() > this.y - 30 && ob.getY() < this.y + 25)) {
                        canLeft = false;
                    }
                } // end of collision verify

                // whether mario kills or mario dies
                for(int i = 0; i < backGround.getEnemies().size(); i ++){
                    Enemy e = backGround.getEnemies().get(i);

                    // 判断mario是否在敌人正上方
                    if(e.getY() == this.y + 20 && (e.getX() - 25 <= this.x && e.getX() + 35 >= this.x)){
                        if(e.getType() == 1){
                            // step on the mushroom
                            e.death();
                            upTime = 3;
                            ySpeed = -10;
                        }else if(e.getType() == 2){
                            // mario dies
                            death();

                        }
                    }

                    if((e.getX() + 35 > this.x && e.getX() - 25 < this.x) &&
                            (e.getY() + 35 > this.y && e.getY() - 20 < this.y)){
                        // mario dies
                        death();
                    }
                }

                // mario jump
                if (onObstacle && upTime == 0) {
                    if (status.indexOf("left") != -1) {
                        if (xSpeed != 0) {
                            status = "move--left";
                        } else {
                            status = "stop--left";
                        }
                    } else {
                        if (xSpeed != 0) {
                            status = "move--right";
                        } else {
                            status = "stop--right";
                        }
                    }
                } else {
                    //处于上升状态
                    if (upTime != 0) {
                        upTime--;
                        // 还没有上升到最高点
                    } else {
                        // up to the climax, then mario should be falling down
                        down();
                    }
                    y += ySpeed;
                }
            }

            if ((canLeft && xSpeed < 0) || (canRight && xSpeed > 0)) {
                x += xSpeed;
                // whether mario is at the left boundary of the screen
                if (x < 0) {
                    x = 0;
                }
            }

            //判断是否是移动状态
            if (status.contains("move")) {
                index = index == 0 ? 1 : 0;
            }

            // 向左移动，两张图片对应两个索引，0，1来回切换
            if ("move--left".equals(status)) {
                show = StaticValue.runLeft.get(index);
            }

            //向右移动，同理
            if ("move--right".equals(status)) {
                show = StaticValue.runRight.get(index);
            }

            // 向左停止
            if ("stop--left".equals(status)) {
                show = StaticValue.standLeft;
            }

            if ("stop--right".equals(status)) {
                show = StaticValue.standRight;
            }

            //向左跳跃
            if ("jump--left".equals(status)) {
                show = StaticValue.jumpLeft;
            }

            //
            if ("jump--right".equals(status)) {
                show = StaticValue.jumpRight;
            }

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }// end of while
    }// end of the run method

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public BufferedImage getShow() {
        return show;
    }

    public void setShow(BufferedImage show) {
        this.show = show;
    }

    public void setBackGround(BackGround backGround) {
        this.backGround = backGround;
    }

    public boolean isReachCastle() {
        return reachCastle;
    }

    public boolean isDeath() {
        return isDeath;
    }
}// end of the class

import java.awt.image.BufferedImage;

public class Mario implements Runnable {

    // the position of mairo
    private  int x;
    private  int y;

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

    public Mario() {
    }

    public Mario(int x, int y) {
        this.x = x;
        this.y = y;
        show = StaticValue.standRight;
        // the initial position of mario

        this.status = "stand--right";
        thread = new Thread(this);
        thread.start();
    }

    // mario runs towards left
    public void leftMove(){
        //改变速度
        xSpeed = -5;

        // to check whether mario is in the air (jumping)
        if(status.indexOf("jump") != -1){
            status = "jump--left";
        }else{
            status = "move--left";
        }
    }

    // mario runs towards right
    public void rightMove(){
        //改变速度
        xSpeed = 5;

        // to check whether mario is in the air (jumping)
        if(status.indexOf("jump") != -1){
            status = "jump--right";
        }else{
            status = "move--right";
        }
    }

    public void leftStop(){
        xSpeed = 0;
        if(status.indexOf("jump") != -1){
            status = "jump--left";
        }else{
            status = "stop--left";
        }
    }

    public void rightStop(){
        xSpeed = 0;
        if(status.indexOf("jump") != -1){
            status = "jump--right";
        }else{
            status = "stop--right";
        }
    }

    @Override
    public void run() {
        while (true){
            if(xSpeed != 0){
                x += xSpeed;
                // whether mario is at the left boundary of the screen
                if(x < 0){
                    x = 0;
                }
            }

            if(status.contains("move")){
                index = index == 0 ? 1 : 0;
            }

            // 向左移动，两张图片对应两个索引，0，1来回切换
            if("move--left".equals(status)){
                show = StaticValue.runLeft.get(index);
            }

            //向右移动，同理
            if("move--right".equals(status)){
                show = StaticValue.runRight.get(index);
            }

            // 向左停止
            if("stop--left".equals(status)){
                show = StaticValue.standLeft;
            }

            if("stop--right".equals(status)){
                show = StaticValue.standRight;
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
}// end of the class

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class MyFrame extends JFrame implements KeyListener, Runnable{

    // store all the bg-img of the game
    private List<BackGround> allBg = new ArrayList<>();

    // current bg-img
    private BackGround curBg = new BackGround();

    //？设置双缓存
    private Image offScreenImage = null;

    private Mario mario = new Mario();

    //
    private Thread thread = new Thread(this);

    public MyFrame(){
        //set the size of window
        this.setSize(800,600);

        //center the window
        this.setLocationRelativeTo(null);

        //visible
        this.setVisible(true);

        // close window by clicking X
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //fix the size
        this.setResizable(false);

        //get event from keyboard
        this.addKeyListener(this);
        // this step requires implementing KeyListener interface (override abstract methods)

        this.setTitle("My own Super Mario");
        // set the name of the window

        // load all the images we need
        StaticValue.init();

        mario = new Mario(10, 355);

        //build bg-img of all sections
        for (int i = 1; i <= 3; i ++){
            allBg.add(new BackGround(i, i == 3));

        }
        // 空参构造初始化游戏，第一个因此背景设置为第一个场景
        curBg = allBg.get(0);

        mario.setBackGround(curBg);
        //绘制图像
        repaint();

        thread.start();
    }// end of the constructor

    @Override
    public void paint(Graphics g) {
        if(offScreenImage == null){
            offScreenImage = createImage(800,600);
        }
        Graphics graphics = offScreenImage.getGraphics();
        graphics.fillRect(0,0,800,600);

        // 绘制背景
        graphics.drawImage(curBg.getBgImg(), 0,0,this);

        //绘制敌人
        for(Enemy e: curBg.getEnemies()){
            graphics.drawImage(e.getShow(),e.getX(),e.getY(),this);
        }
        // 注意绘制顺序，先绘制的东西会被后覆盖的遮住

        // 绘制障碍物
        for(Obstacle ob: curBg.getObstaclesList()){
            graphics.drawImage(ob.getShow(), ob.getX(), ob.getY(), this);

        }

        //绘制城堡
        graphics.drawImage(curBg.getCastle(), 620, 270, this);

        //绘制旗杆
        graphics.drawImage(curBg.getFlagpole(), 500, 220, this);

        //绘制 mario
        graphics.drawImage(mario.getShow(), mario.getX(), mario.getY(), this);

        // 将图像绘制到背景中
        g.drawImage(offScreenImage, 0, 0,this);

    }// end of the paint method

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        // move towards right, 39 refers 键盘上的 向右 按钮
        if(e.getKeyCode() == 39){
            mario.rightMove();
        }

        //向左移动
        if(e.getKeyCode() == 37){
            mario.leftMove();
        }

        if(e.getKeyCode() == 38){
            mario.jump();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // 松开键盘的时候
        // 如果松开的键盘代码是 37
        if(e.getKeyCode() == 37){
            mario.leftStop();
        }

        // 如果松开的键盘代码是 39
        if(e.getKeyCode() == 39){
            mario.rightStop();
        }

    }

    public static void main(String[] args) {

        MyFrame myFrame = new MyFrame();

    }

    @Override
    public void run() {
        while(true){
            repaint();
            try {
                Thread.sleep(50);

                //切换场景
                if(mario.getX() >= 775){
                    curBg = allBg.get(curBg.getSort());
                    mario.setBackGround(curBg);
                    mario.setX(10);
                    mario.setY(355);
                }

                //判断mario是否死亡
                if(mario.isDeath() == true){
                    JOptionPane.showMessageDialog(this, "mario dies!");
                    System.exit(0);
                }
                if(mario.isReachCastle()){
                    // 游戏结束
                    JOptionPane.showMessageDialog(this, "congrats");
                    System.exit(0);
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }// end of while
    }// end of run method

}// end of the class

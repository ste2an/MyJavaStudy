import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class MyFrame extends JFrame implements KeyListener {

    // store all the bg-img of the game
    private List<BackGround> allBg = new ArrayList<>();

    // current bg-img
    private BackGround curBg = new BackGround();

    //？设置双缓存
    private Image offScreenImage = null;

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

        //build bg-img of all sections
        for (int i = 1; i <= 3; i ++){
            allBg.add(new BackGround(i, i == 3));

        }
        // 空参构造初始化游戏，第一个因此背景设置为第一个场景
        curBg = allBg.get(2);

        //绘制图像
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        if(offScreenImage == null){
            offScreenImage = createImage(800,600);
        }
        Graphics graphics = offScreenImage.getGraphics();
        graphics.fillRect(0,0,800,600);

        // 绘制背景
        graphics.drawImage(curBg.getBgImg(), 0,0,this);

        // 将图像绘制到背景中
        g.drawImage(offScreenImage, 0, 0,this);
    }

    public static void main(String[] args) {

        MyFrame myFrame = new MyFrame();

    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}

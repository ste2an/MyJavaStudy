import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MyFrame extends JFrame implements KeyListener {

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

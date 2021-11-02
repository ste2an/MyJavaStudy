import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StaticValue {
    // backGround
    // for different sections
    public static BufferedImage bg = null;
    public static BufferedImage bg2 = null;

    //left jump
    public static BufferedImage jumpLeft = null;

    //right jump
    public static BufferedImage jumpRight = null;

    // face left
    public static BufferedImage standLeft = null;

    // face right
    public static BufferedImage standRight = null;

    //castle
    public static BufferedImage castle = null;

    //flagpole
    public static BufferedImage flagpole = null;


    // create a list of BufferedImage for obstacles
    // if the fields composed by a set of pics, it should be presented in a collection
    public static List<BufferedImage> obstacles = new ArrayList<>();

    // run towards left and run towards right
    public static List<BufferedImage> runLeft  = new ArrayList<>();
    public static List<BufferedImage> runRight = new ArrayList<>();

    //mushroom enemy
    public static List<BufferedImage> mushrooms = new ArrayList<>();

    //flower enemy
    public static List<BufferedImage> flower = new ArrayList<>();

    //for later to get the path of images,
    public static String path = System.getProperty("user.dir") + "/supermario/src/imgs/" ;
    // "user.dir" - to get User working directory, here this code will get
    // '/Users/wangzf/Desktop/personal project/super mario'

    public static void init(){
        // same function as a constructor, to load the images

        try {
            // ImageIO.read() needs to be surrounded with try/catch or throws IOException at the method statement
            bg = ImageIO.read(new File(path + "bg.png"));
            bg2 = ImageIO.read(new File(path+"bg2.png"));

            standLeft = ImageIO.read(new File(path + "s_mario_stand_L.png"));
            standRight = ImageIO.read(new File(path + "s_mario_stand_R.png"));

            jumpLeft = ImageIO.read(new File(path + "s_mario_jump1_L.png"));
            jumpRight = ImageIO.read(new File(path + "s_mario_jump1_R.png"));

            castle = ImageIO.read(new File(path + "castle.png"));
            flagpole = ImageIO.read(new File(path + "flagpole.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }

        // how to add images into a list
        for(int i = 1; i <= 2; i ++){
            try {
                runLeft.add(ImageIO.read(new File(path + "s_mario_run"+ i + "_L.png")));
                runRight.add(ImageIO.read(new File(path + "s_mario_run"+ i + "_R.png")));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // load the brick
        try {
            obstacles.add(ImageIO.read(new File(path + "brick.png")));
            obstacles.add(ImageIO.read(new File(path + "soil_up.png")));
            obstacles.add(ImageIO.read(new File(path + "soil_base.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //load the piles(also a kind of obstacle)

        for(int i = 1; i <= 4; i++){
            try {
                obstacles.add(ImageIO.read(new File(path + "pipe"+ i +".png")));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            obstacles.add(ImageIO.read(new File(path + "brick2.png")));
            obstacles.add(ImageIO.read(new File(path + "flag.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }

        for(int i = 1; i <= 3; i ++){
            try {
                mushrooms.add(ImageIO.read(new File(path + "fungus"+ i +".png")));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        for(int i = 1; i <= 2; i ++){
            try {
                flower.add(ImageIO.read(new File(path + "flower1."+ i +".png")));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}

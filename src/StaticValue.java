import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

public class StaticValue {
    // backGround
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
    public static List<BufferedImage> obstacles = new ArrayList<>();

    // run towards left and run towards right
    public static List<BufferedImage> runLeft  = new ArrayList<>();
    public static List<BufferedImage> runRight = new ArrayList<>();




}

import java.awt.image.BufferedImage;

public class BackGround {

    // current bg
    private BufferedImage bgImg = null;

    //record which section are we in now
    private int sort;

    // whether this section is the last one
    private boolean flagpole;

    public BackGround() {
    }

    public BackGround(int sort, boolean flagpole) {
        this.sort = sort;
        this.flagpole = flagpole;

        if(flagpole){
            bgImg = StaticValue.bg2;
        }else{
            bgImg = StaticValue.bg;
        }
    }

    public BufferedImage getBgImg() {
        return bgImg;
    }

    public int getSort() {
        return sort;
    }

    public boolean isFlagpole() {
        return flagpole;
    }
}

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class BackGround {

    // current bg
    private BufferedImage bgImg = null;

    //record which section are we in now; 1 -- 1st section, 2 -- 2nd section, 3 -- last section
    private int sort;

    // whether this section is the last one
    private boolean flag;

    // to set obstacles in current background
    private List<Obstacle> obstaclesList = new ArrayList<>();

    private List<Enemy> enemies = new ArrayList<>();

    // mario是否到达旗杆
    private boolean isReach = false;
    // 判断旗子是否落地
    private boolean isBase = false;

    private BufferedImage flagpole;
    private BufferedImage castle;

    public BackGround() {
    }

    public BackGround(int sort, boolean flag) {
        this.sort = sort;
        this.flag = flag;

        if(flag){
            bgImg = StaticValue.bg2;
        }else{
            bgImg = StaticValue.bg;
        }

        if(sort == 1){
        //如果是第一关的话
            // 绘制第一层地面(表面带草地的那种），图片长度30，窗口800，30 * 27 = 810
            for(int i = 0; i < 27; i ++){
                obstaclesList.add(new Obstacle(i * 30, 420,1,this));
            }

            //绘制地下部分
            for(int j = 0; j <= 120;j += 30){
                // row
                for(int i = 0; i < 27; i ++){
                    // col
                    obstaclesList.add(new Obstacle(i * 30, 570 - j, 2, this));
                }
            }

            //绘制砖块
            for(int i = 120; i <= 150; i += 30){
                obstaclesList.add(new Obstacle(i, 300, 7, this));
            }

            for(int i = 300; i <= 570; i += 30){
                if(i == 360 || i == 390 || i == 480 || i == 510 || i == 540){
                    obstaclesList.add(new Obstacle(i ,300, 7,this));
                }else{
                    obstaclesList.add(new Obstacle(i ,300, 0 , this));
                }
            }

            for(int i = 420; i <= 450; i += 30){
                obstaclesList.add(new Obstacle(i, 240, 7, this));
            }

            //绘制水管
            for(int i = 360; i <= 600; i += 25) {
                if (i == 360) {
                    obstaclesList.add(new Obstacle(620, i, 3, this));
                    obstaclesList.add(new Obstacle(645, i, 4, this));
                } else {
                    obstaclesList.add(new Obstacle(620, i, 5, this));
                    obstaclesList.add(new Obstacle(645, i, 6, this));
                }
            }

            // enemy in section1
            enemies.add(new Enemy(580, 385, 1, true, this));
            enemies.add(new Enemy(635, 420, 2,true,this,328,428));

        }// end of the preparing of section 1

        if(sort == 2){
            // 绘制第一层地面(表面带草地的那种），图片长度30，窗口800，30 * 27 = 810
            for(int i = 0; i < 27; i ++){
                obstaclesList.add(new Obstacle(i * 30, 420,1,this));
            }

            //绘制地下部分
            for(int j = 0; j <= 120;j += 30){
                // row
                for(int i = 0; i < 27; i ++){
                    // col
                    obstaclesList.add(new Obstacle(i * 30, 570 - j, 2, this));
                }
            }

            //绘制水管
            for(int i = 360; i <= 600; i += 25) {
                if (i == 360) {
                    obstaclesList.add(new Obstacle(60, i, 3, this));
                    obstaclesList.add(new Obstacle(85, i, 4, this));
                } else {
                    obstaclesList.add(new Obstacle(60, i, 5, this));
                    obstaclesList.add(new Obstacle(85, i, 6, this));
                }
            }

            // 绘制第二根水管
            for(int i = 330; i <= 600; i += 25) {
                if (i == 330) {
                    obstaclesList.add(new Obstacle(620, i, 3, this));
                    obstaclesList.add(new Obstacle(645, i, 4, this));
                } else {
                    obstaclesList.add(new Obstacle(620, i, 5, this));
                    obstaclesList.add(new Obstacle(645, i, 6, this));
                }
            }

            // brisks，三角形砖块
            obstaclesList.add(new Obstacle(300, 330, 0, this));

            for(int i = 270; i <= 330; i += 30){
                if(i == 270 || i == 330){
                    obstaclesList.add(new Obstacle(i, 360, 0, this));
                }else{
                    obstaclesList.add(new Obstacle(i, 360, 7, this));
                }
            }

            // 三角形最底下一层砖块
            for(int i = 240; i <= 360; i += 30){
                if(i == 240 || i == 360){
                    obstaclesList.add(new Obstacle(i, 390, 0, this));
                }else{
                    obstaclesList.add(new Obstacle(i, 390, 7, this));
                }
            }

            obstaclesList.add(new Obstacle(240, 300, 0, this));

            for(int i = 360; i <= 540; i += 60){
                obstaclesList.add(new Obstacle(i, 270, 7, this));
            }

            // enemy in section2
            enemies.add(new Enemy(75, 420, 2,true,this,328,428));
            enemies.add(new Enemy(635, 420, 2,true,this,328,428));

            enemies.add(new Enemy(250, 385, 1, true, this));
            enemies.add(new Enemy(500, 385, 1, true, this));

        }// end of the preparing of section 2

        if(sort == 3){
            // 绘制第一层地面(表面带草地的那种），图片长度30，窗口800，30 * 27 = 810
            for(int i = 0; i < 27; i ++){
                obstaclesList.add(new Obstacle(i * 30, 420,1,this));
            }

            //绘制地下部分
            for(int j = 0; j <= 120;j += 30){
                // row
                for(int i = 0; i < 27; i ++){
                    // col
                    obstaclesList.add(new Obstacle(i * 30, 570 - j, 2, this));
                }
            }

            //brick 绘制三角形阶梯
            int temp = 290;
            for(int i = 390; i >= 270; i -= 30){
                for(int j = temp; j <= 410; j += 30){
                    obstaclesList.add(new Obstacle(j, i , 7, this));
                }
                temp += 30;
            }

            temp = 60;
            for(int i = 390; i >= 360; i -= 30){
                for(int j = temp;j <= 90; j += 30){
                    obstaclesList.add(new Obstacle(j,i,7,this));
                }
                temp += 30;
            }

            flagpole = StaticValue.flagpole;
            castle = StaticValue.castle;

            obstaclesList.add(new Obstacle(515, 220, 8, this));

            //enemy
            enemies.add(new Enemy(150, 385, 1, true, this));
            
        }// end of the preparing of section 3

    }// end of the constructor

    public BufferedImage getBgImg() {
        return bgImg;
    }

    public int getSort() {
        return sort;
    }

    public boolean isFlagpole() {
        return flag;
    }

    public List<Obstacle> getObstaclesList() {
        return obstaclesList;
    }

    public BufferedImage getFlagpole() {
        return flagpole;
    }

    public BufferedImage getCastle() {
        return castle;
    }

    public boolean isReach() {
        return isReach;
    }

    public void setReach(boolean reach) {
        isReach = reach;
    }

    public boolean isBase() {
        return isBase;
    }

    public void setBase(boolean base) {
        isBase = base;
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }
}

package com.group.supermario;

import java.awt.*;

public class Bricks {
    int type;//障碍物的类型
    private int x, y;
    private int startType = 1;
    private Image showImage = null;

    public Bricks(int x, int y, int type) {
        this.x = x;
        this.y = y;
        this.type = type;
        this.startType = type;
        this.setImage();
    }

    public void setType(int type) {
        this.type = type;
    }

    public void reset() {//重置障碍物
        this.type = startType;
        this.setImage();
    }

    public Image getShowImage() {
        return showImage;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setImage() {
        showImage = Sources.allObstructionImage[type];
    }
}

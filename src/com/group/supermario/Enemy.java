package com.group.supermario;

import java.applet.AudioClip;
import java.awt.*;

public class Enemy implements Runnable {
    private int x, y;
    private int initX, initY;
    private Image showImage;
    private int type;
    private boolean isMoveorUp = true;
    private Thread t = new Thread(this);
    private int upMax = 0;
    private int downMax = 0;
    private int imageType = 1;
    private int moving = 0;
    private Background bg;


    public Enemy(int x, int y, int type, boolean isMove, Background bg) {
        //创建普通敌人
        this.x = x;
        this.y = y;
        this.type = type;
        this.isMoveorUp = isMove;
        this.bg = bg;
        this.initX = x;
        this.initY = y;
        if (type == 1) {
            showImage = Sources.allMonsterImage[1];
        }
        t.start();

    }

    public Enemy(int x, int y, int type, boolean isUp, int upMax, int downMax, Background bg) {
        //创建水仙花
        this.x = x;
        this.y = y;
        this.type = type;
        this.isMoveorUp = isUp;
        this.upMax = upMax;
        this.downMax = downMax;
        this.bg = bg;
        this.initX = x;
        this.initY = y;
        if (type == 2) {
            showImage = Sources.allFlowerImage[0];
        }
        t.start();


    }

    public void dead() {
        try {
            AudioClip tm = Sources.bg4;
            tm.play();
            this.showImage = Sources.monsterDeadImage;
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.bg.getAllEnemy().remove(this);
        this.bg.getRemovedEnemy().remove(this);

    }

    public void reset() {
        //将坐标重置
        this.x = this.initX;
        this.y = this.initY;
        if (this.type == 1) {
            this.showImage = Sources.allMonsterImage[0];
        }
        if (this.type == 2) {
            this.showImage = Sources.allFlowerImage[0];
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Image getShowImage() {
        return showImage;
    }

    public int getType() {
        return type;
    }

    @Override
    public void run() {
        while (true) {
            if (type == 1) {
                int temp = 0;
                if (isMoveorUp) {
                    this.x -= 2;
                    temp += this.moving;
                    moving++;
                    if (this.moving == 5) {
                        moving = 0;
                    }
                } else {
                    this.x += 2;
                    temp += this.moving + 5;
                    moving++;
                    if (this.moving == 5) {
                        moving = 0;
                    }
                }

                boolean canLeft = true;
                boolean canRight = true;

                for (int i = 0; i < this.bg.getAllBricks().size(); i++) {
                    Bricks ob = this.bg.getAllBricks().get(i);
                    if (ob.getX() == this.x + 60 && (ob.getY() + 50 > this.y && ob.getY() - 50 < this.y)) {
                        canRight = false;
                    }
                    if (ob.getX() == this.x - 110 && (ob.getY() + 50 > this.y && ob.getY() - 50 < this.y)) {
                        canLeft = false;
                    }
                }

                if (isMoveorUp && !canLeft || this.x == 0) {
                    this.isMoveorUp = false;

                } else if (!isMoveorUp && !canRight) {
                    this.isMoveorUp = true;
                }


                this.showImage = Sources.allMonsterImage[temp];
            }
            if (type == 2) {
                if (isMoveorUp) {
                    this.y -= 2;
                } else {
                    this.y += 2;
                }
                if (imageType == 0) {
                    imageType = 1;
                } else {
                    imageType = 0;
                }
                if (this.isMoveorUp && this.y == this.upMax) {
                    this.isMoveorUp = false;
                }
                if (!this.isMoveorUp && this.y == this.downMax) {
                    this.isMoveorUp = true;
                }
                this.showImage = Sources.allFlowerImage[imageType];

            }
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }
}


package com.group.supermario;

import java.applet.AudioClip;
import java.awt.*;

public class Mario implements Runnable {
    private int x;
    private int y;
    private Background bg;
    private String status;//状态
    private Image showImage;
    private int score;
    private int life;
    private int speed;
    private Thread t;
    private int moving = 0;
    private int ymove;
    private int upTime;
    private boolean isDead = false;
    private boolean isTouchWhy = false;//是否碰到问号


    public Mario(int x, int y) {
        this.x = x;
        this.y = y;
        this.showImage = Sources.allMarioImage[0];
        this.score = 50;
        this.life = 3;
        this.status = "rightStanding";
        t = new Thread(this);
        t.start();
    }

    public void setBg(Background bg) {
        this.bg = bg;
    }

    public Image getShowImage() {
        return showImage;
    }

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

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public String getStatus() {
        return status;
    }

    public boolean isTouchWhy() {
        return isTouchWhy;
    }

    public void setTouchWhy(boolean touchWhy) {
        isTouchWhy = touchWhy;
    }

    public void leftMove() {//向左运动
        this.speed = -5;
        if (this.status.indexOf("Jumping") != -1) {
            this.status = "leftJumping";
        } else {
            this.status = "leftMoving";
        }


    }

    public void rightMove() {//向右运动
        this.speed = 5;
        if (this.status.indexOf("Jumping") != -1) {
            this.status = "rightJumping";
        } else {
            this.status = "rightMoving";
        }

    }

    public void leftStop() {//向左运动停止
        this.speed = 0;
        if (this.status.indexOf("Jumping") != -1) {
            this.status = "leftJumping";
        } else {
            this.status = "leftStanding";
        }

    }

    public void rightStop() {//向右运动停止
        this.speed = 0;
        if (this.status.indexOf("Jumping") != -1) {
            this.status = "rightJumping";
        } else {
            this.status = "rightStanding";
        }

    }

    public void jump() {//跳跃
        if (this.status.indexOf("Jumping") == -1) {
            if (this.status.indexOf("left") != -1) {
                this.status = "leftJumping";
            } else {
                this.status = "rightJumping";
            }
        }
        ymove = -5;
        upTime = 16;

    }

    public void down() {//下落
        if (this.status.indexOf("left") != -1) {
            this.status = "leftDowning";
        } else {
            this.status = "rightDowning";
        }
        ymove = 5;

    }

    public void dead() {//马里奥的死亡方法
        this.life--;//生命值减1
        AudioClip dm = Sources.bg3;
        dm.play();
        if (this.life == 0) {
            this.isDead = true;
        } else {
            this.bg.reset();
            this.x = 0;
            this.y = 450;

        }
    }

    public boolean isDead() {
        return isDead;
    }

    @Override
    public void run() {
        while (true) {
            boolean canLeft = true;
            boolean canRight = true;
            boolean onLand = false;
            for (int i = 0; i < this.bg.getAllBricks().size(); i++) {
                //遇到障碍物时不能移动

                Bricks ob = this.bg.getAllBricks().get(i);
                if (ob.getX() == this.x + 60 && (ob.getY() + 50 > this.y && ob.getY() - 50 < this.y)) {
                    canRight = false;
                }
                if (ob.getX() == this.x - 110 && (ob.getY() + 50 > this.y && ob.getY() - 50 < this.y)) {
                    canLeft = false;
                }
                if (ob.getY() == this.y + 60 && (ob.getX() + 60 > this.x && ob.getX() - 60 < this.x)) {
                    onLand = true;

                }
                if (ob.getY() == this.y - 60 && (ob.getX() + 50 > this.x && ob.getX() - 50 < this.x)) {
                    if (ob.type == 1) {//当碰到砖块时
                        AudioClip tm = Sources.bg2;
                        tm.play();
                        this.score += 50;
                        this.bg.getAllBricks().remove(ob);
                        this.bg.getRemovedBricks().add(ob);

                    }
                    if (ob.type == 5) {//当碰到问号时
                        ob.setType(2);
                        ob.setImage();
                        this.isTouchWhy = true;
                        this.score += 100;
                        AudioClip em = Sources.bg1;
                        em.play();
                    }
                    upTime = 0;
                }

            }
            for (int i = 0; i < this.bg.getAllEnemy().size(); i++) {
                //mario碰到敌人
                Enemy e = this.bg.getAllEnemy().get(i);
                if (e.getX() + 50 > this.x && e.getX() - 50 < this.x && (e.getY() + 60 > this.y && e.getY() - 60 < this.y)) {
                    //马里奥死亡
                    this.dead();
                }
                if (e.getY() == this.y + 60 && (e.getX() + 60 > this.x && e.getX() - 60 < this.x)) {
                    if (e.getType() == 1) {
                        e.dead();
                        this.upTime = 10;
                        this.ymove = -5;
                        this.score += 100;
                    } else if (e.getType() == 2) {
                        this.dead();
                    }
                }
            }
            if (onLand && upTime == 0) {
                if (this.status.indexOf("left") != -1) {
                    if (speed != 0) {
                        this.status = "leftMoving";
                    } else {
                        this.status = "leftStanding";
                    }
                } else {
                    if (speed != 0) {
                        this.status = "rightMoving";
                    } else {
                        this.status = "rightStanding";
                    }
                }

            } else {
                if (upTime != 0) {
                    upTime--;
                } else {
                    this.down();
                }
                y += ymove;

            }
            if (this.y > 600) {
                this.dead();
            }
            if ((canLeft && speed < 0) || (canRight && speed > 0)) {
                x += speed;
                if (x < 0) {
                    x = 0;
                }
            }
            int temp = 0;
            if (this.status.indexOf("left") != -1) {
                temp += 22;

            }
            if (this.status.indexOf("Moving") != -1) {//移动中更替图片
                temp += this.moving;
                moving++;
                if (this.moving == 21) {
                    moving = 0;
                }

            }
            if (this.status.indexOf("rightJumping") != -1) {//跳跃时更换图片
                temp = 43;
            }
            if (this.status.indexOf("leftJumping") != -1) {
                temp = 44;
            }
            this.showImage = Sources.allMarioImage[temp];

            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }
}

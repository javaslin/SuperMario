package com.group.supermario;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Background {
    private Image bgImage;
    private int sort;//场景的顺序
    private boolean flag;//当前场景是否为最后一个场景
    private List<Enemy> allEnemy = new ArrayList();//所有敌人用集合来存储
    private List<Enemy> removedEnemy = new ArrayList();
    private List<Bricks> allBricks = new ArrayList();//所有障碍物用集合来存储
    private List<Bricks> removedBricks = new ArrayList();


    public Background(int sort, boolean flag) {
        this.sort = sort;
        this.flag = flag;
        if (flag == true) {
            bgImage = Sources.overImage;
        } else if (sort == 1) {
            bgImage = Sources.bgImage;
        } else if (sort == 2) {
            bgImage = Sources.midImage;
        } else if (sort == 3) {
            bgImage = Sources.endImage;
        }
        if (sort == 1) {
            for (int i = 0; i < 15; i++) {
                this.allBricks.add(new Bricks(i * 60, 520, 9));

            }
            //砖块和问号
            this.allBricks.add(new Bricks(120, 360, 5));
            this.allBricks.add(new Bricks(300, 360, 1));
            this.allBricks.add(new Bricks(360, 360, 5));
            this.allBricks.add(new Bricks(420, 360, 1));
            this.allBricks.add(new Bricks(480, 360, 5));
            this.allBricks.add(new Bricks(540, 360, 2));
            this.allBricks.add(new Bricks(420, 180, 5));
            this.allBricks.add(new Bricks(300, 180, 5));
            this.allBricks.add(new Bricks(540, 60, 1));
            this.allBricks.add(new Bricks(150, 60, 2));
            //水管
            this.allBricks.add(new Bricks(660, 450, 6));
            this.allBricks.add(new Bricks(726, 450, 7));
            //敌人
            this.allEnemy.add(new Enemy(600, 460, 1, true, this));
            this.allEnemy.add(new Enemy(696, 410, 2, true, 380, 450, this));


        }
        if (sort == 2) {
            for (int i = 0; i < 15; i++) {
                if (i != 10) {
                    this.allBricks.add(new Bricks(i * 60, 520, 9));
                }

            }
            //转块和问号
            this.allBricks.add(new Bricks(540, 360, 1));
            this.allBricks.add(new Bricks(420, 180, 5));
            this.allBricks.add(new Bricks(180, 100, 1));
            this.allBricks.add(new Bricks(300, 140, 2));
            this.allBricks.add(new Bricks(220, 290, 1));
            this.allBricks.add(new Bricks(490, 180, 5));
            //水管
            this.allBricks.add(new Bricks(360, 450, 6));
            this.allBricks.add(new Bricks(426, 450, 7));
            this.allBricks.add(new Bricks(680, 420, 6));
            this.allBricks.add(new Bricks(746, 420, 7));
            //敌人
            this.allEnemy.add(new Enemy(200, 460, 1, true, this));
            this.allEnemy.add(new Enemy(716, 380, 2, true, 350, 420, this));


        }
        if (sort == 3) {
            for (int i = 0; i < 15; i++) {
                if (i != 10 && i != 8 && i != 13) {
                    this.allBricks.add(new Bricks(i * 60, 520, 9));
                }

            }
            //转块和问号
            this.allBricks.add(new Bricks(540, 360, 1));
            this.allBricks.add(new Bricks(420, 180, 5));
            this.allBricks.add(new Bricks(600, 200, 1));
            this.allBricks.add(new Bricks(350, 360, 2));
            this.allBricks.add(new Bricks(140, 260, 1));
            //水管
            this.allBricks.add(new Bricks(360, 450, 6));
            this.allBricks.add(new Bricks(426, 450, 7));
            this.allBricks.add(new Bricks(660, 420, 6));
            this.allBricks.add(new Bricks(726, 420, 7));
            //敌人
            this.allEnemy.add(new Enemy(200, 460, 1, true, this));
            this.allEnemy.add(new Enemy(696, 380, 2, true, 350, 420, this));


        }
    }

    public Image getBgImage() {
        return bgImage;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public List<Bricks> getAllBricks() {
        return allBricks;
    }

    public List<Bricks> getRemovedBricks() {
        return removedBricks;
    }

    public List<Enemy> getAllEnemy() {
        return allEnemy;
    }

    public List<Enemy> getRemovedEnemy() {
        return removedEnemy;
    }

    public void reset() {
        //生命值减1时调用，场景重置
        //将已经移除的障碍物和敌人放回原处
        this.allEnemy.addAll(this.removedEnemy);
        this.allBricks.addAll(this.removedBricks);
        for (int i = 0; i < this.allEnemy.size(); i++) {
            this.allEnemy.get(i).reset();
        }
        for (int i = 0; i < this.allBricks.size(); i++) {
            this.allBricks.get(i).reset();
        }
    }

}

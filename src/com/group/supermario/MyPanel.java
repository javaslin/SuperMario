package com.group.supermario;

import javax.swing.*;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MyPanel extends JPanel implements KeyListener, Runnable {
    private List<Background> allBG = new ArrayList<Background>();
    private Mario mario;
    private boolean isStart = false;
    private Background nowBG;

    public MyPanel() {
        for (int i = 1; i <= 4; i++) {
            this.allBG.add(new Background(i, i == 4 ? true : false));
        }
        this.nowBG = allBG.get(0);
        mario = new Mario(0, 450);
        Thread t = new Thread(this);
        this.mario.setBg(nowBG);
        t.start();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (this.isStart) {
            g.drawImage(this.nowBG.getBgImage(), 0, 0, null);
            if (this.mario.getLife() == 3) {
                g.drawImage(Sources.allLifeImage[2], 30, 0, null);
            } else if (this.mario.getLife() == 2) {
                g.drawImage(Sources.allLifeImage[1], 30, 0, null);
            } else {
                g.drawImage(Sources.allLifeImage[0], 30, 0, null);
            }
            if (this.mario.isTouchWhy() == true) {
                g.drawImage(Sources.moneyImage, this.mario.getX(), this.mario.getY() - 110, null);
                this.mario.setTouchWhy(false);
            }
            Iterator<Enemy> iter2 = this.nowBG.getAllEnemy().iterator();
            while (iter2.hasNext()) {
                Enemy enemy = iter2.next();
                g.drawImage(enemy.getShowImage(), enemy.getX(), enemy.getY(), null);
            }
            Iterator<Bricks> iter = this.nowBG.getAllBricks().iterator();
            while (iter.hasNext()) {
                Bricks ob = iter.next();
                g.drawImage(ob.getShowImage(), ob.getX(), ob.getY(), null);
            }
            g.drawImage(this.mario.getShowImage(), mario.getX(), mario.getY(), null);
            g.setColor(Color.WHITE);
            String s = String.valueOf(this.mario.getScore());
            g.setFont(new Font("微软雅黑", Font.BOLD, 40));
            g.drawImage(Sources.scoreImage, 530, 0, null);
            g.drawString(s, 600, 45);
        } else {
            g.drawImage(Sources.startImage, 0, 0, null);
        }


    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (this.isStart) {
            if (e.getKeyCode() == 39) {
                this.mario.rightMove();
            }
            if (e.getKeyCode() == 37) {
                this.mario.leftMove();
            }
            if (e.getKeyCode() == 32) {
                this.mario.jump();
            }
        } else {
            if (e.getKeyCode() == 32) {
                this.isStart = true;
                AudioClip bgm = Sources.bg0;
                bgm.play();
                this.mario.setLife(3);
                this.mario.setScore(0);
            }
        }


    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (this.isStart) {
            if (e.getKeyCode() == 39) {
                this.mario.rightStop();
            }
            if (e.getKeyCode() == 37) {
                this.mario.leftStop();
            }
        }

    }

    @Override
    public void run() {

        while (true) {
            this.repaint();
            try {
                Thread.sleep(50);
                if (this.mario.getX() >= 850) {
                    //切换场景
                    this.nowBG = this.allBG.get(this.nowBG.getSort());
                    this.mario.setBg(this.nowBG);

                    this.mario.setX(0);
                }
                if (this.mario.isDead()) {
                    JOptionPane.showMessageDialog(this, "游戏结束！");
                    System.exit(0);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}

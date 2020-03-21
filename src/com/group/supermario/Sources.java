package com.group.supermario;


import javax.swing.*;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class Sources { //用于初始化所有图片的类
    static Image startImage;
    static Image endImage;
    static Image midImage;
    static Image bgImage;
    static Image overImage;
    static Image moneyImage;
    static Image scoreImage;
    static Image monsterDeadImage;
    static Image[] allMarioImage = new Image[45];
    static Image[] allFlowerImage = new Image[2];
    static Image[] allMonsterImage = new Image[10];
    static Image[] allObstructionImage = new Image[10];
    static Image[] allLifeImage = new Image[3];
    static URL bgMusic, eatmoneyMusic, touchWallMusic, deadMusic, tikMonster;
    static AudioClip bg0, bg1, bg2, bg3, bg4;

    static {
        scoreImage = new ImageIcon("sources/score.png").getImage();
        overImage = new ImageIcon("sources/over.png").getImage();
        midImage = new ImageIcon("sources/bg2.png").getImage();
        monsterDeadImage = new ImageIcon("sources/triangle.png").getImage();
        startImage = new ImageIcon("sources/start.png").getImage();
        endImage = new ImageIcon("sources/bg3.png").getImage();
        bgImage = new ImageIcon("sources/bg1.png").getImage();
        moneyImage = new ImageIcon("sources/money.png").getImage();
        for (int i = 0; i < allMarioImage.length; i++) {
            allMarioImage[i] = new ImageIcon("sources/" + i + ".png").getImage();
        }
        for (int i = 0; i < allFlowerImage.length; i++) {
            allFlowerImage[i] = new ImageIcon("sources/flower" + (i + 1) + ".png").getImage();
        }
        for (int i = 1; i < allObstructionImage.length; i++) {
            allObstructionImage[i] = new ImageIcon("sources/ob" + i + ".gif").getImage();
        }
        for (int i = 0; i < allMonsterImage.length; i++) {
            allMonsterImage[i] = new ImageIcon("sources/monster/" + i + ".png").getImage();
        }
        for (int i = 1; i <= allLifeImage.length; i++) {
            allLifeImage[i - 1] = new ImageIcon("sources/l" + i + ".png").getImage();
        }
        try {
            bgMusic = new File("sources/a.wav").toURL();
            eatmoneyMusic = new File("sources/eatmoney.wav").toURL();
            touchWallMusic = new File("sources/b.wav").toURL();
            deadMusic = new File("sources/c.wav").toURL();
            tikMonster = new File("sources/d.wav").toURL();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        bg0 = Applet.newAudioClip(bgMusic);
        bg1 = Applet.newAudioClip(eatmoneyMusic);
        bg2 = Applet.newAudioClip(touchWallMusic);
        bg3 = Applet.newAudioClip(deadMusic);
        bg4 = Applet.newAudioClip(tikMonster);


    }
}

package Entity;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;

public class Entity {

    public int worldx, worldy;
    public int speed;
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public String direction;

    public int spriteCounter=0;
    public int spriteNum=1;

    //collision
    public Rectangle solidArea;
    public boolean collisionOn =false;
}

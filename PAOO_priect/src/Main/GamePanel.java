package Main;

import Entity.Player;
import Tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{

    //FPS
    private static final int FPS = 60;
    private Database database;
    // SCREEN SETTINGS

    final int originalTileSize=16;
    final int scale = 3;

    public final int tileSize=originalTileSize*scale; //48x48 tile
    public final int maxScreenCol = 16;
    public final int getMaxScreenRow =16;
    public final int screenWidth = tileSize * maxScreenCol; // 768 pix
    public final int screenHeight = tileSize * getMaxScreenRow; // 768 pix

    //World settings
    public final int maxWorldCol=50;
    public final int maxWorldRow=50;
    public final int worldWidth=tileSize*maxWorldCol;
    public final int worldHeight = tileSize*maxWorldRow;

    TileManager tileM = new TileManager(this);

    KeyHandler keyH=new KeyHandler();
    Thread gameThread;
    public collisionCheck cChecker= new collisionCheck(this);
    public Player player;

    //pozitia dif a jucatorului
    //int playerX=100;
   // int playerY=100;
   // int playerSpeed=4;

    public GamePanel(){
        database = new Database();
        player=new Player(this,keyH,database.getX(), database.getY());
        this.setPreferredSize(new Dimension(screenWidth,screenWidth));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);


    }
    public void startGameThread(){
        gameThread=new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        double drawInterval=1000000000/FPS;
        double delta=0;
        long lastTime=System.nanoTime();
        long currentTime;
        long timer=0;
        long drawCount=0;

        while(gameThread!=null){
            currentTime=System.nanoTime();

            delta+=(currentTime-lastTime)/drawInterval;
            timer+=(currentTime-lastTime);
            lastTime=currentTime;

            if(delta>=1){
                update();
                repaint();
                delta--;
                drawCount++;
            }

            if(timer>=1000000000){
                System.out.println("FPS:"+drawCount);
                drawCount=0;
                timer=0;
            }
        }
    }

    public void update(){
    player.update();
    database.update(player.worldx, player.worldy);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2= (Graphics2D)g;

        tileM.draw(g2);

        player.draw(g2);

        g2.dispose();
    }
}



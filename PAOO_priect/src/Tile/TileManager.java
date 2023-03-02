package Tile;

import Main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {

    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][];


    public TileManager(GamePanel gp) {
        this.gp = gp;

        tile = new Tile[10];

        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];

        getTileImage();
        loadMap();
    }

    public void getTileImage() {
        try {

            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/grass.png"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/wall.png"));


            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/water.png"));
            tile[2].collision=true;

            tile[3] = new Tile();
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/earth.png"));

            tile[4] = new Tile();
            tile[4].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/tree.png"));
            tile[4].collision=true;

            tile[5] = new Tile();
            tile[5].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/sand.png"));

            tile[6] = new Tile();
            tile[6].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/lava.png"));
            tile[6].collision=true;

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMap() {

        try {
            InputStream is = getClass().getResourceAsStream("/maps/mp2.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col=0;
            int row=0;

            while(col<gp.maxWorldCol && row<gp.maxWorldRow)
            {
               String line = br.readLine();

               while(col<gp.maxWorldCol){

                   String numbers[]=line.split(" ");

                   int num = Integer.parseInt(numbers[col]);

                   mapTileNum[col][row]=num;
                   col++;
               }
               if(col==gp.maxWorldCol){
                   col=0;
                   row++;
               }
            }
            br.close();
        } catch (Exception e) {

        }
    }



    public void draw(Graphics2D g2){

        int worldcol=0;
        int worldrow=0;


        while(worldcol<gp.maxWorldCol && worldrow<gp.maxWorldRow){

            int tileNum=mapTileNum[worldcol][worldrow];
            int worldx=worldcol*gp.tileSize;
            int worldy=worldrow*gp.tileSize;
            int screenX=worldx-gp.player.worldx+gp.player.screenX;
            int screenY=worldy-gp.player.worldy+gp.player.screenY;

            if(worldx + gp.tileSize>gp.player.worldx-gp.player.screenX &&
                   worldx-gp.tileSize<gp.player.worldx+gp.player.screenX &&
                   worldy+gp.tileSize>gp.player.worldy-gp.player.screenY &&
                   worldy-gp.tileSize<gp.player.worldy+gp.player.screenY)
            {
                g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize,null);
            }

            worldcol++;


            if(worldcol==gp.maxWorldCol){
                worldcol=0;
                worldrow++;
            }
        }
    }

}

package view.title;

import states.PlayState;
import view.main.GamePanel;
import view.utils.Tool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;

public class TileManager {

    GamePanel gp;
    PlayState ps;
    public Tile[] tiles;
    public int[][] mapFileNum;

    public TileManager(GamePanel gp, PlayState ps) {
        this.gp = gp;
        this.ps = ps;
        tiles = new Tile[50];
        getTitleImage();
        mapFileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
        loadMap("/map/world02_v2.txt");
    }

    public void getTitleImage() {
//      Unused: 0 - 9
        setup(0, "grass00", false);
        setup(1, "grass00", false);
        setup(2, "grass00", false);
        setup(3, "grass00", false);
        setup(4, "grass00", false);
        setup(5, "grass00", false);
        setup(6, "grass00", false);
        setup(7, "grass00", false);
        setup(8, "grass00", false);
        setup(9, "grass00", false);
        // using:
        setup(10, "grass00", false);
        setup(11, "grass01", false);
        setup(12, "water00", true);
        setup(13, "water01", true);
        setup(14, "water02", true);
        setup(15, "water03", true);
        setup(16, "water04", true);
        setup(17, "water05", true);
        setup(18, "water06", true);
        setup(19, "water07", true);
        setup(20, "water08", true);
        setup(21, "water09", true);
        setup(22, "water10", true);
        setup(23, "water11", true);
        setup(24, "water12", true);
        setup(25, "water13", true);
        setup(26, "road00", false);
        setup(27, "road01", false);
        setup(28, "road02", false);
        setup(29, "road03", false);
        setup(30, "road04", false);
        setup(31, "road05", false);
        setup(32, "road06", false);
        setup(33, "road07", false);
        setup(34, "road08", false);
        setup(35, "road09", false);
        setup(36, "road10", false);
        setup(37, "road11", false);
        setup(38, "road12", false);
        setup(39, "earth", false);
        setup(40, "wall", true);
        setup(41, "grass01", false);
        setup(42, "tree", true);
//        setup(41, "tree", true);

//
//        try {
//            tiles[0] = new Tile();
//            tiles[0].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/earth.png")));
//
//            tiles[1] = new Tile();
//            tiles[1].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/wall.png")));
//            tiles[1].collision = true;
//            tiles[4] = new Tile();
//            tiles[4].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/tree.png")));
//            tiles[4].collision = true;
//            tiles[2] = new Tile();
//            tiles[2].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/water00.png")));
//            tiles[2].collision = true;
//            tiles[5] = new Tile();
//            tiles[5].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/road00.png")));
//            tiles[3] = new Tile();
//            tiles[3].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/grass00.png")));
//            // additional
//            tiles[6] = new Tile();
//            tiles[6].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/road01.png")));
//            tiles[7] = new Tile();
//            tiles[7].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/road02.png")));
//        }catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    /**
     * Setup new image
     * @param index
     * @param imagePath
     * @param collision
     */
    public void setup(int index, String imagePath, boolean collision) {
        // optimize image loading
        Tool tool = new Tool();
        try {
            tiles[index] = new Tile();
            tiles[index].image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/tiles/" + imagePath + ".png")));
            tiles[index].image = tool.scaleImage(tiles[index].image, gp.titleSize, gp.titleSize);
            tiles[index].collision = collision;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMap (String filePath) {
        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader( new InputStreamReader(is) );

            int col = 0;
            int row = 0;

            while (col < gp.maxWorldRow && row < gp.maxWorldRow) {
                String line = br.readLine();

                while (col < gp.maxWorldCol) {
                    String[] numbers = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);
                    mapFileNum [col][row] = num;
                    col++;
                }
                if (col == gp.maxWorldCol) {
                    col = 0;
                    row++;
                }
            }
            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw (Graphics2D g2) {
        int worldCol = 0;
        int worldRow = 0;

        while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {
            int tileNum = mapFileNum[worldCol][worldRow];

            int worldX = worldCol * gp.titleSize;
            int worldY = worldRow * gp.titleSize;
            int screenX = worldX - ps.player.getWorldX() + ps.player.screenX;
            int screenY = worldY - ps.player.getWorldY() + ps.player.screenY;

            /**
             * NOTE: Stop moving camera at the edge
             */
            if (ps.player.screenX > ps.player.getWorldX()) {
                screenX = worldX;
            }
            if (ps.player.screenY > ps.player.getWorldY()) {
                screenY = worldY;
            }
            int rightOffset = gp.screenWidth - ps.player.screenX;
            if (rightOffset > gp.worldWidth - ps.player.getWorldX()) {
                screenX = gp.screenWidth - (gp.worldWidth - worldX);
            }
            int bottomOffset = gp.screenHeight - ps.player.screenY;
            if (bottomOffset > gp.worldHeight - ps.player.getWorldY()) {
                screenY = gp.screenHeight - (gp.worldHeight - worldY);
            }

            if (
                    worldX > ps.player.getWorldX() - ps.player.screenX - gp.titleSize &&
                    worldX < ps.player.getWorldX() + ps.player.screenX + gp.titleSize &&
                    worldY > ps.player.getWorldY() - ps.player.screenY - gp.titleSize &&
                    worldY < ps.player.getWorldY() + ps.player.screenY + gp.titleSize
            ) {
                g2.drawImage(tiles[tileNum].image, screenX, screenY, gp.titleSize, gp.titleSize, null);
                // TEST
//                g2.drawRect(screenX, screenY, gp.titleSize, gp.titleSize);
            }
            /**
             * NOTE: Stop moving camera at the edge
             */
            else if (ps.player.screenX > ps.player.getWorldX() || ps.player.screenY > ps.player.getWorldY()
            || rightOffset > gp.worldWidth - ps.player.getWorldX() || bottomOffset > gp.worldHeight - ps.player.getWorldY()
            ) {
                g2.drawImage(tiles[tileNum].image, screenX, screenY, gp.titleSize, gp.titleSize, null);
            }

            worldCol++;
            if (worldCol == gp.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
        }
    }
}


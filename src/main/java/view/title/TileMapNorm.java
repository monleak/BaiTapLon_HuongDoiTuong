package view.title;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import view.graphics.Sprite;
import view.math.AABB;
import view.math.Vector2f;
import view.title.blocks.Block;
import view.title.blocks.NormBlock;

/**
 * TileMapNorm: các ô mà người chơi, con vật có thể đi qua được.
 *
 */
public class TileMapNorm extends TileMap {

    public Block[] blocks;

    private int tileWidth;  // c.rộng mặc định: 48
    private int tileHeight; // c.dài mặc định: 48

    private int height;

    public TileMapNorm(String data, ArrayList<BufferedImage> bufferedImages, int width, int height, int tileWidth, int tileHeight, int tileColumns) {
        blocks = new Block[width * height];

        this.tileWidth = tileWidth;
        this.tileHeight = tileHeight;

        this.height = height;

        String[] block = data.split(",");

        for(int i = 0; i < (width * height); i++) {
            int temp = Integer.parseInt(block[i].replaceAll("\\s+",""));
//            int a = (int) ((temp - 1) % tileColumns);
//            int b = (int) ((temp - 1) / tileColumns);
            if(temp != 0) {
                blocks[i] = new NormBlock(
                                new Sprite(bufferedImages.get(temp - 1)),
                                new Vector2f((int) (i % width) * tileWidth, (int) (i / height) * tileHeight),
                                tileWidth,
                                tileHeight
                );
            }
        }

//        for (int i = 0; i < ci.getRows(); i++) {
//            for (int j = 0; j < ci.getColumns(); j++) {
//                blocks[i*ci.getRows() + j] = new NormBlock(
//                        new Sprite(ci.getSubImageByIndex(block[i])),
//                        new Vector2f((int) (i % width) * tileWidth, (int) (i / height) * tileHeight),
//                        tileWidth,
//                        tileHeight
//                );
//            }
//        }
    }

    public Block[] getBlocks() { return blocks; }

    public void render(Graphics2D g, AABB cam) {
        int x = (int) ((cam.getPos().x) / tileWidth);
        int y = (int) ((cam.getPos().y) / tileHeight);

        for(int i = x; i < x + (cam.getWidth() / tileWidth)- 1; i++) {
            for(int j = y; j < y + (cam.getHeight() / tileHeight) - 1; j++) {
                if(i + (j * height) > -1 && i + (j * height) < blocks.length && blocks[i + (j * height)] != null) {
                    blocks[i + (j * height)].render(g);
                }
            }
        }
    }

}

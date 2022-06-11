package view.Graphics;

import view.math.Vector2f;
import view.utils.Tool;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Objects;

import javax.imageio.ImageIO;

//public class SpriteSheet {
//
//    private Sprite SPRITESHEET = null;
//    private Sprite[][] spriteArray;
//    private final int TILE_SIZE = 32;
//    public int w;
//    public int h;
//    private int wSprite;
//    private int hSprite;
//    private String file;
//
//    public static Font currentFont;
//
//    public SpriteSheet(String file) {
//        this.file = file;
//        w = TILE_SIZE;
//        h = TILE_SIZE;
//
//        System.out.println("Loading: " + file + "...");
//        SPRITESHEET = new Sprite(loadSprite(file));
//
//        wSprite = SPRITESHEET.image.getWidth() / w;
//        hSprite = SPRITESHEET.image.getHeight() / h;
//        loadSpriteArray();
//    }
//
//    public SpriteSheet(Sprite sprite, String name, int w, int h) {
//        this.w = w;
//        this.h = h;
//
//        System.out.println("Loading: " + name + "...");
//        SPRITESHEET = sprite;
//
//        wSprite = SPRITESHEET.image.getWidth() / w;
//        hSprite = SPRITESHEET.image.getHeight() / h;
//        loadSpriteArray();
//
//    }
//
//    public SpriteSheet(String file, int w, int h) {
//        this.w = w;
//        this.h = h;
//        this.file = file;
//
//        System.out.println("Loading: " + file + "...");
//        SPRITESHEET = new Sprite(loadSprite(file), 192, 192);
//
//        wSprite = SPRITESHEET.image.getWidth() / w;
//        hSprite = SPRITESHEET.image.getHeight() / h;
//        loadSpriteArray();
//    }
//
//    public void setSize(int width, int height) {
//        setWidth(width);
//        setHeight(height);
//    }
//
//    public void setWidth(int i) {
//        w = i;
//        wSprite = SPRITESHEET.image.getWidth() / w;
//    }
//
//    public void setHeight(int i) {
//        h = i;
//        hSprite = SPRITESHEET.image.getHeight() / h;
//    }
//
//    public int getWidth() { return w; }
//    public int getHeight() { return h; }
//    public int getRows() { return hSprite; }
//    public int getCols() { return wSprite; }
//    public int getTotalTiles() { return wSprite * hSprite; }
//    public String getFilename() { return file; }
//
//    private BufferedImage loadSprite(String file) {
//        BufferedImage sprite = null;
//        try {
////            sprite = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(file)));
//            sprite = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(file)));
//        } catch (Exception e) {
//            System.err.println("ERROR: could not load file: " + file);
//            e.printStackTrace();
//        }
//        return sprite;
//    }
//
//    public void loadSpriteArray() {
//        spriteArray = new Sprite[hSprite][wSprite];
//
//        for (int y = 0; y < hSprite; y++) {
//            for (int x = 0; x < wSprite; x++) {
//                spriteArray[y][x] = getSprite(x, y);
//            }
//        }
//    }
//
////    public void setEffect(Sprite.effect e) {
////        SPRITESHEET.setEffect(e);
////    }
//
//    public Sprite getSpriteSheet() {
//        return SPRITESHEET;
//    }
//
//    public Sprite getSprite(int x, int y) {
//        Sprite s = SPRITESHEET.getSubimage(x * w, y * h, w, h);
//        return s;
//    }
//
//    public Sprite getNewSprite(int x, int y) {
//        return SPRITESHEET.getNewSubimage(x * w, y * h, w, h);
//    }
//
//    public Sprite getSprite(int x, int y, int w, int h) {
//        return SPRITESHEET.getSubimage(x * w, y * h, w, h);
//    }
//
//    public BufferedImage getSubimage(int x, int y, int w, int h) {
//        return SPRITESHEET.image.getSubimage(x, y, w, h);
//    }
//
//    public Sprite[] getSpriteArray(int i) {
//        return spriteArray[i];
//    }
//
//    public Sprite[][] getSpriteArray2() {
//        return spriteArray;
//    }
//
//    public static void drawArray(Graphics2D g, ArrayList<Sprite> img, Vector2f pos, int width, int height, int xOffset, int yOffset) {
//        float x = pos.x;
//        float y = pos.y;
//
//        for (int i = 0; i < img.size(); i++) {
//            if (img.get(i) != null) {
//                g.drawImage(img.get(i).image, (int) x, (int) y, width, height, null);
//            }
//
//            x += xOffset;
//            y += yOffset;
//        }
//    }
//
//    public static void drawArray(Graphics2D g, String word, Vector2f pos, int size) {
//        drawArray(g, currentFont, word, pos, size, size, size, 0);
//    }
//
//    public static void drawArray(Graphics2D g, String word, Vector2f pos, int size, int xOffset) {
//        drawArray(g, currentFont, word, pos, size, size, xOffset, 0);
//    }
//
//    public static void drawArray(Graphics2D g, String word, Vector2f pos, int width, int height, int xOffset) {
//        drawArray(g, currentFont, word, pos, width, height, xOffset, 0);
//    }
//
//    public static void drawArray(Graphics2D g, Font f, String word, Vector2f pos, int size, int xOffset) {
//        drawArray(g, f, word, pos, size, size, xOffset, 0);
//    }
//
//    public static void drawArray(Graphics2D g, Font f, String word, Vector2f pos, int width, int height, int xOffset, int yOffset) {
//        float x = pos.x;
//        float y = pos.y;
//
//        currentFont = f;
//
//        for (int i = 0; i < word.length(); i++) {
//            if (word.charAt(i) != 32)
//                // g.drawImage(f.getLetter(word.charAt(i)), (int) x, (int) y, width, height, null);
//                g.drawString("abcabc", (int) x, (int) y);
//            x += xOffset;
//            y += yOffset;
//        }
//
//    }
//
//}


public class SpriteSheet {

    private Sprite spritesArray[][];
    private int spritesIndexCounter[];
    private int arrLen;

    public SpriteSheet (int sttNum, int len) {
        spritesArray = new Sprite[sttNum][len];
        spritesIndexCounter = new int[sttNum];
        arrLen = len;
    }

    public SpriteSheet addSprite(int sttNum, BufferedImage image) {
        try {
            int currentIdx = spritesIndexCounter[sttNum];
            spritesArray[sttNum][currentIdx] = new Sprite(image);
            spritesIndexCounter[sttNum] += 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }

    public Sprite[] getSpriteArray (int sttIdx) {
        return spritesArray[sttIdx];
    }
    public Sprite getSprite (int sttIdx, int idx) {
        return spritesArray[sttIdx][idx];
    }
}
package view.entity;

import states.PlayState;
import view.effect.ILocatable;
import view.main.GamePanel;
import view.math.AABB;
import view.math.Vector2f;

import java.awt.*;

public class GameObject implements ILocatable {
    protected GamePanel gp;
    protected PlayState ps;

    protected Vector2f pos;

    public String name;
//    private int worldX, worldY;
    // colision
    public Rectangle solidArea;
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collision = false;

    // aabb
    protected AABB bounds;

    public GameObject(GamePanel gp, PlayState ps) {
        this.gp = gp;
        this.ps = ps;
        this.solidArea = new Rectangle(0, 0, 48, 48);

        this.pos = new Vector2f(0, 0);
        this.bounds = new AABB(this.pos, 48, 48);
    }

    public AABB getBounds() {
        return bounds;
    }

//    @Override
//    public int getWorldX() {
//        return worldX;
//    }

//    @Override
//    public void setWorldX(int worldX) {
//        this.worldX = worldX;
//    }

//    @Override
//    public int getWorldY() {
//        return worldY;
//    }

//    @Override
//    public void setWorldY(int worldY) {
//        this.worldY = worldY;
//    }
//    @Override
//    public int getScreenX () {
//        /**
//         * NOTE: Stop moving camera at the edge
//         */
//        int screenX = getWorldX() - ps.player.getWorldX() + (int) Vector2f.getWorldVarX(0) + this.solidAreaDefaultX;
//        if (Vector2f.getWorldVarX(0) > ps.player.getWorldX()) {
//            screenX = worldX;
//        }
//        int rightOffset = gp.screenWidth - (int) Vector2f.getWorldVarX(0);
//        if (rightOffset > gp.worldWidth - ps.player.getWorldX()) {
//            screenX = gp.screenWidth - (gp.worldWidth - worldX);
//        }
//
//        return screenX;
//    }
//    @Override
//    public int getScreenY () {
//        /**
//         * NOTE: Stop moving camera at the edge
//         */
//        int screenY = getWorldY() - ps.player.getWorldY() + (int) Vector2f.getWorldVarY(0) + this.solidAreaDefaultY;
//        if ((int) Vector2f.getWorldVarY(0) > ps.player.getWorldY()) {
//            screenY = worldY;
//        }
//        int bottomOffset = gp.screenHeight - (int) Vector2f.getWorldVarY(0);
//        if (bottomOffset > gp.worldHeight - ps.player.getWorldY()) {
//            screenY = gp.screenHeight - (gp.worldHeight - worldY);
//        }
//
//        return screenY;
//    }
//
//    @Override
//    public boolean checkInMap () {
//        int mapLeftEdge = ps.player.getWorldX() - (int) Vector2f.getWorldVarX(0) - gp.titleSize;
//        int mapRightEdge = ps.player.getWorldX() + (int) Vector2f.getWorldVarX(0) + gp.titleSize;
//        int mapTopEdge = ps.player.getWorldY() - (int) Vector2f.getWorldVarY(0) - gp.titleSize;
//        int mapBottomEdge = ps.player.getWorldY() + (int) Vector2f.getWorldVarY(0) + gp.titleSize;
//        return  ( worldX > mapLeftEdge &&
//                worldX < mapRightEdge &&
//                worldY > mapTopEdge &&
//                worldY < mapBottomEdge
//        );
//    }

    public void draw(Graphics2D g2) {
        // NOTE: construct new draw new item
        System.out.println("Default drawer");
    }
}

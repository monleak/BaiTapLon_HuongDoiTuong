package view.entity;

import states.PlayState;
import view.effect.ILocatable;
import view.main.GamePanel;

import java.awt.*;

public class GameObject implements ILocatable {
    protected GamePanel gp;
    protected PlayState ps;
    public String name;
    private int worldX, worldY;
    // colision
    public Rectangle solidArea;
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collision = false;

    public GameObject(GamePanel gp, PlayState ps) {
        this.gp = gp;
        this.ps = ps;
        this.solidArea = new Rectangle(0, 0, 48, 48);
    }

    @Override
    public int getWorldX() {
        return worldX;
    }

    @Override
    public void setWorldX(int worldX) {
        this.worldX = worldX;
    }

    @Override
    public int getWorldY() {
        return worldY;
    }

    @Override
    public void setWorldY(int worldY) {
        this.worldY = worldY;
    }
    @Override
    public int getScreenX () {
        /**
         * NOTE: Stop moving camera at the edge
         */
        int screenX = getWorldX() - ps.player.getWorldX() + ps.player.screenX + this.solidAreaDefaultX;
        if (ps.player.screenX > ps.player.getWorldX()) {
            screenX = worldX;
        }
        int rightOffset = gp.screenWidth - ps.player.screenX;
        if (rightOffset > gp.worldWidth - ps.player.getWorldX()) {
            screenX = gp.screenWidth - (gp.worldWidth - worldX);
        }

        return screenX;
    }
    @Override
    public int getScreenY () {
        /**
         * NOTE: Stop moving camera at the edge
         */
        int screenY = getWorldY() - ps.player.getWorldY() + ps.player.screenY + this.solidAreaDefaultY;
        if (ps.player.screenY > ps.player.getWorldY()) {
            screenY = worldY;
        }
        int bottomOffset = gp.screenHeight - ps.player.screenY;
        if (bottomOffset > gp.worldHeight - ps.player.getWorldY()) {
            screenY = gp.screenHeight - (gp.worldHeight - worldY);
        }

        return screenY;
    }

    @Override
    public boolean checkInMap () {
        int mapLeftEdge = ps.player.getWorldX() - ps.player.screenX - gp.titleSize;
        int mapRightEdge = ps.player.getWorldX() + ps.player.screenX + gp.titleSize;
        int mapTopEdge = ps.player.getWorldY() - ps.player.screenY - gp.titleSize;
        int mapBottomEdge = ps.player.getWorldY() + ps.player.screenY + gp.titleSize;
        return  ( worldX > mapLeftEdge &&
                worldX < mapRightEdge &&
                worldY > mapTopEdge &&
                worldY < mapBottomEdge
        );
    }

    public void draw(Graphics2D g2) {
        // NOTE: construct new draw new item
        System.out.println("Default drawer");
    }
}

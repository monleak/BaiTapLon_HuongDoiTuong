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

    public void draw(Graphics2D g2) {
        // NOTE: construct new draw new item
        System.out.println("Default drawer");
    }
}

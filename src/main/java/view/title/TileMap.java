package view.title;


import view.math.AABB;
import view.title.blocks.Block;

import java.awt.Graphics2D;

public abstract class TileMap {

    public abstract Block[] getBlocks();
    public abstract void render(Graphics2D g, AABB cam);
}


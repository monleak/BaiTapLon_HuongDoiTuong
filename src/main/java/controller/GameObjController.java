package controller;

import states.PlayState;
import view.effect.FocusableHandler;
import view.main.KeyHandler;
import view.main.MouseHandler;
import view.math.AABB;
import view.math.Vector2f;


import static states.GameStateManager.gp;

public abstract class GameObjController implements IController{
    public String name;
    private final Vector2f pos;
    private final AABB bounds;
    private final FocusableHandler fch;

    public GameObjController(Vector2f pos, PlayState ps) {
        this.pos = pos;
        this.bounds = new AABB(pos, gp.titleSize, gp.titleSize);
        this.fch = new FocusableHandler(ps, this);
        this.name = "Obj ???";
    }

    public GameObjController(AABB bounds, PlayState ps) {
        this.bounds = bounds;
        this.pos = bounds.getPos();
        this.fch = new FocusableHandler(ps, this);

        this.name = "Obj ???";
    }

    public FocusableHandler getFch() {
        return fch;
    }

    public Vector2f getPos() {
        return pos;
    }

    public AABB getBounds() {
        return bounds;
    }
}

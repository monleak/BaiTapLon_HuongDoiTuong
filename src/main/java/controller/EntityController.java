package controller;

import states.PlayState;
import view.ai.PathFinder;
import view.effect.ILocator;
import view.effect.move.Movement;
import view.effect.move.ObjectMovement;
import view.effect.move.PathManager;
import view.math.AABB;
import view.math.Vector2f;
import view.title.TileCollision;
import view.utils.Direction;

import static states.GameStateManager.gp;

public abstract class EntityController extends GameObjController implements ILocator {

    private Movement movement;
    private Direction direction;
    private PathFinder pathFinder;
    private PathManager pathManager;


    public EntityController(Vector2f pos, PlayState ps) {
        super(pos, ps);

        TileCollision tileCollision = new TileCollision(this);
        this.movement = new ObjectMovement(this.getBounds(), tileCollision);
        this.direction = Direction.DOWN;

        this.pathFinder = new PathFinder(gp, ps);
        this.pathManager = new PathManager(pathFinder, getMovement(), getBounds(), this);
    }

    public PathManager getPathManager() {
        return pathManager;
    }

    public PathFinder getPathFinder() {
        return pathFinder;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Movement getMovement() {
        return movement;
    }

}

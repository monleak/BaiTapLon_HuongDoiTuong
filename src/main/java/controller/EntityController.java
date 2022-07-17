package controller;

import behavior.AnimalBehavior;
import states.PlayState;
import view.ai.PathFinder;
import view.effect.ILocator;
import view.effect.move.Movement;
import view.effect.move.ObjectMovement;
import view.effect.move.PathManager;
import view.main.KeyHandler;
import view.main.MouseHandler;
import view.math.Vector2f;
import view.title.TileCollision;
import view.utils.Direction;

import java.util.Random;

import static states.GameStateManager.gp;

public abstract class EntityController extends GameObjController implements ILocator {

    private final Movement movement;
    private Direction direction;
    private final PathFinder pathFinder;
    private final PathManager pathManager;

    int counter;
    int randomMoveCounter;
    int rand;

    public EntityController(Vector2f pos, PlayState ps) {
        super(pos, ps);

        TileCollision tileCollision = new TileCollision(this);
        this.movement = new ObjectMovement(this.getBounds(), tileCollision);
        this.direction = Direction.DOWN;

        this.pathFinder = new PathFinder(gp, ps);
        this.pathManager = new PathManager(pathFinder, getMovement(), getBounds(), this);
    }
    public void randomMove () {
        randomMoveCounter++;
        if (randomMoveCounter > 180) {
            setDirection(getRandomDirection());
            randomMoveCounter = 0;
        }
    }
    @Override
    public void input(KeyHandler keyHandler, MouseHandler mouseHandler) {
        boolean isRunning = this.pathManager.update();
        if (!isRunning) {
            randomMove();
            this.movement.move(this.direction, this.movement.getSpeed());
        }
//        System.out.println(isRunning + " " + this.getMovement().getSpeed());
    }

    public AnimalBehavior getRandomBehavior () {
        counter++;
        if (counter > 300) {
            Random random = new Random();
            rand = random.nextInt();
            counter = 0;
        }
        switch (rand % 3) {
            case 0:
                return AnimalBehavior.DRINK;
            case 1:
                return AnimalBehavior.PLAY;
            case 2:
                return AnimalBehavior.SIT;
        }
        return AnimalBehavior.EAT;
    }

    public Direction getRandomDirection () {
        Random random = new Random();
        int rand = random.nextInt(4);
        System.out.println(rand % 3);
        switch (rand) {
            case 0:
                return Direction.UP;
            case 1:
                return Direction.DOWN;
            case 2:
                return Direction.LEFT;
            default:
                return Direction.RIGHT;
        }
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

package controller;

import behavior.PlayerBehavior;
import states.PlayState;
import view.ai.PathFinder;
import view.effect.FocusManager;
import view.effect.FocusableHandler;
import view.effect.move.PathManager;
import view.main.KeyHandler;
import view.main.MouseHandler;
import view.math.AABB;
import view.math.Vector2f;
import view.utils.Direction;

import static states.GameStateManager.gp;

public class PlayerController extends EntityController{

    private boolean isAttacking;

    private PlayerBehavior behavior;
    private AABB attackBounds;
    private PlayState ps;

    public PlayerController(Vector2f pos, PlayState ps) {
        super(pos, ps);
        this.attackBounds = new AABB(this.getPos(), gp.titleSize, gp.titleSize);


        this.ps = ps;
        getPathManager().setToGoalListener(actionEvent -> {
            ps.gotoTarget.setShown(false);
        });
        this.name = "Player";
    }

    @Override
    public void input(KeyHandler keyHandler, MouseHandler mouseHandler) {

        Direction d = KeyHandler.directionKeyMapper(keyHandler);
        if (d != null) {
            this.setDirection(d);
            this.getMovement().setSpeed(4);
        }
        else {
            if (this.getPathManager().isGoingToGoal())
                this.getMovement().setSpeed(4);
            else
                this.getMovement().setSpeed(0);
        }

        setAttacking(keyHandler.enterPressed);

        getPathManager().update();

        if (mouseHandler.getButton() != -1) {
            this.getPathManager().goTo(
                    (int) (mouseHandler.getX() + Vector2f.getWorldX()),
                    (int) (mouseHandler.getY() + Vector2f.getWorldY())
            );

            if (mouseHandler.getButton() != -1) {
                ps.gotoTarget.setShown(true);
                ps.gotoTarget.getBounds().getPos().setX(ps.mouseTarget.getBounds().getPos().getX());
                ps.gotoTarget.getBounds().getPos().setY(ps.mouseTarget.getBounds().getPos().getY());
            }
        }
    }

    public boolean isAttacking() {
        return isAttacking;
    }

    public void setAttacking(boolean attacking) {
        isAttacking = attacking;
    }

    public PlayerBehavior getBehavior() {
        return behavior;
    }

    public boolean setBehavior(PlayerBehavior behavior) {
        if (this.behavior == behavior)
            return false;
        this.behavior = behavior;
        return true;
    }

    public boolean behaviorMapper () {
        if (this.isAttacking()) {
            switch (this.getDirection()) {
                case UP:
                    return setBehavior(PlayerBehavior.A_UP);
                case DOWN:
                    return setBehavior(PlayerBehavior.A_DOWN);
                case RIGHT:
                    return setBehavior(PlayerBehavior.A_RIGHT);
                case LEFT:
                    return setBehavior(PlayerBehavior.A_LEFT);
            }
        }
        if (this.getMovement().isRunning()) {
            switch (this.getDirection()) {
                case UP:
                    return setBehavior(PlayerBehavior.R_UP);
                case DOWN:
                    return setBehavior(PlayerBehavior.R_DOWN);
                case RIGHT:
                    return setBehavior(PlayerBehavior.R_RIGHT);
                case LEFT:
                    return setBehavior(PlayerBehavior.R_LEFT);
            }
        }
        switch (this.getDirection()) {
            case UP:
                return setBehavior(PlayerBehavior.UP);
            case DOWN:
                return setBehavior(PlayerBehavior.DOWN);
            case RIGHT:
                return setBehavior(PlayerBehavior.RIGHT);
            case LEFT:
                return setBehavior(PlayerBehavior.LEFT);
        }

        return false;
    }

    public AABB getAttackBounds() {
        int OFFSET = 20;
        switch (getDirection()) {
            case UP:
                this.attackBounds.setXOffset(0);
                this.attackBounds.setYOffset(-OFFSET);
                break;
            case DOWN:
                this.attackBounds.setXOffset(0);
                this.attackBounds.setYOffset(OFFSET);
                break;
            case RIGHT:
                this.attackBounds.setXOffset(OFFSET);
                this.attackBounds.setYOffset(0);
                break;
            case LEFT:
                this.attackBounds.setXOffset(-OFFSET);
                this.attackBounds.setYOffset(0);
                break;
        }

        return attackBounds;
    }

}

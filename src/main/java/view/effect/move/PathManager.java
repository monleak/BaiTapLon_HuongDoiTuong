package view.effect.move;

import controller.GameObjController;
import org.jetbrains.annotations.NotNull;
import view.ai.Node;
import view.ai.PathFinder;
import view.effect.ILocator;
import view.math.AABB;
import view.utils.Direction;

import java.awt.event.ActionListener;

import static states.GameStateManager.gp;

public class PathManager {
    private PathFinder pathFinder;
    private Movement movement;
    private AABB bounds;
    private boolean isGoingToGoal;
    private ILocator owner;
    protected ActionListener toGoalListener = null;
    protected ILocator followedEntity;
    private boolean searchedMark;

    public PathManager(@NotNull PathFinder pathFinder, Movement movement, AABB bounds, ILocator iLocator) {
        this.pathFinder = pathFinder;
        this.movement = movement;
        this.bounds = bounds;
        this.owner = iLocator;
    }

    public void moveByPath () {
        // run to goal
        if(pathFinder.getPathList().size() > 0) {
            isGoingToGoal = true;
            Node next = pathFinder.getPathList().get(0);
            if (bounds.getPos().getX() > next.column * gp.titleSize) {
                bounds.getPos().addX(-this.movement.getSpeed());
                this.owner.setDirection(Direction.LEFT);
            } else
            if (bounds.getPos().getX() < next.column * gp.titleSize) {
                bounds.getPos().addX(+this.movement.getSpeed());
                this.owner.setDirection(Direction.RIGHT);
            } else
            if (bounds.getPos().getY() > next.row * gp.titleSize) {
                bounds.getPos().addY(-this.movement.getSpeed());
                this.owner.setDirection(Direction.UP);
            } else
            if (bounds.getPos().getY() < next.row * gp.titleSize) {
                bounds.getPos().addY(+this.movement.getSpeed());
                this.owner.setDirection(Direction.DOWN);
            } else {
                // remove node
                pathFinder.getPathList().remove(0);
            }
        } else {
            if (isGoingToGoal) {
                isGoingToGoal = false;
                if(this.toGoalListener != null)
                    this.toGoalListener.actionPerformed(null);
            }
        }
    }

    public boolean isGoingToGoal() {
        return isGoingToGoal;
    }

    public void setToGoalListener(ActionListener actionListener) {
        this.toGoalListener = actionListener;
    }

    public void removeToGoalListener () {
        this.toGoalListener = null;
    }

    public void follow (ILocator entity) {
        this.followedEntity = entity;
        this.searchedMark = false;
        pathFinder.setNodes(
                this.owner.getPos(), entity.getPos()
        );
    }

    public void unfollow () {
        this.followedEntity = null;
        this.searchedMark = true;
        this.pathFinder.getPathList().removeAll(this.pathFinder.getPathList());
    }

    public boolean goTo (@NotNull GameObjController gameObject) {

        return this.goTo(
                (int) gameObject.getPos().getX(),
                (int) gameObject.getPos().getY()
        ) || this.owner.getBounds().distance(gameObject.getBounds().getPos()) < 60;
    }

    public boolean goTo (int x, int y) {
        this.followedEntity = null;
        this.searchedMark = false;
        pathFinder.setNodes(
                (int) this.owner.getPos().getX(),
                (int) this.owner.getPos().getY(),
                x,
                y
        );
        searchedMark = true;

        return pathFinder.search();
    }

    public void update() {
        if (!searchedMark) {
            if (followedEntity != null) {   // follow
                follow(followedEntity);
                pathFinder.search();
            } else {                        // goto
                // search 1 time in goTo method
            }
        }

        this.moveByPath();

    };

}

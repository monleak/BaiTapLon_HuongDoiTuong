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
//            System.out.println("x: " + bounds.getPos().getX() + "square x: " + next.column * gp.titleSize);
//            System.out.println("y: " + bounds.getPos().getY() + "square y: " + next.row * gp.titleSize);
            if ((int) bounds.getPos().getX() > next.column * gp.titleSize) {
                if (
                        Math.abs((int) bounds.getPos().getX() - next.column * gp.titleSize ) <= movement.getSpeed()
                ) {
                    bounds.getPos().setX(next.column * gp.titleSize);
                } else {
                    bounds.getPos().addX(-this.movement.getSpeed());
                    this.owner.setDirection(Direction.LEFT);
                }
            } else
            if ( (int) bounds.getPos().getX() < next.column * gp.titleSize) {
                if (
                        Math.abs((int) bounds.getPos().getX() - next.column * gp.titleSize ) <= movement.getSpeed()
                ) {
                    bounds.getPos().setX(next.column * gp.titleSize);
                } else {
                    bounds.getPos().addX(+this.movement.getSpeed());
                    this.owner.setDirection(Direction.RIGHT);
                }
            } else
            if ( (int) bounds.getPos().getY() > next.row * gp.titleSize) {
                if (
                        Math.abs((int) bounds.getPos().getY() - next.row * gp.titleSize ) <= movement.getSpeed()
                ) {
                    bounds.getPos().setY(next.row * gp.titleSize);
                } else {
                    bounds.getPos().addY(-this.movement.getSpeed());
                    this.owner.setDirection(Direction.UP);
                }

            } else
            if ( (int) bounds.getPos().getY() < next.row * gp.titleSize) {
                if (
                        Math.abs((int) bounds.getPos().getY() - next.row * gp.titleSize ) <= movement.getSpeed()
                ) {
                    bounds.getPos().setY(next.row * gp.titleSize);
                } else {
                    bounds.getPos().addY(+this.movement.getSpeed());
                    this.owner.setDirection(Direction.DOWN);
                }

            } else {
                // remove node
                pathFinder.getPathList().remove(0);
            }
//            pathFinder.getPathList().remove(0);

        } else {
            if (isGoingToGoal) {
                isGoingToGoal = false;
                searchedMark = false;
                if(this.toGoalListener != null) {
                    this.toGoalListener.actionPerformed(null);
                    this.removeToGoalListener();
                }
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
                this.owner.getBounds(), entity.getBounds()
        );
    }

    public void unfollow () {
        this.followedEntity = null;
        this.searchedMark = true;
        this.pathFinder.getPathList().removeAll(this.pathFinder.getPathList());
        this.removeToGoalListener();
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

    public boolean update() {
        boolean ret = false;

        if (!searchedMark) {
            if (followedEntity != null) {   // follow
                follow(followedEntity);
                pathFinder.search();
            } else {                        // goto
                // search 1 time in goTo method
            }
            ret = true;
        }

        this.moveByPath();

        return isGoingToGoal;
    }

}

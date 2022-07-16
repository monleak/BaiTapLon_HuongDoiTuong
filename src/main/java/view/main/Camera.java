package view.main;

import controller.EntityController;
import states.GameStateManager;
import view.effect.move.CameraMovement;
import view.effect.move.Movement;
import view.entity.Entity;
import view.math.AABB;
import view.math.Vector2f;
import view.utils.Direction;

/**
 * <h1>Camera: 1 phần bản đồ được hiển thị lên màn hình.</h1>
 * <p>
 * Chứa các phương thức để đièu chỉnh vị trí camera.
 * Có thể đi theo mục tiêu băng cách sử dung target(player)
 * Phần ngoài màn hình ( quá 2 ô ) không render để tăng hiệu năng.
 * </p>
 *
 * @see <a href="https://www.youtube.com/watch?v=7Av3LVa3Ksg">Youtube</a>
 */
public class Camera {
    private final AABB collisionCam;
    private EntityController e;

    private Direction direction;
    private int speed = 8;
    private Movement movement;


    public Camera(AABB collisionCam, int tileSize) {
        this.collisionCam = collisionCam;

        this.movement = new CameraMovement(collisionCam, null);
    }

//    public GameObject getTarget() { return e; }
    public Vector2f getPos() {
        return collisionCam.getPos();
    }
    public AABB getBounds() {
        return collisionCam;
    }

    public void input(MouseHandler mouse, KeyHandler key) {
        if (key.upPressed)
            direction = Direction.UP;
        else if (key.downPressed)
            direction = Direction.DOWN;
        else if (key.rightPressed)
            direction = Direction.RIGHT;
        else if (key.leftPressed)
            direction = Direction.LEFT;
        else
            direction = null;
    }

    public void update() {
        if (e != null) {
//            setSpeed(e.get.getSpeed());
            e.getMovement().move(direction, e.getMovement().getSpeed());
            this.move();
        }
        else
            movement.move(direction, 4);
    }


    private void move() {
        if (e != null) {
            int centerScreenX = (GameStateManager.gp.screenWidth - GameStateManager.gp.titleSize) / 2;
            int centerScreenY = (GameStateManager.gp.screenHeight - GameStateManager.gp.titleSize) / 2;

            if (e.getPos().getX() > centerScreenX && e.getPos().getX() < GameStateManager.gp.worldWidth - centerScreenX - GameStateManager.gp.titleSize ) {
                collisionCam.getPos().setX(e.getPos().getX() - centerScreenX);
                Vector2f.setWorldVar(collisionCam.getPos().getX(), collisionCam.getPos().getY());
            }
            if (e.getPos().getY() > centerScreenY && e.getPos().getY() < GameStateManager.gp.worldHeight - centerScreenY - GameStateManager.gp.titleSize ) {
                collisionCam.getPos().setY(e.getPos().getY() - centerScreenY);
                Vector2f.setWorldVar(collisionCam.getPos().getX(), collisionCam.getPos().getY());
            }
        }
    }

    public void target(Entity e) {
        this.e = e.getController();
        int centerScreenX = (GameStateManager.gp.screenWidth - GameStateManager.gp.titleSize) / 2;
        int centerScreenY = (GameStateManager.gp.screenHeight - GameStateManager.gp.titleSize) / 2;
        float camX = this.e.getPos().getX() - centerScreenX;
        float camY = this.e.getPos().getY() - centerScreenY;
        int maxCamX = GameStateManager.gp.worldWidth - GameStateManager.gp.screenWidth;
        int maxCamY = GameStateManager.gp.worldHeight - GameStateManager.gp.screenHeight;
        if(camX >= 0 && camX <= maxCamX)
            collisionCam.getPos().setX(camX);
        else if (camX < 0)
            collisionCam.getPos().setX(0);
        else if (camX > maxCamX)
            collisionCam.getPos().setX(maxCamX);

        if(camY >= 0 && camY <= maxCamY)
            collisionCam.getPos().setY(camY);
        else if (camY < 0)
            collisionCam.getPos().setY(0);
        else if (camY > maxCamX)
            collisionCam.getPos().setY(maxCamY);

        Vector2f.setWorldVar(collisionCam.getPos().getX(), collisionCam.getPos().getY());
        if(e != null) {
            if (e.getController() instanceof EntityController)
                speed = ((EntityController) (e.getController()) ).getMovement().getSpeed();
        } else {
            speed = 8;
        }
    }

    public void setSpeed(int speed) {this.speed = speed; }



}

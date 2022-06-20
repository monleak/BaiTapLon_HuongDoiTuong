package view.main;

import states.GameStateManager;
import view.entity.Entity;
import view.math.AABB;
import view.math.Vector2f;

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
    private Entity e;

    private int widthLimit;
    private int heightLimit;
    private int tileSize;
    private boolean up;
    private boolean down;
    private boolean left;
    private boolean right;
    private float speed = 8f;

    public Camera(AABB collisionCam, int tileSize) {
        this.collisionCam = collisionCam;
        this.tileSize = tileSize;
    }

    public void setLimit(int widthLimit, int heightLimit) {
        this.widthLimit = widthLimit;
        this.heightLimit = heightLimit;
    }
    public Entity getTarget() { return e; }
    public Vector2f getPos() {
        return collisionCam.getPos();
    }
    public AABB getBounds() {
        return collisionCam;
    }

    public void update() {
        if (e != null)
            if (!e.collisionOn)
                move();
    }

    private void move() {
        int rightOffset = GameStateManager.gp.screenWidth
                - (int) Vector2f.getWorldVarX(0)
                - GameStateManager.gp.worldWidth
                + (int) getPos().getWorldVar().x;
        if (e != null)
            System.out.println(e.getPos());
        if (
                up
                && (int) e.getPos().y >= GameStateManager.gp.screenHeight / 2
                && (int) e.getPos().y <= GameStateManager.gp.worldHeight - (GameStateManager.gp.screenHeight) / 2
        ) {
            collisionCam.getPos().y = collisionCam.getPos().y - speed;
            Vector2f.setWorldVar(collisionCam.getPos().x, collisionCam.getPos().y);
        }
//        System.out.println(collisionCam.getPos().y);
        else if (
                down
                && collisionCam.getPos().y < GameStateManager.gp.worldHeight - GameStateManager.gp.screenHeight
                && (int) e.getPos().y > GameStateManager.gp.screenHeight / 2
        ) {
            collisionCam.getPos().y = collisionCam.getPos().y + speed;
            Vector2f.setWorldVar(collisionCam.getPos().x, collisionCam.getPos().y);
        }
        else if (right
                && collisionCam.getPos().x < GameStateManager.gp.worldWidth - GameStateManager.gp.screenWidth
                && (int) e.getPos().x > GameStateManager.gp.screenWidth / 2) {
            collisionCam.getPos().x = collisionCam.getPos().x + speed;
            Vector2f.setWorldVar(collisionCam.getPos().x, collisionCam.getPos().y);
        }
        else if (
                left
                        && (int) e.getPos().x >= GameStateManager.gp.screenWidth / 2
                        && (int) e.getPos().x <= GameStateManager.gp.worldWidth - (GameStateManager.gp.screenWidth) / 2
//                && collisionCam.getPos().x > 0
//                && (int) e.getPos().getWorldVar().x == GameStateManager.gp.screenWidth / 2
        ) {
            collisionCam.getPos().x = collisionCam.getPos().x - speed;
            Vector2f.setWorldVar(collisionCam.getPos().x, collisionCam.getPos().y);
        }
//        if (up) {
//            dy -= acc;
//            if (dy < -maxSpeed) {
//                dy = -maxSpeed;
//            }
//        } else {
//            if (dy < 0) {
//                dy += deacc;
//                if (dy > 0) {
//                    dy = 0;
//                }
//            }
//        }
//        if (down) {
//            dy += acc;
//            if (dy > maxSpeed) {
//                dy = maxSpeed;
//            }
//        } else {
//            if (dy > 0) {
//                dy -= deacc;
//                if (dy < 0) {
//                    dy = 0;
//                }
//            }
//        }
//        if (left) {
//            dx -= acc;
//            if (dx < -maxSpeed) {
//                dx = -maxSpeed;
//            }
//        } else {
//            if (dx < 0) {
//                dx += deacc;
//                if (dx > 0) {
//                    dx = 0;
//                }
//            }
//        }
//        if (right) {
//            dx += acc;
//            if (dx > maxSpeed) {
//                dx = maxSpeed;
//            }
//        } else {
//            if (dx > 0) {
//                dx -= deacc;
//                if (dx < 0) {
//                    dx = 0;
//                }
//            }
//        }
    }

    public void target(Entity e) {
        this.e = e;
        collisionCam.getPos().x = e.getPos().x - GameStateManager.gp.screenWidth / 2.0f;
        collisionCam.getPos().y = e.getPos().y - GameStateManager.gp.screenHeight / 2.0f;
        Vector2f.setWorldVar(collisionCam.getPos().x, collisionCam.getPos().y);
//        Vector2f.setWorldVar(e.getWorldX(), e.getWorldY());
//        getPos().setVector(e.getPos().getWorldVar());
        if(e != null) {
            speed = e.getSpeed();
        } else {
            speed = 8;
        }
    }

    public void setSpeed(int speed) {this.speed = speed; }

    public void input(MouseHandler mouse, KeyHandler key) {

        if (e != null) {
            if (key.upPressed) {
                up = true;
            } else {
                up = false;
            }
            if (key.downPressed) {
                down = true;
            } else {
                down = false;
            }
            if (key.leftPressed) {
                left = true;
            } else {
                left = false;
            }
            if (key.rightPressed) {
                right = true;
            } else {
                right = false;
            }
        } else {
//            if (!e.yCol) {
//                if (collisionCam.getPos().y + collisionCam.getHeight() / 2 + dy > e.getPos().y + e.getSize() / 2 + e.getDy() + 2) {
//                    up = true;
//                    down = false;
//                } else if (collisionCam.getPos().y + collisionCam.getHeight() / 2 + dy < e.getPos().y + e.getSize() / 2 + e.getDy() - 2) {
//                    down = true;
//                    up = false;
//                } else {
//                    dy = 0;
//                    up = false;
//                    down = false;
//                }
//            }
//
//            if (!e.xCol) {
//                if (collisionCam.getPos().x + collisionCam.getWidth() / 2  + dx > e.getPos().x + e.getSize() / 2 + e.getDx() + 2) {
//                    left = true;
//                    right = false;
//                } else if (collisionCam.getPos().x + collisionCam.getWidth() / 2 + dx < e.getPos().x + e.getSize() / 2 + e.getDx() - 2) {
//                    right = true;
//                    left = false;
//                } else {
//                    dx = 0;
//                    right = false;
//                    left = false;
//                }
//            }
        }
    }

}

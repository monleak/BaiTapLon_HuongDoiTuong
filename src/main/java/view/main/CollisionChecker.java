package view.main;

import states.PlayState;
import view.entity.Entity;

/**
 * @deprecated Thay bằng TileColision
 */
public class CollisionChecker {

    GamePanel gp;
    PlayState ps;

    public CollisionChecker (GamePanel gp, PlayState ps) {
        this.gp = gp; this.ps = ps;
    }

    public void checkTile (Entity entity) {
//        int entityLeftWordX = entity.getWorldX() + entity.solidArea.x;
//        int entityRightWordX = entity.getWorldX() + entity.solidArea.x + entity.solidArea.width ;
//        int entityTopWordY = entity.getWorldY() + entity.solidArea.y;
//        int entityBottomWorldY = entity.getWorldY()  + entity.solidArea.y + entity.solidArea.height;
//
//        int entityLeftCol = entityLeftWordX / gp.titleSize;
//        int entityRightCol = entityRightWordX / gp.titleSize;
//        int entityTopRow = entityTopWordY / gp.titleSize;
//        int entityBottomRow = entityBottomWorldY / gp.titleSize;
//
//        int tileNum1, tileNum2;
//
//        // NOTE: when set view.entity.collisionOn = true
//        switch (entity.direction) {
////            case RIGHT:
////                entityRightCol = (entityRightWordX + entity.getSpeed()) / gp.titleSize;
////                tileNum1 = ps.tileM.mapFileNum [entityRightCol][entityTopRow];
////                tileNum2 = ps.tileM.mapFileNum [entityRightCol][entityBottomRow];
////                if (ps.tileM.tiles[tileNum1].collision || ps.tileM.tiles[tileNum2].collision) {
////                    entity.collisionOn = true;
////                }
////                break;
////            case LEFT:
////                entityLeftCol = (entityLeftWordX - entity.getSpeed()) / gp.titleSize;
////                tileNum1 = ps.tileM.mapFileNum [entityLeftCol][entityTopRow];
////                tileNum2 = ps.tileM.mapFileNum [entityLeftCol][entityBottomRow];
////                if (ps.tileM.tiles[tileNum1].collision || ps.tileM.tiles[tileNum2].collision) {
////                    entity.collisionOn = true;
////                }
////                break;
////            case UP:
////                entityTopRow = (entityTopWordY - entity.getSpeed()) / gp.titleSize;
////                tileNum1 = ps.tileM.mapFileNum [entityLeftCol][entityTopRow];
////                tileNum2 = ps.tileM.mapFileNum [entityRightCol][entityTopRow];
////                if (ps.tileM.tiles[tileNum1].collision || ps.tileM.tiles[tileNum2].collision) {
////                    entity.collisionOn = true;
////                }
////                break;
////            case DOWN:
////                entityBottomRow = (entityBottomWorldY + entity.getSpeed()) / gp.titleSize;
////                tileNum1 = ps.tileM.mapFileNum [entityLeftCol][entityBottomRow];
////                tileNum2 = ps.tileM.mapFileNum [entityRightCol][entityBottomRow];
////                if (ps.tileM.tiles[tileNum1].collision || ps.tileM.tiles[tileNum2].collision) {
////                    entity.collisionOn = true;
////                }
//                // TEST
////      //          System.out.println( "1: " + (gp.tileM.tiles[tileNum1].collision || gp.tileM.tiles[tileNum2].collision) + " " + entityLeftCol + " "  + entityRightCol + " "  + entityBottomRow);
////        //        System.out.println("2: " + entityBottomWorldY + " " + gp.titleSize );
////                break;
////            default: break;
//        }
    }

    public int checkObject (Entity entity, boolean player) {
        int index = 999;

        for (int i = 0; i < ps.obj.length; i++) {
            if(ps.obj[i] != null) {

                if(entity.getBounds().collides(ps.obj[i].getBounds())) {
                    index = i;
//                    System.out.println(i);
                    break;
                }

                // Get entity's solid area position
//                entity.solidArea.x = entity.getWorldX() + entity.solidArea.x;
//                entity.solidArea.y = entity.getWorldY() + entity.solidArea.y;
                // Get object's solid area position
//                ps.obj[i].solidArea.x = ps.obj[i].getWorldX() + ps.obj[i].solidArea.x;
//                ps.obj[i].solidArea.y = ps.obj[i].getWorldY() + ps.obj[i].solidArea.y;

//                switch (entity.direction) {
//                    case UP:
//                        entity.solidArea.y -= entity.getSpeed();
//                        if(entity.solidArea.intersects(ps.obj[i].solidArea)) {
//                            System.out.println("Up collision");
//                            if(ps.obj[i].collision) {
//                                entity.collisionOn = true;
//                            }
//                            if(player) {
//                                // NOTE: non player characters cannot pickup object
//                                index = i;
//                            }
//                        }
//                        break;
//                    case DOWN:
//                        entity.solidArea.y += entity.getSpeed();
//                        if(entity.solidArea.intersects(ps.obj[i].solidArea)) {
////                            System.out.println("Down collision");
//                            if(ps.obj[i].collision) {
//                                entity.collisionOn = true;
//                            }
//                            if(player) {
//                                // NOTE: non player characters cannot pick up object
//                                index = i;
//                            }
//                        }
//                        break;
//                    case LEFT:
//                        entity.solidArea.x -= entity.getSpeed();
//                        if(entity.solidArea.intersects(ps.obj[i].solidArea)) {
////                            System.out.println("Left collision");
//                            if(ps.obj[i].collision) {
//                                entity.collisionOn = true;
//                            }
//                            if(player) {
//                                // NOTE: non player characters cannot pickup object
//                                index = i;
//                            }
//                        }
//                        break;
//                    case RIGHT:
//                        entity.solidArea.x += entity.getSpeed();
//                        if(entity.solidArea.intersects(ps.obj[i].solidArea)) {
////                            System.out.println("Right collision");
//                            if(ps.obj[i].collision) {
//                                entity.collisionOn = true;
//                            }
//                            if(player) {
//                                // NOTE: non player characters cannot pickup object
//                                index = i;
//                            }
//                        }
//                        break;
//                }
//                entity.solidArea.x = entity.solidAreaDefaultX;
//                entity.solidArea.y = entity.solidAreaDefaultY;
//                ps.obj[i].solidArea.x = ps.obj[i].solidAreaDefaultX;
//                ps.obj[i].solidArea.y = ps.obj[i].solidAreaDefaultY;
            }
        }
        return index;
    }

    public int checkEntity(Entity entity, Entity[] target) {
        int index = 999;

//        for (int i = 0; i < target.length; i++) {
//            if(target[i] != null) {
//                // Get entity's solid area position
//                entity.solidArea.x = entity.getWorldX() + entity.solidArea.x;
//                entity.solidArea.y = entity.getWorldY() + entity.solidArea.y;
//                // Get object's solid area position
//                target[i].solidArea.x = target[i].getWorldX() + target[i].solidArea.x;
//                target[i].solidArea.y = target[i].getWorldY() + target[i].solidArea.y;
//
//                switch (entity.direction) {
//                    case UP:
//                        entity.solidArea.y -= entity.getSpeed();
//                        if(entity.solidArea.intersects(target[i].solidArea)) {
//                                entity.collisionOn = true;
//                                index = i;
//                        }
//                        break;
//                    case DOWN:
//                        entity.solidArea.y += entity.getSpeed();
//                        if(entity.solidArea.intersects(target[i].solidArea)) {
//                                entity.collisionOn = true;
//                                index = i;
//                        }
//                        break;
//                    case LEFT:
//                        entity.solidArea.x -= entity.getSpeed();
//                        if(entity.solidArea.intersects(target[i].solidArea)) {
//                                entity.collisionOn = true;
//                                index = i;
//                        }
//                        break;
//                    case RIGHT:
//                        entity.solidArea.x += entity.getSpeed();
//                        if(entity.solidArea.intersects(target[i].solidArea)) {
//                                entity.collisionOn = true;
//                                index = i;
//                        }
//                        break;
//                }
//                entity.solidArea.x = entity.solidAreaDefaultX;
//                entity.solidArea.y = entity.solidAreaDefaultY;
//                target[i].solidArea.x = target[i].solidAreaDefaultX;
//                target[i].solidArea.y = target[i].solidAreaDefaultY;
//            }
//        }
        return index;
    }
}

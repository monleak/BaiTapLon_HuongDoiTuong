package view.effect;

import states.GameStateManager;
import states.PlayState;
import view.main.GamePanel;
import view.math.Vector2f;

import java.awt.*;

public class FocusManager {
    private GamePanel gp;
    private PlayState ps;
    private int hoveredObjId, focusedObjId;
    private boolean isNewHovered = false, isNewFocused = false;


    public FocusManager(GamePanel gp, PlayState ps) {
        this.gp = gp;
        this.ps = ps;
        this.focusedObjId = 999;
        this.hoveredObjId = 999;
    }

    public boolean isNewHovered() {
        return isNewHovered;
    }

    public boolean isNewFocused() {
        return isNewFocused;
    }

    public int getHoveredObjId() {
        return hoveredObjId;
    }

    public int getFocusedObjId() {
        return focusedObjId;
    }

    public void setNewHovered(boolean newHovered) {
        isNewHovered = newHovered;
    }

    public void setNewFocused(boolean newFocused) {
        isNewFocused = newFocused;
    }

    public void checkAndHoverObject(int nId) {
        if(hoveredObjId != nId) {
            if(hoveredObjId != 999) {
                if(ps.obj[hoveredObjId] instanceof IFocusable) {
//                    gp.obj[hoveredObjId].fch.setHovered(false);
                    IFocusable obj = (IFocusable) ps.obj[hoveredObjId];
                    obj.setHovered(false);
                }
            }
            if(nId != 999) {
                if(ps.obj[nId] instanceof IFocusable) {
//                    gp.obj[nId].fch.setHovered(true);
                    IFocusable obj = (IFocusable) ps.obj[nId];
                    obj.setHovered(true);
                }
            }
            hoveredObjId = nId;
            // to check sound play
            isNewHovered = true;
        }
    }

    public void checkAndFocusObject () {
//        if(hoveredObjId != 999) {
//            System.out.println("instance of: " + (gp.obj[hoveredObjId] instanceof IFocusable) + " " + hoveredObjId + " " + focusedObjId);
//        } else {
//            System.out.println("instance of: " + hoveredObjId + " " + focusedObjId);
//        }
        if(hoveredObjId != 999) {
            if(focusedObjId != hoveredObjId ) {
                if(focusedObjId != 999) {
//                    gp.obj[focusedObjId].fch.setFocused(false);
                    if(ps.obj[focusedObjId] instanceof IFocusable) {
                        IFocusable obj = (IFocusable) ps.obj[focusedObjId];
                        obj.setFocused(false);
                    }
                }
                focusedObjId = hoveredObjId;
                isNewFocused = true;
                System.out.println("Hover" + focusedObjId);
//                gp.obj[focusedObjId].fch.setFocused(true);
                if(ps.obj[focusedObjId] instanceof IFocusable) {
                    IFocusable obj = (IFocusable) ps.obj[focusedObjId];
                    obj.setFocused(true);
//                    System.out.println("set " + obj.getIsFocused());
                    System.out.println("checkAndFocusObject: " + "hover=" + hoveredObjId +
                            "focus=" + focusedObjId);
                }
            }
        }else {
            if(focusedObjId != 999) {
//                gp.obj[focusedObjId].fch.setFocused(false);
                if(ps.obj[focusedObjId] instanceof IFocusable) {
                    IFocusable obj = (IFocusable) ps.obj[focusedObjId];
                    obj.setFocused(false);
                }
            }
            focusedObjId = 999;
            isNewFocused = false;
        }

    }
}

package view.effect;

import controller.GameObjController;
import states.GameStateManager;
import states.PlayState;
import view.main.GamePanel;
import view.math.AABB;
import view.math.Vector2f;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class FocusManager {
    private final PlayState ps;

    private GameObjController focusedController;

    private List<GameObjController> checkList;

    public FocusManager(GamePanel gp, PlayState ps) {
        this.ps = ps;

        this.checkList = new ArrayList<>();
    }

    public List<GameObjController> getCheckList() {
        return checkList;
    }

    public void setCheckList(List<GameObjController> checkList) {
        this.checkList = checkList;
    }

    public void updateFocus (AABB myBounds) {
        for (GameObjController controller : this.checkList) {
            if ( controller.getBounds().collides(myBounds) ) {
                if (this.focusedController != null)
                    this.focusedController.getFch().setFocused(false);
                this.focusedController = controller;
                this.focusedController.getFch().setFocused(true);
                System.out.println(this.focusedController);
                break;
            } else {
                controller.getFch().setFocused(false);
            }
        }
    }

    public void updateHover (AABB myBounds) {
        for (GameObjController controller : this.checkList) {
            if ( controller.getBounds().collides(myBounds) ) {
                if (this.focusedController != null)
                    this.focusedController.getFch().setHovered(false);
                this.focusedController = controller;
                this.focusedController.getFch().setHovered(true);
            } else {
                controller.getFch().setHovered(false);
            }
        }
    }



}

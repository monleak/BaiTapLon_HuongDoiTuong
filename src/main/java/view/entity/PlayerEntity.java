package view.entity;

import controller.PlayerController;
import states.PlayState;
import view.renderer.PlayerRenderer;
import view.math.Vector2f;


import static states.GameStateManager.gp;

public class PlayerEntity extends Entity {


    public PlayerEntity(PlayState ps) {
        super(ps);
    }

    @Override
    public void create() {
        PlayerController playerController = new PlayerController(new Vector2f(10 * gp.titleSize, 10 * gp.titleSize), getPs());
        this.controller = playerController;
        this.renderer = new PlayerRenderer(playerController);
    }

}

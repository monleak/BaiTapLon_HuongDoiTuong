package view.component;

import view.math.AABB;
import view.math.Vector2f;

import java.awt.*;
import static states.GameStateManager.gp;

public class FlyUpNumberPool extends ObjectPool<FlyUpNumber>{
    public static FlyUpNumberPool instance;

    public FlyUpNumberPool () {
        super();
    }

    /**
     * Singleton
     */
    public static synchronized FlyUpNumberPool getInstance () {
        if(instance == null){
            instance = new FlyUpNumberPool();
        }
        return instance;
    }

    @Override
    protected FlyUpNumber create() {
        return new FlyUpNumber(
                0,
                Color.RED,
                new AABB(
                        new Vector2f(10*gp.titleSize, 10*gp.titleSize),
                        gp.titleSize,
                        gp.titleSize
                )
        );
    }

}

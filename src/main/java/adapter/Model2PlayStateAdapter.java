package adapter;

import controller.GameObjController;
import controller.PlayerController;
import model.Animals.Cat;
import model.Animals.Chicken;
import model.Animals.Dog;
import model.ModelState;
import states.GameStateManager;
import states.PlayState;
//import view.entity.*;
import view.effect.FocusManager;
import view.effect.IDrawable;
import view.entity.CatEntity;
import view.entity.ChickenEntity;
import view.entity.DogEntity;
import view.entity.obj.FoodTray;
import view.main.Camera;
import view.main.GamePanel;
import view.main.KeyHandler;
import view.main.MouseHandler;
import view.math.AABB;
import view.math.Vector2f;
import view.weather.EnvironmentManager;
import view.weather.wind.WindEffect;
//import view.object.OBJ_FoodTray;
//import view.object.OBJ_Key;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Model2PlayStateAdapter extends PlayState {

    private int modelStateCounter;
    private final ModelState modelState;
    private FocusManager fcm;
    private List<GameObjController> focusCheckList;
    private EnvironmentManager environmentManager;


    public Model2PlayStateAdapter(Camera camera, ModelState modelState) {
        super(camera, new Model2UIAdapter(GameStateManager.gp, modelState));
        this.modelState = modelState;

        this.fcm = new FocusManager(GameStateManager.gp, this);
        this.focusCheckList = new ArrayList<>();
        this.environmentManager = new EnvironmentManager(modelState.getTimeManager(), camera);
    }

    @Override
    public void setup () {
        super.setup();

        // create animal entity
        GamePanel gp = GameStateManager.gp;
        PlayState ps = this;
        modelState.getAnimalList().forEach(
                animal -> {
                    if (animal instanceof Chicken) {
                    this.animalEntityList.add(
                            new ChickenEntity(
                                    ps, animal
                            )
                        );
                    } else if (animal instanceof Cat) {
                        this.animalEntityList.add (
                                new CatEntity(
                                        ps, animal
                                )
                        );
                    } else if (animal instanceof Dog) {
                        this.animalEntityList.add (
                                new DogEntity(
                                        ps, animal
                                )
                        );
                    }
                }
        );
        AtomicInteger count = new AtomicInteger();
        int[] x = {8, 12, 14};
        modelState.getFoodInventoryList().forEach(
                foodInventory -> {
                    this.foodTrayList.add(
                            new FoodTray(
                                    ps,
                                    foodInventory,
                                    new Vector2f(
                                            (x[count.get()]) * gp.titleSize,
                                            15 * gp.titleSize
                                    )
                            )
                    );
                    count.getAndIncrement();
                }
        );

        // setup fch list
        this.animalEntityList.forEach(animalEntity -> {
            GameObjController controller = animalEntity.getController();
            this.focusCheckList.add(controller);
        });
        this.foodTrayList.forEach(foodTray -> {
            GameObjController controller = foodTray.getController();
            this.focusCheckList.add(controller);
        });
        this.fcm.setCheckList(this.focusCheckList);
    }

    @Override
    public void input(MouseHandler mouse, KeyHandler key, GameStateManager gsm) {
        super.input(mouse, key, gsm);

        // check focus, hover
        AABB myBound;
        if (player != null) myBound = ((PlayerController) player.getController()).getAttackBounds();
        else myBound = mouseTarget.getBounds();
        if (key.enterPressed) fcm.updateFocus(myBound);
        fcm.updateHover(myBound);
    }

    @Override
    public void update (double time, GameStateManager gsm) {

        if (isUpdatable(gsm))
            if (this.modelState != null && modelStateCounter == 60 * 2 / this.modelState.getSimulationSpeed()) {
                // TODO: ENABLE MODEL RUN
                this.modelState.run();
                modelStateCounter = 0;
            } else {
                modelStateCounter++;
            }
        super.update(time, gsm);
    }

    @Override
    public void draw(Graphics2D g2) {
        super.draw(g2);
        this.environmentManager.draw(g2);
    }
}

package adapter;

import controller.GameObjController;
import controller.PlayerController;
import model.Animals.*;
import model.ModelState;
import states.GameStateManager;
import states.PlayState;
//import view.entity.*;
import view.effect.FocusManager;
import view.effect.IDrawable;
import view.main.Camera;
import view.main.GamePanel;
import view.main.KeyHandler;
import view.main.MouseHandler;
import view.math.AABB;
import view.weather.clould.CloudEffect;
import view.weather.rain.RainEffect;
import view.weather.wind.WindEffect;
//import view.object.OBJ_FoodTray;
//import view.object.OBJ_Key;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Model2PlayStateAdapter extends PlayState {

    private int modelStateCounter;
    private final ModelState modelState;
    private FocusManager fcm;
    private List<GameObjController> focusCheckList;
    private IDrawable rainEffect;


    public Model2PlayStateAdapter(Camera camera, ModelState modelState) {
        super(camera, new Model2UIAdapter(GameStateManager.gp, modelState));
        this.modelState = modelState;

        this.fcm = new FocusManager(GameStateManager.gp, this);
        this.focusCheckList = new ArrayList<>();

        this.rainEffect = new CloudEffect(camera);
    }

//    @Override
//    public void setup () {
//        GamePanel gp = GameStateManager.gp;
//        PlayState ps = this;
//        // create key
//        obj[0] = new OBJ_Key(gp, ps);
//        obj[0].getBounds().getPos().x = 20 * gp.titleSize;
//        obj[0].getBounds().getPos().y = 20 * gp.titleSize;
//        // create food tray
//        for (int j = 1; j <  this.modelState.getFoodInventoryList().size() + 1; j++) {
//            int i = j - 1;
//            OBJ_FoodTray foodTray = new OBJ_FoodTray(gp, ps, this.modelState.getFoodInventoryList().get(i));
//            obj[j] = foodTray;
//            obj[j].getPos().x = ((int)(30f * gp.titleSize));
//            obj[j].getPos().y = ((int)((10f + 4 * j) * gp.titleSize));
//            // foodTrays để con vật tìm loại thức ăn nó cần
//            foodTrays.add(foodTray);
//        }
//
//        // create animal entity
//        int len = foodTrays.size() + 1;;
//        List<Animal> animals = this.modelState.getAnimalList();
//        for (int j = len; j < animals.size() + len; j++) {
//            int i = j - len;
//            if (animals.get(i) instanceof Chicken) {
//                obj[j] = new ChickenEntity(gp, ps, animals.get(i));
//                obj[j].getBounds().getPos().x = ((int)(20f * gp.titleSize));
//                obj[j].getBounds().getPos().y = ((int)((10f + i) * gp.titleSize));
//                ChickenEntity chicken = (ChickenEntity) obj[j];
////                    chicken.goTo(player);
//            } else if(animals.get(i) instanceof Dog){
//                obj[j] = new DogEntity(gp, ps, animals.get(i));
//                obj[j].getBounds().getPos().x = ((int)(10f * gp.titleSize));
//                obj[j].getBounds().getPos().y = ((int)((10f + i) * gp.titleSize));
//            }else if(animals.get(i) instanceof Cat){
//                obj[j] = new CatEntity(gp, ps, animals.get(i));
//                obj[j].getBounds().getPos().x = ((int)(10f * gp.titleSize));
//                obj[j].getBounds().getPos().y = ((int)((10f + i) * gp.titleSize));
//            }else if(animals.get(i) instanceof Manatee){
//                obj[j] = new ManateeEntity(gp, ps, animals.get(i));
//                obj[j].getBounds().getPos().x = ((int)(37f * gp.titleSize));
//                obj[j].getBounds().getPos().y = ((int)((8f + i) * gp.titleSize));
//            }else if(animals.get(i) instanceof Kangaroo){
//                obj[j] = new KangarooEntity(gp, ps, animals.get(i));
//                obj[j].getBounds().getPos().x = ((int)(20f * gp.titleSize));
//                obj[j].getBounds().getPos().y = ((int)((10f + i) * gp.titleSize));
//            }
//            else{
//                obj[j] = new FoxEntity(gp, ps, animals.get(i));
//                obj[j].getPos().x = ((int)(10f * gp.titleSize));
//                obj[j].getPos().y = ((int)((10f + i) * gp.titleSize));
//            }
//        }
//
//        playMusic(0);
//    }

    @Override
    public void setup () {
        super.setup();

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
//                this.modelState.run();
                modelStateCounter = 0;
            } else {
                modelStateCounter++;
            }
        super.update(time, gsm);

//        this.rainEffect.update();
    }

    @Override
    public void draw(Graphics2D g2) {
        super.draw(g2);
        this.rainEffect.draw(g2);
    }
}

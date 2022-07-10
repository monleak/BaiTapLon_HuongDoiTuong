package states;

import model.Animals.Animal;
import model.Animals.*;
import view.entity.*;
import view.main.*;
import view.object.OBJ_FoodTray;
import view.object.OBJ_Key;
import view.title.TileManager;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * PlayState
 * @brief trạng thái của màn hình chơi game.
 *
 */
public class PlayState extends GameState {

    public TileManager tileM;               // draw ban do
    public UI ui;                           // hien thi text tren mh
    public CollisionChecker cChecker;       // check va cham
    public AssetSetter assetSetter;         // them cac con vat...
    Sound music = new Sound();              // nhac nen
    Sound se = new Sound();                 // am thanh khi cham vao con vat
    public GameObject[] obj = new GameObject[100];    // Danh sach
    public List<OBJ_FoodTray> foodTrays = new ArrayList<>();

    public Player player;
    private int modelStateCounter;

    public PlayState (Camera camera) {
        // init
        super(camera);

        tileM       = new TileManager(GameStateManager.gp, this, camera);
        ui          = new UI(GameStateManager.gp);
        cChecker    = new CollisionChecker(GameStateManager.gp, this);
        assetSetter = new AssetSetter(GameStateManager.gp, this);

        player      = new Player(GameStateManager.gp, this, camera);

        camera.target(player);
    }

    /**
     * {@inheritDoc}
     * <ul>
     *     <li>Nếu truyền thêm modelState vào constructor GameState thì tạo con vật dựa trên ModelState </li>
     *     <li>Ngược lại, tạo con vật dựa trên AssetsSetter</li>
     * </ul>
     */
    @Override
    public void setup () {
        GamePanel gp = GameStateManager.gp;
        PlayState ps = this;
        if (GameStateManager.modelState == null)
            assetSetter.setObject();
        else {
            // create key
            obj[0] = new OBJ_Key(gp, ps);
            obj[0].getBounds().getPos().x = 20 * gp.titleSize;
            obj[0].getBounds().getPos().y = 20 * gp.titleSize;
            // create food tray
            for (int j = 1; j <  GameStateManager.modelState.getFoodInventoryList().size() + 1; j++) {
                int i = j - 1;
                OBJ_FoodTray foodTray = new OBJ_FoodTray(gp, ps, GameStateManager.modelState.getFoodInventoryList().get(i));
                obj[j] = foodTray;
                obj[j].getPos().x = ((int)(30f * gp.titleSize));
                obj[j].getPos().y = ((int)((10f + 4 * j) * gp.titleSize));
                // foodTrays để con vật tìm loại thức ăn nó cần
                foodTrays.add(foodTray);
            }

            // create animal entity
            int len = foodTrays.size() + 1;;
            List<Animal> animals = GameStateManager.modelState.getAnimalList();
            for (int j = len; j < animals.size() + len; j++) {
                int i = j - len;
                if (animals.get(i) instanceof Chicken) {
                    obj[j] = new ChickenEntity(gp, ps, animals.get(i));
                    obj[j].getBounds().getPos().x = ((int)(20f * gp.titleSize));
                    obj[j].getBounds().getPos().y = ((int)((10f + i) * gp.titleSize));
                    ChickenEntity chicken = (ChickenEntity) obj[j];
//                    chicken.follow(player);
                } else if(animals.get(i) instanceof Dog){
                    obj[j] = new DogEntity(gp, ps, animals.get(i));
                    obj[j].getBounds().getPos().x = ((int)(10f * gp.titleSize));
                    obj[j].getBounds().getPos().y = ((int)((10f + i) * gp.titleSize));
                }else if(animals.get(i) instanceof Cat){
                    obj[j] = new CatEntity(gp, ps, animals.get(i));
                    obj[j].getBounds().getPos().x = ((int)(10f * gp.titleSize));
                    obj[j].getBounds().getPos().y = ((int)((10f + i) * gp.titleSize));
                }else if(animals.get(i) instanceof Manatee){
                    obj[j] = new ManateeEntity(gp, ps, animals.get(i));
                    obj[j].getBounds().getPos().x = ((int)(37f * gp.titleSize));
                    obj[j].getBounds().getPos().y = ((int)((8f + i) * gp.titleSize));
                }else if(animals.get(i) instanceof Kangaroo){
                    obj[j] = new KangarooEntity(gp, ps, animals.get(i));
                    obj[j].getBounds().getPos().x = ((int)(20f * gp.titleSize));
                    obj[j].getBounds().getPos().y = ((int)((10f + i) * gp.titleSize));
                }
                else{
                    obj[j] = new FoxEntity(gp, ps, animals.get(i));
                    obj[j].getPos().x = ((int)(10f * gp.titleSize));
                    obj[j].getPos().y = ((int)((10f + i) * gp.titleSize));
                }
            }

        }
        playMusic(0);
//        camera.target(obj[1]);
    }

    public boolean isUpdatable(GameStateManager gsm) {
        return !gsm.isStateActive(GameStateManager.PAUSE) && !gsm.isStateActive(GameStateManager.HELP);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void update(double time, GameStateManager gsm) {

        if(isUpdatable(gsm)) {
            player.update();
            ui.update();

            if (GameStateManager.modelState != null && modelStateCounter == 100 / GameStateManager.modelState.getSimulationSpeed()) {
                GameStateManager.modelState.run();
                modelStateCounter = 0;
            } else {
                modelStateCounter++;
            }

            for (int i = 0; i < obj.length; i++) {
                if(obj[i] instanceof Entity ) {
                    ((Entity) obj[i]).update();
                }
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void input(MouseHandler mouse, KeyHandler key, GameStateManager gsm) {
    if (isUpdatable(gsm)) {
            player.input(mouse, key);

            if (key.pPressed) {
                gsm.addAndPop(GameStateManager.PAUSE);
            }

            if (key.hPressed)
                gsm.addAndPop(GameStateManager.HELP);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void draw(Graphics2D g2) {

        tileM.draw(g2);

        for (GameObject superObject : obj) {
            if (superObject != null) {
                superObject.draw(g2);
            }
        }

        player.draw(g2);
        ui.draw(g2);

    }

    /**
     * Sound manager
     * @param i
     */
    public void playMusic (int i) {
//        music.setFile(i);
//        music.play();
//        music.loop();
    }
    public void stopMusic () {
        music.stop();
    }
    public void playSE(int i) {
        se.setFile(i);
        se.play();
    }
}

package states;

import view.component.GotoTarget;
import view.component.MouseTarget;
import view.entity.AnimalEntity;
import view.entity.PlayerEntity;
import view.entity.obj.FoodTray;
import view.main.*;
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
//    public GameObject[] obj = new GameObject[100];    // Danh sach
//    public List<OBJ_FoodTray> foodTrays = new ArrayList<>();

    public PlayerEntity player;
    public List<AnimalEntity> animalEntityList;
    public List<FoodTray> foodTrayList;
    public MouseTarget mouseTarget;
    public GotoTarget gotoTarget;


    public PlayState (Camera camera) {
        // init
        this(camera, new UI(GameStateManager.gp));
    }

    public PlayState (Camera camera, UI ui) {
        super(camera);

        this.ui = ui;

        tileM       = new TileManager(GameStateManager.gp, this, camera);
        cChecker    = new CollisionChecker(GameStateManager.gp, this);
        assetSetter = new AssetSetter(GameStateManager.gp, this);

        player      = new PlayerEntity(this);
        animalEntityList = new ArrayList<>();

        animalEntityList.add(new AnimalEntity(this));
        gotoTarget = new GotoTarget();
        mouseTarget = new MouseTarget();
        this.foodTrayList = new ArrayList<>();
        this.foodTrayList.add(new FoodTray(this));

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
        // default is use asset setter
        // but adapter us model state
        assetSetter.setObject();

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
            mouseTarget.update();
            gotoTarget.update();

            for (AnimalEntity animal: animalEntityList) {
                animal.update();
            }
            for (FoodTray foodTray: foodTrayList) {
                foodTray.update();
            }

//            for (GameObject gameObject : obj) {
//                if (gameObject instanceof Entity) {
//                    ((Entity) gameObject).update();
//                }
//            }

        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void input(MouseHandler mouse, KeyHandler key, GameStateManager gsm) {
        if (isUpdatable(gsm)) {

            for (AnimalEntity animal: animalEntityList) {
                animal.input(key, mouse);
            }
            for (FoodTray foodTray: foodTrayList) {
                foodTray.input(key, mouse);
            }

            player.input(key, mouse);
            mouseTarget.input(key, mouse);
            gotoTarget.input(key, mouse);

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

        for (AnimalEntity animal: animalEntityList) {
            animal.draw(g2);
        }
        for (FoodTray foodTray: foodTrayList) {
            foodTray.draw(g2);
        }
//        for (GameObject superObject : obj) {
//            if (superObject != null) {
//                superObject.draw(g2);
//            }
//        }
//
        mouseTarget.draw(g2);
        gotoTarget.draw(g2);
        player.draw(g2);
//        ui.draw(g2);
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

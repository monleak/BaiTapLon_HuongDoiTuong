package states;

import model.Animals.Animal;
import model.Animals.*;
import view.entity.*;
import view.main.*;
import view.object.OBJ_Key;
import view.title.TileManager;

import java.awt.*;
import java.util.List;

import static basic.Params.*;

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
            List<Animal> animals = GameStateManager.modelState.getAnimalList();
            for (int i = 0; i < animals.size(); i++) {
                if (animals.get(i) instanceof Chicken) {
                    obj[i] = new ChickenEntity(gp, ps, animals.get(i));
                    obj[i].getBounds().getPos().x = CHUONG_GA1[0];
                    obj[i].getBounds().getPos().y = CHUONG_GA1[1];
                } else if(animals.get(i) instanceof Dog){
                    obj[i] = new DogEntity(gp, ps, animals.get(i));
//                    obj[i].getBounds().getPos().x = HOME[0];
//                    obj[i].getBounds().getPos().y = HOME[1];

                    obj[i].getBounds().getPos().x = CHUONG_KANGAROO[0];
                    obj[i].getBounds().getPos().y = CHUONG_KANGAROO[1];
                }else if(animals.get(i) instanceof Cat){
                    obj[i] = new CatEntity(gp, ps, animals.get(i));
                    obj[i].getBounds().getPos().x = HOME[0];
                    obj[i].getBounds().getPos().y = HOME[1];
                }else if(animals.get(i) instanceof Manatee){
                    obj[i] = new ManateeEntity(gp, ps, animals.get(i));
                    obj[i].getBounds().getPos().x = CHUONG_MANATEE[0];
                    obj[i].getBounds().getPos().y = CHUONG_MANATEE[1];
                }else if(animals.get(i) instanceof Kangaroo){
                    obj[i] = new KangarooEntity(gp, ps, animals.get(i));
                    obj[i].getBounds().getPos().x = CHUONG_KANGAROO[0];
                    obj[i].getBounds().getPos().y = CHUONG_KANGAROO[1];
                }
                else{
                    obj[i] = new FoxEntity(gp, ps, animals.get(i));
                    obj[i].getPos().x = HOME[0];
                    obj[i].getPos().y = HOME[1];
                }
            }
            obj[10] = new OBJ_Key(gp, ps);
            obj[10].getBounds().getPos().x = 20 * gp.titleSize;
            obj[10].getBounds().getPos().y = 20 * gp.titleSize;
        }
        playMusic(0);
//        camera.target(obj[1]);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update(double time, GameStateManager gsm) {

        if(!gsm.isStateActive(GameStateManager.PAUSE)) {
            player.update();
            ui.update();

            if (GameStateManager.modelState != null && modelStateCounter == 10 / GameStateManager.modelState.getSimulationSpeed()) {
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
        if(!gsm.isStateActive(GameStateManager.PAUSE)){
            player.input(mouse, key);

            if (key.pPressed) {
                gsm.addAndPop(GameStateManager.PAUSE);
            }
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
        music.setFile(i);
        music.play();
        music.loop();
    }
    public void stopMusic () {
        music.stop();
    }
    public void playSE(int i) {
        se.setFile(i);
        se.play();
    }
}

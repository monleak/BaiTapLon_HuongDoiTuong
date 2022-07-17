package controller;

import behavior.AnimalBehavior;
import model.Activities.DrinkActivity;
import model.Activities.EatActivity;
import model.Animals.Animal;
import states.PlayState;
import view.entity.obj.FoodTray;
import view.math.Vector2f;

import java.util.Random;
import java.util.concurrent.atomic.AtomicReference;

import static states.GameStateManager.gp;

public class CatController extends AnimalController{
    public CatController(Vector2f pos, PlayState ps, Animal model) {
        super(pos, ps, model);
    }

    @Override
    public void onBehaviorChange(AnimalBehavior behavior) {
        switch (behavior) {
            case PLAY:
                this.getPathManager().unfollow();
                this.getMovement().setSpeed(4);
                Random r = new Random();
                int[] posX = {14, 19, 14, 25};
                int[] posY = {24, 29, 36, 10};
                int pos = r.nextInt(4) ;
                int dx = r.nextInt(4);
                int dy = r.nextInt(4);
                this.getPathManager().goTo((posX[pos] + dx) * gp.titleSize, (posY[pos] + dy) * gp.titleSize);
                this.getPathManager().setToGoalListener(actionEvent -> {
                    this.getMovement().setSpeed(2);
                    this.getMovement().move(this.getDirection(), this.getMovement().getSpeed());
                });
                break;
            case GO_TO_DRINK:
                this.getPathManager().unfollow();
                this.getMovement().setSpeed(4);
                this.getPathManager().goTo(39 * gp.titleSize, 14 * gp.titleSize);
                this.getPathManager().setToGoalListener(actionEvent -> {
                    System.out.println("Goal!!!!!!!!!!!!!!!!");
                    setPrevBeh(AnimalBehavior.DRINK);
                    ((DrinkActivity) this.getModel().getActivity()).onPrepareDone( this.getModel().getActivity());
                });
                break;
            case SIT:
                this.getMovement().setSpeed(0);
                this.getPathManager().unfollow();
                break;
            case GO_TO_EAT:
                this.getPathManager().unfollow();
                this.getMovement().setSpeed(4);
                AtomicReference<FoodTray> myFoodTray = new AtomicReference<>();
                ps.foodTrayList.forEach(foodTray -> {
                    if (foodTray.getInventory().getFood().equals(this.getModel().getNeededFood().getFood())) {
                        myFoodTray.set(foodTray);
                    }
                });
                if (myFoodTray.get() != null ) {
                    this.getPathManager().goTo(
                            (int) myFoodTray.get().getController().getPos().getX(),
                            (int) myFoodTray.get().getController().getPos().getY()
                    );
                    this.getPathManager().setToGoalListener(actionEvent -> {
                        System.out.println("Goal!!!!!!!!!!!!!!!!");
                        this.getMovement().setSpeed(0);
                        setPrevBeh(AnimalBehavior.EAT);
                        ((EatActivity) this.getModel().getActivity()).onPrepareDone( this.getModel().getActivity());
                    });
                } else {
                    throw new Error("Not found food tray!");
                }

                break;
            case DRINK:
            case EAT:
                this.getMovement().setSpeed(0);
                break;
        }
    }
}

package view.entity;

import model.Activities.EatActivity;
import model.Animals.Animal;
import model.Food;
import org.jetbrains.annotations.NotNull;
import states.PlayState;
import view.ai.PathFinder;
import view.effect.FocusableHandler;
import view.effect.IFocusable;
import view.main.GamePanel;
import view.object.OBJ_FoodTray;
import view.title.TileCollision;
import view.utils.Direction;

import java.awt.*;
import java.util.concurrent.atomic.AtomicBoolean;

public abstract class AnimalEntity extends Entity implements IFocusable {

    // Kết tập animal
    protected Animal animal;
    public FocusableHandler fch;

    public AnimalEntity(GamePanel gp, PlayState ps) {
        super(gp, ps);

        fch = new FocusableHandler(ps, this);
    }

    /**
     * Lấy ra đối tượng animal
     */
    public Animal getAnimal() {
        return animal;
    }

    @Override
    public void setHovered(boolean hovered) {
        this.fch.setHovered(hovered);
    }
    @Override
    public void setFocused(boolean focused) {
        this.fch.setFocused(focused);
    }
    @Override
    public boolean getIsHovered() {
        return this.fch.getIsHovered();
    }
    @Override
    public boolean getIsFocused() {
        return this.fch.getIsFocused();
    }

    public void goToFoodTray () {
        OBJ_FoodTray foodTray = null;
        for (int i = 0; i < ps.foodTrays.size(); i++) {
            if (
                    ps.foodTrays.get(i).getFoodInventory().getFood().getName().
                            equals(this.animal.getNeededFood().getFood().getName())
            ) {
                foodTray = ps.foodTrays.get(i);
                break;
            }
        }
        
        if (foodTray != null) {
            goTo(foodTray);
        } else {
            throw new Error ("Not found foodTray!");
        }
    }

    /**
     * {@inheritDoc}
     *
     * AnimalEntity.update:
     * <ul>
     *     <li>
     *         Set action
     *     </li>
     *     <li>
     *         Check collision tiles
     *     </li>
     * </ul>
     */
    @Override
    public void update() {
        super.update();
        setAction();

//        if (this.animal.getActivity() instanceof EatActivity) {
//
//        }
    }

    /**
     * {@inheritDoc}
     *
     * AnimalEntity.draw:
     * <ul>
     *     <li>
     *         Vẽ tam giác màu đỏ trên đầu con vật khi focus.
     *     </li>
     * </ul>
     */
    @Override
    public void draw(@NotNull Graphics2D g2) {
        super.draw(g2);
        fch.draw(g2, this);

        pathFinder.draw(g2);
    }
}

package behavior;

import view.utils.Direction;

import java.util.Objects;

public class DirectionBehaviorKey {
    private Direction direction;
    private AnimalBehavior behavior;

    public DirectionBehaviorKey(Direction direction, AnimalBehavior behavior) {
        this.direction = direction;
        this.behavior = behavior;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DirectionBehaviorKey that = (DirectionBehaviorKey) o;
        return direction == that.direction && behavior == that.behavior;
    }

    @Override
    public int hashCode() {
        return Objects.hash(direction, behavior);
    }
}

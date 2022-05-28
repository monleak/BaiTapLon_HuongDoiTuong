package model;

import java.util.Objects;

public class Food {
    private String name;
    private int calo;

    public Food(String name, int calo) {
        this.name = name;
        this.calo = calo;
    }

    public Food(String name) {
        this.name = name;
        this.calo = 1;
    }

    public String getName() {
        return name;
    }

    public int getCalo() {
        return calo;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof  Food)
            return Objects.equals(this.name, ((Food) obj).name);
        return false;
    }
}

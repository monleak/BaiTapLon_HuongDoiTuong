package model;

import model.Animals.Animal;
import model.Animals.Cat;

public class ModelTest {
    public static void main (String[] args) {
        ModelState modelState = new ModelState(10);

        modelState.getAnimalList().add(
                new Cat()
        );

        // gia lap update (60 times per second)
        long lastTime = System.nanoTime();
        final double ns = 1000000000.0 / 2.0;
        double delta = 0;
        // gia lap ngay thang nam
        int dayCounter = 0;
        int hourCounter = 0;
        int minuteCounter = 0;

        while (true) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while(delta >= 1){
                // the code you want to be executed

                // Ham update
                modelState.run();

                System.out.println("day: " + dayCounter + ", hour: " + hourCounter + ", minute: " + minuteCounter);
                // logic cap nhat ngay thang nam
                minuteCounter++;
                if (minuteCounter == 60) {
                    minuteCounter = 0;
                    hourCounter++;
                    if (hourCounter == 24) {
                        dayCounter++;
                        hourCounter = 0;
                        if (dayCounter == 30) {
                            dayCounter = 0;
                        }
                    }
                }

                delta--;
            }
        }
    }
}

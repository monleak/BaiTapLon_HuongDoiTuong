package action;

public abstract class Action {

    private final int hours;


    public Action(int hours) {
        this.hours = hours;
    }

    public int getHours() {
        return hours;
    }

    public void perform (/* Animal a */) {
        System.out.println("Perform: " + this.toString());
        // TODO: update animal state
        // NOTE: Heavy calculate
        double a = 0;
        for (int i = 0; i < 100; i++) {
            a += Math.exp(i);
            for (int j = 0; j < 10000; j++) {
                a -= j;
                for (int k = 1; k < 100; k++) {
                    a -= k;
                }
                // test
                Test2.commonNumber++;
            }
        }
        System.out.println(a);
    }

}

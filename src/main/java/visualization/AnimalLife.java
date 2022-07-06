package visualization;

import model.Activities.*;
import model.Animals.Cat;
import model.Animals.Chicken;
import org.math.plot.Plot2DPanel;
import org.math.plot.plotObjects.BaseLabel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AnimalLife {
    static double[] x = {1, 2, 3, 4};
    static double[] y = {5, 6, 7, 9};
    static double[] hp;
    static double[] calo;
    static double[] sleep;
    static double[] water;
    static double[] hours = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24};
    static double[] eatAct, drinkAct, playAct, sleepAct;

    public static void main (String[] args) {
        setData();

        Plot2DPanel plot = new Plot2DPanel();
        Plot2DPanel plot2 = new Plot2DPanel();
        Plot2DPanel plot3 = new Plot2DPanel();
        Plot2DPanel plot4 = new Plot2DPanel();
        Plot2DPanel plot5 = new Plot2DPanel();

        plot.setAxisLabel(0, "hours");
        plot.setToolTipText("HP");
        double[] d = {1, 3, 4};

        // add title
        BaseLabel title1 = new BaseLabel("HP", Color.RED, 0.5, 1.1);
        title1.setFont(new Font("Courier", Font.BOLD, 20));
        plot.addPlotable(title1);
        BaseLabel title2 = new BaseLabel("Calo", Color.RED, 0.5, 1.1);
        title2.setFont(new Font("Courier", Font.BOLD, 20));
        plot2.addPlotable(title2);
        BaseLabel title3 = new BaseLabel("Sleep", Color.RED, 0.5, 1.1);
        title3.setFont(new Font("Courier", Font.BOLD, 20));
        plot3.addPlotable(title3);
        BaseLabel title4 = new BaseLabel("Water", Color.RED, 0.5, 1.1);
        title4.setFont(new Font("Courier", Font.BOLD, 20));
        plot4.addPlotable(title4);
        BaseLabel title5 = new BaseLabel("Activity", Color.RED, 0.5, 1.1);
        title5.setFont(new Font("Courier", Font.BOLD, 20));
        plot5.addPlotable(title5);

        // add a line plot to the PlotPanel
        plot.addLinePlot("HP", hours, hp);
        plot2.addLinePlot("Calo", hours, calo);
        plot3.addLinePlot("Sleep", hours, sleep);
        plot4.addLinePlot("Water", hours, water);
//        plot5.addBarPlot("Activity", hours);

        if (eatAct.length > 0)
            plot5.addHistogramPlot("Eat", Color.YELLOW, eatAct, 50);
        if (drinkAct.length > 0)
            plot5.addHistogramPlot("Drink", Color.BLUE, drinkAct, 50);
        if (playAct.length > 0)
            plot5.addHistogramPlot("Play", Color.RED, playAct, 50);
        if (sleepAct.length > 0)
            plot5.addHistogramPlot("Sleep", Color.GRAY, sleepAct, 50);

        plot5.addLegend("SOUTH");

        // put the PlotPanel in a JFrame, as a JPanel
        JFrame frame = new JFrame( "Animal life");
//        frame.setContentPane(plot);

        frame.setLayout(new GridLayout(2, 2));
        frame.add(plot);
        frame.add(plot2);
        frame.add(plot3);
        frame.add(plot4);
        frame.add(plot5);

        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1280, 720);
    }

    public static void setData() {

        Chicken cat = new Chicken();

        try {
            hp      = new double[25];
            sleep   = new double[25];
            calo    = new double[25];
            water   = new double[25];

            List<Double> e = new ArrayList<>();
            List<Double> d = new ArrayList<>();
            List<Double> p = new ArrayList<>();
            List<Double> s = new ArrayList<>();

            for (double h : hours) {
                int hour = (int) h;
                cat.life(0, hour, 0);
                AnimalLife.hp[hour] = cat.getHP();
                AnimalLife.sleep[hour] = cat.getSleep();
                AnimalLife.calo[hour] = cat.getCalo();
                AnimalLife.water[hour] = cat.getWater();
                Activity activity = cat.getActivity();
                if (activity instanceof EatActivity) {
                    e.add( h );
                } else if (activity instanceof DrinkActivity ) {
                    d.add( h );
                } else if (activity instanceof PlayActivity) {
                    p.add( h );
                } else if (activity instanceof SleepActivity) {
                    s.add( h );
                }
            }

            eatAct      = e.stream().mapToDouble(Double::doubleValue).toArray();
            drinkAct    = d.stream().mapToDouble(Double::doubleValue).toArray();
            playAct     = p.stream().mapToDouble(Double::doubleValue).toArray();
            sleepAct    = s.stream().mapToDouble(Double::doubleValue).toArray();

            System.out.println("\neat: " + Arrays.toString(eatAct) + "\n drink: " + Arrays.toString(drinkAct) + "\n play: " + Arrays.toString(playAct) + "\n sleep: " + Arrays.toString(sleepAct));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

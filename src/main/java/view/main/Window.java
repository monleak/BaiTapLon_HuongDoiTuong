package view.main;


import javax.swing.*;
import java.awt.image.BufferStrategy;

/**
 * @deprecated: Ko dùng nữa.
 */
public class Window extends JFrame {
    public static final long serialVersionUID = 1L;

    private BufferStrategy bs;
    private GamePanel gp;

    public Window() {
        setTitle("Decay");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setIgnoreRepaint(true);
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    public void addNotify() {
        super.addNotify();

        createBufferStrategy(2);
        bs = getBufferStrategy();

        gp = new GamePanel();
        setContentPane(gp);
    }

}

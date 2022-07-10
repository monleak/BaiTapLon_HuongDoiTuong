package view.main;

import view.component.Button;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Xử lý bàn phím.
 */
public class KeyHandler implements KeyListener {
    /**
     *  true nếu các phím đang được nhấn.
     */
    public boolean upPressed, downPressed, leftPressed, rightPressed;
    public boolean enterPressed, spacePressed, escPress;
    public boolean xPressed, pPressed, hPressed;

    // obs
    private List<Button> buttons = new ArrayList<>();
    public void addObserver(Button button) {
        this.buttons.add(button);
    }

    public void removeObserver(Button button) {
        this.buttons.remove(button);
    }

    public KeyHandler (GamePanel gp) {
        gp.addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    private void markKey (int code, boolean mark) {
        if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP)
            upPressed = mark;
        if(code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT)
            leftPressed = mark;
        if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN)
            downPressed = mark;
        if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT)
            rightPressed = mark;
        if (code == KeyEvent.VK_ENTER)
            enterPressed = mark;
        if (code == KeyEvent.VK_SPACE)
            spacePressed = mark;
        if (code == KeyEvent.VK_P)
            pPressed = mark;
        if (code == KeyEvent.VK_ESCAPE)
            escPress = mark;
        if (code == KeyEvent.VK_X)
            xPressed = mark;
        if (code == KeyEvent.VK_H) {
            hPressed = mark;
        }
    }

    /**
     * Override các method của class cha trong thư viện java swing.
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        markKey(code, true);

        for (Button button: buttons) {
            button.handlePressed(code);
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        markKey(code, false);

        for (Button button: buttons) {
            button.handleReleased(code);
        }
    }

}

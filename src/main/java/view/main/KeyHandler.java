package view.main;

import view.component.KeyPressedButton;
import view.utils.Direction;

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
    private List<KeyPressedButton> buttons = new ArrayList<>();
    public void addObserver(KeyPressedButton button) {
        this.buttons.add(button);
    }

    public void removeObserver(KeyPressedButton button) {
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

        for (KeyPressedButton button: buttons) {
            button.handlePressed(code);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        markKey(code, false);

        for (KeyPressedButton button: buttons) {
            button.handleReleased(code);
        }
    }

    public static Direction directionKeyMapper(KeyHandler keyHandler) {
        if (keyHandler.upPressed)
            return Direction.UP;
        else if (keyHandler.downPressed)
            return Direction.DOWN;
        else if (keyHandler.rightPressed)
            return Direction.RIGHT;
        else if (keyHandler.leftPressed)
            return Direction.LEFT;
        else
            return null;
    }
}

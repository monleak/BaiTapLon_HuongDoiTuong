package view.main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Xử lý bàn phím.
 */
public class KeyHandler implements KeyListener {
    /**
     *  true nếu các phím đang được nhấn.
     */
    public boolean upPressed, downPressed, leftPressed, rightPressed;
    public boolean enterPressed, spacePressed, escPress;
    public boolean xPressed, pPressed;

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
    }

    /**
     * Override các method của class cha trong thư viện java swing.
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        markKey(code, true);
    }
    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        markKey(code, false);
    }

}

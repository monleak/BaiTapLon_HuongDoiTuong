package view.main;

import view.component.KeyPressedButton;
import view.component.MouseButton;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Chuột
 *
 *  - mouseB != -1 nếu chuột được nhấn.
 *  - mouseX, mouseY: tọa độ trên màn hình của chuột.
 *
 *
 */
public class MouseHandler implements MouseListener, MouseMotionListener {

    private static int mouseX = -1;
    private static int mouseY = -1;
    private static int mouseB = -1;

    //observer
    private List<MouseButton> buttons = new ArrayList<>();

    public MouseHandler(GamePanel gp) {
        gp.addMouseListener(this);
        gp.addMouseMotionListener(this);
    }

    public void addObserver(MouseButton button) {
        this.buttons.add(button);
    }

    public void removeObserver(MouseButton button) {
        this.buttons.remove(button);
    }

    public int getX() {
        return mouseX;
    }

    public int getY() {
        return mouseY;
    }

    public int getButton() {
        return mouseB;
    }

    /**
     * Override các method của class cha trong thư viện java swing.
     */
    @Override
    public void mouseClicked(MouseEvent e) {}
    @Override
    public void mousePressed(MouseEvent e) {
        mouseB = e.getButton();

        for (MouseButton button: buttons) {
            button.hamdlePressed(mouseX, mouseY);
        }
    }
    @Override
    public void mouseReleased(MouseEvent e) {
        mouseB = -1;

        for (MouseButton button: buttons) {
            button.handleReleased(mouseX, mouseY);
        }
    }
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}
    @Override
    public void mouseDragged(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();

        for (MouseButton button: buttons) {
            button.handleDragged(mouseX, mouseY);
        }
    }
    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();

        for (MouseButton button: buttons) {
            button.handleMoved(mouseX, mouseY);
        }
    }
}

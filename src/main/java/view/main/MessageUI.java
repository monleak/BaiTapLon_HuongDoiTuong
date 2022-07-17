package view.main;

import java.awt.*;
import java.util.Objects;
import java.util.Stack;
import java.util.concurrent.atomic.AtomicInteger;

public class MessageUI {
    private GamePanel gp;
    private Stack<String> messages;
    private int counter;
//    private Font msgFont;

    public MessageUI(GamePanel gp) {
        this.gp = gp;
        this.messages = new Stack<>();
//        this.msgFont = new Font("Arial", Font.PLAIN, 16);
    }

    public Stack<String> getMessages() {
        return messages;
    }

    public void showMessage (String msg) {
        this.messages.push(msg);
    }

    public void hideMessage (String msg) {
        this.messages.forEach(e -> {
            if (Objects.equals(e, msg)) {
                this.messages.remove(e);
            }
        });
    }

    public void update () {
        if (this.messages.empty()) {
            return;
        }
        if ( counter == 60 ) {
            counter = 0;
            this.messages.pop();
            return;
        }

        counter++;
    }

    public void draw (Graphics2D g2) {
        if (!messages.empty()) {
            g2.setColor(Color.WHITE);
//            g2.setFont(msgFont);
            AtomicInteger i = new AtomicInteger();
            messages.forEach(msg -> {
                g2.drawString(msg, 24, gp.screenHeight - 48 - i.get() * 24);
                i.getAndIncrement();
            });
        }
    }
}

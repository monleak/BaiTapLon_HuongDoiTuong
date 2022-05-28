package view.main;

import java.awt.*;
import java.util.Arrays;

public class UI {
    GamePanel gp;
    Graphics2D g2;
    Font arial_40;
    public boolean messageOn = false;
    public int messageCounter;
    public String[] messages;

    public UI (GamePanel gp) {
        this.gp = gp;
        arial_40 = new Font("Arial", Font.PLAIN, 40);
        messages = new String[10];
        Arrays.fill(messages, "");
    }

    public void showMessage (String msg) {
        messages[0] = (msg);
        messageOn = true;
    }

    public void showMessageList (String[] msgs) {
        for (int i = 0; i < msgs.length; i++) {
            if (i < 10) {
                messages[i] = (msgs[i]);
            }
        }
    }

    public void drawPauseScreen () {
        String text = "PAUSED";
        int x = gp.screenWidth / 2;
        int y = gp.screenHeight / 2;
        int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int height = (int) g2.getFontMetrics().getStringBounds(text, g2).getHeight();

        g2.drawString(text, x - length / 2, y - height / 2);
    }

    public void draw (Graphics2D g2) {
////        g2.setFont(arial_40);
//        g2.setColor(Color.WHITE);
//        g2.drawString("UI: ", 25, 50);
//

        this.g2 = g2;
//        g2.setFont(arial_40);
        g2.setColor(Color.white);

//        if( gp.gameState == GameState.PLAY ) {
                    // MESSAGE
            if(messageOn) {
//              g2.setFont(g2.getFont().deriveFont(30F));
                for (int i = 0; i < 10; i++) {
                    g2.drawString(messages[i], gp.titleSize / 2, gp.titleSize * 2 + 12 * i );
                }
                messageCounter++;
                if(messageCounter > 120) {
                    messageCounter = 0;
//                    messageOn = false;
//                    Arrays.fill(messages, "");
                }
            }
//        } else if (gp.gameState == GameState.PAUSE) {
//            drawPauseScreen();
//        }
    }
}

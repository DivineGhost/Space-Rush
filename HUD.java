import java.awt.Color;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.*;

public class HUD{

    public static int HEALTH = 100;
    public static long SCORE = 0;
    
    public void tick(){
        SCORE++;
        HEALTH = Game.clamp(HEALTH, 0, 100);
    }

    public void render(Graphics g){
        g.setColor(Color.GRAY);
        g.fillRect(13, 13, 204, 24);
        g.setColor(new Color(30 * (200 / HEALTH), 200, 0));        
        g.fillRect(15, 15, HEALTH * 2, 20);
        g.setColor(Color.CYAN);
        g.drawString("Score: " + SCORE, 15, 60);
    }
}
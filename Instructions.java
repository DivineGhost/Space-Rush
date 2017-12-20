import java.awt.event.*;
import java.awt.Graphics;
import javax.swing.*;
import java.awt.*;

public class Instructions extends MouseAdapter implements MousedOver{

    private int backX, backY, backWid, backHei;
    
    public Instructions(){
        this.backX = 30;
        this.backY = 20;
        this.backWid = 100;
        this.backHei = 30;
    }
    
    public void MousePressed(MouseEvent e){
        int mouseX = e.getX();
        int mouseY = e.getY();        
        if(mouseOver(mouseX, mouseY, backX, backY, backWid, backHei)){
            Game.status = Status.Menu;
        }
    }
    
    public void render(Graphics g){
        ImageIcon background = new ImageIcon("sprites/background.png");
        //ImageIcon back = new ImageIcon("sprites/back.png");
                
        JLabel label = new JLabel();
        background.paintIcon(label, g, 0, 0);
        g.setColor(Color.WHITE);
        g.setFont(new Font("arial", 1, 20));
        g.drawString("Back", backX, backY);
        //back.paintIcon(label, g, backX, backY);
    }
}
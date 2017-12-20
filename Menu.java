import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class Menu extends MouseAdapter implements MousedOver{

    private int titleX, titleY;
    private int startX, startY, startWid, startHei;
    private int instX, instY, instWid, instHei;
    private int exitX, exitY, exitWid, exitHei;
    
    public Menu(){
        this.titleX = 500; this.titleY = 100;
        this.startX = 500; this.startY = 300; this.startWid = 200; this.startHei = 100;
        this.instX = 100; this.instY = 400; this.instWid = 200; this.instHei = 100;
        this.exitX = 500; this.exitY = 500; this.exitWid = 200; this.exitHei = 100;
    }
    
    public void mousePressed(MouseEvent e){
        int mouseX = e.getX();
        int mouseY = e.getY();
        // Se o botão "start" for apertado
        if(mouseOver(mouseX, mouseY, startX, startY, startWid, startHei)){            
            Game.handler.addObject(Game.player);
            Game.status = Status.Game;
        }
        // Se o botão "instructions" for apertado
        if(mouseOver(mouseX, mouseY, instX, instY, instWid, instHei)){            
            Game.status = Status.Instructions;
        }
        // Se o botão "exit" for apertado
        if(mouseOver(mouseX, mouseY, exitX, exitY, exitWid, exitHei)){            
            System.out.println("Não esquece de remover o comentário");
            //System.exit(0);
        }
    }
    
    public void render(Graphics g){
        ImageIcon background = new ImageIcon("sprites/menu.png");        
        ImageIcon title = new ImageIcon("sprites/menutitle.png");
        /*ImageIcon start = new ImageIcon("sprites/start.png");
        ImageIcon instructions = new ImageIcon("sprites/instructions.png");
        ImageIcon exit = new ImageIcon("sprites/exit.png");
        */ 
        JLabel label = new JLabel();        
        background.paintIcon(label, g, 0, 0);
        g.setColor(Color.WHITE);
        g.setFont(new Font("arial", 1, 20));
        g.drawString("Instructions", instX, instY);
        title.paintIcon(label, g, titleX, titleY);
        /*start.paintIcon(label, g, startX, startY);
        instructions.paintIcon(label, g, instX, instY);
        exit.paintIcon(label, g, exitX, exitY);
        */
    }
}
package GUI;

import Entities.*;
import Mechanics.*;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class Menu extends MouseAdapter implements MousedOver{

    private int startX, startY, startWid, startHei;
    private int instX, instY, instWid, instHei;
    private int exitX, exitY, exitWid, exitHei;
    
    public Menu(){
        this.startX = 376; this.startY = 270; this.startWid = 252; this.startHei = 54;
        this.instX = 252; this.instY = 432; this.instWid = 486; this.instHei = 54;
        this.exitX = 414; this.exitY = 594; this.exitWid = 162; this.exitHei = 54;
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
            System.exit(0);
        }
    }
    
    public void render(Graphics g){
        ImageIcon menuScreen = new ImageIcon("sprites/menu.png");
        JLabel label = new JLabel();        
        menuScreen.paintIcon(label, g, 0, 0);
    }
}
package GUI;

import Entities.*;
import Mechanics.*;

import java.awt.event.*;
import java.awt.Graphics;
import javax.swing.*;
import java.awt.*;

public class Instructions extends MouseAdapter implements MousedOver{

    private int backX, backY, backWid, backHei;
    
    public Instructions(){
        this.backX = 36;
        this.backY = 36;
        this.backWid = 144;
        this.backHei = 60;
    }
    
    public void mousePressed(MouseEvent e){
        int mouseX = e.getX();
        int mouseY = e.getY();
        // Se o botão "back" for apertado
        if(mouseOver(mouseX, mouseY, backX, backY, backWid, backHei)){
            Game.status = Status.Menu;
        }
    }
    
    public void render(Graphics g){
        ImageIcon instructions = new ImageIcon("sprites/instructions.png");
        JLabel label = new JLabel();
        instructions.paintIcon(label, g, 0, 0);
    }
}
package GUI;

import Entities.*;
import Mechanics.*;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.*;
import java.awt.Font;

public class HUD{
    
    public static long SCORE;
    public static int LEVEL;
    
    public HUD(){
        this.SCORE = 0;
        this.LEVEL = 0;
    }
        
    public void tick(){
        if(Game.status == Status.Game){
            SCORE++;
            if(SCORE % 300 == 0){ LEVEL++; }            
        }
    }

    public void render(Graphics g){
        g.setColor(Color.GRAY);
        g.fillRect(13, 13, 204, 24);
        g.setColor(new Color(10, 200 ,0));        
        g.fillRect(15, 15, (Player.HEALTH * 2/10), 20);
        g.setColor(new Color(10, 200, 0));
        g.setFont(new Font("Tahoma", Font.BOLD, 20));
        g.drawString("Score: " + SCORE, 15, 60);
        g.drawString("Level: " + LEVEL, 15, 80);        
    }
}
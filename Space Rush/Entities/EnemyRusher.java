package Entities;

import Mechanics.*;
import GUI.*;

import java.awt.Graphics;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class EnemyRusher extends GameObject{
    public EnemyRusher(int x, int y, Type type){
        super(10, x, y, type, 113, 127, 2, Allegiance.Enemy);
        xSpeed = -2;
        ySpeed = 0;
        
        this.hitbox = new Hitbox(x, y, width, height);
    }
    
    public void tick(){
        x += xSpeed;
        y += ySpeed;
        
        hitbox.x = x;
        hitbox.y = y;
        
        y = Game.clamp(y, 0, Game.HEIGHT - this.height);
        if(y == Game.HEIGHT - this.height * 2 || y == 0){
            ySpeed = -ySpeed;
        }        
    }
    
    public void render(Graphics g){
        ImageIcon sprite = new ImageIcon("sprites/rusher.png");
        JFrame frameSprite = new JFrame();
        sprite.paintIcon(frameSprite, g, x, y);
    }
}
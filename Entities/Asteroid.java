package Entities;

import Mechanics.*;
import GUI.*;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import java.awt.Graphics;
import java.util.Random;

public class Asteroid extends GameObject{    
    public Asteroid(int x, int y){
        super(40, x, y, Type.Asteroid, 168, 151, 5, Allegiance.Enemy);
        
        xSpeed = -2;
        Random r = new Random();
        ySpeed = r.nextInt(3);
        
        this.hitbox = new Hitbox(x, y, width, height);        
    }
    
    public void tick(){
        x += xSpeed;
        y += ySpeed;
        
        hitbox.x = x;
        hitbox.y = y;
        
        y = Game.clamp(y, 0, Game.HEIGHT - this.height);
        if(y == Game.HEIGHT - this.height - 1 || y == 0){
            ySpeed = -ySpeed;
        } 
    }
    
    public void render(Graphics g){
        ImageIcon sprite = new ImageIcon("sprites/asteroid.png");
        JFrame frameSprite = new JFrame();
        sprite.paintIcon(frameSprite, g, x, y);
    }
}
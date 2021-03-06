package Entities;

import Mechanics.*;
import GUI.*;

import java.awt.Graphics;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class EnemyDestroyer extends GameObject{
    
    private double shotCooldown = 2000000000;
    private long previousShotTime = 0;
    
    public EnemyDestroyer(int x, int y, Type type){
        super(200, x, y, type, 330, 277, 2, Allegiance.Boss);
        xSpeed = -1;
        ySpeed = 0;
        this.hitbox = new Hitbox(x, y, width, height);
    }
    
    public void tick(){
        x += xSpeed;
        y += ySpeed;
        
        hitbox.x = x;
        hitbox.y = y;
        
        if(this.health <= 0){
            Game.status = Status.Game;
            HUD.LEVEL++;
        }
        
        if(this.x == Game.WIDTH - this.width - 10){
            this.xSpeed = 0;
            this.ySpeed = -3;
            // Para não entrar no if novamente
            this.x -= 1;
            hitbox.x -= 1;
        }
        y = Game.clamp(y, 0, Game.HEIGHT - this.height);
        if(y == Game.HEIGHT - this.height - 20 || y == 0){
            ySpeed = -ySpeed;
        }
        if(System.nanoTime() - previousShotTime >= shotCooldown){
            // Se não estiver em recarga, atira e seta o tempo para o próximo evento
            shoot();                    
            previousShotTime = System.nanoTime();
        }        
    }
    
    public void render(Graphics g){
        ImageIcon sprite = new ImageIcon("sprites/destroyer.png");
        JFrame frameSprite = new JFrame();
        sprite.paintIcon(frameSprite, g, x, y);
    }
    
    public void shoot(){
        Game.handler.addObject(new Projectile(20, this.x + 20, this.y + (this.height / 2) - 20,-3, 100, 40, Type.Projectile, Allegiance.Boss));
    }
}
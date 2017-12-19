import javax.swing.ImageIcon;
import javax.swing.*;
import java.awt.Graphics;
import java.awt.Color;

public class Player extends GameObject{
    
    public static int HEALTH;
    private Handler handler;
    public Hitbox hitbox;    
    
    public Player(int health, int x, int y, Type type, Handler handler){
        super(health, x, y, type, 146, 166, 0, Allegiance.Friend);
        this.handler = handler;
        this.HEALTH = health; 
        this.hitbox = new Hitbox(x, y, width, height);
    }
    
    public void tick(){
        x += xSpeed;
        y += ySpeed;

        hitbox.x = x;
        hitbox.y = y;
        
        HEALTH = this.health;
        
        x = Game.clamp(x, 0, Game.WIDTH - this.width - 1);
        y = Game.clamp(y, 0, Game.HEIGHT - this.height - 34);
    }
    
    public void render(Graphics g){
        ImageIcon sprite = new ImageIcon("sprites/player1.png");
        JFrame frameSprite = new JFrame();
        sprite.paintIcon(frameSprite, g, x, y);  
    }
    
    public void shoot(){
        handler.addObject(new Projectile(1, this.x + this.width + 1, this.y + (this.height / 2) - 1, 7, 22, 7, Type.Projectile, Allegiance.Friend));
    }
    
    public Hitbox getHitbox(){
        return this.hitbox;
    }
}
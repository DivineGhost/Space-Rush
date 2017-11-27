import javax.swing.ImageIcon;
import javax.swing.*;
import java.awt.Graphics;
import java.awt.Color;

public class Player extends GameObject{
    
    private Handler handler;
    
    public Player(int x, int y, Type type, Handler handler){
        super(x, y, type, 146, 166);
        this.handler = handler;
    }
    
    public void tick(){
        x += xSpeed;
        y += ySpeed;
        
        x = Game.clamp(x, 0, Game.WIDTH - this.width - 1);
        y = Game.clamp(y, 0, Game.HEIGHT - this.height - 34);
    }
    
    public void render(Graphics g){
        ImageIcon sprite = new ImageIcon("sprites/player1.png");
        JFrame frameSprite = new JFrame();
        sprite.paintIcon(frameSprite, g, x, y);  
    }
    
    public void shoot(){
        handler.addObject(new Projectile(this.x + this.width + 1, this.y + (this.height / 2) - 1, Type.Projectile));
    }
}
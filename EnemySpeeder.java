import java.awt.Graphics;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class EnemySpeeder extends GameObject{

    public EnemySpeeder(int health, int x, int y, Type type){
        super(health, x, y, type, 158, 154, 4, Allegiance.Enemy);
        
        xSpeed = -4;
        ySpeed = -1;
        
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
        ImageIcon sprite = new ImageIcon("sprites/speeder1.png");
        JFrame frameSprite = new JFrame();
        sprite.paintIcon(frameSprite, g, x, y);
    }
}
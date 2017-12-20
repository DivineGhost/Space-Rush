import java.awt.*;
import javax.swing.*;

public class ItemBuff extends GameObject{

    public ItemBuff(int health, int x, int y, Type type, int width, int height, int damage ,Allegiance allegiance){
        super(health, x, y, type, width, height, damage, allegiance);
        this.xSpeed = -1;
        this.ySpeed = 0;
        
        this.hitbox = new Hitbox(x, y, width, height);
    }
    
    public void tick(){
        this.x += this.xSpeed;
        this.y += this.ySpeed;
        
        hitbox.x = x;
        hitbox.y = y;
    }
    
    public void render(Graphics g){          
        ImageIcon sprite = new ImageIcon("sprites/speeder1.png");
        JFrame frameSprite = new JFrame();
        sprite.paintIcon(frameSprite, g, x, y);
    }
}
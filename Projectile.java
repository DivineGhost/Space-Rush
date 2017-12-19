import java.awt.Graphics;
import java.awt.Color;

public class Projectile extends GameObject{
    public Projectile(int health, int x, int y, int xSpeed, int width, int height, Type type, Allegiance allegiance){
        super(health, x, y, type, width, height, 10, allegiance);
        ySpeed = 0;
        this.xSpeed = xSpeed;
        
        hitbox = new Hitbox(x - 1, y - 1, width + 2, height + 2);
    }
    
    public void tick(){
        x += xSpeed;
        y += ySpeed;
        
        hitbox.x = x;
        hitbox.y = y;
    }
    
    public void render(Graphics g){
        g.setColor(Color.WHITE);
        g.fillRect(x - 1, y - 1, width + 2, height + 2);
        if(this.allegiance == Allegiance.Friend){
            g.setColor(Color.ORANGE);        
        }
        else{
            g.setColor(Color.CYAN);
        }
        g.fillRect(x, y, width, height);
    }
    
    public Hitbox getHitbox(){
        return this.hitbox;
    }
}
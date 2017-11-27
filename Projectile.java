import java.awt.Graphics;
import java.awt.Color;

public class Projectile extends GameObject{

    public Projectile(int x, int y, Type type){
        super(x, y, type, 22, 7);
        ySpeed = 0;
        xSpeed = 7;
    }
    
    public void tick(){
        x += xSpeed;
        y += ySpeed;
    }
    
    public void render(Graphics g){
        g.setColor(Color.WHITE);
        g.fillRect(x - 1, y - 1, width + 2, height + 2);
        g.setColor(Color.ORANGE);
        g.fillRect(x, y, width, height);
    }
}
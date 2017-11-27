import java.awt.Graphics;
import java.awt.Color;

public class EnemyRusher extends GameObject{

    public EnemyRusher(int x, int y, Type type){
        super(x, y, type, 16, 16);
        xSpeed = -2;
        ySpeed = 2;
    }
    
    public void tick(){
        x += xSpeed;
        y += ySpeed;
        
        y = Game.clamp(y, 0, Game.HEIGHT - this.height);
        if(y == Game.HEIGHT - this.height || y == 0){
            ySpeed = -ySpeed;
        }        
    }
    
    public void render(Graphics g){
        g.setColor(Color.RED);
        g.fillRect(x, y, width, height);
    }
}
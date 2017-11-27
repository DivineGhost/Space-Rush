import java.awt.Graphics;
import java.awt.Dimension;

public abstract class GameObject{
    protected int x, y;
    protected Type type;
    protected int xSpeed, ySpeed;
    protected int width, height;
    
    public GameObject(int x, int y, Type type, int width, int height){
        this.x = x;
        this.y = y;
        this.type = type;
        this.width = width;
        this.height = height;
        this.xSpeed = this.ySpeed = 0;
    }
    public GameObject(int x, int y, Type type, Dimension d){
        this.x = x;
        this.y = y;
        this.type = type;
        this.width = (int) d.getWidth();
        this.height = (int) d.getHeight();
        this.xSpeed = this.ySpeed = 0;
    }
    
    
    public abstract void tick();
    public abstract void render(Graphics g);
    
    public int getX(){
        return this.x;
    }
    public int getY(){
        return this.y;
    }
    public Type getType(){
        return this.type;
    }
    public void setX(int x){
        this.x = x;
    }
    public void setY(int y){
        this.y = y;
    }
    public void setXSpeed(int xSpeed){
        this.xSpeed = xSpeed;
    }
    public void setYSpeed(int ySpeed){
        this.ySpeed = ySpeed;
    }
    public void shoot(){
        // O método será sobreescrito por Player
    }
}
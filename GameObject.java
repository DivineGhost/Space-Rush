import java.awt.Graphics;
import java.awt.Dimension;

public abstract class GameObject{
    protected int health;
    protected int x, y;
    protected Type type;
    protected int xSpeed, ySpeed;
    protected int width, height;
    protected Hitbox hitbox;
    protected int damage;
    protected Allegiance allegiance;
    
    public GameObject(int health, int x, int y, Type type, int width, int height, int damage, Allegiance allegiance){
        this.health = health;
        this.x = x;
        this.y = y;
        this.type = type;
        this.width = width;
        this.height = height;
        this.xSpeed = this.ySpeed = 0;
        this.damage = damage;
        this.allegiance = allegiance;
    }

    public abstract void tick();
    public abstract void render(Graphics g);
    
    public int getHealth(){
        return this.health;
    }
    public int getX(){
        return this.x;
    }
    public int getY(){
        return this.y;
    }
    public Type getType(){
        return this.type;
    }
    public Hitbox getHitbox(){
        return this.hitbox;
    }
    public int getDamage(){
        return this.damage;
    }
    public void setHealth(int health){
        this.health = health;
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
    public void setDamage(int damage){
        this.damage = damage;
    }
    public boolean isFriendly(GameObject o2){
        if(this.allegiance == o2.allegiance){
            return true;
        }
        else{
            return false;
        }
    }  
    public void shoot(){
        // O método será sobreescrito por Player
    }
}
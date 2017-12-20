import java.awt.Graphics;
import java.util.LinkedList;
import java.awt.Rectangle;

public class Handler{

    protected LinkedList<GameObject> objects;
    
    public Handler(){
         objects = new LinkedList<GameObject>();    
    }
    
    public void tick(){        
        // Percorre todos os objetos da lista e chama o metodo tick() de cada um
        for(int i = 0; i < objects.size(); i++){
            // Utiliza um objeto auxiliar
            GameObject tempObject = objects.get(i);
            tempObject.tick();            
            if(tempObject.getX() < -(tempObject.width) || tempObject.getX() > Game.WIDTH + 10){
                removeObject(tempObject);
            }
            else{
                for(int j = 0; j < objects.size(); j++){
                    GameObject tempObject2 = objects.get(j);
                    if((tempObject.getX() != tempObject2.getX() && tempObject.getY() != tempObject2.getY()) && !tempObject.isFriendly(tempObject2)){
                        if(checkCollision(tempObject.getHitbox(), tempObject2.getHitbox())){
                            tempObject.setHealth(tempObject.getHealth() - tempObject2.getDamage());
                            tempObject2.setHealth(tempObject2.getHealth() - tempObject.getDamage());
                            if(tempObject.getHealth() <= 0){
                                this.removeObject(tempObject);
                            }
                            if(tempObject2.getHealth() <= 0){
                                this.removeObject(tempObject2);
                            }
                        }                               
                    }
                }
            }
        } 
    }
    
    public void render(Graphics g){
        // Percorre todos os objetos da lista e chama o método render(Graphics g) de cada um
        for(int i = 0; i < objects.size(); i++){
            GameObject tempObject = objects.get(i);
            System.out.println(objects.size());
            tempObject.render(g);
        }
    }
    
    // Métodos para adicionar e remover objetos da lista
    public void addObject(GameObject object){
        this.objects.add(object);
    }
    
    public void removeObject(GameObject object){
        this.objects.remove(object);
    }
    
    public boolean checkCollision(Hitbox h1, Hitbox h2){
        if(h1.intersects(h2)){
            return true;
        }
        return false;
    }
}
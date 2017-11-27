import java.awt.Graphics;
import java.util.LinkedList;

public class Handler{

    LinkedList<GameObject> objects;
    
    public Handler(){
         objects = new LinkedList<GameObject>();    
    }
    
    public void tick(){
        // Percorre todos os objetos da lista e chama o metodo tick() de cada um
        for(int i = 0; i < objects.size(); i++){
            // Utiliza um objeto auxiliar
            GameObject tempObject = objects.get(i);
            
            tempObject.tick();
        } 
    }
    
    public void render(Graphics g){
        // Percorre todos os objetos da lista e chama o método render(Graphics g) de cada um
        for(int i = 0; i < objects.size(); i++){
            GameObject tempObject = objects.get(i);
            
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
}
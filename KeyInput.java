import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter{

    private Handler handler;
    private long previousShotTime;
    private long shotCooldown = 300000000; // Tempo de recarga do tiro em nanosegundos para maior precisão
    
    public KeyInput(Handler handler){
        this.handler = handler;
    }
    
    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();
        
        for(int i = 0; i < handler.objects.size(); i++){
            GameObject tempObject = handler.objects.get(i);
            
            if(tempObject.getType() == Type.Player){
                // Movement
                if(key == KeyEvent.VK_W) tempObject.setYSpeed(-5);
                if(key == KeyEvent.VK_S) tempObject.setYSpeed( 5);
                if(key == KeyEvent.VK_D) tempObject.setXSpeed( 5);
                if(key == KeyEvent.VK_A) tempObject.setXSpeed(-5);
                // Shooting
                if(key == KeyEvent.VK_SPACE){
                    if(System.nanoTime() - previousShotTime >= shotCooldown){
                        // Se não estiver em recarga, atira e seta o tempo para o próximo evento
                        tempObject.shoot();                    
                        previousShotTime = System.nanoTime();
                    }
                }                
            }
        }
    }
    
    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();
        
        for(int i = 0; i < handler.objects.size(); i++){
            GameObject tempObject = handler.objects.get(i);
            
            if(tempObject.getType() == Type.Player){
                if(key == KeyEvent.VK_W) tempObject.setYSpeed(0);
                if(key == KeyEvent.VK_S) tempObject.setYSpeed(0);
                if(key == KeyEvent.VK_D) tempObject.setXSpeed(0);
                if(key == KeyEvent.VK_A) tempObject.setXSpeed(0);
            }
        }
        
        if(key == KeyEvent.VK_ESCAPE) System.exit(0);
    }
}
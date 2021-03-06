package Mechanics;

import Entities.*;
import GUI.*;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.LinkedList;

public class KeyInput extends KeyAdapter{

    private Handler handler;
    private boolean keyDown[] = new boolean[4];
    private long previousShotTime = 0;
    private long shotCooldown = 300000000; // Tempo de recarga do tiro em nanosegundos para maior precisão
    
    public KeyInput(Handler handler){
        this.handler = handler;
        for(int i = 0; i < keyDown.length; i++){
            keyDown[i] = false;
        }
    }
    
    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();
        
        for(int i = 0; i < handler.objects.size(); i++){
            GameObject tempObject = handler.objects.get(i);
            
            if(tempObject.getType() == Type.Player && Game.status == Status.Game){
                // Movement
                if(key == KeyEvent.VK_W){
                    tempObject.setYSpeed(-5);
                    keyDown[0] = true;
                }
                if(key == KeyEvent.VK_S){
                    tempObject.setYSpeed( 5);
                    keyDown[1] = true;
                }
                if(key == KeyEvent.VK_D){
                    tempObject.setXSpeed( 5);
                    keyDown[2] = true;
                }
                if(key == KeyEvent.VK_A){
                    tempObject.setXSpeed(-5);
                    keyDown[3] = true;
                }
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
            
            if(tempObject.getType() == Type.Player && Game.status == Status.Game){
                if(key == KeyEvent.VK_W){
                    keyDown[0] = false; 
                }
                if(key == KeyEvent.VK_S){
                    keyDown[1] = false;
                }
                if(key == KeyEvent.VK_D){
                    keyDown[2] = false;
                }
                if(key == KeyEvent.VK_A){
                    keyDown[3] = false;
                }
                
                if(!keyDown[0] && !keyDown[1]){
                    tempObject.setYSpeed(0);
                }                
                if(!keyDown[2] && !keyDown[3]){
                    tempObject.setXSpeed(0);
                }
            }
        }
            
        if(key == KeyEvent.VK_P){
            if(Game.status == Status.Pause || Game.status == Status.Boss){
                Game.status = Status.Game;
            }
            else if(Game.status == Status.GameOver){
                Game.status = Status.Menu;
            }
            else{
                Game.status = Status.Pause;
            }
        }
        
        if(key == KeyEvent.VK_ESCAPE) {
            System.exit(0);
        }
    }
}
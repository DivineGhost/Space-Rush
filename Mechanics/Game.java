package Mechanics;

import Entities.*;
import GUI.*;

import java.awt.Canvas;
import java.awt.image.BufferStrategy;
import java.awt.Graphics;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Game extends Canvas implements Runnable{
    // Constantes que correspondem às dimensões da tela
    public static final int WIDTH = 1000, HEIGHT = WIDTH / 12 * 9;
    public static long ticks = 0;
    public static Status status;
    
    private Thread thread;
    private boolean running = false;
    
    public static Handler handler;
    private Window window;
    public static Player player;
    private HUD hud;
    public static Spawner spawner;
    private Menu menu;
    private Instructions inst;
    private KeyInput keyInput;

    
    public Game(){
        status = Status.Menu;
        handler = new Handler();        
        window = new Window(WIDTH, HEIGHT, "Space Rush", this);
        hud = new HUD();
        player = new Player(1000, 50, (Game.HEIGHT / 2) - 50, Type.Player, handler);
        menu = new Menu();
        inst = new Instructions();
        spawner = new Spawner(handler, hud);
        keyInput = new KeyInput(handler);
        this.addKeyListener(keyInput);
        this.addMouseListener(menu);
        this.addMouseListener(inst);
    }
    
    public void run(){
        this.requestFocus();
        // Pega o tempo atual (tempo zero) em nanosegundos
        long lastTime = System.nanoTime();
        // Seta a quantidade de ticks (frames por segundo)
        double amountOfTicks = 60.0;
        // Inverso da taxa de atualização (1 * 10 ^ 9 nanosegundos / amountOfTicks)
        double ns = 1000000000 / amountOfTicks;
        // Inicializa a variação de tempo em zero (em ticks)
        double delta = 0;
        // Pega o tempo atual
        long timer = System.currentTimeMillis();
        // Inicializa a quantidade de frames renderizados em zero
        int frames = 0;
        while(running){
            // Pega o tempo atual (tempo um) em nanosegundos na iteração i do loop
            long now = System.nanoTime();
            // Calcula a diferença entre o tempo da iteração i e o último tempo
            delta += (now - lastTime) / ns;
            // Iguala o último tempo ao atual para a iteração i + 1 funcionar corretamente
            lastTime = now;
            while(delta >=1){
                // Se o tempo equivalente a um tick ou mais se passou, chama this.tick()
                this.tick();
                // Diminui uma unidade de delta para a iteração i + 1 funcionar corretamente
                delta--;
            }
            if(running){
                // Renderiza os elementos do jogo
                this.render();
            }
            frames++;
            if(System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                frames = 0;
            }
        }
        this.stop();
    }
    
    public synchronized void start(){
        // Inicializando a thread
        thread = new Thread(this);
        thread.start();
        running = true;
    }
    
    public synchronized void stop(){
        try{
            thread.join();
            running = false;
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void tick(){
        handler.tick();
        if(this.status == Status.Game){
            hud.tick();
            spawner.tick();
            this.ticks++;
        }
    }
    
    public void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            // Setando triple buffering para renderização
            this.createBufferStrategy(3);
            return;
        }
        
        Graphics g = bs.getDrawGraphics();
        // Renderizando a tela segundo o status
        if(this.status == Status.Game || this.status == Status.Boss){            
            // Setando propriedades do fundo
            ImageIcon background = new ImageIcon("sprites/background.png");
            JLabel backgroundLabel = new JLabel();
            background.paintIcon(backgroundLabel, g, 0, 0);            
            hud.render(g);
            // Chamando a função render() do handler, que chama iteradamente o render() dos outros objetos
            handler.render(g);
        }        
        else if(this.status == Status.Menu){
            menu.render(g);
        }
        else if(this.status == Status.Pause){
            menu.render(g);
        }
        else if(this.status == Status.Instructions){
            inst.render(g);
        }
        else if(this.status == Status.GameOver){
            // Reseta itens importantes do jogo
            this.handler = new Handler();
            this.hud = new HUD();
            System.out.println(HUD.LEVEL + "    " + HUD.SCORE);
            this.spawner = new Spawner(this.handler, this.hud);
            this.player = new Player(1000, 50, (Game.HEIGHT / 2) - 50, Type.Player, handler);
            this.removeKeyListener(keyInput);
            this.keyInput = new KeyInput(handler);
            this.addKeyListener(keyInput);
            // Setando tela de game over
            ImageIcon gameOver = new ImageIcon("sprites/gameover.png");
            JLabel gameOverLabel = new JLabel();
            gameOver.paintIcon(gameOverLabel, g, 0, 0);
        }
        // Mostrando o conteúdo da janela
        g.dispose();
        bs.show();
    }
    
    public static int clamp(int value, int min, int max){
        if(value >= max){
            return value = max;
        }
        else if(value <= min){
            return value = min;
        }
        else{
            return value;
        }
    }
}
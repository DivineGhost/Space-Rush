import java.awt.Canvas;
import java.awt.image.BufferStrategy;
import java.awt.Graphics;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.util.Random;

public class Game extends Canvas implements Runnable{
    // Constantes que correspondem às dimensões da tela
    public static final int WIDTH = 640, HEIGHT = WIDTH / 12 * 9;
    
    private Thread thread;
    private boolean running = false;
    
    private Handler handler;
    private Window window;
    private Player player;
    private HUD hud;

    
    public Game(){        
        handler = new Handler();
        hud = new HUD();
        this.addKeyListener(new KeyInput(handler));
        window = new Window(WIDTH, HEIGHT, "Space Rush", this);
        player = new Player((WIDTH / 2) - 50, (HEIGHT / 2) - 50, Type.Player, handler);
        Random r = new Random();
        EnemyRusher rusher1 = new EnemyRusher(WIDTH - 20, r.nextInt(HEIGHT - 20), Type.EnemyRusher);
        
        handler.addObject(player);
        handler.addObject(rusher1);
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
            frames++; // teste fps counter:
            if(System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                //System.out.println("FPS: "+ frames);
                frames = 0;
            }
        }
        this.stop();
    }
    
    public synchronized void start(){
        // Inicializando a thread REVER
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
        hud.tick();
    }
    
    public void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            // Setando triple buffering para renderização
            this.createBufferStrategy(3);
            return;
        }
        
        Graphics g = bs.getDrawGraphics();
        // Setando propriedades do fundo
        ImageIcon background = new ImageIcon("sprites/background.png");
        JLabel backgroundLabel = new JLabel();
        background.paintIcon(backgroundLabel, g, 0, 0);
        // Chamando a função render() do handler, que chama iteradamente o render() dos outros objetos
        handler.render(g);
        hud.render(g);
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
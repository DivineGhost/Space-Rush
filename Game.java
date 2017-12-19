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
    private Player player;
    private HUD hud;
    private Spawner spawner;

    
    public Game(){
        status = Status.Game;
        handler = new Handler();
        player = new Player(1000, 50, (HEIGHT / 2) - 50, Type.Player, handler);     
        handler.addObject(player);
        hud = new HUD();
        spawner = new Spawner(handler, hud);
        this.addKeyListener(new KeyInput(handler));
        window = new Window(WIDTH, HEIGHT, "Space Rush", this);
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
        hud.tick();
        spawner.tick();
        this.ticks++;
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
    
    public boolean isGameOver(){
        if(Player.HEALTH <= 0){
            return true;
        }
        return false;
    }
}
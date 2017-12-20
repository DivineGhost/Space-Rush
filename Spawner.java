import java.util.Random;
import java.lang.Math;

public class Spawner{

    private Random r;
    private Handler handler;
    private HUD hud;
    private int denominator;
    private boolean bossSpawned = false;
    
    public Spawner(Handler handler, HUD hud){
        this.handler = handler;
        this.hud = hud;
        r = new Random();
        denominator = 200;        
    }
    
    public void tick(){
        if(hud.LEVEL == 20){
            Game.status = Status.Boss;
        }
        denominator = (int) 100 / (hud.LEVEL + 1);        
        if(Game.status == Status.Game){
            if(hud.SCORE % (denominator * 6) == 0){
                EnemyRusher rusher = new EnemyRusher(Game.WIDTH, r.nextInt(Game.HEIGHT - 120), Type.EnemyRusher);
                handler.addObject(rusher);
            }
            if(hud.SCORE % (denominator * 16) == 0){
                Asteroid asteroid = new Asteroid(Game.WIDTH, r.nextInt(Game.HEIGHT - 120));
                handler.addObject(asteroid);
            }
            if(hud.SCORE % (denominator * 10) == 0){
                EnemySpeeder speeder = new EnemySpeeder(10, Game.WIDTH, r.nextInt(Game.HEIGHT - 120), Type.EnemySpeeder);
                handler.addObject(speeder);
            }
        }
        else if(Game.status == Status.Boss && !bossSpawned){
            EnemyDestroyer destroyer = new EnemyDestroyer(Game.WIDTH, r.nextInt(Game.HEIGHT/2), Type.EnemyDestroyer);
            handler.addObject(destroyer);
            bossSpawned = true;
        }
    }
}
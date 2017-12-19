import java.util.Random;

public class Spawner{

    private Random r;
    private Handler handler;
    private HUD hud;
    private boolean bossSpawned;
    private int control;
    private int denominator;
    private long first, last;
    
    public Spawner(Handler handler, HUD hud){
        this.handler = handler;
        this.hud = hud;
        r = new Random();
        
        control = 0;
        denominator = 200;
    }
    
    public void tick(){        
        if(EnemyDestroyer.HEALTH < 0){
            Game.status = Status.Game;
            bossSpawned = false;
        }else if(EnemyDestroyer.HEALTH == 0){
            last = System.currentTimeMillis();
            if(last - first > 10000){ // Para não bugar o level
                hud.LEVEL = 6 + control;
                control += 5;
                EnemyDestroyer.HEALTH = -1;
            }
        }
        if(hud.LEVEL != 0 && hud.LEVEL % 5 == 0){            
            Game.status = Status.Boss;
        }
        denominator = (int) 200 / (hud.LEVEL + 1);        
        if(Game.status == Status.Game){
            if(hud.SCORE % denominator == 0){
                EnemyRusher rusher = new EnemyRusher(Game.WIDTH, r.nextInt(Game.HEIGHT/2), Type.EnemyRusher);
                handler.addObject(rusher);
            }
            if(hud.SCORE % (denominator * 5) == 0){
                Asteroid asteroid = new Asteroid(Game.WIDTH, r.nextInt(Game.HEIGHT/3));
                handler.addObject(asteroid);
            }
            if(hud.SCORE % (denominator * 3) == 0){
                EnemySpeeder speeder = new EnemySpeeder(10, Game.WIDTH, r.nextInt(Game.HEIGHT/4), Type.EnemySpeeder);
                handler.addObject(speeder);
            }
        }
        else if(Game.status == Status.Boss && !bossSpawned){
            EnemyDestroyer destroyer = new EnemyDestroyer(Game.WIDTH, r.nextInt(Game.HEIGHT/2), Type.EnemyDestroyer);
            handler.addObject(destroyer);
            bossSpawned = true;
            first = System.currentTimeMillis();
        }
    }
}
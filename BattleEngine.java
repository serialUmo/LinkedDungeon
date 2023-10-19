import java.util.Random;
import java.util.Scanner;

/**
 * An engine that will handle fights between the Hero and a Monster.
 */
public class BattleEngine implements Engine
{
    private EnemyKind[] enemies;
    
    private static int difficulty;
    
    private Random random;
    private Scanner scan;
    
    /**
     * Constructs the engine, as well as all the different types of monsters.
     */
    public BattleEngine()
    {
        random = new Random();
        scan = new Scanner(System.in);
        difficulty = 0;
        
        enemies = new EnemyKind[]
        {               // KIND                 HP   ATK   DEF   SPD   MONEY   EXP
            new EnemyKind("Shadow",              1,    1,    0,    0,      0,    0),
            new EnemyKind("Imp",                 8,    5,    0,    5,     12,    5),
            new EnemyKind("Lymph Construct",    11,    3,    1,    2,     30,    9),
            new EnemyKind("Minotaur",           50,   10,    2,    3,     10,   25),
        };
    }
    
    /**
     * Begins a fight with a random enemy.
     * @param hero The hero character.
     */
    public void interact(Hero hero)
    {
        Enemy enemy = new Enemy(enemies[random.nextInt(enemies.length)], difficulty);
        enemy.printStats();
        return;
    }
    
    /**
     * Begins a fight with a specified enemy.
     * @param hero The hero character.
     * @param enemyIndex The index from the "enemies" array to specify what type of monster to fight.
     */
    public void interact(Hero hero, int enemyIndex)
    {
        Enemy enemy = new Enemy(enemies[enemyIndex], difficulty);
        enemy.printStats();
        return;
    }
    
    public void fight(Hero hero, Enemy enemy){
        
    }
    
    public void incrementDifficulty(){
        difficulty++;
    }
    public void setDifficulty(int newDifficulty){
        difficulty = newDifficulty;
    }
    
    public String getMapIcon(){
        return "B";
    }
}

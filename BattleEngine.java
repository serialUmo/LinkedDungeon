import java.util.Random;
import java.util.Scanner;

/**
 * An engine that will handle fights between the Hero and a Monster.
 */
public class BattleEngine implements Engine
{
    private EnemyKind[] enemies;
    private EnemyKind[] bosses;
    
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
            new EnemyKind("Shadow",              5,    2,    0,    0,      7,    3),
            new EnemyKind("Prisoner",            8,    3,    0,    2,     41,    6),
            new EnemyKind("Shrieker",           14,    7,   20,    7,     32,   12),
            new EnemyKind("Sinner",             17,    5,   10,   -3,     12,    8),
            new EnemyKind("A Pitch Black Square",16,   4,    4,    4,     44,   16),
            new EnemyKind("Gilded $hadow",      10,    7,   20,    7,     80,    7),
            new EnemyKind("Harmonizer",         20,    6,   24,    8,     65,   21),
            new EnemyKind("Something Invisible",35,    3,    0,   10,     50,   15),
            new EnemyKind("Pain Sprite",         1,   25,    0,   25,     35,   35),
            new EnemyKind("Sinister Knight",    18,    3,   30,   -9,    -20,   40),
            new EnemyKind("Imp",                 8,    5,    0,    5,     24,    5),
            new EnemyKind("Lymph Construct",    11,    3,    1,    2,     36,    9),
            new EnemyKind("Minotaur",           20,    7,    2,    3,     41,   15),
        };
        
        bosses = new EnemyKind[]
        {               // KIND                 HP   ATK   DEF   SPD   MONEY   EXP
            new EnemyKind("Asmodion",           50,   10,    0,    6,    111,   50),
            new EnemyKind("Septaganon",         70,   17,    7,    7,    177,   77),
            new EnemyKind("Dungeon Tumor",      60,   22,   20,   -1,    220,   60),
            new EnemyKind("Whispers",           40,   33,    0,    0,    250,   80),
            new EnemyKind("Hollow",            100,   20,   15,    0,    333,  120),
            new EnemyKind("[???]",             100,   10,   10,   10,      0,    0),
            new EnemyKind("VENISON",           111,   40,   40,   99,    777,  150),
        };
    }
    
    /**
     * Begins a fight with an enemy or boss.
     * @param hero The hero character.
     * @param explored If the room has already been visited.
     */
    public void interact(Hero hero, boolean explored)
    {
        if(explored){
            System.out.println("You quietly shuffle past the corpse you left behind.");
            return;
        }
        
        Enemy enemy;
        if(!hero.isExiting()){
            int enemyIndex = random.nextInt(enemies.length);
            enemy = new Enemy(enemies[enemyIndex], difficulty);
            System.out.println("You enter a bloodstained room, drawing your sword.\n");
        }
        else{
            if(difficulty <= bosses.length){
                int bossIndex = random.nextInt(bosses.length);
                enemy = new Enemy(bosses[difficulty - 1], difficulty);
                System.out.println("You've reached the exit to the next floor. A massive, ornate chasm lies in front of you.");
                System.out.println("You jump in. You feel a presence with you as you fall...\n"+
                                   "You hear a murmur...!\n");
            }
            else{
                int enemyIndex = random.nextInt(enemies.length);
                enemy = new Enemy(enemies[enemyIndex], difficulty*2);
                System.out.println("You've reached yet another exit. You throw yourself in.");
                System.out.println("The fall is quiet.\n"+
                                   "...and yet...\n");
            }
        }
        
        hero.pressToContinue();
        fight(hero, enemy);
        hero.incrementBattles();
        return;
    }
    
    /**
     * Begins a fight without an intro.
     * @param hero The hero character.
     */
    public void interactQuiet(Hero hero)
    {
        Enemy enemy;
        if(!hero.isExiting()){
            int enemyIndex = random.nextInt(enemies.length);
            enemy = new Enemy(enemies[enemyIndex], difficulty);
        }
        else{
            if(difficulty <= bosses.length){
                int bossIndex = random.nextInt(bosses.length);
                enemy = new Enemy(bosses[difficulty - 1], difficulty);
            }
            else{
                int enemyIndex = random.nextInt(enemies.length);
                enemy = new Enemy(enemies[enemyIndex], difficulty*2);
            }
        }
        
        fight(hero, enemy);
        return;
    }
    
    public void fight(Hero hero, Enemy enemy){
        System.out.println("!===[[ o={===--- FIGHT - -=- <o> ]]===!");
        System.out.println(enemy.getName() + " has come to challenge you!\n");
        enemy.printStats();
        hero.printStats();
        hero.pressToContinue();
        
        int turn = 1;
        System.out.println("\n[TURN #" + turn + "] - - - - - -");
        if(enemy.getSPD() > hero.getSPD()){
            System.out.println("The enemy is faster! " + enemy.getName() + " attacks first!");
            enemy.attack(hero);
            turn++;
            while(hero.getHP() > 0 && enemy.getHP() > 0){
                System.out.println("\n[TURN #" + turn + "] - - - - - -");
                hero.attack(enemy);
                if(enemy.getHP() > 0){
                    enemy.attack(hero);
                }
                turn++;
            }
        }
        else if(enemy.getSPD() <= hero.getSPD()){
            System.out.println("You're faster! You attack first!"); 
            hero.attack(enemy);
            turn++;
            while(hero.getHP() > 0 && enemy.getHP() > 0){
                System.out.println("\n[TURN #" + turn + "] - - - - - -");
                enemy.attack(hero);
                if(enemy.getHP() > 0){
                    hero.attack(enemy);   
                }
                turn++;
            }
        }
        
        System.out.println();
        if(hero.getHP() > 0){
            hero.winRewards(enemy);
        }
        else{
            System.out.println("-=- <(YOU LOST)> -=-");
            System.out.println("You collapse. It seems you have met your end...");
            System.out.println("[YOU FALL ASLEEP TO BELLS RINGING]");
            hero.pressToContinue();
        }
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

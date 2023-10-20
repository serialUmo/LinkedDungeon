import java.util.Scanner;
public class EnemyTest
{
    public static void main(String[] args)
    {
        BattleEngine battle = new BattleEngine();
        Hero hero = new Hero();
        
        Scanner scan = new Scanner(System.in);
        
        for(int i = 0; i < 7; i++){
            hero.setExiting(true);
            battle.incrementDifficulty();
            battle.interact(hero, false);
        }
        battle.interact(hero, true);
    }
}
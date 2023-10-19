public class EnemyTest
{
    public static void main(String[] args)
    {
        BattleEngine battle = new BattleEngine();
        Hero hero = new Hero();
        
        for(int i = 0; i < 10; i++){
            battle.interact(hero, 3);
            battle.incrementDifficulty();
        }
    }
}
public class TrapTest
{
    public static void main(String[] args)
    {
        TrapEngine trap = new TrapEngine();
        Hero hero = new Hero();
        
        for(int i = 0; i < 10; i++){
            trap.interact(hero);
        }
    }
}
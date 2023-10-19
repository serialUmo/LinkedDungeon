public class ItemTest
{
    public static void main(String[] args)
    {
        ItemEngine item = new ItemEngine();
        Hero hero = new Hero();
        
        for(int i = 0; i < 10; i++){
            item.interact(hero);
        }
    }
}
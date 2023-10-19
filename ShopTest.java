public class ShopTest
{
    public static void main(String[] args)
    {
        ShopEngine shop = new ShopEngine();
        Hero hero = new Hero();
        hero.setMoney(1000);
        
        shop.interact(hero);
    }
}
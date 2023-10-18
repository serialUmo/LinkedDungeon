import java.util.Random;
import java.util.Scanner;

public class ShopEngine implements Engine
{
    private Ware[] wares;
    private Ware freebie; //For when the hero is broke.
    
    private Random random;
    private Scanner scan;
    
    //Constructs the engine, as well as all the possible wares to buy.
    public ShopEngine()
    {
        random = new Random();
        scan = new Scanner(System.in);
        
        Ware[] wares =
        {          // NAME,                 HP   mHP   ATK   DEF   SPD   MONEY
            new Ware("HP Potion",           20,    0,    0,    0,    0,    -30),
            new Ware("Greater HP Potion",   50,    0,    0,    0,    0,    -60),
            new Ware("Vigor Potion",       100,   20,    0,    0,    0,   -111),
            new Ware("Armor Upgrade",       20,    0,    0,   10,    0,   -100),
            new Ware("Sword Upgrade",        0,    0,    5,    0,    0,    -75),
            new Ware("Speed Drink",          5,    0,    0,    0,    3,    -50),
        };
        
        freebie = new Ware("Small Candy", 10, 0, 0, 0, 0, 0);
    }

    /**
     * Begins a shopping segment where the player may buy wares.
     */
    public void interact(Hero hero){
        System.out.println("The entrance to this room is strangely well-lit and rustic. The environment changes to something similar to that of a cabin.\n"+
                           "A silent, shadowy figure motions to you from behind a counter. It points you towards a selection of goods and services.");
        
        int count = 0;   
        while(true){
            for(int i = 1; i <= wares.length; i++){
                System.out.println(i + ") " + wares[i-1].getName() + " $" + wares[i-1].getCost());
            }
            System.out.println("MONEY: " + hero.getMoney());
            System.out.println("(type any number from 1 to " + wares.length + " to buy. type '0' to exit shop.");
            
            int input; 
            do{
                input = scan.nextInt();
            }
            while((input > wares.length) || (input < 0));
            
            if(input == 0){
                if(count != 0){
                    System.out.println("The figure hands you your receipt. After putting away your wallet, you look over to thank them, but they've vanished...");
                }
                else{
                    System.out.println("You sheepishly tell the figure that you don't actually want anything. The figure peers at your wallet.");
                    if(hero.getMoney() > 30){
                        System.out.println("The figure hisses at you and vanishes. Talk about bad customer service...");
                    }
                    else{
                        System.out.println("The figure looks at you with pity. It gives you a coupon for a free candy. Thanks...");
                        System.out.print("...");
                        buy(hero, freebie);
                    }
                }
                return;
            }
            
            if(hero.getMoney() < wares[input].getCost()){
                System.out.println("You don't have enough for that!");
            }
            else{
                buy(hero, wares[input]);
                count++;
            }
        }
    }
    private void buy(Hero hero, Ware ware){
        int[] stats = hero.getStats();
        int[] changes = ware.getStatChanges();
        
        System.out.println("You bought the " + ware.getName() + ".");
        
        if(changes[0] != 0){
            System.out.print("HP: " + stats[0] + " + " + changes[0]);
            System.out.print("/" + stats[1]);
            if(changes[1] != 0){
                System.out.print(" + " + changes[1]);
            }
            System.out.println();
        }
        else if(changes[1] != 0){
            System.out.println("HP: " + stats[0] + "/" + stats[1] + " + " + changes[1]);
        }
        
        if(changes[2] != 0){
                System.out.println("ATK: " + stats[2] + " + " + changes[2]);
        }
        if(changes[3] != 0){
                System.out.println("DEF: " + stats[3] + " + " + changes[3]);
        }
        if(changes[4] != 0){
                System.out.println("SPD: " + stats[4] + " + " + changes[4]);
        }
        if(changes[5] != 0){
                System.out.println("MONEY: " + stats[5] + " - " + (-1 * changes[5]));
        }
            
        for(int i = 0; i < stats.length; i++){
            stats[i] += changes[i];
        }
        hero.setStats(stats);
    }
    
    public String toString(){
        return "$";
    }
}

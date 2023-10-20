import java.util.Random;
import java.util.Scanner;

public class ShopEngine implements Engine
{
    private Ware[] wares;
    private Ware freebie; //For when the hero is broke.
    
    private Random random;
    private Scanner scan;
    
    /**
     * Constructs the engine, as well as all the possible wares to buy.
     */
    public ShopEngine()
    {
        random = new Random();
        scan = new Scanner(System.in);
        
        wares = new Ware[]
        {          // NAME,                 HP   mHP   ATK   DEF   SPD   MONEY
            new Ware("HP Potion",           25,    0,    0,    0,    0,    -30),
            new Ware("Greater HP Potion",   50,    0,    0,    0,    0,    -55),
            new Ware("Vigor Potion",       100,   20,    0,    0,    0,   -111),
            new Ware("Armor Upgrade",        0,    0,    0,    2,    0,    -65),
            new Ware("Sword Upgrade",        0,    0,    3,    0,    0,    -70),
            new Ware("Speed Drink",         10,    0,    0,    0,    3,    -50)
        };
        
        freebie = new Ware("Small Candy", 10, 0, 0, 0, 0, 0);
    }

    /**
     * Begins a shopping segment where the player may buy wares.
     * @param hero The hero who's going to be buying.
     * @param explored If the room has already been visited.
     */
    public void interact(Hero hero, boolean explored){
        if(explored){
            System.out.println("All the lights in the shop have gone out, and the shopkeeper is nowhere to be seen.");
            return;
        }
        
        System.out.println("The entrance to this room is strangely well-lit and rustic. The environment changes to something similar to that of a cabin.\n"+
                           "A silent, shadowy figure motions to you from behind a counter. It points you towards a selection of goods and services.\n");
        
        int count = 0;   
        while(true){
            System.out.println("<>=-=-={SHOP}=-=-=<>");
            for(int i = 1; i <= wares.length; i++){
                System.out.println(i + ") " + wares[i-1].getName() + " ~ $" + (-1 * wares[i-1].getMoneyChange()));
            }
            System.out.println("<>=-=-=-=<>=-=-=-=<>");
            
            System.out.println("MONEY: " + hero.getMoney());
            
            int input; 
            do{
                System.out.println("(type 1 to " + wares.length + " to buy. type '0' to exit shop. type 'S' for STATS.)");
                String command = scan.nextLine();
                try{
                    input = Integer.parseInt(command);
                }
                catch(NumberFormatException e){
                    if(command.equalsIgnoreCase("S")){
                        hero.printStats();
                    }
                    input = -1;
                }
            }
            while((input > wares.length) || (input < 0));
            System.out.println();
            
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
                
                System.out.println("You move on with your journey.");
                if(hero.getHP() <= 0){
                    System.out.println("As you leave, you feel a sudden pain in your chest!\n"+
                                       "You feel as though your purchases weren't very wise...!");
                    System.out.println("[SOMETHING IN THE CEILING IS LAUGHING HYSTERICALLY]");
                    hero.pressToContinue();
                }
                return;
            }
            
            if(hero.getMoney() < (-1 * wares[input - 1].getMoneyChange())){
                System.out.println("You don't have enough for that!\n");
            }
            else{
                buy(hero, wares[input - 1]);
                count++;
            }
        }
        
        
    }
    private void buy(Hero hero, Ware ware){
        //Print purchase dialogue.
        System.out.println("You bought the " + ware.getName() + ".");
        
        //Change hero's stats with the purchased item.
        hero.changeStats(ware);
        hero.incrementWares();
    }
    
    public String getMapIcon(){
        return "S";
    }
}

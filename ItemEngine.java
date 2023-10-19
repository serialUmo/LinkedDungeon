import java.util.Random;
import java.util.Scanner;
/**
 * An room engine that, when interacted with, will have the Hero interact with an Item.
 */
public class ItemEngine implements Engine
{
    private Item[] items;
    
    private Random random;
    private Scanner scan;

    /**
     * Constructs the engine, as well as all the different types of items.
     */
    public ItemEngine()
    {
        random = new Random();
        scan = new Scanner(System.in);
        
        items = new Item[]
        {          // DIALOGUE,                                                                                    HP   mHP   ATK   DEF   SPD   MONEY
            new Item("You've been searching and searching, but you found nothing. That's lame...",                  0,    0,    0,    0,    0,      0),
            new Item("[DEF] You found an old suit of armor. You think it's decor, but you wear it anyways.",        0,    0,    0,    5,   -1,      0),
            new Item("[DEF] You found a ruby encrusted shield. It's weak, but you feel a slight warmth from it.",   0,    5,    0,    3,    0,      0),
            new Item("[HP+] You didn't find anything except for a crack in the ceiling. The sunlight is warm...",  10,    0,    0,    0,    0,      0),
            new Item("[HP+] There's a spilled potion on the floor. You begrudgingly drink it.",                    20,    5,    0,    0,    0,      0),
            new Item("[mHP] You find a small parcel of light. You absorb it, and you feel a lot better!.",         50,   15,    0,    0,    0,      0),
            new Item("[ATK] You found some old whetstones. You polish your blade and cast them aside.",             0,    0,    8,    0,    0,      0),
            new Item("[SPD] You found a strange tin canister. You drink the contents, and your vision sharpens.",   0,    0,    1,    0,    3,      0),
            new Item("[HP+] Following a trail of steam, you found a hot spring! All your pain melts away...",     999,    0,    0,    0,    0,      0),
            new Item("[ATK] You stumble upon a huge chest, and open to find a shinier sword! Has a guard, too!",    0,    0,   10,    5,    0,      0),
            new Item("[$$$] A corpse... with a bloodied sack of money. They probably don't need this anymore.",     0,    0,    0,    0,    0,    100),
            new Item("[!!!] While trying to reach an empty chest, a spear trap triggered and impaled your foot.", -10,    0,    0,    0,   -3,      0),
            new Item("[!!!] You find a small parcel of light. You try to absorb it, but it feels like fire...!",  -15,  -10,    0,    0,    0,      0),
            new Item("[!!!] There's a crack in the floor. You reach inside, but something bites your hand...!",    -5,    0,    0,   -2,    0,      0),
            new Item("[$$-] You find nothing. Someone taps your shoulder.",                                         0,    0,    0,    0,    0,    -75),
            new Item("[   ] Something's breathing. You immediately leave.",                                         0,    0,    0,    0,    0,      0),
            new Item("[   ] There's a crack in the floor. You look down, and someone looks back.",                  0,    0,    0,    0,    0,      0),
            new Item("[   ] The walls in this room are shrinking. You brush past someone unseen as you flee.",      0,    0,    0,    0,    0,      0),
            new Item("[   ] The room cuts abruptly to an abyss. There's no seam between the floor and the void.",   0,    0,    0,    0,    0,      0),
        };
    }
    
    /**
     * Begins a segment where the player can choose if they want to take a risk for a reward.
     * @param hero The hero that's going to get an item.
     */
    public void interact(Hero hero)
    {
        System.out.println("Upon entering the room, you notice that the air is still, and the way forward is right ahead.\n"+
                           "However, your map says there's something special about this room. Dare to find out what it could be?\n"+
                           "(Y/N) ('S' for STATS)");
        String input;
        do{
            input = scan.nextLine();
            if(input.equalsIgnoreCase("S")){
                hero.printStats();
            }
        }
        while(!(input.equalsIgnoreCase("Y") || input.equalsIgnoreCase("N")));
        
        if(input.equalsIgnoreCase("Y")){
            System.out.println("You begin searching the room for anything of note.");
            searchForItem(hero);
        }
        else{
            System.out.println("Not wanting to risk it, you move on with your journey. You hear a mechanism, and something unseen within the room shatters.");
            System.out.println();
            return;
        }
        
        System.out.println("You move on with your journey.");
        if(hero.getHP() == 0){
            System.out.println("...but now, you feel faint...");
            System.out.println("[THE LIGHTS CRY OUT AS YOU SLOWLY COLLAPSE]");
        }
        System.out.println();
    }
    private void searchForItem(Hero hero){
        //Get random item index
        int itemIndex = random.nextInt(items.length);
        
        //Print that item's dialogue
        System.out.println();
        System.out.println(items[itemIndex].getDialogue());
        
        //Change hero's stats with item
        hero.changeStats(items[itemIndex]);
    }
    
    public String getMapIcon(){
        return "I";
    }
}
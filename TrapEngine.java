import java.util.Random;
import java.util.Scanner;
/**
 * An room engine that, when interacted with, will have the Hero interact with a Trap.
 */
public class TrapEngine implements Engine
{
    private Trap[] traps;
    
    private Random random;
    private Scanner scan;
    
    private BattleEngine darkPathBattle;
    
    /**
     * Constructs the engine, as well as all the different types of traps.
     */
    public TrapEngine()
    {
        random = new Random();
        scan = new Scanner(System.in);
        darkPathBattle = new BattleEngine();
        
        traps = new Trap[]
        {          // DIALOGUE,                                                                                     HP   mHP   ATK   DEF   SPD   MONEY
            new Trap("[   ] You step on a pressure plate, and suddenly...! Nothing happens. Whew.",                  0,    0,    0,    0,    0,      0),
            new Trap("[!!!] A multitude of arrows come flying at you! The tips are golden, though...",             -15,    0,    0,    0,    0,     30),
            new Trap("[!!!] There's a crack in the ceiling. The moonlight is cold... You feel drowsy.",              0,    0,    0,    0,   -2,      0),
            new Trap("[!!!] A mechanism activates, and a toxic gas enters your lungs! Your armor corroded...",     -10,   -5,    0,   -5,    0,      0),
            new Trap("[!!!] The lights go out, and your body goes numb. The lights return to reveal fresh wounds.",-25,   -5,    0,    0,    0,      0),
            new Trap("[!!!] You find a candle-lit mirror. Your reflection smiles maniacally. Your hands shake...",  -2,    0,   -2,    0,    0,      0),
            new Trap("[!!!] You found a strange tin canister. You drink the contents... and your heart palpitates.",-15,   0,    0,    0,    4,      0),
            new Trap("[<o>] SOMETHING IN THE AIR ENTERS YOUR BODY; AN UNSEEN FORCE TWISTS ALL YOUR WOUNDS SHUT",   999,  -40,    0,    0,    0,      0),
            new Trap("[...] You find a flyer with some coupons attached. You get a papercut while taking one. Ow.", -1,    0,    0,    0,    0,     10),
            new Trap("[<o>] A HORRIBLE CREATURE, IMPERVIOUS TO ALL ATTACKS, GRINS AT YOU. YOUR ARMOR MELTS YOUR SKIN",0,   0,    0,  -10,    0,      0),
            new Trap("[!!!] A bear trap activates, and mangles your ankle!!!",                                     -20,    0,    0,    0,   -4,      0),
            new Trap("[!!!] An innocent looking hallway suddenly greeted you with a boulder. "+
                            "Your armor took the brunt of the force.",                                             -10,    0,    0,   -5,    0,      0),
            new Trap("[!!!] You sprained your ankle, totally because of a trap. You did find some money, though.",  -2,    0,    0,    0,   -2,     50),
            new Trap("[!!!] Suddenly, the room fills with water! You pull a drain plug, but your weapon rusted.",    0,    0,   -2,    0,    0,      0),
            new Trap("[!!!] Your surrounded by flames! Your flammables burn away!",                                -15,    0,   -1,   -2,    0,    -20),
            new Trap("[!!!] There's a crack in the floor. A black tendril reaches out and steals your gold.",        0,    0,    0,    0,    0,    -30),
            new Trap("[!!!] Unable to avoid a strange purple light, you step into it and wither away...",            0,  -10,    0,    0,    0,      0),
            new Trap("[   ] Engravings line the walls. \"WHAT'S YOUR NAME?\" Your ears ring.",                       0,    0,    0,    0,    0,      0),
            new Trap("[   ] Muffles cackles can be heard within the walls. When you try to listen, they stop.",      0,    0,    0,    0,    0,      0),
            new Trap("[   ] Something's behind you. You don't turn around, and keep walking.",                       0,    0,    0,    0,    0,      0),
            new Trap("[   ] The walls, floors, and ceiling are encrusted in salt here... You need to crawl through.",0,    0,    0,    0,    0,      0),
        };
    }
    
    /**
     * Begins a segment where the player can choose if they want to take a certain risk, or the possibility of safety.
     * @param hero The hero that's going to suffer through a trap.
     */
    public void interact(Hero hero)
    {
        System.out.println("As you enter the next room, you come to a halt.\n"+
                           "Ahead of you lies a fork in the road. The left path is bloodied, and the right is dark.\n"+
                           "Instinct tells you that there's definitely a trap to the left, but something worse may lurk in the dark.\n"+
                           "What will you do?\n\n"+
                           "!'!'''!'''!!''TRAP''!''!'!''!'!' \n"+
                           "1) Brave the bloody path\n"+
                           "2) Try the dark path instead\n"+
                           "''!''!!'!''!!'!'!''!'!!'!'!''!'' \n"+
                           "(type 1, 2, or 'S' for STATS)");
        
        int input; 
        do{
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
        while((input != 1) && (input != 2));
        
        if(input == 1){
            System.out.println("Favoring the certainty, you brave the bloody path.\n");
            sufferTrap(hero);
        }
        else{
            System.out.println("Favoring the possibility of safety, you enter the dark.\n");
            
            int misfortune = random.nextInt(5) + 1;
            if(misfortune < 3){
                System.out.println("You reach the end of the darkness unscathed!");
                System.out.println();
                return;
            }
            else if(misfortune == 3){
                sufferTrap(hero);
                System.out.println("You try your best to try and reach the end of this cursed path...");
            }
            else if(misfortune == 4){
                System.out.println("You fumble your way through the darkness until you hear a murmur.");
                darkPathBattle.interact(hero);
            }
            else if(misfortune == 5){
                sufferTrap(hero);
                System.out.println("You try your best to try and reach the end of this cursed path...");
                if(hero.getHP() == 0){
                    System.out.println("[BUT DARKER AND DARKER SHADOWS SUFFOCATE YOU]");
                    System.out.println();
                    return;
                }
                System.out.println("...but just when you think you've reached the end...!");
                darkPathBattle.interact(hero);
            }
        }
        
        System.out.println("You've managed to finally reach the end of the path... The next room lies ahead.");
        if(hero.getHP() == 0){
            System.out.println("...but you longer have the energy to go on. Maybe you can catch a breath...?");
            System.out.println("[A HORRIFYING ROAR CAN BE HEARD UNDER THE FLOOR WHERE YOU LIE]");
        }
        System.out.println();
        return;
    }
    private void sufferTrap(Hero hero){
        //Get random trap index
        int trapIndex = random.nextInt(traps.length);
        
        //Print that trap's dialogue
        System.out.println(traps[trapIndex].getDialogue());
        
        //Change hero's stats with trap
        hero.changeStats(traps[trapIndex]);
    }
    
    public String getMapIcon(){
        if(random.nextInt(2) == 1){
            return "T";
        }
        else{
            //Trap fakeout.
            return "I";
        }
    }
}
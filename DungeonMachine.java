import java.util.Scanner;

/**
 * DungeonMachine acts to put Dungeon into motion; meaning, it is in charge of letting the player traverse through Dungeon.
 */
public class DungeonMachine
{
    Dungeon dungeon;
    Hero hero;
    Scanner scan;
    
    public DungeonMachine(){
        scan = new Scanner(System.in);
        dungeon = new Dungeon();
    }
    
    public void run()
    {   
        System.out.println("Y  Y OOOO U  U RRR    N NN  AA  M  M EEEE\n"+
                           "Y  Y O  O U  U R  R   NN N A  A MMMM E   \n"+
                           " YY  O  O U  U RRR    N  N AAAA M  M EEEE\n"+
                           " YY  O  O U  U R  R   N  N A  A M  M E   \n"+
                           " YY  OOOO  UU  R  R   N  N A  A M  M EEEE\n"+
                           "===================<o>===================");
        
        selectHero();
        System.out.println("You awake in a cold, damp cell. You... don't exactly know why you're here.\nJust a minute ago, you were out slaying beasts...\n"+
                           "Who ever encaptured you, they had the decency to leave all your equipment on you, as well as a map. Strange.\n"+
                           "It seems this complex is cylindical and seperated into floors. You leave and try to find an exit.\n");
        
        dungeon.generateDungeon(7);
        while(hero.getHP() > 0){
            if(hero.isExiting()){
                dungeon.generateDungeon(7);
                hero.setExiting(false);
            }
            
            System.out.println("vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv");
            dungeon.printMap();
            System.out.println("You can go 1) LEFT or 2) RIGHT on this circular floor. Which way will you go?");
                     
            String command;
            do{
                System.out.println("(1: left or 2: right) (S for STATS) (M for map info)");
                command = scan.nextLine();
            
                if(command.equalsIgnoreCase("S")){
                    hero.printStats();
                }
                if(command.equalsIgnoreCase("M")){
                    dungeon.printMap();
                    dungeon.printMapKey();
                    System.out.println();
                }
            }while(!(command.equals("1") || command.equals("2")));
            
            if(command.equals("1")){
                System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
                dungeon.goLeft(hero);
            }
            else if(command.equals("2")){
                System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
                dungeon.goRight(hero);
            }
        }
        System.out.println("Despite your best efforts...");
        System.out.println("Something calls out to you.\n");
        
        System.out.println("[WHAT IS YOUR NAME?] (type)");
        hero.printObituary(scan.nextLine(), dungeon.getFloor());
    }
    
    public void selectHero(){
        System.out.println("Select a sin.");
        System.out.println("1) VIOLENCE");
        System.out.println("2) REPULSION");
        System.out.println("3) HUNGER");
        System.out.println("4) SALACIOUSNESS");
        
        int input;
        do{
            System.out.println("(type 1 - 4)");
            String command = scan.nextLine();
            try{
                input = Integer.parseInt(command);
            }
            catch(NumberFormatException e){
                input = -1;
            }
        }while((input > 4) || (input < 0));
        
        if(input == 1){
            hero = new Hero(80, 80, 5, 0, 3, 35, 10);
        }
        if(input == 2){
            hero = new Hero(100, 100, 3, 10, 0, 75, 12);
        }
        if(input == 3){
            hero = new Hero(40, 75, 2, 2, 2, 0, 5);
        }
        if(input == 4){
            hero = new Hero(70, 150, 1, 1, -1, 150, 15);
        }
        
        System.out.println("\nIt is chosen.");
        hero.printStats();
        hero.pressToContinue();
    }
}

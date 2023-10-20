import java.util.Random;
public class Dungeon
{
    private Random random;
    private Room head;
    private Room tail;
    
    private int floor;
    private int size;
    private Room heroAt;
    
    private BattleEngine battleRoom;
    private ItemEngine itemRoom;
    private TrapEngine trapRoom;
    private ShopEngine shopRoom;
    private ExitEngine exitRoom;
    
    /**
     * Constructs Dungeon.
     * 
     * @param hero The hero that's going to be exploring the dungeon.
     */
    public Dungeon(){
        floor = 0;
        size = 0;
        
        random = new Random();
        
        battleRoom = new BattleEngine();
        itemRoom = new ItemEngine();
        trapRoom = new TrapEngine();
        shopRoom = new ShopEngine();
        exitRoom = new ExitEngine();
    }
    
    /**
     * Generates a Dungeon floor, which has a branching structure of Rooms that converge into a boss room.
     * 
     * @param size The total amount of Rooms within a Dungeon floor, excluding the boss room.
     */
    public void generateDungeon(int length){
        floor++;
        battleRoom.incrementDifficulty();
        resetDungeon();
        heroAt = null;
        
        int dice;
        for(int i = 0; i <= length; i++){
            dice = random.nextInt(10) + 1;
            if (i == length/2){
                add(exitRoom);
            }
            else if(dice < 5){
                add(battleRoom);
            }
            else if(dice < 8){
                add(itemRoom);
            }
            else if(dice < 9){
                add(trapRoom);
            }
            else if(dice <= 10){
                add(shopRoom);
            }
        }
        
    }
    
    private void resetDungeon(){
        head = null;
        size = 0;
    }
    
    public void printMap(){
        String map;
        if (heroAt != null){
            map = "...-";
        }
        else{
            map = "...-(RIGHT ->)-";
        }
        
        Room room = head;
        for(int i = 0; i < size && room != null; i++){
            if (heroAt == room){
                map += "YOU-";
            }
            else{
                map += room + "-";
            }
            room = room.getNext();
        }
        
        if (heroAt != null){
            map += "...";
        }
        else{
            map += "(<- LEFT)-...";
        }
        
        System.out.println("FLOOR: " + floor + "\n\n\t"
                           +           map);
    }
    public void printMapKey(){
        System.out.println("KEY:\nYOU = ARE HERE\n\nB = BATTLE\nI = ITEM\nT = TRAP\nS = SHOP\nX = EXIT!!!\nlowercase = explored");
    }
    
    private void add(Engine engine){
        Room newRoom = new Room(engine); 
        if (size == 0) { 
            head = newRoom;
            tail = head;
        } 
        else { 
            newRoom.setNext(head);
            head = newRoom;
        } 
        size++;
    }
    
    public void goLeft(Hero hero){
        //Initial position
        if(heroAt == null){
            heroAt = tail;
        }
        else{
            heroAt = heroAt.getPrev();
            
            //Looping around
            if(heroAt == null){
                heroAt = tail;
            }
        }
        heroAt.interact(hero);
    }
    public void goRight(Hero hero){
        //Initial position
        if(heroAt == null){
            heroAt = head;
        }
        else{
            heroAt = heroAt.getNext();
            
            //Looping around
            if(heroAt == null){
                heroAt = head;
            }
        }
        heroAt.interact(hero);
    }
    
    //Getters
    public int getFloor()
    {return floor;}
    public int getSize()
    {return size;}
    
    //Prints a map.
    public String toString()
    {return "TBD";}
}
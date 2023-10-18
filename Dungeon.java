import java.util.Random;
public class Dungeon
{
    private Random generator;
    private Room head;
    
    private int floor;
    private int size;
    
    private int items;
    private int battles;
    private int shops;
    
    private BattleEngine battleRoom;
    private ItemEngine itemRoom;
    private ShopEngine shopRoom;
    
    
    public Dungeon(){
        floor = 0;
        size = 0;
        generator = new Random();
        
        battleRoom = new BattleEngine();
        itemRoom = new ItemEngine();
        shopRoom = new ShopEngine();
    }
    
    /**
     * Generates a Dungeon floor, which has a branching structure of Rooms that converge into a boss room.
     * 
     * @param size The total amount of Rooms within a Dungeon floor, excluding the boss room.
     */
    public void generateDungeon(int length){
        floor++;
        resetDungeon();
        battleRoom.incrementDifficulty();
        
        
    }
    
    private void resetDungeon(){
        head = null;
        size = 0;
    }
    
    private void add(String dataInput){
        
    }
    
    //Getters
    public int getFloor()
    {return floor;}
    public int getSize()
    {return size;}
    
    public int getItemCount()
    {return items;}
    public int getBattleCount()
    {return battles;}
    public int getShopCount()
    {return shops;}
    
    //Prints a map.
    public String toString()
    {return "TBD";}
}
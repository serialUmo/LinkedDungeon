public class MapTest
{
    public static void main(String[] args)
    {
        Dungeon dungeon = new Dungeon();
        
        for(int i = 5; i < 15; i++){
            dungeon.generateDungeon(i);
            dungeon.printMap();
        }
        dungeon.printMapKey();
    }
}

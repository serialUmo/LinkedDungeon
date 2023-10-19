public class Room <E>
{
    private Engine event;
    private Room next;
    
    private String mapIcon;
    private boolean explored;
    
    public Room(Engine type){
        event = type;
        explored = false;
    }
    
    public Room getNext(){
        return next;
    }
    public void setNext(Engine type){
        next = new Room(type);
    }
    
    public void interact(Hero hero){
        event.interact(hero);
        
        mapIcon = mapIcon.toLowerCase();
        explored = true;
    }
    
    public boolean isExplored(){
        return explored;
    }
    public String toString(){
        return mapIcon;
    }
}
public class Room
{
    private Engine event;
    private Room next;
    private Room prev;
    
    private String mapIcon;
    private boolean explored;
    
    public Room(Engine type){  
        mapIcon = type.getMapIcon();
        
        event = type;
        explored = false;
    }
    
    public Room getNext(){
        return next;
    }
    public void setNext(Room room){
        room.setPrev(this);
        next = room;
    }
    public Room getPrev(){
        return prev;
    }
    public void setPrev(Room room){
        prev = room;
    }
    
    public void interact(Hero hero){
        event.interact(hero, explored);
        
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
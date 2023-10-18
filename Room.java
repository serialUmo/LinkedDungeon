public class Room <E>
{
    private Engine event;
    private Room next;
    
    public Room(Engine type){
        event = type;   
    }
    
    public Room getNext(){
        return next;
    }
    public void setNext(Engine type){
        next = new Room(type);
    }
    
    public void interact(Hero hero){
        event.interact(hero);
    }
    
    public String toString(){
        return event.toString();
    }
}
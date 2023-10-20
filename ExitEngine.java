/**
 * Holds a tougher "boss" enemy, that when defeated, leads to the next floor.
 */
public class ExitEngine implements Engine
{
    BattleEngine bossBattle;

    /**
     * Constructs the ExitEngine.
     */
    public ExitEngine()
    {
        bossBattle = new BattleEngine();
    }
    
    /**
     * Begins a boss fight that, when finished, leads to the next floor.
     * @param hero The hero character.
     * @param explored If the room has already been visited.
     */
    public void interact(Hero hero, boolean explored)
    {
        hero.setExiting(true);
        bossBattle.interact(hero, explored);
        if(hero.getHP() > 0){
            System.out.println("With the great beast slain, the endless fall begins to feel not-so-endless...");
            System.out.println("An unseen force gently puts you on your feet on the next floor. Onwards...");
        }
    }
    
    public String getMapIcon(){
        return "X";
    }
}

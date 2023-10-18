/**
 * Holds dialogue, as well as an array of stat changes to feed to the Hero.
 */
public class Item
{
    private String dialogue;
    private int[] statChanges;

    /**
     * Constructs an Item.
     * @param text What to print out when interacted with.
     * 
     * @param hp How much to change the Hero's HP.
     * @param maxHp How much to change the Hero's maximum HP.
     * @param atk How much to change the Hero's attack.
     * @param def How much to change the Hero's defense.
     * @param spd How much to change the Hero's speed.
     * @param money How much to change the Hero's money.
     */
    public Item(String text, int hp, int maxHp, int atk, int def, int spd, int money)
    {
        dialogue = text;
        statChanges = new int[]{hp, maxHp, atk, def, spd, money};
    }

    public String getDialogue(){
        return dialogue;
    }
    public int[] getStatChanges(){
        return statChanges;
    }
}

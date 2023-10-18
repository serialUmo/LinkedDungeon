/**
 * Holds a name of a ware, as well as an array of stat changes to feed to the Hero.
 */
public class Ware
{
    private String name;
    private int[] statChanges;

    /**
     * Constructs an Item.
     * @param name What the name of the ware is.
     * @param hp How much to change the Hero's HP.
     * @param maxHp How much to change the Hero's maximum HP.
     * @param atk How much to change the Hero's attack.
     * @param def How much to change the Hero's defense.
     * @param spd How much to change the Hero's speed.
     * @param money How much to change the Hero's money.
     */
    public Ware(String text, int hp, int maxHp, int atk, int def, int spd, int money)
    {
        name = text;
        statChanges = new int[]{hp, maxHp, atk, def, spd, money};
    }

    public String getName(){
        return name;
    }
    public int getCost(){
        return statChanges[5];
    }
    public int[] getStatChanges(){
        return statChanges;
    }
}

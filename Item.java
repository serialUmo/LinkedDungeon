/**
 * An Item is an item or event that usually positively changes the Hero's stats.
 */
public class Item implements StatChanger
{
    private String dialogue;
    private int hpChange;
    private int maxHpChange;
    private int atkChange;
    private int defChange;
    private int spdChange;
    private int moneyChange;

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
        hpChange = hp;
        maxHpChange = maxHp;
        atkChange = atk;
        defChange = def;
        spdChange = spd;
        moneyChange = money;
    }

    public String getDialogue()
    {return dialogue;}
    public int getHPChange()
    {return hpChange;}
    public int getMaxHPChange()
    {return maxHpChange;}
    public int getATKChange()
    {return atkChange;}
    public int getDEFChange()
    {return defChange;}
    public int getSPDChange()
    {return spdChange;}
    public int getMoneyChange()
    {return moneyChange;}
}

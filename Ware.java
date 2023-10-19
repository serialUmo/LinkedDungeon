/**
 * A Ware is something the Hero will buy with MONEY that will change their stats.
 */
public class Ware implements StatChanger
{
    private String name;
    private int hpChange;
    private int maxHpChange;
    private int atkChange;
    private int defChange;
    private int spdChange;
    private int moneyChange;

    /**
     * Constructs a Ware.
     * @param name What the name of the ware is.
     * 
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
        hpChange = hp;
        maxHpChange = maxHp;
        atkChange = atk;
        defChange = def;
        spdChange = spd;
        moneyChange = money;
    }

    public String getName(){
        return name;
    }
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
    
    public boolean isBlank(){
        if((hpChange != 0) || (maxHpChange != 0) || (atkChange != 0) || (defChange != 0) || (spdChange != 0) || (moneyChange != 0)){
            return false;
        }
        else{
            return true;
        }
    }
}

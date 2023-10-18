/**
 * Describes an object that the Hero may consume to change their stats.
 */
public interface StatChanger
{
    public int getHPChange();
    public int getMaxHPChange();
    public int getATKChange();
    public int getDEFChange();
    public int getSPDChange();
    public int getMoneyChange();
}

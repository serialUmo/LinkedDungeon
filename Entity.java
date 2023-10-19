/**
 * An Entity is something that has Stats. It can act in fights.
 */
public interface Entity
{
    //Battle Functions
    public void damage(int dmg);
    public void heal(int heal);
    public void attack(Entity target);
    //Getters
    public String getName();
    public int getHP();
    public int getMaxHP();
    public int getATK();
    public int getDEF();
    public int getSPD();
    public int getMoney();
    public int getLvl();
    public int getExp();
    
    //Setters
    public void setName(String change);
    public void setHP(int change);
    public void setMaxHP(int change);
    public void setATK(int change);
    public void setDEF(int change);
    public void setSPD(int change);
    public void setMoney(int change);
    public void setExp(int change);
    
    public void printStats();
}

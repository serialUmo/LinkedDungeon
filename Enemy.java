public class Enemy
{
    private EnemyKind kind;
    private int hp;
    private int maxHp;
    private int atk;
    private int def;
    private int spd;
    private int money;
    
    //Default
    public Enemy (){
        kind = new EnemyKind();
        hp = kind.getHP();
        maxHp = kind.getMaxHP();
        atk = kind.getATK();
        def = kind.getDEF();
        spd = kind.getSPD();
        money = kind.getMoney();
    }
    //Custom
    public Enemy (EnemyKind kind){
        this.kind = kind;
        hp = kind.getHP();
        maxHp = kind.getMaxHP();
        atk = kind.getATK();
        def = kind.getDEF();
        spd = kind.getSPD();
        money = kind.getMoney();
    }
    
    //---Battle Functions---
    //Decreases HP by a DAMAGE value, which is mitigated by DEFENSE.
    public void damage(int dmg){
        hp -= Math.max(dmg - def, 0);
    }
    
    //Increase HP by a HEAL value.
    public void heal(int heal){
        hp += heal;
        if (hp > maxHp){
            hp = maxHp;
        }
    }
    
    //Damages a hero.
    public void attack(Hero hero){
        hero.damage(atk);
    }
    
    //Refills HP to maximum value.
    public void healFull(){
        hp = maxHp;
    }
    
    //---Changer Functions---
    
    //damage() and heal() should be used to change HP, but use changeHP() if you want undefendable damage.
    public void changeHP(int change)
    {hp += change;}
    public void changeMaxHP(int change)
    {maxHp += change;}
    public void changeATK(int change)
    {atk += change;}
    public void changeDEF(int change)
    {def += change;}
    public void changeSPD(int change)
    {spd += change;}
    public void changeMoney(int change){
        money += change;
        if (money < 0){
            money = 0;
        }
    }
    
    //Getters
    public String getName()
    {return kind.getName();}
    public int getHP()
    {return hp;}
    public int getMaxHP()
    {return maxHp;}
    public int getATK()
    {return atk;}
    public int getDEF()
    {return def;}
    public int getSPD()
    {return spd;}
    public int getMoney()
    {return money;}
    
    public String toString(){
        String stats = kind.getName() + "\n" +
                       "HP: " + hp +" / "+ maxHp + "\n" +
                       "ATK: " + atk  + "\n" +
                       "DEF: " + def + "\n" +
                       "SPD: " + spd + "\n" +
                       "SPOILS: " + money;
        return stats;
    }
}
public class Hero
{
    private String name;
    private int hp;
    private int maxHp;
    private int atk;
    private int def;
    private int spd;
    private int money;
    
    //private boolean defending;
    
    //Default
    public Hero (){
        name = "Stranger";
        hp = 100;
        maxHp = 100;
        atk = 10;
        def = 10;
        spd = 10;
        money = 0;
    }
    //Custom
    public Hero (String name, int hp, int maxHp, int atk, int def, int spd, int money){
        this.name = name;
        this.hp = hp;
        this.maxHp = maxHp;
        this.atk = atk;
        this.def = def;
        this.spd = spd;
        this.money = money;
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
    
    //Damages a monster.
    public void attack(Enemy enemy){
        enemy.damage(atk);
    }
    
    //Getters
    public String getName()
    {return name;}
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
    
    public int[] getStats(){
        int[] stats = new int[]{hp, maxHp, atk, def, spd, money};
        return stats;
    }
    public void setStats(int[] stats){
        hp = stats[0];
        maxHp = stats[1];
        atk = stats[2];
        def = stats[3];
        spd = stats[4];
        money = stats[5];
    }
    
    public String toString(){
        String stats = "HP: " + hp +" / "+ maxHp + "\n" +
                       "ATK: " + atk  + "\n" +
                       "DEF: " + def + "\n" +
                       "SPD: " + spd + "\n" +
                       "MONEY: " + money;
        return stats;
    }
}
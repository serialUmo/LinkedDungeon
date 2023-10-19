import java.util.Random;

public class Enemy implements Entity
{
    private EnemyKind kind;
    private String name;
    private int hp;
    private int maxHp;
    private int atk;
    private int def;
    private int spd;
    private int money;
    
    private int lvl;
    private int exp;
    
    private Random random;
    
    /**
     * Constructs a Monster object with default stats.
     */
    public Enemy (){
        random = new Random();
        
        kind = new EnemyKind();
        name = kind.getName();
        
        hp = kind.getHP();
        maxHp = kind.getMaxHP();
        atk = kind.getATK();
        def = kind.getDEF();
        spd = kind.getSPD();
        money = kind.getMoney();
        
        lvl = 1;
        exp = kind.getExp();
    }
    
    
    /**
     * Constructs a Monster object with a specified kind.
     * @param kind What type of enemy it is.
     * @param level What level enemy it is.
     */
    public Enemy (EnemyKind kind, int level){
        random = new Random();
        
        this.kind = kind;
        name = kind.getName();
        
        hp = kind.getHP();
        maxHp = kind.getMaxHP();
        atk = kind.getATK();
        def = kind.getDEF();
        spd = kind.getSPD();
        money = kind.getMoney();
        exp = kind.getExp();
        
        lvl = level;
        levelUp();
    }
    
    //---Battle Functions---
    /**
     * Decreases HP by an ATK value, which is mitigated by DEF.
     * @param dmg How much health to take away.
     */
    public void damage(int dmg){
        hp -= Math.max(dmg - def, 0);
    }
    
    /**
     * Heals the monster by a set amount.
     * @param heal How much to heal the monster.
     */
    public void heal(int heal){
        hp += heal;
        if (hp > maxHp){
            hp = maxHp;
        }
    }
    
    /**
     * Has the Monster attack an Entity.
     * @param target What entity to attack.
     */
    public void attack(Entity target){
        int attack = atk + ((random.nextInt(Math.abs(atk))/3) - atk/4);
        target.damage(attack);
    }

    /**
     * Increases the monster's stats depending on its level.
     */
    public void levelUp(){
        for (int i = 1; i < lvl; i++){
            maxHp += 2 + random.nextInt(5) + (maxHp*0.05);
            atk += 1 + random.nextInt(3) + (atk*0.1);
            def += 1 + random.nextInt(3) + (def*0.1);
            spd += 1 + random.nextInt(3) + (spd*0.1);
            money += 5 + (money * (Math.random() * 0.5));
            exp += 5 + (exp * (Math.random() * 0.75));
        }
        hp = maxHp;
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
    public int getLvl()
    {return lvl;}
    public int getExp()
    {return exp;}
    
    //Setters
    public void setName(String newName)
    {name = newName;}
    public void setHP(int newHp)
    {hp = newHp;}
    public void setMaxHP(int newMaxHp)
    {maxHp = newMaxHp;}
    public void setATK(int newAtk)
    {atk = newAtk;}
    public void setDEF(int newDef)
    {def = newDef;}
    public void setSPD(int newSpd)
    {spd = newSpd;}
    public void setMoney(int newMoney)
    {money = newMoney;}
    public void setExp(int newExp)
    {exp = newExp;}
    
    /**
     * Prints the monster's stats.
     */
    public void printStats(){
        System.out.println(">>===[ENEMY=STATS]===<<");
        System.out.println("Lv." + lvl + "\t\t" + kind.getName() + "\n" +
                           "HP: " + hp +" / "+ maxHp + "\n" +
                           "ATK: " + atk  + "\n" +
                           "DEF: " + def + "\n" +
                           "SPD: " + spd + "\n" +
                           "MONEY: " + money + "\n" +
                           "EXP DROPPED: " + exp);
        System.out.println("=={=====--   --=====}==\n");
    }
}
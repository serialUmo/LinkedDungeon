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
    
    /**
     * Constructs a Hero object with default stats.
     */
    public Hero (){
        name = "Stranger";
        hp = 100;
        maxHp = 100;
        atk = 10;
        def = 10;
        spd = 10;
        money = 0;
    }
    
    /**
     * Constructs a Hero object with the specified stats.
     * @param name What the hero's name is.
     * @param hp How much current HP the hero has. Cannot be greater than maxHp.
     * @param maxHp The maximum amount of HP the hero may have.
     * @param atk How much HP the hero takes away from a Monster per attack.
     * @param def How much damage is mitigated when attacked by a Monster.
     * @param spd When this value is higher than a Monster's, the hero attacks first.
     * @param money How much money the hero starts with.
     */
    public Hero (String name, int hp, int maxHp, int atk, int def, int spd, int money){
        this.name = name;
        this.hp = hp;
        this.maxHp = maxHp;
        if(this.hp > maxHp){
            this.hp = maxHp;
        }
        this.atk = atk;
        this.def = def;
        this.spd = spd;
        this.money = money;
    }
    
    //---Battle Functions---
    /**
     * Decreases HP by a DAMAGE value, which is mitigated by DEFENSE.
     * @param dmg How much health to take away.
     */
    public void damage(int dmg){
        hp -= Math.max(dmg - def, 0);
    }
    
    /**
     * Heals the hero by a set amount.
     * @param heal How much to heal the hero.
     */
    public void heal(int heal){
        hp += heal;
        if (hp > maxHp){
            hp = maxHp;
        }
    }
    
    /**
     * Has the Hero attack a Monster.
     * @param enemy What enemy to attack.
     */
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
    
    /**
     * Applies an array of stat changes to the hero's stats. Also prints the changed stats, and by how much.
     * @param statChanger The StatChanger that will affect the stats.
     */
    public void changeStats(StatChanger statChanger){
        int hpChange = statChanger.getHPChange();
        int maxHpChange = statChanger.getMaxHPChange();
        int atkChange = statChanger.getATKChange();
        int defChange = statChanger.getDEFChange();
        int spdChange = statChanger.getSPDChange();
        int moneyChange = statChanger.getMoneyChange();
        
        System.out.println("=+=======STATS=======+=");
        //HP, max HP
        if(hpChange != 0){
            System.out.print("HP: (" + hp + " + " + hpChange);
            System.out.print(")/");
            if(maxHpChange != 0){
                System.out.print("(" + maxHp + " + " + maxHpChange + ")");
            }
            else{
                System.out.print(maxHp);
            }
            System.out.println();
        }
        else if(maxHpChange != 0){
            System.out.println("HP: " + hp + "/(" + maxHp + " + " + maxHpChange +")");
        }
        hp = Math.min(Math.max(hp + hpChange, 0), maxHp);
        maxHp = Math.max(maxHp + maxHpChange, 0);
        
        //ATK
        if(atkChange != 0){
            System.out.println("ATK: " + atk + " + " + atkChange);
        }
        atk += atkChange;
        
        //DEF
        if(defChange != 0){
            System.out.println("DEF: " + def + " + " + defChange);
        }
        def += defChange;
        
        //SPD
        if(spdChange != 0){
            System.out.println("SPD: " + spd + " + " + spdChange);
        }
        spd += spdChange;
        
        //MONEY
        if(moneyChange != 0){
            System.out.println("MONEY: " + money + " + " + moneyChange);
        }
        System.out.println("=+=======-----=======+=");
        money = Math.max(money + moneyChange, 0);
    }
    
    /**
     * Returns a string of the hero's stats.
     */
    public String toString(){
        String stats = "HP: " + hp +" / "+ maxHp + "\n" +
                       "ATK: " + atk  + "\n" +
                       "DEF: " + def + "\n" +
                       "SPD: " + spd + "\n" +
                       "MONEY: " + money;
        return stats;
    }
}
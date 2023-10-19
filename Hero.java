import java.util.Random;
public class Hero implements Entity
{
    private String name;
    private int hp;
    private int maxHp;
    private int atk;
    private int def;
    private int spd;
    private int money;
    
    private int lvl;
    private int exp;
    private int expNext;
    
    //private boolean defending;
    
    private Random random;
    
    /**
     * Constructs a Hero object with default stats.
     */
    public Hero (){
        random = new Random();
        
        name = "Stranger";
        hp = 100;
        maxHp = 100;
        atk = 10;
        def = 10;
        spd = 10;
        money = 0;
        
        lvl = 0;
        exp = 0;
        expNext = 10;
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
     * @param firstLvlCost How much EXP is needed to level up. A low value will generally have faster initial leveling.
     */
    public Hero (String name, int hp, int maxHp, int atk, int def, int spd, int money, int firstLvlCost){
        random = new Random();
        
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
        
        lvl = 0;
        exp = 0;
        expNext = firstLvlCost;
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
     * Has the Hero attack an Entity.
     * @param target What entity to attack.
     */
    public void attack(Entity target){
        int attack = atk + ((random.nextInt(Math.abs(atk))/3) - atk/4);
        target.damage(attack);
    }
    
    /**
     * Increases the hero's stats per level.
     */
    public void levelUp(){
        if(exp < expNext){
            return;
        }
        
        for (int i = 1; i < lvl; i++){
            maxHp += 2 + random.nextInt(5) + (maxHp*0.05);
            atk += 1 + random.nextInt(3) + (atk*0.1);
            def += 1 + random.nextInt(3) + (def*0.1);
            spd += 1 + random.nextInt(3) + (spd*0.1);
            money += 5 + (money * (Math.random() * 0.5));
            expNext += 5 + (expNext * (Math.random() * 0.5));
        }
        hp = maxHp;
        exp = 0;
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
     * Applies an array of stat changes to the hero's stats. Also prints the changed stats, and by how much.
     * @param statChanger The StatChanger that will affect the stats.
     */
    public void changeStats(StatChanger statChanger){
        if(statChanger.isBlank()){
            System.out.println();
            return;
        }
        
        int hpChange = statChanger.getHPChange();
        int maxHpChange = statChanger.getMaxHPChange();
        int atkChange = statChanger.getATKChange();
        int defChange = statChanger.getDEFChange();
        int spdChange = statChanger.getSPDChange();
        int moneyChange = statChanger.getMoneyChange();
        
        System.out.println("=+=======STATS=======+=");
        
        //HP, max HP
        if(hpChange == 0 && maxHpChange != 0){
            System.out.print("HP: " + hp);
        }
        else if(hpChange > 0){
            System.out.print("HP: (" + hp + " + " + hpChange + " = " + Math.min(Math.max(hp + hpChange, 0), maxHp + maxHpChange) + ")");
        }
        else if(hpChange < 0){
            System.out.print("HP: (" + hp + " - " + -1*hpChange + " = " + Math.min(Math.max(hp + hpChange, 0), maxHp + maxHpChange) + ")");
        }
        
        if(hpChange != 0 && maxHpChange == 0){
            System.out.println("/" + maxHp);
        }
        else if(maxHpChange > 0){
            System.out.println("/(" + maxHp + " + " + maxHpChange + " = " + Math.max(maxHp + maxHpChange, 0) + ")");
        }
        else if(maxHpChange < 0){
            System.out.println("/(" + maxHp + " - " + -1*maxHpChange + " = " + Math.max(maxHp + maxHpChange, 0) + ")");
        }
        maxHp = Math.max(maxHp + maxHpChange, 0);
        hp = Math.min(Math.max(hp + hpChange, 0), maxHp);
        
        //ATK
        if(atkChange > 0){
            System.out.println("ATK: " + atk + " + " + atkChange + " = " + (atk+atkChange));
        }
        if(atkChange < 0){
            System.out.println("ATK: " + atk + " - " + -1*atkChange + " = " + (atk+atkChange));
        }
        atk += atkChange;
        
        //DEF
        if(defChange > 0){
            System.out.println("DEF: " + def + " + " + defChange + " = " + (def+defChange));
        }
        if(defChange < 0){
            System.out.println("DEF: " + def + " - " + -1*defChange + " = " + (def+defChange));
        }
        def += defChange;
        
        //SPD
        if(spdChange > 0){
            System.out.println("SPD: " + spd + " + " + spdChange + " = " + (spd+spdChange));
        }
        if(spdChange < 0){
            System.out.println("SPD: " + spd + " - " + -1*spdChange + " = " + (spd+spdChange));
        }
        spd += spdChange;
        
        //MONEY
        if(moneyChange > 0){
            System.out.println("MONEY: " + money + " + " + moneyChange + " = " + Math.max(money + moneyChange, 0));
        }
        if(moneyChange < 0){
            System.out.println("MONEY: " + money + " - " + -1*moneyChange + " = " + Math.max(money + moneyChange, 0));
        }
        money = Math.max(money + moneyChange, 0);
        
        System.out.println("=+=======-----=======+=\n");
    }
    
    /**
     * Prints the hero's stats.
     */
    public void printStats(){
        System.out.println("=+=======STATS=======+=");
        System.out.println("HP: " + hp +" / "+ maxHp + "\n" +
                           "ATK: " + atk  + "\n" +
                           "DEF: " + def + "\n" +
                           "SPD: " + spd + "\n" +
                           "MONEY: " + money);
        System.out.println("=+=======-----=======+=\n");
    }
}
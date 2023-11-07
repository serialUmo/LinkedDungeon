import java.util.Scanner;
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
    
    private int battles;
    private int items;
    private int traps;
    private int wares;
    
    private boolean exiting;
    
    private Scanner scan;
    private Random random;
    
    /**
     * Constructs a Hero object with default stats.
     */
    public Hero (){
        scan = new Scanner(System.in);
        random = new Random();
        
        hp = 100;
        maxHp = 100;
        atk = 10;
        def = 5;
        spd = 3;
        money = 0;
        
        lvl = 1;
        exp = 0;
        expNext = 10;
        
        exiting = false;
    }
    
    /**
     * Constructs a Hero object with the specified stats.
     * @param hp How much current HP the hero has. Cannot be greater than maxHp.
     * @param maxHp The maximum amount of HP the hero may have.
     * @param atk How much HP the hero takes away from a Monster per attack.
     * @param def How much damage is mitigated when attacked by a Monster.
     * @param spd When this value is higher than a Monster's, the hero attacks first.
     * @param money How much money the hero starts with.
     * @param firstLvlCost How much EXP is needed to level up. A low value will generally have faster initial leveling.
     */
    public Hero (int hp, int maxHp, int atk, int def, int spd, int money, int firstLvlCost){
        scan = new Scanner(System.in);
        random = new Random();

        this.hp = hp;
        this.maxHp = maxHp;
        if(this.hp > maxHp){
            this.hp = maxHp;
        }
        this.atk = atk;
        this.def = def;
        this.spd = spd;
        this.money = money;
        
        lvl = 1;
        exp = 0;
        expNext = firstLvlCost;
        
        exiting = false;
    }
    
    public void pressToContinue(){
        System.out.println("(press enter to continue)");
        scan.nextLine();
        System.out.println();
    }
    
    //---Battle Functions---
    /**
     * Has the Hero attack an Entity.
     * @param target What entity to attack.
     */
    public void attack(Entity target){
        int attack = atk/((target.getDEF()+10)/10) + random.nextInt(4);
        target.damage(attack);
    }
    
    /**
     * Decreases HP by an ATK value, which is mitigated by DEF.
     * @param dmg How much health to take away.
     */
    public void damage(int dmg){
        System.out.println("YOUR HP: (" + hp + " - " + dmg + " = " + (hp - dmg) + ")/" + maxHp);
        hp -= Math.max(dmg, 0);
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
     * Gives the hero EXP and Money from an enemy. Also checks if hero can level up.
     * @param enemy What enemy to get spoils from.
     */
    public void winRewards(Enemy enemy){
        System.out.println(" -', <YOU WON!> ,'- ");
        int expChange = enemy.getExp();
        int moneyChange = enemy.getMoney();
        
        //MONEY
        if(moneyChange > 0){
            System.out.println("MONEY: " + money + " + " + moneyChange + " = " + Math.max(money + moneyChange, 0));
        }
        if(moneyChange < 0){
            System.out.println("MONEY: " + money + " - " + -1*moneyChange + " = " + Math.max(money + moneyChange, 0));
        }
        money = Math.max(money + moneyChange, 0);
        
        //EXP
        if(expChange > 0){
            System.out.println("EXP: (" + exp + " + " + expChange + " = " + (exp+expChange) + ")/" + expNext);
        }
        exp += expChange;
        System.out.println(" -',='- ,<>, -'=,'- ");
        
        pressToContinue();
        
        levelUp();
    }
    
    /**
     * Increases the hero's stats per level.
     */
    public void levelUp(){
        if(exp < expNext){
            return;
        }
        
        while(exp >= expNext){
            lvl++;
            exp -= expNext;
            System.out.println("=+<>===LEVELUP===<>+=");

            int maxHpChange = (int)( 2 + random.nextInt(5) + (maxHp*0.05));
            int atkChange = (int)(1 + random.nextInt(3) + (atk*0.075));
            int defChange = (int)(1 + random.nextInt(3) + (def*0.075));
            int spdChange = (int)(1 + random.nextInt(3) + (spd*0.1));
            
            expNext += 15 + (expNext * 0.25);
            
            System.out.println("Lvl."+lvl+"\t\tYou");
            System.out.println("EXP: " + exp + "/" + expNext + "\n");
            
            System.out.println("HP: " + (hp + (maxHp+maxHpChange)/2) + "/" + (maxHp+maxHpChange) + " (+" + maxHpChange + ")");
            maxHp += maxHpChange;
            
            System.out.println("ATK: " + (atk+atkChange) + " (+" + atkChange + ")");
            atk += atkChange;
            
            System.out.println("DEF: " + (def+defChange) + " (+" + defChange + ")");
            def += defChange;
            
            System.out.println("SPD: " + (spd+spdChange) + " (+" + spdChange + ")");
            spd += spdChange;
            
            
            
        }
        hp = Math.min(maxHp, hp + maxHp/2);
        System.out.println("=+=======<O>=======+=");
        pressToContinue();      
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
    
    public boolean isExiting(){
        return exiting;
    }
    
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
    
    public void setExiting(boolean value){
        exiting = value;
    }
    
    //Incrementers
    
    public void incrementBattles()
    {battles++;}
    public void incrementItems()
    {items++;}
    public void incrementTraps()
    {traps++;}
    public void incrementWares()
    {wares++;}
    
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
            System.out.print("HP: (" + hp + " + " + hpChange + " = " + Math.min(hp + hpChange, maxHp + maxHpChange) + ")");
        }
        else if(hpChange < 0){
            System.out.print("HP: (" + hp + " - " + -1*hpChange + " = " + Math.min(hp + hpChange, maxHp + maxHpChange) + ")");
        }
        
        if(hpChange != 0 && maxHpChange == 0){
            System.out.println("/" + maxHp);
        }
        else if(maxHpChange > 0){
            System.out.println("/(" + maxHp + " + " + maxHpChange + " = " + (maxHp + maxHpChange) + ")");
        }
        else if(maxHpChange < 0){
            System.out.println("/(" + maxHp + " - " + -1*maxHpChange + " = " + (maxHp + maxHpChange) + ")");
        }
        maxHp += maxHpChange;
        hp = Math.min(hp + hpChange, maxHp);
        
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
        def = Math.max(def + defChange, -99);
        
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
        pressToContinue();
    }
    
    /**
     * Prints the hero's stats.
     */
    public void printStats(){
        System.out.println("=+=====YOUR-STATS=====+=");
        System.out.println("Lvl."+lvl+"\t\tYou\n" +
                           "EXP: " + exp + "/" + expNext + "\n\n" +
                           "HP: " + hp +" / "+ maxHp + "\n" +
                           "ATK: " + atk  + "\n" +
                           "DEF: " + def + "\n" +
                           "SPD: " + spd + "\n" +
                           "MONEY: " + money);
        System.out.println("=+=======------=======+=\n");
    }
    
    /**
     * Prints the hero's final stats.
     */
    public void printObituary(String name, int floor){
        System.out.println("[+===WE=CLAIM=ANOTHER==+]");
        System.out.println("Lvl."+lvl+"\t\t" +name+ "\n" +
                           "EXP: " + exp + "/" + expNext + "\n\n" +
                           "HP: " + hp +" / "+ maxHp + "\n" +
                           "ATK: " + atk  + "\n" +
                           "DEF: " + def + "\n" +
                           "SPD: " + spd + "\n" +
                           "MONEY: " + money);
                           
        System.out.println("Battles fought: " + battles);
        System.out.println("Items got: " + items);
        System.out.println("Wares bought: " + wares);
        System.out.println("Traps shot: " + traps);
        System.out.println("\nFLOOR: " + floor);
        System.out.println("[+=======--RIP--=======+]\n");
    }
}
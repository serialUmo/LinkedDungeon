public class EnemyKind
{
    private String name;
    private int hp;
    private int maxHp;
    private int atk;
    private int def;
    private int spd;
    private int money;
    
    private int exp;
    
    //Default
    public EnemyKind (){
        name = "Shadow";
        hp = 1;
        maxHp = 1;
        atk = 1;
        def = 0;
        spd = 0;
        money = 0;
        exp = 0;
    }
    //Custom
    public EnemyKind (String name, int hp, int atk, int def, int spd, int money, int exp){
        this.name = name;
        this.hp = hp;
        this.maxHp = hp;
        this.atk = atk;
        this.def = def;
        this.spd = spd;
        this.money = money;
        this.exp = exp;
    }
    
    //Changers
    public void changeHP(int change){
        hp += change;
        if (hp < 0){
            hp = 0;
        }
        if (hp > maxHp){
            hp = maxHp;
        }
    }
    public void changeMaxHP(int change){
        maxHp += change;
        if (maxHp < 0){
            maxHp = 0;
        }
    }
    public void changeATK(int change)
    {atk += change;}
    public void changeDEF(int change)
    {def += change;}
    public void changeSPD(int change)
    {spd += change;}
    public void changeMoney(int change)
    {money += change;}
    public void changeExp(int change)
    {exp += change;}
    
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
    public int getExp()
    {return exp;}
    
    //Setters
    public void setHP(int change)
    {hp = change;}
    public void setMaxHP(int change)
    {maxHp = change;}
    public void setATK(int change)
    {atk = change;}
    public void setDEF(int change)
    {def = change;}
    public void setSPD(int change)
    {spd = change;}
    public void setMoney(int change)
    {money = change;}
    public void setExp(int change)
    {exp = change;}
}
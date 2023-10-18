public class EnemyKind
{
    private String name;
    private int hp;
    private int maxHp;
    private int atk;
    private int def;
    private int spd;
    private int money;
    
    //Default
    public EnemyKind (){
        name = "Shadow";
        hp = 1;
        maxHp = 1;
        atk = 1;
        def = 0;
        spd = 0;
        money = 0;
    }
    //Custom
    public EnemyKind (String name, int hp, int atk, int def, int spd, int money){
        this.name = name;
        this.hp = hp;
        this.maxHp = hp;
        this.atk = atk;
        this.def = def;
        this.spd = spd;
        this.money = money;
    }
    
    //---Changer Functions---
    
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
}
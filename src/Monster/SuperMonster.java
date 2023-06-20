package Monster;

public class SuperMonster {
    public String name;
    public int hp;
    public int maxHP;
    public int attack;
    public int goldLoot;

    boolean isDefeated = false;
    boolean isSelected = false;
    String monsterIcon;


    public String getMonsterIcon(){
        return monsterIcon;
    }
    public boolean getStatusDefeated(){
        return isDefeated;
    }
    public boolean getStatusSelected(){
        return isSelected;
    }
    public void setSelected(boolean status){
        isSelected = status;
    }
    public String getHP(){
        return String.valueOf(hp);
    }
    public String getMaxHP(){
        return String.valueOf(maxHP);
    }
    public String getAttack(){return String.valueOf(attack);}
    public String getName(){return name;}

}


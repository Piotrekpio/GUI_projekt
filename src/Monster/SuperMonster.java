package Monster;

public class SuperMonster {
    public String name;
    public int hp;
    public int maxHP;
    public int maxAttack;
    public int minAttack;
    public int goldLoot;
    public int defence;

    boolean isDefeated = false;
    boolean isSelected = false;
    boolean isUnlocked = false;
    String monsterIcon;

    public void setDefaultHP(){
        this.hp = this.maxHP;
    }


    public String getMonsterIcon(){
        return monsterIcon;
    }
    public boolean getStatusDefeated(){
        return isDefeated;
    }
    public boolean getStatusSelected(){
        return isSelected;
    }
    public boolean getStatusUnlocked(){
        return isUnlocked;
    }
    public void setSelected(boolean status){
        isSelected = status;
    }
    public void setDefeated (boolean status){
        isDefeated = status;
    }
    public void setUnlocked (boolean status){
        isUnlocked = status;
    }
    public String getHP(){
        return String.valueOf(hp);
    }
    public String getMaxHP(){
        return String.valueOf(maxHP);
    }
    public String getMaxAttack(){return String.valueOf(maxAttack);}
    public String getName(){return name;}

}


package Main;

import item.*;

public class Player {
    GameManager gm;

    public int playerLife;
    public int playerMaxLife;
    public int playerMaxAttack;
    public int playerMinAttack;
    public int playerDefence;

    public int playerGold;
    public int playerLevel;
    public boolean hasWeapon;
    public boolean hasShield;
    public boolean hasFood;
    public boolean hasCard;
    public boolean hasSuperAttack;

    //inventory
    public SuperWeapon currentWeapon;
    public SuperShield currentShield;

    public SuperWeapon weapon1 = new Weapon_1();
    public SuperWeapon weapon2 = new Weapon_2();

    public SuperShield shield1 = new Shield_1();
    public SuperShield shield2 = new Shield_2();

    public SuperFood food1 = new Food_1();
    public SuperFood food2 = new Food_2();

    public Player(GameManager gm){
        this.gm = gm;
        setPlayerDefaultStatus();
    }

    public void setPlayerDefaultStatus(){
        playerMaxLife =  100 + playerLevel*10;
        playerLife = playerMaxLife;
        playerMaxAttack = 10 + playerLevel;
        playerMinAttack = 5 + playerLevel;
        playerDefence = 5;
        playerGold = 150;
        hasWeapon = false;
        hasShield = false;
        hasFood = false;

    }
    public void refreshPlayerStats(){
        //life
        playerMaxLife = 100 + playerLevel*10;
        //attack
        if (hasWeapon){
            playerMaxAttack = 10 + playerLevel + currentWeapon.maxDamage;
        }
        else {
            playerMaxAttack = 10 + playerLevel;
        }
        if (hasWeapon){
            playerMinAttack = 5 + playerLevel + currentWeapon.minDamage;
        }
        else {
            playerMinAttack = 5 + playerLevel;
        }
        //defence
        if (hasShield){
            playerDefence = 5 + playerLevel + currentShield.defence;
        }
        else {
            playerDefence = 5 + playerLevel;
        }
    }

    public String playerCurrentLife(){
        return "HP: " + Integer.toString(playerLife) + "/" +Integer.toString(playerMaxLife);
    }
    public String getPlayerDefence(){
        return Integer.toString(playerDefence);
    }
    public String getPlayerGold(){
        return Integer.toString(playerGold);
    }
    public String getPlayerLevel(){
        return Integer.toString(playerLevel);
    }
    public void setPlayerLevel(int value){
        playerLevel = value;
    }

}

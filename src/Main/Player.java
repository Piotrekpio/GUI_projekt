package Main;

import item.SuperWeapon;
import item.Weapon_1;

public class Player {
    GameManager gm;

    public int playerLife;
    public int playerMaxLife;
    public int playerMaxAttack;
    public int playerMinAttack;
    public int playerDefence;

    public int playerGold;
    //inventory
    public SuperWeapon currentWeapon;

    public Player(GameManager gm){
        this.gm = gm;
        setPlayerDefaultStatus();
    }

    public void setPlayerDefaultStatus(){
        currentWeapon = new Weapon_1();
        playerMaxLife = 100;
        playerLife = 100;
        playerMaxAttack = currentWeapon.maxDamage;
        playerMinAttack = currentWeapon.minDamage;
        playerDefence = 3;
        playerGold = 10;

    }

    public String playerCurrentLife(){
        return "HP: " + Integer.toString(playerLife) + "/" +Integer.toString(playerMaxLife);
    }
    public String getPlayerAttack(){
        return Integer.toString(playerMaxAttack);
    }
    public String getPlayerDefence(){
        return Integer.toString(playerDefence);
    }
    public String getPlayerGold(){
        return Integer.toString(playerGold);
    }

}

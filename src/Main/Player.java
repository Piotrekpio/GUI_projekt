package Main;

public class Player {
    GameManager gm;

    public int playerLife;
    public int playerMaxLife;
    public int playerAtack;
    public int playerDefence;
    public int playerGold;

    public Player(GameManager gm){
        this.gm = gm;
        setPlayerDefaultStatus();
    }

    public void setPlayerDefaultStatus(){
        playerMaxLife = 100;
        playerLife = 100;
        playerAtack = 3;
        playerDefence = 3;
        playerGold = 10;
    }

    public String playerCurrentLife(){
        return "HP: " + Integer.toString(playerLife) + "/" +Integer.toString(playerMaxLife);
    }

}

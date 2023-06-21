package Event;

import Main.GameManager;

public class Event1 {
    GameManager gm;
    public Event1(GameManager gm){
        this.gm = gm;
    }

    public void redFish(){
        gm.ui.messageText.setText("red fish");
        gm.player.playerLife -= 5;
        if (gm.player.playerLife <= 0) {
            gm.sChanger.showGameOverScreen(1);
        }
        gm.ui.updatePlayerStats();

    }
    public void greenFish(){
        gm.ui.messageText.setText("green fish");
        if (gm.player.playerLife<100){
            gm.player.playerLife += 1;
        }
        else gm.ui.messageText.setText("Full health");
        gm.ui.updatePlayerStats();


    }

    public void selectMonster0(){
        gm.ui.messageText.setText("nibba fish 1 selected");
        gm.mSel.newMonsterSelected(0);
        gm.ui.repaintMonsterChooser();
        gm.ui.repaintEnemy();

    }
    public void selectMonster1(){
        gm.ui.messageText.setText("nibba fish 2 selected");
        gm.mSel.newMonsterSelected(1);
        gm.ui.repaintMonsterChooser();
        gm.ui.repaintEnemy();

    }
    public void selectMonster2(){
        gm.ui.messageText.setText("nibba fish 3 selected");
        gm.mSel.newMonsterSelected(2);
        gm.ui.repaintMonsterChooser();
        gm.ui.repaintEnemy();


    }
    public void selectMonster3(){
        gm.ui.messageText.setText("nibba fish 4 selected");
        gm.mSel.newMonsterSelected(3);
        gm.ui.repaintMonsterChooser();
        gm.ui.repaintEnemy();

    }
    public void selectMonster4(){
        gm.ui.messageText.setText("nibba fish 5 selected");
        gm.mSel.newMonsterSelected(4);
        gm.ui.repaintMonsterChooser();
        gm.ui.repaintEnemy();

    }

    public void attackMonster(){
        int selectedMonster = gm.mSel.checkSelectedMonster();
        int playerAttackPoints = (int)((Math.random()*(gm.player.playerMaxAttack-gm.player.playerMinAttack)+gm.player.playerMinAttack) - gm.mSel.monsterTab[selectedMonster].defence);
        int monsterAttackPoints = (int)((Math.random()*(gm.mSel.monsterTab[selectedMonster].maxAttack-gm.mSel.monsterTab[selectedMonster].minAttack)+gm.mSel.monsterTab[selectedMonster].minAttack) - gm.player.playerDefence);
        //player attack
        gm.mSel.monsterTab[selectedMonster].hp -= playerAttackPoints;
        if(gm.mSel.monsterTab[selectedMonster].hp > 0){
            //monster attack
            gm.player.playerLife -= monsterAttackPoints;
            gm.ui.messageText.setText("You attacked the monster for: " + playerAttackPoints + "\n" + "Monster attacked you for: " + monsterAttackPoints);
        }
        //monster defeated
        else {
            gm.sChanger.defeatedMonster();
            if (gm.mSel.monsterTab[selectedMonster].getStatusDefeated()){
                gm.player.playerGold += (gm.mSel.monsterTab[selectedMonster].goldLoot)/2;
                gm.ui.messageText.append("\nYou recived: " + (gm.mSel.monsterTab[selectedMonster].goldLoot)/2 + " gold!");
            }
            else {
                gm.player.playerGold += gm.mSel.monsterTab[selectedMonster].goldLoot;
                gm.ui.messageText.append("\nYou recived: " + gm.mSel.monsterTab[selectedMonster].goldLoot + " gold!");
                gm.mSel.monsterTab[selectedMonster].setDefeated(true);
            }

            if (selectedMonster < 5) {gm.mSel.monsterTab[selectedMonster+1].setUnlocked(true);}
        }
        //player defeated
        if (gm.player.playerLife <= 0) {
            gm.sChanger.showGameOverScreen(3);
        }
        gm.ui.redrawMonsterStats();
        gm.ui.updatePlayerStats();
    }
}

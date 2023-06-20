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
        gm.mSel.monsterTab[selectedMonster].hp -= gm.player.playerAttack;
        if(gm.mSel.monsterTab[selectedMonster].hp > 1){
            gm.player.playerLife -= gm.mSel.monsterTab[selectedMonster].attack;
        }
        else {
            gm.sChanger.defeatedMonster();
            gm.player.playerGold += gm.mSel.monsterTab[selectedMonster].goldLoot;
        }

        if (gm.player.playerLife <= 0) {
            gm.sChanger.showGameOverScreen(3);
        }
        gm.ui.redrawMonsterStats();
        gm.ui.updatePlayerStats();
    }
}

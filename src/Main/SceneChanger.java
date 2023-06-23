package Main;

public class SceneChanger {
    GameManager gm;

    public SceneChanger(GameManager gm){
        this.gm = gm;
    }

    public void showHub(){
        gm.ui.bgPanel[1].setVisible(true);
        gm.ui.bgPanel[2].setVisible(false);
        gm.ui.bgPanel[3].setVisible(false);
        gm.ui.updatePlayerStats();
        gm.mSel.monsterTab[gm.mSel.checkSelectedMonster()].setDefaultHP();
        gm.ui.repaintMonsterChooser();
        gm.player.refreshPlayerStats();
    }
    public void showShop(){
        gm.ui.bgPanel[1].setVisible(false);
        gm.ui.bgPanel[2].setVisible(true);
        gm.ui.bgPanel[3].setVisible(false);
        gm.ui.messageText.setText("Welcome to the shop");
        gm.ui.updatePlayerStats();
        gm.player.refreshPlayerStats();
    }

    public void showBattle(){
        gm.ui.bgPanel[1].setVisible(false);
        gm.ui.bgPanel[2].setVisible(false);
        gm.ui.bgPanel[3].setVisible(true);
        gm.ui.messageText.setText("Its time for a battle");
        gm.ui.redrawMonsterStats();
        gm.ui.updatePlayerStats();
        gm.ui.arrowButton.setVisible(false);
        gm.ui.battlePanel.setVisible(true);
        if(gm.mSel.monsterTab[gm.mSel.checkSelectedMonster()].hasShield){
            gm.ui.shieldIcon.setVisible(true);
        }
        gm.player.refreshPlayerStats();
    }
    public void showMainMenu(){
        gm.ui.bgPanel[1].setVisible(false);
        gm.ui.bgPanel[2].setVisible(false);
        gm.ui.bgPanel[3].setVisible(false);
        gm.ui.playerStatsText.setVisible(false);
        gm.ui.menuLabel.setVisible(true);
        gm.ui.menuLabel.setText("GUI projekt");
        gm.ui.menuBackground.setVisible(true);
        gm.ui.startButton.setVisible(true);
        gm.ui.startButton.setText("NEW GAME");
        gm.ui.levelSelect.setVisible(true);
        gm.ui.titleLabel.setVisible(false);
        gm.ui.restartButton.setVisible(false);
        gm.ui.messageText.setVisible(false);
        gm.ui.endLabel.setVisible(false);
    }
    public void exitMainMenu(){
        gm.ui.menuLabel.setVisible(false);
        gm.ui.startButton.setVisible(false);
        gm.player.setPlayerDefaultStatus();
        gm.player.playerCurrentLife();
        gm.mSel = new MonsterSelect(gm);
        gm.ui.playerStatsText.setVisible(true);
        gm.ui.levelSelect.setVisible(false);
        gm.ui.messageText.setVisible(true);
        gm.player.setPlayerLevel((int)gm.ui.levelSelect.getValue());
        gm.ui.messageText.setText("Welcome to the main hub.");
        gm.ui.menuBackground.setVisible(false);
        showHub();

    }

    public void showGameOverScreen(int currentBgNum){
        gm.ui.bgPanel[currentBgNum].setVisible(false);
        gm.ui.playerStatsText.setVisible(false);
        gm.ui.titleLabel.setVisible(true);
        gm.ui.titleLabel.setText("YOU DIED");
        gm.ui.restartButton.setVisible(true);
        gm.ui.restartButton.setText("Restart");
    }
    public void showGameFinishedScreen(){
        gm.ui.bgPanel[3].setVisible(false);
        gm.ui.playerStatsText.setVisible(false);
        gm.ui.endLabel.setVisible(true);
        gm.ui.endLabel.setText("CONGRATULATION!");
        gm.ui.messageText.setVisible(false);
        gm.ui.restartButton.setVisible(true);
        gm.ui.restartButton.setText("Go to main menu");
    }
    public void defeatedMonster(){
        gm.ui.messageText.setText("You defeated the "+gm.mSel.monsterTab[gm.mSel.checkSelectedMonster()].name);
        gm.ui.battlePanel.setVisible(false);
    }
}

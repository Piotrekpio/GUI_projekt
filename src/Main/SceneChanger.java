package Main;

public class SceneChanger {
    GameManager gm;

    public SceneChanger(GameManager gm){
        this.gm = gm;
    }

    public void showHub(){
        gm.ui.bgPanel[1].setVisible(true);
        gm.ui.bgPanel[2].setVisible(false);
        gm.ui.messageText.setText("Main Hub");
        gm.ui.updatePlayerStats();

    }
    public void showShop(){
        gm.ui.bgPanel[1].setVisible(false);
        gm.ui.bgPanel[2].setVisible(true);
        gm.ui.messageText.setText("Bitches be shopping");

    }
    public void showGameOverScreen(int currentBgNum){
        gm.ui.bgPanel[currentBgNum].setVisible(false);
        gm.ui.titleLabel.setVisible(true);
        gm.ui.titleLabel.setText("NIBBA U DIED");
        gm.ui.restartButton.setVisible(true);
        gm.ui.restartButton.setText("Restart");
    }
    public void existGameOverScreen(){
        gm.ui.titleLabel.setVisible(false);
        gm.ui.restartButton.setVisible(false);
        gm.player.setPlayerDefaultStatus();
        gm.player.playerCurrentLife();
    }
}

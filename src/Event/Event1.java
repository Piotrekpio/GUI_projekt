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
        gm.ui.updatePlayerStats();
        if (gm.player.playerLife <= 0) {
            gm.sChanger.showGameOverScreen(1);
        }

    }
    public void greenFish(){
        gm.ui.messageText.setText("green fish");
        if (gm.player.playerLife<100){
            gm.player.playerLife += 1;
        }
        else gm.ui.messageText.setText("Full health");
        gm.ui.updatePlayerStats();
    }
}

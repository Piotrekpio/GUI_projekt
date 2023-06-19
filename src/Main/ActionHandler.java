package Main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionHandler implements ActionListener {
    GameManager gm;
    public ActionHandler(GameManager gm){
        this.gm = gm;
    }
    @Override
    public void actionPerformed(ActionEvent e) {

        String yourChoice = e.getActionCommand();

        switch (yourChoice){
            case "redFish": gm.ev1.redFish(); break;
            case "greenFish": gm.ev1.greenFish(); break;

            //change scene
            case "goShop": gm.sChanger.showShop(); break;
            case "goHub": gm.sChanger.showHub(); break;

            //game over screen
            case "restart": gm.sChanger.existGameOverScreen(); gm.sChanger.showHub(); break;
        }

    }
}

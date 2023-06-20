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
            case "goBattle": gm.sChanger.showBattle(); break;

            //game over screen
            case "restart": gm.sChanger.existGameOverScreen(); gm.sChanger.showHub(); break;
            //monster select
            case "selectMonster0": gm.ev1.selectMonster0(); break;
            case "selectMonster1": gm.ev1.selectMonster1(); break;
            case "selectMonster2": gm.ev1.selectMonster2(); break;
            case "selectMonster3": gm.ev1.selectMonster3(); break;
            case "selectMonster4": gm.ev1.selectMonster4(); break;

            //battle screen
            case "attackMonster": gm.ev1.attackMonster(); break;
        }

    }
}

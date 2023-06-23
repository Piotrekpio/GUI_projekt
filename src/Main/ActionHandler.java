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
            //change scene
            case "goShop": gm.sChanger.showShop(); break;
            case "goHub": gm.sChanger.showHub(); break;
            case "goBattle": gm.sChanger.showBattle(); break;

            //game over screen
            case "restart": gm.sChanger.showMainMenu(); break;
            //main menu
            case "start": gm.sChanger.exitMainMenu(); gm.sChanger.showHub(); break;
            //monster select
            case "selectMonster0": gm.ev1.selectMonster0(); break;
            case "selectMonster1": gm.ev1.selectMonster1(); break;
            case "selectMonster2": gm.ev1.selectMonster2(); break;
            case "selectMonster3": gm.ev1.selectMonster3(); break;
            case "selectMonster4": gm.ev1.selectMonster4(); break;

            //battle screen
            case "attackMonster": gm.ev1.attackMonster(); break;
            case "runAway": gm.ev1.runAway(); break;
            case "superAttack": gm.ev1.superAttack(); break;
            //shop UI
            case "buy": gm.ev1.buy(); break;
            case "discard": gm.ev1.discard(); break;

            case "selectWeapon1": gm.ev1.selectWeapon1(); break;
            case "selectWeapon2": gm.ev1.selectWeapon2(); break;

            case "selectShield1": gm.ev1.selectShield1(); break;
            case "selectShield2": gm.ev1.selectShield2(); break;

            case "selectFood1": gm.ev1.selectFood1(); break;
            case "selectFood2": gm.ev1.selectFood2(); break;
            case "selectCard": gm.ev1.selectCard(); break;
            case "selectSuperAttack": gm.ev1.selectSuperAttack(); break;
        }

    }
}

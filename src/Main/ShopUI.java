package Main;

import item.SuperWeapon;
import item.Weapon_1;

import javax.swing.*;
import java.awt.*;

public class ShopUI {
    GameManager gm;
    JPanel selectPanel;
    JButton weapon1, weapon2, weapon3, shield1, shield2, shield3, food1, food2, food3, potion1, potion2,potion3;

    SuperWeapon knife = new Weapon_1();

    public ShopUI(GameManager gm){
        this.gm = gm;
    }

    public void createShopSelection(int bgNum){
        selectPanel = new JPanel();
        selectPanel.setBounds(100,100,192,256);
        selectPanel.setLayout(new GridLayout(4,3));



        weapon1 = new JButton();
        weapon1.setIcon(new ImageIcon(getClass().getClassLoader().getResource(knife.icon)));
        selectPanel.add(weapon1);

    }
}
/**
            monsterButtons[i] = new JButton();
                    monsterButtons[i].setBackground(gm.mSel.monsterStatus(i));
                    monsterButtons[i].setContentAreaFilled(false);
                    monsterButtons[i].setFocusPainted(false);
                    monsterButtons[i].setFocusable(false);
                    monsterButtons[i].setIcon(new ImageIcon(getClass().getClassLoader().getResource(gm.mSel.monsterTab[i].getMonsterIcon())));
                    monsterButtons[i].setOpaque(true);
                    monsterButtons[i].repaint();
                    monsterButtons[i].addActionListener(gm.aHandler);
                    monsterButtons[i].setActionCommand("selectMonster"+i);
                    monsterButtons[i].setEnabled(gm.mSel.monsterUnlocked(i));
                    monsterPanel.add(monsterButtons[i]);
 **/
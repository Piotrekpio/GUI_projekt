package Main;

import item.*;

import javax.swing.*;
import java.awt.*;

public class ShopUI {
    GameManager gm;
    JPanel selectPanel;
    JPanel buyOrDiscard;
    JButton buyButton, discardButton;
    JButton weapon1, weapon2, shield1, shield2, food1, food2;
    JPanel inventoryPanel;
    public JButton weapon;
    public JButton shield;
    public JButton food;
    public JPanel consumables;
    public JButton cardButton, superAttackButton;
    public Card card = new Card();
    public SuperAttack superAttack = new SuperAttack();


    SuperWeapon knife = new Weapon_1();
    SuperWeapon broadSword = new Weapon_2();
    SuperShield roundShield = new Shield_1();
    SuperShield shinyShield = new Shield_2();
    SuperFood candy = new Food_1();
    SuperFood cake = new Food_2();

    public ShopUI(GameManager gm){
        this.gm = gm;
    }


    public void createBuyDiscardButtons(){
        buyOrDiscard = new JPanel();
        buyOrDiscard.setBounds(300, 300, 192, 64);
        buyOrDiscard.setLayout(new GridLayout(1,2));

        buyButton = new JButton("BUY");
        buyButton.addActionListener(gm.aHandler);
        buyButton.setActionCommand("buy");
        buyOrDiscard.add(buyButton);

        discardButton = new JButton("DISCARD");
        discardButton.addActionListener(gm.aHandler);
        discardButton.setActionCommand("discard");
        buyOrDiscard.add(discardButton);
    }
    public void consumablesPanel(){

        consumables = new JPanel();
        consumables.setBounds(160,224,62,128);
        consumables.setLayout(new GridLayout(2,1));

        cardButton = new JButton();
        cardButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource(card.icon)));
        cardButton.addActionListener(gm.aHandler);
        cardButton.setActionCommand("selectCard");
        consumables.add(cardButton);

        superAttackButton = new JButton();
        superAttackButton.setIcon(new ImageIcon(getClass().getClassLoader().getResource(superAttack.icon)));
        superAttackButton.addActionListener(gm.aHandler);
        superAttackButton.setActionCommand("selectSuperAttack");
        consumables.add(superAttackButton);
    }

    public void createInventory(){
        inventoryPanel = new JPanel();
        inventoryPanel.setBounds(500,100,128,128);
        inventoryPanel.setLayout(new GridLayout(1,2));

        weapon = new JButton();
        if (gm.player.hasWeapon){
            weapon.setIcon(new ImageIcon(getClass().getClassLoader().getResource(gm.player.currentWeapon.icon)));
        }
        else{weapon.setIcon(new ImageIcon(getClass().getClassLoader().getResource("palm.png")));}
        weapon.setEnabled(false);
        inventoryPanel.add(weapon);

        shield = new JButton();
        if (gm.player.hasShield){
            shield.setIcon(new ImageIcon(getClass().getClassLoader().getResource(gm.player.currentShield.icon)));
        }
        else{shield.setIcon(new ImageIcon(getClass().getClassLoader().getResource("palm.png")));}
        shield.setEnabled(false);
        inventoryPanel.add(shield);


    }
    public void createShopSelection(){
        selectPanel = new JPanel();
        selectPanel.setBounds(0,160,128,192);
        selectPanel.setLayout(new GridLayout(3,2));


        //weapons
        weapon1 = new JButton();
        weapon1.setIcon(new ImageIcon(getClass().getClassLoader().getResource(knife.icon)));
        weapon1.addActionListener(gm.aHandler);
        weapon1.setActionCommand("selectWeapon1");
        selectPanel.add(weapon1);

        weapon2 = new JButton();
        weapon2.setIcon(new ImageIcon(getClass().getClassLoader().getResource(broadSword.icon)));
        weapon2.addActionListener(gm.aHandler);
        weapon2.setActionCommand("selectWeapon2");
        selectPanel.add(weapon2);

        //shields
        shield1 = new JButton();
        shield1.setIcon(new ImageIcon(getClass().getClassLoader().getResource(roundShield.icon)));
        shield1.addActionListener(gm.aHandler);
        shield1.setActionCommand("selectShield1");
        selectPanel.add(shield1);

        shield2 = new JButton();
        shield2.setIcon(new ImageIcon(getClass().getClassLoader().getResource(shinyShield.icon)));
        shield2.addActionListener(gm.aHandler);
        shield2.setActionCommand("selectShield2");
        selectPanel.add(shield2);

        //food
        food1 = new JButton();
        food1.setIcon(new ImageIcon(getClass().getClassLoader().getResource(candy.icon)));
        food1.addActionListener(gm.aHandler);
        food1.setActionCommand("selectFood1");
        selectPanel.add(food1);

        food2 = new JButton();
        food2.setIcon(new ImageIcon(getClass().getClassLoader().getResource(cake.icon)));
        food2.addActionListener(gm.aHandler);
        food2.setActionCommand("selectFood2");
        selectPanel.add(food2);

    }
}

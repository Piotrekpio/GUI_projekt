package Event;

import Main.GameManager;

import javax.swing.*;

public class Event1 {
    GameManager gm;
    String itemSelected = "null";
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
        if (monsterAttackPoints<1){
            monsterAttackPoints = 1;
        }
        //player attack
        if (gm.mSel.monsterTab[selectedMonster].hasShield){
            gm.ui.shieldIcon.setVisible(false);
            gm.player.playerLife -= monsterAttackPoints;
            gm.ui.messageText.setText("You destroyed monsters shield." +"\n" + "Monster attacked you for: " + monsterAttackPoints);
            gm.mSel.monsterTab[selectedMonster].hasShield = false;
        }
        else{gm.mSel.monsterTab[selectedMonster].hp -= playerAttackPoints;
            if(gm.mSel.monsterTab[selectedMonster].hp > 0){
                //monster attack
                gm.player.playerLife -= monsterAttackPoints;
                gm.ui.messageText.setText("You attacked the monster for: " + playerAttackPoints + "\n" + "Monster attacked you for: " + monsterAttackPoints);
            }
            //monster defeated
            else {
                //level up
                gm.player.setPlayerLevel(gm.player.playerLevel + selectedMonster + 1);
                if (selectedMonster == 4){
                    gm.sChanger.showGameFinishedScreen();
                }

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


                if (selectedMonster < 4) {
                    gm.mSel.monsterTab[selectedMonster+1].setUnlocked(true);
                    gm.ui.arrowButton.setVisible(true);
                }
            }}

        //player defeated
        if (gm.player.playerLife <= 0) {
            gm.sChanger.showGameOverScreen(3);
        }
        gm.ui.redrawMonsterStats();
        gm.ui.updatePlayerStats();
    }
    public void runAway(){
        if(gm.player.hasCard){
            gm.player.hasCard = false;
            gm.ui.messageText.setText("You used your card and run away safely.");
            gm.sChanger.showHub();
        }
        else if(!gm.player.hasWeapon && !gm.player.hasShield){
            gm.player.playerLife = 1;
            gm.ui.messageText.setText("You run away but you received heavy damage.");

        }
        else {
            if (gm.player.hasWeapon) {
                gm.player.currentWeapon = null;
                gm.player.hasWeapon = false;
                gm.sUI.weapon.setIcon(new ImageIcon(getClass().getClassLoader().getResource("palm.png")));
                gm.player.refreshPlayerStats();
                gm.ui.updatePlayerStats();


            }
            if (gm.player.hasShield) {
                gm.player.currentShield = null;
                gm.player.hasShield = false;
                gm.sUI.shield.setIcon(new ImageIcon(getClass().getClassLoader().getResource("palm.png")));
                gm.player.refreshPlayerStats();
                gm.ui.updatePlayerStats();
            }
            gm.ui.messageText.setText("You run away but lost your equipment.");

        }
        gm.sChanger.showHub();

    }

    public void buy(){
        switch (itemSelected){
            case "null":
                gm.ui.messageText.setText("Select what to buy first.");
                break;
            case "weapon1":
                if (gm.player.hasWeapon){
                    gm.ui.messageText.setText("You already have a weapon, discard it first.");
                }
                else if (gm.player.playerGold < gm.player.weapon1.price){
                    gm.ui.messageText.setText("You are too poor to buy this weapon");
                }
                else {
                    gm.player.hasWeapon = true;
                    gm.player.currentWeapon = gm.player.weapon1;
                    gm.player.playerGold = gm.player.playerGold - gm.player.weapon1.price;
                    gm.sUI.weapon.setIcon(new ImageIcon(getClass().getClassLoader().getResource(gm.player.weapon1.icon)));
                    gm.player.refreshPlayerStats();
                    gm.ui.updatePlayerStats();
                }
                break;
            case "weapon2":
                if (gm.player.hasWeapon){
                    gm.ui.messageText.setText("You already have a weapon, discard it first.");
                }
                else if (gm.player.playerGold < gm.player.weapon2.price){
                    gm.ui.messageText.setText("You are too poor to buy this weapon");
                }
                else {
                    gm.player.hasWeapon = true;
                    gm.player.currentWeapon = gm.player.weapon2;
                    gm.player.playerGold = gm.player.playerGold - gm.player.weapon2.price;
                    gm.sUI.weapon.setIcon(new ImageIcon(getClass().getClassLoader().getResource(gm.player.weapon2.icon)));
                    gm.player.refreshPlayerStats();
                    gm.ui.updatePlayerStats();
                }
                break;
            case "shield1":
                if (gm.player.hasShield){
                    gm.ui.messageText.setText("You already have a shield, discard it first.");
                }
                else if (gm.player.playerGold < gm.player.shield1.cost){
                    gm.ui.messageText.setText("You are too poor to buy this shield");
                }
                else {
                    gm.player.hasShield = true;
                    gm.player.currentShield = gm.player.shield1;
                    gm.player.playerGold = gm.player.playerGold - gm.player.shield1.cost;
                    gm.sUI.shield.setIcon(new ImageIcon(getClass().getClassLoader().getResource(gm.player.shield1.icon)));
                    gm.player.refreshPlayerStats();
                    gm.ui.updatePlayerStats();
                }
                break;
            case "shield2":
                if (gm.player.hasShield){
                    gm.ui.messageText.setText("You already have a shield, discard it first.");
                }
                else if (gm.player.playerGold < gm.player.shield2.cost){
                    gm.ui.messageText.setText("You are too poor to buy this shield");
                }
                else {
                    gm.player.hasShield = true;
                    gm.player.currentShield = gm.player.shield2;
                    gm.player.playerGold = gm.player.playerGold - gm.player.shield2.cost;
                    gm.sUI.shield.setIcon(new ImageIcon(getClass().getClassLoader().getResource(gm.player.shield2.icon)));
                    gm.player.refreshPlayerStats();
                    gm.ui.updatePlayerStats();
                }
                break;
            case "food1":

                if (gm.player.playerGold < gm.player.food1.cost){
                    gm.ui.messageText.setText("You are too poor to buy this " + gm.player.food1.name);
                }
                else {
                    gm.player.playerGold = gm.player.playerGold - gm.player.food1.cost;
                    gm.player.playerLife = gm.player.playerLife + gm.player.food1.hpPoints;
                    if(gm.player.playerLife>gm.player.playerMaxLife){
                        gm.player.playerLife = gm.player.playerMaxLife;
                        gm.ui.messageText.setText("You are healthy");
                    }
                    gm.ui.updatePlayerStats();
                }
                break;
            case "food2":

                if (gm.player.playerGold < gm.player.food2.cost){
                    gm.ui.messageText.setText("You are too poor to buy this " + gm.player.food2.name);
                }
                else {
                    gm.player.playerGold = gm.player.playerGold - gm.player.food2.cost;
                    gm.player.playerLife = gm.player.playerLife + gm.player.food2.hpPoints;
                    if(gm.player.playerLife>gm.player.playerMaxLife){
                        gm.player.playerLife = gm.player.playerMaxLife;
                        gm.ui.messageText.setText("You are healthy");
                    }
                    gm.ui.updatePlayerStats();
                }
                break;
        }

    }
    public void discard(){
        switch (itemSelected) {
            case "null":
                gm.ui.messageText.setText("Select what to discard first.");
                break;
            case "weapon1":
            case "weapon2":
                if (gm.player.hasWeapon) {
                    gm.ui.messageText.setText("Weapon discarded");
                    gm.player.currentWeapon = null;
                    gm.player.hasWeapon = false;
                    gm.sUI.weapon.setIcon(new ImageIcon(getClass().getClassLoader().getResource("palm.png")));
                    gm.player.refreshPlayerStats();
                    gm.ui.updatePlayerStats();
                } else {
                    gm.ui.messageText.setText("You have nothing to discard.");
                }
                break;
            case "shield1":
            case "shield2":
                if (gm.player.hasShield) {
                    gm.ui.messageText.setText("Shield discarded");
                    gm.player.currentShield = null;
                    gm.player.hasShield = false;
                    gm.sUI.shield.setIcon(new ImageIcon(getClass().getClassLoader().getResource("palm.png")));
                    gm.player.refreshPlayerStats();
                    gm.ui.updatePlayerStats();
                } else {
                    gm.ui.messageText.setText("You have nothing to discard.");
                }
                break;
        }


    }

    public void selectWeapon1(){
        gm.ui.messageText.setText("Weapon: " + gm.player.weapon1.name + "\n"
            +"Base attack: " + gm.player.weapon1.minDamage + "-" + gm.player.weapon1.maxDamage + "\n"
            +"Price: " + gm.player.weapon1.price);
        itemSelected = "weapon1";
    }
    public void selectWeapon2(){
        gm.ui.messageText.setText("Weapon: " + gm.player.weapon2.name + "\n"
                +"Base attack: " + gm.player.weapon2.minDamage + "-" + gm.player.weapon2.maxDamage + "\n"
                +"Price: " + gm.player.weapon2.price);
        itemSelected = "weapon2";
    }
    public void selectShield1(){
        gm.ui.messageText.setText("Shield: " + gm.player.shield1.name + "\n"
                +"Base deffence: " + gm.player.shield1.defence + "\n"
                +"Price: " + gm.player.shield1.cost);
        itemSelected = "shield1";

    }
    public void selectShield2(){
        gm.ui.messageText.setText("Shield: " + gm.player.shield2.name + "\n"
                +"Base deffence: " + gm.player.shield2.defence + "\n"
                +"Price: " + gm.player.shield2.cost);
        itemSelected = "shield2";
    }
    public void selectFood1(){
        gm.ui.messageText.setText("Food: " + gm.player.food1.name + "\n"
                +"Health: " + gm.player.food1.hpPoints + "\n"
                +"Price: " + gm.player.food1.cost);
        itemSelected = "food1";
    }
    public void selectFood2(){
        gm.ui.messageText.setText("Food: " + gm.player.food2.name + "\n"
                +"Health: " + gm.player.food2.hpPoints + "\n"
                +"Price: " + gm.player.food2.cost);
        itemSelected = "food2";

    }
    public void selectCard(){
        gm.ui.messageText.setText("Consumable: " + gm.sUI.card.name + "\n"
                +"Price: " + gm.sUI.card.cost);
        itemSelected = "card";
    }
    public void selectSuperAttack(){
        gm.ui.messageText.setText("Consumable: " + gm.sUI.superAttack.name + "\n"
                +"Price: " + gm.sUI.superAttack.cost);
        itemSelected = "superAttack";
    }

}

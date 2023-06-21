package Main;

import javax.swing.*;
import java.awt.*;

public class UI {
    GameManager gm;
    JFrame window;
    public JTextArea messageText;
    //player UI
    JPanel statsPanel;
    JTextArea healthTxt, atackTxt, defenceTxt, goldTxt;
    JTextArea playerStatsText;

    //monster chooser panel
    JPanel monsterPanel;
    JButton monsterButtons[] = new JButton[5];

    //battle UI
    JLabel enemyIcon;
    JPanel battlePanel;
    JButton attackButton, itemButton, runButton;
    JTextArea monsterStats;

    //game over UI
    public JLabel titleLabel;
    public JButton restartButton;

    public JPanel bgPanel[] = new JPanel[10];
    public JLabel bgLabel[] = new JLabel[10];
    public UI(GameManager gm) {
        this.gm = gm;
        createMainField();
        createGameOverField();
        generateScene();

        window.setVisible(true);
    }

    public void createMainField(){
        window = new JFrame();
        window.setSize(800,600);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().setBackground(Color.black);
        window.setLayout(null);

        messageText = new JTextArea();
        messageText.setBounds(50,410,700,150);
        messageText.setBackground(Color.blue);
        messageText.setForeground(Color.white);
        messageText.setEditable(false);
        messageText.setLineWrap(true);
        messageText.setWrapStyleWord(true);
        messageText.setFont(new Font("Book Antiqua", Font.PLAIN, 26));
        window.add(messageText);
    }
    public void createBackground(int bgNum, String bgFileName) {
        bgPanel[bgNum] = new JPanel();
        bgPanel[bgNum].setBounds(50,50,700,350);
        bgPanel[bgNum].setBackground(Color.blue);
        bgPanel[bgNum].setLayout(null);
        window.add(bgPanel[bgNum]);

        bgLabel[bgNum] = new JLabel();
        bgLabel[bgNum].setBounds(0,0,700,350);

        ImageIcon bgIcon = new ImageIcon(getClass().getClassLoader().getResource(bgFileName));
        bgLabel[bgNum].setIcon(bgIcon);


    }

    public void createObject(int bgNum, int objx, int objy, int objWidth, int objHeight, String objFilename, String command) {
        JButton objectButton = new JButton();
        objectButton.setBounds(objx, objy, objWidth, objHeight);
        objectButton.setBackground(null);
        objectButton.setContentAreaFilled(false);
        objectButton.setFocusPainted(false);
        objectButton.setFocusable(false);
        objectButton.setBorderPainted(true);  //
        objectButton.addActionListener(gm.aHandler);
        objectButton.setActionCommand(command);


        ImageIcon objectIcon = new ImageIcon(getClass().getClassLoader().getResource(objFilename));
        objectButton.setIcon(objectIcon);
        bgPanel[bgNum].add(objectButton);


    }
    public void createTransitionButton(int bgNum, int x, int y, int width, int height, String iconFileName, String command) {
        ImageIcon arrowIcon = new ImageIcon(getClass().getClassLoader().getResource(iconFileName));

        JButton arrowButton = new JButton();
        arrowButton.setBounds(x,y, width, height);
        arrowButton.setBackground(null);
        arrowButton.setContentAreaFilled(false);
        arrowButton.setFocusPainted(false);
        arrowButton.setFocusable(false);
        arrowButton.setBorderPainted(true);  //
        arrowButton.setIcon(arrowIcon);
        arrowButton.addActionListener(gm.aHandler);
        arrowButton.setActionCommand(command);

        bgPanel[bgNum].add(arrowButton);

    }

    public void createPlayerStats(int bgNum){
        playerStatsText = new JTextArea();
        playerStatsText.setBounds(0, 0, 100,100);
        playerStatsText.setBackground(Color.black);
        playerStatsText.setForeground(Color.white);
        playerStatsText.setEditable(false);
        playerStatsText.setFont(new Font("Book Antiqua", Font.PLAIN, 15));
        bgPanel[bgNum].add(playerStatsText);

    }

    public void updatePlayerStats(){
        playerStatsText.setText(gm.player.playerCurrentLife()+ "\n"
                +gm.player.getPlayerAttack()+"\n"
                +gm.player.getPlayerDefence()+"\n"
                +gm.player.getPlayerGold());
    }

    public void createGameOverField(){
        titleLabel = new JLabel("", JLabel.CENTER);
        titleLabel.setBounds(150, 150, 500, 200);
        titleLabel.setForeground(Color.red);
        titleLabel.setFont(new Font("Times New Roman", Font.PLAIN, 70));
        titleLabel.setVisible(false);
        window.add(titleLabel);

        restartButton = new JButton();
        restartButton.setBounds(340,320,120,50);
        restartButton.setBorder(null);
        restartButton.setForeground(Color.white);
        restartButton.setBackground(null);
        restartButton.setFocusPainted(false);
        restartButton.addActionListener(gm.aHandler);
        restartButton.setActionCommand("restart");
        restartButton.setVisible(false);
        window.add(restartButton);
    }
    public void createMonsterChooser(int bgNum, int x, int y, int width, int height){
        monsterPanel = new JPanel();
        monsterPanel.setBounds(x, y,width, height);
        monsterPanel.setLayout(new GridLayout(1,5));
        bgPanel[bgNum].add(monsterPanel);

        for(int i = 0; i<monsterButtons.length; i++){
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
        }
    }
    public void repaintMonsterChooser (){
        for(int i = 0; i<monsterButtons.length; i++) {
            monsterButtons[i].setBackground(gm.mSel.monsterStatus(i));
            monsterButtons[i].repaint();
            monsterButtons[i].setEnabled(gm.mSel.monsterUnlocked(i));
        }
    }

    public void createBattleOptionButtons(int bgNum){
        battlePanel = new JPanel();
        battlePanel.setBounds(200, 286, 300, 64);
        battlePanel.setLayout(new GridLayout(1,3));
        bgPanel[bgNum].add(battlePanel);

        attackButton = new JButton("Attack");
        attackButton.setContentAreaFilled(false);
        attackButton.setFocusPainted(false);
        attackButton.setFocusable(false);
        attackButton.addActionListener(gm.aHandler);
        attackButton.setActionCommand("attackMonster");
        battlePanel.add(attackButton);

        itemButton = new JButton("Inventory");
        itemButton.setContentAreaFilled(false);
        itemButton.setFocusPainted(false);
        itemButton.setFocusable(false);
        itemButton.addActionListener(gm.aHandler);
        itemButton.setActionCommand("openInventory");
        battlePanel.add(itemButton);

        runButton = new JButton("Run away");
        runButton.setContentAreaFilled(false);
        runButton.setFocusPainted(false);
        runButton.setFocusable(false);
        runButton.addActionListener(gm.aHandler);
        runButton.setActionCommand("runAway");
        battlePanel.add(runButton);

    }
    public void drawEnemy(int bgNum){
        enemyIcon = new JLabel();
        enemyIcon.setBounds(500, 100, 128,128);
        bgPanel[bgNum].add(enemyIcon);

        ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource(gm.mSel.monsterTab[gm.mSel.checkSelectedMonster()].getMonsterIcon()));
        enemyIcon.setIcon(icon);
        }
    public void repaintEnemy(){
        ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource(gm.mSel.monsterTab[gm.mSel.checkSelectedMonster()].getMonsterIcon()));
        enemyIcon.setIcon(icon);

    }

    public void drawMonsterStats(int bgNum){
        monsterStats = new JTextArea();
        monsterStats.setBounds(600,0,100,200);
        monsterStats.setBackground(Color.black);
        monsterStats.setForeground(Color.white);
        monsterStats.setEditable(false);
        monsterStats.setFont(new Font("Book Antiqua", Font.PLAIN, 15));
        bgPanel[bgNum].add(monsterStats);
    }
    public void redrawMonsterStats(){
        monsterStats.setText(gm.mSel.monsterTab[gm.mSel.checkSelectedMonster()].getName() +":\n" +
                "HP: " + gm.mSel.monsterTab[gm.mSel.checkSelectedMonster()].getHP() +"/" + gm.mSel.monsterTab[gm.mSel.checkSelectedMonster()].getMaxHP()
                +"\nAtt: "+ gm.mSel.monsterTab[gm.mSel.checkSelectedMonster()].getMaxAttack());
    }




    public void generateScene(){
        //SCENE 1
        createBackground(1, "bg1.png");
        createObject(1,440,140, 128, 128, "porcupinefish.png", "greenFish");
        createObject(1,100,140, 128, 128, "flatfish.png", "redFish");
        createTransitionButton(1, 0, 150, 50, 50, "shopArrow50x50.png", "goShop");
        createTransitionButton(1, 650, 150, 50, 50, "rightArrow50x50.png", "goBattle");
        createMonsterChooser(1, 190, 286, 320, 64);
        createPlayerStats(1);
        updatePlayerStats();


        bgPanel[1].add(bgLabel[1]);

        //SCENE 2
        createBackground(2, "shopBG700x350.png");
        createTransitionButton(2, 650, 150, 50, 50, "rightArrow50x50.png", "goHub");
        createPlayerStats(2);

        gm.sUI.createShopSelection(2);
        bgPanel[2].add(gm.sUI.selectPanel);
        updatePlayerStats();


        bgPanel[2].add(bgLabel[2]);

        //SCENE 3 Battle
        createBackground(3, "battleBG700x350.png");
        createTransitionButton(3, 0, 150, 50, 50, "shopArrow50x50.png", "goHub");
        createBattleOptionButtons(3);
        drawEnemy(3);
        drawMonsterStats(3);
        createPlayerStats(3);

        bgPanel[3].add(bgLabel[3]);
    }



}

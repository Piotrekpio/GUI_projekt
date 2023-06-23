package Main;

import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

public class UI implements ActionListener {

    GameManager gm;
    JFrame window;
    public JTextArea messageText;
    public JButton arrowButton;
    //player UI
    JTextArea playerStatsText;

    //monster chooser panel
    JPanel monsterPanel;
    JButton monsterButtons[] = new JButton[5];
    //consumables
    public JLabel cardIcon;
    public JLabel superAttackIcon;

    //battle UI
    JLabel enemyIcon;
    public JLabel shieldIcon;
    JPanel battlePanel;
    JButton attackButton, itemButton, runButton;
    JTextArea monsterStats;

    //game over UI
    public JLabel titleLabel;
    public JButton restartButton;
    // game finished
    public JLabel endLabel;
    //main menu UI
    public JLabel menuLabel, menuBackgroundIcon;
    public JPanel menuBackground;
    public JButton startButton;
    public JFormattedTextField levelSelect;

    public JPanel bgPanel[] = new JPanel[10];
    public JLabel bgLabel[] = new JLabel[10];

    //animation
    ImageIcon logo;
    Timer timer;
    int xVelocity = 10;
    int yVelocity = 10;
    int xAnimation = 10;
    int yAnimation = 10;

    public UI(GameManager gm) {
        this.gm = gm;
        createMainField();
        createGameOverField();
        createMainMenuField();
        createGameFinishedField();
        generateScene();

        window.setVisible(true);
    }

    public void createMainField(){
        window = new JFrame();
        window.setSize(800,600);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.getContentPane().setBackground(Color.black);
        window.setLocationRelativeTo(null);
        window.setResizable(false);
        window.setLayout(null);

        messageText = new JTextArea();
        messageText.setBounds(50,410,700,150);
        messageText.setBackground(Color.DARK_GRAY);
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

        arrowButton = new JButton();
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


    public void createGlobalPlayerStats(){
        playerStatsText = new JTextArea();
        playerStatsText.setBounds(50, 50, 100,120);
        playerStatsText.setBackground(null);
        playerStatsText.setOpaque(false);
        playerStatsText.setForeground(Color.white);
        playerStatsText.setEditable(false);
        playerStatsText.setFont(new Font("Book Antiqua", Font.PLAIN, 15));
        window.add(playerStatsText);

    }

    public void updatePlayerStats(){
        playerStatsText.setText("Player Stats:\n"+"      "+gm.player.playerCurrentLife()+ "\n"
                +"      "+gm.player.playerMinAttack + "-" + gm.player.playerMaxAttack+"\n"
                +"      "+gm.player.getPlayerDefence()+"\n"
                +"      "+gm.player.getPlayerGold()+"\n"
                +"      "+gm.player.getPlayerLevel());
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
    public void createGameFinishedField(){
        endLabel = new JLabel("", JLabel.CENTER);
        endLabel.setBounds(150, 50, 600, 300);
        endLabel.setForeground(Color.green);
        endLabel.setFont(new Font("Times New Roman", Font.PLAIN, 60));
        endLabel.setVisible(false);
        window.add(endLabel);

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
    public void createMainMenuField(){


        menuLabel = new JLabel("", JLabel.CENTER);
        menuLabel.setBounds(150, 150, 500, 200);
        menuLabel.setForeground(Color.WHITE);
        menuLabel.setFont(new Font("Times New Roman", Font.ITALIC, 70));
        menuLabel.setVisible(false);
        window.add(menuLabel);

        NumberFormat format = NumberFormat.getInstance();
        NumberFormatter formatter = new NumberFormatter(format);
        formatter.setValueClass(Integer.class);
        formatter.setMinimum(1);
        formatter.setMaximum(1000);
        formatter.setAllowsInvalid(false);
        formatter.setCommitsOnValidEdit(true);
        levelSelect = new JFormattedTextField(formatter);
        levelSelect.setBounds(340,370,120,50);
        levelSelect.setVisible(true);
        levelSelect.setFont(new Font("Book Antiqua", Font.PLAIN, 30));
        levelSelect.setForeground(Color.black);
        levelSelect.setValue(0);
        window.add(levelSelect);

        startButton = new JButton();
        startButton.setBounds(340,320,120,50);
        startButton.setBorder(null);
        startButton.setForeground(Color.white);
        startButton.setBackground(Color.blue);
        startButton.setFocusPainted(false);
        startButton.addActionListener(gm.aHandler);
        startButton.setActionCommand("start");
        startButton.setVisible(false);
        window.add(startButton);

        menuBackground = new JPanel();
        menuBackground.setBounds(0,0,800,600);
        menuBackground.setBackground(Color.yellow);
        window.add(menuBackground);
        menuBackground.setVisible(true);
        menuBackgroundIcon = new JLabel();
        menuBackgroundIcon.setBounds(0,0,800,600);
        menuBackground.add(menuBackgroundIcon);

        ImageIcon bgIcon = new ImageIcon(getClass().getClassLoader().getResource("menu_background.png"));
        menuBackgroundIcon.setIcon(bgIcon);
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
        battlePanel.setBounds(150, 286, 400, 64);
        battlePanel.setLayout(new GridLayout(1,3));
        bgPanel[bgNum].add(battlePanel);

        attackButton = new JButton("Attack");
        attackButton.setContentAreaFilled(false);
        attackButton.setFocusPainted(false);
        attackButton.setFocusable(false);
        attackButton.addActionListener(gm.aHandler);
        attackButton.setActionCommand("attackMonster");
        battlePanel.add(attackButton);

        itemButton = new JButton("Super Attack");
        itemButton.setContentAreaFilled(false);
        itemButton.setFocusPainted(false);
        itemButton.setFocusable(false);
        itemButton.addActionListener(gm.aHandler);
        itemButton.setActionCommand("superAttack");
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

        shieldIcon = new JLabel();
        shieldIcon.setBounds(500, 50, 128,128);
        bgPanel[bgNum].add(shieldIcon);
        ImageIcon shieldlogo = new ImageIcon(getClass().getClassLoader().getResource("checked-shield.png"));
        shieldIcon.setIcon(shieldlogo);
        shieldIcon.setVisible(false);
        }

    public void drawConsumables(int bgNum){
        cardIcon = new JLabel();
        cardIcon.setBounds(286, 0, 128,128);
        bgPanel[bgNum].add(cardIcon);

        ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("card.png"));
        cardIcon.setIcon(icon);
        cardIcon.setVisible(false);

        superAttackIcon = new JLabel();
        superAttackIcon.setBounds(350, 0, 128,128);
        bgPanel[bgNum].add(superAttackIcon);
        ImageIcon attackLogo = new ImageIcon(getClass().getClassLoader().getResource("superAttack.png"));
        superAttackIcon.setIcon(attackLogo);
        superAttackIcon.setVisible(false);
    }

    public void repaintEnemy(){
        ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource(gm.mSel.monsterTab[gm.mSel.checkSelectedMonster()].getMonsterIcon()));
        enemyIcon.setIcon(icon);

    }

    public void drawMonsterStats(int bgNum){
        monsterStats = new JTextArea();
        monsterStats.setBounds(600,0,100,95);
        monsterStats.setOpaque(false);
        monsterStats.setForeground(Color.white);
        monsterStats.setEditable(false);
        monsterStats.setFont(new Font("Book Antiqua", Font.PLAIN, 15));
        bgPanel[bgNum].add(monsterStats);
    }
    public void redrawMonsterStats(){
        monsterStats.setText("Enemy stats:\n" +
                "      "+ gm.mSel.monsterTab[gm.mSel.checkSelectedMonster()].getHP() +"/" + gm.mSel.monsterTab[gm.mSel.checkSelectedMonster()].getMaxHP()
                +"\n"+"      "+ gm.mSel.monsterTab[gm.mSel.checkSelectedMonster()].getMaxAttack()
                +"\n"+"      "+ gm.mSel.monsterTab[gm.mSel.checkSelectedMonster()].defence
                +"\n"+"      "+ gm.mSel.monsterTab[gm.mSel.checkSelectedMonster()].goldLoot);
    }




    public void generateScene(){
        //createMainMenu(4);
        createGlobalPlayerStats();

        //SCENE 1
        createBackground(1, "bg1.png");
        createTransitionButton(1, 0, 150, 50, 50, "shopArrow50x50.png", "goShop");
        createTransitionButton(1, 650, 150, 50, 50, "rightArrow50x50.png", "goBattle");
        createMonsterChooser(1, 190, 286, 320, 64);




        bgPanel[1].add(bgLabel[1]);

        //SCENE 2
        createBackground(2, "shopBG700x350.png");
        createTransitionButton(2, 650, 150, 50, 50, "rightArrow50x50.png", "goHub");


        gm.sUI.createShopSelection();
        gm.sUI.consumablesPanel();
        gm.sUI.createBuyDiscardButtons();
        gm.sUI.createInventory();
        drawConsumables(2);
        bgPanel[2].add(gm.sUI.selectPanel);
        bgPanel[2].add(gm.sUI.buyOrDiscard);
        bgPanel[2].add(gm.sUI.inventoryPanel);
        bgPanel[2].add(gm.sUI.consumables);
        bgPanel[2].add(bgLabel[2]);


        //SCENE 3 Battle
        createBackground(3, "battleBG700x350.png");
        createTransitionButton(3, 0, 150, 50, 50, "shopArrow50x50.png", "goHub");
        createBattleOptionButtons(3);
        drawEnemy(3);
        drawMonsterStats(3);
        drawConsumables(3);

        bgPanel[3].add(bgLabel[3]);
        startTimer();
    }


    public void startTimer(){
        timer = new Timer(10, this);
       // timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (xAnimation>= 500-128 || xAnimation<0){
            xVelocity = xVelocity * -1;
        }
        xAnimation = xAnimation + xVelocity;

        if (yAnimation>= 500-128 || yAnimation<0){
            yVelocity = yVelocity * -1;
        }
        yAnimation = yAnimation + yVelocity;
        System.out.println(xAnimation);

        enemyIcon = new JLabel();
        enemyIcon.setBounds(xAnimation, yAnimation, 128,128);
        enemyIcon.setIcon(logo);
        bgPanel[3].add(enemyIcon);


    }
}

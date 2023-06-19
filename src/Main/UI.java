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
    public JTextArea playerStatsText;

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
        createPlayerStats();
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
    private void setDefaultTxtArea(JTextArea textArea){
        textArea.setBackground(Color.black);
        textArea.setForeground(Color.white);
        textArea.setEditable(false);
        textArea.setFont(new Font("Book Antiqua", Font.PLAIN, 15));
    }

    public void createPlayerStats(){
        statsPanel = new JPanel();
        statsPanel.setBounds(50, 10,400, 30);
        statsPanel.setLayout(new GridLayout(1,8));
        window.add(statsPanel);
        //hp
        healthTxt = new JTextArea("HP");
        setDefaultTxtArea(healthTxt);
        statsPanel.add(healthTxt);
        //atack
        atackTxt = new JTextArea("ATACK");
        setDefaultTxtArea(atackTxt);
        statsPanel.add(atackTxt);
        //def
        defenceTxt = new JTextArea("Def");
        setDefaultTxtArea(defenceTxt);
        statsPanel.add(defenceTxt);
        //gold
        goldTxt = new JTextArea("gold");
        setDefaultTxtArea(goldTxt);
        statsPanel.add(goldTxt);


    }

    public void updatePlayerStats(){
        healthTxt.setText(gm.player.playerCurrentLife());
        atackTxt.setText(gm.player.getPlayerAttack());
        defenceTxt.setText(gm.player.getPlayerDefence());
        goldTxt.setText(gm.player.getPlayerGold());
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
    public void generateScene(){
        //SCENE 1
        createBackground(1, "bg1.png");
        createObject(1,440,140, 128, 128, "porcupinefish.png", "greenFish");
        createObject(1,100,140, 128, 128, "flatfish.png", "redFish");
        createTransitionButton(1, 0, 150, 50, 50, "shopArrow50x50.png", "goShop");
        bgPanel[1].add(bgLabel[1]);

        //SCENE 2
        createBackground(2, "shopBG700x350.png");
        createTransitionButton(2, 650, 150, 50, 50, "rightArrow50x50.png", "goHub");
        bgPanel[2].add(bgLabel[2]);
    }



}

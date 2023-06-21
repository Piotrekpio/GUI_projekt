package Main;

import Monster.*;

import java.awt.*;

public class MonsterSelect {
    GameManager gm;

    int currentMonster = 0;
    public SuperMonster monsterTab[] = new SuperMonster[5];

    public MonsterSelect(GameManager gm) {
        createMonsterTab();
        this.gm = gm;

    }

    public void createMonsterTab(){
        monsterTab[0] = new Monster_Nibba();
        monsterTab[1] = new Monster_2();
        monsterTab[2] = new Monster_3();
        monsterTab[3] = new Monster_4();
        monsterTab[4] = new Monster_5();
    }
    public Color monsterStatus(int i){
        if (monsterTab[i].getStatusSelected()){
            return Color.green;
        }
        else if (monsterTab[i].getStatusUnlocked()){
            return Color.BLUE;
        }
        else {
            return Color.gray;
        }
    }
    public boolean monsterUnlocked(int i){
       return monsterTab[i].getStatusUnlocked();
    }
    public void newMonsterSelected(int selectedMonster){
        for (int i = 0; i < monsterTab.length; i++){
            monsterTab[i].setSelected(false);
        }
        monsterTab[selectedMonster].setSelected(true);
    }
    public int checkSelectedMonster(){
        for (int i = 0; i < monsterTab.length; i++) {
            if (monsterTab[i].getStatusSelected()) {
                return i;
            }
        }
        return 0;
    }



}

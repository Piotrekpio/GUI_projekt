package Main;

import Event.Event1;

public class GameManager {
    ActionHandler aHandler = new ActionHandler(this);

    public Player player = new Player(this);
    public MonsterSelect mSel = new MonsterSelect(this);
    public UI ui = new UI(this);

    public Event1 ev1 = new Event1(this);
    public SceneChanger sChanger = new SceneChanger(this);

    public static void main(String[] args) {
        new GameManager();
    }
    public GameManager() {
        player.setPlayerDefaultStatus();
        sChanger.showHub();

    }

}

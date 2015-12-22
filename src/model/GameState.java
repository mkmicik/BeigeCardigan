package model;

public class GameState {

	private static GameState _instance;
	
	private GameMode mode;
	
	private int friendlyScore;
	private int enemyScore;
	
	private Map map;
	private double time;
	
	private Tank slowTank;
	private Tank fastTank;
	
	public static GameState getInstance() {
		if (_instance == null) {
			_instance = new GameState();
		}
		return _instance;
	}
}

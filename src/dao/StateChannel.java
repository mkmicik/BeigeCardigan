package dao;

public class StateChannel {
	
	private static StateChannel _instance;
	
	public static StateChannel getInstance() {
		if (_instance == null) {
			_instance = new StateChannel();
		}
		return _instance;
	}

}

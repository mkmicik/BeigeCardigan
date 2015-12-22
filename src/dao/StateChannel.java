package dao;

public class StateChannel {
	
	private static StateChannel _instance;
	
	private static final String host = "127.0.0.1";
	private static final int port = 5557;
	
	public static StateChannel getInstance() {
		if (_instance == null) {
			_instance = new StateChannel();
		}
		return _instance;
	}

}

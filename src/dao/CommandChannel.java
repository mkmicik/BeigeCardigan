package dao;

import com.google.gson.Gson;

public class CommandChannel {

	private static CommandChannel _instance;
	
	private static final String host = "127.0.0.1";
	private static final int port = 5557;
	private static String UUID;
	
	public static CommandChannel getInstance() {
		if (_instance == null) {
			_instance = new CommandChannel();
		}
		return _instance;
	}
}

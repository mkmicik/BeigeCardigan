package dao;

public class CommandChannel {

	private static CommandChannel _instance;
	
	public static CommandChannel getInstance() {
		if (_instance == null) {
			_instance = new CommandChannel();
		}
		return _instance;
	}
}

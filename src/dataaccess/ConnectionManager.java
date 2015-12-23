package dataaccess;

public class ConnectionManager {
	
	private static ConnectionManager _instance;
	
	private static CommandChannel cc;
	private static StateChannel sc;
	
	public ConnectionManager getInstance() {
		if (_instance == null) {
			_instance = new ConnectionManager();
		}
		return _instance;
	}
	
	public ConnectionManager() {
		cc = new CommandChannel();
		sc = new StateChannel();
	}

	/*
	1. Create connections to the Central Game System (using the publish/subscribe envelope).
		a. Establish the publish/subscribe ZeroMQ connection to the central game system (state channel).
		b. Establish the request/response ZeroMQ connection to the central game system (command channel).
	2. Connect to the match using a provided match token to obtain the client token. 
		a. Build a match connect message.
		b. Send the match connect message over the command channel.
		c. Receive and store the client token.
	3. Wait for the game to start.
		a. Monitor the state channel for game board state matching the game.
	4. Play the game.
		a. Monitor the state channel for game state information.
		b. Build and send game move messages over the command channel. 
		c. Monitor the state channel for game end messages.
	*/
	
	
}

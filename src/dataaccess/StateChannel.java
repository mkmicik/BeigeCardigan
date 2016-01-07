package dataaccess;

import org.zeromq.ZMQ;

public class StateChannel {
	
	private ZMQ.Context context;
	
	private ZMQ.Socket socket;
	private String socketAddress;
	
	private static final int port = 5556;
	//private static String UUID;

	public StateChannel(String ipAddress, String matchToken) {
		
		this.socketAddress = "tcp://" + ipAddress + ":" + port;
		
		context = ZMQ.context(1);
		socket = context.socket(ZMQ.PUB);
		
		socket.connect(socketAddress);
		socket.subscribe(matchToken.getBytes());
	}
}

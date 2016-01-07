package dataaccess;

import org.zeromq.ZContext;
import org.zeromq.ZFrame;
import org.zeromq.ZMQ;
import org.zeromq.ZMQ.PollItem;
import org.zeromq.ZMQ.Socket;
import org.zeromq.ZMsg;
import org.zeromq.ZMQ.Poller;

import messages.MatchConnectCommand;
import json.Converter;
import constants.Constants;

public class CommandChannel {
	
	private ZMQ.Context context;
	
	private ZMQ.Socket socket;
	private String socketAddress;
	private String matchToken;
	
	private static final int port = 5557;
	//private static String UUID;

	public CommandChannel(String ipAddress, String matchToken) {
		
		this.matchToken = matchToken;
		this.socketAddress = "tcp://" + ipAddress + ":" + port;
		
		context = ZMQ.context(1);
		socket = context.socket(ZMQ.REQ);
		
		socket.connect(socketAddress);

	}
	
	public void connect() {
		MatchConnectCommand cmd = new MatchConnectCommand(matchToken, Constants.team_name, Constants.password);
		String json_cmd = Converter.toJSON(cmd);
		
		System.out.println(json_cmd);
	}
	
	
}

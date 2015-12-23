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

//import com.google.gson.Gson;

public class CommandChannel {
	
	private static final String host = "127.0.0.1";
	private static final int port = 5557;
	private static String UUID;

	public CommandChannel() {
		MatchConnectCommand cmd = new MatchConnectCommand(Constants.match_token, Constants.team_name, Constants.password);
		String json_cmd = Converter.toJSON(cmd);
		
		System.out.println(json_cmd);
		
		
		
	}
	
	
}

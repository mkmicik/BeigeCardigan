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
		
		ZContext ctx = new ZContext();
		Socket client = ctx.createSocket(ZMQ.DEALER);
		
		//  Set random identity to make tracing easier
		String identity = String.format("%04X-%04X", rand.nextInt(), rand.nextInt());
		client.setIdentity(identity.getBytes());
		client.connect("tcp://localhost:5570");
		
		PollItem[] items = new PollItem[] { new PollItem(client, Poller.POLLIN) };
		
		int requestNbr = 0;
		while (!Thread.currentThread().isInterrupted()) {
		    //  Tick once per second, pulling in arriving messages
		for (int centitick = 0; centitick < 100; centitick++) {
		    ZMQ.poll(items, 10);
		    if (items[0].isReadable()) {
		        ZMsg msg = ZMsg.recvMsg(client);
		        msg.getLast().print(identity);
		        msg.destroy();
		    }
		}
		client.send(String.format("request #%d", ++requestNbr), 0);
		}
		ctx.destroy();
		
		
		
	}
	
	
}

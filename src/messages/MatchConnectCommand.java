package messages;

public class MatchConnectCommand extends Message {

	private static final String comm_type = "MatchConnect";
	private String match_token;
	private String team_name;
	private String password;
	
	public MatchConnectCommand(String match_token, String team_name, String password) {
		this.match_token = match_token;
		this.team_name = team_name;
		this.password = password;
	}
}

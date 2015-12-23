package messages;

public class ResponseError extends Message {

	private static final String comm_type = "ErrorResp";
    
	private String error;
    private String message;
	
}

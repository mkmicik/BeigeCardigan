package json;

import java.lang.reflect.Type;
import com.google.gson.*;

import messages.*;

public final class Converter {
	
	private static Gson gson = new Gson();
	
	public static String toJSON(Message m) {
		return gson.toJson(m);
	}

	public static Message fromJSON(String s) {
		return new MatchConnectResponse();
	}
	
}

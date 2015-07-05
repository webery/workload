package dlnu.workload.framework.mvc.model;

import net.sf.json.JSONObject;

public class JsonResponse {

	public static String getJsonResponse(Integer code, String message) {
		JsonMessage jsonMessage = new JsonMessage(code, message);
		return JSONObject.fromObject(jsonMessage).toString();
	}

	public static JsonMessage getJsonMessage(Integer code, String message) {
		return new JsonMessage(code, message);
	}
}

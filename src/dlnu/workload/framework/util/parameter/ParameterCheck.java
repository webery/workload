package dlnu.workload.framework.util.parameter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParameterCheck {

	public static boolean checkObjectId(String objectId) {

		if (objectId == null) {
			return false;
		}
		Pattern pattern = Pattern.compile("[a-z0-9]{24}");
		Matcher matcher = pattern.matcher(objectId);
		if (matcher.matches()) {
			return true;
		}

		return false;
	}
}

package capstone.common.constant;

import java.util.regex.Pattern;

public class CommonConstant {
	
	public static String INVALID = "999";
	
	public static String VALID = "000";
	
	public static String BLANK = "";
	
	public static String EMAIL = "mieminabua@gmail.com";
	
	public static String NULL = null;
	
	public static Pattern EMAIL_PATTERN = Pattern
			.compile("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$");
	
}

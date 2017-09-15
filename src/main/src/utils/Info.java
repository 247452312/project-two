package utils;

import java.text.SimpleDateFormat;
import java.util.Date;


public class Info {
	public static String getNow() {
		return new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
	}

	public static String getClientCode() {
		return new SimpleDateFormat("yyyyMMddHHmmSS").format(new Date());
	}

}

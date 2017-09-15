package utils;

public class StringUtils {

	public static String getBigName(String fildeName) {
		byte[] items = fildeName.getBytes();
		items[0] = (byte) ((char) items[0] - 'a' + 'A');
		return new String(items);
	}

	public static String getLittleName(String fildeName) {
		byte[] items = fildeName.getBytes();
		items[0] = (byte) ((char) items[0] - 'A' + 'a');
		return new String(items);
	}
	public static String AddDId(String name){
		return name+".id";
	}


}

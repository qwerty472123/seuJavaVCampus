package vCampus.utility;

import java.io.*;

public class FileOperate {
	public static String readFile(String name) {
		try {
			File file = new File(name);
			InputStream input;
			input = new FileInputStream(file);
			byte[] buf = new byte[(int) file.length()];
			input.read(buf);
			input.close();
			return new String(buf);
		} catch (Exception e) {
			Config.log(e);
		}
		return "";
	}
	public static void writeFile(String name, String text) {
		try {
			OutputStream output = new FileOutputStream(name, false);
			output.write(text.getBytes());
			output.close();
		} catch (Exception e) {
			Config.log(e);
		}
	}
}

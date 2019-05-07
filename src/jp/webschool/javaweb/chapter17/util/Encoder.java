package jp.webschool.javaweb.chapter17.util;

import java.io.UnsupportedEncodingException;

public class Encoder {

	public static String encodeUTF8(String latin1) {
		String utf8 = null;
		try {
			utf8 = new String(latin1.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return utf8;
	}
}

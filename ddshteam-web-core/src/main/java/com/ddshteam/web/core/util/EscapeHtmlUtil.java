package com.ddshteam.web.core.util;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Set;

public class EscapeHtmlUtil {
	public static final HashMap<Character, String> ESCAPE_MAP = new HashMap<>();
	
	static {
		init();
	}
	
	private static void init() {
		ESCAPE_MAP.put('<', "&lt;");
		ESCAPE_MAP.put('>', "&gt;");
		ESCAPE_MAP.put('=', "#e");
		ESCAPE_MAP.put('*', "#s");
		ESCAPE_MAP.put('\'', "#1");
		ESCAPE_MAP.put('\"', "&quot;");
		ESCAPE_MAP.put('&', "&amp;");
		ESCAPE_MAP.put('|', "&brvbar;");

	}
	
	
	public static String escapeHtml(String content) {
		if (content == null) {
			return null;
		}
		StringWriter sw = new StringWriter();
		long len = content.length();
		char c;
		for (int i = 0; i < len; i++) {
			c = content.charAt(i);
			if (ESCAPE_MAP.containsKey(c)) {
				sw.write(ESCAPE_MAP.get(c));
			} else {
				sw.write(c);
			}
		}
		return sw.toString();
	}

	
	public static String unescapeHtml(String content) {

		if (content == null) {
			return null;
		}
		Set<Character> key = ESCAPE_MAP.keySet();
		for (char k : key) {
			content = content.replaceAll(ESCAPE_MAP.get(k), String.valueOf(k));
		}
		return content;
	}

}

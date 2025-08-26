package com.gao.hsbc;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringService implements IStringService {
	private static final Pattern PATTERN = Pattern.compile("([a-z])\\1{2,}");

	@Override
	public String remove(String input) {
		if (input == null || input.isEmpty()) {
			return input;
		}

		Matcher matcher = PATTERN.matcher(input);

		while (matcher.find()) {
			String originalStr = matcher.group();

			input = input.replace(originalStr, "");
			matcher = PATTERN.matcher(input);
		}

		return input;
	}

	@Override
	public String replace(String input) {
		if (input == null || input.isEmpty()) {
			return input;
		}

		Matcher matcher = PATTERN.matcher(input);

		while (matcher.find()) {

			String originalStr = matcher.group();
			int idx = input.indexOf(originalStr);
			String replace = idx == 0 ? "" : "" + input.charAt(idx - 1);
			input = input.replace(originalStr, replace);
			matcher = PATTERN.matcher(input);
		}

		return input;
	}

}

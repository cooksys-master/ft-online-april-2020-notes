package com.cooksys.collections;

import java.util.ArrayList;
import java.util.List;

public class FizzBuzz {
	
	public static boolean divisible(int a, int b) {
		return a % b == 0;
	}
	
	public static String message(int x) {
		String result = x + ": ";
		if (divisible(x, 3)) {
			result += "Fizz";
		}
		if (divisible(x, 5)) {
			result += "Buzz";
		}
		return result.equals(x + ": ") ? null : result;
	}
	
	public static String[] messages(int start, int end) {
		List<String> result = new ArrayList<>();
		for (int i = start; i < end; i++) {
			String message = message(i);
			if (message != null) {
				result.add(message);
			}
		}
		return result.toArray(new String[result.size()]);
	}

}

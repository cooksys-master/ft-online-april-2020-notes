package com.cooksys.collections;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import com.cooksys.collections.generics.Box;
import com.cooksys.collections.hotel.Guest;
import com.cooksys.collections.hotel.Hotel;

public class Application {
	
	public static void main( String[] args ) {
		
//		int[] numbers = new int[5];
		
//		int[] temp = new int[10];
//		for (int i = 0; i < 10; i++) {
//			temp[i] = i + 1;
//		}
//		numbers = temp;
		
//		for (int i = 0; i < 100; i++) {
//			if (i < numbers.length) {
//				numbers[i] = i + 1;
//			} else {
//				int[] temp = new int[numbers.length + 1];
//				for (int j = 0; j < numbers.length; j++) {
//					temp[j] = numbers[j];
//				}
//				temp[i] = i + 1;
//				numbers = temp;
//			}
//		}
//		
//		for (int i = 0; i < numbers.length; i++) {
//			System.out.println(numbers[i]);
//		}
		
		// List,      Set,     Map
		// ArrayList, HashSet, HashMap
		
//		List<Silverware> silverware = new ArrayList<>();
		
		// Integer -> int
		// Double -> double
		// Float -> float
		
		// Type Generics
//		Box<Integer> integerBox = new Box<>(5);
//		Box<String> stringBox = new Box<>("Hello");
//		
//		System.out.println(stringBox.getX());
//		
//		Silverware s = new Knife();
//		
//		if (s instanceof Spoon) {
//			((Spoon) s).scoop();
//		}
//		
//		System.out.println(countVowels("William"));
//		
//		Hotel hilton = new Hotel();
//		
//		Guest guest1 = new Guest("Will");
//		Guest guest2 = new Guest("Frank");
//		
//		Set<Guest> guests = new HashSet<>();
//		guests.add(guest1);
//		guests.add(guest2);
//		
//		hilton.checkIn(309, guests);
//		
//		System.out.println(hilton.getGuestList());
//		
//		hilton.checkOut(309);
//		
//		System.out.println(hilton.getGuestList());
		
		hashCodeAndEquals();
		
	}
	
	
	public static int countVowels(String s) {
		Set<String> vowels = new HashSet<>();
		vowels.add("a");
		vowels.add("a");
		vowels.add("e");
		vowels.add("i");
		vowels.add("o");
		vowels.add("u");
		
		String[] characters = s.split("");
		
		int result = 0;
		for (String c : characters) {
			if (vowels.contains(c)) {
				result++;
			}
		}
		return result;
	}
	
	
	public static void hashCodeAndEquals() {
		Spoon s1 = new Spoon("metal");
		Spoon s2 = new Spoon("wood");
		
		System.out.println(s1 == s2);
		System.out.println(s1.equals(s2));
	}
	
	
}
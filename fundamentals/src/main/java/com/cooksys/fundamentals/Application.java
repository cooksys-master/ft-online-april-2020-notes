package com.cooksys.fundamentals;

import com.cooksys.fundamentals.characters.Goblin;
import com.cooksys.fundamentals.characters.GoblinRanger;
import com.cooksys.fundamentals.characters.GoblinWizard;
import com.cooksys.fundamentals.characters.Human;
import com.cooksys.fundamentals.characters.HumanRanger;
import com.cooksys.fundamentals.characters.HumanWarrior;
import com.cooksys.fundamentals.characters.HumanWizard;
import com.cooksys.fundamentals.characters.Character;

public class Application {
	
	public static void main( String[] args ) {
		
		// WORA (Write Once, Run Anywhere)
		// JIT (Just In Time)
		// Bytecode
		
		// Object-Oriented Programming
		// Functional Programming
		
		// Java is not 100% Object-Oriented
		/*
		 * Java has 8 primitive values
		 * 
		 * int
		 * float
		 * double
		 * long
		 * short
		 * boolean
		 * char
		 * byte
		 * 
		 */
		
		// Java is a statically typed language
		// as opposed to dynamically typed
		
		// Stack
		
		// Heap
		
		int x = 5;
		
		String s = "Hello";
		
		for (int i = 0; i < 11; i++) {
			x += i;
		}
		
		System.out.println(x);
		
		if (x > 50) {
			System.out.println(x + " is greater than 50");
		} else if (x < 50) {
			System.out.println(x + " is less than 50");
		} else {
			System.out.println(x + " is 50");
		}
		
		System.out.println("Continue execution");
		
		HumanWarrior warriorOne = new HumanWarrior(100);
		HumanWarrior warriorTwo = new HumanWarrior(20);
	
		
//		System.out.println("Warrior One's health is " + warriorOne.getHealth());
//		System.out.println("Warrior Two's health is " + warriorTwo.getHealth());
		
		Human fighter = new HumanRanger(100);
		Goblin goblin = new GoblinRanger(100);
//		
//		int count = 1;
//		
//		while (goblin.isAlive()) {
//			goblin.takeDamage(fighter.attack());
//			fighter.takeDamage(goblin.attack());
//			System.out.println("The fighter attacked for the " + count + " time");
//			count++;
//		}
//		
//		if (!goblin.isAlive()) {
//			System.out.println("The goblin was defeated!");
//			System.out.println("Is the fighter alive: " + fighter.isAlive());
//		}
//		
//		fighter = new HumanWizard(100);
		
		fight(fighter, goblin);
		
		fight(new HumanWarrior(500), new GoblinRanger(200));
		
		fight(new HumanRanger(50), new GoblinWizard(1000));
		
		
		
	}
	
	public static void fight(Human human, Goblin goblin) {
		int count = 1;
		while (goblin.isAlive() && human.isAlive()) {
			goblin.takeDamage(human.attack());
			human.takeDamage(goblin.attack());
			System.out.println("The human attacked for the " + count + " time");
			count++;
		}
		
		if (!goblin.isAlive()) {
			System.out.println("The goblin was defeated!");
			System.out.println("Is the human alive: " + human.isAlive());
		} else {
			System.out.println("The human was defeated!");
			System.out.println("Is the goblin alive: " + goblin.isAlive());
		}
	}
	
}
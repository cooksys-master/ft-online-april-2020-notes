package com.cooksys.serialization;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Warrior {
	
	private String name;
	private int health;
	List<Item> items;
	
	public Warrior() {
	}
	
	public Warrior(String name, int health) {
		this.name = name;
		this.health = health;
		this.items = new ArrayList<>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}
	
	public List<Item> getItems() {
		return items;
	}
	
	public void addItem(Item item) {
		items.add(item);
	}
	
	public void removeItem(Item item) {
		items.remove(item);
	}
	
	public void addAllItems(List<Item> items) {
		items.addAll(items);
	}
	
}

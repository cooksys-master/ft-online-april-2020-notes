package com.cooksys.collections.hotel;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Hotel {
	
	private Map<Integer, Set<Guest>> rooms;
	
	public Hotel() {
		rooms = new HashMap<>();
	}
	
	public void checkIn(Integer roomNumber, Set<Guest> guests) {
		rooms.put(roomNumber, guests);
	}
	
	public void checkOut(Integer roomNumber) {
		rooms.remove(roomNumber);
	}
	
	public String getGuestList() {
		return rooms.toString();
	}

}

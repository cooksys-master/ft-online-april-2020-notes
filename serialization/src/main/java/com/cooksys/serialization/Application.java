package com.cooksys.serialization;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Set;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Application {

	private static ObjectMapper objectMapper;

	public static void main(String[] args) {

		Warrior warrior = new Warrior("Thor", 100);

		Item item1 = new Sword(15);
		Item item2 = new Shield(5);
		Item item3 = new HealthPotion(1);

		warrior.addItem(item1);
		warrior.addItem(item2);
		warrior.addItem(item3);

		try {
			saveGame(warrior);
		} catch (JsonParseException e) {
			System.out.println("Problem reading in the json object");
			e.printStackTrace();
		} catch (JsonGenerationException e) {
			System.out.println("Problem generating json for the given object");
			e.printStackTrace();
		} catch (JsonMappingException e) {
			System.out.println("Problem mapping the given object to JSON or converting JSON to Java");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("General IOException");
			e.printStackTrace();
		}

	}

	public static ObjectMapper getObjectMapper() {
		if (objectMapper == null) {
			objectMapper = new ObjectMapper();
		}
		return objectMapper;
	}

	public static void serializeObject() throws JsonGenerationException, JsonMappingException, IOException {
		Warrior warrior = new Warrior("Thor", 100);

		Item item1 = new Sword(15);
		Item item2 = new Shield(5);
		Item item3 = new HealthPotion(1);

		warrior.addItem(item1);
		warrior.addItem(item2);
		warrior.addItem(item3);

		ObjectMapper objectMapper = getObjectMapper();

		objectMapper.writeValue(new File("warrior.json"), warrior);
	}

	public static void deserializeObject() throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper objectMapper = getObjectMapper();

		Warrior warrior = objectMapper.readValue(new File("warrior.json"), Warrior.class);

		System.out.println(warrior.getName());
		System.out.println(warrior.getHealth());
		System.out.println(warrior.getItems());

	}

	public static void saveGame(Warrior warrior) throws JsonGenerationException, JsonMappingException, IOException {
		Save save = new Save(warrior, LocalDate.now());
		ObjectMapper objectMapper = getObjectMapper();

		objectMapper.writeValue(new File("save.json"), save);
	}

}
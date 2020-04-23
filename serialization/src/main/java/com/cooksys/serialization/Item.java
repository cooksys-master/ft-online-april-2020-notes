package com.cooksys.serialization;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
	@Type(value = HealthPotion.class, name = "Health Potion"),
	@Type(value = Shield.class, name = "Shield"),
	@Type(value = Sword.class, name = "Sword")
})
public interface Item {
	
}

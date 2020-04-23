package com.cooksys.serialization;

import java.time.LocalDate;

public class Save {

	private Warrior warrior;
	private LocalDate saveDate;

	public Save() {
	}

	public Save(Warrior warrior, LocalDate saveDate) {
		this.warrior = warrior;
		this.saveDate = saveDate;
	}

	public Warrior getWarrior() {
		return warrior;
	}

	public void setWarrior(Warrior warrior) {
		this.warrior = warrior;
	}

	public LocalDate getSaveDate() {
		return saveDate;
	}

	public void setSaveDate(LocalDate saveDate) {
		this.saveDate = saveDate;
	}

}

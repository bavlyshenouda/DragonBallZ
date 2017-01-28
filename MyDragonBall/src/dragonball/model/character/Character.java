package dragonball.model.character;

import java.io.Serializable;

public abstract class Character implements Serializable {

	private static final long serialVersionUID = 1L;
	private String name;

	public Character(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}

package starter;

public class Stats {
	public Stats(int powerPunch, int powerKick, int defense, int health) {
		super();
		this.powerPunch = powerPunch;
		this.powerKick = powerKick;
		this.defense = defense;
		this.health = health;
	}

	private int powerPunch;
	private int powerKick;
	private int defense;
	private int health;
	public int getPowerPunch() {
		return powerPunch;
	}
	public void setPowerPunch(int powerPunch) {
		this.powerPunch = powerPunch;
	}
	public int getPowerKick() {
		return powerKick;
	}
	public void setPowerKick(int powerKick) {
		this.powerKick = powerKick;
	}
	public int getDefense() {
		return defense;
	}
	public void setDefense(int defense) {
		this.defense = defense;
	}

}

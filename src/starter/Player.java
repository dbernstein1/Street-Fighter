package starter;

public class Player {

	public Player(PlayerName name, Stats stats) {
		super();
		this.name = name;
		this.stats = stats;
	}
	
	//TODO make State classes
	//private State state
	private Stats stats;
	private PlayerName name;

	public PlayerName getPlayerName() {
		return name;
	}

	public int powerOfPunch()
	{
		return 0;
	}
	
	public int powerOfKick()
	{
		return 0;
	}
	
	public void jump()
	{
		return;
	}
	
	public void moveRight()
	{
		return;
	}
	
	public void moveLeft()
	{
		return;
	}
	
	public void movePlayer(int spaces)
	{
		return;
	}
	
	public void decrementHealth(int opPower)
	{
		return;
	}
	
	public void handleInput(Input input)
	{
		return;
	}
	
	public static void main(String[] args) {

	}	
}

package starter;
import Player;
import Referee;

public class Game {
	public Game(Referee ref, Player pOne, Player pTwo, Background background, GRect healthbarP1, GRect healthbarP2) {
		super();
		this.ref = ref;
		this.pOne = pOne;
		this.pTwo = pTwo;
		this.background = background;
		this.healthbarP1 = healthbarP1;
		this.healthbarP2 = healthbarP2;
	}

	private Referee ref;
	private Player pOne;
	private Player pTwo;
	private Background background;
	private GRect healthbarP1;
	private GRect healthbarP2;
	
	public void handleCollision()
	{
		
	}
	
	//updates screen on current state, location and health of players
	public void updateScreen()
	{
		
	}
	
	public boolean gameOver()
	{
		return true;
	}
	
	public void playGame()
	{
		return;
	}
}

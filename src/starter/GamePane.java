package starter;
import java.awt.Color;
import java.awt.event.*;

import java.util.ArrayList;

import javax.swing.*;

import acm.graphics.*;

public class GamePane extends GraphicsPane implements ActionListener {
	private Background bgPort;
	private Background bgPort2;
	private Background background;
	private Background bgBeach, bgBeach2, bgBeach3;
	private Background bgForest;

	private GImage gameBack;
	private Result disp_p1;
	private boolean game_Over=false;
	public Animation p1Animation;
	public Animation p2Animation;
	private Result disp_p2;
	public GamePane(MainApplication app)
	{
		super();
		this.program = app;
		background = program.background;
		bgPort = program.backgroundPort;
		bgPort2 = program.backgroundPort2;
		bgForest = program.backgroundForest;
		bgBeach = program.backgroundBeach;
		bgBeach2 = program.backgroundBeach2;
		bgBeach3 = program.backgroundBeach3;
		t=new Timer(50,this);
		disp_p1=new Result(program,1);
		disp_p2=new Result(program,2);
	}


	private Player PLAYER_ONE;
	private Player PLAYER_TWO;
	private Level level; 

	private static MainApplication program;
	public static final int GROUND = 550;
	private Timer t;
	private int numTimes = 0;
	//	backgroundPort = new Background("portMapMain.png", WINDOW_WIDTH, WINDOW_HEIGHT);
	//  backgroundPort2 = new Background("portMapMain2.png", WINDOW_WIDTH, WINDOW_HEIGHT);

	public Player getPLAYER_ONE() {
		return PLAYER_ONE;
	}


	public void setPLAYER_ONE(Player pLAYER_ONE) {
		PLAYER_ONE = pLAYER_ONE;
	}


	public Player getPLAYER_TWO() {
		return PLAYER_TWO;
	}


	public void setPLAYER_TWO(Player pLAYER_TWO) {
		PLAYER_TWO = pLAYER_TWO;
	}

	public void set_Choice(Level l) {
		level=l;
	}

	public static void add(GObject something)
	{
		program.add(something);
	}

	public static void remove(GObject something)
	{
		program.remove(something);
	}
	public static void playerMove(Player p, double horizontal, double vertical) { // need 2 remove static

		if (p == null) return;

		if (p.body.getX()<0 || p.body.getX()>MainApplication.WINDOW_WIDTH-p.body.getWidth()) { 

			moveBody(p, 4* -horizontal, vertical);

			return;
		}		
		moveBody(p, horizontal, vertical);

		System.out.println("Movement: "+ p.body);

	}
	private static void moveBody(Player p, double horizontal, double vertical) {
		p.head.move(horizontal, vertical);
		p.body.move(horizontal, vertical);
		p.leg.move(horizontal, vertical);
		p.arm.move(horizontal, vertical);
		p.punch.move(horizontal, vertical);
		p.kick.move(horizontal, vertical);
	}

	public void updateHealthPoints(Player p) {

		if (p == null) return;

		remove(p.hpbar);
		p.hpbar = new GRect(p.hpbarx, 25, 500 * (p.hp / p.hptotal), 15);
		p.hpbar.setFillColor(p.hp > 40 ? new Color(0, 255, 0) : new Color(255,0,0));
		p.hpbar.setFilled(true);
		add(p.hpbar);

		gameOver();
	}

	public void add(Background bgPort) {
		program.add(bgPort);
	}

	public void remove(Background bgPort) {
		program.remove(bgPort);
	}
	
	public void addBeach(Background bgBeach) 
	{
		program.add(bgBeach);
	}
	
	public void removeBeach(Background bgBeach)
	{
		program.remove(bgBeach);
	}
	

	public void actionPerformed(ActionEvent e)
	{
		numTimes ++;
		if(level.get_Choice()==2)
		{
			if (numTimes % 20 == 0) {
				gameBack.setImage("portMapMain.png");
			}
			else if (numTimes % 20 == 10){
				gameBack.setImage("portMapMain2.png");
			}
		}
		else if (level.get_Choice() == 4)
		{
			if (numTimes % 18 == 0) 
			{
				gameBack.setImage("maps/BeachMap/beachMapMain.png");
			}
			else if (numTimes % 18 == 6)
			{
				gameBack.setImage("maps/BeachMap/beachMap2.png");
			}
			else if (numTimes % 18 == 12) 
			{
				gameBack.setImage("maps/BeachMap/beachMap3.png");
			}
		}
		showUpdatedContents();
		PLAYER_ONE.HandleMovement();
		PLAYER_TWO.HandleMovement();
		p1Animation.handleState();
		p2Animation.handleState();
		handleCollision();
	}

	@Override
	public void keyPressed(KeyEvent e)
	{
		if(e.getKeyCode()==KeyEvent.VK_RIGHT)
		{
			if(!PLAYER_TWO.isBackward)
			{
				PLAYER_TWO.isForward=true;
			}
		}
		if(e.getKeyCode()==KeyEvent.VK_LEFT)
		{
			if(!PLAYER_TWO.isForward)
			{
				PLAYER_TWO.isBackward=true;
			}
		}
		if(e.getKeyCode()==KeyEvent.VK_D)
		{
			if(!PLAYER_ONE.isBackward)
			{
				PLAYER_ONE.isForward=true;
			}
		}
		if(e.getKeyCode()==KeyEvent.VK_A)
		{
			if(!PLAYER_ONE.isForward)
			{
				PLAYER_ONE.isBackward=true;
			}
		}
		if(e.getKeyCode()==KeyEvent.VK_UP)
		{	
			if(!PLAYER_TWO.isJumping  && !PLAYER_TWO.isDucking)
			{
				PLAYER_TWO.jump=-10;
				PLAYER_TWO.isJumping=true;
			}
		}
		if(e.getKeyCode()==KeyEvent.VK_W)
		{
			if(!PLAYER_ONE.isJumping  && !PLAYER_ONE.isDucking)
			{
				PLAYER_ONE.jump=-10;
				PLAYER_ONE.isJumping=true;
			}
		}
		if(e.getKeyCode()==KeyEvent.VK_DOWN)
		{	
			if(!PLAYER_TWO.isDucking  && !PLAYER_TWO.isJumping)
			{
				PLAYER_TWO.duck=7;
				PLAYER_TWO.isDucking=true;
			}
		}
		if(e.getKeyCode()==KeyEvent.VK_S)
		{	
			if(!PLAYER_ONE.isDucking && !PLAYER_ONE.isJumping)
			{
				PLAYER_ONE.duck=7;
				PLAYER_ONE.isDucking=true;
			}
		}
		if(e.getKeyCode()==KeyEvent.VK_P)
		{
			if(!PLAYER_ONE.isPunching && !PLAYER_ONE.isKicking)
			{
				disp_p1.addTotal_p();
				PLAYER_ONE.hittime=0;
				remove(PLAYER_ONE.arm);
				PLAYER_ONE.isPunching=true;
			}
		}
		if(e.getKeyCode()==KeyEvent.VK_SPACE)
		{
			if(!PLAYER_TWO.isPunching && !PLAYER_TWO.isKicking)
			{
				if(game_Over)
				{
					program.switchToMenu();
				}
				else
				{
					disp_p2.addTotal_p();
					PLAYER_TWO.hittime=0;
					remove(PLAYER_TWO.arm);
					PLAYER_TWO.isPunching=true;
				}
			}
		}
		if(e.getKeyCode()==KeyEvent.VK_CONTROL)
		{
			if(!PLAYER_TWO.isKicking && !PLAYER_TWO.isPunching)
			{
				disp_p2.addTotal_k();
				PLAYER_TWO.hittime=0;
				PLAYER_TWO.isKicking=true;
			}
		}
		if(e.getKeyCode()==KeyEvent.VK_K)
		{
			if(!PLAYER_ONE.isKicking && !PLAYER_ONE.isPunching)
			{
				disp_p1.addTotal_k();
				PLAYER_ONE.hittime=0;
				PLAYER_ONE.isKicking=true;
			}
		}
		if(e.getKeyCode()==KeyEvent.VK_ESCAPE)
		{
			t.stop();
			program.switchToPauseMenu();
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e)
	{
		if(e.getKeyCode()==KeyEvent.VK_RIGHT)
		{
			PLAYER_TWO.isForward=false;
		}
		if(e.getKeyCode()==KeyEvent.VK_LEFT)
		{
			PLAYER_TWO.isBackward=false;
		}
		if(e.getKeyCode()==KeyEvent.VK_D)
		{
			PLAYER_ONE.isForward=false;
		}
		if(e.getKeyCode()==KeyEvent.VK_A)
		{
			PLAYER_ONE.isBackward=false;
		}
	}

	public void handleCollision()
	{
		if (intersection(PLAYER_ONE.body, PLAYER_TWO.body)) {
			playerMove(PLAYER_TWO, 10, 0);

			playerMove(PLAYER_ONE, -10, 0);
		}

		if ((PLAYER_ONE.isKicking && intersection(PLAYER_ONE.kick, PLAYER_TWO.body)) || (PLAYER_ONE.isPunching && intersection(PLAYER_ONE.punch, PLAYER_TWO.body))) {
			disp_p1.addTotal_h();
			PLAYER_TWO.hp -= 3 * PLAYER_ONE.strength;
			updateHealthPoints(PLAYER_TWO);

			playerMove(PLAYER_TWO, 80, 0);
			PLAYER_TWO.speed=0;
		}
		if ((PLAYER_TWO.isKicking && intersection(PLAYER_TWO.kick, PLAYER_ONE.body)) || (PLAYER_TWO.isPunching && intersection(PLAYER_TWO.punch, PLAYER_ONE.body))) {
			disp_p2.addTotal_h();
			PLAYER_ONE.hp -= 3 * PLAYER_TWO.strength;
			updateHealthPoints(PLAYER_ONE);

			playerMove(PLAYER_ONE, -80, 0);
			PLAYER_ONE.speed=0;
		}

	}

	public boolean intersection(GObject x1, GObject x2) {

		return x1.getBounds().intersects(x2.getBounds());

	}

	public void gameOver()
	{
		if (PLAYER_ONE.hp <= 0) {
			System.out.println("player 2 wins");	
			PLAYER_ONE.lost = true;
		}
		else if (PLAYER_TWO.hp <= 0) {
			System.out.println("player 1 wins");
			PLAYER_TWO.lost = true;
		}
	}

	public void showStats()
	{
		// add slow motion
		t.stop();
		PLAYER_ONE.remove();
		PLAYER_TWO.remove();
		disp_p1.displayBox();
		disp_p2.displayBox();
		GParagraph winner = new GParagraph(PLAYER_ONE.lost ? "Player 1 Wins!" : "Player 2 Wins!", 220, 140);
		GParagraph loser = new GParagraph(PLAYER_ONE.lost ? "Player 2 Loses!" : "Player 2 Wins!", 670, 140);
		winner.setFont("Arial-24");
		winner.setColor(Color.RED);
		loser.setFont("Arial-24");
		loser.setColor(Color.RED);
		add(winner);
		add(loser);
		game_Over=true;
	}
	public void showUpdatedContents()
	{
		PLAYER_ONE.RefreshArray();
		PLAYER_TWO.RefreshArray();
		for (int i = 0; i < PLAYER_ONE.arrayList.size(); i++) {
			add(PLAYER_ONE.arrayList.get(i));

		}
		for (int i = 0; i < PLAYER_TWO.arrayList.size(); i++) {
			add(PLAYER_TWO.arrayList.get(i));
		}
	}
	
	@Override
	public void showContents() {
		for (int i = 0; i < PLAYER_ONE.arrayList.size(); i++) {
			add(PLAYER_ONE.arrayList.get(i));

		}
		for (int i = 0; i < PLAYER_TWO.arrayList.size(); i++) {
			add(PLAYER_TWO.arrayList.get(i));

		}
		gameBack = level.getLvl_Img();
		if(level.get_Choice()==2)
		{
			gameBack.setImage("portMapMain.png");
		}
		else if (level.get_Choice() == 4)
		{
			gameBack.setImage("maps/BeachMap/beachMapMain.png");
		}
		gameBack.setLocation(0,0);
		gameBack.setSize(1200, 600);
		add(gameBack);
		//add(PLAYER_ONE.getAnimation().getCurImg());
		t.start();
		p1Animation = new Animation(program, PLAYER_ONE);
		p2Animation = new Animation(program, PLAYER_TWO);
		
		
		
		
//		
//		gameBack = level.getLvl_Img();
//		if(level.get_Choice()==4)
//		{
//			gameBack.setImage("maps/BeachMap/beachMapMain.png");
//		}
//		gameBack.setLocation(0,0);
//		gameBack.setSize(1200, 600);
//		add(gameBack);
//		//add(PLAYER_ONE.getAnimation().getCurImg());
//		t.start();
//		p1Animation = new Animation(program, PLAYER_ONE);
//		p2Animation = new Animation(program, PLAYER_TWO);
//		
		
		
		
		
	}


	@Override
	public void hideContents() {
		// TODO Auto-generated method stub

	}
}



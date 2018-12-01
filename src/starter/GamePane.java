package starter;
import java.awt.Color;
import java.awt.event.*;

import java.util.ArrayList;

import javax.swing.*;

import acm.graphics.*;

public class GamePane extends GraphicsPane implements ActionListener {
	

	private Result disp_p1;
	private int rd_time=0;
	private boolean game_Over=false;
	private GRect rect1;
	public static Animation p1Animation;
	public static Animation p2Animation;
	private Result disp_p2;
	private ArrayList<GParagraph> Time;
	private int tot;
	
	public void setrd_time(int ctr)
	{
		rd_time=ctr;
		tot=rd_time;
		for(int i=0;i<=rd_time;i++)
		{
			GParagraph tempTime = new GParagraph(" "+i,560,60);
			tempTime.setFont("Arial-40");
			Time.add(i,tempTime);
		}
	}
	
	public GamePane(MainApplication app)
	{
		super();
		this.program = app;
		rect1=new GRect(565,20,80,60);
		rect1.setFillColor(Color.WHITE);
		rect1.setFilled(true);
		Time= new ArrayList<GParagraph>();
		if(!isrd_timeset)
		{
			rd_time=60;
			tot=rd_time;
			for(int i=0;i<=rd_time;i++)
			{
				GParagraph tempTime = new GParagraph(" "+i,560,60);
				tempTime.setFont("Arial-40");
				Time.add(tempTime);
			}
		}
		t=new Timer(50,this);
		disp_p1=new Result(program,1);
		disp_p2=new Result(program,2);
		
	}


	public Player PLAYER_ONE;
	public Player PLAYER_TWO;
	private Level level; 

	private static MainApplication program;
	public static final int GROUND = 550;
	private Timer t;
	private int numTimes = 0;
	private boolean isrd_timeset=false;
	private boolean rdEnd=false;
	private boolean isDraw=false;

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
		setLevel(l);
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
		if (p1Animation.getCurImg().getX()<0 || p1Animation.getCurImg().getX()>MainApplication.WINDOW_WIDTH-p1Animation.getCurImg().getWidth()) { 
			p.outOfBounds = true;

			moveBody(p,7* -horizontal, vertical);
			return;
		}
		else if (p2Animation.getCurImg().getX()<0 || p2Animation.getCurImg().getX()>MainApplication.WINDOW_WIDTH-p2Animation.getCurImg().getWidth()) { 
			p.outOfBounds = true;

			moveBody(p, 7* -horizontal, vertical);

			return;
		}
		p.outOfBounds = false;
		moveBody(p, horizontal, vertical);

	}
	private static void moveBody(Player p, double horizontal, double vertical) {
		if(p.Id == 1)
		{
			p1Animation.getCurImg().move(horizontal, vertical);
		}
		else
		{
			p2Animation.getCurImg().move(horizontal, vertical);
		}
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

	public void actionPerformed(ActionEvent e)
	{
		numTimes ++;
		if(getLevel().get_Choice()==2)
		{
			if (numTimes % 20 == 0) {
				getLevel().getLvl_Img().setImage("portMapMain.png");
			}
			else if (numTimes % 20 == 10){
				getLevel().getLvl_Img().setImage("portMapMain2.png");
			}
			getLevel().getLvl_Img().setSize(1200, 600);
		}
		else if (getLevel().get_Choice() == 4)
		{
			if (numTimes % 18 == 0) 
			{
				getLevel().getLvl_Img().setImage("maps/BeachMap/beachMap01.png");
			}
			else if (numTimes % 18 == 6)
			{
				getLevel().getLvl_Img().setImage("maps/BeachMap/beachMap02.png");
			}
			else if (numTimes % 18 == 12) 
			{
				getLevel().getLvl_Img().setImage("maps/BeachMap/beachMap03.png");
			}
			getLevel().getLvl_Img().setSize(1200, 600);
		}
		if (intersection(p1Animation.getCurImg(), p2Animation.getCurImg())) {
			p1Animation.getCurImg().move(-3, 0);
			p2Animation.getCurImg().move(3, 0);
		}
		
		if(numTimes%20==0)
		{
			program.add(rect1);
			program.add(Time.get(rd_time));
			if(rd_time<tot)
			{
				program.remove(Time.get(rd_time+1));
			}
			if(rd_time==0)
			{
				t.stop();
				rdEnd=true;
				gameOver();
				showStats();
			}
			if(rd_time>=0)
			{
				rd_time--;
			}
		}
		
		PLAYER_ONE.HandleMovement();
		PLAYER_TWO.HandleMovement();
		p1Animation.handleState();
		p2Animation.handleState();
		handleCollision();
		
	}

	public void resetPlayersState()
	{
		PLAYER_ONE.isBackward = false;
		PLAYER_ONE.isForward = false;
		PLAYER_ONE.isJumpMoving = false;
		PLAYER_ONE.isJumpVert = false;
		PLAYER_ONE.isDucking = false;
		PLAYER_ONE.isKicking = false;
		PLAYER_ONE.isPunching = false;
		PLAYER_TWO.isBackward = false;
		PLAYER_TWO.isForward = false;
		PLAYER_TWO.isJumpMoving = false;
		PLAYER_TWO.isJumpVert = false;
		PLAYER_TWO.isDucking = false;
		PLAYER_TWO.isKicking = false;
		PLAYER_TWO.isPunching = false;
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
			if(!PLAYER_TWO.isJumpMoving  && !PLAYER_TWO.isJumpVert  && !PLAYER_TWO.isDucking)
			{
				PLAYER_TWO.jump=-10;
				if(PLAYER_TWO.isForward || PLAYER_TWO.isBackward)
					PLAYER_TWO.isJumpMoving=true;
				else
					PLAYER_TWO.isJumpVert=true;
			}
		}
		if(e.getKeyCode()==KeyEvent.VK_W)
		{
			if(!PLAYER_ONE.isJumpMoving  &&!PLAYER_ONE.isJumpVert  && !PLAYER_ONE.isDucking)
			{
				PLAYER_ONE.jump=-10;
				if(PLAYER_ONE.isForward || PLAYER_ONE.isBackward)
				{
					PLAYER_ONE.isJumpMoving=true;
				}
				else
				{
					PLAYER_ONE.isJumpVert=true;
				}
			}
		}
		if(e.getKeyCode()==KeyEvent.VK_DOWN)
		{	
			if(!PLAYER_TWO.isDucking  && !PLAYER_TWO.isJumpVert)
			{
				PLAYER_TWO.duck=7;
				PLAYER_TWO.isDucking=true;
			}
		}
		if(e.getKeyCode()==KeyEvent.VK_S)
		{	
			if(!PLAYER_ONE.isDucking && !PLAYER_ONE.isJumpVert)
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
				PLAYER_ONE.isPunching=true;
			}
		}
		if(e.getKeyCode()==KeyEvent.VK_SPACE)
		{
			if(!PLAYER_TWO.isPunching && !PLAYER_TWO.isKicking)
			{
				if(game_Over)
				{
					sound(true);
					program.switchToMenu();
				}
				else
				{
					disp_p2.addTotal_p();
					PLAYER_TWO.hittime=0;
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
		//		 
	}

	public void handleCollision()
	{
		if ((PLAYER_ONE.isKicking && intersection(p1Animation.getCurImg(), p2Animation.getCurImg())) || (PLAYER_ONE.isPunching && intersection(p1Animation.getCurImg(), p2Animation.getCurImg()))) {
			disp_p1.addTotal_h();
			PLAYER_TWO.hp -= 3 * PLAYER_ONE.strength;
			updateHealthPoints(PLAYER_TWO);

			playerMove(PLAYER_TWO, 80, 0);
			PLAYER_TWO.speed=0;
		}
		if ((PLAYER_TWO.isKicking && intersection(p1Animation.getCurImg(), p2Animation.getCurImg())) || (PLAYER_TWO.isPunching && intersection(p1Animation.getCurImg(), p2Animation.getCurImg()))) {
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
		if(rdEnd)
		{
			if(PLAYER_ONE.hp<PLAYER_TWO.hp)
			{
				PLAYER_ONE.lost = true;
			}
			else if(PLAYER_TWO.hp<PLAYER_ONE.hp)
			{
				PLAYER_TWO.lost = true;
			}
			else
			{
				isDraw=true;
			}
		}
	}

	public void showStats()
	{
		// add slow motion
		t.stop();
		disp_p1.displayBox();
		disp_p2.displayBox();
		if(isDraw)
		{
			GParagraph winner = new GParagraph("Round Draw", 220, 140);
			winner.setFont("Arial-24");
			winner.setColor(Color.RED);
			GParagraph loser = new GParagraph("Round Draw", 670, 140);
			loser.setFont("Arial-24");
			loser.setColor(Color.RED);
			add(winner);
			add(loser);
		}
		else
		{
		GParagraph winner = new GParagraph(PLAYER_ONE.lost ? "Player 1 Loses!" : "Player 1 Wins!", 220, 140);
		GParagraph loser = new GParagraph(PLAYER_ONE.lost ? "Player 2 Wins!" : "Player 2 Loses!", 670, 140);
		winner.setFont("Arial-24");
		winner.setColor(Color.RED);
		loser.setFont("Arial-24");
		loser.setColor(Color.RED);
		add(winner);
		add(loser);
		}
		GParagraph next = new GParagraph("Press Space Bar To Go Back To Menu", 350, 440);
		next.setFont("Arial-24");
		next.setColor(Color.BLACK);
		add(next);
		game_Over=true;
	}


	@Override
	public void showContents() {
		getLevel().getLvl_Img().setLocation(0,0);
		getLevel().getLvl_Img().setSize(1200, 600);
		add(getLevel().getLvl_Img());
		p1Animation = new Animation(program, PLAYER_ONE);
		p2Animation = new Animation(program, PLAYER_TWO);
		add(PLAYER_ONE.hpoutline);
		add(PLAYER_TWO.hpoutline);
		add(PLAYER_ONE.hpbar);
		add(PLAYER_TWO.hpbar);
		sound(false);
		t.start();
	
	}


	@Override
	public void hideContents() {
		remove(p1Animation.getCurImg());
		remove(p2Animation.getCurImg());
		remove(PLAYER_ONE.hpoutline);
		remove(PLAYER_TWO.hpoutline);
		remove(PLAYER_ONE.hpbar);
		remove(PLAYER_TWO.hpbar);
		remove(getLevel().getLvl_Img());
		remove(rect1);
		remove(Time.get(rd_time+1));
	}

	public void rd_timestate(boolean b) {
		isrd_timeset=b;
		
	}

	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}
	public void resetTime()
	{
		rd_time=tot;
	}
	public void sound(boolean isPlaying)
	{
		program.levelSound(level.get_Choice(), isPlaying);
	}
}




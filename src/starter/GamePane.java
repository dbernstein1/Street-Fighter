package starter;
import java.awt.Color;
import java.awt.event.*;

import java.util.ArrayList;

import javax.swing.*;

import acm.graphics.*;

public class GamePane extends GraphicsPane implements ActionListener {
	private Background bgPort;
	private Background bgPort2;

	public GamePane(MainApplication app)
	{
		super();
		GamePane.program = app;
		bgPort = program.backgroundPort;
		bgPort2 = program.backgroundPort2;
		t=new Timer(50,this);
	}
	
	
	private Player PLAYER_ONE;
	private Player PLAYER_TWO;

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
		program.add(bgPort2);
	}
	
	public void remove(Background bgPort) {
		program.remove(bgPort);
		program.remove(bgPort2);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		
		
		numTimes ++;
		if (numTimes % 20 == 0) {
			add(bgPort.getImage());
			remove(bgPort2);
		}
		else if (numTimes % 20 == 10){
			add(bgPort2.getImage());
			remove(bgPort);
		}
		showContents();
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
				PLAYER_ONE.hittime=0;
				remove(PLAYER_ONE.arm);
				PLAYER_ONE.isPunching=true;
			}
		}
		if(e.getKeyCode()==KeyEvent.VK_SPACE)
		{
			if(!PLAYER_TWO.isPunching && !PLAYER_TWO.isKicking)
			{
				PLAYER_TWO.hittime=0;
				remove(PLAYER_TWO.arm);
				PLAYER_TWO.isPunching=true;
			}
		}
		if(e.getKeyCode()==KeyEvent.VK_CONTROL)
		{
			if(!PLAYER_TWO.isKicking && !PLAYER_TWO.isPunching)
			{
				PLAYER_TWO.hittime=0;
				PLAYER_TWO.isKicking=true;
			}
		}
		if(e.getKeyCode()==KeyEvent.VK_K)
		{
			if(!PLAYER_ONE.isKicking && !PLAYER_ONE.isPunching)
			{
				PLAYER_ONE.hittime=0;
				PLAYER_ONE.isKicking=true;
			}
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
			
			PLAYER_TWO.hp -= 3 * PLAYER_ONE.strength;
			updateHealthPoints(PLAYER_TWO);
			
			playerMove(PLAYER_TWO, 40, 0);
			
		}
		if ((PLAYER_TWO.isKicking && intersection(PLAYER_TWO.kick, PLAYER_ONE.body)) || (PLAYER_TWO.isPunching && intersection(PLAYER_TWO.punch, PLAYER_ONE.body))) {
			
			PLAYER_ONE.hp -= 3 * PLAYER_TWO.strength;
			updateHealthPoints(PLAYER_ONE);
			
			playerMove(PLAYER_ONE, -40, 0);
		}
	
	}
	
	public boolean intersection(GObject x1, GObject x2) {
		
		return x1.getBounds().intersects(x2.getBounds());
		
	}

	public void gameOver()
	{
		if (PLAYER_ONE.hp <= 0) {
			System.out.println("player 2 wins");
			// add slow motion
			
			PLAYER_ONE.remove();
			PLAYER_TWO.remove();

			GImage img = new GImage("robot head.jpg", MainApplication.WINDOW_WIDTH / 2, MainApplication.WINDOW_HEIGHT / 2);
			GParagraph para = new GParagraph("Player 1 Wins!", 150, 300);
			para.setFont("Arial-24");
			add(img);
			add(para);

			t.stop();
			
		//	MainApplication.switchToMenu();
			
		//	t.start();
		}
		else if (PLAYER_TWO.hp <= 0) {
			System.out.println("player 1 wins");
			// add slow motion
			
			PLAYER_ONE.remove();
			PLAYER_TWO.remove();
			
			GImage img = new GImage("robot head.jpg", MainApplication.WINDOW_WIDTH / 2, MainApplication.WINDOW_HEIGHT / 2);
			GParagraph para = new GParagraph("Player 1 Wins!", 150, 300);
			para.setFont("Arial-24");
			add(img);
			add(para);
			
			t.stop();
			
	//		GraphicsApplication.switchToScreen(MainApplication.menu);
			
			//t.start();
		}
	}

	@Override
	public void showContents() {

//		add(background.getImage());
		PLAYER_ONE.RefreshArray();
		PLAYER_TWO.RefreshArray();
		
		for (int i = 0; i < PLAYER_ONE.arrayList.size(); i++) {
			add(PLAYER_ONE.arrayList.get(i));
			
		}
		for (int i = 0; i < PLAYER_TWO.arrayList.size(); i++) {
			add(PLAYER_TWO.arrayList.get(i));
			
		}
		PLAYER_ONE.HandleMovement();
		PLAYER_TWO.HandleMovement();
		t.start();
		return;
	}


	@Override
	public void hideContents() {
		// TODO Auto-generated method stub

	}
}

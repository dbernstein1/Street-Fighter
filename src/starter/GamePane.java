package starter;
import java.awt.Color;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;


import acm.graphics.*;

public class GamePane extends GraphicsPane implements ActionListener {

	public GamePane(MainApplication app)
	{
		super();
		GamePane.program = app;
		width = app.getWidth();
		background = program.background;
		t=new Timer(50,this);
	}
	
	
	private Player PLAYER_ONE = new Player(1, "Playerone", 80, 2, 25); // 80 HP, 200% attack multiplier, 25 stamina 
	private Player PLAYER_TWO = new Player(2, "player_Two", 150, .8, 15);  // 150 hp, 20% attack reduction, 15 stamina
	
	private static MainApplication program;
	public static final int GROUND = 550;
	public static int width = 1200;
	private Timer t;
	private Background background;
	
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
	
	public void actionPerformed(ActionEvent e)
	{
		PLAYER_ONE.HandleMovement();
		PLAYER_TWO.HandleMovement();
		
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
			t.stop();
		}
		else if (PLAYER_TWO.hp <= 0) {
			System.out.println("player 1 wins");
			// add slow motion
			t.stop();
			
		}
	}

	@Override
	public void showContents() {

		add(background.getImage());
		
		PLAYER_ONE.RefreshArray();
		PLAYER_TWO.RefreshArray();
		
		for (int i = 0; i < PLAYER_ONE.arrayList.size(); i++) {
			add(PLAYER_ONE.arrayList.get(i));
			
		}
		for (int i = 0; i < PLAYER_TWO.arrayList.size(); i++) {
			add(PLAYER_TWO.arrayList.get(i));
			
		}
	
		
		
		t.start();
		return;
	}


	@Override
	public void hideContents() {
		// TODO Auto-generated method stub

	}
}

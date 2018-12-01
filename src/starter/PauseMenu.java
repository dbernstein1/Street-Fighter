package starter;

import java.awt.Color;
import java.awt.event.MouseEvent;
import acm.graphics.GObject;
import acm.graphics.GRect;

public class PauseMenu extends GraphicsPane{
	private MainApplication program;
	private GRect rect;
	private GButton resume;
	private GButton menu;
	private GParagraph pause;
	
	PauseMenu(MainApplication app)
	{
		this.program=app;
		rect=new GRect(400,100,300,400);
		rect.setColor(Color.BLACK);
		rect.setFilled(true);
		resume=new GButton("RESUME",480,190,150,50);
		resume.setFillColor(Color.WHITE);
		menu=new GButton("MENU",480,310,150,50);
		menu.setFillColor(Color.WHITE);
		pause= new GParagraph("PAUSE MENU",480,140);
		pause.setFont("Arial-24");
		pause.setColor(Color.WHITE);
	}

	@Override
	public void showContents() {
		// TODO Auto-generated method stub
		program.add(program.getGamePane().getLevel().getLvl_Img());
		program.add(program.getGamePane().p1Animation.getCurImg());
		program.add(program.getGamePane().p2Animation.getCurImg());
		program.add(program.getGamePane().PLAYER_ONE.hpoutline);
		program.add(program.getGamePane().PLAYER_TWO.hpoutline);
		program.add(program.getGamePane().PLAYER_ONE.hpbar);
		program.add(program.getGamePane().PLAYER_TWO.hpbar);
		program.add(rect);
		program.add(resume);
		program.add(menu);
		program.add(pause);
		program.getGamePane().resetPlayersState();
	}

	@Override
	public void hideContents() {
		// TODO Auto-generated method stub
		program.remove(rect);
		program.remove(resume);
		program.remove(menu);
		program.remove(pause);
		
		program.remove(program.getGamePane().p1Animation.getCurImg());
		program.remove(program.getGamePane().p2Animation.getCurImg());
		program.remove(program.getGamePane().PLAYER_ONE.hpoutline);
		program.remove(program.getGamePane().PLAYER_TWO.hpoutline);
		program.remove(program.getGamePane().PLAYER_ONE.hpbar);
		program.remove(program.getGamePane().PLAYER_TWO.hpbar);
		program.remove(program.getGamePane().getLevel().getLvl_Img());
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		GObject obj = program.getElementAt(e.getX(), e.getY());
		if (obj == resume) {
			program.remove(rect);
			program.remove(resume);
			program.remove(menu);	
			program.switchToGame();
		}
		if(obj==menu)
		{
			program.getGamePane().resetTime();
			program.switchToMenu();
		}
	}

}

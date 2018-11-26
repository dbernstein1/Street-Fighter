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
	private GamePane game;
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
		program.add(rect);
		program.add(resume);
		program.add(menu);
		program.add(pause);
	}

	@Override
	public void hideContents() {
		// TODO Auto-generated method stub
		program.remove(rect);
		program.remove(resume);
		program.remove(menu);
		program.remove(pause);
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
			program.switchToMenu();
		}
	}

}

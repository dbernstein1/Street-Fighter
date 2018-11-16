package starter;
import java.awt.Color;
import java.awt.event.MouseEvent;

import acm.graphics.GImage;
import acm.graphics.GObject;
//level select
public class LevelSelectionPane extends GraphicsPane {
	private MainApplication program;
	private static final String IMAGES[]= {"example_back.jpg","menu_back_arrow.png","highlight.png"};
	private GImage arena1,backArrow,highlight;
	private GButton fight;
	private int choice=0;
	public LevelSelectionPane(MainApplication app )
	{
		this.program=app;
		arena1=new GImage(IMAGES[0],100,100);
		arena1.setSize(300, 200);
		backArrow=new GImage(IMAGES[1],0,0);
		backArrow.setSize(80,80);
		fight=new GButton("Fight",435,265,200,100);
		fight.setFillColor(Color.RED);
	}
	
	@Override
	public void showContents() {
		program.add(arena1);
		program.add(backArrow);
		program.add(fight);
	}

	@Override
	public void hideContents() {
		program.remove(arena1);
		program.remove(backArrow);
		program.remove(fight);
	}
	@Override
	public void mousePressed(MouseEvent e) {
		GObject obj = program.getElementAt(e.getX(), e.getY());
		if (obj == backArrow) {
			choice=0;
			program.remove(highlight);
			program.switchToMenu();
		}
		if(obj==arena1)
		{
			choice=1;
			highlight=new GImage(IMAGES[2],80,80);
			highlight.setSize(340 ,240);
			program.add(highlight);
		}
		if(obj==fight)
		{
			program.remove(highlight);
			checkChoice();
		}
	}
	public void checkChoice()
	{
		switch(choice)
		{
		case 1:
			program.switchToCharacterPane();
		case 2:
		}
	}
}

package starter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import acm.graphics.GImage;
import acm.graphics.GLabel;
import acm.graphics.GObject;
import acm.graphics.GOval;
import acm.graphics.GRect;
import java.awt.Color;

public class CharacterPane extends GraphicsPane {
	private MainApplication program; // you will use program to get access to
		// all of the GraphicsProgram calls
	private static final String IMAGES[]= {"example_back.jpg","menu_back_arrow.png","highlight.png", "square.png"
			,"characterselection.jpg", "menu_back_arrow.png", "rightarrow.png"};
	private GButton para;
	private GRect r1,r2,r3,r4;
	private GImage player1Img, player2Img, player3Img, player4Img,
	highlight,highlight2, img, continueArrow, backArrow;
	boolean p1selected = false, p2selected = false;
	
	public CharacterPane(MainApplication app) {
		this.program = app;
		para = new GButton("Select\nyour character!", 200, 50, 140, 50, Color.WHITE);
		//para.setFont("Arial-24");
		continueArrow = new GImage(IMAGES[6], program.getWidth() - 100 ,program.getHeight() - 100);
		continueArrow.setSize(80,80);
		backArrow = new GImage(IMAGES[5], 100 ,program.getHeight() - 100);
		backArrow.setSize(80,80);
		img = new GImage(IMAGES[4], 0,0);
		img.setSize(program.getWidth(), program.getHeight());
		Stats fredStats = new Stats(20,20,20,100);
		//Player fred = new Player(PlayerName.FRED,fredStats);
		player1Img = new GImage("sprites/Rick/Rick_Selection.jpg", program.getWidth() - 400,program.getHeight()-  program.getHeight()/2.5);
		player1Img.setSize(200,200);
		player2Img = new GImage("sprites/Trident/Trident_Selection.png", program.getWidth() - 600,program.getHeight() -  program.getHeight()/2.5);
		player2Img.setSize(200,200);
		player3Img = new GImage("sprites/Cody/cody_selection.png", program.getWidth() - 800,program.getHeight() -  program.getHeight()/2.5);
		player3Img.setSize(200,200);
		player4Img = new GImage("sprites/Guy/Guy_Selection.png", program.getWidth() - 1000,program.getHeight() -  program.getHeight()/2.5);
		player4Img.setSize(200,200);
		r1 = new GRect(program.getWidth() - 400,program.getHeight() -  program.getHeight()/2.5, 200, 200);
//		Color p1Color = new Color(23, 244, 12);
//		r1.setFillColor(p1Color);
		r2 = new GRect(program.getWidth() - 600,program.getHeight() -  program.getHeight()/2.5, 200, 200);
	    r3 = new GRect(program.getWidth() - 800,program.getHeight() -  program.getHeight()/2.5, 200, 200);
		r4 = new GRect(program.getWidth() - 1000,program.getHeight() -  program.getHeight()/2.5, 200, 200);
	
	}
	
	public void add(GObject something) {
		program.add(something);
	}
	
	
	@Override
	public void showContents() {
		program.add(img);
		program.add(para);
		program.add(player1Img);
		program.add(player2Img);
		program.add(player3Img);
		program.add(player4Img);
		program.add(r2);
		program.add(r3);
		program.add(r4);
		program.add(r1);
		program.add(continueArrow);
		program.add(backArrow);
	}
	public void addHighlight(int image, int x)
	{
		if(!p1selected)
		{
			highlight=new GImage(IMAGES[image],x - 10,program.getHeight()-  program.getHeight()/2.5 - 10);
			highlight.setSize(220 ,220);
			program.add(highlight);
		}
		else if(!p2selected)
		{
			highlight2=new GImage(IMAGES[image],x - 10,program.getHeight()-  program.getHeight()/2.5 - 10);
			highlight2.setSize(220 ,220);
			program.add(highlight2);
		}
	}
	@Override
	public void hideContents() {
		program.remove(img);
		program.remove(para);
		program.remove(r1);
		program.remove(r2);
		program.remove(r3);
		program.remove(r4);
		program.remove(player1Img);
		program.remove(player2Img);
		program.remove(player3Img);
		program.remove(player4Img);
		if(p1selected)
			program.remove(highlight);
		if(p2selected)
			program.remove(highlight2);
		program.remove(continueArrow);
		program.remove(backArrow);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		GObject obj = program.getElementAt(e.getX(), e.getY());
		System.out.println(obj);
		if(obj == r1) {
			if(p1selected == false)
			{
				addHighlight(2, 800);
				p1selected = true;
			}
			else
			{
				addHighlight(3, 800);
				p2selected = true;
			}
		}
		else if(obj==r2)
		{
			if(p1selected == false)
			{
				addHighlight(2, 600);
				p1selected = true;
			}
			else
			{
				addHighlight(3, 600);
				p2selected = true;
			}
		}
		else if(obj== r3)
		{
			if(p1selected == false)
			{
				addHighlight(2, 400);
				p1selected = true;
			}
			else
			{
				addHighlight(3, 400);
				p2selected = true;
			}
		}
		else if(obj== r4)
		{
			if(p1selected == false)
			{
				addHighlight(2, 200);
				p1selected = true;
			}
			else
			{
				addHighlight(3, 200);
				p2selected = true;
			}
		}
		else if(obj==highlight)
		{
			p1selected = false;
			program.remove(highlight);
		}
		else if(obj==highlight2)
		{
			p2selected = false;
			program.remove(highlight2);
		}
		else if(obj==backArrow)
		{
			program.switchToLevel();
			p1selected = false;
			p2selected = false;
		}
		else if(obj==continueArrow)
		{
			if(p1selected && p2selected)
			{
				program.getGamePane().setPLAYER_ONE(getWhichPlayer(highlight));
				program.getGamePane().setPLAYER_TWO(getWhichPlayer(highlight2));
				program.switchToGame();
			}
		}
	}
	
	Player getWhichPlayer(GImage img)
	{
		boolean player1 = true;
		if (img.getX() == highlight.getX())
			player1 = true;
		else if (img.getX() == highlight2.getX())
			player1 = false;
		
		
		if(img.getX() == 190)
		{
			return new Player(player1 ? 1 : 2, 1, "Guy", 2, 2, 25);
		}
		else if(img.getX() == 390)
		{
			return new Player(player1 ? 1 : 2, 2, "Cody", 2, .8, 15);
		}
		else if(img.getX() == 590)
		{
			return new Player(player1 ? 1 : 2, 3,"Trident", 80, 2, 25);
		}
		return new Player(player1 ? 1 : 2, 4,"Rick", 150, .8, 15);
	}
	
}


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

	private GImage img;
	private GButton para;
	private GRect r1;
	private GRect r2;
	private GRect r3;
	private GRect r4;
	private GImage player1Img;
	private GImage player2Img;
	private GImage player3Img;
	private GImage player4Img;
	private ArrayList<GObject> ovals;
	public CharacterPane(MainApplication app) {
		this.program = app;
		para = new GButton("Select\nyour character!", 200, 50, 140, 50, Color.WHITE);
		//para.setFont("Arial-24");
		img = new GImage("characterselection.jpg", 0,0);
		img.setSize(program.getWidth(), program.getHeight());
		Stats fredStats = new Stats(20,20,20,100);
		//Player fred = new Player(PlayerName.FRED,fredStats);
		player1Img = new GImage("fred.jpg", program.getWidth() - 400,program.getHeight()-  program.getHeight()/2.5);
		player1Img.setSize(200,200);
		player2Img = new GImage("fred.jpg", program.getWidth() - 600,program.getHeight() -  program.getHeight()/2.5);
		player2Img.setSize(200,200);
		player3Img = new GImage("fred.jpg", program.getWidth() - 800,program.getHeight() -  program.getHeight()/2.5);
		player3Img.setSize(200,200);
		player4Img = new GImage("fred.jpg", program.getWidth() - 1000,program.getHeight() -  program.getHeight()/2.5);
		player4Img.setSize(200,200);
		GRect r1 = new GRect(program.getWidth() - 400,program.getHeight() -  program.getHeight()/2.5, 200, 200);
		Color p1Color = new Color(23, 244, 12);
		r1.setFillColor(p1Color);
		GRect r2 = new GRect(program.getWidth() - 600,program.getHeight() -  program.getHeight()/2.5, 200, 200);
		GRect r3 = new GRect(program.getWidth() - 800,program.getHeight() -  program.getHeight()/2.5, 200, 200);
		GRect r4 = new GRect(program.getWidth() - 1000,program.getHeight() -  program.getHeight()/2.5, 200, 200);
		
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
	}

	@Override
	public void hideContents() {
		program.remove(img);
		program.remove(para);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		GObject obj = program.getElementAt(e.getX(), e.getY());
		if(obj == r1) {
			//program.player = ;
		}
		else if(obj==r2)
		{
			//program.player = ;
		}
	}
	
	
}


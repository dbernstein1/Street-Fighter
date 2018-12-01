package starter;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import acm.graphics.GImage;
import acm.graphics.GObject;
import acm.graphics.GOval;
import acm.graphics.GRect;

public class SoundOptions extends GraphicsPane{
	private MainApplication program;
	private Background background;

	private int cnt=3;
	private GImage backArrow;
	private GButton play;
	private GButton pause;
	private GParagraph s_Instruction;

	public SoundOptions(MainApplication app) {
		super();
		this.program=app;
		background=program.menu_Bg;
		backArrow=new GImage("menu_back_arrow.png",0,0);
		backArrow.setSize(80,80);
		play= new GButton("Play",370, 265, 100, 50);
		pause= new GButton("Pause",710, 265, 100, 50);
		s_Instruction = new GParagraph("Click on Pause to Stop Music\nClick on Play To Start Music",400,120);
		s_Instruction.setFont("Arial-24");
		s_Instruction.setColor(Color.WHITE);
	}

	@Override
	public void showContents() {
		program.add(background.getImage());
		program.add(backArrow);
		program.add(play);
		program.add(pause);
		program.add(s_Instruction);
		


	}

	@Override
	public void hideContents() {
		program.remove(background.getImage());
		program.remove(backArrow);
		program.remove(play);
		program.remove(pause);
		program.remove(s_Instruction);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		GObject obj = program.getElementAt(e.getX(), e.getY());
		if(obj==play)
		{
			program.playSound();
		}
		if(obj==pause)
		{
			program.stopSound();
		}
		if(obj==backArrow)
		{
			program.switchToOptions();
		}
	}

	public void add(GObject something)
	{
		program.add(something);
	}
	
	
	
	
}

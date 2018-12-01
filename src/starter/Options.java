package starter;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import acm.graphics.GImage;
import acm.graphics.GObject;
import acm.graphics.GOval;

public class Options extends GraphicsPane {
	private MainApplication program; // you will use program to get access to
									// all of the GraphicsProgram calls
	private MenuPane menu;
	
	
	private static int SIZE1=410;// x - axis of the middle oval
	private static int SIZE2=250;// y - axis of the middle circle1
	private static int SIZE3=750;
	private static int RADIUS1=20;// height of smallest oval
	private static int RADIUS2=200;// length of smallest oval


	private GButton Time,Sound,Timer1,Timer2;
	private GParagraph t_Instruction,s_Instruction;
	private ArrayList<GObject> ovals;
	private GImage backArrow;
	private Background background;
	public Options(MainApplication app) {
		super();
		menu= new MenuPane(app);
		program = app;
		Time = new GButton("Time Options",365, 235, 100, 50);
		Sound= new GButton("Sound Options", 710, 235, 100, 50);
		backArrow=new GImage("menu_back_arrow.png",0,0);
		backArrow.setSize(80,80);
		s_Instruction = new GParagraph("Time Options:\nLets you choose\ntime one round lasts",100,250);
		s_Instruction.setFont("Arial-24");
		s_Instruction.setColor(Color.WHITE);
		t_Instruction = new GParagraph("Sound Options:\nLets you \nPause And Play\nBackground Music",900,250);
		t_Instruction.setFont("Arial-24");
		t_Instruction.setColor(Color.WHITE);
		ovals = new ArrayList<GObject>();	
		for(int i=0;i<13;i++)  //Used to add 1st circle
		{
			GOval oval=menu.makeCircle1();
			add(oval);
			ovals.add(oval);
		}
		for(int i=0;i<13;i++)  //Used to add 2nd circle
		{
			GOval oval=menu.makeCircle2();
			add(oval);
			ovals.add(oval);
		}
		background=program.menu_Bg;
	}

	private void add(GOval something) {
		program.add(something);

	}

	public void add(GImage something)
	{
		program.add(something);
	}

	@Override
	public void showContents() {
		program.add(background.getImage());
		program.add(backArrow);
		for(int i=25;i>=0;i--)
		{
			program.add(ovals.get(i));
		}
		program.add(Time);
		program.add(Sound);
		program.add(s_Instruction);
		program.add(t_Instruction);
		return;
	}

	@Override
	public void hideContents() {
		for(int i=25;i>=0;i--)
		{
			program.remove(ovals.get(i));
		}
		program.remove(Time);
		program.remove(Sound);
		program.remove(backArrow);
		program.remove(background.getImage());
		program.remove(s_Instruction);
		program.remove(t_Instruction);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		GObject obj = program.getElementAt(e.getX(), e.getY());
		if (obj == Time) {
			program.switchToTimeOptions();	
		}
		if(obj==Sound)
		{
			program.switchToSoundOptions();
		}
		if(obj==backArrow)
		{
			program.switchToMenu();
		}
	}	
	
}

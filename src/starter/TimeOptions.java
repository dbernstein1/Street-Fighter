package starter;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import acm.graphics.GImage;
import acm.graphics.GObject;
import acm.graphics.GOval;

public class TimeOptions extends GraphicsPane{
	private MainApplication program;
	private MenuPane menu;
	private Background background;
	
	private ArrayList<GObject> ovals;
	private int ctr=0;
	private GButton Timer1;
	private GButton Timer2;
	private GImage backArrow;
	private GParagraph Instruction;
	public TimeOptions(MainApplication app)
	{
		super();
		menu= new MenuPane(app);
		this.program=app;
		background=program.menu_Bg;
		Timer1= new GButton("60",365, 235, 100, 50);
		Timer1.setFillColor(Color.RED);
		Timer2= new GButton("120",710, 235, 100, 50);
		backArrow=new GImage("menu_back_arrow.png",0,0);
		backArrow.setSize(80,80);
		Instruction = new GParagraph("Choose the time for rounds in seconds\n         Click on the time you want",380,150);
		Instruction.setFont("Arial-24");
		Instruction.setColor(Color.WHITE);
		ovals= new ArrayList<GObject>();
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
	}
	

	@Override
	public void showContents() {
		program.add(background.getImage());
		for(int i=25;i>=0;i--)
		{
			program.add(ovals.get(i));
		}
		program.add(Timer1);
		program.add(Timer2);
		program.add(Instruction);
		program.add(backArrow);
	}

	@Override
	public void hideContents() {
		program.remove(background.getImage());
		for(int i=25;i>=0;i--)
		{
			program.remove(ovals.get(i));
		}
		program.remove(Timer1);
		program.remove(Timer2);
		program.remove(Instruction);
		program.remove(backArrow);
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		GObject obj = program.getElementAt(e.getX(), e.getY());
		if(obj==Timer1)
		{
			Timer2.setFillColor(Color.WHITE);
			Timer1.setFillColor(Color.RED);
			program.add(Timer1);
			program.add(Timer2);
		}
		if(obj==Timer2)
		{
			program.remove(Timer1);
			Timer1.setFillColor(Color.WHITE);
			Timer2.setFillColor(Color.RED);
			program.add(Timer1);
			program.add(Timer2);
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

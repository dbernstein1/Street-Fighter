package starter;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import acm.graphics.GImage;
import acm.graphics.GObject;
import acm.graphics.GOval;

public class MenuPane extends GraphicsPane {
	private MainApplication program; // you will use program to get access to
									// all of the GraphicsProgram calls
	private static int SIZE1=410;// x - axis of the middle oval
	private static int SIZE2=250;// y - axis of the middle circle1
	private static int SIZE3=750;
	private static int RADIUS1=20;// height of smallest oval
	private static int RADIUS2=200;// length of smallest oval
	private int ctr=0;

	private GButton rect1,rect2;
	private ArrayList<GObject> ovals;
	private Background background;
	public MenuPane(MainApplication app) {
		super();
		program = app;
		rect1 = new GButton("Fight",365, 235, 100, 50);
		rect1.setFillColor(Color.WHITE);
		rect2 = new GButton("Options", 710, 235, 100, 50);
		rect2.setFillColor(Color.WHITE);
		ovals = new ArrayList<GObject>();	
		for(int i=0;i<13;i++)  //Used to add 1st circle
		{
			GOval oval=makeCircle1();
			add(oval);
			ovals.add(oval);
		}
		for(int i=0;i<13;i++)  //Used to add 2nd circle
		{
			GOval oval=makeCircle2();
			add(oval);
			ovals.add(oval);
		}
		/*for(int i=0;i<13;i++)  //Used to add oval
		{
			GOval oval=makeOval();
			add(oval);
			ovals.add(oval);
		}*/
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
		add(background.getImage());
		for(int i=25;i>=0;i--)
		{
			program.add(ovals.get(i));
		}
		program.add(rect1);
		program.add(rect2);
		return;
	}

	@Override
	public void hideContents() {
		for(int i=25;i>=0;i--)
		{
			program.remove(ovals.get(i));
		}
		program.remove(rect1);
		program.remove(rect2);
		program.remove(background.getImage());
	}

	@Override
	public void mousePressed(MouseEvent e) {
		GObject obj = program.getElementAt(e.getX(), e.getY());
		if (obj == rect1) {
			program.switchToGame();
		}
		if(obj==rect2)
		{
			program.switchToSome();
		}
	}
	public GOval makeCircle1() {
		GOval temp = new GOval(SIZE1-(ctr*5), SIZE2-(ctr*5), RADIUS1+(ctr*10), RADIUS1+(ctr*10));
		if(ctr%2==0)
		{
			temp.setColor(Color.BLACK);
		}
		else 
		{
			temp.setColor(Color.WHITE);	
		}
		temp.setFilled(true);
		ctr++;
		checkCounter();
		return temp;
	}
	public GOval makeCircle2() {
		GOval temp = new GOval(SIZE3-(ctr*5), SIZE2-(ctr*5), RADIUS1+(ctr*10), RADIUS1+(ctr*10));
		if(ctr%2==0)
		{
			temp.setColor(Color.BLACK);
		}
		else 
		{
			temp.setColor(Color.WHITE);	
		}
		temp.setFilled(true);
		ctr++;
		checkCounter();
		return temp;
	}
	public GOval makeOval() {
		GOval temp = new GOval(SIZE1-(ctr*5), SIZE2-(ctr*5), RADIUS2+(ctr*10), RADIUS1+(ctr*10));
		if(ctr%2==0)
		{
			temp.setColor(Color.BLACK);
		}
		else 
		{
			temp.setColor(Color.WHITE);	
		}
		temp.setFilled(true);
		ctr++;
		checkCounter();
		return temp;
	}
	public void checkCounter()
	{
		if(ctr==13)
		{
			ctr=0;
		}
	}
}

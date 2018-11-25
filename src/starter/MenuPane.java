package starter;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import acm.graphics.GImage;
import acm.graphics.GObject;
import acm.graphics.GOval;
import acm.graphics.GRect;

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
	private GParagraph Instruction,quit_Prompt;
	private GRect rect3;
	private GButton yes_button;
	private GButton no_button;
	public MenuPane(MainApplication app) {
		super();
		program = app;
		rect1 = new GButton("Fight",365, 235, 100, 50);
		rect1.setFillColor(Color.WHITE);
		rect2 = new GButton("Options", 710, 235, 100, 50);
		rect2.setFillColor(Color.WHITE);
		rect3=new GRect(330,200,500,200);
		rect3.setColor(Color.WHITE);
		rect3.setFilled(true);
		yes_button = new GButton("YES",365, 320, 100, 50);
		yes_button.setFillColor(Color.RED);
		no_button = new GButton("NO",690, 320, 100, 50);
		no_button.setFillColor(Color.RED);
		quit_Prompt = new GParagraph("Do You Really Want to Quit\n  This Awesome Game?",450,250);
		quit_Prompt.setFont("Arial-24");
		quit_Prompt.setColor(Color.RED);
		Instruction = new GParagraph("Press Escape To Quit",470,600);
		Instruction.setFont("Arial-24");
		Instruction.setColor(Color.WHITE);
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
		
		program.add(Instruction);
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
			program.switchToLevel();
		}
		if(obj==rect2)
		{
			program.switchToOptions();
		}
		if(obj==no_button)
		{
			program.remove(no_button);
			program.remove(yes_button);
			program.remove(quit_Prompt);
			program.remove(rect3);
		}
		if(obj==yes_button)
		{
			System.exit(0);
		}
	}
	

	@Override
	public void keyPressed(KeyEvent e)
	{
		if(e.getKeyCode()==KeyEvent.VK_ESCAPE)
		{
			program.add(rect3);
			program.add(quit_Prompt);
			program.add(yes_button);
			program.add(no_button);
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

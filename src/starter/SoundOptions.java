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
	private GButton s_Increase;
	private GButton s_Decrease;
	private GParagraph s_Instruction;
	private ArrayList<GObject> volume_bars;

	public SoundOptions(MainApplication app) {
		super();
		this.program=app;
		background=program.menu_Bg;
		backArrow=new GImage("menu_back_arrow.png",0,0);
		backArrow.setSize(80,80);
		s_Increase= new GButton("Increase",710, 195, 100, 50);
		s_Decrease= new GButton("Decrease",710, 265, 100, 50);
		s_Instruction = new GParagraph("  Click on Increase to Increase Volume\nClick on Decrease to Decrease Volume",380,120);
		s_Instruction.setFont("Arial-24");
		s_Instruction.setColor(Color.WHITE);
		volume_bars=new ArrayList<GObject>();
	}

	@Override
	public void showContents() {
		program.add(background.getImage());
		program.add(backArrow);
		program.add(s_Increase);
		program.add(s_Decrease);
		program.add(s_Instruction);
		for(int i=0;i<cnt;i++)
		{
			Make_Volume(i);
			program.add(volume_bars.get(i));
		}


	}

	@Override
	public void hideContents() {
		program.remove(background.getImage());
		program.remove(backArrow);
		for(int i=0;i<cnt;i++)
		{
			program.remove(volume_bars.get(i));
		}
		program.remove(s_Increase);
		program.remove(s_Decrease);
		program.remove(s_Instruction);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		GObject obj = program.getElementAt(e.getX(), e.getY());
		if(obj==s_Increase)
		{
			if(cnt<5)
			{
				Make_Volume(cnt);
				program.add(volume_bars.get(cnt));
				cnt++;
			}
		}
		if(obj==s_Decrease)
		{
			if(cnt>0)
			{
				cnt--;
				program.remove(volume_bars.get(cnt));
			}
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
	
	public void Make_Volume(int cnt)
	{
		GRect vol= new GRect(385,295-(cnt*30),60,20);
		vol.setColor(Color.GREEN);
		vol.setFilled(true);
		volume_bars.add(cnt,vol);
	}
	
	
}

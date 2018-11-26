package starter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Timer;

import acm.graphics.GImage;
import acm.graphics.GObject;

public class Animation{
	private ArrayList<GImage> walk;
	private int curFrame;
	private static MainApplication program;
	private int numTimes = 0;
	private boolean isWalking = true;
	
	public void setWalking(boolean isWalking) {
		this.isWalking = isWalking;
	}

	public Animation(MainApplication app) {
		Animation.program = app;
		GImage one = new GImage("sprites/Cody/CodyWalk/walk1.png");
		GImage two = new GImage("sprites/Cody/CodyWalk/walk2.png");
		GImage three = new GImage("sprites/Cody/CodyWalk/walk3.png");
		GImage four = new GImage("sprites/Cody/CodyWalk/walk4.png");
		GImage five = new GImage("sprites/Cody/CodyWalk/walk5.png");
		GImage six = new GImage("sprites/Cody/CodyWalk/walk6.png");
		curFrame = 0;
		walk = new ArrayList<GImage>();	
		walk.add(one);
		walk.add(two);
		walk.add(three);
		walk.add(four);
		walk.add(five);
		walk.add(six);
		for (GImage img: walk)
		{
			img.setSize(111, 264);
			img.setLocation(350,280);
		}
	}

	public void handleState()
	{
		if (numTimes == 1)
		{
			if(isWalking)
			{
				walk();
			}
		}
		numTimes = (numTimes + 1) %2;
	}
	public void walk()
	{
		curFrame = (curFrame + 1) % (walk.size());
		program.remove(walk.get((curFrame == 0) ? (walk.size() - 1) : (curFrame - 1)));
		walk.get(curFrame).setLocation(walk.get(curFrame).getX() + 20, 0);
		program.add(walk.get(curFrame));
	}
}

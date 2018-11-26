package starter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Timer;

import acm.graphics.GImage;
import acm.graphics.GObject;

public class Animation{
	private GImage walkImg;
	private String[] array = {"sprites/Cody/CodyWalk/walk1.png","sprites/Cody/CodyWalk/walk2.png","sprites/Cody/CodyWalk/walk3.png"};
	private ArrayList<GImage> walk;
	private ArrayList<GImage> jump;
	private ArrayList<GImage> kick;
	private int curFrame;
	private static MainApplication program;
	private int numTimes = 0;
	private boolean isWalking = true;
	
	public void setWalking(boolean isWalking) {
		this.isWalking = isWalking;
	}

	public Animation(MainApplication app) {
		Animation.program = app;
		walkImg = new GImage(array[0]);
		curFrame = 0;
		GImage img = walkImg;
		/*
		GImage one = new GImage("sprites/Cody/CodyWalk/walk1.png");
		GImage two = new GImage("sprites/Cody/CodyWalk/walk2.png");
		GImage three = new GImage("sprites/Cody/CodyWalk/walk3.png");
		GImage four = new GImage("sprites/Cody/CodyWalk/walk4.png");
		GImage five = new GImage("sprites/Cody/CodyWalk/walk5.png");
		GImage six = new GImage("sprites/Cody/CodyWalk/walk6.png");
		
		walk = new ArrayList<GImage>();	
		walk.add(one);
		walk.add(two);
		walk.add(three);
		walk.add(four);
		walk.add(five);
		walk.add(six);
		for (GImage img: walk)
		{*/
			img.setSize(111, 264);
			img.setLocation(350,280);
		//}
		//program.add(img);
	}

	public void handleState()
	{
		if(numTimes == 0)
		{
			program.add(walkImg);
		}
		if (numTimes % 2 == 1)
		{
			if(isWalking)
			{
				walk();
			}
		}
		numTimes++;
	}
	public void walk()
	{
		curFrame = (curFrame + 1) % (array.length);
		//program.remove(walk.get((curFrame == 0) ? (array.length - 1) : (curFrame - 1)));
		walkImg.move(20, 0);
		walkImg.setImage(array[curFrame]);
		//program.add(walk.get(curFrame));
	}
	
	
	public void walkBackwards()
	{
		curFrame--;
		if(curFrame == -1)
			curFrame = walk.size() - 1;
		//program.remove(walk.get((curFrame == 0) ? (walk.size() - 1) : (curFrame - 1)));
		walk.get(curFrame).setLocation(walk.get(curFrame).getX() - 20, 0);
		program.add(walk.get(curFrame));
	}
	
	public void isWalking()
	{
		//return program.getGamePane().getPlayer.getisWalking();
	}
	
	public GImage getCurImg()
	{
		return walkImg;
	}
}

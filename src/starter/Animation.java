package starter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Timer;

import acm.graphics.GImage;
import acm.graphics.GObject;

public class Animation{
	private int prevState = 0;
	private int curState = 0;
	
	private GImage curImg;
	private String filePrefixWalk = "sprites/Cody/CodyWalk/walk";
	private String filePrefixJump = "sprites/Cody/CodyVertJump/stVertJump";
	private String filePrefixKick = "sprites/Cody/CodyKick/kick";
	private String filePrefixPunch = "sprites/Cody/CodyPunch/punch";
	private String filePost = ".png";
	private final int NUM_IMAGES_WALK = 6;
	private final int NUM_IMAGES_JUMP = 3;
	private final int NUM_IMAGES_PUNCH = 2;
	private final int NUM_IMAGES_KICK = 3;
	private ArrayList<GImage> walk;
	private ArrayList<GImage> jump;
	private ArrayList<GImage> kick;
	private int curFrame;
	private static MainApplication program;
	private int frame = 0;
	private boolean isWalking = true;
	private Player player;
	public void setWalking(boolean isWalking) {
		this.isWalking = isWalking;
	}

	public GImage flipImage(String image) {
		GImage org = new GImage(image);
		int[][] array = org.getPixelArray();
		int width = array[0].length;
		for (int i = 0; i < array.length; i++) {
			for (int p1 = 0; p1 < width / 2; p1++) {
				int p2 = width - p1 - 1;
				int temp = array[i][p1];
				array[i][p1] = array[i][p2];
				array[i][p2] = temp;
			}
		}
		return new GImage(array);
	}
	
	public Animation(MainApplication app, Player player) {
		Animation.program = app;
		
		switch (player.Id) {
		case 1:
			curImg = new GImage("sprites/Cody/CodyWalk/walk1.png");
			curImg.setLocation(350, 280);
			curFrame = 0;
			this.player = player;
			break;
		case 2:
			curImg = flipImage("sprites/Cody/CodyWalk/walk1.png");
			curImg.setLocation(800, 280);
			curFrame = 0;
			this.player = player;
			break;
		}
			
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
		//}
		//program.add(img);
	}

	public void handleState()
	{
		if(frame == 0)
		{
			program.add(curImg);
		}
		if (frame % 2 == 1)
		{
			if(player.isForward && curState != 3)
			{
				curState = 1;
				walk();
			}
			else if(player.isBackward && curState != 3)
			{
				curState = 2;
				walkBackwards();
			}
			else if(player.isJumping)
			{
				curState = 3;
				jump();
			}
			else if (!player.isJumping && curState == 3) {
				drop();
			}
			else if(player.isKicking)
			{
				curState = 4;
				kick();
			}
			else if(player.isPunching)
			{
				curState = 5;
				punch();
			}
			else
			{
				idle();
				curState = 0;
			}
		}
		if(curState != prevState)
			frame =0;
		prevState = curState;
		frame++;
		
	}
	
	public void idle()
	{
		curImg.setImage("sprites/Cody/normStance.png");
		curImg.setSize(111, 264);
		
	}
	
	public void walk()
	{
		curImg.setImage(filePrefixWalk + (frame % NUM_IMAGES_WALK + 1) + filePost);
		curImg.setSize(166, 264);
		//curFrame = (curFrame + 1) % (array.length);
		//program.remove(walk.get((curFrame == 0) ? (array.length - 1) : (curFrame - 1)));
		curImg.move(20, 0);
		//curImg.setImage(array[curFrame]);
		//program.add(walk.get(curFrame));
	}
	
	
	public void walkBackwards()
	{
		curImg.setImage(filePrefixWalk + (frame % NUM_IMAGES_WALK + 1) + filePost);
		curImg.setSize(166, 264);
		curImg.move(-20, 0);
	}
	
	public void jump()
	{
		curImg.setImage(filePrefixJump + 2 + filePost);
		curImg.setSize(166, 264);
		if (curImg.getLocation().getY() > 10)
			curImg.move(player.isForward ? 20 : (player.isBackward ? -20 : 0), -20);
	}
	
	public void drop()
	{
		if (curImg.getLocation().getY() < 280) {
			curImg.move(player.isForward ? 20 : (player.isBackward ? -20 : 0), 20);
			curImg.setImage(filePrefixJump + 3 + filePost);
			curImg.setSize(166, 264);
		}
		else {
			idle();
			curState = 0;
		}
	}
	
	public void kick()
	{
		curImg.setImage(filePrefixKick + (frame % NUM_IMAGES_KICK + 1) + filePost);
		curImg.setSize(166, 264);
	}
	
	public void punch()
	{
		curImg.setImage(filePrefixPunch + (frame % NUM_IMAGES_PUNCH  + 1) + filePost);
		curImg.setSize(166, 264);
	}
	
	public GImage getCurImg()
	{
		return curImg;
	}
}

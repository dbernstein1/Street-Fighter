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
	private String filePrefixJumpMove = "sprites/Cody/movJump/movJump";
	private String filePrefixKick = "sprites/Cody/CodyKick/kick";
	private String filePrefixPunch = "sprites/Cody/CodyPunch/punch";
	private String filePrefixKnockdown = "sprites/Cody/knockdown/knockdown";
	private String filePost = ".png";
	private final int NUM_IMAGES_WALK = 6;
	private final int NUM_IMAGES_JUMP = 3;
	private final int NUM_IMAGES_JUMP_MOVE = 5;
	private final int NUM_IMAGES_PUNCH = 2;
	private final int NUM_IMAGES_KICK = 3;
	private final int NUM_IMAGES_KNOCKDOWN = 2;
	private static MainApplication program;
	private int frame = 0;
	private Player player;

	public void flipPlayerImage()
	{
		if(player.Id == 2)
			curImg.setImage(flipImage(curImg).getImage());
	}
	
	public GImage flipImage(GImage org) {
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
		this.player = player;
		curImg = new GImage("sprites/Cody/normStance.png");
		switch (player.Id) {
		case 1:
			curImg.setLocation(350, 280);
			break;
		case 2:
			curImg.setLocation(800, 280);
			flipPlayerImage();
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
			if((player.isBackward || player.isForward) && player.isJumping)
			{
				curState = 3;
				jumpMove();
			}
			else if(player.isForward)
			{
				curState = 1;
				walk();
			}
			else if(player.isBackward)
			{
				curState = 2;
				walkBackwards();
			}
			else if(player.isJumping)
			{
				curState = 4;
				jump();
			}
			else if(player.isKicking)
			{
				curState = 5;
				kick();
			}
			else if(player.isPunching)
			{
				curState = 6;
				punch();
			}
			else if(player.lost)
			{
				curState = -1;
				knockDown();
			}
			else
			{
				curState = 0;
				idle();
			}
			if (!player.isJumping) {
				drop();
			}
		}
		if(curState != prevState)
			frame = 0;
		prevState = curState;
		frame++;
		
		}
		
	
	
	public void idle()
	{
		curImg.setImage("sprites/Cody/normStance.png");
		flipPlayerImage();
		curImg.setSize(111, 264);
	}
	
	public void walk()
	{
		curImg.setImage(filePrefixWalk + (frame % NUM_IMAGES_WALK + 1) + filePost);
		flipPlayerImage();
		curImg.setSize(166, 264);
		curImg.move(20, 0);
	}
	
	
	public void walkBackwards()
	{
		curImg.setImage(filePrefixWalk + (frame % NUM_IMAGES_WALK + 1) + filePost);
		flipPlayerImage();
		curImg.setSize(166, 264);
		curImg.move(-20, 0);
	}
	
	public void jump()
	{
		curImg.setImage(filePrefixJump + 2 + filePost);
		flipPlayerImage();
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
		flipPlayerImage();
		curImg.setSize(111, 264);
	}
	
	public void punch()
	{
		curImg.setImage(filePrefixPunch + (frame % NUM_IMAGES_PUNCH  + 1) + filePost);
		flipPlayerImage();
		curImg.setSize(111, 264);
	}
	
	public void jumpMove()
	{
		curImg.setImage(filePrefixJumpMove + (frame % NUM_IMAGES_JUMP_MOVE + 1) + filePost);
		flipPlayerImage();
		curImg.setSize(111, 264);
	}
	public void knockDown()
	{
		if(frame < NUM_IMAGES_KNOCKDOWN)
			curImg.setImage(filePrefixKnockdown + (frame + 1) + filePost);
		if(frame > 15)
			program.getGamePane().showStats();
		flipPlayerImage();
		curImg.setSize(111, 264);
	}
	
	public GImage getCurImg()
	{
		return curImg;
	}
}

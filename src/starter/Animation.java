package starter;
import java.nio.file.Files;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.Timer;

import acm.graphics.GImage;
import acm.graphics.GObject;

public class Animation{
	private int numMoveOut = 0;
	private int prevState = 0;
	private int curState = 0;
	private int speed = 1;
	private GImage curImg;
	private String fileIdle;
	private String filePrefixWalk;
	private String filePrefixJump;
	private String filePrefixJumpMove;
	private String filePrefixJumpKick;
	private String filePrefixKick;
	private String filePrefixPunch;
	private String filePrefixKnockdown;
	private String fileDuck;
	private String filePost;
	private int NUM_IMAGES_WALK;
	private int NUM_IMAGES_JUMP;
	private int NUM_IMAGES_JUMP_MOVE;
	private int NUM_IMAGES_PUNCH;
	private int NUM_IMAGES_KICK;
	private int NUM_IMAGES_KNOCKDOWN;
	private int NUM_IMAGES_DUCK;
	private static MainApplication program;
	private int frame = 0;
	private Player player;

	public void flipPlayerImage()
	{
		if(player.Id == 2)
		{
			curImg.setImage(flipImage(curImg).getImage());
		}
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
		fileIdle = "sprites/" + player.name + "/normStance.png";
		fileDuck = "sprites/"  + player.name + "/duck.png";
		filePrefixWalk = "sprites/" + player.name + "/" + player.name + "Walk/walk";
		filePrefixJump = "sprites/"  + player.name + "/" + player.name + "Jump/" + "jump";
		filePrefixJumpMove = "sprites/"  + player.name + "/movJump/movJump";
		filePrefixKick = "sprites/"  + player.name + "/" + player.name + "Kick/kick";
		filePrefixPunch = "sprites/"  + player.name + "/"  + player.name + "Punch/punch";
		filePrefixKnockdown = "sprites/"  + player.name + "/knockdown/knockDown";
		filePrefixJumpKick = "sprites/"  + player.name + "/"  + player.name + "JumpKick/jumpKick";
		filePost = ".png";
		switch (player.number) {
		case 1:
			filePrefixJumpKick = "sprites/"  + player.name + "/"  + player.name + "movJumpKick/jumpKick";
			NUM_IMAGES_WALK = 6;
			NUM_IMAGES_JUMP = 6;
			NUM_IMAGES_JUMP_MOVE = 6;
			NUM_IMAGES_PUNCH = 2;
			NUM_IMAGES_KICK = 5;
			NUM_IMAGES_KNOCKDOWN = 1;
			NUM_IMAGES_DUCK = 1;
			break;
		case 2:
			NUM_IMAGES_WALK = 6;
			NUM_IMAGES_JUMP = 3;
			NUM_IMAGES_JUMP_MOVE = 5;
			NUM_IMAGES_PUNCH = 2;
			NUM_IMAGES_KICK = 3;
			NUM_IMAGES_KNOCKDOWN = 2;
			NUM_IMAGES_DUCK = 1;
			break;
		case 3:
			NUM_IMAGES_WALK = 6;
			NUM_IMAGES_JUMP = 4;
			NUM_IMAGES_JUMP_MOVE = 4;
			NUM_IMAGES_PUNCH = 4;
			NUM_IMAGES_KICK = 6;
			NUM_IMAGES_KNOCKDOWN = 3;
			NUM_IMAGES_DUCK = 1;
			break;
		case 4:
			NUM_IMAGES_WALK = 7;
			NUM_IMAGES_JUMP = 3;
			NUM_IMAGES_JUMP_MOVE = 3;
			NUM_IMAGES_PUNCH = 3;
			NUM_IMAGES_KICK = 2;
			NUM_IMAGES_KNOCKDOWN = 3;
			NUM_IMAGES_DUCK = 1;
			break;
		}
		
		curImg = new GImage(fileIdle);
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
			if(player.isJumpVert)
			{
				setCurState(4);
				jump();
			}
			else if(player.isJumpMoving)
			{
				setCurState(7);
				jumpMove();
			}
			else if(player.isDucking)
			{
				setCurState(8);
				duck();
			}
			else if(player.isKicking)
			{
				setCurState(5);
				if(player.Id== 2)
				{
					numMoveOut += 10;
					curImg.move(-10, 0);
				}
				else
				{
					numMoveOut -= 10;
					curImg.move(10, 0);
				}
				kick();
			}
			else if(player.isPunching)
			{
				setCurState(6);
				if(player.Id== 2)
				{
					numMoveOut += 10;
					curImg.move(-10, 0);
				}
				else
				{
					numMoveOut -= 10;
					curImg.move(10, 0);
				}
				punch();
			}
			else if(player.isForward)
			{
				setCurState(1);
				walk();
			}
			else if(player.isBackward)
			{
				setCurState(2);
				walkBackwards();
			}
			else if(player.lost)
			{
				setCurState(-1);
				if(getCurState() != prevState)
					frame = 0;
				knockDown();
			}
			else
			{
				setCurState(0);
				idle();
			}
		}
		if(getCurState() != prevState)
		{
			frame = 0;
			if(prevState == 6)
			{
				curImg.move(numMoveOut, 0);
				numMoveOut = 0;
			}
			if(prevState == 5)
			{
				curImg.move(numMoveOut, 0);
				numMoveOut = 0;
			}
		}
		prevState = getCurState();
		frame++;
		
		}
		
	
	
	public void idle()
	{
		curImg.setImage(fileIdle);
		flipPlayerImage();
		curImg.setSize(111, 264);
	}
	
	public void walk()
	{
		curImg.setImage(filePrefixWalk + (frame % NUM_IMAGES_WALK + 1) + filePost);
		flipPlayerImage();
		if(player.number == 2)
			curImg.setSize(166, 264);
		else
			curImg.setSize(111, 264);
	}
	
	
	public void walkBackwards()
	{
		curImg.setImage(filePrefixWalk + (frame % NUM_IMAGES_WALK + 1) + filePost);
		flipPlayerImage();
		if(player.number == 2)
			curImg.setSize(166, 264);
		else
			curImg.setSize(111, 264);
	}
	
	public void jump()
	{
		curImg.setImage(filePrefixJump + (frame % NUM_IMAGES_JUMP + 1) + filePost);
		if(player.number == 2)
			curImg.setImage(filePrefixJump + 2 + filePost);
		flipPlayerImage();
		curImg.setSize(111, 264);
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
	
	public void duck()
	{
		curImg.setImage(fileDuck);
		flipPlayerImage();
		curImg.setSize(111, 264);
	}
	
	public void knockDown()
	{
		if(frame < NUM_IMAGES_KNOCKDOWN)
		{
			curImg.setImage(filePrefixKnockdown + (frame + 1) + filePost);
			flipPlayerImage();
		}
		if(frame > 15)
			program.getGamePane().showStats();
		curImg.setSize(111, 264);
	}
	
	public GImage getCurImg()
	{
		return curImg;
	}

	public int getCurState() {
		return curState;
	}

	public void setCurState(int curState) {
		this.curState = curState;
	}
	
}
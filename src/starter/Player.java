package starter;

import java.awt.Color;
import java.util.ArrayList;

import acm.graphics.GImage;
import acm.graphics.GObject;
import acm.graphics.GOval;
import acm.graphics.GRect;
import acm.program.GraphicsProgram;
import acm.program.Program;

public class Player extends GraphicsProgram {
	public static final int GROUND = 550;

	public String name = "";
	public int Id;
	
	public int jump, duck, hittime;
	public int jctr=1,dctr=1,speed=1;
	public double strength = 1;
	public double hpbarx;
	public double hptotal = 100, hp = 100;
	public boolean isJumpVert =false, isDucking=false, isJumpMoving = false;
	public boolean isPunching=false,isKicking=false;
	public boolean isForward = false;
	public boolean isBackward = false;
	public boolean outOfBounds = false;
	public boolean lost = false;
	public GRect hpoutline;
	public GRect hpbar;
	public int number;
	Player(int player, int number, String n, double MaxHealth, double StrengthMultiplier){
		this.number = number;
		name = n;
		hptotal = MaxHealth;
		hp = hptotal;
		strength = StrengthMultiplier;
		switch (player) {
		case 1:
			Id = 1;
			hpbarx = (MainApplication.WINDOW_WIDTH * 0.025);
			hpoutline = new GRect(hpbarx, 25, 500, 15);
			hpoutline.setFilled(true);
			hpbar = new GRect(hpbarx * (hp / hptotal), 25, 500, 15);
			hpbar.setFillColor(new Color(0, 255, 0));
			hpbar.setFilled(true);
			break;
		case 2:
			Id = 2;
			hpbarx = (MainApplication.WINDOW_WIDTH * 0.55);
			hpoutline = new GRect(hpbarx, 25, 500, 15);
			hpoutline.setFilled(true);
			hpbar = new GRect(hpbarx * (hp / hptotal), 25, 500, 15);
			hpbar.setFillColor(new Color(0, 255, 0));
			hpbar.setFilled(true);
			break;
		}
	}
	
	public void HandleMovement() {
			if(isDucking){
				isForward = false;
				isBackward = false;
				GamePane.playerMove(this, 0, duck);
				duck-=dctr;
				if (duck == -8){
					duck=7;
					isDucking=false;
				}
			}
			if(isForward){
				if(speed<3)
				{
					speed++;
				}
				if(isPunching||isKicking)
				{
					speed=0;
				}
				GamePane.playerMove(this, speed, 0);
			}
			if(!isForward){
				if(speed>0)
				{
					speed--;
				}
				if(isPunching||isKicking)
				{
					speed=0;
				}
					GamePane.playerMove(this, speed, 0);
			}
			if(isBackward){
				if(speed>-3)
				{
					speed--;
				}
				if(isPunching||isKicking)
				{
					speed=0;
				}
				GamePane.playerMove(this, speed, 0);
			}
			if(!isBackward){
				if(speed<0)
				{
					speed++;
				}
				if(isPunching||isKicking)
				{
					speed=0;
				}
					GamePane.playerMove(this, speed, 0);
			}
			
			if(isKicking){
				hittime++;
				if(hittime==10){
					isKicking=false;
					hittime=0;
				}
			}
			if(isPunching){
				hittime++;
				if(hittime==6){
					isPunching=false;
					hittime=0;
				}
			}
			if(isJumpVert){
				GamePane.playerMove(this, 0, jump);
				jump+=jctr;
				if(jump==11){
					jump=-10;
					isJumpVert=false;
				}	
			}
			if(isJumpMoving){
				GamePane.playerMove(this, 0, jump);
				jump+=jctr;
				if(jump==11){
					jump=-10;
					isJumpMoving=false;
				}	
			}
		}
	
}

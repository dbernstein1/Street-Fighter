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

	@SuppressWarnings("unused")
	public String name = "";
	public int Id;
	
	public int jump, duck, hittime;
	public int jctr=1,dctr=1,speed=1;
	public double stamina = 15, staminatotal = 15;
	public double strength = 1;
	public double hpbarx, staminabarx;
	public double hptotal = 100, hp = 100;
	public boolean isJumpVert =false, isDucking=false, isJumpMoving = false;
	public boolean isPunching=false,isKicking=false;
	public boolean isForward = false;
	public boolean isBackward = false;
	public boolean outOfBounds = false;
	public boolean lost = false;
	public GRect body,leg,arm,punch,kick,jumpbox;
	public GRect hpbar, hpoutline, staminabar;
	public GOval head;
	public GImage chCody;
	
	public ArrayList<GObject> arrayList = new ArrayList<GObject>();
	private Animation animation;
	public int number;
	Player(int player, int number, String n, double MaxHealth, double StrengthMultiplier, double MaxStamina){
		this.animation = animation;
		this.number = number;
		hptotal = MaxHealth;
		hp = hptotal;
		strength = StrengthMultiplier;
		staminatotal = MaxStamina;
		stamina = staminatotal;
		name = n;
		switch (player) {
		case 1:
			Id = 1;
			punch=new GRect(270,GROUND-80,30,10);
			kick=new GRect(270,GROUND-50,30,10);
			head=new GOval(210,GROUND-150,50,50);
			body=new GRect(200,GROUND-100,70,70);
			body.setColor(new Color(255,255, 255, 0));
			leg=new GRect(230,GROUND-30,10,30);
			jumpbox= new GRect(800,GROUND-235,110,85);
			arm=new GRect(240,GROUND-80,10,40);
			chCody = new GImage("sprites/Cody/normStance.png",350,GROUND-270);
			hpbarx = (MainApplication.WINDOW_WIDTH * 0.05);
			hpoutline = new GRect(hpbarx, 25, 500, 15);
			hpoutline.setFilled(true);
			hpbar = new GRect(hpbarx * (hp / hptotal), 25, 500, 15);
			hpbar.setFillColor(new Color(0, 255, 0));
			hpbar.setFilled(true);
			staminabarx = hpbarx + 1;
			staminabar = new GRect(staminabarx * (stamina / staminatotal), 42, 260, 15);
			staminabar.setFillColor(new Color(0, 0, 255));
			staminabar.setFilled(true);
			
			break;
		case 2:
			Id = 2;
			punch=new GRect(770,GROUND-80,30,10);
			kick=new GRect(770,GROUND-50,30,10);
			head=new GOval(840,GROUND-265,30,30);
			body=new GRect(800,GROUND-235,110,85);
			leg=new GRect(830,GROUND-150,60,150);
			arm=new GRect(820,GROUND-80,10,40);
			jumpbox= new GRect(800,GROUND-235,160,150);
			chCody = new GImage("sprites/Cody/normStance.png",0,0);
			hpbarx = (MainApplication.WINDOW_WIDTH * 0.5);
			hpoutline = new GRect(hpbarx, 25, 500, 15);
			hpoutline.setFilled(true);
			hpbar = new GRect(hpbarx * (hp / hptotal), 25, 500, 15);
			hpbar.setFillColor(new Color(0, 255, 0));
			hpbar.setFilled(true);
			staminabarx = hpbarx + 239;
			staminabar = new GRect(staminabarx * (stamina / staminatotal), 42, 260, 15);
			staminabar.setFillColor(new Color(0, 0, 255));
			staminabar.setFilled(true);
			break;
		}
	}
	
	public void RefreshArray() {  
		arrayList.add(head);
		arrayList.add(body);
		arrayList.add(leg);
		arrayList.add(hpoutline);
		arrayList.add(hpbar);
		arrayList.add(staminabar);	
	}
	
	public void remove() {
		
		for (int i = 0; i < arrayList.size(); i++) {
			GamePane.remove(arrayList.get(i));
		}
		GamePane.remove(punch);
		GamePane.remove(kick);
	}
	
	public void updateStamina() { 
		
		
		GamePane.remove(staminabar);
		staminabar = new GRect(staminabarx, 42, 260 * (stamina / staminatotal), 15);
		staminabar.setFillColor(stamina > 4.5 ? new Color(0,0,255) : new Color(255,255,0));
		staminabar.setFilled(true);
		GamePane.add(staminabar);
		
	}
		

	
	public void HandleMovement() {
			if(isDucking){
				isForward = false;
				isBackward = false;
				GamePane.playerMove(this, 0, duck);
				GamePane.remove(leg);
				stamina -= 0.1;
				updateStamina();
				duck-=dctr;
				if (duck == -8){
					duck=7;
					isDucking=false;
					GamePane.add(leg);
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
				GamePane.add(kick);
				GamePane.remove(leg);
				hittime++;
				stamina -= 0.05;
				updateStamina();
				if(hittime==10){
					GamePane.remove(kick);
					isKicking=false;
					hittime=0;
				}
			}
			if(isPunching){
				GamePane.add(punch);
				GamePane.remove(arm);
				hittime++;
				stamina -= 0.05;
				updateStamina();
				if(hittime==6){
					GamePane.remove(punch);
					GamePane.add(arm);
					isPunching=false;
					hittime=0;
				}
			}
			if(isJumpVert){
				GamePane.playerMove(this, 0, jump);
				GamePane.remove(leg);
				GamePane.remove(body);
				GamePane.remove(head);
				GamePane.add(jumpbox);
				jump+=jctr;
				stamina -= 0.1;
				updateStamina();
				if(jump==11){
					jump=-10;
					isJumpVert=false;
				}	
			}
			if(isJumpMoving){
				GamePane.playerMove(this, 0, jump);
				GamePane.remove(leg);
				GamePane.remove(body);
				GamePane.remove(head);
				GamePane.add(jumpbox);
				jump+=jctr;
				stamina -= 0.1;
				updateStamina();
				if(jump==11){
					jump=-10;
					isJumpMoving=false;
				}	
			}
		}
	
	public Animation getAnimation()
	{
		return animation;
	}
}

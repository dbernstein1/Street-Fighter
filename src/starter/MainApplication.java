package starter;
import java.awt.event.*;
import javax.swing.*;
import acm.graphics.*;

public class MainApplication extends GraphicsApplication implements ActionListener {
	public static final int WINDOW_WIDTH = 1200;
	public static final int WINDOW_HEIGHT = 600;
	public static final int GROUND = 550;
	public static final String MUSIC_FOLDER = "sounds";
	//private static final String[] SOUND_FILES = { "r2d2.mp3", "somethinlikethis.mp3" };

	private GRect body1,body2,leg1,leg2,arm1,arm2,punch1,punch2,kick1,kick2;
	private GOval head1,head2;
	/*private SomePane somePane;
	private MenuPane menu;
	private int count;*/
	private int jump1,jump2,duck1,duck2,hittime1,hittime2;
	private int jctr=1,dctr=1;
	private boolean isJumping1=false,isJumping2=false,isDucking1=false,isDucking2=false;
	private boolean isPunching1=false,isPunching2=false,isKicking1=false,isKicking2=false;


	public void init() {
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);	
	}

	public void run() {
		addKeyListeners();
		punch1=new GRect(270,GROUND-80,30,10);
		punch2=new GRect(770,GROUND-80,30,10);
		kick1=new GRect(270,GROUND-50,30,10);
		kick2=new GRect(770,GROUND-50,30,10);
		head1=new GOval(210,GROUND-150,50,50);
		add(head1);
		head2=new GOval(810,GROUND-150,50,50);
		add(head2);
		body1=new GRect(200,GROUND-100,70,70);
		add(body1);
		body2=new GRect(800,GROUND-100,70,70);
		add(body2);
		/*leg1=new GRect(235,GROUND,30,10);
		add(leg1);
		leg2=new GRect(635,GROUND,30,10);
		add(leg2);*/
		leg1=new GRect(230,GROUND-30,10,30);
		add(leg1);
		leg2=new GRect(830,GROUND-30,10,30);
		add(leg2);
		arm1=new GRect(240,GROUND-80,10,40);
		add(arm1);
		arm2=new GRect(820,GROUND-80,10,40);
		add(arm2);
		Timer t=new Timer(50,this);
		t.start();
		/*System.out.println("Hello, world!");
		somePane = new SomePane(this);
		menu = new MenuPane(this);
		switchToMenu();*/
	}

public void actionPerformed(ActionEvent e)
{
	if(isKicking1)
	{
		add(kick1);
		hittime1++;
		if(hittime1==10)
		{
			remove(kick1);
			isKicking1=false;
			hittime1=0;
		}
	}
	if(isKicking2)
	{
		add(kick2);
		hittime2++;
		if(hittime2==10)
		{
			remove(kick2);
			isKicking2=false;
			hittime2=0;
		}
	}
	if(isPunching1)
	{
		add(punch1);
		hittime1++;
		if(hittime1==6)
		{
			remove(punch1);
			add(arm1);
			isPunching1=false;
			hittime1=0;
		}
	}
	if(isPunching2)
	{
		add(punch2);
		hittime2++;
		if(hittime2==6)
		{
			remove(punch2);
			add(arm2);
			isPunching2=false;
			hittime2=0;
		}
	}
	if(isDucking1)
	{
		head1.move(0, duck1);
		body1.move(0,duck1);
		arm1.move(0, duck1);
		punch1.move(0, duck1);
		kick1.move(0, duck1);
		remove(leg1);
		duck1-=dctr;
		if(duck1==-8)
		{
			duck1=7;
			isDucking1=false;
			add(leg1);
		}
	}
	if(isDucking2)
	{
		head2.move(0, duck2);
		body2.move(0,duck2);
		arm2.move(0, duck2);
		punch2.move(0, duck2);
		kick2.move(0, duck2);
		remove(leg2);
		duck2-=dctr;
		if(duck2==-8)
		{
			duck2=7;
			isDucking2=false;
			add(leg2);
		}
	}
		if(isJumping1)
		{
			head1.move(0,jump1);
			body1.move(0,jump1);
			leg1.move(0,jump1);
			arm1.move(0,jump1);
			punch1.move(0, jump1);
			kick1.move(0, jump1);
			jump1+=jctr;
			if(jump1==11)
			{
				jump1=-10;
				isJumping1=false;
			}	
		}
		if(isJumping2)
		{
			head2.move(0,jump2);
			body2.move(0,jump2);
			leg2.move(0,jump2);
			arm2.move(0,jump2);
			punch2.move(0, jump2);
			kick2.move(0, jump2);
			jump2+=jctr;
			if(jump2==11)
			{
				jump2=-10;
				isJumping2=false;
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent e)
	{
		if(e.getKeyCode()==KeyEvent.VK_RIGHT)
		{
			head2.move(5, 0);
			body2.move(5, 0);
			leg2.move(5, 0);
			arm2.move(5,0);
			punch2.move(5, 0);
			kick2.move(5, 0);
		}
		if(e.getKeyCode()==KeyEvent.VK_LEFT)
		{
			head2.move(-5, 0);
			body2.move(-5, 0);
			leg2.move(-5, 0);
			arm2.move(-5,0);
			punch2.move(-5, 0);
			kick2.move(-5, 0);
		}
		if(e.getKeyCode()==KeyEvent.VK_D)
		{
			head1.move(5, 0);
			body1.move(5, 0);
			leg1.move(5, 0);
			arm1.move(5,0);
			punch1.move(5, 0);
			kick1.move(5, 0);
		}
		if(e.getKeyCode()==KeyEvent.VK_A)
		{
			head1.move(-5, 0);
			body1.move(-5, 0);
			leg1.move(-5, 0);
			arm1.move(-5,0);
			punch1.move(-5, 0);
			kick1.move(-5, 0);
		}
		if(e.getKeyCode()==KeyEvent.VK_UP)
		{	
			if(!isJumping2  && !isDucking2)
			{
				jump2=-10;
				isJumping2=true;
			}
		}
		if(e.getKeyCode()==KeyEvent.VK_W)
		{
			if(!isJumping1  && !isDucking1)
			{
				jump1=-10;
				isJumping1=true;
			}
		}
		if(e.getKeyCode()==KeyEvent.VK_DOWN)
		{	
			if(!isDucking2  && !isJumping2)
			{
				duck2=7;
				isDucking2=true;
			}
		}
		if(e.getKeyCode()==KeyEvent.VK_S)
		{	
			if(!isDucking1 && !isJumping1)
			{
				duck1=7;
				isDucking1=true;
			}
		}
		if(e.getKeyCode()==KeyEvent.VK_P)
		{
			if(!isPunching1 && !isKicking1)
			{
				hittime1=0;
				remove(arm1);
				isPunching1=true;
			}
		}
		if(e.getKeyCode()==KeyEvent.VK_SPACE)
		{
			if(!isPunching2 && !isKicking2)
			{
				hittime2=0;
				remove(arm2);
				isPunching2=true;
			}
		}
		if(e.getKeyCode()==KeyEvent.VK_CONTROL)
		{
			if(!isKicking2 && !isPunching2)
			{
				hittime2=0;
				isKicking2=true;
			}
		}
		if(e.getKeyCode()==KeyEvent.VK_K)
		{
			if(!isKicking1 && !isPunching1)
			{
				hittime1=0;
				isKicking1=true;
			}
		}
	}

	/*public void switchToMenu() {
		playRandomSound();
		count++;
		switchToScreen(menu);
	}

	public void switchToSome() {
		playRandomSound();
		switchToScreen(somePane);
	}

	private void playRandomSound() {
		AudioPlayer audio = AudioPlayer.getInstance();
		audio.playSound(MUSIC_FOLDER, SOUND_FILES[count % SOUND_FILES.length]);
	}*/
}

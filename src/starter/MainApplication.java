package starter;
import java.awt.event.*;
import javax.swing.*;
import acm.graphics.*;

public class MainApplication extends GraphicsApplication /*implements ActionListener*/ {
	public static final int WINDOW_WIDTH = 1200;
	public static final int WINDOW_HEIGHT = 600;
	public static final int GROUND = 550;
	public static final String MUSIC_FOLDER = "sounds";
	private static final String[] SOUND_FILES = {"megalovania.mp3" , "r2d2.mp3", "somethinlikethis.mp3" };

	private SomePane somePane;
	private GamePane gamePane;
	private MenuPane menu;
	private CharacterPane characterPane;
	private int count;
	public Background background,menu_Bg;
	//private Background background;
	
	/*
	public Background getBackground(){
		return background;
	}
	*/
	public void setBackground(Background background) {
		this.background = background;
	}


	public void init() {
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);	
	}
	

	public void run() {
		System.out.println("Hello, world!");
		somePane = new SomePane(this);
		background = new Background("example_back.jpg", WINDOW_WIDTH, WINDOW_HEIGHT);
		menu_Bg= new Background("sans.jpg", WINDOW_WIDTH, WINDOW_HEIGHT);
		menu = new MenuPane(this);
		gamePane = new GamePane(this);
		characterPane = new CharacterPane(this);
		switchToGame();
	}

	public void switchToMenu() {
		playRandomSound();
		count++;
		switchToScreen(menu);
	}

	public void switchToSome() {
		playRandomSound();
		switchToScreen(somePane);
	}
	
	public void switchToGame() {
		switchToScreen(gamePane);
	}

	public void switchToCharacterPane() {
		switchToScreen(characterPane);
	}
	private void playRandomSound() {
		AudioPlayer audio = AudioPlayer.getInstance();
		audio.playSound(MUSIC_FOLDER, SOUND_FILES[count % SOUND_FILES.length],true);
	}
}

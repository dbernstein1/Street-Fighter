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
	private static final String IMAGES[]= {"sans.jpg","example_back.jpg","portMapMain.png"};


	private LevelSelectionPane levelPane;
	private MenuPane menu;
	private CharacterPane characterPane;
	private Options options;
	public TimeOptions t_Opt;
	public GamePane gamePane;
	private LevelSelectionPane level;
	private int count;
	private Player PLAYER_ONE;


	private Player PLAYER_TWO;
	public Background background,menu_Bg;
	public Background backgroundPort;
	public Background backgroundPort2;
	//private Background background;
	
	/*
	public Background getBackground(){
		return background;
	}
	*/
	
	public GamePane getGamePane() {
		return gamePane;
	}
	
	public LevelSelectionPane getLevelPane() {
		return level;
	}
	public void setBackground(Background background) {
		this.background = background;
	}


	public void init() {
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);	
		requestFocus();
	}
	

	public void run() {
		background = new Background(IMAGES[1], WINDOW_WIDTH, WINDOW_HEIGHT);
		backgroundPort = new Background(IMAGES[2], WINDOW_WIDTH, WINDOW_HEIGHT);
		backgroundPort2 = new Background("portMapMain2.png", WINDOW_WIDTH, WINDOW_HEIGHT);
		menu_Bg= new Background(IMAGES[0], WINDOW_WIDTH, WINDOW_HEIGHT);
		menu = new MenuPane(this);
		gamePane = new GamePane(this);
		characterPane = new CharacterPane(this);
		levelPane= new LevelSelectionPane(this); 
		options = new Options(this);
		t_Opt=new TimeOptions(this);
		switchToMenu();
	}

	public void switchToMenu() {
		//playSound();
		count++;
		switchToScreen(menu);
	}

	public void switchToOptions() {
		switchToScreen(options);
	}
	
	public void switchToLevel() {
		switchToScreen(levelPane);
	}
	
	public void switchToGame() {
		stopSound();
		switchToScreen(gamePane);
	}

	public void switchToCharacterPane() {
		switchToScreen(characterPane);
	}
	public void switchToTimeOptions() {
		switchToScreen(t_Opt);
	}
	private void playSound() {
		AudioPlayer audio = AudioPlayer.getInstance();
		audio.playSound(MUSIC_FOLDER, SOUND_FILES[0],true);
	}
	private void stopSound() {
		AudioPlayer audio = AudioPlayer.getInstance();
		audio.stopSound(MUSIC_FOLDER, SOUND_FILES[0]);
	}


	public void remove(Background bgPort) {
		// TODO Auto-generated method stub
	
	}


	public void add(Background bgPort) {
		// TODO Auto-generated method stub
		
	}


}

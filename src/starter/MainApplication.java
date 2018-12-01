package starter;
import java.awt.event.*;
import javax.swing.*;
import acm.graphics.*;

public class MainApplication extends GraphicsApplication /*implements ActionListener*/ {
	public static final int WINDOW_WIDTH = 1200;
	public static final int WINDOW_HEIGHT = 600;
	public static final int GROUND = 550;
	public static final String MUSIC_FOLDER = "sounds";
	private static final String[] SOUND_FILES = {"megalovania.mp3" , "deathbyglamour.mp3", "ghostfight.mp3","spiderdance.mp3","spearofjustice.mp3" };
	private static final String IMAGES[]= {"sans.jpg","example_back.jpg","portMapMain.png", "forestMap.jpg","beachMap01.png"};


	private LevelSelectionPane levelPane;
	private MenuPane menu;
	private CharacterPane characterPane;
	private Options options;
	private TimeOptions t_Opt;
	private SoundOptions s_Opt;
	private GamePane gamePane;
	private PauseMenu pause;
	private LevelSelectionPane level;
	private int count;
	private Player PLAYER_ONE;


	private Player PLAYER_TWO;
	public Background background,menu_Bg;
	public Background backgroundPort;
	public Background backgroundPort2;
	public Background backgroundForest;
	public Background backgroundBeach, backgroundBeach2, backgroundBeach3;
	public Timer rndTimer;
	private InstructionScreen inst;

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
		backgroundForest = new Background("forestMap.jpg", WINDOW_WIDTH, WINDOW_HEIGHT);
		backgroundBeach = new Background("maps/BeachMap/beachMap01.png", WINDOW_WIDTH, WINDOW_HEIGHT);
		backgroundBeach2 = new Background("maps/BeachMap/beachMap02.png", WINDOW_WIDTH, WINDOW_HEIGHT);
		backgroundBeach3 = new Background("maps/BeachMap/beachMap03.png", WINDOW_WIDTH, WINDOW_HEIGHT);
		menu_Bg= new Background(IMAGES[0], WINDOW_WIDTH, WINDOW_HEIGHT);
		//	rndTimer = new Timer(T_IMAGES[10], );
		menu = new MenuPane(this);
		gamePane = new GamePane(this);
		characterPane = new CharacterPane(this);
		levelPane= new LevelSelectionPane(this); 
		options = new Options(this);
		t_Opt=new TimeOptions(this);
		s_Opt=new SoundOptions(this);
		pause=new PauseMenu(this);
		inst=new InstructionScreen(this);
		switchToMenu();
	}
	
	public void switchToMenu() {
		playSound();
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
		switchToScreen(gamePane);
	}

	public void switchToCharacterPane() {
		switchToScreen(characterPane);
	}

	public void switchToTimeOptions() {
		switchToScreen(t_Opt);
	}

	public void switchToPauseMenu() {
		switchToScreen(pause);
	}

	public void switchToSoundOptions(){
		switchToScreen(s_Opt);
	}

	public void switchToInstructionScreen()
	{
		stopSound();
		switchToScreen(inst);
	}
	public void playSound() {
		AudioPlayer audio = AudioPlayer.getInstance();
		audio.playSound(MUSIC_FOLDER, SOUND_FILES[2],true);
	}
	public void stopSound() {
		AudioPlayer audio = AudioPlayer.getInstance();
		audio.stopSound(MUSIC_FOLDER, SOUND_FILES[2]);
	}
	public void levelSound(int level,boolean isPlaying)
	{
		AudioPlayer audio = AudioPlayer.getInstance();
		switch(level)
		{
		case 1:
			if(!isPlaying)
			{
				audio.playSound(MUSIC_FOLDER, SOUND_FILES[0],true);
				break;
			}
			audio.stopSound(MUSIC_FOLDER, SOUND_FILES[0]);
			break;
		case 2:
			if(!isPlaying)
			{
				audio.playSound(MUSIC_FOLDER, SOUND_FILES[1],true);
				break;
			}
			audio.stopSound(MUSIC_FOLDER, SOUND_FILES[1]);
			break;
		case 3:
			if(!isPlaying)
			{
				audio.playSound(MUSIC_FOLDER, SOUND_FILES[3],true);
				break;
			}
			audio.stopSound(MUSIC_FOLDER, SOUND_FILES[3]);
			break;
		case 4:
			if(!isPlaying)
			{
				audio.playSound(MUSIC_FOLDER, SOUND_FILES[4],true);
				break;
			}
			audio.stopSound(MUSIC_FOLDER, SOUND_FILES[4]);
			break;
		}
	}


}

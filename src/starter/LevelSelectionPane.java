package starter;
import java.awt.event.MouseEvent;

import acm.graphics.GImage;
import acm.graphics.GObject;

public class LevelSelectionPane extends GraphicsPane {
	private MainApplication program;
	private static final String IMAGES[]= {"example_back.jpg","menu_back_arrow.png"};
	private GImage arena1,backArrow;
	public LevelSelectionPane(MainApplication app )
	{
		this.program=app;
		arena1=new GImage(IMAGES[0],100,100);
		arena1.setSize(300, 200);
		backArrow=new GImage(IMAGES[1],0,0);
		backArrow.setSize(80,80);
	}
	
	@Override
	public void showContents() {
		program.add(arena1);
		program.add(backArrow);
	}

	@Override
	public void hideContents() {
		program.remove(arena1);
		program.remove(backArrow);
	}
	@Override
	public void mousePressed(MouseEvent e) {
		GObject obj = program.getElementAt(e.getX(), e.getY());
		if (obj == backArrow) {
			program.switchToMenu();
		}
		if(obj==arena1)
		{
			program.switchToGame();
		}
	}

}

package starter;
import java.awt.Color;
import java.awt.event.MouseEvent;

import acm.graphics.GImage;
import acm.graphics.GObject;

import acm.program.GraphicsProgram;

public class Level extends GraphicsProgram{
	int choice;
	GImage lvl_Img;
	public GImage getLvl_Img() {
		return lvl_Img;
	}

	Level(int l,GImage img)
	{
		choice=l;
		lvl_Img=img;
	}
	
	public int get_Choice()
	{
		return choice;
	}

}

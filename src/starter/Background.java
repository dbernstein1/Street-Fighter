package starter;
import acm.graphics.*;
import acm.program.*;
import acm.util.*;
import java.awt.*;

public class Background{
	public Background(String backgroundName, int width, int height)
	{
		this.image = new GImage(backgroundName, 0, 0);
		this.image.setSize(width, height);
		this.height = height;
		this.width = width;
	}
	private GImage image;
	private double height;
	private double width;
	public boolean outOfBounds(Player player)
	{
		return true;
	}
	
	public GImage getImage() {
		return image;
	}
	
}

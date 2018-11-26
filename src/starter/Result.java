package starter;

import java.awt.Color;

import acm.graphics.GRect;

public class Result extends GraphicsApplication {
	private MainApplication program;
	private int total_p=0,total_k=0,total_h=0, cnt;
	private GRect disp_box;
	private GParagraph tot_p;
	private GParagraph tot_k;
	private GParagraph tot_h;
	public Result(MainApplication app,int n)
	{
		cnt=n;
		this.program=app;
	}
	public void displayBox()
	{
		if(cnt==1)
		{
			disp_box=new GRect(150,100,400,400);
			disp_box.setFillColor(Color.WHITE);
			disp_box.setFilled(true);
			tot_p = new GParagraph("Total Punches Attempted :"+ total_p,160,200);
			tot_p.setFont("Arial-24");
			tot_k = new GParagraph("Total Kicks Attempted :"+ total_k,160,280);
			tot_k.setFont("Arial-24");
			tot_h = new GParagraph("Total hits on Opponent :"+ total_h,160,340);
			tot_h.setFont("Arial-24");
			program.add(disp_box);
			program.add(tot_p);
			program.add(tot_k);
			program.add(tot_h);
		}
		if(cnt==2)
		{
			disp_box=new GRect(600,100,400,400);
			disp_box.setFillColor(Color.WHITE);
			disp_box.setFilled(true);
			tot_p = new GParagraph("Total Punches Attempted :"+ total_p,610,200);
			tot_p.setFont("Arial-24");
			tot_k = new GParagraph("Total Kicks Attempted :"+ total_k,610,280);
			tot_k.setFont("Arial-24");
			tot_h = new GParagraph("Total hits on Opponent :"+ total_h,610,340);
			tot_h.setFont("Arial-24");
			program.add(disp_box);
			program.add(tot_p);
			program.add(tot_k);
			program.add(tot_h);
		}
	}

	public void addTotal_p()
	{
		total_p++;
	}
	public void addTotal_k()
	{
		total_k++;
	}
	public void addTotal_h()
	{
		total_h++;
	}
}

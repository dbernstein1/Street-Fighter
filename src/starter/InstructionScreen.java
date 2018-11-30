package starter;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import acm.graphics.GImage;
import acm.graphics.GObject;
import acm.graphics.GRect;

public class InstructionScreen extends  GraphicsPane {
	private MainApplication program;
	private GParagraph P_Instruction;
	private GParagraph Pl_Instruction;
	private GParagraph Pl_Instruction2;
	private GParagraph K_Instruction;
	private GParagraph J_Instruction;
	private GParagraph D_Instruction;
	private GParagraph L_Instruction;
	private GParagraph R_Instruction;
	private GParagraph P_Button;
	private GParagraph K_Button;
	private GParagraph J_Button;
	private GParagraph D_Button;
	private GParagraph L_Button;
	private GParagraph R_Button;
	private GParagraph P_Button2;
	private GParagraph K_Button2;
	private GParagraph J_Button2;
	private GParagraph L_Button2;
	private GParagraph R_Button2;
	private GParagraph D_Button2;
	private GParagraph next;
	

	public InstructionScreen(MainApplication app) {
		this.program=app;
		Pl_Instruction = new GParagraph(" Player1 ",400,120);
		Pl_Instruction.setFont("Arial-24");
		Pl_Instruction2 = new GParagraph(" Player2 ",800,120);
		Pl_Instruction2.setFont("Arial-24");
		P_Instruction = new GParagraph(" Punch ",100,420);
		P_Instruction.setFont("Arial-24");
		K_Instruction = new GParagraph(" Kick ",100,480);
		K_Instruction.setFont("Arial-24");
		J_Instruction = new GParagraph(" Jump ",100,300);
		J_Instruction.setFont("Arial-24");
		D_Instruction = new GParagraph(" Duck ",100,360);
		D_Instruction.setFont("Arial-24");
		L_Instruction = new GParagraph(" Move Left ",100,180);
		L_Instruction.setFont("Arial-24");
		R_Instruction = new GParagraph(" Move Right ",100,240);
		R_Instruction.setFont("Arial-24");
		P_Button = new GParagraph(" P ",420,420);
		P_Button.setFont("Arial-24");
		K_Button = new GParagraph(" K ",420,480);
		K_Button.setFont("Arial-24");
		J_Button = new GParagraph(" W ",420,300);
		J_Button.setFont("Arial-24");
		D_Button = new GParagraph(" S ",420,360);
		D_Button.setFont("Arial-24");
		L_Button = new GParagraph(" A ",420,180);
		L_Button.setFont("Arial-24");
		R_Button = new GParagraph(" D ",420,240);
		R_Button.setFont("Arial-24");
		P_Button2 = new GParagraph(" SpaceBar ",780,420);
		P_Button2.setFont("Arial-24");
		K_Button2 = new GParagraph(" RCtrl ",780,480);
		K_Button2.setFont("Arial-24");
		J_Button2 = new GParagraph(" Up Arrow Key ",780,300);
		J_Button2.setFont("Arial-24");
		D_Button2 = new GParagraph(" Down Arrow Key ",780,360);
		D_Button2.setFont("Arial-24");
		L_Button2 = new GParagraph(" Left Arrow Key",780,180);
		L_Button2.setFont("Arial-24");
		R_Button2 = new GParagraph(" Right Arrow Key ",780,240);
		R_Button2.setFont("Arial-24");
		next = new GParagraph(" Press SpaceBar To Begin Fight ",360,540);
		next.setFont("Arial-24");
		
	
	}

	@Override
	public void showContents() {
		program.add(Pl_Instruction);
		program.add(Pl_Instruction2);
		program.add(P_Instruction);
		program.add(K_Instruction);
		program.add(J_Instruction);
		program.add(D_Instruction);
		program.add(L_Instruction);
		program.add(R_Instruction);
		program.add(P_Button);
		program.add(K_Button);
		program.add(J_Button);
		program.add(D_Button);
		program.add(L_Button);
		program.add(R_Button);
		program.add(P_Button2);
		program.add(K_Button2);
		program.add(J_Button2);
		program.add(D_Button2);
		program.add(L_Button2);
		program.add(R_Button2);
		program.add(next);
		
	}

	@Override
	public void hideContents() {
		program.remove(Pl_Instruction);
		program.remove(Pl_Instruction2);
		program.remove(P_Instruction);
		program.remove(K_Instruction);
		program.remove(J_Instruction);
		program.remove(D_Instruction);
		program.remove(L_Instruction);
		program.remove(R_Instruction);
		program.remove(P_Button);
		program.remove(K_Button);
		program.remove(J_Button);
		program.remove(D_Button);
		program.remove(L_Button);
		program.remove(R_Button);
		program.remove(P_Button2);
		program.remove(K_Button2);
		program.remove(J_Button2);
		program.remove(D_Button2);
		program.remove(L_Button2);
		program.remove(R_Button2);
		program.remove(next);
	}
	
	@Override
	public void keyPressed(KeyEvent e)
	{
		if(e.getKeyCode()==KeyEvent.VK_SPACE)
		{
			program.switchToGame();
		}
	}
	

}

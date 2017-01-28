package dragonball.view.panels;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class NewPlayerPanel extends MasterPanel {

	JLabel enter;
	JTextArea nameL;
	JLabel choose;
	JButton [] fighters;
	JLabel EnterFighterName;
	JTextArea fighterName;
	JLabel BackgroundL;
	
	public JLabel getEnter() {
		return enter;
	}

	public void setEnter(JLabel enter) {
		this.enter = enter;
	}

	public JTextArea getNameL() {
		return nameL;
	}

	public void setNameL(JTextArea name) {
		this.nameL = name;
	}

	public JLabel getChoose() {
		return choose;
	}

	public void setChoose(JLabel choose) {
		this.choose = choose;
	}

	public JButton[] getFighters() {
		return fighters;
	}

	public void setFighters(JButton[] fighters) {
		this.fighters = fighters;
	}

	public JLabel getEnterFighterName() {
		return EnterFighterName;
	}

	public void setEnterFighterName(JLabel enterFighterName) {
		EnterFighterName = enterFighterName;
	}

	public JTextArea getFighterName() {
		return fighterName;
	}

	public void setFighterName(JTextArea fighterName) {
		this.fighterName = fighterName;
	}

	public JLabel getBackgroundL() {
		return BackgroundL;
	}

	public void setBackgroundL(JLabel background) {
		BackgroundL = background;
	}

	
	public NewPlayerPanel (int W, int H){
		
		Font font = new Font("Times new Roman", Font.BOLD, 30);
		
		setLayout(null);
		
		BackgroundL=new JLabel();
		BackgroundL.setBounds(0,0,W,H);
		BackgroundL.setIcon(resize("NewPlayerBackground.jpg",W,H));
		
		
		enter = new JLabel("Please enter your name: ");
        enter.setBounds(W/16,H/8,W/2,H/32);
        enter.setFont(font);
		add(enter);
		
		nameL = new JTextArea();
        nameL.setBounds(W/2+W/16,H/8,W/8,H/20);
        nameL.setFont(font);
		add(nameL);
		
		EnterFighterName = new JLabel("Please enter fighter name: ");
		EnterFighterName.setBounds(W/16,H/4,W/2,H/32);
		EnterFighterName.setFont(font);
		add(EnterFighterName);
		
		fighterName = new JTextArea();
		fighterName.setBounds(W/2+W/16,H/4,W/8,H/20);
		fighterName.setFont(font);
		add(fighterName);
		
		choose = new JLabel("Choose your fighter");
        choose.setBounds(W/2-W/6,H/3+H/10,W/3,H/16);
        choose.setFont(font);
		add(choose);
		
		fighters= new JButton[5];
        	fighters[0]=new JButton("Earthling");
        	fighters[1]=new JButton("Frieza");
        	fighters[2]=new JButton("Majin");
        	fighters[3]=new JButton("Namekian");
        	fighters[4]=new JButton("Sayian");
        
        for(int i =0; i<5;i++){
            fighters[i].setBounds(W/16+(i*(W/8+W/16)),H/2,W/8,H/4);	
            fighters[i].setBorderPainted(false); 
            fighters[i].setContentAreaFilled(false); 
            fighters[i].setFocusPainted(false);
            fighters[i].setOpaque(false);
        	add(fighters[i]);
        }
       
        fighters[0].setIcon(resize("Earthling.png",W/8,H/4));
        fighters[1].setIcon(resize("Frieza.png",W/8,H/4));
        fighters[2].setIcon(resize("Majin.png",W/8,H/4));
        fighters[3].setIcon(resize("Namekian.png",W/8,H/4));
        fighters[4].setIcon(resize("Saiyan.png",W/8,H/4));


            
	}
}

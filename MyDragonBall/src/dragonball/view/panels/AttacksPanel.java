package dragonball.view.panels;

import java.awt.GridLayout;

import javax.swing.JButton;

import dragonball.model.character.fighter.PlayableFighter;

@SuppressWarnings("serial")
public class AttacksPanel extends MasterPanel {

	JButton attacks [];

	
	public AttacksPanel(int W, int H, PlayableFighter f){
		
		setBounds(0,H-H/16,W,H/16);
		attacks=new JButton[7];
		int UASize=f.getUltimateAttacks().size();
		int SASize=f.getSuperAttacks().size();
		setLayout(new GridLayout(1,7));
		
		for(int i=0;i<7;i++){
		  attacks[i]=new JButton();
		  add(attacks[i]);
		}
		
		int physical=50+f.getPhysicalDamage();
		int Blast=f.getBlastDamage();
		
		
		attacks[0].setText("Physical Atttack:"+physical);
		attacks[0].setActionCommand("pa");
		
		for(int i=1;i<SASize+1;i++){
			attacks[i].setText(f.getSuperAttacks().get(i-1).getName()+":"+(Blast+f.getSuperAttacks().get(i-1).getDamage()));
		    attacks[i].setActionCommand("sa"+(i-1));
		}
		for(int i=1+SASize;i<1+SASize+UASize;i++){
			attacks[i].setText(f.getSuperAttacks().get(i-1-SASize).getName()+":"+(Blast+f.getSuperAttacks().get(i-1-SASize).getDamage()));
		    attacks[i].setActionCommand("ua"+(i-(1+SASize)));
		}
		for(int i=1+SASize+UASize;i<7;i++)
			attacks[i].setVisible(false);
		
		
		
		
		
	}


	public JButton[] getAttacks() {
		return attacks;
	}


	public void setAttacks(JButton[] attacks) {
		this.attacks = attacks;
	}
	
	
}

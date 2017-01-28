package dragonball.view.panels;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;

import dragonball.model.character.fighter.PlayableFighter;

@SuppressWarnings("serial")
public class UpgradePanel extends MasterPanel {
	
	
	JButton Done;
	JButton HealthPoints;
	JButton PhysicalDamage;
	JButton BlastDamage;
	JButton Ki;
	JButton Stamina;
	JLabel AbilityPoints;
	ChooseOneFighterPanel FighterPanel;
	
	
	public UpgradePanel(int W,int H,PlayableFighter f) {
		
		
		Font font = new Font("Times new Roman", Font.BOLD, 25);
		
		setLayout(null);
		setBounds(0,0,W,H);
		
		AbilityPoints=new JLabel("Ability Points: "+f.getAbilityPoints());
		HealthPoints=new JButton("Add HealthPoints by 50");
		PhysicalDamage=new JButton("Add Physcial Damage by 50");
	    BlastDamage=new JButton("Add BlastDamage by 50");
	    Ki=new JButton("Add Ki by 1");
	    Stamina=new JButton("Add Stamina by 1");
	    FighterPanel=new ChooseOneFighterPanel(W/5,H-H/10,f);
		Done=new JButton("Done");
	    
		
	    int x=W-W/5;
	    
	    FighterPanel.setBounds(0,0,W/5,H-H/10);
		AbilityPoints.setBounds(W/5+x/2-x/8,H/4,x/4,H/16);
		HealthPoints.setBounds(W/5,H/2,x/5,H/16);
		PhysicalDamage.setBounds(W/5+x/5,H/2,x/5,H/16);
		BlastDamage.setBounds(W/5+2*x/5,H/2,x/5,H/16);
		Ki.setBounds(W/5+3*x/5,H/2,x/5,H/16);
		Stamina.setBounds(W/5+4*x/5,H/2,x/5,H/16);
		Done.setBounds(W/5+x/2-x/4,H-H/8,x/2,H/16);
		
	    
	    AbilityPoints.setFont(font);
	    
	    
		add(AbilityPoints);
		add(HealthPoints);
		add(PhysicalDamage);
		add(BlastDamage);
		add(Ki);
		add(Stamina);
		add(FighterPanel);
		add(Done);
		
		
	}
	
	public JButton getDone() {
		return Done;
	}
	public void setDone(JButton done) {
		Done = done;
	}
	public JButton getHealthPoints() {
		return HealthPoints;
	}
	public void setHealthPoints(JButton healthPoints) {
		HealthPoints = healthPoints;
	}
	public JButton getPhysicalDamage() {
		return PhysicalDamage;
	}
	public void setPhysicalDamage(JButton physicalDamage) {
		PhysicalDamage = physicalDamage;
	}
	public JButton getBlastDamage() {
		return BlastDamage;
	}
	public void setBlastDamage(JButton blastDamage) {
		BlastDamage = blastDamage;
	}
	public JButton getKi() {
		return Ki;
	}
	public void setKi(JButton ki) {
		Ki = ki;
	}
	public JButton getStamina() {
		return Stamina;
	}
	public void setStamina(JButton stamina) {
		Stamina = stamina;
	}
	public JLabel getAbilityPoints() {
		return AbilityPoints;
	}

	public void setAbilityPoints(JLabel abilityPoints) {
		AbilityPoints = abilityPoints;
	}

	public ChooseOneFighterPanel getFighterPanel() {
		return FighterPanel;
	}

	public void setFighterPanel(ChooseOneFighterPanel fighterPanel) {
		FighterPanel = fighterPanel;
	}
	
	
	
	
	
	
	
	

}

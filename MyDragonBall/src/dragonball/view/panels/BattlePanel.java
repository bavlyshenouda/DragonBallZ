package dragonball.view.panels;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;

import dragonball.model.battle.Battle;
import dragonball.model.character.fighter.Fighter;
import dragonball.model.character.fighter.NonPlayableFighter;
import dragonball.model.character.fighter.PlayableFighter;
import dragonball.model.exceptions.NotEnoughKiException;

@SuppressWarnings("serial")
public class BattlePanel extends MasterPanel {
   Battle b;
   JLabel meName;
   JLabel foeName;
   JLabel mehp;
   JLabel foehp;
   JLabel meKi;
   JLabel foeKi;
   JLabel meLevel;
   JLabel foeLevel;
   JLabel Me;
   JLabel backgroundImage;
   JLabel state;
   JLabel turn;
   AttacksPanel attacksPanel;
   JButton use;
   JButton block;
   int H;
   int W;
   
   public BattlePanel(int W,int H,Battle b){
	
	this.H=H;
	this.W=W;
	this.b=b;      
	setBounds(0,0,W,H);
	setLayout(null);
	meName = new JLabel();
	foeName=new JLabel();
	mehp=new JLabel();
	foehp=new JLabel();
	meKi=new JLabel();
	foeKi=new JLabel();
	meLevel=new JLabel();
	foeLevel=new JLabel();
	Me=new JLabel();
	state= new JLabel();

	
	   
	PlayableFighter me=(PlayableFighter)b.getMe();  
	NonPlayableFighter foe=(NonPlayableFighter)b.getFoe();  
	
	turn=new JLabel(me.getName()+" Turn");
	
	Font font = new Font("Times new Roman", Font.BOLD, 20);
	meName.setText(me.getName()+"  Level: " +me.getLevel());
	meName.setBounds(W/32,H/16,W,H/14);
	meName.setFont(font);
	foeName.setText(foe.getName());
	foeName.setBounds(W/3+W/24,H/16,W,H/14);
	foeName.setFont(font);
	mehp.setText("Health: " + me.getHealthPoints()+"/" + me.getMaxHealthPoints());
	mehp.setBounds(W/32,H/16+H/24,W, H/14);
	mehp.setFont(font);
	foehp.setText("Health: " + foe.getHealthPoints()+"/" + foe.getMaxHealthPoints());
	foehp.setBounds(W/3+W/24,H/16+H/24,W, H/14);
	foehp.setFont(font);
	meKi.setText("Ki: "+me.getKi()+"/"+me.getMaxKi()+"  Stamina: "+me.getStamina()+"/"+me.getMaxStamina());
	meKi.setBounds(W/32,H/16+H/14,W,H/14);
	meKi.setFont(font);
	foeKi.setText("Ki: "+foe.getKi()+"/"+foe.getMaxKi()+"  Stamina: "+foe.getStamina()+"/"+foe.getMaxStamina());
	foeKi.setBounds(W/3+W/24,H/16+H/14,W,H/14);
	foeKi.setFont(font);
	String s = me.getClass().getName();
    s = s.substring(s.lastIndexOf('.') + 1);
    s=s+".png";
	Me.setIcon(resize(s,W/8,H/3));
	Me.setBounds(W/10,H/3-H/10, W/8, H/3);
	font = new Font("Times new Roman", Font.BOLD, 35);
	
	state.setFont(font);
	state.setForeground(Color.BLUE);
	state.setBounds(0,H-H/4,W/2,H/16);
	
	turn.setFont(font);
	turn.setForeground(Color.BLUE);
	turn.setBounds(W/2+W/8,H-H/4,W/2,H/16);
	
	use=new JButton("use");
	block=new JButton("block");
	
	backgroundImage=new JLabel();
	backgroundImage.setBounds(0,0,W,H);
	backgroundImage.setIcon(resize("BattleBackground.jpg",W,H));
	attacksPanel=new AttacksPanel(W,H,me);
	
	use.setBounds(0,H-H/8,W/16,H/16);
	block.setBounds(W/16,H-H/8,W/16,H/16);
	
	
	add(Me);
	add(state);
	add(turn);
	add(meName);
	add(foeName);
	add(mehp);
	add(foehp);
	add(meKi);
	add(foeKi);
	add(use);
	add(block);
	add(attacksPanel);   
	   
   }

public JLabel getBackgroundImage() {
	return backgroundImage;
}

public void setBackgroundImage(JLabel background) {
	this.backgroundImage = background;
}

public AttacksPanel getAttacksPanel() {
	return attacksPanel;
}

public void setAttacksPanel(AttacksPanel attacksPanel) {
	this.attacksPanel = attacksPanel;
}

public JButton getUse() {
	return use;
}

public void setUse(JButton use) {
	this.use = use;
}

public JButton getBlock() {
	return block;
}

public void setBlock(JButton block) {
	this.block = block;
}


public Battle getB() {
	return b;
}

public void setB(Battle b) {
	this.b = b;
}

public JLabel getState() {
	return state;
}

public void setState(JLabel state) {
	this.state = state;
}

public void update(){
	
	PlayableFighter me=(PlayableFighter)b.getMe();  

	
	while((Fighter)b.getAttacker()==b.getFoe() && me.getHealthPoints()>0 ){
		try {
			b.play();
		} catch (NotEnoughKiException e) {

		}		
	} 
    change();
	

}

public JLabel getTurn() {
	return turn;
}

public void setTurn(JLabel turn) {
	this.turn = turn;
}

  public void change(){
		PlayableFighter me=(PlayableFighter)b.getMe();  
		NonPlayableFighter foe=(NonPlayableFighter)b.getFoe();  
		mehp.setText("Health: " + me.getHealthPoints()+"/" + me.getMaxHealthPoints());
		foehp.setText("Health: " + foe.getHealthPoints()+"/" + foe.getMaxHealthPoints());
		meKi.setText("Ki: "+me.getKi()+"/"+me.getMaxKi()+"  Stamina: "+me.getStamina()+"/"+me.getMaxStamina());
		foeKi.setText("Ki: "+foe.getKi()+"/"+foe.getMaxKi()+"  Stamina: "+foe.getStamina()+"/"+foe.getMaxStamina());
	
	
  }




}

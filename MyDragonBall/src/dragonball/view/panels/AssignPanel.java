package dragonball.view.panels;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import dragonball.model.character.fighter.PlayableFighter;
import dragonball.model.player.Player;

@SuppressWarnings("serial")
public class AssignPanel extends MasterPanel {
	
	JButton add;
	JButton replace;
	JButton super1;
	JButton ultimate;
	JButton done;
	boolean added;
	JMenuBar menuBar;
	
	Player p;
	PlayableFighter f;
	

    JMenu playerSuper;
	JMenu fighterSuper;
	JMenu playerUltimate;
	JMenu fighterUltimate;
	JMenuItem playerS[];
	JMenuItem fighterS[];
	JMenuItem playerU[];
	JMenuItem fighterU[];
	
	int W;
	int H;
	
	JLabel playerAttack;
	JLabel fighterAttack;

		
	public AssignPanel(int W,int H,Player p,PlayableFighter f){
		
		setLayout(null);
		setBounds(0,0,W,H);
		
		this.p=p;
		this.f=f;
		this.W=W;
		this.H=H;
		
		add=new JButton("add");
		replace=new JButton("replace");
		
		super1=new JButton("Super Attacks");
		super1.setVisible(false);
		ultimate=new JButton("Ultimate Attacks");
		ultimate.setVisible(false);
		
   		done=new JButton("Done");
   		
   		playerAttack=new JLabel("");
   		fighterAttack=new JLabel("");
   		
   		add.setBounds(W/32,H/8,W/8,H/16);
   		replace.setBounds(W/32,H/2,W/8,H/16);
   		done.setBounds(W/2-W/16,H-H/8,W/8,H/16);
   		
   		menuBar=new JMenuBar();
   		menuBar.setBounds(W/2,H/4,W/2-W/16,H/8);
   		menuBar.setVisible(false);
  		menuBar.setLayout(new GridLayout(1,4));
  		
  		playerAttack.setBounds(W/2,H/8,W/4,H/8);
  		fighterAttack.setBounds(W/2+W/4,H/8,W/4,H/8);

   		playerSuper=new JMenu("player super");
   		fighterSuper=new JMenu("fighter super");
   		
   		playerUltimate=new JMenu("player ultimate");
   		fighterUltimate=new JMenu("fighter ultimate");
   		
   	   	playerSuper.setVisible(false);	
   		
   		int n=p.getSuperAttacks().size();
   		playerS=new JMenuItem[n];
   		
   		for(int i=0;i<n;i++){
   		playerS[i]=new JMenuItem(p.getSuperAttacks().get(i).getName()+"--"+p.getSuperAttacks().get(i).getDamage());
   		playerS[i].setActionCommand("s"+i);
   		playerSuper.add(playerS[i]);	
   		}
   		
   		
   		n=p.getUltimateAttacks().size();
   		playerU=new JMenuItem[n];
   		
   		for(int i=0;i<n;i++){
   		playerU[i]=new JMenuItem(p.getUltimateAttacks().get(i).getName()+"--"+p.getUltimateAttacks().get(i).getDamage());
   		playerU[i].setActionCommand("u"+i);
   		playerUltimate.add(playerS[i]);	
   		}
   		
   		
   		n=f.getSuperAttacks().size();
   		fighterS=new JMenuItem[n];
   		
   		for(int i=0;i<n;i++){
   		fighterS[i]=new JMenuItem(f.getSuperAttacks().get(i).getName()+"--"+f.getSuperAttacks().get(i).getDamage());
   		fighterS[i].setActionCommand("-s"+i);
   		fighterSuper.add(fighterS[i]);	
   		}
   		
   		
   		n=f.getUltimateAttacks().size();
   		fighterU=new JMenuItem[n];
   		
   		for(int i=0;i<n;i++){
   		fighterU[i]=new JMenuItem(f.getUltimateAttacks().get(i).getName()+"--"+f.getUltimateAttacks().get(i).getDamage());
   		fighterU[i].setActionCommand("-u"+i);
   		fighterUltimate.add(fighterU[i]);	
   		}
   	
 
   				
   		menuBar.add(playerSuper);
   		menuBar.add(fighterSuper);
   		menuBar.add(playerUltimate);
   		menuBar.add(fighterUltimate);
   		
   		
   		add(add);
   		add(replace);
   		add(super1);
   		add(ultimate);
   		add(done);
   		add(menuBar);
	    add(playerAttack);
	    add(fighterAttack);
	
	}
	


	public JButton getAdd() {
		return add;
	}


	public void setAdd(JButton add) {
		this.add = add;
	}


	public JButton getReplace() {
		return replace;
	}


	public void setReplace(JButton replace) {
		this.replace = replace;
	}


	public JButton getSuper1() {
		return super1;
	}


	public void setSuper1(JButton super1) {
		this.super1 = super1;
	}


	public JButton getUltimate() {
		return ultimate;
	}


	public void setUltimate(JButton ultimate) {
		this.ultimate = ultimate;
	}


	public JButton getDone() {
		return done;
	}


	public void setDone(JButton done) {
		this.done = done;
	}


	public boolean isAdded() {
		return added;
	}


	public void setAdded(boolean added) {
		this.added = added;
	}

	public JMenuBar getMenuBar() {
		return menuBar;
	}


	public void setMenuBar(JMenuBar menuBar) {
		this.menuBar = menuBar;
	}
	
	public JMenu getPlayerSuper() {
		return playerSuper;
	}


	public void setPlayerSuper(JMenu playerSuper) {
		this.playerSuper = playerSuper;
	}


	public JMenu getFighterSuper() {
		return fighterSuper;
	}


	public void setFighterSuper(JMenu fighterSuper) {
		this.fighterSuper = fighterSuper;
	}


	public JMenu getPlayerUltimate() {
		return playerUltimate;
	}


	public void setPlayerUltimate(JMenu playerUltimate) {
		this.playerUltimate = playerUltimate;
	}


	public JMenu getFighterUltimate() {
		return fighterUltimate;
	}


	public void setFighterUltimate(JMenu fighterUltimate) {
		this.fighterUltimate = fighterUltimate;
	}


	public JMenuItem[] getPlayerS() {
		return playerS;
	}


	public void setPlayerS(JMenuItem[] playerS) {
		this.playerS = playerS;
	}


	public JMenuItem[] getFighterS() {
		return fighterS;
	}


	public void setFighterS(JMenuItem[] fighterS) {
		this.fighterS = fighterS;
	}


	public JMenuItem[] getPlayerU() {
		return playerU;
	}


	public void setPlayerU(JMenuItem[] playerU) {
		this.playerU = playerU;
	}


	public JMenuItem[] getFighterU() {
		return fighterU;
	}


	public void setFighterU(JMenuItem[] fighterU) {
		this.fighterU = fighterU;
	}
	

	public JLabel getPlayerAttack() {
		return playerAttack;
	}



	public void setPlayerAttack(JLabel playerAttack) {
		this.playerAttack = playerAttack;
	}



	public JLabel getFighterAttack() {
		return fighterAttack;
	}



	public void setFighterAttack(JLabel fighterAttack) {
		this.fighterAttack = fighterAttack;
	}
   

	public void SA() {
		playerUltimate.setVisible(false);
        fighterUltimate.setVisible(false);
        playerSuper.setVisible(true);

        
        if(added)
        	fighterSuper.setVisible(false);
        else
        	fighterSuper.setVisible(true);
        
        playerAttack.setText("");
        fighterAttack.setText("");
	}


	public void UA() {
		playerSuper.setVisible(false);
		fighterSuper.setVisible(false);
        playerUltimate.setVisible(true);

        
        if(added)
        	fighterUltimate.setVisible(false);
        else
        	fighterUltimate.setVisible(true);
        
        playerAttack.setText("");
        fighterAttack.setText("");	
		
	}
	
	
	
	
	
	

}

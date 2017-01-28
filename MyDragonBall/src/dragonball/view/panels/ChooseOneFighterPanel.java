package dragonball.view.panels;

import java.awt.GridLayout;

import javax.swing.JButton;

import dragonball.model.character.fighter.PlayableFighter;

@SuppressWarnings("serial")
public class ChooseOneFighterPanel extends MasterPanel {

	FighterPanel fighterPanel;
	JButton fighterImage;

    public ChooseOneFighterPanel(int W,int H,PlayableFighter f){
    	
    	
    setLayout(new GridLayout(2,0));
    fighterPanel=new FighterPanel(f);
    
    String s = f.getClass().getName();
    s = s.substring(s.lastIndexOf('.') + 1);
    s=s+".png";
    
    fighterImage=new JButton();
    fighterImage.setIcon(resize(s,W,H/2));
    fighterImage.setContentAreaFilled(false);
    fighterImage.setBorderPainted(false); 
    fighterImage.setFocusPainted(false);
    fighterImage.setFocusable(false);
    
    add(fighterImage);
    add(fighterPanel);

    	
    	
    	
    }

	public FighterPanel getFighterPanel() {
		return fighterPanel;
	}

	public void setFighterPanel(FighterPanel fighterPanel) {
		this.fighterPanel = fighterPanel;
	}

	public JButton getFighterImage() {
		return fighterImage;
	}

	public void setFighterImage(JButton fighterImage) {
		this.fighterImage = fighterImage;
	}







}

package dragonball.view.panels;

import javax.swing.JLabel;

import dragonball.model.character.fighter.PlayableFighter;
import dragonball.model.player.Player;

@SuppressWarnings("serial")

public class WorldPanel extends MasterPanel {
	
 MapPanel mapPanel;
 FighterPanel fighterPanel;
 PlayerPanel playerPanel;
 JLabel pic;
    
    public WorldPanel(Player p,PlayableFighter f,int W,int H){
    	
    setLayout(null);
    
    mapPanel=new MapPanel(f,H/10);
    mapPanel.setBounds((W-H)/2,0,H,H);
    
    fighterPanel=new FighterPanel(f);
    fighterPanel.setBounds( (W-H)/2 +H,W-((W-H)/2 +H)+H/8,W-((W-H)/2 +H),H-(W-((W-H)/2 +H))-H/8 );
    add(fighterPanel);
    
    pic=new JLabel();
    String s = f.getClass().getName();
    s = s.substring(s.lastIndexOf('.') + 1);
    s=s+".png";
    pic.setBounds( ((W-H)/2 +H),H/8,W-((W-H)/2 +H),W-((W-H)/2 +H));
    pic.setIcon(resize( s,W-((W-H)/2 +H),W-((W-H)/2 +H) ) );
    
    playerPanel=new PlayerPanel(p);
    playerPanel.setBounds(0,0,(W-H)/2,H-H/8);
    add(playerPanel);
    add(pic);
    
    	    
    }

	public MapPanel getMapPanel() {
		return mapPanel;
	}

	public void setMapPanel(MapPanel mapPanel) {
		this.mapPanel = mapPanel;
	}

	public FighterPanel getFighterPanel() {
		return fighterPanel;
	}

	public void setFighterPanel(FighterPanel fighterPanel) {
		this.fighterPanel = fighterPanel;
	}

	public PlayerPanel getPlayerPanel() {
		return playerPanel;
	}

	public void setPlayerPanel(PlayerPanel playerPanel) {
		this.playerPanel = playerPanel;
	}

	public JLabel getPic() {
		return pic;
	}

	public void setPic(JLabel pic) {
		this.pic = pic;
	}
	
	public void changeIcon(String s,int W,int H){
	    pic.setIcon(resize(s,W-((W-H)/2 +H),W-((W-H)/2 +H) ) );		
	}
	



}

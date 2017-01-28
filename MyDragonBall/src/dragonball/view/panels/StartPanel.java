package dragonball.view.panels;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JLabel;

public class StartPanel extends MasterPanel {

	private static final long serialVersionUID = 1L;
    private JButton newGame;
    private JButton loadGame;
    private JLabel BackgroundL;
	
	public StartPanel(int W,int H){
		
	Font font = new Font("Times new Roman", Font.BOLD, 30);
		
	newGame=new JButton("New Game");
	loadGame=new JButton("Load Last Game");
	
	setLayout(null);
	
	
	newGame.setBounds(W/16,H-H/4,W/6,H/16);
	loadGame.setBounds(W-W/4,H-H/4,W/6,H/16);
	
	
	newGame.setFont(font);
	loadGame.setFont(font);
	
	newGame.setForeground(Color.BLUE);
	
	loadGame.setForeground(Color.BLUE);
	
	 BackgroundL=new JLabel();
	 BackgroundL.setBounds(0,0,W,H);
	 BackgroundL.setIcon(resize("StartBackGround.jpg",W,H));
	 

	add(newGame);
	add(loadGame);
	//add(BackgroundL);
	
	
	}
	
    public JButton getNewGame() {
		return newGame;
	}
	public JButton getLoadGame() {
		return loadGame;
	}

	public JLabel getBackgroundL() {
		return BackgroundL;
	}
	

    


}

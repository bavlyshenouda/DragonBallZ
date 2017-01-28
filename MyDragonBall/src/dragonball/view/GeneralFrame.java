package dragonball.view;


import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

import dragonball.view.panels.AssignPanel;
import dragonball.view.panels.BattlePanel;
import dragonball.view.panels.ChooseFighterPanel;
import dragonball.view.panels.CollectPanel;
import dragonball.view.panels.DialogPanel;
import dragonball.view.panels.DragonPanel;
import dragonball.view.panels.ExceptPanel;
import dragonball.view.panels.NewPlayerPanel;
import dragonball.view.panels.StartPanel;
import dragonball.view.panels.UpgradePanel;
import dragonball.view.panels.WorldPanel;

@SuppressWarnings("serial")
public class GeneralFrame extends JFrame {


	StartPanel startPanel;
	NewPlayerPanel newPlayerPanel ;
    WorldPanel worldPanel;
    DialogPanel dialogPanel;
    ChooseFighterPanel chooseFighterPanel;
    ExceptPanel exceptPanel;
    UpgradePanel upgradePanel;
    BattlePanel battlePanel;
    AssignPanel assignPanel;
    DragonPanel dragonPanel;
    CollectPanel collectPanel;
    JButton music;
    JButton exit;
    ImageIcon musicOn;
    ImageIcon musicOff;
    

public GeneralFrame(){
	
	 Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	 int W =(int) screenSize.getWidth();
	 int H =(int) screenSize.getHeight();

	 setSize(W,H);
	 setResizable(false);
	 setUndecorated(true);
	 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	 
	 
	 setLayout(null);
	 
	 music=new JButton("music");
	 exit=new JButton("exit");
	 
	 exit.setBounds(W-W/32,0,W/32,H/16);
	 music.setBounds(W-W/16,0,W/32,H/16);
	 
	 exit.setIcon(resize("Close.png",W/32,H/16));	 
	 musicOn=resize("MusicOn.png",W/32,H/16);
	 musicOff=resize("MusicOff.png",W/32,H/16);	 
	 music.setIcon(musicOn);
	 
	 exit.setBorderPainted(false); 
	 exit.setContentAreaFilled(false); 
	 exit.setFocusPainted(false);
	 exit.setOpaque(false);
	 
	 music.setBorderPainted(false); 
	 music.setContentAreaFilled(false); 
	 music.setFocusPainted(false);
	 music.setOpaque(false);
	 
	 music.setFocusable(false);
	 exit.setFocusable(false);
	 
	 
	 startPanel=new StartPanel(W,H);
	 newPlayerPanel=new NewPlayerPanel(W,H);
	 
	 startPanel.setBounds(0,0,W,H);
	 newPlayerPanel.setBounds(0,0,W,H);

	 
	 dialogPanel=new DialogPanel(W,H);
	 dialogPanel.setVisible(false);
 
	 
	 exceptPanel=new ExceptPanel(W,H);
	 exceptPanel.setVisible(true);
	 
     collectPanel=new CollectPanel(W,H);
     collectPanel.setVisible(false);
	 
	 add(startPanel);
	 add(newPlayerPanel);
	 add(dialogPanel);
	 add(exceptPanel);
	 add(collectPanel);
	 exceptPanel.setVisible(false);
	 newPlayerPanel.setVisible(false);
	 setVisible(true);	

}


public StartPanel getStartPanel() {
	return startPanel;
}

public void setStartPanel(StartPanel startPanel) {
	this.startPanel = startPanel;
}

public NewPlayerPanel getNewPlayerPanel() {
	return newPlayerPanel;
}

public void setNewPlayerPanel(NewPlayerPanel newPlayerPanel) {
	this.newPlayerPanel = newPlayerPanel;
}

public WorldPanel getWorldPanel() {
	return worldPanel;
}

public void setWorldPanel(WorldPanel worldPanel) {
	this.worldPanel = worldPanel;
}


public DialogPanel getDialogPanel() {
	return dialogPanel;
}

public void setDialogPanel(DialogPanel dialogPanel) {
	this.dialogPanel = dialogPanel;
}

public ChooseFighterPanel getChooseFighterPanel() {
	return chooseFighterPanel;
}

public void setChooseFighterPanel(ChooseFighterPanel chooseFighterPanel) {
	this.chooseFighterPanel = chooseFighterPanel;
}

public ExceptPanel getExceptPanel() {
	return exceptPanel;
}

public void setExceptPanel(ExceptPanel exceptPanel) {
	this.exceptPanel = exceptPanel;
}


public UpgradePanel getUpgradePanel() {
	return upgradePanel;
}

public void setUpgradePanel(UpgradePanel upgradePanel) {
	this.upgradePanel = upgradePanel;
}

public BattlePanel getBattlePanel() {
	return battlePanel;
}

public void setBattlePanel(BattlePanel battlePanel) {
	this.battlePanel = battlePanel;
}	
public JButton getMusic() {
	return music;
}

public void setMusic(JButton music) {
	this.music = music;
}

public JButton getExit() {
	return exit;
}

public void setExit(JButton exit) {
	this.exit = exit;
}
public DragonPanel getDragonPanel() {
	return dragonPanel;
}

public void setDragonPanel(DragonPanel dragonPanel) {
	this.dragonPanel = dragonPanel;
}
public AssignPanel getAssignPanel() {
	return assignPanel;
}

public void setAssignPanel(AssignPanel assignPanel) {
	this.assignPanel = assignPanel;
}


public CollectPanel getCollectPanel() {
	return collectPanel;
}


public void setCollectPanel(CollectPanel collectPanel) {
	this.collectPanel = collectPanel;
}

public static ImageIcon resize(String s,int width, int height) {
	
	BufferedImage image = null;
	
     try {
	   image = ImageIO.read(new File (s) );
	} catch (IOException e) {
		e.printStackTrace();
	}

    BufferedImage bi = new BufferedImage(width, height, BufferedImage.TRANSLUCENT);
    Graphics2D g2d = (Graphics2D) bi.createGraphics();
    g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));
    g2d.drawImage(image, 0, 0, width, height, null);
    g2d.dispose();
    return new ImageIcon(bi);
}


public ImageIcon getMusicOn() {
	return musicOn;
}


public void setMusicOn(ImageIcon musicOn) {
	this.musicOn = musicOn;
}


public ImageIcon getMusicOff() {
	return musicOff;
}


public void setMusicOff(ImageIcon musicOff) {
	this.musicOff = musicOff;
}











}

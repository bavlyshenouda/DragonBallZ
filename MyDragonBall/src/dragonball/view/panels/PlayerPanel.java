package dragonball.view.panels;

import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;

import dragonball.model.player.Player;

@SuppressWarnings("serial")
public class PlayerPanel extends MasterPanel {
	JButton New;
	JButton Save;
	JButton load;
	JButton NewCharacter;
	JButton ChooseFigther;
	JButton AssAttack;
	JButton Upgrade;
	JLabel NameL;
	JLabel SB;
	JLabel DB;
	JLabel Map;
	Player me;
	
public PlayerPanel(Player me){
	Font font = new Font("Times new Roman", Font.BOLD, 25);
	this.me=me;
	New = new JButton("Start New Game");
	New.setFocusable(false);
	New.setFont(font);
	Save = new JButton("Save Game");
	Save.setFocusable(false);
	Save.setFont(font);
	load = new JButton("load last Game");
	load.setFocusable(false);
	load.setFont(font);
	NewCharacter = new JButton("Create new fighter");
	NewCharacter.setFocusable(false);
	NewCharacter.setFont(font);
	ChooseFigther = new JButton("Change active fighter");
	ChooseFigther.setFocusable(false);
	ChooseFigther.setFont(font);
	AssAttack = new JButton("Assign attacks");
	AssAttack.setFocusable(false);
	AssAttack.setFont(font);
	Upgrade = new JButton("Upgrade fighter");
	Upgrade.setFocusable(false);
	Upgrade.setFont(font);
	setLayout(new GridLayout(11,0));
	
	String temp = "Player Name: " + me.getName();
	NameL = new JLabel(temp);
	NameL.setFont(font);
	temp = "Maps Finished: " + me.getExploredMaps();
	Map = new JLabel(temp);
	Map.setFont(font);
	temp = "Senzu Beans: " + me.getSenzuBeans();
	SB = new JLabel(temp);
	SB.setFont(font);
	temp = "Dragon Balls: "+ me.getDragonBalls();
	DB = new JLabel(temp);
	DB.setFont(font);

	add(New);
	add(Save);
	add(load);
	add(NewCharacter);
	add(ChooseFigther);
	add(AssAttack);
	add(Upgrade);
	add(NameL);
	add(Map);
	add(SB);
	add(DB);	
}

public JButton getNewCharacter() {
	return NewCharacter;
}

public void setNewCharacter(JButton newCharacter) {
	NewCharacter = newCharacter;
}

public JButton getSave() {
	return Save;
}

public void setSave(JButton save) {
	Save = save;
}


public JButton getNew() {
	return New;
}

public void setNew(JButton new1) {
	New = new1;
}

public JButton getLoad() {
	return load;
}

public void setLoad(JButton load) {
	this.load = load;
}

public JButton getChooseFigther() {
	return ChooseFigther;
}

public void setChooseFigther(JButton chooseFigther) {
	ChooseFigther = chooseFigther;
}

public JButton getAssAttack() {
	return AssAttack;
}

public void setAssAttack(JButton assAttack) {
	AssAttack = assAttack;
}

public JButton getUpgrade() {
	return Upgrade;
}

public void setUpgrade(JButton upgrade) {
	Upgrade = upgrade;
}

public JLabel getNameL() {
	return NameL;
}

public void setNameL(JLabel name) {
	NameL = name;
}

public JLabel getSB() {
	return SB;
}

public void setSB(JLabel sB) {
	SB = sB;
}

public JLabel getDB() {
	return DB;
}

public void setDB(JLabel dB) {
	DB = dB;
}

public JLabel getMap() {
	return Map;
}

public void setMap(JLabel map) {
	Map = map;
}

public Player getMe() {
	return me;
}

public void setMe(Player me) {
	this.me = me;
}
	
	

}

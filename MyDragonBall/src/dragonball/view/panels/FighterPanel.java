package dragonball.view.panels;

import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JLabel;

import dragonball.model.character.fighter.PlayableFighter;

@SuppressWarnings("serial")
public class FighterPanel extends MasterPanel {

    
    PlayableFighter fighter;
    JLabel nameFighter;
    JLabel xp;
    JLabel targetXp;
    JLabel AbilityPoints;
    JLabel level;
    JLabel maxHealthPoints;
    JLabel maxKi;
    JLabel maxStamina;
    JLabel physicalDamage;
    JLabel blastDamage;


public FighterPanel(PlayableFighter fighter){
	
   this.fighter=fighter;
	
  setLayout(new GridLayout(10,0));
  
  nameFighter=new JLabel("Name: "+fighter.getName());
  xp=new JLabel("XP: "+fighter.getXp());
  targetXp=new JLabel("Target Xp: "+fighter.getTargetXp());
  AbilityPoints=new JLabel("Ability Points: "+fighter.getAbilityPoints());
  level=new JLabel("Level: "+fighter.getLevel());
  maxHealthPoints=new JLabel("Health Points: "+fighter.getMaxHealthPoints());
  maxKi=new JLabel("Ki: "+fighter.getMaxKi());
  maxStamina=new JLabel("Stamina: "+fighter.getMaxStamina());
  physicalDamage=new JLabel("physicalDamage: "+fighter.getPhysicalDamage());
  blastDamage=new JLabel("Blast Damage :"+fighter.getBlastDamage());
  
  Font font = new Font("Times new Roman", Font.BOLD, 25);
  
  nameFighter.setFont(font);
  xp.setFont(font);
  targetXp.setFont(font);
  AbilityPoints.setFont(font);
  level.setFont(font);
  maxHealthPoints.setFont(font);
  maxKi.setFont(font);
  maxStamina.setFont(font);
  physicalDamage.setFont(font);
  blastDamage.setFont(font);
  
  
  add(nameFighter);
  add(level);
  add(xp);
  add(targetXp);
  add(AbilityPoints);
  add(maxHealthPoints);
  add(maxKi);
  add(maxStamina);
  add(physicalDamage);
  add(blastDamage);
  
  
   
}


public PlayableFighter getFighter() {
	return fighter;
}


public void setFighter(PlayableFighter fighter) {
	this.fighter = fighter;
}


public JLabel getNameFighter() {
	return nameFighter;
}


public void setNameFighter(JLabel name) {
	this.nameFighter = name;
}


public JLabel getXp() {
	return xp;
}


public void setXp(JLabel xp) {
	this.xp = xp;
}


public JLabel getTargetXp() {
	return targetXp;
}


public void setTargetXp(JLabel targetXp) {
	this.targetXp = targetXp;
}


public JLabel getAbilityPoints() {
	return AbilityPoints;
}


public void setAbilityPoints(JLabel abilityPoints) {
	AbilityPoints = abilityPoints;
}


public JLabel getLevel() {
	return level;
}


public void setLevel(JLabel level) {
	this.level = level;
}


public JLabel getMaxHealthPoints() {
	return maxHealthPoints;
}


public void setMaxHealthPoints(JLabel maxHealthPoints) {
	this.maxHealthPoints = maxHealthPoints;
}


public JLabel getMaxKi() {
	return maxKi;
}


public void setMaxKi(JLabel maxKi) {
	this.maxKi = maxKi;
}


public JLabel getMaxStamina() {
	return maxStamina;
}


public void setMaxStamina(JLabel maxStamina) {
	this.maxStamina = maxStamina;
}


public JLabel getPhysicalDamage() {
	return physicalDamage;
}


public void setPhysicalDamage(JLabel physicalDamage) {
	this.physicalDamage = physicalDamage;
}


public JLabel getBlastDamage() {
	return blastDamage;
}


public void setBlastDamage(JLabel blastDamage) {
	this.blastDamage = blastDamage;
}








}

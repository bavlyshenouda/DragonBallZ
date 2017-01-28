package dragonball.model.character.fighter;

import java.util.ArrayList;

import dragonball.model.attack.SuperAttack;
import dragonball.model.attack.UltimateAttack;

@SuppressWarnings("serial")
public class Namekian extends PlayableFighter {
	
	public Namekian (String name){
		
		
		super(name , 1350 ,0,50,3,5,new ArrayList<SuperAttack>(),new ArrayList<UltimateAttack>());

	}

	public Namekian(String name, int level, int xp, int targetXp, int maxHealthPoints,
	     int blastDamage, int physicalDamage, int abilityPoints, int maxKi, int maxStamina,
	     ArrayList<SuperAttack> superAttacks, ArrayList<UltimateAttack> ultimateAttacks){
		
		
	   super(name, level, xp,targetXp, maxHealthPoints,blastDamage, physicalDamage, abilityPoints, maxKi,maxStamina,
			      superAttacks, ultimateAttacks);
			
	}
	@Override
	public void onAttackerTurn() {
	    
		if(getStamina()<getMaxStamina())
	    	setStamina(getStamina()+1);
		
	    if(getHealthPoints()+50<getMaxHealthPoints())
	    	setHealthPoints(getHealthPoints()+50);
	    else
	    	setHealthPoints(getMaxHealthPoints());
		
	}

	@Override
	public void onDefenderTurn() {
		
		if(getStamina()<getMaxStamina())
	    	setStamina(getStamina()+1);
		
	    if(getHealthPoints()+50<getMaxHealthPoints())
	    	setHealthPoints(getHealthPoints()+50);
	    else
	    	setHealthPoints(getMaxHealthPoints());
		
	}
}

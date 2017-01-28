package dragonball.model.character.fighter;

import java.util.ArrayList;

import dragonball.model.attack.SuperAttack;
import dragonball.model.attack.UltimateAttack;

@SuppressWarnings("serial")
public class Majin extends PlayableFighter  {
	public Majin (String name){
		
		
		super(name , 1500 ,50,50,3,6,new ArrayList<SuperAttack>(),new ArrayList<UltimateAttack>());

	}

	public Majin(String name, int level, int xp, int targetXp, int maxHealthPoints,
	     int blastDamage, int physicalDamage, int abilityPoints, int maxKi, int maxStamina,
	     ArrayList<SuperAttack> superAttacks, ArrayList<UltimateAttack> ultimateAttacks){
		
		
	   super(name, level, xp,targetXp, maxHealthPoints,blastDamage, physicalDamage, abilityPoints, maxKi,maxStamina,
			      superAttacks, ultimateAttacks);
			
	}

	@Override
	public void onAttackerTurn() {

		
	}

	@Override
	public void onDefenderTurn() {
	    
		if(getStamina()<getMaxStamina())
	    	setStamina(getStamina()+1);
		
	}
}

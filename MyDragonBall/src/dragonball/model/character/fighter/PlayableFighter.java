package dragonball.model.character.fighter;

import java.util.ArrayList;

import dragonball.model.attack.SuperAttack;
import dragonball.model.attack.UltimateAttack;
import dragonball.model.character.PlayableCharacter;

@SuppressWarnings("serial")
public abstract class PlayableFighter extends Fighter implements PlayableCharacter{
	
	private int xp;
	private int targetXp;
	private int abilityPoints;


	public PlayableFighter(String name, int level, int xp, int targetXp, int maxHealthPoints,
		     int blastDamage, int physicalDamage, int abilityPoints, int maxKi, int maxStamina,
		     ArrayList<SuperAttack> superAttacks, ArrayList<UltimateAttack> ultimateAttacks) {

			super(name , level ,maxHealthPoints ,blastDamage,physicalDamage,maxKi,maxStamina,
					superAttacks,ultimateAttacks);
			this.xp = xp;
			this.targetXp = targetXp;
			this.abilityPoints = abilityPoints;

		    setHealthPoints(getMaxHealthPoints());
		    setStamina(getMaxStamina());
		    setKi(0);
		}


		public PlayableFighter(String name, int maxHealthPoints, int blastDamage, int physicalDamage,
		int maxKi, int maxStamina, ArrayList<SuperAttack> superAttacks, ArrayList<UltimateAttack>
		ultimateAttacks){
			
			this(name , 1 ,0,10,maxHealthPoints ,blastDamage,physicalDamage,0,maxKi,maxStamina,
					superAttacks,ultimateAttacks);
		}

	public int getXp() {
		return xp;
	}

	public void setXp(int i) {
		
		 while(true){	 
				 if(i<getTargetXp()){
				  xp=i;	 
				  return;
				 }
				 i=i-getTargetXp();
				 setLevel(getLevel()+1);
				 xp=0;
				 setTargetXp(getTargetXp()+20);
				 setAbilityPoints(getAbilityPoints()+2);
				 
			 }
	}

	public int getTargetXp() {
		return targetXp;
	}

	public void setTargetXp(int targetXp) {
		this.targetXp = targetXp;
	}

	public int getAbilityPoints() {
		return abilityPoints;
	}

	public void setAbilityPoints(int abilityPoints) {
		this.abilityPoints = abilityPoints;
	}

	public void addXP(int i) {

	
	}
}

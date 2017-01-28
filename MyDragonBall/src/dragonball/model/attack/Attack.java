package dragonball.model.attack;

import java.io.Serializable;

import dragonball.model.battle.BattleOpponent;
import dragonball.model.character.fighter.Fighter;
import dragonball.model.character.fighter.Saiyan;
import dragonball.model.exceptions.NotEnoughKiException;

public abstract class Attack implements Serializable {

	private static final long serialVersionUID = 1L;
	private String name;
	private int damage;

	public Attack(String name, int damage) {
		this.name = name;
		this.damage = damage;
	}

	public String getName() {
		return name;
	}

	public int getDamage() {
		return damage;
	}
	
	public abstract int getAppliedDamage(BattleOpponent attacker);
	
	public void onUse(BattleOpponent attacker, BattleOpponent defender,boolean defenderBlocking) throws NotEnoughKiException{
		
		double k=1;
		
		
		try{
			
		Saiyan s=(Saiyan) attacker ;	
		  
		if(s.isTransformed())
			  k=1.25;	
		}catch(Exception e){
			
		}
		
		
		
		int x=(int)(getAppliedDamage(attacker)*k);
		Fighter def=(Fighter)defender ;
		
		if(defenderBlocking){
			int lvls=x/100;
			int s=def.getStamina();
			
			if(s>lvls){
				def.setStamina(s-lvls);
			    if(x%100!=0)
			    	def.setStamina(def.getStamina()-1);
			}
			else{
				x=x-(100*s);
			    def.setStamina(0);
			    def.setHealthPoints(def.getHealthPoints()-x);
			}
		}
		else
		    def.setHealthPoints(def.getHealthPoints()-x);
		
	}
	
	public String attackType(){
		return "Attack";
	}
	
	public boolean equals(Attack a){
	
	 if(name.equals(a.name) && damage==a.damage)
		 return true;
	 else
		 return false;
		
	}
	
	

}

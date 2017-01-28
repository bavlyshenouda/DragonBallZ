package dragonball.model.attack;

import dragonball.model.battle.BattleOpponent;
import dragonball.model.character.fighter.Fighter;

public class MaximumCharge extends SuperAttack {

	
	private static final long serialVersionUID = 1L;

	public MaximumCharge() {
		super("Maximum Charge", 0);
	}
	
	@Override 
	public int getAppliedDamage(BattleOpponent attacker) {
		return 0;
	}
	
	@Override
	public void onUse(BattleOpponent attacker, BattleOpponent defender,
			boolean defenderBlocking) {
		
		Fighter atk=(Fighter)attacker ;
		
		if(atk.getKi()+3<atk.getMaxKi())
			atk.setKi(atk.getKi()+3);
		else
			atk.setKi(atk.getMaxKi());
		
	
		
	}
	
	public String attackType(){
		return "MaximumCharge";
	}
}

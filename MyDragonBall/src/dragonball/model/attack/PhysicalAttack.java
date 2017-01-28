package dragonball.model.attack;

import dragonball.model.battle.BattleOpponent;
import dragonball.model.character.fighter.Fighter;
import dragonball.model.exceptions.NotEnoughKiException;

public class PhysicalAttack extends Attack {

	private static final long serialVersionUID = 1L;
	public PhysicalAttack() {
		super("Physical Attack", 50);
	}

	@Override
	public int getAppliedDamage(BattleOpponent attacker) {
		
		Fighter f=(Fighter)attacker ;
		return 50+f.getPhysicalDamage();
	}

	@Override
	public void onUse(BattleOpponent attacker, BattleOpponent defender,
			boolean defenderBlocking) throws NotEnoughKiException {
       
		Fighter atk=(Fighter)attacker ;
		
		if(atk.getKi()!=atk.getMaxKi())
			atk.setKi(atk.getKi()+1);

		super.onUse(attacker, defender, defenderBlocking);	
		
	
	}
	public String attackType(){
		return "PhysicalAttack";
	}
	
}

package dragonball.model.attack;

import dragonball.model.battle.BattleOpponent;
import dragonball.model.character.fighter.Saiyan;
import dragonball.model.exceptions.NotEnoughKiException;

public class SuperSaiyan extends UltimateAttack {

	private static final long serialVersionUID = 1L;
	public SuperSaiyan() {
		super("Super Saiyan", 0);
	}
	
	@Override 
	public int getAppliedDamage(BattleOpponent attacker) {
		return 0;
	}
	
	@Override
	public void onUse(BattleOpponent attacker, BattleOpponent defender,
			boolean defenderBlocking) throws NotEnoughKiException {
		
		
		Saiyan atk=(Saiyan)attacker ;
		
		if(atk.getKi()<3)
			throw new NotEnoughKiException(3,atk.getKi());
		else
		    atk.setTransformed(true);
		

		
	}
	public String attackType(){
		return "SuperSaiyan";
	}
}

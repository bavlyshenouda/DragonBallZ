package dragonball.model.attack;

import dragonball.model.battle.BattleOpponent;
import dragonball.model.character.fighter.Fighter;
import dragonball.model.character.fighter.Saiyan;
import dragonball.model.exceptions.NotEnoughKiException;

public class UltimateAttack extends Attack {

	
	private static final long serialVersionUID = 1L;
	public UltimateAttack(String name, int damage) {
		super(name, damage);
	}

	@Override
	public int getAppliedDamage(BattleOpponent attacker) {
		return getDamage()+((Fighter)attacker).getBlastDamage();
	}

	@Override
	public void onUse(BattleOpponent attacker, BattleOpponent defender,
			boolean defenderBlocking) throws NotEnoughKiException {
		
		try{
			
		Saiyan s=(Saiyan) attacker ;	
		  
		if(s.isTransformed()){
		    super.onUse(attacker, defender, defenderBlocking);
		    return ;
		}
		}catch(Exception e){
		}
		
		Fighter atk=(Fighter)attacker ;
		
		if(atk.getKi()>2){
			atk.setKi(atk.getKi()-3);
		    super.onUse(attacker, defender, defenderBlocking);
		}
		else
			throw new NotEnoughKiException(3,atk.getKi());


		
	}
	public String attackType(){
		return "UltimateAttack";
	}
}

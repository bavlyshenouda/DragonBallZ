package dragonball.model.exceptions;

import dragonball.model.attack.Attack;

public class DuplicateAttackException extends InvalidAssignAttackException {

	
	private static final long serialVersionUID = 1L;
	private Attack attack;
	
	public Attack getAttack() {
		return attack;
	}
	public DuplicateAttackException(Attack attack){
		super("This attack already exsist for this fighter");
		this.attack=attack;
	}

}

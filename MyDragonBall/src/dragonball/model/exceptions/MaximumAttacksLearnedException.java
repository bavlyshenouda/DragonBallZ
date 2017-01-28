package dragonball.model.exceptions;

public class MaximumAttacksLearnedException extends InvalidAssignAttackException {

	private static final long serialVersionUID = 1L;

	public MaximumAttacksLearnedException(){
		super("This fighter already reached this maximum number of attacks");
	}

}

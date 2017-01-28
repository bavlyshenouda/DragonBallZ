package dragonball.model.exceptions;

public class NotASaiyanException extends InvalidAssignAttackException {

	
	private static final long serialVersionUID = 1L;

	public NotASaiyanException(){
		super("This attack is only valid for Saiyan fighters");
	}

}

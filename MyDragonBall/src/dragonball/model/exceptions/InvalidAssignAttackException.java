package dragonball.model.exceptions;

public abstract class InvalidAssignAttackException extends DragonBallException {


	private static final long serialVersionUID = 1L;
	
	public InvalidAssignAttackException(){
		super();
	}
	public InvalidAssignAttackException(String message){
		super(message);
	}
}

package dragonball.model.exceptions;

public abstract class DragonBallException extends Exception {


	private static final long serialVersionUID = 1L;
	public DragonBallException (){
		super();
	}
	public DragonBallException(String message){
		super(message);
	}

	
	
}

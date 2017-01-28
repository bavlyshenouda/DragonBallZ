package dragonball.model.exceptions;

public abstract class NotEnoughResourcesException extends DragonBallException {

private static final long serialVersionUID = 1L;

public NotEnoughResourcesException(){
	 super();
 }
 public NotEnoughResourcesException(String message){
	 super(message);
 }
 
}

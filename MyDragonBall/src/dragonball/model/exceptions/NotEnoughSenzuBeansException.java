package dragonball.model.exceptions;

public class NotEnoughSenzuBeansException extends NotEnoughResourcesException {

	
	private static final long serialVersionUID = 1L;

	public NotEnoughSenzuBeansException(){
		super("No enough Senzu Beans");
	}

}

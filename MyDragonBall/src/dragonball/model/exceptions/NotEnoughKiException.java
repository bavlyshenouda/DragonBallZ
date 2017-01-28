package dragonball.model.exceptions;

public class NotEnoughKiException extends NotEnoughResourcesException {

	
private static final long serialVersionUID = 1L;
private int availableKi;
private	int requiredKi;
	
	public int getAvailableKi() {
		return availableKi;
	}

	public int getRequiredKi() {
		return requiredKi;
	}

	public NotEnoughKiException(int requiredKi, int availableKi){
		super("This attack requires "+requiredKi+" Ki but you only have "+availableKi+" Ki");
		this.availableKi=availableKi;
		this.requiredKi=requiredKi;
	}
	

}

package dragonball.model.exceptions;

public class NotEnoughAbilityPointsException extends NotEnoughResourcesException {


private static final long serialVersionUID = 1L;

	public NotEnoughAbilityPointsException(){
		super("No enough ability points");
	}

}

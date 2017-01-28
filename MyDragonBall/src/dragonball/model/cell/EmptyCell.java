package dragonball.model.cell;

@SuppressWarnings("serial")
public class EmptyCell extends Cell {

	@Override
	public String toString() {
		return "[ ]";
	}

	@Override
	public void onStep() {
        ;		
	}
}

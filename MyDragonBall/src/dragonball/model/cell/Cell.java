package dragonball.model.cell;

import java.io.Serializable;

public abstract class Cell implements Serializable {

	private static final long serialVersionUID = 1L;
	@Override
	public abstract String toString();
	private CellListener listener; 
	public abstract void onStep();
	
	
	public CellListener getListener() {
		return listener;
	}
	public void setListener(CellListener cellListener) {
		this.listener = cellListener;
	}
}

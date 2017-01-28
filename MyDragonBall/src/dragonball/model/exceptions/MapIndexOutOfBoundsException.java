package dragonball.model.exceptions;

public class MapIndexOutOfBoundsException extends IndexOutOfBoundsException {

	
	private static final long serialVersionUID = 1L;
	private int row;
	private int column;

	public MapIndexOutOfBoundsException(int row, int column){
		super("You are on ("+row+","+column+") , can't go further this way");
		this.row=row;
		this.column=column;
	}

	public int getRow() {
		return row;
	}

	public int getColumn() {
		return column;
	}
}

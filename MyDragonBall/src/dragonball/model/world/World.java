package dragonball.model.world;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

import dragonball.model.cell.Cell;
import dragonball.model.cell.CellListener;
import dragonball.model.cell.Collectible;
import dragonball.model.cell.CollectibleCell;
import dragonball.model.cell.EmptyCell;
import dragonball.model.cell.FoeCell;
import dragonball.model.character.fighter.NonPlayableFighter;
import dragonball.model.exceptions.MapIndexOutOfBoundsException;

public class World implements CellListener,Serializable {


	private static final long serialVersionUID = 1L;
	private Cell[][] map;
	private int playerRow;
	private int playerColumn;
	private WorldListener listener;

	public World(){
		map = new Cell[10][10];
		playerRow=9;
		playerColumn=9;
	}
	
	

	public WorldListener getListener() {
		return listener;
	}



	public void setListener(WorldListener worldListener) {
		this.listener = worldListener;
	}



	public Cell[][] getMap() {
		return map;
	}

	public int getPlayerRow() {
		return playerRow;
	}

	public int getPlayerColumn() {
		return playerColumn;
	}


	public void generateMap(ArrayList<NonPlayableFighter> weakFoes, ArrayList<NonPlayableFighter>
	strongFoes){
		for(int i=0; i<10;i++){
			for(int j = 0; j<10;j++){
				map[i][j]= new EmptyCell();
			}
		}
		Random R=new Random();
		map[0][0]=new FoeCell(strongFoes.get(R.nextInt(strongFoes.size())));
		int Beans = R.nextInt(3)+3;
	
		for(int i=0;i<Beans;i++){
			int x=R.nextInt(10);
			int y=R.nextInt(10);
			String test = map[x][y].toString();
			boolean emp= !((x==0 && y==0) || (x==9 && y==9)) ; 
			 if(test.equals("[ ]") && emp)
				map[x][y]= new CollectibleCell(Collectible.SENZU_BEAN);
			 else 
				Beans++;
			}
		int foes = 15;
		for(int i=0;i<foes;i++){
			int x=R.nextInt(10);
			int y=R.nextInt(10);
			String test = map[x][y].toString();
			boolean emp= !((x==0 && y==0) || (x==9 && y==9)) ; 
			if(test.equals("[ ]") && emp)
				map[x][y]= new FoeCell(weakFoes.get(R.nextInt(weakFoes.size())));
			else foes++;
		}
		int ball=1;
		
		for(int i=0;i<ball;i++){
			int x=R.nextInt(10);
			int y=R.nextInt(10);
			String test = map[x][y].toString();
			boolean emp= !((x==0 && y==0) || (x==9 && y==9)) ; 
			if(test.equals("[ ]") && emp)
				map[x][y]= new CollectibleCell(Collectible.DRAGON_BALL);
			else 
				ball++;
		}
		
		for(int i=0; i<10;i++){
			for(int j = 0; j<10;j++){
				map[i][j].setListener(this);
			}
		}
		
	}

	@Override
	public String toString(){
	     String out="";
	     for(int i =0; i<10;i++){
				for(int j = 0; j<10;j++){
					
					if(i==playerRow && j==playerColumn)
						out=out+"[X] ";
					else
					    out= out + map[i][j]+ " ";
				}
				out=out+"\n";
		 }
	     return out;
		}

	@Override
	public void onFoeEncountered(NonPlayableFighter foe) {
	
		
		if(listener!=null)
		listener.onFoeEncountered(foe);
		
	}

	@Override
	public void onCollectibleFound(Collectible collectible) {
		
		map[playerRow][playerColumn]=new EmptyCell(); 
		
	if(listener!=null){
		listener.onCollectibleFound(collectible);
	 }
	}
	
	public void resetPlayerPosition(){
		
	  playerRow=9;
	  playerColumn=9;
	}
	
	public void moveUp()  {
		
	if(playerRow!=0){
		playerRow--;
	    map[playerRow][playerColumn].onStep();
	 }
	else
		throw new MapIndexOutOfBoundsException(playerRow,playerColumn);
	
	}
	
	public void moveDown(){
		
		if(playerRow!=9){
			playerRow++;
	        map[playerRow][playerColumn].onStep();
		}
		else
			throw new MapIndexOutOfBoundsException(playerRow,playerColumn);
	}
	
	public void moveRight(){
		
		if(playerColumn!=9){
			playerColumn++;
	        map[playerRow][playerColumn].onStep();
		}
		else
			throw new MapIndexOutOfBoundsException(playerRow,playerColumn);
	}
	
	public void moveLeft(){
		
		if(playerColumn!=0){
			playerColumn--;
	        map[playerRow][playerColumn].onStep();
		}
		else
			throw new MapIndexOutOfBoundsException(playerRow,playerColumn);
	}

}

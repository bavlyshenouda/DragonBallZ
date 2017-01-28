package dragonball.view.panels;

import java.awt.GridLayout;

import javax.swing.Icon;
import javax.swing.JLabel;

import dragonball.model.character.fighter.PlayableFighter;

@SuppressWarnings("serial")
public class MapPanel extends MasterPanel {

	
	
	
	JLabel cells[][];
	
	public MapPanel(PlayableFighter f,int x) {
		
      setLayout(new GridLayout(10,10) );
      
      cells=new JLabel[10][10];
      
      for(int i=0;i<10;i++){
    	  for(int j=0;j<10;j++){
    		cells[j][i]=new JLabel();
    		cells[j][i].setIcon(resize("MapButton.png",x,x));
    		add(cells[j][i]);  
    	  }  
      }
      cells[0][0].setIcon(resize("StrongFoeMap.png",x,x));
      
      String s = f.getClass().getName();
      s = s.substring(s.lastIndexOf('.') + 1);
      s=s+"Map.png";
      cells[9][9].setIcon(resize(s,x,x));
	
	
	
	
	
	
	}

	public JLabel[][] getCells() {
		return cells;
	}

	public void setCells(JLabel[][] cells) {
		this.cells = cells;
	}
	
	public void changeIcon(int i,int j,String s,int x){
	    cells[i][j].setIcon(resize(s,x,x));	
	}

	public void swicthIcon(int x, int y, int x1, int y1,int size) {
		Icon I1=cells[x][y].getIcon();
		Icon I2=cells[x1][y1].getIcon();
		
		if(x1==0 && y1==0)
		    cells[x][y].setIcon(resize("MapButton.png",size,size));
		else
			cells[x][y].setIcon(I2);
		
		cells[x1][y1].setIcon(I1);
		
	}

	public void clear(int x) {
	      for(int i=0;i<10;i++){
	    	  for(int j=0;j<10;j++){
	    		cells[j][i].setIcon(resize("MapButton.png",x,x)); 
	    	  }  
	      }
		
	}
	
	



}

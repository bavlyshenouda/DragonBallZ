package dragonball.view.panels;

import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;


@SuppressWarnings("serial")
public class CollectPanel extends MasterPanel {

ImageIcon dragon;
ImageIcon senzu;
JButton collect;




  public CollectPanel(int W,int H){
	  
	  
	setLayout(new GridLayout(1,1));  
	setBounds(W/2-W/16,H/4,W/8,H/4);
	  


	
    senzu=resize("Senzu.png",W/8,H/4);
    dragon=resize("DragonBall.png",W/8,H/4);
	
	collect=new JButton("collect");
	collect.setBorderPainted(false); 
	collect.setContentAreaFilled(false); 
	collect.setFocusPainted(false);
	collect.setOpaque(false);
	collect.setVisible(true);
  

  add(collect);
  
  setOpaque(false);
  setVisible(false);
  
  }





public ImageIcon getDragon() {
	return dragon;
}



public void setDragon(ImageIcon dragon) {
	this.dragon = dragon;
}



public ImageIcon getSenzu() {
	return senzu;
}


public void setSenzu(ImageIcon senzu) {
	this.senzu = senzu;
}








public JButton getCollect() {
	return collect;
}




public void setCollect(JButton collect) {
	this.collect = collect;
}



}

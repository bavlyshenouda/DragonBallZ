package dragonball.view.panels;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class DialogPanel extends MasterPanel {

 
    JLabel message;
    JButton yes;
    JButton no;
    
	
  public DialogPanel(int W,int H){
	  
	  setLayout(new GridLayout(3,0));
	  message=new JLabel("");
	  yes=new JButton("Yes");
	  no=new JButton("No");
	  
	  add(message);
	  add(yes);
	  add(no);
	  
	  setBounds(W/2-W/8,H/4+W/32,W/4,H/8);
	 
  }

public JLabel getMessage() {
	return message;
}

public void setMessage(JLabel message) {
	this.message = message;
}

public JButton getYes() {
	return yes;
}

public void setYes(JButton yes) {
	this.yes = yes;
}

public JButton getNo() {
	return no;
}

public void setNo(JButton no) {
	this.no = no;
}

	
  
	


}

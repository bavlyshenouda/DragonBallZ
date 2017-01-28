package dragonball.view.panels;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class ExceptPanel extends MasterPanel {


JLabel message;
JButton ok;

public JLabel getMessage() {
	return message;
}
public void setMessage(JLabel message) {
	this.message = message;
}
public JButton getOk() {
	return ok;
}
public void setOk(JButton ok) {
	this.ok = ok;
}

public ExceptPanel(int W,int H){
	
	setLayout(new GridLayout(2,0));
	setBounds(W/2-W/8,H/2+H/6,W/4,H/8);
	
	message=new JLabel();
	ok=new JButton("OK");
	
	add(message);
	add(ok);
}







}

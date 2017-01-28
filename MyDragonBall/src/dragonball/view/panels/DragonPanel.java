package dragonball.view.panels;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;

import dragonball.model.dragon.DragonWish;
import dragonball.model.dragon.DragonWishType;

@SuppressWarnings("serial")
public class DragonPanel extends MasterPanel{
	DragonWish[] dragonWish;
	JButton DB[];
	JLabel BackgroundL;
	
public DragonPanel (int W,int H,DragonWish[] dragon){
	
	setLayout(null);
	setBounds(0,0,W,H);
	
	this.dragonWish=dragon;
	BackgroundL = new JLabel();
	BackgroundL.setBounds(0,0,W,H);
	BackgroundL.setIcon(resize("Dragon.jpg",W,H));
	Font font = new Font("Times new Roman", Font.BOLD, 20);
	
	int L=dragonWish.length;
    
	DB=new JButton[L];
	
	System.out.println(L);
	
	for(int i=0;i<L;i++){

	DB[i]=new JButton();
	DB[i].setBounds((i+1)*W/5,H-H/4,W/8,H/16);
     	
	
	if(dragon[i].getType()==DragonWishType.ABILITY_POINTS)
		DB[i].setText(dragon[i].getAbilityPoints()+" AbilityPoints");
	
	if(dragon[i].getType()==DragonWishType.SENZU_BEANS)
		DB[i].setText(dragon[i].getSenzuBeans()+" Senzu Beans");
	
	if(dragon[i].getType()==DragonWishType.SUPER_ATTACK)
		DB[i].setText("New Super Attack"+dragon[i].getSuperAttack().getName());
	
	if(dragon[i].getType()==DragonWishType.ULTIMATE_ATTACK)
		DB[i].setText("New Ultimate Attack"+dragon[i].getUltimateAttack().getName());
		
	
	DB[i].setActionCommand("DB"+i);
	DB[i].setFont(font);
	add(DB[i]);
	}
	

	
	add(BackgroundL);

}

public DragonWish[] getDragonWish() {
	return dragonWish;
}

public void setDragonWish(DragonWish[] dragonWish) {
	this.dragonWish = dragonWish;
}


public JLabel getBackgroundL() {
	return BackgroundL;
}

public void setBackgroundL(JLabel background) {
	BackgroundL = background;
}

public JButton[] getDB() {
	return DB;
}

public void setDB(JButton[] dB) {
	DB = dB;
}

	


}

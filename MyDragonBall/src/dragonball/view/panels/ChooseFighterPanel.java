package dragonball.view.panels;


import javax.swing.JButton;

import dragonball.model.player.Player;

@SuppressWarnings("serial")
public class ChooseFighterPanel extends MasterPanel{


	
	ChooseOneFighterPanel AllFighters[];
	JButton numbers[];
	

    public ChooseFighterPanel(int W,int H,Player p){
    	
        
      setBounds(0,0,W,H);
      setLayout(null);
    	
    int n=p.getFighters().size();
    AllFighters=new ChooseOneFighterPanel[n];
    numbers=new JButton[1+(n-1)/5];
    
    for(int i=0;i<numbers.length;i++){
    	numbers[i]=new JButton(""+(i+1));
        numbers[i].setBounds(i*W/40, H-H/20, W/40, H/20);
        add(numbers[i]);
    }
    
    

    
    int x=W/5;
    int m;
    
    if(n<5)
    	m=n;
    else
    	m=5;
    
    for(int i=0;i<m;i++){
    AllFighters[i]=new ChooseOneFighterPanel(x,H-H/20,p.getFighters().get(i));
    AllFighters[i].setBounds(i*W/5, 0, W/5,H-H/10);
    add(AllFighters[i]);	
    }
    
    
    for(int i=5;i<n;i++){
        AllFighters[i]=new ChooseOneFighterPanel(x,H-H/10,p.getFighters().get(i));
        AllFighters[i].setBounds((i%5)*W/5,0, W/5,H-H/10);
        AllFighters[i].setVisible(false);
        add(AllFighters[i]);	
    }

    
    	
    	
    	
    	
    }


	public ChooseOneFighterPanel[] getAllFighters() {
		return AllFighters;
	}


	public void setAllFighters(ChooseOneFighterPanel[] allFighters) {
		AllFighters = allFighters;
	}


	public JButton[] getNumbers() {
		return numbers;
	}


	public void setNumbers(JButton[] numbers) {
		this.numbers = numbers;
	}
	







	
}

package dragonball.controller;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import dragonball.model.attack.Attack;
import dragonball.model.attack.PhysicalAttack;
import dragonball.model.attack.SuperAttack;
import dragonball.model.battle.Battle;
import dragonball.model.battle.BattleEvent;
import dragonball.model.battle.BattleEventType;
import dragonball.model.cell.Collectible;
import dragonball.model.character.fighter.Fighter;
import dragonball.model.character.fighter.PlayableFighter;
import dragonball.model.dragon.Dragon;
import dragonball.model.exceptions.DuplicateAttackException;
import dragonball.model.exceptions.MapIndexOutOfBoundsException;
import dragonball.model.exceptions.MaximumAttacksLearnedException;
import dragonball.model.exceptions.NotASaiyanException;
import dragonball.model.exceptions.NotEnoughAbilityPointsException;
import dragonball.model.exceptions.NotEnoughKiException;
import dragonball.model.exceptions.NotEnoughSenzuBeansException;
import dragonball.model.game.Game;
import dragonball.model.game.GameListener;
import dragonball.model.player.Player;
import dragonball.view.GeneralFrame;
import dragonball.view.panels.AssignPanel;
import dragonball.view.panels.BattlePanel;
import dragonball.view.panels.ChooseFighterPanel;
import dragonball.view.panels.CollectPanel;
import dragonball.view.panels.DragonPanel;
import dragonball.view.panels.FighterPanel;
import dragonball.view.panels.PlayerPanel;
import dragonball.view.panels.UpgradePanel;
import dragonball.view.panels.WorldPanel;

@SuppressWarnings("serial")
public class Main implements ActionListener,GameListener,KeyListener,Serializable {

	GeneralFrame generalframe;
	Game game;
	int W;
	int H;
	boolean newFighter=false;
	boolean NewGame=false;
	boolean SaveGame=false;
	boolean LoadGame=false;
	boolean dialog=false;
	boolean data=true;
	boolean stop=false;
	boolean collect=false;
	boolean Exit=false;
	boolean Music=true;
	
	Clip clip;
	long TotalTime;
	private int replaceNum;
	
	
	public Main(){
		
	
        try{
		FileInputStream fileIn = new FileInputStream("saved");
        @SuppressWarnings({ "resource", "unused" })
		ObjectInputStream in = new ObjectInputStream(fileIn);
        }
		catch(Exception e){
		data=false;	
		}
        
      //music();    
     
    
    
	generalframe=new GeneralFrame();
	generalframe.getMusic().addActionListener(this);
	generalframe.getExit().addActionListener(this);
	generalframe.getStartPanel().getNewGame().addActionListener(this);
	generalframe.getStartPanel().getLoadGame().addActionListener(this);
	generalframe.getDialogPanel().getYes().addActionListener(this);
	generalframe.getDialogPanel().getNo().addActionListener(this);
	generalframe.getStartPanel().add(generalframe.getMusic());
	generalframe.getStartPanel().add(generalframe.getExit());
	generalframe.getStartPanel().add(generalframe.getDialogPanel());
	generalframe.getStartPanel().add(generalframe.getStartPanel().getBackgroundL());
    generalframe.getExceptPanel().getOk().addActionListener(this);
	
	
	if(!data)
		generalframe.getStartPanel().remove(generalframe.getStartPanel().getLoadGame());
	

	for(int i=0;i<5;i++)
		generalframe.getNewPlayerPanel().getFighters()[i].addActionListener(this);
	
	
	game=new Game();
	game.setListener(this);
		
	 Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	 W =(int) screenSize.getWidth();
	 H =(int) screenSize.getHeight();
	 

	}

	@Override
	public void keyPressed(KeyEvent k) {
		
	 if(generalframe.getWorldPanel().isVisible() && !stop &&!collect){
         
		 
		if(k.getKeyCode()==KeyEvent.VK_RIGHT){
			
		
			try{
			
			int x=game.getWorld().getPlayerColumn();
			int y=game.getWorld().getPlayerRow();
			game.getWorld().moveRight();
			generalframe.getWorldPanel().getMapPanel().swicthIcon(x,y,game.getWorld().getPlayerColumn(),game.getWorld().getPlayerRow(),H/10);
			}catch(MapIndexOutOfBoundsException e){
				
			  showException(e);
			}
		}
		
		if(k.getKeyCode()==KeyEvent.VK_UP){
				
			try{
			int x=game.getWorld().getPlayerColumn();
			int y=game.getWorld().getPlayerRow();
			game.getWorld().moveUp();
			generalframe.getWorldPanel().getMapPanel().swicthIcon(x,y,game.getWorld().getPlayerColumn(),game.getWorld().getPlayerRow(),H/10);
			}catch(MapIndexOutOfBoundsException e){
				 showException(e);
			}
		}
		
		if(k.getKeyCode()==KeyEvent.VK_DOWN){
				
			try{
	
			int x=game.getWorld().getPlayerColumn();
			int y=game.getWorld().getPlayerRow();
			game.getWorld().moveDown();
			generalframe.getWorldPanel().getMapPanel().swicthIcon(x,y,game.getWorld().getPlayerColumn(),game.getWorld().getPlayerRow(),H/10);
			}catch(MapIndexOutOfBoundsException e){
				  showException(e);
			}
		}
		
		if(k.getKeyCode()==KeyEvent.VK_LEFT){
			
			try{
			int x=game.getWorld().getPlayerColumn();
			int y=game.getWorld().getPlayerRow();
			game.getWorld().moveLeft();	
			generalframe.getWorldPanel().getMapPanel().swicthIcon(x,y,game.getWorld().getPlayerColumn(),game.getWorld().getPlayerRow(),H/10);
			}catch(MapIndexOutOfBoundsException e){
				  showException(e);
			}
		}

	}
		
			
	}
	
	@Override
	public void keyReleased(KeyEvent arg0) {
	}


	@Override
	public void keyTyped(KeyEvent arg0) {
	}

	@Override
	public void onDragonCalled(Dragon dragon) {
		generalframe.getWorldPanel().setVisible(false);
        DragonPanel c=new DragonPanel(W,H,dragon.getWishes()) ;
        int L=c.getDB().length;
        
         for(int i=0;i<L;i++){
        	 c.getDB()[i].addActionListener(this);
         }
         c.setVisible(true);
         generalframe.setDragonPanel(c);
         generalframe.add(c);
		
		
	} 

	@Override
	public void onCollectibleFound(Collectible collectible) {
		


		
		CollectPanel c=generalframe.getCollectPanel();
		c.getCollect().addActionListener(this);
		c.getCollect().setFocusable(true);
		
		if(collectible==Collectible.SENZU_BEAN){
           c.getCollect().setIcon(c.getSenzu());
		}else{
			c.getCollect().setIcon(c.getDragon());
		}
		c.setVisible(true);
		collect=true;


		
	}
 
	@Override
	public void onBattleEvent(BattleEvent e) {

		if(e.getType()==BattleEventType.STARTED){
			generalframe.getWorldPanel().setVisible(false);
			Battle b1=(Battle)e.getSource();
			BattlePanel b=new BattlePanel(W,H,b1);		
			PlayableFighter me=(PlayableFighter)b1.getMe();
			b.getUse().addActionListener(this);
			b.getBlock().addActionListener(this);
			int x=me.getSuperAttacks().size()+1+me.getUltimateAttacks().size();
			
			for(int i=0;i<x;i++)
				b.getAttacksPanel().getAttacks()[i].addActionListener(this);
			
			b.setVisible(true);
			b.add(generalframe.getMusic());
			b.add(generalframe.getExit());
			b.add(generalframe.getDialogPanel());
			b.add(b.getBackgroundImage());
			generalframe.setBattlePanel(b);
			generalframe.add(b);
		}
		if(e.getType()==BattleEventType.ATTACK){
			Attack a=e.getAttack();
			Fighter f=(Fighter)e.getCurrentOpponent();
			generalframe.getBattlePanel().getState().setText(f.getName()+" attacked with "+a.getName());
		}
		if(e.getType()==BattleEventType.BLOCK){
			
			Fighter f=(Fighter)e.getCurrentOpponent();
           generalframe.getBattlePanel().getState().setText(f.getName()+" blocked");
			  
			
		}
		if(e.getType()==BattleEventType.USE){
			generalframe.getBattlePanel().getState().setText(game.getPlayer().getActiveFighter().getName()+" used Senzu Beans");

			
		}
		if(e.getType()==BattleEventType.NEW_TURN){
			Fighter f=((Fighter)e.getCurrentOpponent());
			generalframe.getBattlePanel().getTurn().setText(f.getName()+" Turn");
			
		}
		if(e.getType()==BattleEventType.ENDED){
			Fighter f=((Fighter)e.getWinner());
			generalframe.getBattlePanel().getTurn().setText(f.getName()+" wins");
			showException(new Exception());
			generalframe.getExceptPanel().getMessage().setText(f.getName()+" wins");
		}
			
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		 String s=e.getActionCommand();
		 
		  if(!collect){
		 
		 if(!stop){
		 
		 if(!dialog){
			 
			 
		if(s.substring(0,2).equals("DB")){
			
			int x=Integer.parseInt(s.charAt(2)+"");
			
			game.getPlayer().chooseWish(generalframe.getDragonPanel().getDragonWish()[x]);
			
			generalframe.getDragonPanel().setVisible(false);
			generalframe.getWorldPanel().setVisible(true);
			update();
			
		}

			 
		 if(s.substring(0,2).equals("pa")){
			 
			try {
				generalframe.getBattlePanel().getB().attack(new PhysicalAttack());
				generalframe.getBattlePanel().change();
				
				int x=((Fighter)generalframe.getBattlePanel().getB().getFoe()).getHealthPoints() ;
				if(x>0){
				showException(new Exception());
				generalframe.getExceptPanel().getMessage().setText("Foe Turn");
				}
			} catch (NotEnoughKiException e1) {
                showException(e1);
			} 
			 
		 }
		 if(s.substring(0,2).equals("sa")){
			 int x=Integer.parseInt(s.charAt(2)+"");
			 
		    try {
				generalframe.getBattlePanel().getB().attack(game.getPlayer().getActiveFighter().getSuperAttacks().get(x));
				generalframe.getBattlePanel().change();
				int y=((Fighter)generalframe.getBattlePanel().getB().getFoe()).getHealthPoints() ;
				if(y>0){
				showException(new Exception());
				generalframe.getExceptPanel().getMessage().setText("Foe Turn");
				}
			} catch (NotEnoughKiException e1) {
                showException(e1);
			}	 
		 		 
		 }
		 if(s.substring(0,2).equals("ua")){
			 int x=Integer.parseInt(s.charAt(2)+"");
			 
			    try {
					generalframe.getBattlePanel().getB().attack(game.getPlayer().getActiveFighter().getUltimateAttacks().get(x));
					generalframe.getBattlePanel().change();
					int y=((Fighter)generalframe.getBattlePanel().getB().getFoe()).getHealthPoints() ;
					if(y>0){
					showException(new Exception());
					generalframe.getExceptPanel().getMessage().setText("Foe Turn");
					}
				} catch (NotEnoughKiException e1) {
	                showException(e1);
				}	 			 
		 }
		 if(s.equals("use")){
			 
			 try {
				generalframe.getBattlePanel().getB().use(game.getPlayer(),Collectible.SENZU_BEAN);
				generalframe.getBattlePanel().change();
				showException(new Exception());
				generalframe.getExceptPanel().getMessage().setText("Foe Turn");
			} catch (NotEnoughSenzuBeansException e1) {
                showException(e1);
			}
			 
			 
		 }
		 if(s.equals("block")){
			generalframe.getBattlePanel().getB().block();
			generalframe.getBattlePanel().change();
			showException(new Exception());
			generalframe.getExceptPanel().getMessage().setText("Foe Turn");
		 }
		 
		 
		 if(s.equals("New Game")){
				
			generalframe.getNewPlayerPanel().add(generalframe.getMusic());
			generalframe.getNewPlayerPanel().add(generalframe.getExit());
			generalframe.getNewPlayerPanel().add(generalframe.getDialogPanel());
			generalframe.getNewPlayerPanel().add(generalframe.getNewPlayerPanel().getBackgroundL());
			 
		   generalframe.getStartPanel().setVisible(false);	 
		   generalframe.getNewPlayerPanel().setVisible(true);
		 }
		 
		 if(s.equals("Load Last Game")){
		   
			game.load("saved");
			game.setListener(this);
			generalframe.getStartPanel().setVisible(false);
			WorldPanel w=new WorldPanel(game.getPlayer(),game.getPlayer().getActiveFighter(),W,H);
            w.getPlayerPanel().getNewCharacter().addActionListener(this);
            w.getPlayerPanel().getChooseFigther().addActionListener(this);
            w.getPlayerPanel().getAssAttack().addActionListener(this);
            w.getPlayerPanel().getUpgrade().addActionListener(this);
            w.getPlayerPanel().getSave().addActionListener(this);
            w.getPlayerPanel().getLoad().addActionListener(this);
            w.getPlayerPanel().getNew().addActionListener(this);
            w.add(generalframe.getMusic());
            w.add(generalframe.getExit());
            w.add(generalframe.getDialogPanel());
            w.add(w.getMapPanel());
			generalframe.setWorldPanel(w);
            generalframe.getWorldPanel().setBounds(0,0,W,H);
			generalframe.getWorldPanel().setVisible(true);
			generalframe.add(w);
			update();
			generalframe.addKeyListener(this);
            generalframe.setFocusable(true);
            generalframe.requestFocus();
		 }
		 		 
		 if(newFighter)
			 generalframe.getNewPlayerPanel().getNameL().setText(" ");
		 
		 String playerName=generalframe.getNewPlayerPanel().getNameL().getText();
		 String fighterName= generalframe.getNewPlayerPanel().getFighterName().getText();
		 generalframe.getNewPlayerPanel().getFighterName().setText("");
		 generalframe.getNewPlayerPanel().getNameL().setText("");
		 
		 if(!(playerName.equals("") ||fighterName.equals("")) ){
		 
					 
		 switch(s){
		 case "Earthling":
		 case "Frieza"   :
		 case "Majin"    :
		 case "Namekian" :
		 case "Sayian"   :
			 
			              if(!newFighter){
				            game=new Game();	
				            game.setListener(this);
			                game.getPlayer().setName(playerName); 
			                game.getPlayer().createFighter(s.charAt(0), fighterName);
			                WorldPanel w=new WorldPanel(game.getPlayer(),game.getPlayer().getActiveFighter(),W,H);
			                w.getPlayerPanel().getNewCharacter().addActionListener(this);
			                w.getPlayerPanel().getChooseFigther().addActionListener(this);
			                w.getPlayerPanel().getAssAttack().addActionListener(this);
			                w.getPlayerPanel().getUpgrade().addActionListener(this);
			                w.getPlayerPanel().getSave().addActionListener(this);
			                w.getPlayerPanel().getLoad().addActionListener(this);
			                w.getPlayerPanel().getNew().addActionListener(this);
			                w.add(generalframe.getMusic());
			                w.add(generalframe.getExit());
			                w.add(generalframe.getDialogPanel());
			                w.add(w.getMapPanel());
			                generalframe.setWorldPanel(w); 
			                w.setBounds(0,0,W,H);
			                
			                if(!NewGame){
			                   generalframe.addKeyListener(this);
			                }
				              generalframe.add(w);
			                
			                if(!data)
			                	w.getPlayerPanel().getLoad().removeActionListener(this);
			                


			              }
			              else{
                             game.getPlayer().createFighter(s.charAt(0), fighterName);
			              
                            WorldPanel w=generalframe.getWorldPanel();
                            w.remove(w.getMapPanel());
 			                w.add(generalframe.getMusic());
 			                w.add(generalframe.getExit());
 			                w.add(generalframe.getDialogPanel());
 			                w.add(w.getMapPanel());
                             
                             
			              }
		                  
			              generalframe.getNewPlayerPanel().setVisible(false);
		                  generalframe.getNewPlayerPanel().getNameL().setVisible(true);
		                  generalframe.getNewPlayerPanel().getEnter().setVisible(true);
		                  generalframe.getWorldPanel().setVisible(true);
		                  		                  
                          generalframe.setFocusable(true);
		                  generalframe.requestFocus();
		                  newFighter=false;
		                  NewGame=false;
		                  break;
		 default         :break;
		  
		 }
		}
		 
	   if(s.equals("Create new fighter")){
		   newFighter=true;
		   generalframe.getWorldPanel().setVisible(false);
		   generalframe.getNewPlayerPanel().getEnter().setVisible(false);
		   generalframe.getNewPlayerPanel().getNameL().setVisible(false);
		   generalframe.getNewPlayerPanel().getNameL().setText(" ");
		   generalframe.getNewPlayerPanel().setVisible(true);
		   generalframe.getNewPlayerPanel().remove(generalframe.getNewPlayerPanel().getBackgroundL());
		   generalframe.getNewPlayerPanel().add(generalframe.getMusic());
		   generalframe.getNewPlayerPanel().add(generalframe.getExit());
		   generalframe.getNewPlayerPanel().add(generalframe.getDialogPanel());
		   generalframe.getNewPlayerPanel().add(generalframe.getNewPlayerPanel().getBackgroundL());
		   
		   
	   }
	   if(s.equals("Change active fighter")){
		   
		   generalframe.getWorldPanel().setVisible(false);
	        ChooseFighterPanel c=new ChooseFighterPanel(W,H,game.getPlayer());
	        
	        for(int i=0;i<game.getPlayer().getFighters().size();i++){
	          c.getAllFighters()[i].getFighterImage().setActionCommand("-"+i);
	          c.getAllFighters()[i].getFighterImage().addActionListener(this);
	        }
	        for(int i=0;i<c.getNumbers().length;i++)
	        	c.getNumbers()[i].addActionListener(this);
	        
	        c.setVisible(true);
	    
	        generalframe.setChooseFighterPanel(c);
	        generalframe.add(c);
	        
		   int x=1;
		   int n=game.getPlayer().getFighters().size();   
		   for(int i=0;i<n;i++)
			   generalframe.getChooseFighterPanel().getAllFighters()[i].setVisible(false);
		   
		   int m=x*5-5;
	      
	     for(int i=m;i<n && i<x*5 ;i++)
	    	 generalframe.getChooseFighterPanel().getAllFighters()[i].setVisible(true);

	   }
	   if(s.equals("Assign attacks")){
		   game.getPlayer().setDragonBalls(6);
		   ArrayList<SuperAttack> sa=new ArrayList<SuperAttack> ();
		   
		   sa.add(new SuperAttack("mik",4000));
           game.getPlayer().getActiveFighter().setSuperAttacks(sa);		   
		   
		   
//		     createAssign();
	   }
	   if(s.equals("add")){
		   generalframe.getAssignPanel().getSuper1().setBounds(W/4,H/8,W/8,H/16);
		   generalframe.getAssignPanel().getUltimate().setBounds(W/4,H/4,W/8,H/16);
		   generalframe.getAssignPanel().getMenuBar().setVisible(false);
		   generalframe.getAssignPanel().getSuper1().setVisible(true);
		   generalframe.getAssignPanel().getUltimate().setVisible(true);
		   generalframe.getAssignPanel().getPlayerAttack().setText("");
		   generalframe.getAssignPanel().getFighterAttack().setText("");		   
		   generalframe.getAssignPanel().setAdded(true);

	   }
	   if(s.equals("replace")){
		   generalframe.getAssignPanel().getSuper1().setBounds(W/4,H/2,W/8,H/16);
		   generalframe.getAssignPanel().getUltimate().setBounds(W/4,H/2+H/8,W/8,H/16);
		   generalframe.getAssignPanel().getMenuBar().setVisible(false);
		   generalframe.getAssignPanel().getSuper1().setVisible(true);
		   generalframe.getAssignPanel().getUltimate().setVisible(true);
		   generalframe.getAssignPanel().getPlayerAttack().setText("");
		   generalframe.getAssignPanel().getFighterAttack().setText("");
		   generalframe.getAssignPanel().setAdded(false);
	   }
	   if(s.equals("Super Attacks")){
		   
		
		  generalframe.getAssignPanel().getMenuBar().setVisible(true);
		  generalframe.getAssignPanel().SA();

	   }
	   if(s.equals("Ultimate Attacks")){
		generalframe.getAssignPanel().getMenuBar().setVisible(true);
		  generalframe.getAssignPanel().UA();
	   }
	   
	   if(s.equals("Upgrade fighter")){
		   UpgradePanel c=new UpgradePanel(W,H,game.getPlayer().getActiveFighter());
		   c.getHealthPoints().addActionListener(this);
		   c.getPhysicalDamage().addActionListener(this);
		   c.getBlastDamage().addActionListener(this);
		   c.getKi().addActionListener(this);
		   c.getStamina().addActionListener(this);
		   c.getDone().addActionListener(this);
		   c.setVisible(true);
		   generalframe.getWorldPanel().setVisible(false);
		   generalframe.setUpgradePanel(c);
		   generalframe.add(c);
		   
		   }
	   
	   if(s.equals("Done")){
		  update();
		  
		  try{
		  generalframe.getUpgradePanel().setVisible(false);
		  }catch(NullPointerException e3){
			  
		  }
		  try{
		  generalframe.getAssignPanel().setVisible(false);
		  }catch(NullPointerException e3){
			  
		  }
		  generalframe.getWorldPanel().setVisible(true);
		  generalframe.setFocusable(true);
		  generalframe.requestFocus();
	   }
	   
	   if(s.equals("Add HealthPoints by 50")){
			
		   try {
	     game.getPlayer().upgradeFighter(game.getPlayer().getActiveFighter(),'H');
		 generalframe.getUpgradePanel().getAbilityPoints().setText("Ability Points: "+game.getPlayer().getActiveFighter().getAbilityPoints());
		 generalframe.getUpgradePanel().getFighterPanel().getFighterPanel().getMaxHealthPoints().setText("Health Points: "+game.getPlayer().getActiveFighter().getMaxHealthPoints());
		 generalframe.getUpgradePanel().getFighterPanel().getFighterPanel().getAbilityPoints().setText("Ability Points: "+game.getPlayer().getActiveFighter().getAbilityPoints());
		 
		   } catch (NotEnoughAbilityPointsException e1) {
             showException(e1);
			}
	  }
		   
	   if(s.equals("Add Physcial Damage by 50")){
		   try {
			     game.getPlayer().upgradeFighter(game.getPlayer().getActiveFighter(),'P');
				 generalframe.getUpgradePanel().getAbilityPoints().setText("Ability Points: "+game.getPlayer().getActiveFighter().getAbilityPoints());
				 generalframe.getUpgradePanel().getFighterPanel().getFighterPanel().getPhysicalDamage().setText("physicalDamage: "+game.getPlayer().getActiveFighter().getPhysicalDamage());
				 generalframe.getUpgradePanel().getFighterPanel().getFighterPanel().getAbilityPoints().setText("Ability Points: "+game.getPlayer().getActiveFighter().getAbilityPoints());	
				} catch (NotEnoughAbilityPointsException e1) {
		             showException(e1);
			    }
			   
			}
	   if(s.equals("Add BlastDamage by 50")){
		  try {
	     game.getPlayer().upgradeFighter(game.getPlayer().getActiveFighter(),'B');
	     generalframe.getUpgradePanel().getAbilityPoints().setText("Ability Points: "+game.getPlayer().getActiveFighter().getAbilityPoints());
		 generalframe.getUpgradePanel().getFighterPanel().getFighterPanel().getBlastDamage().setText("Blast Damage :"+game.getPlayer().getActiveFighter().getBlastDamage());
		 generalframe.getUpgradePanel().getFighterPanel().getFighterPanel().getAbilityPoints().setText("Ability Points: "+game.getPlayer().getActiveFighter().getAbilityPoints());			
	   } catch (NotEnoughAbilityPointsException e1) {
		          showException(e1);
			}
	   }
	   if(s.equals("Add Ki by 1")){
			  try {
		     game.getPlayer().upgradeFighter(game.getPlayer().getActiveFighter(),'K');
		     generalframe.getUpgradePanel().getAbilityPoints().setText("Ability Points: "+game.getPlayer().getActiveFighter().getAbilityPoints());
			 generalframe.getUpgradePanel().getFighterPanel().getFighterPanel().getMaxKi().setText("Ki: "+game.getPlayer().getActiveFighter().getMaxKi());
			 generalframe.getUpgradePanel().getFighterPanel().getFighterPanel().getAbilityPoints().setText("Ability Points: "+game.getPlayer().getActiveFighter().getAbilityPoints());					
	         } catch (NotEnoughAbilityPointsException e1) {
			    showException(e1);
			}
	   }
	   if(s.equals("Add Stamina by 1")){
			  try {
	        game.getPlayer().upgradeFighter(game.getPlayer().getActiveFighter(),'S');
	        generalframe.getUpgradePanel().getAbilityPoints().setText("Ability Points: "+game.getPlayer().getActiveFighter().getAbilityPoints());
	        generalframe.getUpgradePanel().getFighterPanel().getFighterPanel().getMaxStamina().setText("Stamina: "+game.getPlayer().getActiveFighter().getMaxStamina());
	        generalframe.getUpgradePanel().getFighterPanel().getFighterPanel().getAbilityPoints().setText("Ability Points: "+game.getPlayer().getActiveFighter().getAbilityPoints());
			 } catch (NotEnoughAbilityPointsException e1) {
			          showException(e1);
		     }
	   }
	   
	   if(s.equals("Start New Game")){
	       
		   dialog=true;
		   generalframe.getDialogPanel().getMessage().setText("Are you sure you want to Start a new game");
		   generalframe.getDialogPanel().setVisible(true);
		   generalframe.setFocusable(false);
		   NewGame=true;
	   
	   }
	   if(s.equals("Save Game")){
           
		   dialog=true;
		   generalframe.getDialogPanel().getMessage().setText("Are you sure you want to Save the game");
		   generalframe.getDialogPanel().setVisible(true);
		   generalframe.setFocusable(false);
		   SaveGame=true;
	   }
	   if(s.equals("load last Game")){
		   
		   dialog=true;
		   
		   generalframe.getDialogPanel().getMessage().setText("Are you sure you want to load last Saved game");
		   generalframe.getDialogPanel().setVisible(true);
		   generalframe.setFocusable(false);
		   LoadGame=true;
	   }
	   if(s.equals("exit")){
		   
		   dialog=true;
		   
		   generalframe.getDialogPanel().getMessage().setText("Are you sure you want to Quit the game,all unsaved progress will be lost");
		   generalframe.getDialogPanel().setVisible(true);
		   generalframe.setFocusable(false);
		   Exit=true;
	   }
	   if(s.equals("music")){
		   MusicSetting();
	   }

	   

		   if(s.charAt(0)=='-'){
		   String m=s.substring(1);
		   int x=Integer.parseInt(m);
		   game.getPlayer().setActiveFighter(game.getPlayer().getFighters().get(x));
		   generalframe.getChooseFighterPanel().setVisible(false);
		   update();
		   generalframe.getWorldPanel().setVisible(true);
		   generalframe.setFocusable(true);
		   generalframe.requestFocus();
		   }

	   try{
		   
		   int x=Integer.parseInt(s);
		   int n=game.getPlayer().getFighters().size();   
		   for(int i=0;i<n;i++)
			   generalframe.getChooseFighterPanel().getAllFighters()[i].setVisible(false);
		   
		   int m=x*5-5;
	      
	     for(int i=m;i<n && i<x*5 ;i++)
	    	 generalframe.getChooseFighterPanel().getAllFighters()[i].setVisible(true);
	     
	     generalframe.getWorldPanel().setVisible(false);
	     generalframe.getChooseFighterPanel().setVisible(true);
	     
	   
	   
	   }
	   catch(Exception e1){
		   
	   }
	   
	   
    }else{
		 
	   if(s.equals("Yes")){
		  
		   dialog=false;
	   
		 if(SaveGame){
		   
		if(!data){
		 generalframe.getWorldPanel().getPlayerPanel().getLoad().addActionListener(this);	
		 data=true;	
		}
			 
	    game.setListener(null);
		game.save("saved");
		game.setListener(this);
		generalframe.getDialogPanel().setVisible(false);   
		generalframe.setFocusable(true);
		generalframe.requestFocus(); 
		SaveGame=false;
	   }
	   
	   if(LoadGame){
		game.load("saved");
		game.setListener(this);
		update();
		generalframe.getDialogPanel().setVisible(false);   
		generalframe.setFocusable(true);
		generalframe.requestFocus(); 
		LoadGame=false;
	   }
	   
	   if(NewGame){
		   
		generalframe.getDialogPanel().setVisible(false);     
		generalframe.getWorldPanel().setVisible(false);
		generalframe.getNewPlayerPanel().setVisible(true);
	   }
	   if(Exit){
		 System.exit(0);  
	   }
	   
	   }
	   
	   if(s.equals("No")){
			
		dialog=false;
		generalframe.getDialogPanel().setVisible(false);   
		generalframe.setFocusable(true);
		generalframe.requestFocus();
		   
		NewGame=false;
		SaveGame=false;
		LoadGame=false; 
		Exit=false;
	   }
			 
		 
     }
		 
	}
		 else
		 {
			 if(s.equals("OK")){
			generalframe.getExceptPanel().setVisible(false);
			generalframe.setFocusable(true);
			generalframe.requestFocus();
			stop=false;
			
			if(generalframe.getExceptPanel().getMessage().getText().equals("Foe Turn")){
				generalframe.getBattlePanel().update();
			    game.setListener(this);	
			}
			
			
			if(generalframe.getExceptPanel().getMessage().getText().contains("wins")){
					generalframe.getBattlePanel().setVisible(false);
			        update();
			        generalframe.getWorldPanel().setVisible(true);
			}
				
			
			 }
		 }
		 }
		  else{
			  
			  
			  if(s.equals("collect")){								  
				generalframe.getCollectPanel().setVisible(false);
				collect=false;
                update();
				  	  
			  }
		  }
	}
	
	public void update(){
		
		Game g=game;
		PlayableFighter fighter=g.getPlayer().getActiveFighter();  
		FighterPanel fighter1=generalframe.getWorldPanel().getFighterPanel();
				
		fighter1.getNameFighter().setText("Name: "+fighter.getName());
		fighter1.getXp().setText("XP: "+fighter.getXp());
		fighter1.getTargetXp().setText("Target Xp: "+fighter.getTargetXp());
		fighter1.getAbilityPoints().setText("Ability Points: "+fighter.getAbilityPoints());
		fighter1.getLevel().setText("Level: "+fighter.getLevel());
		fighter1.getMaxHealthPoints().setText("Health Points: "+fighter.getMaxHealthPoints());
		fighter1.getMaxKi().setText("Ki: "+fighter.getMaxKi());
		fighter1.getMaxStamina().setText("Stamina: "+fighter.getMaxStamina());
		fighter1.getPhysicalDamage().setText("physicalDamage: "+fighter.getPhysicalDamage());
		fighter1.getBlastDamage().setText("Blast Damage :"+fighter.getBlastDamage());
		
		
		PlayerPanel player=generalframe.getWorldPanel().getPlayerPanel();
		Player me=g.getPlayer();
		
		player.getNameL().setText("Player Name: " + me.getName());
		player.getMap().setText("Maps Finished: " + me.getExploredMaps());
		player.getSB().setText("Senzu Beans: " + me.getSenzuBeans());
		player.getDB().setText("Dragon Balls: "+ me.getDragonBalls());
		
	    String s = fighter.getClass().getName();
	    s = s.substring(s.lastIndexOf('.') + 1);
	    s=s+".png";
		generalframe.getWorldPanel().changeIcon(s,W,H);
		
		
	    s=fighter.getClass().getName();
		s = s.substring(s.lastIndexOf('.') + 1);
	    s=s+"Map.png";
	    int x=H/10;
	    	    
	    generalframe.getWorldPanel().getMapPanel().clear(x);
		generalframe.getWorldPanel().getMapPanel().changeIcon(0,0,"StrongFoeMap.png",x);	    
		generalframe.getWorldPanel().getMapPanel().changeIcon(g.getWorld().getPlayerColumn(),g.getWorld().getPlayerRow(),s,x);
		
		generalframe.getWorldPanel().remove(generalframe.getWorldPanel().getMapPanel());
		generalframe.getWorldPanel().add(generalframe.getExit());
		generalframe.getWorldPanel().add(generalframe.getMusic());
		generalframe.getWorldPanel().add(generalframe.getDialogPanel());
		generalframe.getWorldPanel().add(generalframe.getWorldPanel().getMapPanel());
		
		
	}
	private void showException(Exception e) {
		  generalframe.getExceptPanel().getMessage().setText(e.getMessage());
		  generalframe.getExceptPanel().setVisible(true);
		  generalframe.getExceptPanel().setFocusable(true);
          generalframe.setFocusable(false);
          stop=true;
	}
	
	
	public static void main(String args[]){
		
		new Main();
	}
	
	public void music() {

		 try {

		 URL url = this.getClass().getClassLoader().getResource("Ms.wav");
		 AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);

		 clip = AudioSystem.getClip();

		 clip.open(audioIn);
		 TotalTime=clip.getMicrosecondLength();
		 clip.start();
		 clip.loop(Clip.LOOP_CONTINUOUSLY);
		} catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {

		}




	   }
         	  public void MusicSetting(){
				if(Music){
				Music=false;
			    long clipTime= clip.getMicrosecondPosition();
			    clip.stop();
			    clipTime=clipTime%TotalTime;
			    clip.setMicrosecondPosition(clipTime);
				generalframe.getMusic().setIcon(generalframe.getMusicOff());
				}
				else{
				Music=true;
			    clip.start();
			    clip.loop(Clip.LOOP_CONTINUOUSLY);
				generalframe.getMusic().setIcon(generalframe.getMusicOn());
				}
				  
				generalframe.setFocusable(true);
				generalframe.requestFocus();
			  }

    class MenuAction implements ActionListener{


		@Override
		public void actionPerformed(ActionEvent e) {
		
			String s=e.getActionCommand();
			Player p=game.getPlayer();
			PlayableFighter f=game.getPlayer().getActiveFighter();
			
			if(s.charAt(0)=='-'){//Fighter Attacks
				
				int x=Integer.parseInt(s.substring(2));
				
				       if(generalframe.getAssignPanel().getPlayerAttack().getText().equals("")){
					
				
				               if(s.charAt(1)=='s')	
					                  generalframe.getAssignPanel().getFighterAttack().setText
					                  (f.getSuperAttacks().get(x).getName()+"-"+
						               f.getSuperAttacks().get(x).getDamage()); 
				               else
					                   generalframe.getAssignPanel().getFighterAttack().setText
					                   (f.getUltimateAttacks().get(x).getName()+"-"+
						               f.getUltimateAttacks().get(x).getDamage());   
					
				       replaceNum=x;
				  
				      }
				     else{
					
					
			              if(s.charAt(1)=='s')
				              try {
					             p.assignAttack(f,p.getSuperAttacks().get(replaceNum),
					             f.getSuperAttacks().get(x));
					           generalframe.getAssignPanel().setVisible(false);	
					           createAssign();
				             } catch (MaximumAttacksLearnedException
						       | DuplicateAttackException e1) {
							    	showException(e1);
                   
				             }
			               else
				             try {
					               p.assignAttack(f,p.getUltimateAttacks().get(replaceNum),
					               f.getUltimateAttacks().get(x));					
					               generalframe.getAssignPanel().setVisible(false);	
					              createAssign();
				               } catch (MaximumAttacksLearnedException
						      |DuplicateAttackException | NotASaiyanException e1) {
							    	showException(e1);

				                 }  

				
			         }
				
				
		   }	
		   else{    //player Attacks
			  int x=Integer.parseInt(s.substring(1));	
				
				if(generalframe.getAssignPanel().isAdded()){
					
					  if(s.charAt(0)=='s')
						  try {
							    game.getPlayer().assignAttack(game.getPlayer().getActiveFighter(), 
							    game.getPlayer().getSuperAttacks().get(x),null);
							    generalframe.getAssignPanel().setVisible(false);	
							    createAssign();
						  } catch (MaximumAttacksLearnedException
								| DuplicateAttackException e1) {
						    	showException(e1);
						  }
					else
					     try {
							    game.getPlayer().assignAttack(game.getPlayer().getActiveFighter(), 
							    game.getPlayer().getSuperAttacks().get(x),null);							
							    generalframe.getAssignPanel().setVisible(false);	
							    createAssign();
						} catch (MaximumAttacksLearnedException
								| DuplicateAttackException e1) {
					    	showException(e1);
					    }
				
				
				
				}
				else{
					
					    if(generalframe.getAssignPanel().getFighterAttack().getText().equals("")){
						
					    	
					     if(s.charAt(0)=='s')	
							   generalframe.getAssignPanel().getPlayerAttack().setText
							   (p.getSuperAttacks().get(x).getName()+"-"+
								p.getSuperAttacks().get(x).getDamage()); 
						  else
							   generalframe.getAssignPanel().getPlayerAttack().setText
							   (p.getUltimateAttacks().get(x).getName()+"-"+
								p.getUltimateAttacks().get(x).getDamage());   
							
						   replaceNum=x;
						  
						}
						else{
                         								
					        if(s.charAt(0)=='s')

						     try {
							    p.assignAttack(f,p.getSuperAttacks().get(x),
							    f.getSuperAttacks().get(replaceNum));							
							    generalframe.getAssignPanel().setVisible(false);	
							    createAssign();
						     } catch (MaximumAttacksLearnedException
								| DuplicateAttackException e1) {
							    	showException(e1);
						     }
					        
					      else
						      try {
							   p.assignAttack(f,p.getUltimateAttacks().get(x),
							   f.getUltimateAttacks().get(replaceNum));
							
							generalframe.getAssignPanel().setVisible(false);	
							createAssign();
						    
						    } catch (MaximumAttacksLearnedException
								| DuplicateAttackException | NotASaiyanException e1) {
						    	showException(e1);
						    }  
						
					      
				
				       }
			
			      }
			
			    }

       	
       }

    }
	private void createAssign() {
		   AssignPanel c=new AssignPanel(W,H,game.getPlayer(),game.getPlayer().getActiveFighter());
	       generalframe.getWorldPanel().setVisible(false);
	       c.setVisible(true);
	       c.getDone().addActionListener(this);
	       c.getAdd().addActionListener(this);
	       c.getReplace().addActionListener(this);
	       c.getSuper1().addActionListener(this);
	       c.getUltimate().addActionListener(this);
	       
	       for(int i=0;i<game.getPlayer().getSuperAttacks().size();i++)
	    	   c.getPlayerS()[i].addActionListener(new MenuAction());
	       
	       for(int i=0;i<game.getPlayer().getActiveFighter().getSuperAttacks().size();i++)
	    	   c.getFighterS()[i].addActionListener(new MenuAction());
	       
	       for(int i=0;i<game.getPlayer().getUltimateAttacks().size();i++)
	    	   c.getPlayerU()[i].addActionListener(new MenuAction());
	       
	       for(int i=0;i<game.getPlayer().getActiveFighter().getUltimateAttacks().size();i++)
	    	   c.getFighterU()[i].addActionListener(new MenuAction());
	       
	       generalframe.setAssignPanel(c);
	       generalframe.add(c);
		
	 }

}

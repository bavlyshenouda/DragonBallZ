package dragonball.model.battle;

import java.util.ArrayList;
import java.util.Random;

import dragonball.model.attack.Attack;
import dragonball.model.attack.PhysicalAttack;
import dragonball.model.cell.Collectible;
import dragonball.model.character.fighter.Fighter;
import dragonball.model.character.fighter.Saiyan;
import dragonball.model.exceptions.NotEnoughKiException;
import dragonball.model.exceptions.NotEnoughSenzuBeansException;
import dragonball.model.player.Player;

public class Battle {
	private BattleOpponent me;
	private BattleOpponent foe;
	private BattleOpponent attacker;
	private boolean meBlocking;
	private boolean foeBlocking;
	private BattleListener listener;
	

	public Battle(BattleOpponent me, BattleOpponent foe) {
		this.me = me;
		this.foe = foe;
		this.attacker = me;


		Fighter meFighter = (Fighter) me;
		meFighter.setHealthPoints(meFighter.getMaxHealthPoints());
		meFighter.setKi(0);
		meFighter.setStamina(meFighter.getMaxStamina());
		
		
		try {		
			Saiyan s=(Saiyan)meFighter ;
			s.setTransformed(false);
			}
			catch (ClassCastException e) {
			;
			}

		
		
		Fighter foeFighter = (Fighter) foe;
		foeFighter.setHealthPoints(foeFighter.getMaxHealthPoints());
		foeFighter.setKi(0);
		foeFighter.setStamina(foeFighter.getMaxStamina());
	}

	public BattleListener getListener() {
		return listener;
	}

	public void setListener(BattleListener battleListener) {
		this.listener = battleListener;
	}

	public boolean isMeBlocking() {
		return meBlocking;
	}

	public boolean isFoeBlocking() {
		return foeBlocking;
	}

	public BattleOpponent getMe() {
		return me;
	}

	public BattleOpponent getFoe() {
		return foe;
	}

	public BattleOpponent getAttacker() {
		return attacker;
	}
	
	public ArrayList<Attack> getAssignedAttacks(){
		
	Fighter f=(Fighter)attacker ;
	Attack p=new PhysicalAttack();
	ArrayList<Attack> a=new ArrayList<Attack>();
    a.add(p);
	
	
	 for(int i=0;i<f.getSuperAttacks().size();i++){
		 
	  Attack s=f.getSuperAttacks().get(i);	 
	  a.add(s);	 
	 }
	 
	 for(int i=0;i<f.getUltimateAttacks().size();i++){
		 
		  Attack s=f.getUltimateAttacks().get(i);	 
		  a.add(s);	 
	}
	
		
	return a;	
	}
	
    public void attack(Attack attack) throws NotEnoughKiException{
    	
    boolean block;
    BattleOpponent def=getDefender();
    
    if(attacker==me){
      def=foe;	
      block=foeBlocking;	
    }
    else{
     def=me;	
     block=meBlocking ;	
    }
    
    attack.onUse(attacker, def, block);
    BattleEvent b=new BattleEvent(this,BattleEventType.ATTACK,attack);
    
    meBlocking=false;
    foeBlocking=false;
        
	if(listener!=null)
       listener.onBattleEvent(b); 
	
    endTurn();
    }
    
    public void block(){
    	
    if(me==attacker){
    	meBlocking=true;
        foeBlocking=false;	
    }
    else{
    	foeBlocking=true;
        meBlocking=false;
    }
    BattleEvent b=new BattleEvent(this,BattleEventType.BLOCK);
    	
   if(listener!=null)
    listener.onBattleEvent(b);  
    
   endTurn();
    }
    
    public void use(Player player, Collectible collectible) throws NotEnoughSenzuBeansException{
    	
      if(player.getSenzuBeans()==0)
    	  throw new NotEnoughSenzuBeansException();
      else
    	  player.setSenzuBeans(player.getSenzuBeans()-1);
      
      Fighter f=(Fighter)attacker ;
      f.setStamina(f.getMaxStamina());
      f.setHealthPoints(f.getMaxHealthPoints());
      
    	foeBlocking=false;
    	meBlocking=false;
    	
      BattleEvent b=new BattleEvent(this,BattleEventType.USE,collectible);
      
  	if(listener!=null)
      listener.onBattleEvent(b); 
  	
  	endTurn();
    }
    
    public BattleOpponent getDefender(){
    	
     if(me==attacker)
    	 return foe;
     else
    	 return me;
    
    }
    
    
    public void play() throws NotEnoughKiException{
    	Random r = new Random();
    	Boolean block= r.nextBoolean();
    	if(block==true && ((Fighter)me).getKi()>=2 && ((Fighter)foe).getStamina()>=2)
    		this.block();
    	else{
    		ArrayList<Attack> attacks = this.getAssignedAttacks();
    		Attack chosen;
    		Attack phy = new PhysicalAttack();
    		chosen = attacks.get(r.nextInt(attacks.size()));
    	    int kis=((Fighter)foe).getKi();
    	    if( chosen.attackType().equals("SuperAttack") &&   kis>=1)
    	    	this.attack(chosen);
    	    else if( chosen.attackType().equals("UltimateAttack")&& kis>=3)
    	    	this.attack(chosen);
    	    else if(chosen.attackType().equals("MaximumCharge"))
    	    	this.attack(chosen);
    	    else this.attack(phy);
    	   }	
    	    
    	
    }
    
    public void start(){
    	
    if(listener!=null)
    	listener.onBattleEvent(new BattleEvent(this,BattleEventType.STARTED));
    }

    public void endTurn(){
       	
     	
    	if(((Fighter)me).getHealthPoints()<=0){
    		if(listener!=null)
    		listener.onBattleEvent(new BattleEvent(this,BattleEventType.ENDED,foe));
    	 	
    	}
       	else{ 
       		if(((Fighter)foe).getHealthPoints()<=0){
       			if(listener!=null)
    		listener.onBattleEvent(new BattleEvent(this,BattleEventType.ENDED,me));}
       	     else {       		
       	         
      	         switchTurn();
       	    	 BattleOpponent def=getDefender();
       	
       	
       	        attacker.onAttackerTurn();  	
       	        def.onDefenderTurn();
  
             if(listener!=null)
       	        listener.onBattleEvent(new BattleEvent(this,BattleEventType.NEW_TURN));
       	         }
       	}
    }

    public void switchTurn(){
       	if(attacker.equals(me))
       		attacker=foe;
       	else attacker=me;
    }
    
    
    		
}

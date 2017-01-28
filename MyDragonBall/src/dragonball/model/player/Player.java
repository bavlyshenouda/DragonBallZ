package dragonball.model.player;

import java.io.Serializable;
import java.util.ArrayList;

import dragonball.model.attack.Attack;
import dragonball.model.attack.SuperAttack;
import dragonball.model.attack.SuperSaiyan;
import dragonball.model.attack.UltimateAttack;
import dragonball.model.character.fighter.Earthling;
import dragonball.model.character.fighter.Frieza;
import dragonball.model.character.fighter.Majin;
import dragonball.model.character.fighter.Namekian;
import dragonball.model.character.fighter.PlayableFighter;
import dragonball.model.character.fighter.Saiyan;
import dragonball.model.dragon.DragonWish;
import dragonball.model.exceptions.DuplicateAttackException;
import dragonball.model.exceptions.MaximumAttacksLearnedException;
import dragonball.model.exceptions.NotASaiyanException;
import dragonball.model.exceptions.NotEnoughAbilityPointsException;

public class Player implements Serializable{
	

	private static final long serialVersionUID = 1L;
	private PlayerListener listener;
	private String name;
	private ArrayList<PlayableFighter> fighters;
	private ArrayList<SuperAttack> superAttacks;
	private ArrayList<UltimateAttack> ultimateAttacks;
	private int senzuBeans;
	private int dragonBalls;
	private PlayableFighter activeFighter;
	private int exploredMaps;


	public PlayerListener getListener() {
		return listener;
	}

	public void setListener(PlayerListener playerListener) {
		this.listener = playerListener;
	}

	public Player(String name) {
		this.name = name;
		fighters=new ArrayList<PlayableFighter>();
		superAttacks=new ArrayList<SuperAttack>();
		ultimateAttacks=new ArrayList<UltimateAttack>();
		
	}


	public Player(String name, ArrayList<PlayableFighter> fighters, ArrayList<SuperAttack>
	superAttacks, ArrayList<UltimateAttack> ultimateAttacks, int senzuBeans, int dragonBalls,
	PlayableFighter activeFighter, int exploredMaps) {
		
		this.name = name;
		this.fighters = fighters;
		this.superAttacks = superAttacks;
		this.ultimateAttacks = ultimateAttacks;
		this.senzuBeans = senzuBeans;
		this.dragonBalls = dragonBalls;
		this.activeFighter = activeFighter;
		this.exploredMaps = exploredMaps;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<PlayableFighter> getFighters() {
		return fighters;
	}

	public void setFighters(ArrayList<PlayableFighter> fighters) {
		this.fighters = fighters;
	}

	public ArrayList<SuperAttack> getSuperAttacks() {
		return superAttacks;
	}

	public void setSuperAttacks(ArrayList<SuperAttack> superAttacks) {
		this.superAttacks = superAttacks;
	}

	public ArrayList<UltimateAttack> getUltimateAttacks() {
		return ultimateAttacks;
	}

	public void setUltimateAttacks(ArrayList<UltimateAttack> ultimateAttacks) {
		this.ultimateAttacks = ultimateAttacks;
	}

	public int getSenzuBeans() {
		return senzuBeans;
	}

	public void setSenzuBeans(int senzuBeans) {
		this.senzuBeans = senzuBeans;
	}

	public int getDragonBalls() {
		return dragonBalls;
	}

	public void setDragonBalls(int dragonBalls) {
		this.dragonBalls = dragonBalls;
	}

	public PlayableFighter getActiveFighter() {
		return activeFighter;
	}

	public void setActiveFighter(PlayableFighter activeFighter) {
		this.activeFighter = activeFighter;
	}

	public int getExploredMaps() {
		return exploredMaps;
	}

	public void setExploredMaps(int exploredMaps) {
		this.exploredMaps = exploredMaps;
	}
	
	public int getMaxFighterLevel(){
		
	int x=0;
	
	  for(int i=0;i<fighters.size();i++){
		  
		 int y=fighters.get(i).getLevel();
		  
		 if(y>x)
			  x=y;
	}
	
	return x;
	
	}
	
	public void callDragon(){
		
		dragonBalls=0;
		
	if(listener!=null)
		listener.onDragonCalled();
	}
	
	public void chooseWish(DragonWish wish){
		
	 switch(wish.getType())	{
	 
	 case SENZU_BEANS     :  senzuBeans=wish.getSenzuBeans()+senzuBeans; break;
	 
	 case ABILITY_POINTS  :  activeFighter.setAbilityPoints(activeFighter.getAbilityPoints()+wish.getAbilityPoints());break;
	 
	 case SUPER_ATTACK    :	 superAttacks.add(wish.getSuperAttack())       ; break;
	 
	 case ULTIMATE_ATTACK :  ultimateAttacks.add(wish.getUltimateAttack()) ; break;
	 
	 	 }
		
	
	  if(listener!=null)
	     listener.onWishChosen(wish);
	}
	
	public void createFighter(char race, String name){
		
		PlayableFighter p=null;
		
	  switch(race){
	 
	    case 'e':
	    case 'E':p=new Earthling(name);break;
	    case 's':
	    case 'S':p=new Saiyan(name);break;
	    case 'n':
	    case 'N':p=new Namekian(name);break;
	    case 'f':
	    case 'F':p=new Frieza(name);break;
	    case 'm':
	    case 'M':p=new Majin(name);break;
	 
	 }
	
	
	 if(p!=null)
		 fighters.add(p);
	 
	 if(p!=null && fighters.size()==1)
		 activeFighter=p;
	
	
	}
	
	
	
	public void upgradeFighter(PlayableFighter fighter, char fighterAttribute) throws NotEnoughAbilityPointsException{
		
	   
		if(fighter.getAbilityPoints()<=0)
			throw new NotEnoughAbilityPointsException();
		else
			fighter.setAbilityPoints(fighter.getAbilityPoints()-1);
		
		
	    switch(fighterAttribute){
	   
	    case 'H':fighter.setMaxHealthPoints(fighter.getMaxHealthPoints()+50);break;
	    case 'B':fighter.setBlastDamage(fighter.getBlastDamage()+50);break;
	    case 'P':fighter.setPhysicalDamage(fighter.getPhysicalDamage()+50);break; 
	    case 'K':fighter.setMaxKi(fighter.getMaxKi()+1);break;
	    case 'S':fighter.setMaxStamina(fighter.getMaxStamina()+1);break;
	   
	   }

	}
	
	public void assignAttack(PlayableFighter fighter, SuperAttack newAttack, SuperAttack oldAttack) throws MaximumAttacksLearnedException, DuplicateAttackException{
		
	
	ArrayList<SuperAttack> s=fighter.getSuperAttacks();
		
	 if(oldAttack==null){
		 		 
	 if(fighter.getSuperAttacks().size()==4)
	       throw new MaximumAttacksLearnedException();
	 
      
     if(DublicateAttacks(s,newAttack))
    	 throw new DuplicateAttackException(newAttack);
				 
	
	 if(fighter.getSuperAttacks().size()<4)
	    	  s.add(newAttack);
	    	
	    fighter.setSuperAttacks(s);
		 
	 }
	 else{
		 
	     if(DublicateAttacks(s,newAttack))
	    	 throw new DuplicateAttackException(newAttack);
		 
		 fighter.setSuperAttacks(replaceAttack(s,oldAttack,newAttack));
	 }

	}

	private ArrayList<SuperAttack> replaceAttack(ArrayList<SuperAttack> s,
			SuperAttack oldAttack, SuperAttack newAttack) {
		
		for(int i=0;i<s.size();i++){
			
			Attack a=s.get(i);
			
			if(a.getName().equals(oldAttack.getName()) && a.getDamage()==oldAttack.getDamage() ){
				s.remove(i);
			    break;
			}
			
			
		}
		
		s.add(newAttack);
		return s;
		
	}
	
	public void assignAttack(PlayableFighter fighter, UltimateAttack newAttack, UltimateAttack oldAttack) throws MaximumAttacksLearnedException, NotASaiyanException, DuplicateAttackException{
		
		
	ArrayList<UltimateAttack> s=fighter.getUltimateAttacks();
		
	 if(oldAttack==null){
		 		 
    if(fighter.getUltimateAttacks().size()==2)
		throw new MaximumAttacksLearnedException();
    
    if(newAttack instanceof SuperSaiyan && !(fighter instanceof Saiyan))
    	throw new NotASaiyanException();
    
    if(DublicateAttacks(s,newAttack))
   	   throw new DuplicateAttackException(newAttack);
		 
	if(fighter.getUltimateAttacks().size()<2)
	    	  s.add(newAttack);
	    	
	    fighter.setUltimateAttacks(s);
		 
	 }
	 else{
		
	     if(DublicateAttacks(s,newAttack))
	    	 throw new DuplicateAttackException(newAttack);
		 
         fighter.setUltimateAttacks(replaceAttack(s,oldAttack,newAttack));
	 }

	}


	private ArrayList<UltimateAttack> replaceAttack(ArrayList<UltimateAttack> s,
			UltimateAttack oldAttack, UltimateAttack newAttack) {
		
		for(int i=0;i<s.size();i++){
			
			Attack a=s.get(i);
			
			if(a.getName().equals(oldAttack.getName()) && a.getDamage()==oldAttack.getDamage() ){
				s.remove(i);
			    break;
			}
			
			
		}
		
		s.add(newAttack);
		return s;
		
	}
	private boolean DublicateAttacks(ArrayList<SuperAttack> s,
			SuperAttack newAttack) {
		
		
		for(int i=0;i<s.size();i++){
			
		Attack AttackList=s.get(i);	
		
		if(AttackList.equals(newAttack))
			return true;
		
		}
		
		return false;
	}
	
	private boolean DublicateAttacks(ArrayList<UltimateAttack> s,
			UltimateAttack newAttack) {
		
		
		for(int i=0;i<s.size();i++){
			
		Attack AttackList=s.get(i);	
		
		if(AttackList.equals(newAttack))
			return true;
		
		}
		
		return false;
	}
	
	

	
}

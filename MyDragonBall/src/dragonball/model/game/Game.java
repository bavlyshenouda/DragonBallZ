package dragonball.model.game;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;
















import dragonball.model.attack.Attack;
import dragonball.model.attack.MaximumCharge;
import dragonball.model.attack.SuperAttack;
import dragonball.model.attack.SuperSaiyan;
import dragonball.model.attack.UltimateAttack;
import dragonball.model.battle.Battle;
import dragonball.model.battle.BattleEvent;
import dragonball.model.battle.BattleListener;
import dragonball.model.cell.Collectible;
import dragonball.model.cell.EmptyCell;
import dragonball.model.cell.Cell;
import dragonball.model.character.fighter.Fighter;
import dragonball.model.character.fighter.NonPlayableFighter;
import dragonball.model.character.fighter.PlayableFighter;
import dragonball.model.dragon.Dragon;
import dragonball.model.dragon.DragonWish;
import dragonball.model.exceptions.MissingFieldException;
import dragonball.model.exceptions.UnknownAttackTypeException;
import dragonball.model.player.Player;
import dragonball.model.player.PlayerListener;
import dragonball.model.world.World;
import dragonball.model.world.WorldListener;

public class Game implements PlayerListener, WorldListener, BattleListener,Serializable {
	
	private static final long serialVersionUID = 1L;
	private Player player;
	private World world;
	private ArrayList<NonPlayableFighter> weakFoes;
	private ArrayList<NonPlayableFighter> strongFoes;
	private ArrayList<Attack> attacks;
	private ArrayList<Dragon> dragons;
	private GameState state;
    private GameListener listener ;

	public Game() {
		weakFoes = new ArrayList<NonPlayableFighter>();
		strongFoes = new ArrayList<NonPlayableFighter>();
		attacks = new ArrayList<Attack>();
		dragons = new ArrayList<Dragon>();

		try {
			loadAttacks("Database-Attacks.csv");
		} catch (IOException e) {
            try {
				attacks=new ArrayList<Attack>(); 
            	loadAttacks("Database-Attacks-aux.csv");
			    }
             catch (IOException e1) {
			 }
		}
		try {
			loadFoes("Database-Foes-Range1.csv");
		} catch (IOException e) {
            try {
				weakFoes=new ArrayList<NonPlayableFighter>();
				strongFoes =new ArrayList<NonPlayableFighter>();
            	loadFoes("Database-Foes-aux.csv");
			} catch (IOException e1) {
			}
		}
		try {
			loadDragons("Database-Dragons.csv");
		} catch (IOException e) {
            try {
				dragons =new ArrayList<Dragon>();
            	loadDragons("Database-Dragons-aux.csv");
			} catch (IOException e1) {
			}
		}
	
		


		world = new World();
		world.generateMap(weakFoes, strongFoes);
		world.setListener(this);
		state=GameState.WORLD ;
		
		player=new Player("new player");
		player.setListener(this);
		
			
	}
	
	public GameListener getListener() {
		return listener;
	}

	public void setListener(GameListener gameListener) {
		this.listener = gameListener;
	}

	public Player getPlayer() {
		return player;
	}

	public GameState getState() {
		return state;
	}

	public World getWorld() {
		return world;
	}

	public ArrayList<NonPlayableFighter> getWeakFoes() {
		return weakFoes;
	}

	public ArrayList<NonPlayableFighter> getStrongFoes() {
		return strongFoes;
	}

	public ArrayList<Attack> getAttacks() {
		return attacks;
	}

	public ArrayList<Dragon> getDragons() {
		return dragons;
	}

	private ArrayList<String> loadCSV(String filePath) throws IOException {
		
		String currentLine = "";
		FileReader fileReader = null;
		fileReader = new FileReader(filePath);

		@SuppressWarnings("resource")
		BufferedReader br = new BufferedReader(fileReader);
		ArrayList<String> s=new ArrayList<String>() ;  

			while( (currentLine = br.readLine() ) != null){
			s.add(currentLine);
			}
		return s;
		
	}

	public void loadAttacks(String filePath) throws IOException,MissingFieldException,UnknownAttackTypeException {
		
	   ArrayList<String> s;

	  s=loadCSV(filePath);
	  attacks=new ArrayList<Attack>();
	  String s1[];
	  int x=0;
	  
	     while(!s.isEmpty()){
	    	 
	      s1=s.remove(0).split(",");
	      Attack a;
	      x++;
	      
	      if(s1.length!=3)
	    	  throw new MissingFieldException("The "+filePath+" is Corrupted in line "+x+","+(3-s1.length)+" Fields were missing ",
	    			  filePath,x,3-s1.length);
	      
	      switch(s1[0]){
	        case "SA":a=new SuperAttack(s1[1],Integer.parseInt(s1[2]) );break;
	        case "UA":a=new UltimateAttack(s1[1],Integer.parseInt(s1[2]) );break;
	        case "MC":a=new MaximumCharge();break;
	        case "SS":a=new SuperSaiyan();break;
	        default: a=null; throw new UnknownAttackTypeException("The "+filePath+" is Corrupted in line "+x,filePath,x,s1[0]);
	      }
	       
	     
	          attacks.add(a);
	     }
	 
	     
	}

	public void loadFoes(String filePath) throws IOException,MissingFieldException,UnknownAttackTypeException {
		
	  ArrayList <String> s=loadCSV(filePath);
	   weakFoes=new ArrayList<NonPlayableFighter>();
	   strongFoes=new ArrayList<NonPlayableFighter>();
	   int x=1;
	  
		while(!s.isEmpty()){
			
			
		String s1[]=s.remove(0).split(",");	
		String s2[],s3[] ;
		
		if(s1.length!=8)
		    throw new MissingFieldException("The "+filePath+" is Corrupted in line "+x+","+(8-s1.length)+" Fields were missing ",
		    		filePath,x,8-s1.length);
			
		
		x=x+3;
		
		if(!s.isEmpty())
			s2=s.remove(0).split(",");
		else
			s2=new String[0];
		
		if(!s.isEmpty())
			s3=s.remove(0).split(",");
		else
			s3=new String[0];
		
		if(s2.length>0)
			if(s2[0].equals(""))
				s2=new String[0];
		
		if(s3.length>0)
			if(s3[0].equals(""))
				s3=new String[0];
		
		
			
			
		ArrayList<SuperAttack> sa=new ArrayList<SuperAttack>();
		ArrayList<UltimateAttack> ua=new ArrayList<UltimateAttack>();
		
		
		    for(int i=0;i<s2.length;i++)
		    	sa.add(getSA(s2[i]));
		
		
		    for(int i=0;i<s3.length;i++)
		    	ua.add(getUA(s3[i]));
		     
		    
		    
		    int lvl,health,BlastDamage,physicalDamage,MaxKi,MaxStamina ;
		    
		    lvl=Integer.parseInt(s1[1]);
		    health=Integer.parseInt(s1[2]);
		    BlastDamage=Integer.parseInt(s1[3]);
		    physicalDamage=Integer.parseInt(s1[4]);
		    MaxKi=Integer.parseInt(s1[5]);
		    MaxStamina=Integer.parseInt(s1[6]);
		    
		    boolean strong="TRUE".equals(s1[7]);
		   
		
		    NonPlayableFighter a=new NonPlayableFighter(s1[0],lvl,health,BlastDamage,physicalDamage,MaxKi,MaxStamina,strong,sa,ua);
		    
		    
		if(a.isStrong())
			strongFoes.add(a);
		else
			weakFoes.add(a);
		
		}
		

	}



	public void loadDragons(String filePath) throws IOException,MissingFieldException,UnknownAttackTypeException {
		 ArrayList <String> s=loadCSV(filePath);

		 
		  dragons=new ArrayList<Dragon>();
		  
		  int x=1;
		  
			while(!s.isEmpty()){
				
				
			String s1[]=s.remove(0).split(",");	
			String s2[]=s.remove(0).split(",");
			String s3[]=s.remove(0).split(",");
			
				
			if(s1.length!=3)
				throw new MissingFieldException("The "+filePath+" is Corrupted in line "+x+","+(3-s1.length)+" Fields were missing ",
						filePath,x,3-s1.length);
			
			x=x+3;
			
			ArrayList<SuperAttack> sa=new ArrayList<SuperAttack>();
			ArrayList<UltimateAttack> ua=new ArrayList<UltimateAttack>();
			
			
			    for(int i=0;i<s2.length;i++)
			    	sa.add(getSA(s2[i]));
			
			
			    for(int i=0;i<s3.length;i++)
			    	ua.add(getUA(s3[i]));
			     
			    
			    
			    int beans,abilityP ;
			    
	            
			    beans=Integer.parseInt(s1[1]);
			    abilityP=Integer.parseInt(s1[2]);
			    
			    Dragon a=new Dragon(s1[0],sa,ua,beans,abilityP);
			    dragons.add(a);
			    
			}
			
			
	}


	  public SuperAttack getSA(String s){
		
		
		for(int i=0;i<attacks.size();i++){
			
		    if(s.equals(attacks.get(i).getName()))
		    	return (SuperAttack) attacks.get(i);

		}  
		  
		  return null;
	  }

	  public UltimateAttack getUA(String s){
		
			for(int i=0;i<attacks.size();i++){
				
			    if(s.equals(attacks.get(i).getName()))
			    	return (UltimateAttack) attacks.get(i);

			}  
			  
			  return null;
	  }

	@Override
	public void onBattleEvent(BattleEvent e) {
	
		boolean flag=false;
		
		switch(e.getType()){
		case STARTED :state=GameState.BATTLE ;break;
		case ENDED   :state=GameState.WORLD ;flag=true;break;
		default: break;
		}
		   
		   if(listener!=null)
			   listener.onBattleEvent(e);
	   
		int hp=((Fighter)((Battle)e.getSource()).getDefender()).getHealthPoints();
		
	   if(flag && hp<=0){
	   PlayableFighter f=player.getActiveFighter();
	   NonPlayableFighter f2=(NonPlayableFighter)((Battle)e.getSource()).getFoe();
	   
	   if(f==e.getWinner()){
	   f.setXp(f2.getLevel()*5+f.getXp());
	  
	   
	   ArrayList <SuperAttack> sa=player.getSuperAttacks() ;
	   sa.addAll(f2.getSuperAttacks());
	   player.setSuperAttacks(sa);
	   
	   ArrayList <UltimateAttack> ua=player.getUltimateAttacks() ;
	   ua.addAll(f2.getUltimateAttacks());
	   player.setUltimateAttacks(ua);
	   
	   Cell[][] c=world.getMap();
	   c[world.getPlayerRow()][world.getPlayerColumn()]=new EmptyCell();
	   
	   }
	   else{
	       try{
		     load("saved");
	       }
	       catch(Exception e1){
	    	   
	   		world = new World();
			world.generateMap(weakFoes, strongFoes);
			world.setListener(this);
			state=GameState.WORLD ;
			
			player=new Player(player.getName());
			player.setListener(this);
	       }
		   
		   
		   
	   
	   
	   }
		  	   
	   if(f2.isStrong() && f==e.getWinner() ){
           player.setExploredMaps(player.getExploredMaps()+1);
           world=new World();
           world.generateMap(weakFoes, strongFoes);
           world.resetPlayerPosition();
           world.setListener(this);
	   }
	}

	
	}

	@Override
	public void onFoeEncountered(NonPlayableFighter foe) {
		
		Battle b=new Battle(player.getActiveFighter(),foe);
		state=GameState.BATTLE;
		b.setListener(this);
		b.start();
	       	
	}

	@Override
	public void onCollectibleFound(Collectible collectible) {
		
		switch(collectible){
		
		case SENZU_BEAN : player.setSenzuBeans(player.getSenzuBeans()+1);break;
		case DRAGON_BALL: player.setDragonBalls(player.getDragonBalls()+1);break;
		}
		
		if(player.getDragonBalls()==7)
			player.callDragon();

		
		
		
		if(listener!=null)
			   listener.onCollectibleFound(collectible);
		
	}

	@Override
	public void onDragonCalled() {
		Random r=new Random();
		
		state=GameState.DRAGON ;
		
		Dragon d=dragons.get(r.nextInt(dragons.size()));
		
		if(listener!=null)
			listener.onDragonCalled(d);
	}

	@Override
	public void onWishChosen(DragonWish wish) {
		state=GameState.WORLD ;
		
	}
	
	public void save(String fileName){
		
	
        
		try{
		FileOutputStream fileOut =new FileOutputStream(fileName);
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        out.writeObject(this);
        out.close();
        fileOut.close();
		}
		catch(IOException i){			
		}
	}
	
	public void load(String fileName){
		
        try{
		FileInputStream fileIn = new FileInputStream(fileName);
        ObjectInputStream in = new ObjectInputStream(fileIn);
        Game g = (Game) in.readObject();
        in.close();
        fileIn.close();
        
        this.player=g.player;
        this.world=g.world;
        this.listener=g.listener; 
        this.state=g.state;
        this.weakFoes=g.weakFoes;
        this.strongFoes=g.strongFoes;
        this.attacks=g.attacks;
        this.dragons=g.dragons;
        this.player.setListener(this);
        this.world.setListener(this);
        
        
        }
        catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
      }
	
}

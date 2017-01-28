	package dragonball.model.dragon;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

import dragonball.model.attack.SuperAttack;
import dragonball.model.attack.UltimateAttack;

public class Dragon implements Serializable {

	
	private static final long serialVersionUID = 1L;
	private String name;
	private ArrayList<SuperAttack> superAttacks;
	private ArrayList<UltimateAttack> ultimateAttacks;
	private int senzuBeans;
	private int abilityPoints;

	public Dragon(String name, ArrayList<SuperAttack> superAttacks, ArrayList<UltimateAttack> ultimateAttacks,
			int senzuBeans, int abilityPoints) {
		this.name = name;
		this.superAttacks = superAttacks;
		this.ultimateAttacks = ultimateAttacks;
		this.senzuBeans = senzuBeans;
		this.abilityPoints = abilityPoints;
	}

	public String getName() {
		return name;
	}

	public ArrayList<SuperAttack> getSuperAttacks() {
		return superAttacks;
	}

	public ArrayList<UltimateAttack> getUltimateAttacks() {
		return ultimateAttacks;
	}

	public int getSenzuBeans() {
		return senzuBeans;
	}

	public int getAbilityPoints() {
		return abilityPoints;
	}
	public DragonWish[] getWishes(){
		
	Random r=new Random();
		
	ArrayList<DragonWish> list=new ArrayList<DragonWish>(); 
		
	if(senzuBeans>0){
		DragonWish a=new DragonWish(this,DragonWishType.SENZU_BEANS,senzuBeans);
	    list.add(a);
	}	
	
	if(abilityPoints>0){
		DragonWish a=new DragonWish(this,DragonWishType.ABILITY_POINTS,abilityPoints);
	    list.add(a);
	}
	
	if(superAttacks!=null && superAttacks.size()>0){
		
		int x=r.nextInt(superAttacks.size() );
		SuperAttack s=superAttacks.get(x);		
		DragonWish a=new DragonWish(this,DragonWishType.SUPER_ATTACK,s);
	    list.add(a);
		}
	
	if(ultimateAttacks!=null && ultimateAttacks.size()>0){
		
		int x=r.nextInt(ultimateAttacks.size() );
		UltimateAttack s=ultimateAttacks.get(x);		
		DragonWish a=new DragonWish(this,DragonWishType.ULTIMATE_ATTACK,s);
	    list.add(a);
		}
		
	int n=list.size();	
    DragonWish []a=new DragonWish[n];

    for(int i=0;i<n;i++){	
    a[i]=list.get(i);	
    }
	
    return a;
	
	}
}

package dragonball.model.cell;

public enum Collectible {
	SENZU_BEAN, DRAGON_BALL;
	
	public String toString() {
		
		switch(this){
		case  SENZU_BEAN: return "Senzu Bean";
		default :return "Dragon Ball";
		
		
		}
	}
}

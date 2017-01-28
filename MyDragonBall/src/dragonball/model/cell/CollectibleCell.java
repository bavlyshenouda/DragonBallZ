package dragonball.model.cell;

@SuppressWarnings("serial")
public class CollectibleCell extends Cell {
	private Collectible collectible;

	public CollectibleCell(Collectible collectible) {
		this.collectible = collectible;
	}

	public Collectible getCollectible() {
		return collectible;
	}

	@Override
	public String toString(){
		if(collectible==Collectible.DRAGON_BALL)
			return "[d]";
		return "[s]";
	}

	@Override
	public void onStep() {
		
	  if(getListener()!=null){
		getListener().onCollectibleFound(collectible);
	  }
	}
}

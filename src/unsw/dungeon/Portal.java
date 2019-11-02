package unsw.dungeon;

public class Portal extends Entity implements Immovable{
//	PortalState blockedState;
//	PortalState unblockedState;
	PortalState state;
	private int portalID;
	public Portal(int x, int y, int portalID) {
        super(x, y);
        this.portalID = portalID;
//        blockedState = new BlockedState(this);
//        unblockedState = new UnblockedState(this);
//        state = unblockedState;
    }
	
	public void teleport(Player player, Dungeon dungeon , int x, int y, String direction) {
		for (Entity e : dungeon.getEntitiesList()) {
			if (e instanceof Portal && !e.equals(this)) {
				if (((Portal) e).getPortalID() == portalID) {
					player.move(x, y, direction);
					if(!checkBlocked(e.getX(), e.getY(), dungeon)) {
						player.setXandY(e.getX(), e.getY());
					}
				}
			}	
		}
	}
	
	public boolean checkBlocked(int x, int y, Dungeon dungeon) {
		
		for(Entity e: dungeon.getEntitiesList()) {
			if(e instanceof Boulder) {
				Boulder b = (Boulder)e;
				if(b.getX() == x && b.getY() == y) return true;
			}
		}
		return false;
	}
	
	public int getPortalID() {
		return portalID;
	}
	public void setPortalID(int portalID) {
		this.portalID = portalID;
	}

	@Override
	public void collide(Player player, int x, int y, String direction) {
		teleport(player, player.getDungeon(),x,y,direction);
	}
}

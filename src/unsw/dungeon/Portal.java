package unsw.dungeon;

public class Portal extends Entity implements Immovable{

	private int portalID;
	public Portal(int x, int y, int portalID) {
        super(x, y);
        this.portalID = portalID;
    }
	
	
	/**
	 * this handles the event of a Player colliding with a portal. Depending if a boulder is blocking
	 * the other side of the portal, it will either teleport the player to the other Linked portal
	 * or player will not enter the portal.
	 * @param player
	 * @param dungeon
	 * @param x
	 * @param y
	 * @param direction
	 */
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
	
	/**
	 * This determines if the linked portal is blocked by a boulder.
	 * @param x
	 * @param y
	 * @param dungeon
	 * @return
	 */
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

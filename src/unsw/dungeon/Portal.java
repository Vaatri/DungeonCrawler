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
	public void teleport() {
		Player player = getDungeon().getPlayer();
		for (Entity e : getDungeon().getEntitiesList()) {
			if (e instanceof Portal && !e.equals(this)) {
				if (((Portal) e).getPortalID() == portalID) {
					if(!checkBlocked(e.getX(), e.getY())) {
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
	public boolean checkBlocked(int x, int y) {
		
		for(Entity e: getDungeon().getEntitiesList()) {
			if(e.getX() == x && e.getY() == y) return true;
			
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
	public void collide() {
		Player p = getDungeon().getPlayer();
		if(checkPos(p.getX(), p.getY(), getX(), getY())) {
			teleport();
		}
	}
	@Override
	public boolean checkCollision(int x, int y, String dir) {
		return true; 
	}
}

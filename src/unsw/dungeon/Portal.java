package unsw.dungeon;

import java.util.List;

public class Portal extends Entity implements Immovable{

	private int portalID;
	private boolean teleported;
	public Portal(int x, int y, int portalID) {
        super(x, y);
        this.portalID = portalID;
        this.teleported = false;
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
		Portal p = getDungeon().findLinkedPortal(this);
		
		if(!checkBlocked(p.getX(),p.getY())) {
			player.setXandY(p.getX(), p.getY());
			p.setTeleported();
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
		Dungeon d = getDungeon();
		List<Entity> atLoc = d.getEntityAtLocation(x, y);
		if(atLoc.size() == 2 || getTeleported()) {
			return true;
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
		resetPortal();
		return true; 
	}
	
	public void setTeleported() {
		teleported = true;
	}
	public void resetPortal() {
		teleported = false;
	}
	
	public boolean getTeleported() {
		return teleported;
	}
}

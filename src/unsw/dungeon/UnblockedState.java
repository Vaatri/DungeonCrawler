package unsw.dungeon;

public class UnblockedState implements PortalState{
	Portal portal;
	public UnblockedState(Portal portal) {
		this.portal = portal;
	}
	public void teleport(Player player, Dungeon dungeon , int x, int y, String direction) {
		for (Entity e : dungeon.getEntitiesList()) {
			if (e instanceof Portal && e.getX() != portal.getX()) {
				
				if (((Portal) e).getPortalID() == portal.getPortalID()) {
					player.move(x, y, direction);
					player.setXandY(e.getX(), e.getY());
				}
			}
		}
	}
}

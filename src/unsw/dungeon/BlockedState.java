package unsw.dungeon;

public class BlockedState implements PortalState{
	Portal portal;
	public BlockedState(Portal portal) {
		this.portal = portal;
	}
	public void teleport(Player player, Dungeon dungeon, int x, int y, String direction) {
		System.out.println("The other side of the portal is blokcked, cannot teleport");
	}
}

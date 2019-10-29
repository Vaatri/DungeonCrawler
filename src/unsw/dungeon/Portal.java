package unsw.dungeon;

public class Portal extends Entity {
	PortalState blockedState;
	PortalState unblockedState;
	PortalState state;
	private int portalID;
	public Portal(int x, int y, int portalID) {
        super(x, y);
        blockedState = new BlockedState(this);
        unblockedState = new UnblockedState(this);
        state = unblockedState;
    }
	public int getPortalID() {
		return portalID;
	}
	public void setPortalID(int portalID) {
		this.portalID = portalID;
	}
	public void teleport(Player p, Dungeon dungeon, int x, int y, String direction) {
		state.teleport(p, dungeon, x, y, direction);
	}
	public PortalState getBlockedState() {
		return blockedState;
	}

	public void setBlockedState(PortalState blockedState) {
		this.blockedState = blockedState;
	}

	public PortalState getUnblockedState() {
		return unblockedState;
	}

	public void setUnblockedState(PortalState unblockedState) {
		this.unblockedState = unblockedState;
	}

	public PortalState getState() {
		return state;
	}

	public void setState(PortalState state) {
		this.state = state;
	}

	
}

package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

public class SingleGoal implements Goal, Observer {
	
	
	private String type;
	private int 
	
	
	public SingleGoal(String type) {
		this.type = type;
		
	}
	
	@Override
	public boolean goalSatisfied() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public void addGoal() {
		
	}
	
	@Override
	public void removeGoal() {
		
	}

}

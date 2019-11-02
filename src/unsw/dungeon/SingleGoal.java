package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

public class SingleGoal implements Goal, Observer {
	
	
	private String type;
	private int goalsSatisfied;
	private int neededToSatisfy;
	
	public SingleGoal(String type, int neededToSatisfy) {
		this.type = type;
		this.goalsSatisfied = 0;
		this.neededToSatisfy = neededToSatisfy;
		
	}
	
	
	@Override
	public void addGoal(Goal g) {
		
	}
	
	@Override
	public void removeGoal() {
		
	}
	
	@Override
	public void setNeededToSatisfy(int i) {
		neededToSatisfy = i;
	}
	
	public String toString() {
		return "goal type: "+type+" "+neededToSatisfy;
	}
	
	@Override 
	public void update(Subject obj) {
		goalsSatisfied++;
	}
	
	@Override
	public boolean checkCompleted() {
		if (goalsSatisfied == neededToSatisfy) 
			return true;
		else 
			return false;
	}

}

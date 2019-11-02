package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

public class ComplexGoal implements Goal {

	List<Goal> goalList = new ArrayList<Goal>();
	private String type;
	private int goalsSatisfied;
	private int neededToSatisfy;
	
	public ComplexGoal(String type, int neededToSatisfy) {
		this.type = type;
		this.goalsSatisfied = 0;
		this.neededToSatisfy = neededToSatisfy;
		
	}

	
	@Override
	public void addGoal(Goal g) {
		goalList.add(g);
	}
	
	@Override
	public void removeGoal() {
		
	}
	@Override
	public void setNeededToSatisfy(int i) {
		neededToSatisfy = i;
	}
	
	public String toString() {
		return "neededToSatisfy: " +neededToSatisfy+" containing Goals: "+goalList;
	}
	
	@Override
	public boolean checkCompleted() {
		int goalsFinished = 0;
		for(Goal g : goalList) {
			if (g.checkCompleted()) {
				goalsFinished++;
			}
		}
		
		goalsSatisfied = goalsFinished;
		if(goalsSatisfied >= neededToSatisfy)
			return true;
		
		return false;
	}
}

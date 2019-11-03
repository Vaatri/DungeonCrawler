package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

public class ComplexGoal implements Goal {

	List<Goal> goalList = new ArrayList<Goal>();
	private String type;
	private int goalsSatisfied;
	private int neededToSatisfy;
	private int goalPoints;
	
	public ComplexGoal(String type, int neededToSatisfy) {
		this.type = type;
		this.goalsSatisfied = 0;
		this.neededToSatisfy = neededToSatisfy;
		this.goalPoints = 0;
	}

	public List<Goal> getGoalList(){
		return goalList;
	}
	
	@Override
	public String getType() {
		return type;
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
				goalsFinished += g.getGoalPoints();
			}
		}
		
		goalsSatisfied = goalsFinished;
		if(goalsSatisfied >= neededToSatisfy) {
			return true;
		}	
		
		return false;
	}
	
	@Override
	public int getGoalPoints() {
		return goalPoints;
	}
}

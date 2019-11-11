package unsw.dungeon;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class ComplexGoal implements Goal {

	List<Goal> goalList = new ArrayList<Goal>();
	private String type;
	private IntegerProperty AndGoalsSatisfied;
	private IntegerProperty totalAndGoals;
	private boolean OrSatisfied;
	
	/**
	 * Complex goal contains a list of single goals
	 * The type of goal it is.
	 * The amount of goals that have been satisfied
	 * The amount of goals that need to be satisfied
	 * @param type
	 * @param neededToSatisfy
	 */
	public ComplexGoal(String type) {
		this.type = type;
		this.AndGoalsSatisfied = new SimpleIntegerProperty(0);
		this.OrSatisfied = false;
		this.totalAndGoals = new SimpleIntegerProperty(0);
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
//	public String toString() {
//		return "neededToSatisfy: " +neededToSatisfy+" containing Goals: "+goalList;
//	}
	
	/**
	 * this will check all of the single Goals within complex goal's list
	 * and see if there are the required goals satisfied.
	 * if all required goals are satisfied, this will return true.
	 */
	@Override
	public boolean checkCompleted() {
		for(Goal g : goalList) {
			g.setCompleted(this);
		}
		
		if(totalAndGoals == AndGoalsSatisfied && OrSatisfied)
			return true;
		
		return false;
	}

	@Override
	public void setNeededToSatisfy(int i) {
		// TODO Auto-generated method stub
		totalAndGoals.set(i);
	}
	
	@Override
	public int getNeededToSatisfy() {
		return totalAndGoals.get();
	}
	

	@Override
	public int getGoalsSatisfied() {
		// TODO Auto-generated method stub
		return AndGoalsSatisfied.get();
	}

	@Override
	public void addSatisfied() {
		// TODO Auto-generated method stub
		AndGoalsSatisfied.add(getGoalsSatisfied()+1);
	}

	@Override
	public void setCompleted(Goal g) {
		// TODO Auto-generated method stub
		OrSatisfied = true;
	}
	
	@Override
	public IntegerProperty propertyNTS() {
		// TODO Auto-generated method stub
		return totalAndGoals;
	}


	@Override
	public IntegerProperty propertyGS() {
		// TODO Auto-generated method stub
		return AndGoalsSatisfied;
	}

	@Override
	public boolean getMandatory() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public BooleanProperty getMandoProperty() {
		// TODO Auto-generated method stub
		return null;
	}
	
}

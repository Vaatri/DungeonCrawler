package unsw.dungeon;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class SingleGoal implements Goal, Observer {
	
	
	private String type;
	private IntegerProperty goalsSatisfied;
	private IntegerProperty neededToSatisfy;
	private GoalCriteria goalCriteria;
	private BooleanProperty mandatory;
	
	public SingleGoal(String type, int neededToSatisfy, GoalCriteria gc, boolean mandatory) {
		this.type = type;
		this.goalsSatisfied = new SimpleIntegerProperty(0);
		this.neededToSatisfy = new SimpleIntegerProperty(neededToSatisfy);
		this.goalCriteria = gc;
		this.mandatory = new SimpleBooleanProperty(mandatory);
	}
	
	
	@Override
	public String getType() {
		return type;
	}
	
	@Override
	public void addGoal(Goal g) {
		
	}
	
	@Override
	public void removeGoal() {
		
	}
	
	@Override
	public void setNeededToSatisfy(int i) {
		neededToSatisfy.set(i);
	}
	
	@Override
	public int getNeededToSatisfy() {
		return neededToSatisfy.get();
	}
	
	@Override
	public int getGoalsSatisfied() {
		return goalsSatisfied.get();
	}
	
	public String toString() {
		return "goal type: "+type+" "+neededToSatisfy;
	}
	
	@Override
	public boolean getMandatory() {
		return mandatory.get();
	}
	
	@Override
	public BooleanProperty getMandoProperty() {
		return mandatory;
	}
	
	/**
	 * depending on the subject, it will update the number of entity it requires for the
	 * Single goal to be satisfied.
	 */
	@Override 
	public void update(Subject obj) {
		String subjectType = obj.getType();
		if (subjectType.equals("enemy")) {
			subjectType = "enemies";
		}
		if(subjectType.equals("switch")) {
			FloorSwitch fs = (FloorSwitch)obj;
			if(fs.getTriggerStatus())
				addSatisfied();
			else if (!fs.getTriggerStatus())
				goalsSatisfied.set(getGoalsSatisfied() - 1);
		}
		
		System.out.println(type+" "+subjectType);
		if(type.equals(subjectType) && !subjectType.equals("switch")) {
			addSatisfied();
		}	
		
		checkCompleted();
	}
	
	/**
	 * return true if SingleGoal is completed
	 */
	@Override
	public boolean checkCompleted() {
		if (goalsSatisfied.get() == neededToSatisfy.get()) {
			return true;
		}else {
			return false;
		}	
	}
	


	@Override
	public void setCompleted(Goal g) {
		// TODO Auto-generated method stub
		if (checkCompleted())
			goalCriteria.setCompleted(g);
	}


	@Override
	public void addSatisfied() {
		// TODO Auto-generated method stub
		goalsSatisfied.set(getGoalsSatisfied() + 1);
		
	}


	@Override
	public IntegerProperty propertyNTS() {
		// TODO Auto-generated method stub
		return neededToSatisfy;
	}


	@Override
	public IntegerProperty propertyGS() {
		// TODO Auto-generated method stub
		return goalsSatisfied;
	}


	
}

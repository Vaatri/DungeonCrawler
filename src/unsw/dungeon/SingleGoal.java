package unsw.dungeon;

public class SingleGoal implements Goal, Observer {
	
	
	private String type;
	private int goalsSatisfied;
	private int neededToSatisfy;
	private GoalCriteria goalCriteria;
	private boolean completed;
	
	public SingleGoal(String type, int neededToSatisfy, GoalCriteria gc) {
		this.type = type;
		this.goalsSatisfied = 0;
		this.neededToSatisfy = neededToSatisfy;
		this.goalCriteria = gc;
		this.completed = false;
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
		neededToSatisfy = i;
	}
	
	public int getNeededToSatisfy() {
		return neededToSatisfy;
	}
	
	@Override
	public int getGoalsSatisfied() {
		return goalsSatisfied;
	}
	
	public String toString() {
		return "goal type: "+type+" "+neededToSatisfy;
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
				goalsSatisfied--;
		}
		
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
		if (goalsSatisfied == neededToSatisfy) {
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
		goalsSatisfied++;
		
	}


	
}

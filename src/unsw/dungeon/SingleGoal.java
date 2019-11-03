package unsw.dungeon;

public class SingleGoal implements Goal, Observer {
	
	
	private String type;
	private int goalsSatisfied;
	private int neededToSatisfy;
	private int goalPoints;
	
	public SingleGoal(String type, int neededToSatisfy, int gp) {
		this.type = type;
		this.goalsSatisfied = 0;
		this.neededToSatisfy = neededToSatisfy;
		this.goalPoints = gp;
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
	
	public int getGoalsSatisfied() {
		return goalsSatisfied;
	}
	
	public String toString() {
		return "goal type: "+type+" "+neededToSatisfy;
	}
	
	@Override 
	public void update(Subject obj) {
		String subjectType = obj.getType();
		if (subjectType.equals("enemy")) {
			subjectType = "enemies";
		}
		if(subjectType.equals("switch")) {
			FloorSwitch fs = (FloorSwitch)obj;
			if(fs.getTriggerStatus())
				goalsSatisfied++;
			else if (!fs.getTriggerStatus())
				goalsSatisfied--;
		}
		
		if(type.equals(subjectType) && !subjectType.equals("switch")) {
			goalsSatisfied++;
		}	
		
		checkCompleted();
	}
	
	@Override
	public boolean checkCompleted() {
		if (goalsSatisfied == neededToSatisfy) {
			return true;
		}else 
			return false;
	}
	
	@Override
	public int getGoalPoints() {
		return goalPoints;
	}

}

package unsw.dungeon;

public class SingleGoal implements Goal, Observer {
	
	
	private String type;
	private String subType;
	private int goalsSatisfied;
	private int neededToSatisfy;
	
	public SingleGoal(String type, String subType, int neededToSatisfy) {
		this.type = type;
		this.subType = subType;
		this.goalsSatisfied = 0;
		this.neededToSatisfy = neededToSatisfy;
		
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
			System.out.println("Single Goal Completed");
			return true;
		}else 
			return false;
	}

}

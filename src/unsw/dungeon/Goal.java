package unsw.dungeon;

public interface Goal {
	
	public void addGoal(Goal g);
	public void removeGoal();
	public void setNeededToSatisfy(int i);
	public void addSatisfied();
	public boolean checkCompleted();
	public String getType();
	public int getGoalsSatisfied();
	public void setCompleted(Goal g);
}

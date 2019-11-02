package unsw.dungeon;

public interface Goal {
	
	public void addGoal(Goal g);
	public void removeGoal();
	public void setNeededToSatisfy(int i);
	public boolean checkCompleted();
	
}

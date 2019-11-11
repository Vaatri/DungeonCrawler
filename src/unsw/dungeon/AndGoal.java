package unsw.dungeon;

public class AndGoal implements GoalCriteria {

	@Override
	public boolean setCompleted(Goal g) {
		// TODO Auto-generated method stub
		g.addSatisfied();
		return false;
	}

}

package unsw.dungeon;

public class OrGoal implements GoalCriteria {

	@Override
	public boolean setCompleted(Goal g) {
		// TODO Auto-generated method stub
		g.setCompleted(g);
		return false;
	}

}

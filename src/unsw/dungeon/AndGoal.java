package unsw.dungeon;

public class AndGoal implements GoalCriteria {

		/**
		 * Marks the AND goal as compeleted
		 */
	@Override
	public boolean setCompleted(Goal g) {
		g.addSatisfied();
		return false;
	}

}

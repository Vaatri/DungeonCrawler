package unsw.dungeon;

public class OrGoal implements GoalCriteria {

	/** 
	 * If Or goal is completed, it will set the complex goals 
	 * OrGoalSatisfied attribute to completed.
	 */
	@Override
	public boolean setCompleted(Goal g) {
		// TODO Auto-generated method stub
		g.setCompleted(g);
		return false;
	}

}

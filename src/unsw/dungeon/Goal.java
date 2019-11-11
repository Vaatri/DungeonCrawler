package unsw.dungeon;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;

public interface Goal {
	
	public void addGoal(Goal g);
	public void removeGoal();
	public void setNeededToSatisfy(int i);
	public void addSatisfied();
	public boolean checkCompleted();
	public String getType();
	public int getGoalsSatisfied();
	public void setCompleted(Goal g);
	public int getNeededToSatisfy();
	public IntegerProperty propertyNTS();
	public IntegerProperty propertyGS();
	public boolean getMandatory();
	public BooleanProperty getMandoProperty();
}

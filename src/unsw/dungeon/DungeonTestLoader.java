package unsw.dungeon;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class DungeonTestLoader extends DungeonLoader{
	private List<ImageView> entities;

    //Images
    private Image playerImage;
    private Image wallImage;
    private Image treasureImage;
    private Image portalImage;
    private Image floorSwitchImage;
    private Image doorImage;
    private Image boulderImage;
    private Image enemyImage;
    private Image exitImage;
    private Image potionImage;
    private Image keyImage;
    private Image swordImage;
    private Image openDoorImage;

    public DungeonTestLoader(String filename)
            throws FileNotFoundException {
        super(filename);
    }

    @Override
    public void onLoad(Entity player) {
    }

    @Override
    public void onLoad(Wall wall) {
    }
    @Override
    public void onLoad(Treasure treasure) {
    }
    @Override
    public void onLoad(Portal portal) {
    }
    @Override
    public void onLoad(FloorSwitch floorSwitch) {
    }
    @Override
    public void onLoad(Door door) {
    }
    @Override
    public void onLoad(Boulder boulder) {
    }
    @Override
    public void onLoad(Enemy enemy) {
    }
    @Override
    public void onLoad(Exit exit) {
    }
    @Override
    public void onLoad(Potion potion) {
    }
    @Override
    public void onLoad(Key key) {
    }
    @Override
    public void onLoad(Sword sword) {
    }

    private void addEntity(Entity entity, ImageView view) {
    }

    /**
     * Set a node in a GridPane to have its position track the position of an
     * entity in the dungeon.
     *
     * By connecting the model with the view in this way, the model requires no
     * knowledge of the view and changes to the position of entities in the
     * model will automatically be reflected in the view.
     * @param entity
     * @param node
     */
    private void trackPosition(Entity entity, Node node) {
        
    }

    /**
     * Create a controller that can be attached to the DungeonView with all the
     * loaded entities.
     * @return
     * @throws FileNotFoundException
     */
    public DungeonController loadController() throws FileNotFoundException {
        return new DungeonController(load(), entities);
    }

	@Override
	public void onLoad(Goal goal) {
		// TODO Auto-generated method stub
		
	}

}

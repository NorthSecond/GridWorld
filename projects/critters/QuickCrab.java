/**
 * @author yangyf83
 * @date 2022-10-06
 * @brief The runner class of the <code>QuickCrab</code>, finished in part 4. <br />
 * @version 1.0
 * @notes 2022-10-06: Finish the code of the class.
 * <br /> 2022-10-07: Add the comments and docs.
 */

import info.gridworld.actor.Actor;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import java.util.ArrayList;

/**
 * A <code>QuickCrab</code> moves to one of the two locations, randomly selected, that are two spaces to its right or
 * left, if that location and the intervening location are both empty.<br />
 * Otherwise, a QuickCrab moves like a CrabCritter.
 */
public class QuickCrab extends CrabCritter {
    /**
     * @return the locations to move to.
     */
    @Override
    public ArrayList<Location> getMoveLocations() {
        ArrayList<Location> locs = new ArrayList<>();
        int[] dirs = {Location.LEFT, Location.RIGHT};
        Grid<Actor> gr = getGrid();

        for (int dir : dirs) {
            Location loc = getLocation().getAdjacentLocation(getDirection() + dir);
            if (gr.isValid(loc) && gr.get(loc) == null) {
                Location next = loc.getAdjacentLocation(getDirection() + dir);
                if (gr.isValid(next) && gr.get(next) == null) {
                    locs.add(next);
                }
            }
        }

        return locs;
    }
}


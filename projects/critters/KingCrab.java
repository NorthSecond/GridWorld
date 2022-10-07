/**
 * @author yangyf83
 * @date 2022-10-06
 * @brief The runner class of the <code>KingCrab</code>, finished in part 4. <br />
 * @version 1.0
 * @notes 2022-10-06: Finish the code of the class.
 * <br /> 2022-10-07: Add the comments and docs.
 */

import info.gridworld.actor.Actor;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import java.util.ArrayList;


/**
 * A <code>KingCrab</code> moves like a CrabCritter, but it causes each actor that it processes to move one location
 * further away from the KingCrab. If the actor cannot move away, the KingCrab removes it from the grid. When the
 * KingCrab has completed processing the actors, it moves like a CrabCritter.
 */
public class KingCrab extends CrabCritter {
    /**
     * the method to process the actors. <br />
     *
     * @param actors the actors to be processed
     */
    @Override
    public void processActors(ArrayList<Actor> actors) {
        Grid<Actor> gr = getGrid();
        Location loc = getLocation();
        for (Actor a : actors) {
            Location aLoc = a.getLocation();
            int dir = loc.getDirectionToward(aLoc);
            Location next = aLoc.getAdjacentLocation(dir);
            if (gr.isValid(next) && gr.get(next) == null) {
                a.moveTo(next);
            } else {
                a.removeSelfFromGrid();
            }
        }
    }
}

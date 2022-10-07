/**
 * @author yangyf83
 * @date 2022-10-06
 * @brief The class of the <code>RockHound</code>
 * @version 1.0
 * @note 2022-10-06: Add the code of the class.
 */

import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.actor.Rock;

import java.util.ArrayList;

/**
 * A <code>RockHound</code> is an actor that to be processed in the same way as a Critter.
 * It removes any rocks in that list from the grid.
 */
public class RockHound extends Critter {
    /**
     * The method to process the actors. Remove the rocks in the list.
     * @param actors the list of actors to be processed.
     */
    @Override
    public void processActors(ArrayList<Actor> actors) {
        // remove actors that are rocks
        for (Actor a : actors) {
            if (a instanceof Rock) {
                a.removeSelfFromGrid();
            }
        }
    }
}

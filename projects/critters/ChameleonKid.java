/**
 * @author yangyf83
 * @date 2022-10-06
 * @brief The class of the ChameleonCritter, finished in part 4. <br />
 * @version 1.0
 * @notes 2022-10-07: Finish the code of the class.
 */

import info.gridworld.actor.Actor;
import info.gridworld.grid.Location;

import java.util.ArrayList;

/**
 * A <code>ChameleonKid</code> changes its color to the color of one of the actors
 * immediately in front or behind as it moves through the grid. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class ChameleonKid extends ChameleonCritter {

    /**
     * @return the actors that located in the front or behind of the critter.
     */
    @Override
    public ArrayList<Actor> getActors() {
        ArrayList<Actor> res = new ArrayList<>();
        ArrayList<Actor> neighbors = getGrid().getNeighbors(getLocation());
        int dir = getDirection();
        Location front = getLocation().getAdjacentLocation(dir);
        Location back = getLocation().getAdjacentLocation(dir + 180);
        for (Actor a : neighbors) {
            if (a.getLocation().equals(front) || a.getLocation().equals(back)) {
                res.add(a);
            }
        }
        return res;
    }

}
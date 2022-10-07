/**
 * @author yangyf83
 * @date 2022-10-06
 * @brief The runner class of the <code>KingCrab</code>, finished in part 4. <br />
 * @version 1.0
 * @notes 2022-10-06: Finish the code of the class.
 * <br /> 2022-10-07: Add the comments and docs.
 */

import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Location;

/**
 * This class runs a world that contains KingCrabs. <br />
 * This class is not tested on the AP CS A and AB exams.
 */
public class KingCrabRunner {
    public static void main(String[] args) {
        ActorWorld world = new ActorWorld();
        world.add(new Location(7, 8), new Rock());
        world.add(new Location(3, 3), new Rock());
        world.add(new Location(2, 8), new Rock());
        world.add(new Location(5, 5), new Rock());
        world.add(new Location(7, 2), new Rock());
        world.add(new Location(4, 5), new KingCrab());
        world.add(new Location(5, 7), new KingCrab());

        world.show();
    }
}

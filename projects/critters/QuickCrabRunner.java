/**
 * @author yangyf83
 * @date 2022-10-06
 * @brief The runner class of the <code>QuickCrab</code>, finished in part 4. <br />
 * @version 1.0
 * @notes 2022-10-06: Finish the code of the class.
 * <br /> 2022-10-07: Add the comments and docs.
 */

import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Location;

import java.awt.*;

/**
 * This class runs a world that contains QuickCrab. <br />
 * This class is not tested on the AP CS A and AB exams.
 */
public class QuickCrabRunner {
    public static void main(String[] args) {
        // repeat codes
        ActorWorld world = new ActorWorld();
        world.add(new Location(7, 8), new Rock());
        world.add(new Location(3, 3), new Rock());
        world.add(new Location(2, 8), new Rock(Color.BLUE));
        world.add(new Location(5, 5), new Rock(Color.PINK));
        world.add(new Location(1, 5), new Rock(Color.RED));
        world.add(new Location(7, 2), new Rock(Color.YELLOW));

        QuickCrab alice = new QuickCrab();
        alice.setColor(Color.ORANGE);
        alice.setDirection(Location.EAST);
        world.add(new Location(4, 4), alice);

        QuickCrab bob = new QuickCrab();
        bob.setColor(Color.CYAN);
        bob.setDirection(Location.SOUTH);
        world.add(new Location(5, 8), bob);

        QuickCrab charlie = new QuickCrab();
        charlie.setColor(Color.MAGENTA);
        charlie.setDirection(Location.WEST);
        world.add(new Location(8, 5), charlie);

        world.show();
    }
}

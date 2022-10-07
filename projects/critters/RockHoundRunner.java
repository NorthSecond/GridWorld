/**
 * @author yangyf83
 * @date 2022-10-06
 * @brief TThe runner class of the <code>RockHound</code>, finished in part 4. <br />
 * @version 1.0
 * @notes 2022-10-06: Finish the code of the class.
 */

import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Location;

import java.awt.*;

/**
 * This class runs a world that contains rock hounds. <br />
 * This class is not tested on the AP CS A and AB exams.
 */
public class RockHoundRunner {
    public static void main(String[] args) {
        // repeat codes
        ActorWorld world = new ActorWorld();
        world.add(new Location(7, 8), new Rock());
        world.add(new Location(3, 3), new Rock());
        world.add(new Location(2, 8), new Rock(Color.BLUE));
        world.add(new Location(5, 5), new Rock(Color.PINK));
        world.add(new Location(1, 5), new Rock(Color.RED));
        world.add(new Location(7, 2), new Rock(Color.YELLOW));
        world.add(new Location(4, 5), new Rock());

        RockHound alice = new RockHound();
        alice.setColor(Color.ORANGE);
        alice.setDirection(Location.EAST);
        world.add(new Location(4, 4), alice);

        RockHound bob = new RockHound();
        bob.setColor(Color.CYAN);
        bob.setDirection(Location.SOUTH);
        world.add(new Location(5, 8), bob);

        world.show();
    }
}

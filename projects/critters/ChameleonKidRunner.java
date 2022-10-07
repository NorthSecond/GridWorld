/**
 * @author yangyf83
 * @date 2022-10-06
 * @brief The runner class of the <code>ChameleonKid</code>, finished in part 4. <br />
 * @version 1.0
 * @notes 2022-10-06: Finish the code of the class.
 */

import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Location;

import java.awt.*;


/**
 * This class runs a world that contains chameleon kid critters. <br />
 * This class is not tested on the AP CS A and AB exams.
 */
public class ChameleonKidRunner {
    public static void main(String[] args) {
        // repeat codes
        ActorWorld world = new ActorWorld();
        world.add(new Location(7, 8), new Rock());
        world.add(new Location(3, 3), new Rock());
        world.add(new Location(2, 8), new Rock(Color.BLUE));
        world.add(new Location(5, 5), new Rock(Color.PINK));
        world.add(new Location(1, 5), new Rock(Color.RED));
        world.add(new Location(7, 2), new Rock(Color.YELLOW));

        ChameleonKid alice = new ChameleonKid();
        alice.setColor(Color.ORANGE);
        alice.setDirection(Location.EAST);
        world.add(new Location(4, 4), alice);

        ChameleonKid bob = new ChameleonKid();
        bob.setColor(Color.CYAN);
        bob.setDirection(Location.SOUTH);
        world.add(new Location(5, 8), bob);

        ChameleonKid charlie = new ChameleonKid();
        charlie.setColor(Color.MAGENTA);
        charlie.setDirection(Location.WEST);
        world.add(new Location(8, 5), charlie);

        world.show();
    }
}

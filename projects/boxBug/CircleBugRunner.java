/**
 * @author: yangyf83
 * @date: 2022-09-12
 * @brief: the runner class of circle bug in part 2.
 * @version: 1.0
 * @note: 2022-09-12: Add the code the class.
 * <br /> 2022-10-02: Finish the comments and docs.
 */

import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;

import java.awt.*;

/**
 * The class that runs a world that contains circle bugs. <br />
 */
public class CircleBugRunner {
    /**
     * The main method of the class. <br />
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ActorWorld world = new ActorWorld();
        CircleBug alice = new CircleBug(6);
        alice.setColor(Color.ORANGE);
        CircleBug bob = new CircleBug(3);
        world.add(new Location(7, 8), alice);
        world.add(new Location(5, 5), bob);
        world.show();
    }
}

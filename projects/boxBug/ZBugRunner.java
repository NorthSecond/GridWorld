/**
 * @author: yangyf83
 * @date: 2022-09-12
 * @brief: the runner class of the 'Z' bug in part 2.
 * @version: 1.0
 * @note: 2022-09-12: Add the code of the class.
 * <br /> 2022-10-02: Finish the comments and docs.
 */

import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;

public class ZBugRunner {
    /**
     * @param args the command line arguments.
     */
    public static void main(String[] args) {
        ActorWorld world = new ActorWorld();
        ZBug alice = new ZBug(7);
        world.add(new Location(1, 1), alice);
        world.show();
    }
}

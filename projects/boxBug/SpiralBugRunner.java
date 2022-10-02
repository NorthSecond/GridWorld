/**
 * @author: yangyf83
 * @date: 2022-09-12
 * @brief: the runner class of spiral bug in part 2.
 * @version: 1.0
 * @note: 2022-09-12: Add the code of the class.
 * <br /> 2022-10-02: Finish the comments and docs.
 */

import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.BoundedGrid;
import info.gridworld.grid.Location;

/**
 * This class runs a world that contains spiral bugs.
 */
public class SpiralBugRunner {
    /**
     * @param args the command line arguments.
     */
    public static void main(String[] args) {
        ActorWorld world = new ActorWorld(new BoundedGrid<>(18, 18));
        SpiralBug alice = new SpiralBug(2);
        world.add(new Location(9, 6), alice);
        world.show();
    }
}

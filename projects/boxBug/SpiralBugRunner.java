/**
 *
 */

import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.BoundedGrid;
import info.gridworld.grid.Location;

/**
 * This class runs a world that contains spiral bugs.
 */
public class SpiralBugRunner {
public static void main(String[] args) {
        ActorWorld world = new ActorWorld(new BoundedGrid<>(18, 18));
        SpiralBug alice = new SpiralBug(2);
        world.add(new Location(9, 6), alice);
        world.show();
    }
}

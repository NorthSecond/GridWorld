/**
 *
 */

import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;

public class ZBugRunner {
    public static void main(String[] args) {
        ActorWorld world = new ActorWorld();
        ZBug alice = new ZBug(7);
        world.add(new Location(1, 1), alice);
        world.show();
    }
}

/**
 * @author: yangyf83
 * @date: 2022-09-12
 * @brief: the runner class of dancing bug in part 2.
 * @version: 1.0
 * @note: 2022-09-12: Add the code of the class.
 * <br /> 2022-10-02: Finish the comments and docs.
 */

import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;

public class DancingBugRunner {
    /**
     * The main method of the class. <br />
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ActorWorld world = new ActorWorld();
        int[] turns = new int[8];
        for (int i = 0; i < 8; ++i) {
            turns[i] = i;
        }
        DancingBug alice = new DancingBug(turns);
        world.add(new Location(5, 4), alice);
        world.show();
    }
}

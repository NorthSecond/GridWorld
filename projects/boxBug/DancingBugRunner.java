import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;

public class DancingBugRunner {
    public static void main(String[] args) {
        ActorWorld world = new ActorWorld();
        int[] turns = new int[8];
        for (int i = 0; i < 8; ++i){
            turns[i] = i;
        }
        DancingBug alice = new DancingBug(turns);
        world.add(new Location(5, 4), alice);
        world.show();
    }
}

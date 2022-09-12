import info.gridworld.actor.Bug;

/**
 * A <code>DancingBug</code> traces out a square "box" of a given size. <br />
 */
public class DancingBug extends Bug {
    private int steps;
    private int sideLength;

    // the array of the number of turns when acting
    private final int[] turnArray;
    // the times that the bug has acted.
    private int turnIndex;

    /**
     * Constructs a box bug that traces a square of a given side length.
     * @param length the side length
     * @param turns  the array of the number of turns when acting
     */
    public DancingBug(int[] turns, int length) {
        steps = 0;
        sideLength = length;
        turnArray = turns;
        turnIndex = 0;
    }

    /**
     * Constructs a box bug that traces a square of a given side length.
     * <p>
     * In this function, the length = 1.
     * @param turns the array of the number of turns when acting
     */
    public DancingBug(int[] turns) {
        this(turns, 1);
    }

    /**
     * Moves to the next location of the square.
     */
    public void act() {
        if (steps < sideLength && canMove()) {
            move();
            steps++;
        } else {
            for (int i = 0; i < turnArray[turnIndex]; i++) {
                turn();
            }
            turnIndex = (turnIndex + 1) % turnArray.length;
            steps = 0;
        }
    }
}

/**
 * @author: yangyf83
 * @date: 2022-09-12
 * @brief: A <code>SpiralBug</code> traces out a spiral of a given size, finished in part 2. <br />
 * @version: 1.0
 * @note: 2022-09-12: Add the code of the class.
 * <br /> 2022-10-02: Finish the comments and docs.
 */

import info.gridworld.actor.Bug;

/**
 * A <code>SpiralBug</code> traces out a spiral of a given size. <br />
 */
public class SpiralBug extends Bug {
    private int steps;
    private int sideLength;

    /**
     * Constructs a spiraled bug
     * @param length the side length
     */
    public SpiralBug(int length) {
        steps = 0;
        sideLength = length;
    }

    /**
     * Moves to the next location of the square.
     * <p>
     * Adjust the side length when the bug turns so that the
     * bug can drop flowers in a spiral pattern
     */
    public void act() {
        if (steps < sideLength && canMove()) {
            move();
            steps++;
        } else {
            turn();
            turn();
            steps = 0;
            sideLength++;
        }
    }
}

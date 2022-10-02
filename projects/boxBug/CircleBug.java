/**
 * @author: yangyf83
 * @date: 2022-09-12
 * @brief: the class of circle bug in part 2.
 * @version: 1.0
 * @note: 2022-09-12: Add the code of the class.
 * <br /> 2022-10-02: Finish the comments and docs.
 */

import info.gridworld.actor.Bug;

/**
 * A <code>CircleBug</code> traces out a circle of a given size. <br />
 */
public class CircleBug extends Bug {
    private int steps;
    private final int sideLength;

    /**
     * Constructs a box bug that traces a square of a given side length
     * @param length the side length
     */
    public CircleBug(int length) {
        steps = 0;
        sideLength = length;
    }

    /**
     * Moves to the next location of the square.
     * Except that in the <code>act</code> method the <code>turn</code> method
     * is called once instead of twice.
     */
    public void act() {
        if (steps < sideLength && canMove()) {
            move();
            steps++;
        } else {
            turn();
            steps = 0;
        }
    }
}

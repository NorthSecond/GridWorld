/**
 * @author: yangyf83
 * @date: 2022-09-12
 * @brief: the class of 'Z' bug in part 2.
 * @version: 1.0
 * @note: 2022-09-12: Add the code of the class.
 * <br /> 2022-10-02: Finish the comments and docs.
 */

import info.gridworld.actor.Bug;

/**
 * A <code>ZBug</code> traces out a "Z" of a given side length. <br />
 */
public class ZBug extends Bug {
    private int steps;
    private final int sideLength;

    private boolean flag;

    /**
     * Constructs a Z bug that traces a "Z" of a given side length
     * @param length the side length
     */
    public ZBug(int length) {
        steps = 0;
        sideLength = length;
        this.setDirection(90);
        flag = false;
    }

    /**
     * Moves to the next location of the "Z".
     */
    public void act() {

        if (steps < sideLength && canMove()) {
            move();
            steps++;
        } else if (steps == sideLength) {
            if (flag) {
                return;
            } else if (this.getDirection() == 90) {
                this.setDirection(225);
            } else if (this.getDirection() == 225) {
                flag = true;
                this.setDirection(90);
            }
            steps = 0;
        }
    }
}

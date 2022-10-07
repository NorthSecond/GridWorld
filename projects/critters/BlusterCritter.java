/**
 * @author yangyf83
 * @date 2022-10-06
 * @brief The runner class of the <code>BlusterCritter</code>, finished in part 4. <br />
 * @version 1.0
 * @notes 2022-10-06: Finish the code of the class.
 * <br /> 2022-10-07: Add the comments and docs.
 */

import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Location;

import java.awt.*;
import java.util.ArrayList;

public class BlusterCritter extends Critter {
    // 我还是没想明白为啥要把这玩意放到每一个类里面都定义一次
    static final double DARKENING_FACTOR = 0.05;
    private final int courage;

    public BlusterCritter(int c) {
        super();
        courage = c;
    }

    /**
     * @return the actors within two steps of its current location.
     */
    @Override
    public ArrayList<Actor> getActors() {
        ArrayList<Actor> actors = new ArrayList<>();
        Location loc = getLocation();
        for (int r = loc.getRow() - 2; r < loc.getRow() + 3; r++) {
            for (int c = loc.getCol() - 2; c < loc.getCol() + 3; c++) {
                Location tmpLoc = new Location(r, c);
                if (getGrid().isValid(tmpLoc)) {
                    Actor a = getGrid().get(tmpLoc);
                    if (a != null && a != this) {
                        actors.add(a);
                    }
                }
            }
        }
        return actors;
    }

    /**
     * The method to process the actors.
     * <p> if the number of actors is more than c, bright the color, and if otherwise, darken it.</p>
     *
     * @param actors the actors to be processed
     */
    @Override
    public void processActors(ArrayList<Actor> actors) {
        int n = actors.size();
        if (n < courage) {
            brighten();
        } else {
            darken();
        }
    }

    /**
     * brighten the color of the critter
     */
    private void brighten() {
        Color c = getColor();
        int red = (int) (c.getRed() * (1 + DARKENING_FACTOR) > 255 ? 255 : c.getRed() * (1 + DARKENING_FACTOR));
        int green = (int) (c.getGreen() * (1 + DARKENING_FACTOR) > 255 ? 255 : c.getGreen() * (1 + DARKENING_FACTOR));
        int blue = (int) (c.getBlue() * (1 + DARKENING_FACTOR) > 255 ? 255 : c.getBlue() * (1 + DARKENING_FACTOR));
        setColor(new Color(red, green, blue));
    }


    /**
     * Repeat the codes. I want to make a method to do this.
     * <p> darken the critter </p>
     */
    private void darken() {
        Color c = getColor();
        int red = (int) (c.getRed() * (1 - DARKENING_FACTOR));
        int green = (int) (c.getGreen() * (1 - DARKENING_FACTOR));
        int blue = (int) (c.getBlue() * (1 - DARKENING_FACTOR));
        setColor(new Color(red, green, blue));
    }
}

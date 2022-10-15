package info.gridworld.grid;

import java.util.ArrayList;

public class UnboundedGrid2<E> extends AbstractGrid<E> {
    private Object[][] occupantArray;
    private final int size = 16;

    public UnboundedGrid2() {
        occupantArray = new Object[size][size];
    }

    @Override
    public int getNumRows() {
        return -1;
    }

    @Override
    public int getNumCols() {
        return -1;
    }

    @Override
    public boolean isValid(Location loc) {
        return loc != null;
    }

    @Override
    public ArrayList<Location> getOccupiedLocations() {
        ArrayList<Location> ans = new ArrayList<>();

        for (int i = 0; i < occupantArray.length; i++) {
            for (int j = 0; j < occupantArray[i].length; j++) {
                Location loc = new Location(i, j);
                if (get(loc) != null) {
                    ans.add(loc);
                }
            }
        }

        return ans;
    }

    @Override
    public E get(Location loc) {
        if (!isValid(loc)) {
            throw new IllegalArgumentException("Location " + loc + " is not valid");
        }

        if (loc.getRow() >= occupantArray.length || loc.getCol() >= occupantArray[0].length) {
            return null;
        }

        return (E) occupantArray[loc.getRow()][loc.getCol()];
    }

    @Override
    public E put(Location loc, E obj) {
        if (!isValid(loc)) {
            throw new IllegalArgumentException("Location " + loc + " is not valid");
        }

        if (obj == null) {
            throw new NullPointerException("obj == null");
        }

        if (loc.getRow() >= occupantArray.length || loc.getCol() >= occupantArray[0].length) {
            changeSize(loc);
        }

        E old = get(loc);
        occupantArray[loc.getRow()][loc.getCol()] = obj;
        return old;
    }

    private void changeSize(Location loc){
        int pSize = Math.max(loc.getRow(), loc.getCol()) + 1;
        // double both array bounds until they are large enough
        int newSize = size;
        while (newSize < pSize) {
            newSize <<= 1;
        }

        Object[][] newArray = new Object[newSize][newSize];

        // There seems to be a problem with the way it is written.
//        System.arraycopy(occupantArray, 0, newArray, 0, occupantArray.length);

        for (int i = 0; i < newSize; i++) {
            System.arraycopy(occupantArray[i], 0, newArray[i], 0, newSize);
        }

        occupantArray = newArray;
    }

    @Override
    public E remove(Location loc) {
        if (!isValid(loc)) {
            throw new IllegalArgumentException("Location " + loc + " is not valid");
        }

        if (loc.getRow() >= occupantArray.length || loc.getCol() >= occupantArray[0].length) {
            return null;
        }

        E old = get(loc);
        occupantArray[loc.getRow()][loc.getCol()] = null;
        return old;
    }
}

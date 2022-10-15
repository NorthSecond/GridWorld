package info.gridworld.grid;

import java.util.ArrayList;
import java.util.HashMap;

// using Hashmap
public class SparseBoundedGrid2<E> extends AbstractGrid<E> {
    private final HashMap<Location, E> occupantMap;
    private final int rows;
    private final int cols;

    public SparseBoundedGrid2(int r, int c) {
        if (r <= 0)
            throw new IllegalArgumentException("rows <= 0");
        if (c <= 0)
            throw new IllegalArgumentException("cols <= 0");
        rows = r;
        cols = c;
        occupantMap = new HashMap<>();
    }

    @Override
    public int getNumRows() {
        return rows;
    }

    @Override
    public int getNumCols() {
        return cols;
    }

    @Override
    public boolean isValid(Location loc) {
        return (0 <= loc.getRow()) && (loc.getRow() < getNumRows())
                && (0 <= loc.getCol()) && (loc.getCol() < getNumCols());
    }

    @Override
    public ArrayList<Location> getOccupiedLocations() {
        return new ArrayList<>(occupantMap.keySet());
    }

    @Override
    public E get(Location loc) {
        if (loc == null)
            throw new NullPointerException("loc == null");
        return occupantMap.get(loc);
    }

    @Override
    public E put(Location loc, E obj) {
        if (loc == null)
            throw new NullPointerException("loc == null");
        if (obj == null)
            throw new NullPointerException("obj == null");
        return occupantMap.put(loc, obj);
    }

    @Override
    public E remove(Location loc) {
        if (loc == null)
            throw new NullPointerException("loc == null");
        return occupantMap.remove(loc);
    }
}


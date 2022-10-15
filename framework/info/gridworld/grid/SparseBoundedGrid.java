package info.gridworld.grid;

import java.util.ArrayList;
import java.util.LinkedList;

import info.gridworld.grid.OccupantInCol;

public class SparseBoundedGrid<E> extends AbstractGrid<E>
{
    private ArrayList<LinkedList<OccupantInCol> > occupantArray;
    private int rows;
    private int cols;

    public SparseBoundedGrid(int r, int c)
    {
        if (r <= 0)
            throw new IllegalArgumentException("rows <= 0");
        if (c <= 0)
            throw new IllegalArgumentException("cols <= 0");
        rows = r;
        cols = c;
        occupantArray = new ArrayList<LinkedList<OccupantInCol> >();
        for (int i = 0; i < rows; i++)
            occupantArray.add(new LinkedList<OccupantInCol>());
    }

    @Override
    public int getNumRows()
    {
        return rows;
    }

    @Override
    public int getNumCols()
    {
        return cols;
    }

    @Override
    public boolean isValid(Location loc)
    {
        return (0 <= loc.getRow()) && (loc.getRow() < getNumRows())
                && (0 <= loc.getCol()) && (loc.getCol() < getNumCols());
    }

    @Override
    public ArrayList<Location> getOccupiedLocations()
    {
        ArrayList<Location> theLocations = new ArrayList<Location>();
        for (int r = 0; r < getNumRows(); r++)
        {
            for (OccupantInCol oic : occupantArray.get(r))
            {
                Location loc = new Location(r, oic.getCol());
                theLocations.add(loc);
            }
        }
        return theLocations;
    }

    @Override
    public E get(Location loc)
    {
        if (!isValid(loc))
            throw new IllegalArgumentException("Location " + loc + " is not valid");
        return (E) occupantArray.get(loc.getRow()).get(loc.getCol()).getOccupant();
    }

    @Override
    public E put(Location loc, E obj)
    {
        if (!isValid(loc))
            throw new IllegalArgumentException("Location " + loc + " is not valid");
        if (obj == null)
            throw new NullPointerException("obj == null");

        E oldOccupant = get(loc);
        occupantArray.get(loc.getRow()).add(loc.getCol(), new OccupantInCol(obj, loc.getCol()));
        return oldOccupant;
    }

    @Override
    public E remove(Location loc)
    {
        if (!isValid(loc))
            throw new IllegalArgumentException("Location " + loc + " is not valid");
        E r = get(loc);
        occupantArray.get(loc.getRow()).remove(loc.getCol());
        return r;
    }
}

package info.gridworld.grid;

public class OccupantInCol {
    private Object occupant;
    private int col;

    public OccupantInCol(Object obj, int c) {
        occupant = obj;
        col = c;
    }

    public Object getOccupant() {
        return occupant;
    }

    public int getCol() {
        return col;
    }

    public void setOccupant(Object obj) {
        occupant = obj;
    }

    public void setCol(int c) {
        col = c;
    }
}

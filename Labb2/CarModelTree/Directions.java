package CarModelTree;

public enum Directions {
    NORTH(0,1), EAST(1,0), SOUTH(0,-1), WEST(-1,0);
    private final int xDelta;
    private final int yDelta;

    Directions(int xDelta, int yDelta){
        this.xDelta = xDelta;
        this.yDelta = yDelta;
    }

    public int getXDelta() {
        return this.xDelta;
    }

    public int getYDelta() {
        return this.yDelta;
    }

    public Directions getClockwiseDirection() {
        int ordinal = (this.ordinal() + 1) % 4;
        return Directions.values()[ordinal];
    }

    public Directions gerCounterClockwiseDirection(){
        int ordinal = (this.ordinal() +3) % 4;
        return Directions.values()[ordinal];
    }
}

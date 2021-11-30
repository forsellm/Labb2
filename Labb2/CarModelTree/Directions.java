package VehicleFiles;

public enum Directions {
    NORTH(0,1), EAST(1,0), SOUTH(0,-1), WEST(-1,0);
    private final int xDelta;
    private final int yDelta;

    /**
     * @param xDelta represent direction in x-axis
     * @param yDelta represent direction in y-axis
     */
    Directions(int xDelta, int yDelta){
        this.xDelta = xDelta;
        this.yDelta = yDelta;
    }

    /**
     * @return delta x representing direction in x-axis
     */
    public int getXDelta() {
        return this.xDelta;
    }

    /**
     * Gets delta y
     * @return delta y representing direction in y-axis
     */
    public int getYDelta() {
        return this.yDelta;
    }

    /**
     * Gets clockwise direction
     * @return clockwise direction
     */
    public Directions getClockwiseDirection() {
        int ordinal = (this.ordinal() + 1) % 4;
        return Directions.values()[ordinal];
    }

    /**
     * Gets counterclockwise direction
     * @return counterclockwise direction
     */
    public Directions getCounterClockwiseDirection(){
        int ordinal = (this.ordinal() +3) % 4;
        return Directions.values()[ordinal];
    }
}

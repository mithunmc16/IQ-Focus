package comp1110.ass2;

public class Location {

    private int X;
    private int Y;
    static final int OUT = -1;

    public Location(int X, int Y){
        this.X = X;
        this.Y = Y;
    }

    public Location() {
        this.X = OUT;
        this.Y = OUT;
    }
    public int getX() {
        return X;
    }

    public int getY() {
        return Y;
    }
}

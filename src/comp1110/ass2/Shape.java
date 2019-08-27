package comp1110.ass2;

import static comp1110.ass2.Orientation.*;

public class Shape {
    private ShapeType shape;           //Which shape type it is (a...j)
    private Orientation orientation;   //The shape's current orientation
    private Location location;         //The shape's current location

    public Shape(String placement){
        this.shape = ShapeType.valueOf(Character.toString((placement.charAt(0) - 32)));
        this.orientation = placementToOrientation(placement);
        this.location = placementToLocation(placement);
    }
    public ShapeType getShapeType(){return shape;}

    public Orientation getOrientation(){
        return orientation;
    }

    public Location getLocation(){
        return location;
    }

    /**
     * placementToLocation decodes the shape's location based on the placement string.
     */
    public static Location placementToLocation(String placement){
        int x = Character.getNumericValue(placement.charAt(1));
        int y = Character.getNumericValue(placement.charAt(2));

        return new Location(x,y);

    }

    /**
     * placementToOrientation decodes the tile's orientation given the placement string.
     */

    public static Orientation placementToOrientation (String placement){
        switch(placement.charAt(3)){
            case '0':
                return EAST;
            case '1':
                return SOUTH;

            case '2':
                return WEST;

            default:
                return NORTH;
        }
    }

}

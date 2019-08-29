package comp1110.ass2;

import static comp1110.ass2.Orientation.*;
import static comp1110.ass2.ShapeType.*;
import static comp1110.ass2.State.EMPTY;

public class BoardState {

    /*
    States at each of the board's 43 locations.
    The board has been initialised to represent the empty board.
    The "null" value represents the part of the board that is not indented,
    hence a piece cannot be placed there.
     */
    private State[][] boardStates = {
            {EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY},
            {EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY},
            {EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY},
            {EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY},
            {null, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, null},
    };

    /*
    Shapes occupying the board where the indices refer to column (0 ... 8)
    and row (0 ... 4).

    The entries refer to the Shape instance occupying an indent on the board.
     */
    private Shape[][] shapes = new Shape[5][9];

    /**
    Add a new shape placement to the board state.
     */
    private void addShapeToBoard(String placement) {
        /* create the the shape*/
        Shape shape = new Shape(placement);

        /* update the data structure for the indents that compose the shape */
        updateShapes(shape);
    }
    

    /**
     *Update the shapes data structure with a new shape placement.
     * Indents will be updated to have the tile that's covering it.
     * Indents that are not covered by a shape will point to null.
     */
    private void updateShapes(Shape shape) {
        Location location = shape.getLocation();
        Orientation orientation = shape.getOrientation();
        ShapeType shapetype = shape.getShapeType();

        if(orientation == EAST){
            if(shapetype == A){
                shapes[location.getY()+1][location.getX()+1] = shape;

                for(int i = 0; i < 3; i++){
                    shapes[location.getY()][location.getX()+i] = shape;
                }
            }
            if(shapetype == B){
                shapes[location.getY()+1][location.getX()] = shape;
                shapes[location.getX()+1][location.getX()+1] = shape;

                for(int i = 1; i < 4; i++){
                    shapes[location.getY()][location.getX()+i] = shape;
                }

            }
            if(shapetype == C){
                shapes[location.getY()][location.getX()+2] = shape;
                for(int i = 0; i<4; i++){
                    shapes[location.getY()+1][location.getX()+i] = shape;
                }
            }
            if(shapetype == D){
                shapes[location.getY()+1][location.getX()+2] = shape;
                for(int i = 0; i<3; i++){
                    shapes[location.getY()][location.getX()+i] = shape;
                }
            }
            if(shapetype == E){
                shapes[location.getY()+1][location.getX()] = shape;
                shapes[location.getY()+1][location.getX()+1] = shape;
                for(int i = 0; i<3; i++){
                    shapes[location.getY()][location.getX()+i] = shape;
                }
            }
            if(shapetype == F){
                for(int i = 0; i<3; i++){
                    shapes[location.getY()][location.getX()+i] = shape;
                }
            }
            if(shapetype == G){
                shapes[location.getY()][location.getX()] = shape;
                shapes[location.getY()][location.getX()+1] = shape;
                shapes[location.getY()+1][location.getX()+1] = shape;
                shapes[location.getY()+1][location.getX()+2] = shape;
            }
            if(shapetype == H){
                for(int i = 0; i<3; i++){
                    shapes[location.getY()][location.getX()+i] = shape;
                    shapes[location.getY()+i][location.getX()] = shape;
                }
            }
            if(shapetype == I){
                shapes[location.getY()][location.getX()] = shape;
                shapes[location.getY()][location.getX()+1] = shape;
                shapes[location.getY()+1][location.getX()+1] = shape;
            }
            if(shapetype == J){
                shapes[location.getY()+1][location.getX()] = shape;
                for(int i = 0; i < 4; i++){
                    shapes[location.getY()][location.getX()+i] = shape;
                }
            }
        }

        if(orientation == SOUTH){
            if(shapetype == A){
                shapes[location.getY()+1][location.getX()] = shape;
                for(int i = 0; i < 3; i++){
                    shapes[location.getY()+i][location.getX()+1] = shape;
                }
            }
            if(shapetype == B){
                shapes[location.getY()][location.getX()] = shape;
                shapes[location.getY()+1][location.getX()] = shape;
                for(int i = 1; i < 4; i++){
                    shapes[location.getY()+i][location.getX()+1] = shape;
                }
            }
            if(shapetype == C){
                shapes[location.getY()+2][location.getX()+1] = shape;
                for(int i = 0; i<4; i++){
                    shapes[location.getY()+i][location.getX()] = shape;
                }
            }
            if(shapetype == D){
                shapes[location.getY()+2][location.getX()] = shape;
                for(int i = 0; i < 3; i++){
                    shapes[location.getY()+i][location.getX()+1] = shape;
                }
            }
            if(shapetype == E){
                shapes[location.getY()][location.getX()] = shape;
                shapes[location.getY()+1][location.getX()] = shape;
                for(int i = 0; i < 3; i++){
                    shapes[location.getY()+i][location.getX()+1] = shape;
                }
            }
            if(shapetype == F){
                for(int i = 0; i < 3; i++){
                    shapes[location.getY()+i][location.getX()] = shape;
                }
            }
            if(shapetype == G){
                shapes[location.getY()][location.getX()+1] = shape;
                shapes[location.getY()+1][location.getX()+1] = shape;
                shapes[location.getY()+1][location.getX()] = shape;
                shapes[location.getY()+2][location.getX()] = shape;
            }
            if(shapetype == H){
                for(int i = 0; i < 3; i++){
                    shapes[location.getY()][location.getX()+i] = shape;
                    shapes[location.getY()+i][location.getX()+2] = shape;
                }
            }
            if(shapetype == I){
                shapes[location.getY()][location.getX()+1] = shape;
                shapes[location.getY()+1][location.getX()] = shape;
                shapes[location.getY()+1][location.getX()+1] = shape;
            }
            if(shapetype == J){
                shapes[location.getY()][location.getX()] = shape;
                for(int i = 0; i<4; i++){
                    shapes[location.getY()+i][location.getX()+1] = shape;
                }
            }
        }

        if(orientation == WEST){
            if(shapetype == A){
                shapes[location.getY()][location.getX()+1] = shape;
                for(int i = 0; i<3; i++){
                    shapes[location.getY()+1][location.getX()+i] = shape;
                }
            }
            if(shapetype == B){
                shapes[location.getY()][location.getX()+2] = shape;
                shapes[location.getY()][location.getX()+3] = shape;
                for(int i = 0; i < 3; i++){
                    shapes[location.getY()+1][location.getX()+i] = shape;
                }
            }
            if(shapetype == C){
                shapes[location.getY()+1][location.getX()+1] = shape;
                for(int i = 0; i < 4; i++){
                    shapes[location.getY()][location.getX()+i] = shape;
                }
            }
            if(shapetype == D){
                shapes[location.getY()][location.getX()] = shape;
                for(int i = 0; i < 3; i++){
                    shapes[location.getY()][location.getX()+i] = shape;
                }
            }
            if(shapetype == E){
                shapes[location.getY()][location.getX()+1] = shape;
                shapes[location.getY()][location.getX()+2] = shape;
                for(int i = 0; i < 3; i++){
                    shapes[location.getY()+1][location.getX()+i] = shape;
                }
            }
            if(shapetype == F){
                for(int i = 0; i < 3; i++){
                    shapes[location.getY()][location.getX()+i] = shape;
                }
            }
            if(shapetype == G){
                shapes[location.getY()][location.getX()] = shape;
                shapes[location.getY()][location.getX()+1] = shape;
                shapes[location.getY()+1][location.getX()+1] = shape;
                shapes[location.getY()+1][location.getX()+2] = shape;
            }
            if(shapetype == H){
                for(int i = 0; i < 3; i++){
                    shapes[location.getY()+2][location.getX()+i] = shape;
                    shapes[location.getY()+i][location.getX()+2] = shape;
                }
            }
            if(shapetype == I){
                shapes[location.getY()][location.getX()] = shape;
                shapes[location.getY()+1][location.getX()] = shape;
                shapes[location.getY()+1][location.getX()+1] = shape;
            }
            if(shapetype == J){
                shapes[location.getY()][location.getX()+3] = shape;
                for(int i = 0; i < 4; i++){
                    shapes[location.getY()+1][location.getX()+i] = shape;
                }
            }
        }

        if(orientation == NORTH){
            if(shapetype == A){
                shapes[location.getY()+1][location.getX()+1] = shape;
                for(int i = 0; i < 3; i++){
                    shapes[location.getY()+i][location.getX()] = shape;
                }
            }
            if(shapetype == B){
                shapes[location.getY()+2][location.getX()+1] = shape;
                shapes[location.getY()+3][location.getX()+1] = shape;
                for(int i = 0; i < 3; i++){
                    shapes[location.getY()+i][location.getX()] = shape;
                }
            }
            if(shapetype == C){
                shapes[location.getY()+1][location.getX()] = shape;
                for(int i = 0; i < 4; i++){
                    shapes[location.getY()+i][location.getX()+1] = shape;
                }
            }
            if(shapetype == D){
                shapes[location.getY()][location.getX()+1] = shape;
                for(int i = 0; i < 3; i++){
                    shapes[location.getY()+i][location.getX()] = shape;
                }
            }
            if(shapetype == E){
                shapes[location.getY()+1][location.getX()+1] = shape;
                shapes[location.getY()+2][location.getX()+1] = shape;

                for(int i = 0; i < 3; i++){
                    shapes[location.getY()+i][location.getX()] = shape;
                }
            }
            if(shapetype == F){
                for(int i = 0; i<3; i++){
                    shapes[location.getY()+i][location.getX()] = shape;
                }
            }
            if(shapetype == G){
                shapes[location.getY()][location.getX()+1] = shape;
                shapes[location.getY()+1][location.getX()+1] = shape;
                shapes[location.getY()+1][location.getX()] = shape;
                shapes[location.getY()+2][location.getX()] = shape;
            }
            if(shapetype == H){
                for(int i = 0; i < 3; i++){
                    shapes[location.getY()+i][location.getX()] = shape;
                    shapes[location.getY()+2][location.getX()+i] = shape;
                }
            }
            if(shapetype == I){
                shapes[location.getY()][location.getX()] = shape;
                shapes[location.getY()][location.getX()+1] = shape;
                shapes[location.getY()+1][location.getX()] = shape;
            }
            if(shapetype == J){
                shapes[location.getY()+3][location.getX()+1] = shape;
                for(int i = 0; i < 4; i++){
                    shapes[location.getY()][location.getX()+i] = shape;
                }
            }
        }

    }

    /*
    isPlacementOnBoard checks if the string representing a shape placement fits on the board.
     */

    public static boolean isPlacementOnBoard(String placement){
        Shape shape = new Shape(placement);
        ShapeType shapetype = shape.getShapeType();
        Orientation orientation = shape.getOrientation();
        Location location = shape.getLocation();
        int x = location.getX();
        int y = location.getY();

        if(orientation == EAST){
            if(shapetype == A){
                if(x >= 0 && x <= 6 && y>= 0 && y <= 3){
                    return true;
                }
            }
            if(shapetype == B){
                if(x >= 0 && x <= 5 && y >= 0 && y <= 3){
                    return true;
                }

            }
            if(shapetype == C){
                if(x >= 0 && x <= 5 && y >= 0 && y <= 3){
                    if(x == 0 && y == 3 || x == 5 && y == 3){
                        return false;
                    }
                    else{
                        return true;
                    }
                }
            }
            if(shapetype == D){
                if(x >= 0 && x <= 6 && y >= 0 && y <=3){
                    if(x == 6 && y == 3){
                        return false;
                    }
                    else{
                        return true;
                    }
                }
            }
            if(shapetype == E){
                if(x >= 0 && x <= 6 && y >= 0 && y <= 3){
                    if(x == 0 && y == 3){
                        return false;
                    }
                    else{
                        return true;
                    }
                }
            }
            if(shapetype == F){
                if(x >= 0 && x <= 6 && y >= 0 && y <= 4){
                    if(x == 0 && y == 4 || x == 6 && y == 4){
                        return false;
                    }
                    else{
                        return true;
                    }
                }
            } if(shapetype == G){
                if(x >= 0 && x <= 6 && y >= 0 && y <= 3){
                    if(x == 6 && y == 3){
                        return false;
                    }
                    else{
                        return true;
                    }
                }
            }

            if(shapetype == H) {
                if(x >= 0 && x <= 6 && y >= 0 && y <= 2){
                    if(x == 0 && y == 2){return false;}

                    else{return true;}
                }
            }
            if(shapetype == I){
                if(x >= 0 && x <= 7 && y >= 0 && y <= 3){
                    if(x == 7 && y == 3){return false;}

                    else{return true;}
                }
            }
            if(shapetype == J){
                if(x >= 0 && x <= 5 && y >= 0 && y <= 3){
                    if(x == 0 && y == 3){return false;}

                    else{return true;}
                }
            }

        }
        //end EAST case

        if(orientation == WEST) {
            if (shapetype == A) {
                if (x >= 0 && x <= 6 && y >= 0 && y <= 3) {
                    if (x == 0 && y == 2 || x == 6 && y == 2) {
                        return false;
                    } else {
                        return true;
                    }
                }
            }
            if (shapetype == B) {
                if (x >= 0 && x <= 5 && y >= 0 && y <= 3) {
                    if (x == 0 && y == 3) {
                        return false;
                    } else {
                        return true;
                    }
                }

            }
            if (shapetype == C) {
                if (x >= 0 && x <= 5 && y >= 0 && y <= 3) {
                    return true;
                }
            }

            if (shapetype == D) {
                if (x >= 0 && x <= 6 && y >= 0 && y <= 3) {
                    if (x == 0 && y == 3) {
                        return false;
                    } else {
                        return true;
                    }
                }
            }
            if (shapetype == E) {
                if (x >= 0 && x <= 6 && y >= 0 && y <= 3) {
                    if (x == 6 && y == 3) {
                        return false;
                    } else {
                        return true;
                    }
                }
            }
            if (shapetype == F) {
                if (x >= 0 && x <= 6 && y >= 0 && y <= 4) {
                    if (x == 0 && y == 4 || x == 6 && y == 4) {
                        return false;
                    } else {
                        return true;
                    }
                }
            }
            if (shapetype == G) {
                if (x >= 0 && x <= 6 && y >= 0 && y <= 3) {
                    if (x == 6 && y == 3) {
                        return false;
                    } else {
                        return true;
                    }
                }
            }

            if (shapetype == H) {
                if (x >= 0 && x <= 6 && y >= 0 && y <= 2) {
                    if (x == 6 && y == 2) {
                        return false;
                    } else {
                        return true;
                    }
                }
            }
            if (shapetype == I) {
                if (x >= 0 && x <= 7 && y >= 0 && y <= 3) {
                    if (x == 0 && y == 3) {
                        return false;
                    } else {
                        return true;
                    }
                }
            }
            if (shapetype == J) {
                if (x >= 0 && x <= 5 && y >= 0 && y <= 3) {
                    if (x == 5 && y == 3) {
                        return false;
                    } else {
                        return true;
                    }
                }
            }
        }

        // end WEST case

        if(orientation == SOUTH){
            if(shapetype == A){
                if(x >= 0 && x <= 7 && y>= 0 && y <= 2){
                    if(x == 7 && y == 2) {
                        return false;
                    }
                    else{
                        return true;}
                }
            }
            if(shapetype == B){
                if(x >= 0 && x <= 7 && y >= 0 && y <= 1){
                    if(x == 7 && y == 1){
                        return false;
                    }
                    else{
                        return true;
                    }
                }

            }
            if(shapetype == C){
                if(x >= 0 && x <= 7 && y >= 0 && y <= 1){
                    if(x == 0 && y == 1){
                        return false;
                    }
                    else{
                        return true;
                    }
                }
            }
            if(shapetype == D){
                if(x >= 0 && x <= 7 && y >= 0 && y <=2){
                    if(x == 7 && y == 2 || x == 0 && y == 2){
                        return false;
                    }
                    else{
                        return true;
                    }
                }
            }
            if(shapetype == E){
                if(x >= 0 && x <= 7 && y >= 0 && y <= 2){
                    if(x == 7 && y == 2){
                        return false;
                    }
                    else{
                        return true;
                    }
                }
            }
            if(shapetype == F){
                if(x >= 0 && x <= 8 && y >= 0 && y <= 2){
                    if(x == 0 && y == 2 || x == 8 && y == 2){
                        return false;
                    }
                    else{
                        return true;
                    }
                }
            } if(shapetype == G){
                if(x >= 0 && x <= 7 && y >= 0 && y <= 2){
                    if(x == 0 && y == 2){
                        return false;
                    }
                    else{
                        return true;
                    }
                }
            }

            if(shapetype == H) {
                if(x >= 0 && x <= 6 && y >= 0 && y <= 2){
                    if(x == 6 && y == 2){return false;}

                    else{return true;}
                }
            }
            if(shapetype == I){
                if(x >= 0 && x <= 7 && y >= 0 && y <= 3){
                    if(x == 7 && y == 3 || x == 0 && y == 3){return false;}

                    else{return true;}
                }
            }
            if(shapetype == J){
                if(x >= 0 && x <= 7 && y >= 0 && y <= 1){
                    if(x == 7 && y == 1){return false;}

                    else{return true;}
                }
            }

        }
        if(orientation == NORTH){
            if(shapetype == A){
                if(x >= 0 && x <= 7 && y>= 0 && y <= 2){
                    if(x == 0 && y == 2) {
                        return false;
                    }
                    else{
                        return true;}
                }
            }
            if(shapetype == B){
                if(x >= 0 && x <= 7 && y >= 0 && y <= 1){
                    if(x == 7 && y == 1){
                        return false;
                    }
                    else{
                        return true;
                    }
                }

            }
            if(shapetype == C){
                if(x >= 0 && x <= 7 && y >= 0 && y <= 1){
                    if(x == 7 && y == 1){
                        return false;
                    }
                    else{
                        return true;
                    }
                }
            }
            if(shapetype == D){
                if(x >= 0 && x <= 7 && y >= 0 && y <=2){
                    if(x == 0 && y == 2){
                        return false;
                    }
                    else{
                        return true;
                    }
                }
            }
            if(shapetype == E){
                if(x >= 0 && x <= 7 && y >= 0 && y <= 2){
                    if(x == 0 && y == 2){
                        return false;
                    }
                    else{
                        return true;
                    }
                }
            }
            if(shapetype == F){
                if(x >= 0 && x <= 8 && y >= 0 && y <= 2){
                    if(x == 0 && y == 2 || x == 8 && y == 2){
                        return false;
                    }
                    else{
                        return true;
                    }
                }
            } if(shapetype == G){
                if(x >= 0 && x <= 7 && y >= 0 && y <= 2){
                    if(x == 0 && y == 2){
                        return false;
                    }
                    else{
                        return true;
                    }
                }
            }

            if(shapetype == H) {
                if(x >= 0 && x <= 6 && y >= 0 && y <= 2){
                    if(x == 0 && y == 2){return false;}

                    else{return true;}
                }
            }
            if(shapetype == I){
                if(x >= 0 && x <= 7 && y >= 0 && y <= 3){
                    if(x == 0 && y == 3){return false;}

                    else{return true;}
                }
            }
            if(shapetype == J){
                if(x >= 0 && x <= 7 && y >= 0 && y <= 1){
                    if(x == 0 && y == 1){return false;}

                    else{return true;}
                }
            }

        }
        return false;
    }
}

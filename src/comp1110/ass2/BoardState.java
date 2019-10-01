package comp1110.ass2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static comp1110.ass2.Orientation.*;
import static comp1110.ass2.ShapeType.*;

public class BoardState {
    /**
     * All of the following code is written by Joanne Louie, u6949022
     */

    /*
    States at each of the board's 43 locations.
    The board has been initialised to represent the empty board.
    The "null" value represents the part of the board that is not indented,
    hence a piece cannot be placed there.
     */
    public static State[][] boardStates = new State[5][9];

    /**
     * The updateStates function updates every cell in the boardStates array which represents the gameboard,
     * given a placement representing a shape.
     * For example, "a000" will update (0,0) to G (green), (0,1) to W (white) and so on.
     */

    public static void updateStates(String placement){
        Shape shape = new Shape(placement);
        ShapeType shapetype = shape.getShapeType();
        Orientation orientation = shape.getOrientation();
        Location location = shape.getLocation();
        int x = location.getX();
        int y = location.getY();


        switch(orientation){
            case EAST:
                for(int col = 0; col < 4; col++){
                    for(int row = 0; row < 3; row++){
                        if(shapetype.stateFromOffset(col,row,orientation) != null){
                            boardStates[y + row][x + col] = shapetype.stateFromOffset(col,row,orientation);
                        }
                    }
                }
            break;

            case SOUTH: //south
                if(shapetype == H){
                    for(int row = 0; row < 4; row++){
                        for(int col = 0; col < 3; col++){
                            if(shapetype.stateFromOffset(col,row,orientation) != null){
                                boardStates[row + y][col+x] = shapetype.stateFromOffset(col,row,orientation);
                            }
                        }
                    }
                }
                else if(shapetype == F){
                    for(int row = 0; row < 4; row++){
                        for(int col = 2; col < 3; col++){
                            if(shapetype.stateFromOffset(col,row,orientation) != null){
                                boardStates[row+y][(col-2)+x] = shapetype.stateFromOffset(col,row,orientation);
                            }
                        }
                    }
                }
                else{
                    for(int row = 0; row < 4; row++){
                        for(int col = 1; col < 3; col++){
                            if(shapetype.stateFromOffset(col,row,orientation) != null){
                                boardStates[row+y][(col-1)+x] = shapetype.stateFromOffset(col,row,orientation);
                            }
                        }
                    }
                }
             break;
        }
        if(orientation == WEST){
            if(shapetype == H){
                for(int col = 0; col < 4; col++){
                    for(int row = 0; row < 3; row++){
                        if(shapetype.stateFromOffset(col,row,orientation) != null){
                            boardStates[row+y][(col-1)+x] = shapetype.stateFromOffset(col,row,orientation);
                        }
                    }
                }
            }
            else if(shapetype == B || shapetype == C || shapetype == J){
                for(int col = 0; col<4; col++){
                    for(int row = 1; row <3; row++){
                        if(shapetype.stateFromOffset(col,row,orientation) != null){
                            boardStates[(row-1)+y][col+x] = shapetype.stateFromOffset(col,row,orientation);
                        }
                    }
                }
            }
            else if(shapetype == I){
                for(int col = 2; col<4; col++){
                    for(int row = 1; row <3; row++){
                        if(shapetype.stateFromOffset(col,row,orientation) != null){
                            boardStates[(row-1)+y][(col-2)+x] = shapetype.stateFromOffset(col,row,orientation);
                        }
                    }
                }
            }
            else if(shapetype == F){
                for(int col = 1; col<4; col++){
                    for(int row = 2; row <3; row++){
                        if(shapetype.stateFromOffset(col,row,orientation) != null){
                            boardStates[(row-2)+y][(col-1)+x] = shapetype.stateFromOffset(col,row,orientation);
                        }
                    }
                }
            }
            else{
                for(int col = 1; col < 4; col++){
                    for(int row = 1; row <3; row++){
                        if(shapetype.stateFromOffset(col,row,orientation) != null){
                            boardStates[(row-1)+y][(col-1)+x] = shapetype.stateFromOffset(col,row,orientation);
                        }
                    }
                }
            }
        }

        if(orientation == NORTH){
            if(shapetype == B || shapetype == C || shapetype == J){
                for(int row = 0; row < 4; row++){
                    for(int col = 0; col <3; col++){
                        if(shapetype.stateFromOffset(col,row,orientation) != null){
                            boardStates[row+y][col+x] = shapetype.stateFromOffset(col,row,orientation);
                        }
                    }
                }
            }
            else if(shapetype == I){
                for(int row = 2; row < 4; row++){
                    for(int col = 0; col <3; col++){
                        if(shapetype.stateFromOffset(col,row,orientation) != null){
                            boardStates[(row-2)+y][col+x] = shapetype.stateFromOffset(col,row,orientation);
                        }
                    }
                }
            }
            else{
                for(int row = 1; row < 4; row++){
                    for(int col = 0; col <3; col++){
                        if(shapetype.stateFromOffset(col,row,orientation) != null){
                            boardStates[(row-1)+y][col+x] = shapetype.stateFromOffset(col,row,orientation);
                        }
                    }
                }
            }
        }
    }


    /**
    Shapes occupying the board where the indices refer to column (0 ... 8)
    and row (0 ... 4).

    The entries refer to the Shape instance occupying an indent on the board.
     */
    public static Shape[][] shapes = new Shape[5][9];

    /**
    Add a new shape placement to the board state.
     */
    public static void addShapeToBoard(String placement) {
        /* create the the shape*/
        Shape shape = new Shape(placement);

        /* update the data structure for the indents that compose the shape */
        updateShapes(shape);
    }


    /**
     *Update the shapes data structure with a new shape placement.
     * Indents will be updated to have the tile that's covering it.
     * Indents that are not covered by a shape will point to null.
     * This keeps track of which shapes have already been placed.
     */
    public static void updateShapes(Shape shape) {
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
                shapes[location.getY()+1][location.getX()+1] = shape;

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
                    shapes[location.getY()+1][location.getX()+i] = shape;
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
                    shapes[location.getY()+i][location.getX()] = shape;
                }
            }
        }

    }
    /**Test for isPlacementOnBoard*/


    /**
    isPlacementOnBoard checks if the 4- character string representing a shape placement fits on the board.
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
            if(shapetype == B || shapetype == J){
                if(x >= 0 && x <= 5 && y >= 0 && y <= 3){
                    if(x == 0 && y == 3){
                        return false;
                    }
                    return true;
                }

            }
            if(shapetype == C){
                if(x >= 0 && x <= 5 && y >= 0 && y <= 3){
                    if(x == 0 && y == 3 || x == 5 && y == 3){
                        return false;
                    }
                    return true;

                }
            }
            if(shapetype == D || shapetype == G){
                if(x >= 0 && x <= 6 && y >= 0 && y <=3){
                    if(x == 6 && y == 3){
                        return false;
                    }
                    return true;
                }
            }
            if(shapetype == E){
                if(x >= 0 && x <= 6 && y >= 0 && y <= 3){
                    if(x == 0 && y == 3){
                        return false;
                    }
                    return true;
                }
            }
            if(shapetype == F){
                if(x >= 0 && x <= 6 && y >= 0 && y <= 4){
                    if(x == 0 && y == 4 || x == 6 && y == 4){
                        return false;
                    }
                    return true;
                }
            }
            //shapetype == G has the same condition as shapetype == D & has been written above

            if(shapetype == H) {
                if(x >= 0 && x <= 6 && y >= 0 && y <= 2){
                    if(x == 0 && y == 2) {
                        return false;
                    }
                    return true;
                }
            }
            if(shapetype == I){
                if(x >= 0 && x <= 7 && y >= 0 && y <= 3){
                    if(x == 7 && y == 3){
                        return false;
                    }
                    return true;
                }
            }

            //shapetype J has the same conditions as shapetype B (see above)

        }
        //end EAST case

        if(orientation == WEST) { //Start WEST case

            if (shapetype == A || shapetype == D || shapetype == E) {
                if (x >= 0 && x <= 6 && y >= 0 && y <= 3) {
                    if (x == 0 && y == 3|| x == 6 && y == 3) {
                        return false;
                    }
                    return true;
                }
            }
            if (shapetype == B) {
                if (x >= 0 && x <= 5 && y >= 0 && y <= 3) {
                    if (x == 0 && y == 3) {
                        return false;
                    }
                    return true;
                }

            }
            if (shapetype == C) {
                if (x >= 0 && x <= 5 && y >= 0 && y <= 3) {
                    return true;
                }
            }
            //shapetype D and E shares the same condition as A, and has been addressed above

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
                    if (x == 0 && y == 2 || x == 6 && y == 2) {
                        return false;
                    }
                    return true;
                }
            }
            if (shapetype == I) {
                if (x >= 0 && x <= 7 && y >= 0 && y <= 3) {
                    if (x == 0 && y == 3 || x == 7 && y == 3) {
                        return false;
                    }
                    return true;
                }
            }
            if (shapetype == J) {
                if (x >= 0 && x <= 5 && y >= 0 && y <= 3) {
                    if (x == 0 && y == 3 || x == 5 && y == 3) {
                        return false;
                    }
                    return true;
                }
            }
        }

        // end WEST case

        if(orientation == SOUTH){ //start SOUTH case
            if(shapetype == A){
                if(x >= 0 && x <= 7 && y>= 0 && y <= 2){
                    if(x == 7 && y == 2) {
                        return false;
                    }
                    return true;
                }
            }
            if(shapetype == B){
                if(x >= 0 && x <= 7 && y >= 0 && y <= 1){
                    if(x == 7 && y == 1){
                        return false;
                    }
                    return true;
                }

            }
            if(shapetype == C){
                if(x >= 0 && x <= 7 && y >= 0 && y <= 1){
                    if(x == 0 && y == 1){
                        return false;
                    }
                    return true;
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
                    return true;
                }
            }
            if(shapetype == G){
                if(x >= 0 && x <= 7 && y >= 0 && y <= 2){
                    if(x == 0 && y == 2){
                        return false;
                    }
                    return true;
                }
            }

            if(shapetype == H) {
                if(x >= 0 && x <= 6 && y >= 0 && y <= 2){
                    if(x == 6 && y == 2){
                        return false;
                    }
                    return true;
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
                    if(x == 7 && y == 1){
                        return false;
                    }

                    return true;
                }
            }

        }
        if(orientation == NORTH){
            if(shapetype == A){
                if(x >= 0 && x <= 7 && y>= 0 && y <= 2){
                    if(x == 0 && y == 2) {
                        return false;
                    }
                    return true;
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
                    if(x == 0 && y == 2 || x == 7 && y == 2){
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
                    if(x == 0 && y == 2 || x == 6 && y == 2){return false;}

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
                    if(x == 0 && y == 1 || x == 7 && y == 1){return false;}

                    else{return true;}
                }
            }

        } // end NORTH case
        return false;
    }

    /**
     * isShapeOverlapping determines whether a proposed shape placement will overlap with previously
     * placed shapes.
     * @param placement A 4-character string which represents the proposed placement
     * @return True if the proposed shape placement will overlap existing placements,
     * False if the proposed shape placement will not cause an overlap.
     */

    public static boolean isShapeOverlapping(String placement){
        Shape shape = new Shape(placement);
        ShapeType type = shape.getShapeType();
        Location location = shape.getLocation();
        Orientation orientation = shape.getOrientation();
        int x = location.getX();
        int y = location.getY();

        switch(type){
            case A:
                if(orientation == EAST){
                    if(shapes[y][x] == null && shapes[y][x+1] == null && shapes[y][x+2] == null && shapes[y+1][x+1] == null){
                        return false;
                    }
                }
                else if (orientation == SOUTH){
                    if(shapes[y+1][x] == null && shapes[y][x+1] == null & shapes[y+1][x+1] == null && shapes[y+2][x+1] == null){
                        return false;
                    }
                }
                else if(orientation == WEST){
                    if(shapes[y][x+1] == null && shapes[y+1][x] == null && shapes[y+1][x+1] == null && shapes[y+1][x+2] == null){
                        return false;
                    }
                }
                else {
                    if(shapes[y][x] == null && shapes[y+1][x] == null && shapes[y+2][x] == null && shapes[y+1][x+1] == null){
                        return false;
                    }
                }
                break; // end case A

            case B:
                if(orientation == EAST){
                    if(shapes[y][x+1] == null && shapes[y][x+2] == null && shapes[y][x+3] == null
                       && shapes[y+1][x] == null && shapes[y+1][x+1] == null){
                        return false;
                    }
                }
                else if(orientation == SOUTH){
                    if(shapes[y][x] == null && shapes[y+1][x] == null && shapes[y+1][x+1] == null
                       && shapes[y+2][x+1] == null && shapes[y+3][x+1] == null){
                        return false;
                    }
                }
                else if(orientation == WEST){
                    if(shapes[y+1][x] == null & shapes[y+1][x+1] == null && shapes[y+1][x+2] == null
                      && shapes[y][x+2] == null && shapes[y][x+3] == null){
                        return false;
                    }
                }
                else{
                    if(shapes[y][x] == null && shapes[y+1][x] == null && shapes[y+2][x] == null
                       && shapes[y+2][x+1] == null && shapes[y+3][x+1] == null){
                        return false;
                    }
                }
                break; //end case B

            case C:
                if(orientation == EAST){
                    if(shapes[y][x+2] == null && shapes[y+1][x] == null && shapes[y+1][x+1] == null
                            && shapes[y+1][x+2] == null && shapes[y+1][x+3] == null){
                        return false;
                    }
                }
                else if(orientation == SOUTH){
                    if(shapes[y][x] == null && shapes[y+1][x] == null && shapes[y+2][x] == null
                      && shapes[y+3][x] == null && shapes[y+2][x+1] == null){
                        return false;
                    }
                }
                else if(orientation == WEST){
                    if(shapes[y][x] == null && shapes[y][x+1] == null && shapes[y][x+2] == null
                      && shapes[y][x+3] == null && shapes[y+1][x+1] == null){
                        return false;
                    }
                }
                else{
                    if(shapes[y+1][x] == null && shapes[y][x+1] == null && shapes[y+1][x+1] == null
                      && shapes[y+2][x+1] == null && shapes[y+3][x+1] == null){
                        return false;
                    }
                }
                break; // end case C

            case D:
                if(orientation == EAST){
                    if(shapes[y][x] == null && shapes[y][x+1] == null && shapes[y][x+2] == null
                      && shapes[y+1][x+2] == null){
                        return false;
                    }
                }
                else if(orientation == SOUTH){
                    if(shapes[y+2][x] == null && shapes[y][x+1] == null && shapes[y+1][x+1] == null
                      && shapes[y+2][x+1] == null){
                        return false;
                    }
                }
                else if(orientation == WEST){
                    if(shapes[y][x] == null && shapes[y+1][x] == null && shapes[y+1][x+1] == null
                      && shapes[y+1][x+2] == null){
                        return false;
                    }
                }
                else{
                    if(shapes[y][x] == null && shapes[y+1][x] == null && shapes[y+2][x] == null
                      && shapes[y][x+1] == null){
                        return false;
                    }
                }
                break; //end case D

            case E:
                if(orientation == EAST){
                    if(shapes[y][x] == null && shapes[y][x+1] == null && shapes[y][x+2] == null
                      && shapes[y+1][x] == null && shapes[y+1][x+1] == null){
                        return false;
                    }
                }
                else if(orientation == SOUTH){
                    if(shapes[y][x] == null && shapes[y+1][x] == null && shapes[y][x+1] == null
                      && shapes[y+1][x+1] == null && shapes[y+2][x+1] == null){
                        return false;
                    }
                }
                else if(orientation == WEST){
                    if(shapes[y][x+1] == null && shapes[y][x+2] == null && shapes[y+1][x] == null
                      && shapes[y+1][x+1] == null && shapes[y+1][x+2] == null){
                        return false;
                    }
                }
                else{
                    if(shapes[y][x] == null && shapes[y+1][x] == null && shapes[y+2][x] == null
                      && shapes[y+1][x+1] == null && shapes[y+2][x+1] == null){
                        return false;
                    }
                }
                break; //end case E

            case F:
                if(orientation == EAST || orientation == WEST){
                    if(shapes[y][x] == null && shapes[y][x+1] == null && shapes[y][x+2] == null){
                        return false;
                    }
                }
                else{
                    if(shapes[y][x] == null && shapes[y+1][x] == null && shapes[y+2][x] == null){
                        return false;
                    }
                }
                break; //end case F

            case G:
                if(orientation == EAST || orientation == WEST){
                    if(shapes[y][x] == null && shapes[y][x+1] == null && shapes[y+1][x+1] == null
                      && shapes[y+1][x+2] == null){
                        return false;
                    }
                }
                else{
                    if(shapes[y][x+1] == null && shapes[y+1][x] == null && shapes[y+1][x+1] == null
                      && shapes[y+2][x] == null){
                        return false;
                    }
                }
                break; // end case G

            case H:
                if(orientation == EAST){
                    if(shapes[y][x] == null && shapes[y][x+1] == null && shapes[y][x+2] == null
                      && shapes[y+1][x] == null && shapes[y+2][x] == null){
                        return false;
                    }
                }
                else if(orientation == SOUTH){
                    if(shapes[y][x] == null && shapes[y][x+1] == null && shapes[y][x+2] == null
                      && shapes[y+1][x+2] == null && shapes[y+2][x+2] == null){
                        return false;
                    }
                }
                else if(orientation == WEST){
                    if(shapes[y+2][x] == null && shapes[y+2][x+1] == null && shapes[y+2][x+2] == null
                      && shapes[y+1][x+2] == null && shapes[y][x+2] == null){
                        return false;
                    }
                }
                else{
                    if(shapes[y][x] == null && shapes[y+1][x] == null && shapes[y+2][x] == null
                      && shapes[y+2][x+1] == null && shapes[y+2][x+2] == null){
                        return false;
                    }
                }
                break; // end case H

            case I:
                if(orientation == EAST){
                    if(shapes[y][x] == null && shapes[y][x+1] == null && shapes[y+1][x+1] == null){
                        return false;
                    }
                }
                else if(orientation == SOUTH){
                    if(shapes[y][x+1] == null && shapes[y+1][x] == null && shapes[y+1][x+1] == null){
                        return false;
                    }
                }
                else if(orientation == WEST){
                    if(shapes[y][x] == null && shapes[y+1][x] == null && shapes[y+1][x+1] == null){
                        return false;
                    }
                }
                else{
                    if(shapes[y][x] == null && shapes[y][x+1] == null && shapes[y+1][x] == null){
                        return false;
                    }
                }
                break; //end case I

            case J:
                if(orientation == EAST){
                    if(shapes[y][x] == null && shapes[y][x+1] == null && shapes[y][x+2] == null &&
                      shapes[y][x+3] == null && shapes[y+1][x] == null){
                        return false;
                    }
                }
                else if(orientation == SOUTH){
                    if(shapes[y][x] == null && shapes[y][x+1] == null && shapes[y+1][x+1] == null
                      && shapes[y+2][x+1] == null && shapes[y+3][x+1] == null){
                        return false;
                    }
                }
                else if(orientation == WEST){
                    if(shapes[y][x+3] == null && shapes[y+1][x] == null && shapes[y+1][x+1] == null
                      && shapes[y+1][x+2] == null && shapes[y+1][x+3] == null){
                        return false;
                    }
                }
                else{
                    if(shapes[y][x] == null && shapes[y+1][x] == null && shapes[y+2][x] == null
                      && shapes[y+3][x] == null && shapes[y+3][x+1] == null){
                        return false;
                    }
                }
                break; //end case J

        }
        return true;
    }

    //Full credit goes to Keppil on stackoverflow
    // https://stackoverflow.com/questions/11447780/convert-two-dimensional-array-to-list-in-java
    // This functions turns a 2D array into a list
    public static List<Shape> shapesAsList(Shape[][] array){
        List<Shape> lists = new ArrayList<>();
        for(Shape[] arrays : array){
            lists.addAll(Arrays.asList(arrays));
        }
        return lists;
    }
}



package comp1110.ass2;

import static comp1110.ass2.State.*;

public enum ShapeType {
    A, B, C, D, E, F, G, H, I, J;

    /**
     * The following functions are heavily inspired by Assignment 1 "TileType".
     *
     * The following indices are the four rotations of a shape from 0 to 3.
     * The integer indicates the index into the stateKey array for the piece.
     *
     *        E          S           W          N
     *    0 1  2  3    8 4 0    11 10 9 8    3 7 11
     *    4 5  6  7    9 5 1    7  6  5 4    2 6 10
     *    8 9 10 11   10 6 2    3  2  1 0    1 5 9
     *                11 7 3                 0 4 8
     *
     * @param orientation The orientation of the piece
     * @param offX The x-offset from the piece's location
     * @param offY the y-offset from the piece's location
     * @return The state of the given orientation, x-offset and y-offset,
     * or null if the position is not covered by the shape.
     */

    public State stateFromOffset(int offX, int offY, Orientation orientation){
        if(offX < 0 || offY < 0 || offX > 3 || offY > 3){
            return null;
        }
        State[] states = stateKey[this.ordinal()];
        switch  (orientation){
            case EAST:
                if(this != H){
                    if(offY == 2){ return null;}
                }
                if(this == A || this == D || this == E || this == F || this == G || this == H){
                    return (offX == 3) ? null : states[(offY*4) + offX];
                }
                else if(this == I){
                    return (offX == 2 || offX == 3) ? null : states[(offY*4) + offX];
                }
                else{
                    return states[(offY*4 + offX)];
                }
            case SOUTH:
                if(this != H){
                    if(offX == 0){ return null;}
                }
                if(this == A || this == D || this == E || this == F || this == G || this == H){
                    return (offY == 3) ? null : states[(2 - offX)*4 + offY];
                }
                if(this == I){
                    return (offY == 2 || offY == 3) ? null : states[(2-offX)*4 + offY];
                }
                else{
                    return states[(2-offX)*4 + offY];
                }

            case WEST:
                if(this != H){
                    if(offY == 0){return null;}
                }

                if(this == A || this == D || this == E || this == F || this == G || this == H){
                    return(offX == 0) ? null : states[(2 - offY)*4 + (3 - offX)];
                }

                if(this == I){
                    return (offX == 0 || offX == 1) ? null : states[(2 - offY)*4 + (3 - offX)];
                }
                else{
                    return states[(2 - offY)*4 + (3 - offX)];
                }
            case NORTH:
                if(this != H){
                    if(offX == 2){return null;}
                }

                if(this == A || this == D || this == E || this == F || this == G || this == H){
                    return(offY == 0) ? null : states[(offX * 4) + (3 - offY)];
                }

                if(this == I){
                    return (offY == 0 || offY == 1) ? null : states[(4 * offX) + (3 - offY)];
                }

        } return null;
    }


    /**
     * stateKey represents all the shapes from A to J, representing their colour as states.
     */

    private static State[][] stateKey = {
            // Represents Shape A
            {GREEN, WHITE, RED, EMPTY,
            EMPTY, RED, EMPTY, EMPTY,
            EMPTY, EMPTY, EMPTY, EMPTY},

            //Represents Shape B
            {EMPTY, BLUE, GREEN, GREEN,
            WHITE, WHITE, EMPTY, EMPTY,
            EMPTY, EMPTY, EMPTY, EMPTY},

            //Represents Shape C
            {EMPTY, EMPTY, GREEN, EMPTY,
            RED, RED, WHITE, BLUE,
            EMPTY, EMPTY, EMPTY, EMPTY},

            //Represents Shape D
            {RED, RED, RED, EMPTY,
            EMPTY, EMPTY, BLUE, EMPTY,
            EMPTY, EMPTY, EMPTY, EMPTY},

            //Represents Shape E
            {BLUE, BLUE, BLUE, EMPTY,
            RED, RED, EMPTY, EMPTY,
            EMPTY, EMPTY, EMPTY, EMPTY},

            //Represents Shape F
            {WHITE, WHITE, WHITE, EMPTY,
            EMPTY, EMPTY, EMPTY, EMPTY,
            EMPTY, EMPTY, EMPTY, EMPTY},

            //Represents Shape G
            {WHITE, BLUE, EMPTY, EMPTY,
            EMPTY, BLUE, WHITE, EMPTY,
            EMPTY, EMPTY, EMPTY, EMPTY},

            //Represents Shape H
            {RED, GREEN, GREEN, EMPTY,
            WHITE, EMPTY, EMPTY, EMPTY,
            WHITE, EMPTY, EMPTY, EMPTY},

            //Represents Shape I
            {BLUE, BLUE, EMPTY, EMPTY,
             EMPTY, WHITE, EMPTY, EMPTY,
             EMPTY, EMPTY, EMPTY, EMPTY
            },

            //Represents Shape J
            {GREEN, GREEN, WHITE, RED,
            GREEN, EMPTY, EMPTY, EMPTY,
            EMPTY, EMPTY, EMPTY, EMPTY}
    };
}

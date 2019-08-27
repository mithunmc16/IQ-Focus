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
                    if(x == 7 && y == 2){
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
                    if(x == 7 && y == 3){return false;}

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
        return false;
    }
}

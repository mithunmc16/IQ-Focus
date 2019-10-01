package comp1110.ass2;

import static comp1110.ass2.BoardState.*;
import static comp1110.ass2.State.*;


import java.util.*;


/**
 * This class provides the text interface for the IQ Focus Game
 * <p>
 * The game is based directly on Smart Games' IQ-Focus game
 * (https://www.smartgames.eu/uk/one-player-games/iq-focus)
 */
public class FocusGame {

    /**
     * Determine whether a piece placement is well-formed according to the
     * following criteria:
     * - it consists of exactly four characters
     * - the first character is in the range a .. j (shape)
     * - the second character is in the range 0 .. 8 (column)
     * - the third character is in the range 0 .. 4 (row)
     * - the fourth character is in the range 0 .. 3 (orientation)
     *
     * @param piecePlacement A string describing a piece placement
     * @return True if the piece placement is well-formed
     */

    //Written by Benjamin Samuel
    static boolean isPiecePlacementWellFormed(String piecePlacement) {
        if (piecePlacement.length() != 4) {
            return false;
        }
        char s = piecePlacement.charAt(0);
        if (!Character.toString(s).matches("^[a-j]")) {
            return false;
        }
        char c = piecePlacement.charAt(1);
        if (!Character.toString(c).matches("^[0-8]")) {
            return false;
        }
        char r = piecePlacement.charAt(2);
        if (!Character.toString(r).matches("^[0-4]")) {
            return false;
        }
        char o = piecePlacement.charAt(3);
        if (!Character.toString(o).matches("^[0-3]")) {
            return false;
        }
        return true;
        // FIXME Task 2: determine whether a piece placement is well-formed
    }

    /**
     * Determine whether a placement string is well-formed:
     * - it consists of exactly N four-character piece placements (where N = 1 .. 10);
     * - each piece placement is well-formed
     * - no shape appears more than once in the placement
     *
     * @param placement A string describing a placement of one or more pieces
     * @return True if the placement is well-formed
     */

    //Written by Benjamin Samuel
    public static boolean isPlacementStringWellFormed(String placement) {
        if (placement.length() % 4 == 0 && placement.length() >= 4 && placement.length() <= 40) {
            String[] placements = placement.split("(?<=\\G.{" + 4 + "})");
            for (int i = 0; i < placements.length; i++) {
                if (!isPiecePlacementWellFormed(placements[i])) {
                    return false;
                }
                for (int j = i + 1; j < placements.length; j++) {
                    if (placements[i].substring(0, 1).equals(placements[j].substring(0, 1))) {
                        return false;
                    }
                }
            }
            return true;
        }
        return false;
    }


    /**
     * Determine whether a placement string is valid.
     *
     * To be valid, the placement string must be:
     * - well-formed, and
     * - each piece placement must be a valid placement according to the
     *   rules of the game:
     *   - pieces must be entirely on the board
     *   - pieces must not overlap each other
     *
     * @param placement A placement string
     * @return True if the placement sequence is valid
     */

    //Written by Joanne Louie
    public static boolean isPlacementStringValid(String placement) {
        String[] placements = placement.split("(?<=\\G.{" + 4 + "})");

        //Check if a placement string is well formed, if not, return "false"
        if(!isPlacementStringWellFormed(placement)){
            return false;
        }

        // Check if every placement is on the board
        for(String p : placements) {
            if (!isPlacementOnBoard(p)) {
                return false;
            }
        }

        //The code below checks for overlap
        Shape[] shapeArr = new Shape[placements.length]; //Create an array to store the placements as Shapes

        //update the shapes data structure ONLY IF there is no overlap between shapes
        for(int i = 0; i < placements.length; i++){
            shapeArr[i] = new Shape(placements[i]);
            if(!isShapeOverlapping(placements[i])){
                updateShapes(shapeArr[i]);
            }
        }
        List<Shape> nonOverlappingShapes = new ArrayList<>(); //a list which will only contain the shapes that were not overlapping
        Set<Shape> addedShapes = new HashSet<>(shapesAsList(shapes)); //A set of all shapes which have been added to the board
        for(Shape s : shapeArr){
            if(addedShapes.contains(s)){
                nonOverlappingShapes.add(s);
            }
        }
        //clear the shapes data structure (since placements are not actually being placed)
        shapes = new Shape[5][9];

        //check if the list which contains non-overlapping shapes is the same size as the original placements array
        //if they're not the same, at least one of the shapes in the placement string was overlapping another
        return (nonOverlappingShapes.size() == placements.length);
    }




    /**
     * Given a string describing a placement of pieces and a string describing
     * a challenge, return a set of all possible next viable piece placements
     * which cover a specific board cell.
     *
     * For a piece placement to be viable
     * - it must be valid
     * - it must be consistent with the challenge
     *
     * @param placement A viable placement string
     * @param challenge The game's challenge is represented as a 9-character string
     *                  which represents the color of the 3*3 central board area
     *                  squares indexed as follows:
     *                  [0] [1] [2]
     *                  [3] [4] [5]
     *                  [6] [7] [8]
     *                  each character may be any of
     *                  - 'R' = RED square
     *                  - 'B' = Blue square
     *                  - 'G' = Green square
     *                  - 'W' = White square
     * @param col      The cell's column.
     * @param row      The cell's row.
     * @return A set of viable piece placements, or null if there are none.
     */

    //Written by Joanne Louie
    public static Set<String> getViablePiecePlacements(String placement, String challenge, int col, int row) {
        //Create a set of all valid shapes which can be placed on the board
        Set<String> validShapes = generateValidUnplacedPieces(getUnplacedPieces(placement), placement);

        //If there is only one shape in the set of all possible valid shapes, return the set
        if(validShapes.size() == 1){
            return validShapes;
        }

        //convert the challenge colours into states & store them into a State array
        State[] challengeStates = new State[challenge.length()];
        for(int i = 0; i < challengeStates.length; i++){
            challengeStates[i] = stateFromCharacter(challenge.charAt(i));
        }

        //store the challenge states into the appropriate indexes on the 5x9 board
        //this will be changed later using a for loop but was hardcoded to ensure the challenge was encoded into the right indexes properly
        State[][] boardWithChallenge = new State[5][9];
        boardWithChallenge[1][3] = stateFromCharacter(challenge.charAt(0));
        boardWithChallenge[1][4] = stateFromCharacter(challenge.charAt(1));
        boardWithChallenge[1][5] = stateFromCharacter(challenge.charAt(2));
        boardWithChallenge[2][3] = stateFromCharacter(challenge.charAt(3));
        boardWithChallenge[2][4] = stateFromCharacter(challenge.charAt(4));
        boardWithChallenge[2][5] = stateFromCharacter(challenge.charAt(5));
        boardWithChallenge[3][3] = stateFromCharacter(challenge.charAt(6));
        boardWithChallenge[3][4] = stateFromCharacter(challenge.charAt(7));
        boardWithChallenge[3][5] = stateFromCharacter(challenge.charAt(8));

        //update the boardStates data structure with all the possible valid shapes
        //check if the shapes cover the row and column, if not, remove.
        for(Iterator<String> i = validShapes.iterator(); i.hasNext();){
            String element = i.next();
            updateStates(element);
            if(boardStates[row][col] == null){
                i.remove();
            }

            boardStates = new State[5][9];
        }
        //for each possible shape placement, update the state (the colour) in each cell
        //if a challenge cell state gets updated, it must be the same colour indicated by the challenge string. If not, remove from set.
        for(Iterator<String>i = validShapes.iterator(); i.hasNext();){
            String element = i.next();
            updateStates(element);
            for(int column = 3; column < 6; column++){
                for(int r = 1; r < 4; r++){
                    if(boardStates[r][column] != null){
                        if(boardStates[r][column] != boardWithChallenge[r][column]){
                            i.remove();
                        }
                    }
                }
            }

            boardStates = new State[5][9];
        }
        if(validShapes.size() == 0){
            return null;
        }
        // FIXME Task 6: determine the set of all viable piece placements given existing placements and a challenge
        return validShapes;
    }

    /**Generates a list of unplaced shapes given a placement string
     *
     * @param placement
     * @return all unplaced shapes in a set
     */
    //Written by Mithun Comar, rewritten as a helper function for Task 6 by Joanne Louie
    public static Set<Character> getUnplacedPieces(String placement){
        Set<Character> placementPieces = new HashSet<>();

        for(int i = 0; i < placement.length(); i+=4){
            placementPieces.add(placement.charAt(i));
        }

        Set<Character> unplacedPieces = new HashSet<>(Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j'));
        for(char piece : placementPieces){
            unplacedPieces.remove(piece);
        }
        return unplacedPieces;

    }

    /**
     * Given a set of all shapes which are unplaced, and all the places which have been placed, the function
     * generates a set of all possible shapes which can be validly placed.
     * NOTE to other members: this function needs to be optimised so it generates less pieces for Task 6 to check for.
     * This could possibly be achieved by taking in a row and column and not adding a piece to a set if its too far from the row and coluumn
     * @param pieces a set of all the unplaced shapes
     * @param placement a string representing all of the pieces which have been placed on the board
     * @return
     */

    //Written by Mithun Comar, rewritten as a helper function for Task 6 by Joanne Louie
    public static Set<String> generateValidUnplacedPieces(Set<Character> pieces, String placement){
        Set<String> placementPieces = new HashSet<>();

        for(char piece : pieces){
            String shape;
            for(int i = 0; i < 8; i++){ //i for column
                for(int j = 0; j < 5; j++){ //j for row
                    for(int o = 0; o < 4; o++){ //o for orientation
                        shape = piece + String.valueOf(i) + String.valueOf(j) + String.valueOf(o);

                        if(isPlacementStringValid(placement + shape)){
                            placementPieces.add(shape);
                        }
                    }
                }
            }
        }

        return placementPieces;
    }


    /**
     * Return the canonical encoding of the solution to a particular challenge.
     *
     * A given challenge can only solved with a single placement of pieces.
     *
     * Since some piece placements can be described two ways (due to symmetry),
     * you need to use a canonical encoding of the placement, which means you
     * must:
     * - Order the placement sequence by piece IDs
     * - If a piece exhibits rotational symmetry, only return the lowest
     *   orientation value (0 or 1)
     *
     * @param challenge A challenge string.
     * @return A placement string describing a canonical encoding of the solution to
     * the challenge.
     */
    public static String getSolution(String challenge) {
        // FIXME Task 9: determine the solution to the game, given a particular challenge
        return null;
    }
}
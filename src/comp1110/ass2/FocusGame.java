package comp1110.ass2;

import static comp1110.ass2.BoardState.*;

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

        //fill the array with the Shape pieces
        for(int i = 0; i < shapeArr.length; i++){
            shapeArr[i] = new Shape(placements[i]);
        }

        //update the shapes data structure ONLY IF there is no overlap between shapes
        for(int i = 0; i < placements.length; i++){
            if(!isShapeOverlapping(placements[i])){
                updateShapes(shapeArr[i]);
            }
        }

        //Convert the shapes data structure into a list to make it easier to check for elements
        List<Shape> listShapes = shapesAsList(shapes);
        List<Shape> l = new ArrayList<>(); //a list which will only contain the shapes that were not overlapping

        //add the non-overlapping shapes to the list
        for(Shape s : Arrays.asList(shapeArr)){
            if(listShapes.contains(s)){
                l.add(s);
            }
        }
        //clear the shapes data structure (since placements are not actually being placed)
        shapes = new Shape[5][9];

        //check if the list which contains non-overlapping shapes is the same size as the original placements array
        return (l.size() == placements.length);
            // FIXME Task 5
    }


    /**
     * Given a string describing a placement of pieces and a string describing
     * a challenge, return a set of all possible next viable piece placements
     * which cover a specific board location.
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
     * @param col      The location's column.
     * @param row      The location's row.
     * @return A set of viable piece placements, or null if there are none.
     */
    public static Set<String> getViablePiecePlacements(String placement, String challenge, int col, int row) {
        Set<String> piecelist = new HashSet<>();
        Set<Character> input = new HashSet<>();
        int length = placement.length() / 4;
        char[] placements = new char[length];

        char[] notin = new char[100];

        int x = 0;
        for (int i = 0; i < placement.length(); i += 4) {
            placements[x] = placement.charAt(i);//
            x++;
        }


        Arrays.sort(placements);


        String r = Integer.toString(row);
        String c = Integer.toString(col);
        Set<Character> allset = new HashSet<>(Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j'));

        for (int i = 0; i < placements.length; i++) {
            input.add(placements[i]);
        }
        allset.remove(input);
        //String[]  = new String[allset.size()];
        Character[] not_in_placement = allset.toArray(new Character[allset.size()]);
        Arrays.sort(not_in_placement);
        for (int i = 0; i < not_in_placement.length; i++) {
            String first = not_in_placement[i].toString();
            for (int j = 0; j < 4; j++) {
                String val = Integer.toString(j);
                for (int k = 0; k <= row + 1; k++) {
                    for (int l = 0; l <= col + 1; l++) {
                        String a = first + l + k + val;
                        String fin = placement + a;
                        if (isPlacementStringValid(fin)) {
                            piecelist.add(a);

                        }
                    }
                }
            }
        char[] Challenge = challenge.toCharArray();


            //FIXME Task 6

        }
            if(piecelist.isEmpty()){
                return null;
            }
            return piecelist;

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
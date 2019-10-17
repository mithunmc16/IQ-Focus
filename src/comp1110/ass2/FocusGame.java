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

        //Check if a placement string is well formed, if not, return "false"
        if (!isPlacementStringWellFormed(placement)) {
            return false;
        }

        String[] placements = placement.split("(?<=\\G.{" + 4 + "})");

        // Check if every placement is on the board
        for (String p : placements) {
            if (!isPlacementOnBoard(p)) {
                return false;
            }
        }

        //counts the number of non-overlapping shapes
        int count = 0;
        for (String s : placements) {
            if (!isShapeOverlapping(s)) {
                Shape shape = new Shape(s);
                updateShapes(shape);
                count++;
            }
        }

        //clears the shapes data structure (since placements aren't actually being placed)
        shapes = new Shape[5][9];

        //checks if the number of non-overlapping shapes is the same as the array with all the shapes in it
        return count == placements.length;
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

    //Written by Joanne Louie
    public static Set<String> getViablePiecePlacements(String placement, String challenge, int col, int row) {
        //Create a set of all the pieces which haven't been placed
        Set<Character> unplacedPieces = getUnplacedPieces(placement);
        Set<String> validPieces = new HashSet<>(); //Create the set which will contain all viable piece placements

        for(char piece : unplacedPieces){
            String shape;
            for(int column = 0; column<8; column++){
                for(int r = 0; r <5; r++){
                    for(int orientation = 0; orientation < 4; orientation++){
                        shape = piece + String.valueOf(column) + String.valueOf(r) + String.valueOf(orientation);

                        if(isPlacementOnBoard(shape)){
                            updateStates(shape); //update the board states only if a placement is on the board
                            if(boardStates[row][col] != null){ //check if after updating the board with a placement, it's covering the cell
                                if(placementConsistentWithChallenge(shape,challenge)){ //check if the placement is consistent with the challenge
                                    if(isPlacementStringValid(placement + shape)){
                                        validPieces.add(shape);
                                    }
                                }
                            }
                        }
                        boardStates = new State[5][9]; //remove all the states off the board to prepare for the next iteration

                    }
                }
            }
        }

        if(validPieces.size() == 0){
            return null;
        }

        return validPieces;
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
     * placementConsistentWithChallenge returns true if the placement is consistent with the challenge
     * or returns false if the placement is inconsistent with the challenge
     */

    public static boolean placementConsistentWithChallenge(String placement, String challenge){
        State[][] boardWithChallenge = new State[5][9];

        //Encodes the challenge into the appropriate locations of the board
        boardWithChallenge[1][3] = stateFromCharacter(challenge.charAt(0));
        boardWithChallenge[1][4] = stateFromCharacter(challenge.charAt(1));
        boardWithChallenge[1][5] = stateFromCharacter(challenge.charAt(2));
        boardWithChallenge[2][3] = stateFromCharacter(challenge.charAt(3));
        boardWithChallenge[2][4] = stateFromCharacter(challenge.charAt(4));
        boardWithChallenge[2][5] = stateFromCharacter(challenge.charAt(5));
        boardWithChallenge[3][3] = stateFromCharacter(challenge.charAt(6));
        boardWithChallenge[3][4] = stateFromCharacter(challenge.charAt(7));
        boardWithChallenge[3][5] = stateFromCharacter(challenge.charAt(8));

        updateStates(placement);

        //Checks if a piece is consistent with a challenge if it has covered a challenge cell
        if(boardStates[1][3]!=null){
            if(characterFromState(boardStates[1][3]) != challenge.charAt(0)){
                return false;
            }
        }

        if(boardStates[1][4]!=null){
            if(characterFromState(boardStates[1][4]) != challenge.charAt(1)){
                return false;
            }
        }

        if(boardStates[1][5]!=null){
            if(characterFromState(boardStates[1][5]) != challenge.charAt(2)){
                return false;
            }
        }

        if(boardStates[2][3]!=null){
            if(characterFromState(boardStates[2][3]) != challenge.charAt(3)){
                return false;
            }
        }

        if(boardStates[2][4]!=null){
            if(characterFromState(boardStates[2][4]) != challenge.charAt(4)){
                return false;
            } 
        }

        if(boardStates[2][5] != null){
            if(characterFromState(boardStates[2][5]) != challenge.charAt(5)){
                return false;
            }
        }

        if(boardStates[3][3]!=null){
            if(characterFromState(boardStates[3][3]) != challenge.charAt(6)){
                return false;
            }
        }

        if(boardStates[3][4]!=null){
            if(characterFromState(boardStates[3][4]) != challenge.charAt(7)){
                return false;
            }
        }
        if(boardStates[3][5] != null){
                if(characterFromState(boardStates[3][5]) != challenge.charAt(8)){
                    return false;
                }
            }

        boardStates = new State[5][9];
        return true;
    }

    /**
     * A set of pieces which cause dead cells on the board
     * This is used instead of a function which checks for dead cells since checking if a HashSet contains an element is
     * O(1) in complexity.
     */

    static Set<String> deadCells = new HashSet<>(Arrays.asList(
            "a001", "a002", "a703",
            "b000", "b701", "b002", "b703",
            "c000", "c701", "c532", "c432", "c003",
            "d030", "d001", "d602", "d723",
            "e002", "e703",
            "g001", "g600", "g602", "g003",
            "h002", "h603",
            "i001", "i702",
            "j530", "j011"));

    /**
     * A set of all the locations on the board
     * A set in the getSolution is initialised to this, and then gradually removes locations as pieces are added
     * to the solution string
     */
    static Set<String> locations = new HashSet<>(Arrays.asList(
            "00", "10", "20", "30", "40", "50", "60", "70", "80",
            "01", "11", "21", "31", "41", "51", "61", "71", "81",
            "02", "12", "22", "32", "42", "52", "62", "72", "82",
            "03", "13", "23", "33", "43", "53", "63", "73", "83",
            "14", "24", "34", "44", "54", "64", "74"
    ));


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
     * orientation value (0 or 1)
     *
     * @param challenge A challenge string.
     * @return A placement string describing a canonical encoding of the solution to
     * the challenge.
     */
    public static String getSolution(String challenge) {

        return null;
    }

    public static String solutions(String challenge) {
        LinkedList<Character> pieces = new LinkedList<>();
        pieces.add('g');
        pieces.add('f');
        pieces.add('c');
        pieces.add('b');
        pieces.add('e');
        pieces.add('j');
        pieces.add('h');
        pieces.add('d');
        pieces.add('a');
        pieces.add('i');
        LinkedList<String> unusedlocations = new LinkedList<>();
        unusedlocations.addAll(locations);
        int orientation;
        StringBuilder builtSolution = new StringBuilder(40);

        for (char shape : pieces) {
            for (String location : unusedlocations) {
                if (shape == 'g' || shape == 'f') {
                    orientation = 2;
                } else {
                    orientation = 4;
                }

                for (int i = 0; i < orientation; i++) {
                    String s = shape + location + i;
                    if (!deadCells.contains(s)) {
                        if (isPlacementOnBoard(s)) {
                            if (placementConsistentWithChallenge(s, challenge)) {
                                String allPlacements = builtSolution.toString() + s;
                                if (placementConsistentWithChallenge(allPlacements, challenge)) {
                                    if (isPlacementStringValid(allPlacements)) {
                                        builtSolution.append(s);
                                        Set<String> hSet = new HashSet<>(unusedlocations);
                                        removeOccupied(hSet, s);
                                    }
                                }
                            }
                        }
                    }
                    boardStates = new State[5][9]; //remove all the states off the board to prepare for the next iteration
                }
            }
        }
        return sortSolution(builtSolution.toString());

    }

    /**
     * Sorts the solution string by using the sort method from Collections
     */
    public static String sortSolution(String solution) {
        String[] placements = solution.split("(?<=\\G.{" + 4 + "})");
        List<String> sortedPlacements = Arrays.asList(placements);
        String sol = "";
        Collections.sort(sortedPlacements);
        for (String s : sortedPlacements) {
            sol = sol + s;
        }

        return sol;
    }

    public static Set<String> removeOccupied(Set<String> validLocations, String placement) {
        Set<String> occupied = getOccupiedLocations(placement);
        for (String s : occupied) {
            validLocations.remove(s);
        }

        return validLocations;
    }

    /**
     * Assigns shapes a value based on: 1. rotational symmetry, 2. how big they are 3. how many dead cells they cause
     */
    private static int giveValue(Character shapetype) {
        switch (shapetype) {
            case 'a':
                return 9;
            case 'b':
                return 4;
            case 'c':
                return 3;
            case 'd':
                return 8;
            case 'e':
                return 5;
            case 'f':
                return 2;
            case 'g':
                return 1;
            case 'h':
                return 7;
            case 'i':
                return 10;
            default:
                return 6;
        }
    }

    /**
     * Assigns a number a shape type
     */

    private static Character giveShape(int value) {
        switch (value) {
            case 1:
                return 'g';
            case 2:
                return 'f';
            case 3:
                return 'c';
            case 4:
                return 'b';
            case 5:
                return 'e';
            case 6:
                return 'j';
            case 7:
                return 'h';
            case 8:
                return 'd';
            case 9:
                return 'a';
            default:
                return 'i';
        }
    }

    /**
     * Prioritises shapes based on:
     * 1. rotational symmetry
     * 2. their size
     * 3. how many dead cells they cause (generally)
     */
    private static ArrayList<Character> prioritise(Set<Character> pieces){
        ArrayList<Integer> sortNum = new ArrayList<>();
        for(Character shape : pieces){
            sortNum.add(giveValue(shape));
        }

        ArrayList<Character> prioritisedShapes = new ArrayList<>();
        Collections.sort(sortNum);
        for(Integer i : sortNum){
            prioritisedShapes.add(giveShape(i));
        }
        return prioritisedShapes;
    }

    public static Set<String> nextPiece(String placement, char shapetype, Set<String> validLocations, String challenge) {
        Set<String> usableLocations = removeOccupied(validLocations,placement);
        Set<String> output = new HashSet<>();

        int rotations;
        if(shapetype == 'g' || shapetype == 'f'){
            rotations = 2;
        }
        else{
            rotations = 4;
        }

        for(int orientation = 0; orientation < rotations; orientation++){
            for(String loc : usableLocations){
                String shape = shapetype + loc + orientation;
                if(!deadCells.contains(shape)){
                    if(isPlacementOnBoard(shape)){
                        if(placementConsistentWithChallenge(shape,challenge)){
                            if(isPlacementStringValid(placement+shape)){
                                output.add(shape);
                            }
                        }
                    }
                }
                boardStates = new State[5][9]; //remove all the states off the board to prepare for the next iteration
            }
        }
        return output;
    }

    public static Set<String> getAllPieces(Set<String> placements, char shapetype, Set<String> validLocations, String challenge){
        Set<String> allPieces = new HashSet<>();
        for(String piece : placements){
            Set<String> unusedLocations = new HashSet<>(validLocations);
            Set<String> usableLocations = removeOccupied(unusedLocations,piece);
            allPieces.addAll(nextPiece(piece,shapetype,usableLocations,challenge));
        }
        return allPieces;
    }

    public static Set<String> generateStartingPieces(String challenge){
        Set<String> startingPieces = new HashSet<>();
        for(int orientation = 0; orientation < 2; orientation++){
            for(String loc: locations){
                String shape = 'g' + loc + orientation;
                if(isPlacementOnBoard(shape)){
                    if(placementConsistentWithChallenge(shape,challenge)){
                        startingPieces.add(shape);
                    }
                }
                boardStates = new State[5][9];
            }
        }
        return startingPieces;
    }
    public static Set<String> solutions(ArrayList<Character> unusedShapes, Set<String> validLocations, String challenge){
        Set<String> output = new HashSet<>(Arrays.asList(""));
        for(char s : unusedShapes){
            output = getAllPieces(output, s,validLocations, challenge);
        }
        return output;
    }

    public static void main(String[] args) {
        Set<String> startingPlacements = generateStartingPieces("RRRBWBBRB");
        ArrayList<Character> unplaced = new ArrayList<>(getUnplacedPieces("g000"));

        Set<String> idk = solutions(unplaced,locations,"RRRBWBBRB");

        for(String s : idk){
            System.out.println(s);
        }





        }

}
package comp1110.ass2;

import org.junit.Test;
import static comp1110.ass2.BoardState.*;

import static org.junit.Assert.*;

public class isPlacementOnBoardTest {
    @Test
    public void testValid(){
        assertTrue("expected 'true' for e210", isPlacementOnBoard("e210"));
        assertTrue("expected 'true' for h521", isPlacementOnBoard("h521"));
        assertTrue("expected 'true' for c002", isPlacementOnBoard("c002"));
        assertTrue("expected 'true' for e630", isPlacementOnBoard("e630"));
        assertTrue("expected 'true' for f321", isPlacementOnBoard("f321"));
        assertTrue("expected 'true' for a030", isPlacementOnBoard("a030"));
        assertTrue("expected 'true' for b011", isPlacementOnBoard("b011"));
        assertTrue("expected 'true' for c711", isPlacementOnBoard("c711"));
        assertTrue("expected 'true' for d711", isPlacementOnBoard("d711"));
        assertTrue("expected 'true' for i733", isPlacementOnBoard("i733"));
    }

    @Test
    public void testInvalid(){
        assertFalse("expected 'false' for h622", isPlacementOnBoard("h622"));
        assertFalse("expected 'false' for j323", isPlacementOnBoard("j323"));
        assertFalse("expected 'false' for d721", isPlacementOnBoard("d721"));
        assertFalse("expected 'false' for e440", isPlacementOnBoard("e440"));
        assertFalse("expected 'false' for h023", isPlacementOnBoard("h023"));
        assertFalse("expected false for a821", isPlacementOnBoard("a821"));
        assertFalse("expected false for g632", isPlacementOnBoard("g632"));
    }


}
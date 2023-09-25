package it.unibo.jtrs;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.unibo.jtrs.model.api.GameModel;
import it.unibo.jtrs.model.api.Tetromino;
import it.unibo.jtrs.model.impl.GameModelImpl;
import it.unibo.jtrs.model.impl.TetrominoImpl;
import it.unibo.jtrs.utils.Pair;
import it.unibo.jtrs.utils.TetrominoData;

/**
 * GameModel test class.
 */
class GameModelTest {

    private GameModel model;

    // CHECKSTYLE: MagicNumber OFF
     /**
     * Initialize necessary field for the tests.
     */
    @BeforeEach
    void init() {
        this.model = new GameModelImpl();
    }

    /**
     * Test next piece.
     */
    @Test
    void testNextPiece() {
        for (int i = 18; i >= 0; i -= 2) {
            assertTrue(this.model.nextPiece(new TetrominoImpl(TetrominoData.O_COORD, i, 0, TetrominoData.O_COLOR)));
        }
        assertFalse(this.model.nextPiece(new TetrominoImpl(TetrominoData.O_COORD, 0, 0, TetrominoData.O_COLOR)));
    }

    /**
     * Test delete one row.
     */
    @Test
    void testDeleteOneRow() {
        this.model.nextPiece(new TetrominoImpl(TetrominoData.I_COORD, 18, 0, TetrominoData.I_COLOR));
        this.model.nextPiece(new TetrominoImpl(TetrominoData.I_COORD, 18, 4, TetrominoData.I_COLOR));
        this.model.nextPiece(new TetrominoImpl(TetrominoData.O_COORD, 18, 8, TetrominoData.O_COLOR));
        assertEquals(1, this.model.deleteRows());
        assertEquals(2, this.model.getPieces().size());
        assertEquals(Set.of(new Pair<>(19, 8), new Pair<>(19, 9)), this.getAllComponents());
    }

    /**
     * Test delete two rows.
     */
    @Test
    void testDeleteTwoRows() {
        for (int i = 0; i < 10; i += 2) {
            this.model.nextPiece(new TetrominoImpl(TetrominoData.O_COORD, 18, i, TetrominoData.O_COLOR));
        }
        this.model.nextPiece(new TetrominoImpl(TetrominoData.O_COORD, 16, 0, TetrominoData.O_COLOR));

        assertEquals(2, this.model.deleteRows());
        assertEquals(1, this.model.getPieces().size());
        assertEquals(Set.of(new Pair<>(18, 0), new Pair<>(18, 1), 
            new Pair<>(19, 0), new Pair<>(19, 1)), this.getAllComponents());
    }

    /**
     * Test delete three rows.
     */
    @Test
    void testDeleteThreeRows() {
        for (int i = 0; i < 8; i += 2) {
            this.model.nextPiece(new TetrominoImpl(TetrominoData.O_COORD, 18, i, TetrominoData.O_COLOR));
        }
        this.model.nextPiece(new TetrominoImpl(TetrominoData.I_COORD, 16, 0, TetrominoData.I_COLOR));
        this.model.nextPiece(new TetrominoImpl(TetrominoData.I_COORD, 16, 4, TetrominoData.I_COLOR));

        Tetromino tmp = new TetrominoImpl(TetrominoData.I_COORD, 16, 6, TetrominoData.I_COLOR);
        tmp.rotate();
        this.model.nextPiece(tmp);
        tmp = new TetrominoImpl(TetrominoData.I_COORD, 16, 7, TetrominoData.I_COLOR);
        tmp.rotate();
        this.model.nextPiece(tmp);

        assertEquals(3, this.model.deleteRows());
        assertEquals(2, this.model.getPieces().size());
        assertEquals(Set.of(new Pair<>(19, 8), new Pair<>(19, 9)), this.getAllComponents());
    }

    /**
     * Test delete four rows.
     */
    @Test
    void testDeleteFourRows() {
        Tetromino tmp;
        for (int i = 0; i < 10; i++) {
            tmp = new TetrominoImpl(TetrominoData.I_COORD, 16, i - 2, TetrominoData.I_COLOR);
            tmp.rotate();
            this.model.nextPiece(tmp);
        }
        this.model.nextPiece(new TetrominoImpl(TetrominoData.O_COORD, 14, 0, TetrominoData.O_COLOR));
        assertEquals(4, this.model.deleteRows());
        assertEquals(1, this.model.getPieces().size());
        assertEquals(Set.of(new Pair<>(19, 0), new Pair<>(19, 1), 
            new Pair<>(18, 0), new Pair<>(18, 1)), this.getAllComponents());
    }
    // CHECKSTYLE: MagicNumber ON

    Set<Pair<Integer, Integer>> getAllComponents() {
        return this.model.getPieces().stream()
                .flatMap(p -> p.getComponents().stream())
                .collect(Collectors.toSet());
    }
}

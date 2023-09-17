package it.unibo.jtrs.controller.impl;

import it.unibo.jtrs.model.api.PreviewModel;
import it.unibo.jtrs.model.api.Tetromino;
import it.unibo.jtrs.model.impl.PreviewModelImpl;

/**
 * PreviewController implementation.
 */
public class PreviewController {

    private final PreviewModel model;

    /**
     * Constructor.
     */
    public PreviewController() {
        this.model = new PreviewModelImpl();
    }

    /**
     * Return the current Tetromino.
     * 
     * @return the Tetromino
     */
    public Tetromino getCurrentTetromino() {
        return this.model.current();
    }

    /**
     * Advance to next Tetromino.
     */
    public void nextTetromino() {
        this.model.next();
    }

}

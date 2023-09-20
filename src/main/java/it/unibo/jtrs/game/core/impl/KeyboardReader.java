package it.unibo.jtrs.game.core.impl;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;

import it.unibo.jtrs.game.core.api.KeyboardQuery;

/**
 * This class is able to detect which directional key is pressed.
 */
public class KeyboardReader implements KeyListener, KeyboardQuery {

    private static final int SCAN_RATE = 250;

    private final Map<Integer, Boolean> keys = new HashMap<>();
    private long millis;

    /**
     * Constructor.
     */
    public KeyboardReader() {
        this.keys.put(KeyEvent.VK_W, false);
        this.keys.put(KeyEvent.VK_S, false);
        this.keys.put(KeyEvent.VK_A, false);
        this.keys.put(KeyEvent.VK_D, false);

        this.millis = System.currentTimeMillis();
    }

    @Override
    public void keyTyped(final KeyEvent e) { }

    /**
     * {@inheritDoc}
     */
    @Override
    public void keyPressed(final KeyEvent e) {
        this.keys.put(e.getKeyCode(), true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void keyReleased(final KeyEvent e) {
        this.keys.put(e.getKeyCode(), false);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isUpPressed() {
        return this.keys.get(KeyEvent.VK_W) && this.isReady();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isDownPressed() {
        return this.keys.get(KeyEvent.VK_S) && this.isReady();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isLeftPressed() {
        return this.keys.get(KeyEvent.VK_A) && this.isReady();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isRightPressed() {
        return this.keys.get(KeyEvent.VK_D) && this.isReady();
    }

    private boolean isReady() {
        if (System.currentTimeMillis() - this.millis > SCAN_RATE) {
            this.millis = System.currentTimeMillis();
            return true;
        }
        return false;
    }

}

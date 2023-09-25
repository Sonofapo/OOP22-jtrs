package it.unibo.jtrs.view.impl;

import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import it.unibo.jtrs.controller.api.Application;
import it.unibo.jtrs.view.api.GenericPanel;
import it.unibo.jtrs.view.custom.Constants;

/**
 * A class modelling the main application panel. This panel consists of
 * 3 sub-panels, displaying the game field, the preview and the score.
 */
public class MainPanel extends GenericPanel {

    public static final long serialVersionUID = 4328743;

    private final PreviewPanel previewPanel;
    private final ScorePanel scorePanel;
    private final GamePanel gamePanel;

    /**
     * Constructor.
     *
     * @param application the application to manage
     */
    public MainPanel(final Application application) {
        this.previewPanel = new PreviewPanel(application.getPreviewController());
        this.scorePanel = new ScorePanel(application.getScoreController());
        this.gamePanel = new GamePanel(application.getGameController());

        this.setLayout(new GridLayout(1, 2));
        this.setBorder(new EmptyBorder(Constants.ApplicationPanel.PADDING,
            Constants.ApplicationPanel.PADDING,
            Constants.ApplicationPanel.PADDING,
            Constants.ApplicationPanel.PADDING));
        this.setOpaque(false);

        final var subpanel = new JPanel(new GridLayout(2, 1));
        subpanel.setOpaque(false);
        subpanel.add(this.previewPanel);
        subpanel.add(this.scorePanel);

        this.add(this.gamePanel);
        this.add(subpanel);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void redraw() {
        this.gamePanel.redraw();
        this.previewPanel.redraw();
        this.scorePanel.redraw();
    }

}

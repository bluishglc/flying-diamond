/**
 * 
 */
package flyingdiamond.view.uicomponent;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JToolBar;

import flyingdiamond.common.IconResourcer;
import flyingdiamond.common.ResourceEnum;

/**
 * The Class ToolBar.
 */
public class ToolBar extends JToolBar{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 3194048681053771294L;
	
	/** The new game button. */
	private JButton newGameButton;
	
	/** The speaker button. */
	private JButton speakerButton;
	
	/** The exit button. */
	private JButton exitButton;
	
	/** The score panel. */
	private ScorePanel scorePanel;
	
	/** The life panel. */
	private LifePanel lifePanel;
	
	/**
	 * Initialize.
	 */
	public void initialize(){
		setRollover(true);
		setFloatable(false);
		setLayout(new FlowLayout(FlowLayout.LEADING));
		//config newGameButton
		newGameButton.setFocusable(false);
		newGameButton.setPreferredSize(new Dimension(40,40));
		newGameButton.setIcon(IconResourcer.getInstance().getIcon(ResourceEnum.ICON_NEW_GAME_LARGE));
		add(newGameButton);
		//config speakerButton
		speakerButton.setFocusable(false);
		speakerButton.setPreferredSize(new Dimension(40,40));
		speakerButton.setIcon(IconResourcer.getInstance().getIcon(ResourceEnum.ICON_SPEAKER_LARGE));
		add(speakerButton);
		//config exitButton
		exitButton.setFocusable(false);
		exitButton.setPreferredSize(new Dimension(40,40));
		exitButton.setIcon(IconResourcer.getInstance().getIcon(ResourceEnum.ICON_EXIT_LARGE));
		add(exitButton);
		//sorce panel.
		add(scorePanel);
		//life panel.
		add(lifePanel);
	}
	
	/*------------------------------------   Accessor Methods   ------------------------------------ */

	/**
	 * Gets the new game button.
	 * 
	 * @return the newGameButton
	 */
	public JButton getNewGameButton() {
		return newGameButton;
	}

	/**
	 * Sets the new game button.
	 * 
	 * @param newGameButton the newGameButton to set
	 */
	public void setNewGameButton(JButton newGameButton) {
		this.newGameButton = newGameButton;
	}

	/**
	 * Gets the score panel.
	 * 
	 * @return the scorePanel
	 */
	public ScorePanel getScorePanel() {
		return scorePanel;
	}

	/**
	 * Sets the score panel.
	 * 
	 * @param scorePanel the scorePanel to set
	 */
	public void setScorePanel(ScorePanel scorePanel) {
		this.scorePanel = scorePanel;
	}

	/**
	 * Gets the life panel.
	 * 
	 * @return the lifePanel
	 */
	public LifePanel getLifePanel() {
		return lifePanel;
	}

	/**
	 * Sets the life panel.
	 * 
	 * @param lifePanel the lifePanel to set
	 */
	public void setLifePanel(LifePanel lifePanel) {
		this.lifePanel = lifePanel;
	}

	/**
	 * Gets the speaker button.
	 * 
	 * @return the speakerButton
	 */
	public JButton getSpeakerButton() {
		return speakerButton;
	}

	/**
	 * Sets the speaker button.
	 * 
	 * @param speakerButton the speakerButton to set
	 */
	public void setSpeakerButton(JButton speakerButton) {
		this.speakerButton = speakerButton;
	}

	/**
	 * Gets the exit button.
	 * 
	 * @return the exitButton
	 */
	public JButton getExitButton() {
		return exitButton;
	}

	/**
	 * Sets the exit button.
	 * 
	 * @param exitButton the exitButton to set
	 */
	public void setExitButton(JButton exitButton) {
		this.exitButton = exitButton;
	}
	
	
}

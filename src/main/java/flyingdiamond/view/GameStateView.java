/**
 * 
 */
package flyingdiamond.view;

import java.lang.reflect.InvocationTargetException;
import java.util.Observable;
import java.util.Observer;

import javax.swing.SwingUtilities;

import org.apache.log4j.Logger;

import flyingdiamond.model.GameRunningManager;
import flyingdiamond.model.ModelUpdateCaseEnum;
import flyingdiamond.view.uicomponent.LifePanel;
import flyingdiamond.view.uicomponent.ScorePanel;


/**
 * The GameStateView presents game running status such as score and life.
 */
public class GameStateView implements Observer {
	
	private static Logger logger = Logger.getLogger(GameStateView.class.getName());
	
	/** The score panel. */
	private ScorePanel scorePanel;
	
	/** The life panel. */
	private LifePanel lifePanel;
	
	/**
	 * Initialize.
	 */
	public void initialize(){	
	}

	/*------------------------------------   Business Methods   ------------------------------------ */
	
	/**
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	public void update(final Observable o, final Object arg) {
		try {
			ModelUpdateCaseEnum updateCase =(ModelUpdateCaseEnum) arg;
			if(updateCase==ModelUpdateCaseEnum.NEW_GAME_PREPROCESS
			   ||updateCase==ModelUpdateCaseEnum.GOAL
			   ||updateCase==ModelUpdateCaseEnum.NEW_LEVEL){
				//NOTE:All view update work are UI work,they should be in EDT!!!
				//The update method is called by models,they are in system threads,
				//So,SwingUtilities.invokeAndWait can put the Runnable job in EDT!
				//Be carefull:We should use invokeAndWait not invokelater,because,
				//Ours UI update are ordered!For example:When matrix loading,we have to
				//waiting for one grew and the up updated,then grow a new line and update
				//again.
				SwingUtilities.invokeAndWait(new Runnable() {
					public void run() {
						GameRunningManager controller = (GameRunningManager) o;
						scorePanel.showScore(controller.getTotalScore());
						lifePanel.showLife(controller.getLife());
						logger.info("The game state view updated by game runing manager's new status");
					}
				});
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	/*------------------------------------   Accessor Methods   ------------------------------------ */
	
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
	
}

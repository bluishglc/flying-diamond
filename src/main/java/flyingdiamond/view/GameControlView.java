/**
 * 
 */
package flyingdiamond.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JMenuItem;

import flyingdiamond.controller.GameRunningController;
import flyingdiamond.model.ModelUpdateCaseEnum;

/**
 * The GameControlView is view of GameRunningManager. It provides game control ui and delegate
 * actions to GameRunningController.
 * 
 * @author Laurence Geng
 * @version 1.0
 * @created 2009-2-1
 */
public class GameControlView implements ActionListener,Observer{
	
	/** The new game button. */
	private JButton newGameButton;
	
	/** The new game menu item. */
	private JMenuItem newGameMenuItem;
	
	/** The exit button. */
	private JButton exitButton;
	
	/** The exit menu item. */
	private JMenuItem exitMenuItem;
	
	/** The game running controller. */
	private GameRunningController gameRunningController;
	
	/** The new game operation enabled flag. */
	private boolean newGameEnabled = true;
	
	/*------------------------------------   Business Methods   ------------------------------------ */
	
	/**
	 * Initialize.
	 */
	public void initialize(){
		newGameButton.addActionListener(this);
		newGameMenuItem.addActionListener(this);
		exitButton.addActionListener(this);
		exitMenuItem.addActionListener(this);
	}
	
	public void update(Observable o, Object arg) {
		ModelUpdateCaseEnum updateCase =(ModelUpdateCaseEnum) arg;
		if(updateCase==ModelUpdateCaseEnum.RELOAD_PREPROCESS
		   ||updateCase==ModelUpdateCaseEnum.NEW_GAME_PREPROCESS){
			setNewGameEnabled(false);
		}else if(updateCase==ModelUpdateCaseEnum.RELOAD_POSTPROCESS
				 ||updateCase==ModelUpdateCaseEnum.NEW_GAME_POSTPROCESS){
			setNewGameEnabled(true);
		}
	}
	
	/**
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(newGameButton) || e.getSource().equals(newGameMenuItem)) {
			//For all evnet-handler methods,they are all in EDT!!!
			//Here,the operation in vnet-handler methods are backend model's work,
			//It is not concerned to UI,so,we have to make these job be in system thread!
			//If during model's work,the UI need fresh,such like:view's update method which
			//call by notifyview methods,we then put the UI work in EDT!See update mehtod!
			new Thread() {
				@Override
				public void run() {
					gameRunningController.newGame();
				}
			}.start();
		}else if (e.getSource().equals(exitButton) || e.getSource().equals(exitMenuItem)) {
			new Thread() {
				@Override
				public void run() {
					gameRunningController.endGame();
				}
			}.start();
		}
	}
	
	/**
	 * Checks if is new game enabled.
	 * 
	 * @return true, if is new game enabled
	 */
	public boolean isNewGameEnabled() {
		return newGameEnabled;
	}

	/**
	 * Sets the new game enabled.
	 * 
	 * @param newGameEnabled the new new game enabled
	 */
	public void setNewGameEnabled(boolean newGameEnabled) {
		this.newGameEnabled = newGameEnabled;
		newGameButton.setEnabled(newGameEnabled);
		newGameMenuItem.setEnabled(newGameEnabled);
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
	 * Gets the game running controller.
	 * 
	 * @return the game running controller
	 */
	public GameRunningController getGameRunningController() {
		return gameRunningController;
	}

	/**
	 * Sets the game running controller.
	 * 
	 * @param gameRunningController the new game running controller
	 */
	public void setGameRunningController(GameRunningController gameRunningController) {
		this.gameRunningController = gameRunningController;
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
	 * Gets the new game menu item.
	 * 
	 * @return the newGameMenuItem
	 */
	public JMenuItem getNewGameMenuItem() {
		return newGameMenuItem;
	}

	/**
	 * Sets the new game menu item.
	 * 
	 * @param newGameMenuItem the newGameMenuItem to set
	 */
	public void setNewGameMenuItem(JMenuItem newGameMenuItem) {
		this.newGameMenuItem = newGameMenuItem;
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

	/**
	 * Gets the exit menu item.
	 * 
	 * @return the exitMenuItem
	 */
	public JMenuItem getExitMenuItem() {
		return exitMenuItem;
	}

	/**
	 * Sets the exit menu item.
	 * 
	 * @param exitMenuItem the exitMenuItem to set
	 */
	public void setExitMenuItem(JMenuItem exitMenuItem) {
		this.exitMenuItem = exitMenuItem;
	}
	
}

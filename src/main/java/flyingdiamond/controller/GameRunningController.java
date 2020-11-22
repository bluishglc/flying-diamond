/*
 * GameRunningController.java
 */

package flyingdiamond.controller;

import org.apache.log4j.Logger;

import flyingdiamond.model.ArticleMatrix;
import flyingdiamond.model.GameRunningManager;
import flyingdiamond.view.ArticleMatrixView;
import flyingdiamond.view.GameControlView;
import flyingdiamond.view.GameStateView;
import flyingdiamond.view.MainView;
import flyingdiamond.view.SoundView;

/**
 * The core controller.It takes charge of delegating player actions from views:
 * to models:GameRunningManager.
 * NOTE:The GameRunningController does not contains nextLevel and retry method,which
 * means the controller won't delegate go-to-next-level and retry-this-level action to GameRunningManager,
 * because they are model's inner operations,they will be invoked in model automatically not triggered
 * by user action.
 * 
 * @author Laurence Geng
 * @version 1.0
 * @created 2009-2-1
 */
public class GameRunningController {
	
	/** The logger. */
	private static Logger logger = Logger.getLogger(GameRunningController.class.getName());
	
	/** The article matrix. */
	private ArticleMatrix articleMatrix;
	
	/** The game running manager. */
	private GameRunningManager gameRunningManager;
	
	/** The main view. */
	private MainView mainView;
	
	/** The article matrix view. */
	private ArticleMatrixView articleMatrixView;
	
	/** The game state view. */
	private GameStateView gameStateView;
	
	/** The game control view. */
	private GameControlView gameControlView;
	
	/** The sound view. */
	private SoundView soundView;
	
	/**
	 * Initialize.
	 */
	public void initialise() {
		// Initialize views.
		articleMatrixView.initialize();
		gameStateView.initialize();
		gameControlView.initialize();
		mainView.initialize();
		soundView.initialize();

		// Register views for models.
		articleMatrix.addObserver(articleMatrixView);
		articleMatrix.addObserver(soundView);
		gameRunningManager.addObserver(mainView);
		gameRunningManager.addObserver(gameStateView);
		gameRunningManager.addObserver(gameControlView);
		gameRunningManager.addObserver(soundView);
	}
	
    /**
     * New game.
     */
    public void newGame() {
    	gameRunningManager.newGame();
    	logger.info("start new game.");
	}
    
    /**
     * End game.
     */
    public void endGame(){
    	gameRunningManager.endGame();
    	logger.info("Game over.");
    }

	/**
	 * Activate article.
	 * 
	 * @param row the row
	 * @param col the col
	 */
	public void activateArticle(int row, int col) {
		logger.info("The artilce ("+row+","+col+") is activated");
		gameRunningManager.activateArticle(row, col);
	}

	/**
	 * Unactivate articles.
	 */
	public void unactivateArticles(){
		gameRunningManager.unactivateArticles();
		logger.info("All articles dis-activated.");
	}
	
	/**
	 * Removes the activated articles.
	 */
	public void removeActivatedArticles(){
		gameRunningManager.removeActivatedArticles();
	}

    /*------------------------------------   Accessor Methods   ------------------------------------ */
    
    /**
     * Gets the article matrix.
     * 
     * @return the articleMatrix
     */
	public ArticleMatrix getArticleMatrix() {
		return articleMatrix;
	}
	
	/**
	 * Sets the article matrix.
	 * 
	 * @param articleMatrix the articleMatrix to set
	 */
	public void setArticleMatrix(ArticleMatrix articleMatrix) {
		this.articleMatrix = articleMatrix;
	}
	
	/**
	 * Gets the main view.
	 * 
	 * @return the mainView
	 */
	public MainView getMainView() {
		return mainView;
	}
	
	/**
	 * Sets the main view.
	 * 
	 * @param mainView the mainView to set
	 */
	public void setMainView(MainView mainView) {
		this.mainView = mainView;
	}
	
	/**
	 * Gets the article matrix view.
	 * 
	 * @return the articleMatrixView
	 */
	public ArticleMatrixView getArticleMatrixView() {
		return articleMatrixView;
	}
	
	/**
	 * Sets the article matrix view.
	 * 
	 * @param articleMatrixView the articleMatrixView to set
	 */
	public void setArticleMatrixView(ArticleMatrixView articleMatrixView) {
		this.articleMatrixView = articleMatrixView;
	}
	
	/**
	 * Gets the game state view.
	 * 
	 * @return the gameStateView
	 */
	public GameStateView getGameStateView() {
		return gameStateView;
	}
	
	/**
	 * Sets the game state view.
	 * 
	 * @param gameStateView the gameStateView to set
	 */
	public void setGameStateView(GameStateView gameStateView) {
		this.gameStateView = gameStateView;
	}

	/**
	 * Gets the game control view.
	 * 
	 * @return the gameControlView
	 */
	public GameControlView getGameControlView() {
		return gameControlView;
	}

	/**
	 * Sets the game control view.
	 * 
	 * @param gameControlView the gameControlView to set
	 */
	public void setGameControlView(GameControlView gameControlView) {
		this.gameControlView = gameControlView;
	}

	/**
	 * Gets the game running manager.
	 * 
	 * @return the gameRunningManager
	 */
	public GameRunningManager getGameRunningManager() {
		return gameRunningManager;
	}

	/**
	 * Sets the game running manager.
	 * 
	 * @param gameRunningManager the gameRunningManager to set
	 */
	public void setGameRunningManager(GameRunningManager gameRunningManager) {
		this.gameRunningManager = gameRunningManager;
	}

	/**
	 * Gets the sound view.
	 * 
	 * @return the sound view
	 */
	public SoundView getSoundView() {
		return soundView;
	}

	/**
	 * Sets the sound view.
	 * 
	 * @param soundView the new sound view
	 */
	public void setSoundView(SoundView soundView) {
		this.soundView = soundView;
	}

}

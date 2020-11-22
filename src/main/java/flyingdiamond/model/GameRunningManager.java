package flyingdiamond.model;

import java.util.Observable;

import org.apache.log4j.Logger;

import flyingdiamond.model.gamerule.BombBonusRule;
import flyingdiamond.model.gamerule.LevelRule;

/**
 * The GameRunningManager is the game running model.It contains game states,such
 * like:socre,life,level and so on. It provides APIs to control the game
 * running,such like:start a new game,step to next level and so on.It contains
 * an ArticleMatrix,all operations involved in article matrix compute are
 * delegated to the ArticleMatrix.
 * <p>
 * Why does the GameRunningManager have to dependent an ArticleMatrix? From an
 * overview,the work of delegating to ArticleMatrix may be an controller's
 * responsibility,however,the GameRunningManager concern with ArticleMatrix
 * tightly.For each active action,the GameRunningManager has to get the compute
 * result of ArticleMatrix so as to calculate score,level and life,and at the
 * same time,GameRunningManager has to make an decision whether give the player
 * bonus.Different bonus will cause different article generating for
 * ArticleMatrix.So it is better to let GameRunningManager manage ArticleMatrix.
 * 
 * @author Laurence Geng
 * @version 1.0
 * @created 2009-2-1
 */
public class GameRunningManager extends Observable{
	
	/** The logger. */
	private static Logger logger = Logger.getLogger(GameRunningManager.class.getName());
	
	/** The interval of row growing when matrix is reloading. */
	private static final int INTERVAL = 1000;
	
	/** Max life count for new game. */
	public static final int MAX_LIVES = 3;

	/** The total score. */
	private int totalScore;
	
	/** The score get in a level. */
	private int levelScore;
	
	/** The level. */
	private int level;
	
	/** The life. */
	private int life;
	
	/** The article matrix. */
	private ArticleMatrix matrix;
	
	/** The flag for level lost. */
	private boolean levelLost = false;
	
	
	/**
	 * Instantiates a new game running manager.
	 * 
	 * @param matrix the matrix
	 */
	public GameRunningManager(ArticleMatrix matrix) {
		this.matrix = matrix;
	}
	
	/*------------------------------------   Business Methods   ------------------------------------ */
	
	/**
	 * Activates the specified articles.
	 * 
	 * @param row the specified row
	 * @param col the specified col
	 */
	public void activateArticle(int row, int col) {
		matrix.activateArticle(row, col);
		matrix.notifyViews(ModelUpdateCaseEnum.ACTIVATED);
	}

	/**
	 * Removes all activated articles.Before remove,manager need get the amount
	 * of removed articles,calculate score,life and level, and decide how to give bonus.
	 * 
	 * @return true, if removes the activated articles
	 */
	public void removeActivatedArticles(){
		//The GameRunningUpdateEnum is for view update,but,we 
		//have to provide another type to tell controller how to go
		//to next step,such as:next level or retry this level.
		if(matrix.isActivated()){		
			if (matrix.isFull()) {
				life--;
				if (life >= 0) {
					notifyViews(ModelUpdateCaseEnum.LEVEL_LOST);
					retry();
				}else{
					notifyViews(ModelUpdateCaseEnum.GAME_OVER);
				}
			} else {
				//Calculates score.
				int activatedCount = matrix.getActivatedCount();
				boolean keepScore = matrix.getActivatedArticle() instanceof Scoreable;
				if (keepScore) {
					totalScore += activatedCount;
					levelScore += activatedCount;
					notifyViews(ModelUpdateCaseEnum.GOAL);
					notifyViews(new BombBonusRule().filterBonusUpdateCase(activatedCount));
				}
				//Remove activated articles.
				matrix.notifyViews(ModelUpdateCaseEnum.REMOVE_ARTICLE_PREPROCESS);
				matrix.removeActivatedArticles();
				matrix.realign();
				matrix.grow(new BombBonusRule().filterArticleGenerationStrategy(activatedCount));
				matrix.notifyViews(ModelUpdateCaseEnum.STRUCTURE_CHANGED);				
				// If level passed.
				if (levelPassed()) {   
					notifyViews(ModelUpdateCaseEnum.LEVEL_PASSED);
					nextLevel();
				}
			}
		}
	}
	
	/**
	 * Disactivates articles.
	 */
	public void unactivateArticles(){
		matrix.unactivateArticles();
		matrix.notifyViews(ModelUpdateCaseEnum.UNACTIVATED);
	}
	
	/**
	 * Notify views.
	 * 
	 * @param caseEnum the case enum
	 */
	public void notifyViews(ModelUpdateCaseEnum caseEnum) {
		setChanged();
		notifyObservers(caseEnum);
	}

	/**
	 * Starts a new game.
	 */
	public void newGame() {
		totalScore = 0;
		levelScore = 0;
		level = 1;
		life = MAX_LIVES;
		levelLost = false;		
		notifyViews(ModelUpdateCaseEnum.NEW_GAME_PREPROCESS);	
		//NOTE:NEW GAME HAS PROBOBOM!
		matrix.reload(new LevelRule().getMatrixInitHeight(level),INTERVAL);	
		notifyViews(ModelUpdateCaseEnum.NEW_GAME_POSTPROCESS);
	}

	/**
	 * Steps to next level.
	 * Here:need operate the matrix!
	 */
	private void nextLevel(){
		++level;
		levelScore = 0;
		notifyViews(ModelUpdateCaseEnum.NEW_LEVEL);
		//reload matrix!
		matrix.reload(new LevelRule().getMatrixInitHeight(level),INTERVAL);
	}
	
	/**
	 * Retry current level.
	 */
	private void retry(){
		
	}
	
	/**
	 * Ends game.
	 */
	public void endGame(){
		
	}
	
	private boolean levelPassed(){
		return levelScore >= 100 + level * 5;
	}
	
	/*------------------------------------   Accessor Methods   ------------------------------------ */
	
	/**
	 * Gets the total score.
	 * 
	 * @return the totalScore
	 */
	public int getTotalScore() {
		return totalScore;
	}

	/**
	 * Gets the level score.
	 * 
	 * @return the levelScore
	 */
	public int getLevelScore() {
		return levelScore;
	}

	/**
	 * Gets the level.
	 * 
	 * @return the level
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * Gets the life.
	 * 
	 * @return the life
	 */
	public int getLife() {
		return life;
	}


	/**
	 * Gets the matrix.
	 * 
	 * @return the matrix
	 */
	public ArticleMatrix getMatrix() {
		return matrix;
	}

	/**
	 * Sets the matrix.
	 * 
	 * @param matrix the matrix to set
	 */
	public void setMatrix(ArticleMatrix matrix) {
		this.matrix = matrix;
	}

}

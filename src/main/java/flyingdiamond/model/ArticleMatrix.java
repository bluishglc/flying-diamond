package flyingdiamond.model;

import java.util.Observable;

import org.apache.log4j.Logger;

import flyingdiamond.model.articlegeneration.ArticleGenerationStrategy;
import flyingdiamond.model.articlegeneration.ArticleGenerationStrategyFactory;
import flyingdiamond.model.chainreaction.ActivationChainReaction;

/**
 * A matrix of articles.In the game model,the essence of game running is computing of the
 * article matrix.The ArticleMatrix operate inner articles according to player's request.
 * Once the ArticleMatrix's inner state changed,it will notice its view:ArticleMatrixView.
 * 
 * @author Laurence Geng
 * @version 1.0
 * @created 2009-2-1
 */
public class ArticleMatrix extends Observable{
	
	/** The logger. */
	private static Logger logger = Logger.getLogger(ArticleMatrix.class.getName());
	
	/** The Constant:HEIGHT. */
	public static final int HEIGHT = 15;
	
	/** The Constant:WIDTH. */
	public static final int WIDTH = 15;
	
	/** The Constant: MAX_MATRIX_INIT_HEIGHT. */
	public static final int MAX_MATRIX_INIT_HEIGHT = 10;
	
	/** The height. */
	private int height=HEIGHT;
	
	/** The width. */
	private int width=WIDTH;
	
	/** The articles the matrix contains and operates. */
	private Article[][] articles = new Article[height][width];
	
	/** If any articles activated,the matrix is marked as activated. */
	private boolean activated = false;
	
	/** The activated row. */
	private int activatedRow = -1;
	
	/** The activated col. */
	private int activatedCol = -1;
	
	/** The activated count. */
	private int activatedCount = 0;
	
	/*------------------------------------   Business Methods   ------------------------------------ */
	
	/**
	 * Activates specified article.For different articles,matrix apply different
	 * active operation,in other words,the matrix couples with atricle's concrete
	 * types.to avoid this,we introduced ActivationChainReaction which represents
	 * the chain reaction when a type of article is activated.Different type article
	 * have different ActivationChainReaction,when an article activated,the matrix
	 * also get its chain reaction and apply it. We use the ActivationChainReaction
	 * to decouple matrix and particular type articles.
	 * 
	 * @param row the specified article's row.
	 * @param col the specified article's column.
	 */
	public void activateArticle(int row, int col){
		if (articles[row][col]!=null) {
			ActivationChainReaction reaction = articles[row][col].getActivationChainReaction();
			if(reaction.canActivate(row, col, this)){
				activatedCount=reaction.react(row, col, this);
				activated = true;
				activatedRow = row;
				activatedCol = col;
			}
		}
	}
	
	/**
	 * Unactivate articles.
	 */
	public void unactivateArticles() {
		activated = false;
		activatedRow = -1;		
		activatedCol = -1;	
		activatedCount = 0;
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				if(articles[i][j]!=null&&articles[i][j].isActivated()){
					articles[i][j].setActivated(false);
				}
			}
		}
	}
	
	/**
	 * Removes the activated articles.
	 * 
	 * @return the count of removed articles.
	 */
	public void removeActivatedArticles() {
		if (activated) {
			for (int i = 0; i < height; i++) {
				for (int j = 0; j < width; j++) {
					if (articles[i][j] != null && articles[i][j].isActivated()) {
						articles[i][j] = null;
					}
				}
			}
			activated = false;
			activatedRow = -1;		
			activatedCol = -1;	
			activatedCol = 0;
		}
//		return count;
	}
		
	/**
	 * Clears the matrix,remove all articles.
	 */
	public void clear(){
		activated = false;
		activatedRow = -1;		
		activatedCol = -1;	
		activatedCount = 0;
		for(int i=0;i<height;i++){
			for(int j=0;j<width;j++){
				removeArtilce(i, j);
			}
		}
		notifyViews(ModelUpdateCaseEnum.STRUCTURE_CHANGED);
	}
		
	/**
	 * Reload articles according to row count.
	 * 
	 * @param rows the rows
	 * @param interval The interval of row growing when matrix is reloading
	 */
	public void reload(int rows,long interval) {
		try {
			notifyViews(ModelUpdateCaseEnum.RELOAD_PREPROCESS);
			clear();
			// Sets initial articles.
			for (int i = 0; i < rows; i++) {
				grow(ArticleGenerationStrategyFactory.getInstance()
						.createStrategy(ArticleGenerationStrategy.NORMAL_STRATEGY));
				Thread.sleep(interval);
				notifyViews(ModelUpdateCaseEnum.STRUCTURE_CHANGED);
			}
			notifyViews(ModelUpdateCaseEnum.RELOAD_POSTPROCESS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} 
	}
	
	/**
	 * Checks whether the matrix is full. If the any column's top position is
	 * took up by an article,The matrix is regard as full.
	 * 
	 * @return true, if is full
	 */
	public boolean isFull(){
		for (int i = 0; i < width; i++) {
			if (articles[height - 1][i]!=null) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Generates a line of articles.This is template method,which type article will
	 * be generated is up to the ArticleGenerationStrategy which is set by client such
	 * like GameRunningManager.
	 * 
	 * @param strategy the article generation strategy
	 */
	public void grow(ArticleGenerationStrategy strategy){
		// Moves all articles upward.
		for (int i = 0; i < width; i++) {
			for (int j = height - 2; j >= 0; j--) {
				moveArticle(j,i,j+1,i);
			}
		}
		// Generates a new line of articles and sets them to bottom row.
		strategy.generateArticles(articles[0]);
		logger.info("Matrix has grown.");
	}

	/**
	 * Realigns matrix.move those hanging articles down and align
	 * the matrix to left.
	 */
	public void realign() {
		// Aligns the matrix to bottom.
		for (int col = 0; col <= width - 1; col++) {
			int i = 0, j = 0;
			while (i <= height - 1 && j <= height - 1) {
				while (i <= height - 1 && articles[i][col]!=null) {
					++i;
				}
				j = i;
				while (j <= height - 1 && articles[j][col]==null) {
					++j;
				}
				if (i <= height - 1 && j <= height - 1) {
					moveArticle(j, col, i, col);
				}
			}
		}
		// Aligns the matrix to left.
		int i = 0, j = 0;
		while (i <= width - 1 && j <= width - 1) {
			while (i <= width - 1 && articles[0][i]!=null) {
				++i;
			}
			j = i;
			while (j <= width - 1 && articles[0][j]==null) {
				++j;
			}
			if (i <= width - 1 && j <= width - 1) {
				for (int row = 0; row <= height - 1 && getArticle(row, j) != null; row++) {
					moveArticle(row, j, row, i);
				}
			}
		}
		logger.info("Matrix has realigned.");
	}

	/**
	 * Notifies all views to update. Note:The view update work is about swing
	 * components, These work should execute in EDT.
	 * 
	 * @param e the e
	 */
	public void notifyViews(ModelUpdateCaseEnum e) {
		setChanged();
		notifyObservers(e);
		logger.info("Matrix has updated,notifiy its views.");
	}
	
	/**
	 * Checks whether the given position is valid.
	 * 
	 * @param row the row
	 * @param col the col
	 * 
	 * @return true, if is valid index
	 */
	public boolean isValidPosition(int row,int col){
		return 0<=row&&row<=height-1&&0<=col&&col<=width-1;		
	}
	
	/**
	 * Move article from source position to destination position.
	 * 
	 * @param srcRow the row of source position
	 * @param srcCol the column of source position
	 * @param destRow the row of destination position
	 * @param destCol the column of destination position
	 */
	public void moveArticle(int srcRow,int srcCol,int destRow,int destCol){
		setArticle(destRow, destCol, getArticle(srcRow, srcCol));
		removeArtilce(srcRow, srcCol);
	}

	/**
	 * Gets the specified article.
	 * 
	 * @param row the specified row
	 * @param col the specified column
	 * 
	 * @return the target article
	 */
	public Article getArticle(int row, int col) {
		return articles[row][col];
	}
	
	/**
	 * Gets the activated article.
	 * 
	 * @return the activated article
	 */
	public  Article getActivatedArticle(){
		return getArticle(activatedRow,activatedCol);
	}
	
	/**
	 * Removes the specified article.
	 * 
	 * @param row the specified row
	 * @param col the specified column
	 */
	public void removeArtilce(int row,int col){
		setArticle(row,col,null);
	}	

	/**
	 * Sets the article to specified position.
	 * 
	 * @param row the specified row
	 * @param col the specified column
	 * @param anArticle the article to set.
	 */
	public void setArticle(int row, int col,Article anArticle) {
		articles[row][col]=anArticle;
	}

	/*------------------------------------   Accessor Methods   ------------------------------------ */
	
	/**
	 * Gets the matrix's height.
	 * 
	 * @return the matrix's height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Sets the matrix's height.
	 * 
	 * @param height the matrix's height to set
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * Gets the matrix's width.
	 * 
	 * @return the matrix's width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Sets the matrix's width.
	 * 
	 * @param width the matrix's width to set
	 */
	public void setWidth(int width) {
		this.width = width;
	}
	
	/**
	 * Checks if is activated.
	 * 
	 * @return true, if is activated
	 */
	public boolean isActivated() {
		return activated;
	}

	/**
	 * Gets the activated row.
	 * 
	 * @return the activated row
	 */
	public int getActivatedRow() {
		return activatedRow;
	}

	/**
	 * Gets the activated col.
	 * 
	 * @return the activated col
	 */
	public int getActivatedCol() {
		return activatedCol;
	}

	/**
	 * Gets the activated count.
	 * 
	 * @return the activated count
	 */
	public int getActivatedCount() {
		return activatedCount;
	}
	
	
}
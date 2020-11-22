package flyingdiamond.model.gamerule;

import flyingdiamond.model.ArticleMatrix;

/**
 * The LevelRule determine whether the level has passed or calculate
 * the matrix initial height when a new level start.
 */
public class LevelRule {
	
	/**
	 * Whether level passed.
	 * 
	 * @param level the current level
	 * @param levelScore the level score
	 * @return result, if passed,return true.
	 */
	public boolean isPassed(int level,int levelScore){
		return levelScore >= 10 + level * 10;		
	}

	
	/**
	 * Gets the matrix initial height when a new level starts.
	 * 
	 * @param level the level
	 * @return the matrix's initial height.
	 */
	public int getMatrixInitHeight(int level){
		int calcValue = 4 + level / 5;
		return calcValue<ArticleMatrix.MAX_MATRIX_INIT_HEIGHT?calcValue:ArticleMatrix.MAX_MATRIX_INIT_HEIGHT;
	}

}

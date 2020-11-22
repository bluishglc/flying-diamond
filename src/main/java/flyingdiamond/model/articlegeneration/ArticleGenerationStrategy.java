package flyingdiamond.model.articlegeneration;

import flyingdiamond.model.Article;

/**
 * The ArticleGenerationStrategy take charge of generating articles according
 * with different rules.
 * 
 * @author Laurence Geng
 * @version 1.0
 * @created 2009-2-1
 */
public interface ArticleGenerationStrategy {

	/** The NormalGenerationStrategy's name. */
	String NORMAL_STRATEGY = "NORMAL_STRATEGY";
	
	/** The BombBonusGenerationStrategy's name. */
	String BOMB_BONUS_STRATEGY = "BOMB_BONUS_STRATEGY";
	
	/** The SuperBombBonusGenerationStrategy's name. */
	String SUPER_BOMB_BONUS_STRATEGY = "SUPER_BOMB_BONUS_STRATEGY";
	
	/**
	 * Generate articles.
	 * 
	 * @param articleRow the given row to set new articles of matrix.
	 */
	void generateArticles(Article[] articleRow);

}
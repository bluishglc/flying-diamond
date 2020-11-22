package flyingdiamond.model.articlegeneration;

/**
 * The BonusGenerationStrategy is an decorator(Decorator Pattern).It base on an
 * existing generation strategy such like a normal generation strategy ,then
 * replace existing articles with special bonus articles.
 * 
 * @author Laurence Geng
 * @version 1.0
 * @created 2009-2-1
 */
public abstract class BonusGenerationStrategy implements ArticleGenerationStrategy {
	
	/** The basic strategy. */
	protected ArticleGenerationStrategy basicStrategy;
	
	/**
	 * Instantiates a new bonus generation strategy.
	 * 
	 * @param basicStrategy a basic strategy
	 */
	BonusGenerationStrategy(ArticleGenerationStrategy basicStrategy){
		this.basicStrategy = basicStrategy;
	}
}

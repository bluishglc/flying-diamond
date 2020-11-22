package flyingdiamond.model.articlegeneration;

import java.util.Random;

import flyingdiamond.model.Article;
import flyingdiamond.model.ArticleEnum;
import flyingdiamond.model.ArticleFactory;

/**
 * The BombBonusGenerationStrategy generates a line of articles which
 * consist of random diamonds and a bomb on a random position.
 * 
 * @author Laurence Geng
 * @version 1.0
 * @created 2009-2-1
 */
public class BombBonusGenerationStrategy extends BonusGenerationStrategy {
	
	/**
	 * Instantiates a new bomb bonus generation strategy.
	 * 
	 * @param basicStrategy the basic strategy
	 */
	BombBonusGenerationStrategy(ArticleGenerationStrategy basicStrategy) {
		super(basicStrategy);
	}


	/**
	 * @see flyingdiamond.model.articlegeneration.ArticleGenerationStrategy#generateArticles(flyingdiamond.model.Article[])
	 */
	public void generateArticles(Article[] articleRow) {
		int bombPosition = new Random().nextInt(articleRow.length);
		basicStrategy.generateArticles(articleRow);
		articleRow[bombPosition]=ArticleFactory.getInstance().createArticle(ArticleEnum.BOMB);
	}
}
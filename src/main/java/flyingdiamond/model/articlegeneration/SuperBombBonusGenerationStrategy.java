package flyingdiamond.model.articlegeneration;

import flyingdiamond.model.Article;
import flyingdiamond.model.ArticleEnum;
import flyingdiamond.model.ArticleFactory;

/**
 * The BombBonusGenerationStrategy generates a line of aritlces which
 * consist of random diamonds and 3 bombs on 3 fixed positions.
 * 
 * @author Laurence Geng
 * @version 1.0
 * @created 2009-2-1 17:08:39
 */
public class SuperBombBonusGenerationStrategy extends BonusGenerationStrategy {
	
	/**
	 * Instantiates a new super bomb bonus generation strategy.
	 * 
	 * @param basicStrategy the basic strategy
	 */
	SuperBombBonusGenerationStrategy(
			ArticleGenerationStrategy basicStrategy) {
		super(basicStrategy);
	}

	/* (non-Javadoc)
	 * @see flyingdiamond.model.articlegeneration.ArticleGenerationStrategy#generateArticles(flyingdiamond.model.Article[])
	 */
	public void generateArticles(Article[] articleRow) {
		int length = articleRow.length;
		int postion1 = length/6;
		int postion2 = length/2;
		int postion3 = length-(length/6+1);
		basicStrategy.generateArticles(articleRow);
		articleRow[postion1]=ArticleFactory.getInstance().createArticle(ArticleEnum.BOMB);
		articleRow[postion2]=ArticleFactory.getInstance().createArticle(ArticleEnum.BOMB);
		articleRow[postion3]=ArticleFactory.getInstance().createArticle(ArticleEnum.BOMB);
	}
}

package flyingdiamond.model.articlegeneration;

import flyingdiamond.model.Article;
import flyingdiamond.model.ArticleFactory;

/**
 * The NormalGenerationStrategy generate a line of articles which
 * consist of random diamonds.
 * 
 * @author Laurence Geng
 * @version 1.0
 * @created 2009-2-1
 */
public class NormalGenerationStrategy implements ArticleGenerationStrategy {

	/**
	 * Instantiates a new normal generation strategy.
	 */
	NormalGenerationStrategy(){}	

	/* (non-Javadoc)
	 * @see flyingdiamond.model.articlegeneration.ArticleGenerationStrategy#generateArticles(flyingdiamond.model.Article[])
	 */
	public void generateArticles(Article[] articleRow) {
		for(int i =0;i<articleRow.length;i++){
			articleRow[i]=ArticleFactory.getInstance().createDiamondRandomly();
		}	
	}
	
}
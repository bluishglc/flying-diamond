/**
 * 
 */
package flyingdiamond.model;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import flyingdiamond.model.articlegeneration.ArticleGenerationStrategy;
import flyingdiamond.model.articlegeneration.ArticleGenerationStrategyFactory;
import org.junit.AfterClass;
import org.junit.AfterClass;
import org.junit.AfterClass;

/**
 * @author laurence.geng
 *
 */
public class ArticleMatrixTest {

	private ArticleMatrix matrix;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		matrix = new ArticleMatrix();
	}

	/**
	 * Test method for {@link flyingdiamond.model.ArticleMatrix#activateArticle(int, int)}.
	 */
	@Test
	public void testActivateArticle() {

	}

	/**
	 * Test method for {@link flyingdiamond.model.ArticleMatrix#unactivateArticles()}.
	 */
	@Test
	public void testUnactivateArticles() {

	}

	/**
	 * Test method for {@link flyingdiamond.model.ArticleMatrix#removeActivatedArticles()}.
	 */
	@Test
	public void testRemoveActivatedArticles() {

	}

	/**
	 * Test method for {@link flyingdiamond.model.ArticleMatrix#clear()}.
	 */
	@Test
	public void testClear() {

	}

	/**
	 * Test method for {@link flyingdiamond.model.ArticleMatrix#reload(int, long)}.
	 */
	@Test
	public void testReload() {

	}

	/**
	 * Test method for {@link flyingdiamond.model.ArticleMatrix#isFull()}.
	 */
	@Test
	public void testIsFull() {

	}

	/**
	 * Test method for {@link flyingdiamond.model.ArticleMatrix#grow(flyingdiamond.model.articlegeneration.ArticleGenerationStrategy)}.
	 */
	@Test
	public void testGrow() {
		ArticleMatrix matrix = new ArticleMatrix();
		matrix.grow(ArticleGenerationStrategyFactory.getInstance().createStrategy(ArticleGenerationStrategy.NORMAL_STRATEGY));
		Article[] row1 = new Article[matrix.getWidth()];
		for (int i = 0; i < row1.length; i++) {
			row1[i]=matrix.getArticle(0, i);
		}
		//Grows again,and previous row 1 changes to row 2;
		matrix.grow(ArticleGenerationStrategyFactory.getInstance().createStrategy(ArticleGenerationStrategy.NORMAL_STRATEGY));
		for (int i = 0; i < row1.length; i++) {	
			assertEquals(row1[i],matrix.getArticle(1, i));
		}
	}

	/**
	 * Test method for {@link flyingdiamond.model.ArticleMatrix#realign()}.
	 */
	@Test
	public void testRealign() {
	}

	/**
	 * Test method for {@link flyingdiamond.model.ArticleMatrix#moveArticle(int, int, int, int)}.
	 */
	@Test
	public void testMoveArticle() {

	}

}

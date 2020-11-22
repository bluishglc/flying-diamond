/**
 * 
 */
package flyingdiamond.model.chainreaction;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import flyingdiamond.model.ArticleEnum;
import flyingdiamond.model.ArticleFactory;
import flyingdiamond.model.ArticleMatrix;
import flyingdiamond.model.Bomb;

/**
 * @author laurence.geng
 *
 */
public class BlastChainReactionTest {

	private BlastChainReaction blastChainReaction;
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		blastChainReaction = new BlastChainReaction(Bomb.ACTION_RADIUS);
	}

	/**
	 * Test method for {@link flyingdiamond.model.chainreaction.BlastChainReaction#react(int, int, flyingdiamond.model.ArticleMatrix)}.
	 */
	@Test
	public void testReact() {
		//Generates a matrix which is full of diamonds and a bomb in the center.
		ArticleMatrix matrix = generateTestMatrix(ArticleMatrix.HEIGHT/2,ArticleMatrix.WIDTH/2);
		//Actives bomb
		int blastCount = blastChainReaction.react(ArticleMatrix.HEIGHT/2,ArticleMatrix.WIDTH/2, matrix);
		//Counts activated articles.
		int activatedCount = 0;
		for(int i=0;i<ArticleMatrix.HEIGHT;i++){
			for(int j=0;j<ArticleMatrix.WIDTH;j++){
				if(matrix.getArticle(i, j).isActivated()){
					activatedCount++;
				}
			}
		}
		assertEquals(activatedCount,25);
		assertEquals(blastCount,25);
	}
	
	private ArticleMatrix generateTestMatrix(int row,int col){
		ArticleMatrix matrix = new ArticleMatrix();
		//Fill matrix full with articles.
		//Fills non-target diamonds.
		for(int i=0;i<ArticleMatrix.HEIGHT;i++){
			for(int j=0;j<ArticleMatrix.WIDTH;j++){
				matrix.setArticle(i, j, ArticleFactory.getInstance().createDiamondRandomly());
			}
		}
		//Sets a bomb at given position.
		matrix.setArticle(row, col, ArticleFactory.getInstance().createArticle(ArticleEnum.BOMB));
		return matrix;
	}

}

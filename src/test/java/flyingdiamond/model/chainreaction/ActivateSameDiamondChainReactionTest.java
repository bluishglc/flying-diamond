/**
 * 
 */
package flyingdiamond.model.chainreaction;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import flyingdiamond.model.Article;
import flyingdiamond.model.ArticleEnum;
import flyingdiamond.model.ArticleFactory;
import flyingdiamond.model.ArticleMatrix;

/**
 * @author laurence.geng
 *
 */
public class ActivateSameDiamondChainReactionTest {
	
	private ActivateSameDiamondChainReaction reaction;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		 reaction = new ActivateSameDiamondChainReaction();
	}
	
	/**
	 * Test method for {@link flyingdiamond.model.chainreaction.ActivateSameDiamondChainReaction#react(int, int, flyingdiamond.model.ArticleMatrix)}.
	 */
	@Test
	public void testReact() {
		Article blueDia = ArticleFactory.getInstance().createArticle(ArticleEnum.BLUE_DIAMOND);
		Article greenDia = ArticleFactory.getInstance().createArticle(ArticleEnum.GREEN_DIAMOND);
		//Set pattern to different positions,check whether the react method work well.
		for (int i = 0; i < ArticleMatrix.HEIGHT; i++) {
			for (int j = 0; j < ArticleMatrix.WIDTH; j++) {
				ArticleMatrix matrix = generateTestMatrix(i,j);
				int nullCells = reaction.react(i, j, matrix);
				int redDiamonds = 0;
				for (int row = 0; row < ArticleMatrix.HEIGHT; row++) {
					for (int col = 0; col < ArticleMatrix.WIDTH; col++) {
						Article art = matrix.getArticle(row, col);
						if (art.equals(greenDia) && !art.isActivated()) {
							redDiamonds++;
						}
					}
				}				
				assertEquals(nullCells+redDiamonds,ArticleMatrix.HEIGHT*ArticleMatrix.WIDTH );
			}
		}
	}

	/**
	 * Generates a matrix with a pattern as below:
	 * 
	 * - - - - o - - - -
	 * - - - o o o - - -
	 * - - - - o - - - -
	 * - o - - o - - o -
	 * o o o o o o o o o
	 * - o - - o - - o -
	 * - - - - o - - - -
	 * - - - o o o - - -
	 * - - - - o - - - -
	 * 
	 * This pattern can test the recursive method:MarkSameDiamonds effectively.
	 * The circle represents same type of article.This method sets this pattern
	 * with each cell of matrix as the center cell(trim those out of boundary articles),
	 * and fill rest cells with the other type articles.After activateArticle method
	 * invoked,asserts all circle are activated and all the other cells are not.
	 */
	private ArticleMatrix generateTestMatrix(int row,int col){
		ArticleMatrix matrix = new ArticleMatrix();
		int height = matrix.getHeight();
		int width = matrix.getWidth();
		//Fills target diamonds.
		fillACross(row, col, matrix) ;
		fillACross(row-3, col, matrix) ;
		fillACross(row+3, col, matrix) ;
		fillACross(row, col-3, matrix) ;
		fillACross(row, col+3, matrix) ;
		
		//Fills non-target diamonds.
		for(int i=0;i<height;i++){
			for(int j=0;j<width;j++){
				if(matrix.getArticle(i, j)==null){
					matrix.setArticle(i, j, ArticleFactory.getInstance().createArticle(ArticleEnum.GREEN_DIAMOND));
				}
			}
		}
		return matrix;
	}
	
	/**
	 * Fills a cross as bellow into a matrix:
	 *  - o -
	 *  o o o
	 *  - o -
	 * 
	 * @param row The cross center's row.
	 * @param col The cross center's column.
	 * @param targetArticleName The article name which will set as active target.
	 * @param matrix The text matrix.
	 */
	private void fillACross(int row, int col, ArticleMatrix matrix) {
		if (matrix.isValidPosition(row, col)) {
			matrix.setArticle(row, col, ArticleFactory.getInstance().createArticle(ArticleEnum.BLUE_DIAMOND));
		}
		if (matrix.isValidPosition(row + 1, col)) {
			matrix.setArticle(row + 1, col, ArticleFactory.getInstance().createArticle(ArticleEnum.BLUE_DIAMOND));
		}
		if (matrix.isValidPosition(row - 1, col)) {
			matrix.setArticle(row - 1, col, ArticleFactory.getInstance().createArticle(ArticleEnum.BLUE_DIAMOND));
		}
		if (matrix.isValidPosition(row, col + 1)) {
			matrix.setArticle(row, col + 1, ArticleFactory.getInstance().createArticle(ArticleEnum.BLUE_DIAMOND));
		}
		if (matrix.isValidPosition(row, col - 1)) {
			matrix.setArticle(row, col - 1, ArticleFactory.getInstance().createArticle(ArticleEnum.BLUE_DIAMOND));
		}
	}

}

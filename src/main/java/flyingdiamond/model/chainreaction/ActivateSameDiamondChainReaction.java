package flyingdiamond.model.chainreaction;

import org.apache.log4j.Logger;

import flyingdiamond.model.Article;
import flyingdiamond.model.ArticleMatrix;

/**
 * The ActivateSameDiamondChainReaction activates all same diamonds around the activated diamond.
 * 
 * @author Laurence Geng
 * @version 1.0
 * @created 2009-2-1
 */
public class ActivateSameDiamondChainReaction implements ActivationChainReaction {
	
	private static Logger logger = Logger.getLogger(ActivateSameDiamondChainReaction.class.getName());
	
	/**
	 * @see flyingdiamond.model.activation.ActivationChainReaction#releaseEnergy(int, int, flyingdiamond.model.ArticleMatrix)
	 */
	public int react(int row, int col, ArticleMatrix matrix) {
		//Makes sure there are no less than one same type diamond
		//around the target diamond,if not,do nothing.
		if(canActivate(row, col, matrix)){
			//Traverses all same type diamonds
			return traverse(row, col, matrix);
		}else{
			return 0;
		}
	}

	/* (non-Javadoc)
	 * @see flyingdiamond.model.chainreaction.ActivationChainReaction#canActivate(int, int, flyingdiamond.model.ArticleMatrix)
	 */
	public boolean canActivate(int row, int col, ArticleMatrix matrix) {
		Article activatedArtilce = matrix.getArticle(row, col);
		if(matrix.isValidPosition(row+1, col)&&activatedArtilce.equals(matrix.getArticle(row+1,col))){//top position hit
			return true;
		}else if(matrix.isValidPosition(row, col+1)&&activatedArtilce.equals(matrix.getArticle(row,col+1))){//right position hit
			return true;
		}else if(matrix.isValidPosition(row-1, col)&&activatedArtilce.equals(matrix.getArticle(row-1,col))){//bottom position hit
			return true;
		}else if(matrix.isValidPosition(row, col-1)&&activatedArtilce.equals(matrix.getArticle(row,col-1))){//left position hit
			return true;
		}else{
			return false;
		}				
	}
	
	/**
	 * Traverses matrix recursively,and make all eligible articles activated.
	 * 
	 * @param row the given row
	 * @param col the given column
	 * @param matrix the matrix
	 */
	private int traverse(int row, int col, ArticleMatrix matrix){
		int count = 1;
		//Sets activated first,then traverse up,down,left,right position recursively.
		matrix.getArticle(row, col).setActivated(true);
		logger.info("The artilce ("+row+","+col+") is activated due to activing same dimaond chain reaction.");
		if(matrix.isValidPosition(row+1, col)//detect up position
				&&matrix.getArticle(row+1, col) != null
				&&matrix.getArticle(row+1, col).equals(matrix.getArticle(row, col))
				&&!matrix.getArticle(row+1, col).isActivated()){
			count+=traverse(row+1, col,matrix);
		}
		if(matrix.isValidPosition(row, col+1)//detect right position
				&&matrix.getArticle(row, col+1) != null
				&&matrix.getArticle(row, col+1).equals(matrix.getArticle(row, col))
				&&!matrix.getArticle(row, col+1).isActivated()){
			count+=traverse(row, col+1,matrix);
		}
		if(matrix.isValidPosition(row-1, col)//detect down position
				&&matrix.getArticle(row-1, col) != null
				&&matrix.getArticle(row-1, col).equals(matrix.getArticle(row, col))
				&&!matrix.getArticle(row-1, col).isActivated()){
			count+=traverse(row-1, col,matrix);
		}
		if(matrix.isValidPosition(row, col-1)//detect left position
				&&matrix.getArticle(row, col-1) != null
				&&matrix.getArticle(row, col-1).equals(matrix.getArticle(row, col))
				&&!matrix.getArticle(row, col-1).isActivated()){
			count+=traverse(row, col-1,matrix);
		}
		return count;
	}	
}
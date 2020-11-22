/*
 * 
 */
package flyingdiamond.model.chainreaction;

import org.apache.log4j.Logger;

import flyingdiamond.model.ArticleMatrix;

/**
 * The BlastChainReaction activates .
 * 
 * @author Laurence Geng
 * @version 1.0
 * @created 2009-2-1
 */
public class BlastChainReaction implements ActivationChainReaction {
	
	private static Logger logger = Logger.getLogger(BlastChainReaction.class.getName());
	
	/** The action radius. */
	private int actionRadius;
		
	/**
	 * Instantiates a new blast chain reaction.
	 * 
	 * @param actionRadius the action radius
	 */
	public BlastChainReaction(int actionRadius){
		this.actionRadius = actionRadius;
	}
	
	/*------------------------------------   Business Methods   ------------------------------------ */

	/**
	 * @see flyingdiamond.model.chainreaction.ActivationChainReaction#canActivate(int, int, flyingdiamond.model.ArticleMatrix)
	 */
	public boolean canActivate(int row, int col, ArticleMatrix matrix) {
		return true;
	}
	
	/**
	 * @see flyingdiamond.model.activation.ActivationChainReaction#releaseEnergy(int, int, flyingdiamond.model.ArticleMatrix)
	 */
	public int react(int row, int col, ArticleMatrix matrix) {
		int count = 0;
		for(int x=-actionRadius;x<=actionRadius;x++){
			for(int y=-Math.abs(actionRadius-Math.abs(x));y<=Math.abs(actionRadius-Math.abs(x));y++){
				if(matrix.isValidPosition(row+x, col+y)
						&& matrix.getArticle(row+x, col+y) != null
						&& !matrix.getArticle(row+x, col+y).isActivated()){
					matrix.getArticle(row+x, col+y).setActivated(true);
					count++;
					logger.info("The artilce ("+row+","+col+") is activated due to blast chain reaction.");
					if(matrix.getArticle(row+x, col+y).equals(matrix.getArticle(row, col))){
						count+=react(row+x, col+y,matrix);
					}
				}
			}
		}
		return count;
	}

	/*------------------------------------   Accessor Methods   ------------------------------------ */

	/**
	 * Gets the action radius.
	 * 
	 * @return the action radius
	 */
	public int getActionRadius() {
		return actionRadius;
	}
}
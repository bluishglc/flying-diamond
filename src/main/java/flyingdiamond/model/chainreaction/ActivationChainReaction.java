package flyingdiamond.model.chainreaction;

import flyingdiamond.model.ArticleMatrix;

/**
 * The ActivationChainReaction is a matrix computing strategy.It concerns with particular
 * article type.If an article is activated,it will cause a chain reaction that some other
 * articles around it will be effected too.we call this activation chain reaction.For diamond,
 * there is ActivateSameDiamondChainReaction with it which will activate all same diamonds around
 * the activated one.For bomb,there is BlastChainReaction with it which will blast all articles 
 * within the bomb action radius.
 * 
 * @author Laurence Geng
 * @version 1.0
 * @created 2009-2-1 17:08:39
 */
public interface ActivationChainReaction {
	
	/** The ActivateSameDiamondChainReaction's name. */
	String ACTIVATE_SAME_DIAMOND_CHAIN_REACTION = "ACTIVATE_SAME_DIAMOND_CHAIN_REACTION";
	
	/** The BlastChainReaction's name. */
	String BLAST_CHAIN_REACTION ="BLAST_CHAIN_REACTION";
	
	/**
	 * Checks whether the given article can activate.For diamond,If there is no
	 * one same diamond at its up,down,left or right position,this article can not
	 * activate.For bomb,it can always activate.
	 * 
	 * @param row the given artilce's row
	 * @param col the given artilce's column
	 * @param matrix the given matrix
	 * @return true, if can active.
	 */
	public boolean canActivate(int row,int col,ArticleMatrix matrix);

	/**
	 * React the chain reaction.For ActivateSameDiamondChainReaction, if the
	 * given article is diamond, all same diamonds around the given one will
	 * activate too.For BlastChainReaction,if the given article is bomb, all
	 * articles within the bomb action radius will activate.
	 * 
	 * @param row the given artilce's row
	 * @param col the given artilce's column
	 * @param matrix the given matrix
	 * 
	 * @return the count of reacted article
	 */
	public int react(int row,int col,ArticleMatrix matrix);

}
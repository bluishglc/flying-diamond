package flyingdiamond.model;

import java.applet.AudioClip;

import javax.swing.ImageIcon;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import flyingdiamond.model.chainreaction.ActivationChainReaction;
import flyingdiamond.model.chainreaction.BlastChainReaction;

/**
 * The Bomb is a type of article which concern with a BlastChainReaction.If a bomb
 * is activated,the BlastChainReaction will be applied and all articles in blast action
 * area will be removed.
 * 
 * @author Laurence Geng
 * @version 1.0
 * @created 2009-2-1
 */
public class Bomb extends Article {

	/** The Constant ACTION_RADIUS. */
	public static final int ACTION_RADIUS = 3;
	
	/** The action radius. */
	private int actionRadius = ACTION_RADIUS;
	
	/**
	 * Instantiates a new bomb. The bomb instance should be created by
	 * ArticleFactory,so the construtor's visibility is package.
	 * 
	 * @param imageIcon the image icon
	 * @param activatedIcon the activated icon
	 * @param audioClip the audio clip
	 */
	Bomb(ImageIcon imageIcon, ImageIcon activatedIcon, AudioClip audioClip) {
		super(imageIcon, activatedIcon,audioClip);
	}

	/*------------------------------------   Business Methods   ------------------------------------ */
	
	/* (non-Javadoc)
	 * @see flyingdiamond.model.Article#getActivationChainReaction()
	 */
	@Override
	public ActivationChainReaction getActivationChainReaction() {
		return new BlastChainReaction(actionRadius);
	}

	/*------------------------------------    Common Methods   --------------------------------------*/
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof Bomb)) {
			return false;
		}
		Bomb rhs = (Bomb) object;
		return new EqualsBuilder().append(
				this.imageIcon, rhs.imageIcon).append(this.audioClip, rhs.audioClip).isEquals();
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(-2020969363, 1415828599)
				.append(this.imageIcon).append(this.audioClip).toHashCode();
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

	/**
	 * Sets the action radius.
	 * 
	 * @param actionRadius the new action radius
	 */
	public void setActionRadius(int actionRadius) {
		this.actionRadius = actionRadius;
	}
	
}
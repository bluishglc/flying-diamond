package flyingdiamond.model;

import java.applet.AudioClip;

import javax.swing.ImageIcon;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import flyingdiamond.model.chainreaction.ActivateSameDiamondChainReaction;
import flyingdiamond.model.chainreaction.ActivationChainReaction;

/**
 * The Diamond is an important type of article. It has a special ability that
 * The same type diamonds around a diamond can be activated too when this diamond activated.
 * 
 * @author Laurence Geng
 * @version 1.0
 * @created 2009-2-1
 */
public class Diamond extends Article implements Scoreable {

	/**
	 * Instantiates a new diamond.The diamond instance should be created by
	 * ArticleFactory,so the construtor's visibility is package.
	 * 
	 * @param imageIcon the image icon
	 * @param activatedIcon the activated icon
	 * @param audioClip the audio clip
	 */
	Diamond(ImageIcon imageIcon, ImageIcon activatedIcon, AudioClip audioClip) {
		super(imageIcon, activatedIcon, audioClip);
	}

	/*------------------------------------   Business Methods   ------------------------------------ */	
	
	/**
	 * @see flyingdiamond.model.Article#getActivationChainReaction()
	 */
	public ActivationChainReaction getActivationChainReaction() {
		return new ActivateSameDiamondChainReaction();
	}
	
	/*------------------------------------    Common Methods   --------------------------------------*/
	
	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof Diamond)) {
			return false;
		}
		Diamond rhs = (Diamond) object;
		return new EqualsBuilder().append(
				this.imageIcon, rhs.imageIcon).append(this.audioClip, rhs.audioClip).isEquals();
	}
	
	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(1088091799, -1355344027)
				.append(this.imageIcon).append(this.audioClip).toHashCode();
	}
	
}
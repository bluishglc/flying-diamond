package flyingdiamond.model;

import java.applet.AudioClip;

import javax.swing.ImageIcon;

import flyingdiamond.model.chainreaction.ActivationChainReaction;

/**
 * The article can be a diamond or a bomb.they are put into or removed from matrix.
 * Each type article concern with a particular activation chain reaction which the matrix
 * will get from the activated article and apply it to matrix compute.
 * 
 * @author Laurence Geng
 * @version 1.0
 * @created 2009-2-1
 */
public abstract class Article{

	/** The image icon for article. */
	protected ImageIcon imageIcon;
	
	/** The activated icon when article activated. */
	protected ImageIcon activatedIcon;
	
	/** The audio clip when article activated. */
	protected AudioClip audioClip;
	
	/** The activate status tag. */
	protected boolean activated = false;

	/**
	 * Instantiates a new article. An article instance should be created by
	 * ArticleFactory,so the construtor's visibility is package.
	 * 
	 * @param imageIcon the image icon
	 * @param activatedIcon the activated icon
	 * @param audioClip the audio clip
	 */
	Article(ImageIcon imageIcon, ImageIcon activatedIcon, AudioClip audioClip) {
		super();
		this.imageIcon = imageIcon;
		this.activatedIcon = activatedIcon;
		this.audioClip = audioClip;
	}

	/*------------------------------------   Business Methods   ------------------------------------ */	

	/**
	 * Gets an ActivationChainReaction which correspond to this type article.
	 * This a typical factory method.Different type articles return different type reaction,
	 * and besides,the product's creation need know some details of factory.For example: when
	 * bomb create a BlastChainReaction,it pass its action radius to the BlastChainReaction.
	 * The factory method's two core intention:
	 * 1.Creating products polymorphically.
	 * 2.The product need factory's information when creating.
	 * 
	 * @return the activation chain reaction
	 */
	public abstract ActivationChainReaction getActivationChainReaction();
	
	
	/*------------------------------------   Accessor Methods   ------------------------------------ */

	/**
	 * Gets the audio clip.
	 * 
	 * @return the audio clip
	 */
	public AudioClip getAudioClip() {
		return audioClip;
	}

	/**
	 * Gets the image icon.
	 * 
	 * @return the imageIcon
	 */
	public ImageIcon getImageIcon() {
		return imageIcon;
	}

	/**
	 * Sets the image icon.
	 * 
	 * @param imageIcon the imageIcon to set
	 */
	public void setImageIcon(ImageIcon imageIcon) {
		this.imageIcon = imageIcon;
	}

	/**
	 * Sets the audio clip.
	 * 
	 * @param audioClip the new audio clip
	 */
	public void setAudioClip(AudioClip audioClip) {
		this.audioClip = audioClip;
	}

	/**
	 * Checks if is activated.
	 * 
	 * @return the activated
	 */
	public boolean isActivated() {
		return activated;
	}

	/**
	 * Sets the activated.
	 * 
	 * @param activated the activated to set
	 */
	public void setActivated(boolean activated) {
		this.activated = activated;
	}

	/**
	 * Gets the activated icon.
	 * 
	 * @return the activatedIcon
	 */
	public ImageIcon getActivatedIcon() {
		return activatedIcon;
	}

	/**
	 * Sets the activated icon.
	 * 
	 * @param activatedIcon the activatedIcon to set
	 */
	public void setActivatedIcon(ImageIcon activatedIcon) {
		this.activatedIcon = activatedIcon;
	}

}
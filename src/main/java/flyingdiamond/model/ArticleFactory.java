package flyingdiamond.model;

import java.util.Random;

import flyingdiamond.common.IconResourcer;
import flyingdiamond.common.ResourceEnum;
import flyingdiamond.common.SoundResourcer;

/**
 * A singleton factory for creating Article objects.
 * 
 * @author Laurence Geng
 * @version 1.0
 * @created 2009-2-1
 */
public class ArticleFactory {
	
	/** The singleton instance. */
	private static ArticleFactory instance=new ArticleFactory(); 
	
	/**
	 * Private constructor for forbidding creating factory instance arbitrarily.
	 */
	private ArticleFactory (){}

	/**
	 * Gets the single instance of ArticleFactory.
	 * 
	 * @return single instance of ArticleFactory
	 */
	public static ArticleFactory getInstance(){
		return instance;
	}
	

	/**
	 * Creates a new Article object.
	 * 
	 * @param articleEnum the article enum
	 * 
	 * @return the article
	 */
	public Article createArticle(ArticleEnum articleEnum){
		if(articleEnum==ArticleEnum.BLUE_DIAMOND){
			return new Diamond(IconResourcer.getInstance().getIcon(ResourceEnum.ICON_BLUE_DIAMOND), 
							   IconResourcer.getInstance().getIcon(ResourceEnum.ICON_ACTIVATED_DIAMOND),
							   SoundResourcer.getInstance().getAuidoClip(ResourceEnum.SOUND_ACTIVE1));
		}
		if(articleEnum==ArticleEnum.GREEN_DIAMOND){
			return new Diamond(IconResourcer.getInstance().getIcon(ResourceEnum.ICON_GREEN_DIAMOND), 
							   IconResourcer.getInstance().getIcon(ResourceEnum.ICON_ACTIVATED_DIAMOND), 
							   SoundResourcer.getInstance().getAuidoClip(ResourceEnum.SOUND_ACTIVE2));
		}
		if(articleEnum==ArticleEnum.RED_DIAMOND){
			return new Diamond(IconResourcer.getInstance().getIcon(ResourceEnum.ICON_RED_DIAMOND), 
							   IconResourcer.getInstance().getIcon(ResourceEnum.ICON_ACTIVATED_DIAMOND), 
							   SoundResourcer.getInstance().getAuidoClip(ResourceEnum.SOUND_ACTIVE3));
		}
		if(articleEnum==ArticleEnum.BOMB){
			return new Bomb(IconResourcer.getInstance().getIcon(ResourceEnum.ICON_BOMB),
					        IconResourcer.getInstance().getIcon(ResourceEnum.ICON_BOMB),
					        SoundResourcer.getInstance().getAuidoClip(ResourceEnum.SOUND_BLAST));
		}
		return null;
		
	}
	
	/**
	 * Creates a blue or green or red diamond instance randomly.
	 * 
	 * @return the new diamond instance.
	 */
	public Article createDiamondRandomly() {
		switch (new Random().nextInt(3)) {
		case 0:
			return createArticle(ArticleEnum.BLUE_DIAMOND);
		case 1:
			return createArticle(ArticleEnum.GREEN_DIAMOND);
		default:
			return createArticle(ArticleEnum.RED_DIAMOND);
		}
	}

}

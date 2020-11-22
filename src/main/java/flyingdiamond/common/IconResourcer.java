package flyingdiamond.common;

import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;

/**
 * A global resource locator for all icons.
 * @author Laurence Geng
 */
public class IconResourcer {

	private static IconResourcer instance = new IconResourcer();
	
	private static Map<ResourceEnum,ImageIcon> iconMap = new HashMap<ResourceEnum,ImageIcon>();
	
	static{
		iconMap.put(ResourceEnum.ICON_GREEN_DIAMOND, new ImageIcon(IconResourcer.class.getResource("/images/greenDiamond.png")));
		iconMap.put(ResourceEnum.ICON_BLUE_DIAMOND, new ImageIcon(IconResourcer.class.getResource("/images/blueDiamond.png")));
		iconMap.put(ResourceEnum.ICON_RED_DIAMOND, new ImageIcon(IconResourcer.class.getResource("/images/redDiamond.png")));
		iconMap.put(ResourceEnum.ICON_ACTIVATED_DIAMOND, new ImageIcon(IconResourcer.class.getResource("/images/activatedDiamond.png")));
		iconMap.put(ResourceEnum.ICON_BOMB, new ImageIcon(IconResourcer.class.getResource("/images/bomb.png")));
		iconMap.put(ResourceEnum.ICON_NEW_GAME_SMALL, new ImageIcon(IconResourcer.class.getResource("/images/newGame_small.png")));
		iconMap.put(ResourceEnum.ICON_NEW_GAME_LARGE, new ImageIcon(IconResourcer.class.getResource("/images/newGame_large.png")));
		iconMap.put(ResourceEnum.ICON_PRE_STEP_SMALL, new ImageIcon(IconResourcer.class.getResource("/images/preStep_small.png")));
		iconMap.put(ResourceEnum.ICON_PRE_STEP_LARGE, new ImageIcon(IconResourcer.class.getResource("/images/preStep_large.png")));
		iconMap.put(ResourceEnum.ICON_NEXT_STEP_SMALL, new ImageIcon(IconResourcer.class.getResource("/images/nextStep_small.png")));
		iconMap.put(ResourceEnum.ICON_NEXT_STEP_LARGE, new ImageIcon(IconResourcer.class.getResource("/images/nextStep_large.png")));
		iconMap.put(ResourceEnum.ICON_SPEAKER_SMALL, new ImageIcon(IconResourcer.class.getResource("/images/speaker_small.png")));
		iconMap.put(ResourceEnum.ICON_SPEAKER_LARGE, new ImageIcon(IconResourcer.class.getResource("/images/speaker_large.png")));
		iconMap.put(ResourceEnum.ICON_EXIT_SMALL, new ImageIcon(IconResourcer.class.getResource("/images/exit_small.png")));
		iconMap.put(ResourceEnum.ICON_EXIT_LARGE, new ImageIcon(IconResourcer.class.getResource("/images/exit_large.png")));
		iconMap.put(ResourceEnum.ICON_SCORE, new ImageIcon(IconResourcer.class.getResource("/images/score.png")));
		iconMap.put(ResourceEnum.ICON_LIFE, new ImageIcon(IconResourcer.class.getResource("/images/life.png")));
		iconMap.put(ResourceEnum.ICON_MESSAGE, new ImageIcon(IconResourcer.class.getResource("/images/message.png")));
		iconMap.put(ResourceEnum.ICON_BACKGROUND, new ImageIcon(IconResourcer.class.getResource("/images/background.jpg")));
		iconMap.put(ResourceEnum.ICON_0, new ImageIcon(IconResourcer.class.getResource("/images/0.png")));
		iconMap.put(ResourceEnum.ICON_1, new ImageIcon(IconResourcer.class.getResource("/images/1.png")));
		iconMap.put(ResourceEnum.ICON_2, new ImageIcon(IconResourcer.class.getResource("/images/2.png")));
		iconMap.put(ResourceEnum.ICON_3, new ImageIcon(IconResourcer.class.getResource("/images/3.png")));
		iconMap.put(ResourceEnum.ICON_4, new ImageIcon(IconResourcer.class.getResource("/images/4.png")));
		iconMap.put(ResourceEnum.ICON_5, new ImageIcon(IconResourcer.class.getResource("/images/5.png")));
		iconMap.put(ResourceEnum.ICON_6, new ImageIcon(IconResourcer.class.getResource("/images/6.png")));
		iconMap.put(ResourceEnum.ICON_7, new ImageIcon(IconResourcer.class.getResource("/images/7.png")));
		iconMap.put(ResourceEnum.ICON_8, new ImageIcon(IconResourcer.class.getResource("/images/8.png")));
		iconMap.put(ResourceEnum.ICON_9, new ImageIcon(IconResourcer.class.getResource("/images/9.png")));
	}

	/**
	 * Returns the single instance of this class.
	 * <p>
	 * 
	 * @return the single instance of this class.
	 */
	public static IconResourcer getInstance() {
		return instance;
	}

	public ImageIcon getIcon(ResourceEnum name){
		return iconMap.get(name);
	}

}
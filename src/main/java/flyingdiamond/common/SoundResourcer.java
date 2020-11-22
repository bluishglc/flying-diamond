package flyingdiamond.common;

import java.applet.Applet;
import java.applet.AudioClip;
import java.util.HashMap;
import java.util.Map;

/**
 * A global resource locator for all sound files.
 * @author Laurence Geng
 */
public class SoundResourcer {
	
	private static SoundResourcer instance = new SoundResourcer();
	
	private static Map<ResourceEnum, AudioClip> audioClipMap = new HashMap<ResourceEnum, AudioClip>();
	
	private SoundResourcer(){}
	
	static{
		audioClipMap.put(ResourceEnum.SOUND_ACTIVE1, Applet.newAudioClip(SoundResourcer.class.getResource("/sounds/active1.au")));
		audioClipMap.put(ResourceEnum.SOUND_ACTIVE2, Applet.newAudioClip(SoundResourcer.class.getResource("/sounds/active2.au")));
		audioClipMap.put(ResourceEnum.SOUND_ACTIVE3, Applet.newAudioClip(SoundResourcer.class.getResource("/sounds/active3.au")));
		audioClipMap.put(ResourceEnum.SOUND_AGAIN, Applet.newAudioClip(SoundResourcer.class.getResource("/sounds/again.au")));
		audioClipMap.put(ResourceEnum.SOUND_BLAST, Applet.newAudioClip(SoundResourcer.class.getResource("/sounds/blast.au")));
		audioClipMap.put(ResourceEnum.SOUND_BOMB_BONUS, Applet.newAudioClip(SoundResourcer.class.getResource("/sounds/bombbonus.au")));
		audioClipMap.put(ResourceEnum.SOUND_CAREFUL, Applet.newAudioClip(SoundResourcer.class.getResource("/sounds/careful.au")));
		audioClipMap.put(ResourceEnum.SOUND_GAME_OVER, Applet.newAudioClip(SoundResourcer.class.getResource("/sounds/gameover.au")));
		audioClipMap.put(ResourceEnum.SOUND_GOOD, Applet.newAudioClip(SoundResourcer.class.getResource("/sounds/good.au")));
		audioClipMap.put(ResourceEnum.SOUND_GOOD_WORK, Applet.newAudioClip(SoundResourcer.class.getResource("/sounds/goodwork.au")));
		audioClipMap.put(ResourceEnum.SOUND_NO, Applet.newAudioClip(SoundResourcer.class.getResource("/sounds/no.au")));
		audioClipMap.put(ResourceEnum.SOUND_START, Applet.newAudioClip(SoundResourcer.class.getResource("/sounds/start.au")));
		audioClipMap.put(ResourceEnum.SOUND_SUPER_BOMB_BONUS, Applet.newAudioClip(SoundResourcer.class.getResource("/sounds/superbombbonus.au")));

	}
	
	public static SoundResourcer getInstance(){
		return instance;
	}

	public AudioClip getAuidoClip(ResourceEnum resource)
	{
		return audioClipMap.get(resource);
	}
	
	public static void main(String[] args) {
		while(true){
			 Applet.newAudioClip(SoundResourcer.class.getResource("/sounds/active1.au")).play();
			 Applet.newAudioClip(SoundResourcer.class.getResource("/sounds/start.au")).play();
		}
//		Applet.newAudioClip(SoundResourcer.class.getResource("/sounds/active1.au")).play();
	}
}

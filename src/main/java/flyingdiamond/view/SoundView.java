package flyingdiamond.view;

import java.util.Observable;
import java.util.Observer;

import flyingdiamond.common.ResourceEnum;
import flyingdiamond.common.SoundResourcer;
import flyingdiamond.model.ArticleMatrix;
import flyingdiamond.model.ModelUpdateCaseEnum;

/**
 * We recognize sound is presentation work,so,the SoundView is a view about sound play.
 * It observes the models,play sounds according to models' status.
 */
public class SoundView implements Observer{

	public void update(Observable o, Object arg) {
		ModelUpdateCaseEnum updateCase =(ModelUpdateCaseEnum) arg;
		if(updateCase==ModelUpdateCaseEnum.REMOVE_ARTICLE_PREPROCESS){
			((ArticleMatrix) o).getActivatedArticle().getAudioClip().play();
		}else if(updateCase==ModelUpdateCaseEnum.BOMB_BONUS){
			SoundResourcer.getInstance().getAuidoClip(ResourceEnum.SOUND_BOMB_BONUS).play();
		}else if(updateCase==ModelUpdateCaseEnum.SUPER_BOMB_BONUS){
			SoundResourcer.getInstance().getAuidoClip(ResourceEnum.SOUND_SUPER_BOMB_BONUS).play();
		}else if(updateCase==ModelUpdateCaseEnum.NEW_GAME_POSTPROCESS){
			SoundResourcer.getInstance().getAuidoClip(ResourceEnum.SOUND_START).play();
		}
	}

	public void initialize() {
		
	}

}

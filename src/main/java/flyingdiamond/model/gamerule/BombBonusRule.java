package flyingdiamond.model.gamerule;

import flyingdiamond.model.ModelUpdateCaseEnum;
import flyingdiamond.model.articlegeneration.ArticleGenerationStrategy;
import flyingdiamond.model.articlegeneration.ArticleGenerationStrategyFactory;

/**
 * The BombBonusRule determine whether give bomb bonus by the cleared article amount.
 */
public class BombBonusRule {
	
	/**
	 * Selects ArticleGenerationStrategy by one-time cleared article amount.
	 * 
	 * @param amount one-time cleared article amount
	 * 
	 * @return The article generation strategy
	 */
	public ArticleGenerationStrategy filterArticleGenerationStrategy(int amount){
		if(amount>=25){
			return ArticleGenerationStrategyFactory.getInstance().createStrategy(ArticleGenerationStrategy.SUPER_BOMB_BONUS_STRATEGY);
		}else if(amount>=10){
			return ArticleGenerationStrategyFactory.getInstance().createStrategy(ArticleGenerationStrategy.BOMB_BONUS_STRATEGY);
		}else{
			return ArticleGenerationStrategyFactory.getInstance().createStrategy(ArticleGenerationStrategy.NORMAL_STRATEGY);
		}
	}
	
	public ModelUpdateCaseEnum filterBonusUpdateCase(int amount){
		if(amount>=25){
			return ModelUpdateCaseEnum.SUPER_BOMB_BONUS;
		}else if(amount>=10){
			return ModelUpdateCaseEnum.BOMB_BONUS;
		}else{
			return ModelUpdateCaseEnum.NO_BONUS;
		}
	}
}

package flyingdiamond.model.articlegeneration;

/**
 * A factory for creating ArticleGenerationStrategy objects.
 * 
 * @author Laurence Geng
 * @version 1.0
 * @created 2009-2-1
 */
public class ArticleGenerationStrategyFactory {
	
	/** The singleton instance. */
	private static ArticleGenerationStrategyFactory instance= new ArticleGenerationStrategyFactory();
	
	/**
	 * Private constructor for forbidding creating factory instance arbitrarily.
	 */
	private ArticleGenerationStrategyFactory(){}

	/**
	 * Gets the single instance of ArticleGenerationStrategyFactory.
	 * 
	 * @return single instance of ArticleGenerationStrategyFactory
	 */
	public static ArticleGenerationStrategyFactory getInstance(){
		return instance;
	}
	
	/**
	 * Creates a new ArticleGenerationStrategy object.
	 * 
	 * @param strategyName the strategy's name to generate
	 * @return the generated strategy
	 */
	public ArticleGenerationStrategy createStrategy(String strategyName){
		if(ArticleGenerationStrategy.NORMAL_STRATEGY.equals(strategyName)){
			return new NormalGenerationStrategy();
		}else if(ArticleGenerationStrategy.BOMB_BONUS_STRATEGY.equals(strategyName)){
			return  new BombBonusGenerationStrategy(new NormalGenerationStrategy());
		}else if(ArticleGenerationStrategy.SUPER_BOMB_BONUS_STRATEGY.equals(strategyName)){
			return new SuperBombBonusGenerationStrategy(new NormalGenerationStrategy());
		}else{
			return null;
		}
	}
}

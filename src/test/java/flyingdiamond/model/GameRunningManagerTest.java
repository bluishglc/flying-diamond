/**
 * 
 */
package flyingdiamond.model;

import static org.junit.Assert.assertEquals;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Before;
import org.junit.Test;

import flyingdiamond.model.articlegeneration.ArticleGenerationStrategy;


public class GameRunningManagerTest {
		
	private Mockery context;
	private ArticleMatrix mockMatrix;
	private GameRunningManager gameRunningManager;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		context = new JUnit4Mockery(){{
	        setImposteriser(ClassImposteriser.INSTANCE);
	    }};
	    mockMatrix = context.mock(ArticleMatrix.class);
		gameRunningManager=new GameRunningManager(mockMatrix);
	}

	/**
	 * Test method for {@link flyingdiamond.model.GameRunningManager#activateArticle(int, int)}.
	 */
	@Test
	public void testActivateArticle() {
//		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link flyingdiamond.model.GameRunningManager#removeActivatedArticles(int, int)}.
	 */
	@Test
	public void testRemoveActivatedArticles() {
//		 context.checking(new Expectations(){{
//			 oneOf(mockMatrix).isActivated();
//			 will(returnValue(true));
//			 oneOf(mockMatrix).isFull();
//			 will(returnValue(false));
//			 oneOf(mockMatrix).getArticle(0, 0);
//			 will(returnValue(ArticleFactory.getInstance().createDiamondRandomly()));
//			 oneOf(mockMatrix).removeActivatedArticles();
//			 will(returnValue(9));
//			 oneOf(mockMatrix).realign();
//			 oneOf(mockMatrix).notifyViews(with(any(ModelUpdateCaseEnum.class)));
//			 oneOf(mockMatrix).grow(with(any(ArticleGenerationStrategy.class)));
//			 oneOf(mockMatrix).notifyViews(with(any(ModelUpdateCaseEnum.class)));
//		 }});
//		 gameRunningManager.removeActivatedArticles();
//		 assertEquals(gameRunningManager.getLevelScore(), 9);
//		 assertEquals(gameRunningManager.getTotalScore(), 9);
	}

	/**
	 * Test method for {@link flyingdiamond.model.GameRunningManager#newGame()}.
	 */
	@Test
	public void testNewGame() {
//		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link flyingdiamond.model.GameRunningManager#passedLevel()}.
	 */
	@Test
	public void testPassedLevel() {
//		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link flyingdiamond.model.GameRunningManager#nextLevel()}.
	 */
	@Test
	public void testNextLevel() {
//		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link flyingdiamond.model.GameRunningManager#retry()}.
	 */
	@Test
	public void testRetry() {
//		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link flyingdiamond.model.GameRunningManager#endGame()}.
	 */
	@Test
	public void testEndGame() {
//		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link flyingdiamond.model.GameRunningManager#calcInitialScale()}.
	 */
	@Test
	public void testCalcInitialScale() {
//		fail("Not yet implemented");
	}

}

package flyingdiamond.controller;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * The main controller is application entry.When the application startup,the main controller take charge of
 * reading resources,initialize spring ioc container.
 * 
 * @author Laurence Geng
 * @version 1.0
 * @created 2009-2-1
 */
public class MainController {
	
	private static Logger logger = Logger.getLogger(MainController.class.getName());
	
	/** The game running controller. */
	private GameRunningController gameRunningController;
	
	/*------------------------------------   Business Methods   ------------------------------------ */
	
	/**
	 * Startup.
	 */
	public void startup(){
		gameRunningController.initialise();
	}
	
	/**
	 * The main method.
	 * 
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		DOMConfigurator.configure(MainController.class.getResource("/config/log4j.xml"));
		logger.info("start to load spring application context...");
		ApplicationContext context = new ClassPathXmlApplicationContext(
     			new String[] {"classpath:/config/ApplicationContext-Model.xml", "classpath:/config/ApplicationContext-Controller.xml","classpath:/config/ApplicationContext-View.xml"});
		MainController mainController = (MainController) context.getBean("mainController");
		logger.info("srping application context loading finished.");
		mainController.startup();
	}
	
	/*------------------------------------   Accessor Methods   ------------------------------------ */
	
	/**
	 * Gets the game running controller.
	 * 
	 * @return the game running controller
	 */
	public GameRunningController getGameRunningController() {
		return gameRunningController;
	}
	
	/**
	 * Sets the game running controller.
	 * 
	 * @param gameRunningController the new game running controller
	 */
	public void setGameRunningController(GameRunningController gameRunningController) {
		this.gameRunningController = gameRunningController;
	}
}

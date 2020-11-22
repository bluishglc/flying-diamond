/**
 * 
 */
package flyingdiamond.view;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.InvocationTargetException;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import org.apache.log4j.Logger;

import flyingdiamond.controller.GameRunningController;
import flyingdiamond.model.ArticleMatrix;
import flyingdiamond.model.ModelUpdateCaseEnum;
import flyingdiamond.view.uicomponent.ArticleLabel;

/**
 * The ArticleMatrixView is the view of ArticleMatrix.It takes charge of
 * presenting ArticleMatrix's status.
 * 
 * @author Laurence Geng
 * @version 1.0
 * @created 2009-2-1
 */
public class ArticleMatrixView extends MouseAdapter implements Observer{
	
	private static Logger logger = Logger.getLogger(ArticleMatrixView.class.getName());
	
	/** The article labels. */
	private ArticleLabel[][] articleLabels;
	
	/** The article matrix panel. */
	private JPanel articleMatrixPanel;
	
	/** The game running controller. */
	private GameRunningController gameRunningController;
	
	/** The enabled flag whether the view can response user action. */
	private boolean enabled = true;
	
	/*------------------------------------   Business Methods   ------------------------------------ */
	
	/**
	 * Initialize and setting components.
	 */
	public void initialize(){
		//Initialize article label and set it to matrix articleMatrixPanel.
		articleLabels = new ArticleLabel[ArticleMatrix.HEIGHT][ArticleMatrix.WIDTH];
		articleMatrixPanel.setLayout(new GridLayout(ArticleMatrix.HEIGHT,ArticleMatrix.WIDTH));
		for(int i=ArticleMatrix.HEIGHT-1;i>=0;i--){
			for(int j=0;j<ArticleMatrix.WIDTH;j++){
				final int r = i;
				final int c = j;
				articleLabels[i][j]= new ArticleLabel(i,j);
				articleLabels[i][j].setPreferredSize(new Dimension(35,35));
				articleLabels[i][j].addMouseListener(this);
				articleLabels[i][j].addMouseMotionListener(this);
				articleMatrixPanel.add(articleLabels[i][j]);
			}
		}
	}	
	
	/**
	 * @see java.awt.event.MouseAdapter#mouseClicked(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseClicked(final MouseEvent e) {
		if(!enabled){
			return;
		}
		if (((ArticleLabel) e.getSource()).getIcon() != null) {
			stopShine();
			//For all event-handler methods,they are all in EDT!!!
			//Here,the operation in event-handler methods are back-end model's work,
			//It is not concerned to UI,so,we have to make these job be in system thread!
			//If during model's work,the UI need fresh,such like:view's update method which
			//call by notifyview methods,we then put the UI work in EDT!See update mehtod!
			new Thread() {
				@Override
				public void run() {
					ArticleLabel label = (ArticleLabel) e.getSource();				
					gameRunningController.removeActivatedArticles();
					//If last remove action make level passed,do not activate again!
					gameRunningController.activateArticle(label.getRow(), label.getCol());
				}
			}.start();
			
		}
	}
	
	/**
	 * @see java.awt.event.MouseAdapter#mouseMoved(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseEntered(final MouseEvent e) {
		if(!enabled){
			return;
		}
		if (((ArticleLabel) e.getSource()).getIcon() != null) {
			//For all event-handler methods,they are all in EDT!!!
			//Here,the operation in event-handler methods are back-end model's work,
			//It is not concerned to UI,so,we have to make these job be in system thread!
			//If during model's work,the UI need fresh,such like:view's update method which
			//call by notifyview methods,we then put the UI work in EDT!See update mehtod!
			new Thread() {
				@Override
				public void run() {
					ArticleLabel label = (ArticleLabel) e.getSource();
					gameRunningController.activateArticle(label.getRow(), label.getCol());
				}
			}.start();
		}
	}
	
	/**
	 * @see java.awt.event.MouseAdapter#mouseExited(java.awt.event.MouseEvent)
	 */
	public void mouseExited(MouseEvent e) {
		if(!enabled){
			return;
		}
		if (((ArticleLabel) e.getSource()).getIcon() != null) {
			//For all evnet-handler methods,they are all in EDT!!!
			//Here,the operation in vnet-handler methods are backend model's work,
			//It is not concerned to UI,so,we have to make these job be in system thread!
			//If during model's work,the UI need fresh,such like:view's update method which
			//call by notifyview methods,we then put the UI work in EDT!See update mehtod!
			new Thread() {
				@Override
				public void run() {
					gameRunningController.unactivateArticles();
				}
			}.start();
		}
	}

	/**
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	public void update(final Observable o, final Object arg) {
		try {
			//NOTE:All view update work are UI work,they should be in EDT!!!
			//The update method is called by models,they are in system threads,
			//So,SwingUtilities.invokeAndWait can put the Runnable job in EDT!
			//Be carefull:We should use invokeAndWait not invokelater,because,
			//Ours UI update are ordered!For example:When matrix loading,we have to
			//waiting for one grew and the up updated,then grow a new line and update
			//again.
			SwingUtilities.invokeAndWait(new Runnable() {
				public void run() {
					ArticleMatrix matrix = (ArticleMatrix) o;
					ModelUpdateCaseEnum updateCase =(ModelUpdateCaseEnum) arg;
					if (updateCase == ModelUpdateCaseEnum.ACTIVATED) {
						shine(matrix);
					} else if (updateCase == ModelUpdateCaseEnum.UNACTIVATED) {
						stopShine();
					} else if (updateCase == ModelUpdateCaseEnum.STRUCTURE_CHANGED) {
						refresh(matrix);
					} else if(updateCase == ModelUpdateCaseEnum.RELOAD_PREPROCESS) {
						setEnabled(false);
					} else if(updateCase == ModelUpdateCaseEnum.RELOAD_POSTPROCESS) {
						setEnabled(true);
					}
					logger.info("The view of article matrix updated by matrix's new status");
				}
			});
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Make article labels which article are activated shine.
	 * 
	 * @param matrix the matrix
	 */
	private void shine(ArticleMatrix matrix) {
		// run a single thread,and flush icons for all activated articles.
		for (int i = 0; i < matrix.getHeight(); i++) {
			for (int j = 0; j < matrix.getWidth(); j++) {
				if (matrix.getArticle(i, j) != null	&& matrix.getArticle(i, j).isActivated()) {
					articleLabels[i][j].shine(matrix.getArticle(i, j).getImageIcon(), matrix.getArticle(i, j).getActivatedIcon());
				}
			}
		}
	}
	
	/**
	 * Stop article labels shining.
	 */
	private void stopShine() {
		for (int i = 0; i < ArticleMatrix.HEIGHT; i++) {
			for (int j = 0; j < ArticleMatrix.WIDTH; j++) {
				if (articleLabels[i][j].getIcon() != null) {
					articleLabels[i][j].stopShine();
				}
			}
		}
	}
	
	/**
	 * Refresh view by model status.
	 * 
	 * @param matrix the matrix
	 */
	private void refresh(ArticleMatrix matrix){		
		for (int i = 0; i < matrix.getHeight(); i++) {
			for (int j = 0; j < matrix.getWidth(); j++) {
				if(matrix.getArticle(i, j) != null){
					articleLabels[i][j].setIcon(matrix.getArticle(i, j).getImageIcon());
					articleLabels[i][j].setAudioClip(matrix.getArticle(i, j).getAudioClip());
				}else {
					articleLabels[i][j].setIcon(null);
					articleLabels[i][j].setAudioClip(null);
				}
			}
		}
	}
	
	/*------------------------------------   Accessor Methods   ------------------------------------ */

	/**
	 * Gets the article matrix panel.
	 * 
	 * @return the articleMatrixPanel
	 */
	public JPanel getArticleMatrixPanel() {
		return articleMatrixPanel;
	}

	/**
	 * Sets the article matrix panel.
	 * 
	 * @param articleMatrixPanel the articleMatrixPanel to set
	 */
	public void setArticleMatrixPanel(JPanel articleMatrixPanel) {
		this.articleMatrixPanel = articleMatrixPanel;
	}

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
	
	/**
	 * Checks if is enabled.
	 * 
	 * @return true, if is enabled
	 */
	public boolean isEnabled() {
		return enabled;
	}
	
	/**
	 * Sets the enabled.
	 * 
	 * @param enabled the new enabled
	 */
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

}

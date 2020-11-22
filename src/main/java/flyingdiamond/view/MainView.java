package flyingdiamond.view;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import org.apache.log4j.Logger;

import flyingdiamond.model.GameRunningManager;
import flyingdiamond.model.ModelUpdateCaseEnum;
import flyingdiamond.view.uicomponent.MainFrame;
import flyingdiamond.view.uicomponent.MessageBox;

// TODO: Auto-generated Javadoc
/**
 * The MainView contains the MainFrame,it presents some main-frame-class views such as message box.
 */
public class MainView implements Observer{
	
	/** The logger. */
	private static Logger logger = Logger.getLogger(GameStateView.class.getName());

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -5141099747639458810L;

	/** The main frame. */
	private MainFrame mainFrame;
	
	/** The message box. */
	private MessageBox messageBox;

	
	/**
	 * Initialize.
	 */
	public void initialize(){
		mainFrame.initialize();
		messageBox.initialize();
	}
	
	/**
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	public void update(final Observable o, final Object arg) {
		// NOTE:All view update work are UI work,they should be in EDT!!!
		// The update method is called by models,they are in system threads,
		// So,SwingUtilities.invokeAndWait can put the Runnable job in EDT!
		// Be carefull:We should use invokeAndWait not invokelater,because,
		// Ours UI update are ordered!For example:When matrix loading,we have to
		// waiting for one grew and the up updated,then grow a new line and
		// update again.
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				GameRunningManager manager = (GameRunningManager) o;
				ModelUpdateCaseEnum updateCase = (ModelUpdateCaseEnum) arg;
				if (updateCase == ModelUpdateCaseEnum.LEVEL_PASSED) {
					JOptionPane.showMessageDialog(mainFrame,
							"You passed this level! Now level "
									+ manager.getLevel() + "starts...");
				}
				logger
						.info("The main view updated by game runing manager's new status");
			}
		});
	}

	/**
	 * Gets the main frame.
	 * 
	 * @return the mainFrame
	 */
	public MainFrame getMainFrame() {
		return mainFrame;
	}

	/**
	 * Sets the main frame.
	 * 
	 * @param mainFrame the mainFrame to set
	 */
	public void setMainFrame(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
	}

	
	/**
	 * Sets the message box.
	 * 
	 * @param messageBox the new message box
	 */
	public void setMessageBox(MessageBox messageBox) {
		this.messageBox = messageBox;
	}
	
}

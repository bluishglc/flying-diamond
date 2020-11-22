/**
 * 
 */
package flyingdiamond.view.uicomponent;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import flyingdiamond.common.IconResourcer;
import flyingdiamond.common.ResourceEnum;



/**
 * The main frame of game interface.
 * @author Laurence Geng
 */
public class MainFrame extends JFrame {

	private static final long serialVersionUID = -5725297697313950098L;
    private JPanel articleMatrixPanel;
    private MenuBar mainFrameMenuBar;
    private ToolBar mainFrameToolBar;
    
    public void initialize(){
    	setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    	setTitle("Diamond Flying");
    	
    	//Set background image.
    	ImageIcon background = IconResourcer.getInstance().getIcon(ResourceEnum.ICON_BACKGROUND);
    	JLabel imgLabel = new JLabel(background); 
    	getLayeredPane().add(imgLabel,Integer.valueOf(Integer.MIN_VALUE)); 
    	imgLabel.setBounds(0,0,background.getIconWidth(),background.getIconHeight()); 
    	((JPanel)getContentPane()).setOpaque(false); 
    	
    	mainFrameMenuBar.initialize();
    	mainFrameToolBar.initialize();
    	setJMenuBar(mainFrameMenuBar);
    	Container contentPane = getRootPane().getContentPane();
    	contentPane.setLayout(new BorderLayout());
    	contentPane.add(mainFrameToolBar,BorderLayout.PAGE_START);
    	articleMatrixPanel.setOpaque(false);
    	contentPane.add(articleMatrixPanel,BorderLayout.CENTER);
    	pack();
    	setResizable(false);
    	//align center
    	setLocationRelativeTo(null);
    	setVisible(true);
    }
    
    /*------------------------  Accessor Methods  --------------------------*/
	
	/**
	 * @return the articleMatrixPanel
	 */
	public JPanel getArticleMatrixPanel() {
		return articleMatrixPanel;
	}

	/**
	 * @return the mainFrameMenuBar
	 */
	public MenuBar getMainFrameMenuBar() {
		return mainFrameMenuBar;
	}

	/**
	 * @param mainFrameMenuBar the mainFrameMenuBar to set
	 */
	public void setMainFrameMenuBar(MenuBar mainFrameMenuBar) {
		this.mainFrameMenuBar = mainFrameMenuBar;
	}

	/**
	 * @param articleMatrixPanel the articleMatrixPanel to set
	 */
	public void setArticleMatrixPanel(JPanel articleMatrixPanel) {
		this.articleMatrixPanel = articleMatrixPanel;
	}

	/**
	 * @return the mainFrameToolBar
	 */
	public ToolBar getMainFrameToolBar() {
		return mainFrameToolBar;
	}

	/**
	 * @param mainFrameToolBar the mainFrameToolBar to set
	 */
	public void setMainFrameToolBar(ToolBar mainFrameToolBar) {
		this.mainFrameToolBar = mainFrameToolBar;
	}	
	
}

/**
 * 
 */
package flyingdiamond.view.uicomponent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import flyingdiamond.common.IconResourcer;
import flyingdiamond.common.ResourceEnum;


/**
 * The Class MenuBar.
 */
public class MenuBar extends JMenuBar{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -1494790689380018403L;
	
	/** The new game menu item. */
	private JMenuItem newGameMenuItem;
	
	/** The exit menu item. */
	private JMenuItem exitMenuItem;
	
	/**
	 * Initialize.
	 */
	public void initialize(){
		JMenu gameMenu = new javax.swing.JMenu();
		gameMenu.setText("Game");
		newGameMenuItem.setIcon(IconResourcer.getInstance().getIcon(ResourceEnum.ICON_NEW_GAME_SMALL));
		newGameMenuItem.setText("New");
		gameMenu.add(newGameMenuItem);
		exitMenuItem.setIcon(IconResourcer.getInstance().getIcon(ResourceEnum.ICON_EXIT_SMALL));
		exitMenuItem.setText("Exit");
		gameMenu.add(exitMenuItem);
		add(gameMenu);
		JMenu operateMenu = new javax.swing.JMenu();
		operateMenu.setText("Operate");
		add(operateMenu);
	}
	
	/*------------------------------------   Accessor Methods   ------------------------------------ */

	/**
	 * Gets the new game menu item.
	 * 
	 * @return the newGameMenuItem
	 */
	public JMenuItem getNewGameMenuItem() {
		return newGameMenuItem;
	}

	/**
	 * Sets the new game menu item.
	 * 
	 * @param newGameMenuItem the newGameMenuItem to set
	 */
	public void setNewGameMenuItem(JMenuItem newGameMenuItem) {
		this.newGameMenuItem = newGameMenuItem;
	}

	/**
	 * Gets the exit menu item.
	 * 
	 * @return the exitMenuItem
	 */
	public JMenuItem getExitMenuItem() {
		return exitMenuItem;
	}

	/**
	 * Sets the exit menu item.
	 * 
	 * @param exitMenuItem the exitMenuItem to set
	 */
	public void setExitMenuItem(JMenuItem exitMenuItem) {
		this.exitMenuItem = exitMenuItem;
	}
	
}

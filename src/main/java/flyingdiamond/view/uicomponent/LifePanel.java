/**
 * 
 */
package flyingdiamond.view.uicomponent;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import flyingdiamond.common.IconResourcer;
import flyingdiamond.common.ResourceEnum;
import flyingdiamond.model.GameRunningManager;

/**
 * The UI Panel for GameRunningManager's life status.
 */
public class LifePanel extends JPanel{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 3911925135825875377L;

	/** The life labels. */
	private JLabel[] lifeLabels = new JLabel[GameRunningManager.MAX_LIVES];
	
	/**
	 * Instantiates a new life panel.
	 */
	public LifePanel(){
		setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
		setLayout(new GridLayout(1,GameRunningManager.MAX_LIVES));
		for(int i=GameRunningManager.MAX_LIVES-1;i>=0;i--){
			lifeLabels[i] = new JLabel();
			lifeLabels[i].setPreferredSize(new Dimension(35,35));
			lifeLabels[i].setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
			add(lifeLabels[i]);
		}
	}
	
	/*------------------------------------   Business Methods   ------------------------------------ */
	
	/**
	 * Show life.
	 * 
	 * @param life the life
	 */
	public void showLife(int life){
		erase();
		for(int i=0;i<life;i++){
			lifeLabels[i].setIcon(IconResourcer.getInstance().getIcon(ResourceEnum.ICON_LIFE));
		}
	}
	
	/**
	 * Erase.
	 */
	public void erase(){
		for(int i=0;i<3;i++){
			lifeLabels[i].setIcon(null);
		}
	}
}

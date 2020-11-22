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

/**
 * The Class ScorePanel.
 */
public class ScorePanel extends JPanel{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -1857203837391114971L;
	
	/** The Constant DIGITS. */
	private static final int DIGITS = 7;
	
	/** The score labels. */
	private JLabel[] scoreLabels = new JLabel[DIGITS];
	
	/**
	 * Instantiates a new score panel.
	 */
	public ScorePanel(){
		setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
		setLayout(new GridLayout(1,DIGITS));
		for(int i=DIGITS-1;i>=0;i--){
			scoreLabels[i] = new JLabel();
			scoreLabels[i].setPreferredSize(new Dimension(35,35));
			scoreLabels[i].setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
			add(scoreLabels[i]);
		}
	}
	
	/**
	 * Show score.
	 * 
	 * @param score the score
	 */
	public void showScore(int score){
		erase();
		String scoreStr = String.valueOf(score);
		int pos = 0;
		for(int i=scoreStr.length()-1;i>=0;i--){
			scoreLabels[pos++].setIcon(IconResourcer.getInstance().getIcon(ResourceEnum.valueOf("ICON_"+(scoreStr.substring(i,i+1)))));
		}
	}
	
	/**
	 * Erase.
	 */
	public void erase(){
		for(int i=DIGITS-1;i>=0;i--){
			scoreLabels[i].setIcon(null);
		}
	}

}

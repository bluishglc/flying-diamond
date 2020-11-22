/**
 * 
 */
package flyingdiamond.view.uicomponent;

import java.applet.AudioClip;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * The ArticleLabel is Article's UI component.
 */
public class ArticleLabel extends JLabel {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 4873339286759933021L;
	
	/** The row. */
	private int row;
	
	/** The col. */
	private int col;
	
	/** The shining. */
	private boolean shining = false;
	
	/** The timer. */
	private Timer timer;
	
	/** The icon switch task. */
	private IconSwitchTask iconSwitchTask;
	
	/** The icon. */
	private ImageIcon icon;
	
	private AudioClip audioClip;
	
	/**
	 * The Constructor.
	 * 
	 * @param row the row
	 * @param col the col
	 */
	public ArticleLabel(int row, int col) {
		super();
		this.row = row;
		this.col = col;
		this.timer = new Timer();
	}
	
	/*------------------------------------   Business Methods   ------------------------------------ */
	
	/**
	 * Shine.
	 * 
	 * @param icon the icon
	 * @param shiningIcon the shining icon
	 */
	public void shine(ImageIcon icon,ImageIcon shiningIcon){
		if (!shining) {
			shining = true;
			this.icon = icon;
			timer = new Timer();
			iconSwitchTask = new IconSwitchTask(icon, shiningIcon);
			timer.schedule(iconSwitchTask, 0, 500);
		}
	}
	
	/**
	 * Stop shine.
	 */
	public void stopShine() {
		if(shining){
			shining=false;
			timer.cancel();
			setIcon(icon);
		}
	}
	
	
	/*------------------------------------   Accessor Methods   ------------------------------------ */
	
	/**
	 * Gets the row.
	 * 
	 * @return the row
	 */
	public int getRow() {
		return row;
	}
	
	/**
	 * Sets the row.
	 * 
	 * @param row the row to set
	 */
	public void setRow(int row) {
		this.row = row;
	}
	
	/**
	 * Gets the col.
	 * 
	 * @return the col
	 */
	public int getCol() {
		return col;
	}
	
	/**
	 * Sets the col.
	 * 
	 * @param col the col to set
	 */
	public void setCol(int col) {
		this.col = col;
	}

	
	public AudioClip getAudioClip() {
		return audioClip;
	}

	public void setAudioClip(AudioClip audioClip) {
		this.audioClip = audioClip;
	}
	
	/*---------------------------------------   Inner Class   -------------------------------------- */

	/**
	 * A switch icon TimeTask.
	 */
	private class IconSwitchTask extends TimerTask{
		
		/** The old icon. */
		private ImageIcon oldIcon;
		
		/** The new icon. */
		private ImageIcon newIcon;		

		/**
		 * The Constructor.
		 * 
		 * @param oldIcon the old icon
		 * @param newIcon the new icon
		 */
		public IconSwitchTask(ImageIcon oldIcon, ImageIcon newIcon) {
			super();
			this.oldIcon = oldIcon;
			this.newIcon = newIcon;
		}

		/* (non-Javadoc)
		 * @see java.lang.Thread#run()
		 */
		@Override
		public void run() {
			setIcon(getIcon()==oldIcon?newIcon:oldIcon);
		}

		/**
		 * Gets the old icon.
		 * 
		 * @return the oldIcon
		 */
		public ImageIcon getOldIcon() {
			return oldIcon;
		}

		/**
		 * Sets the old icon.
		 * 
		 * @param oldIcon the oldIcon to set
		 */
		public void setOldIcon(ImageIcon oldIcon) {
			this.oldIcon = oldIcon;
		}

		/**
		 * Gets the new icon.
		 * 
		 * @return the newIcon
		 */
		public ImageIcon getNewIcon() {
			return newIcon;
		}

		/**
		 * Sets the new icon.
		 * 
		 * @param newIcon the newIcon to set
		 */
		public void setNewIcon(ImageIcon newIcon) {
			this.newIcon = newIcon;
		}			
	}
}

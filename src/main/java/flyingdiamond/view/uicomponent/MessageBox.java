package flyingdiamond.view.uicomponent;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;

import javax.swing.JDialog;
import javax.swing.JPanel;

public class MessageBox extends JDialog{

	private static final long serialVersionUID = 2733083345027004706L;
	
	private String message = "";
	
	private Frame owner;
	
	public MessageBox(Frame owner,boolean modal){
		super(owner,modal);
		this.owner = owner;
	}
	
	public void initialize(){
		setSize(new Dimension(300,150));
		add(new JPanel(){
			public void paintComponent(Graphics g){
				super.paintComponent(g);
				g.drawString(message, 30,60);
			}
		});
	}
	public void show(String title,String message){
		setTitle(title);
		setMessage(message);
		repaint();
		setLocationRelativeTo(owner);
		setVisible(true);
	}
	
	public void setMessage(String message) {
		this.message=message;
	}

}

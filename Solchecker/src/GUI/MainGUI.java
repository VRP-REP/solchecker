package GUI;

import javax.swing.*;

/**
 * MainGUI - Main class of GUI
 */
public class MainGUI{	
	public static void main(String [] args){
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				MainWindow window = new MainWindow();
			}
		});
	}
}

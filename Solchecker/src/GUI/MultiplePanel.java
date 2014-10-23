package GUI;

import java.awt.BorderLayout;

import javax.swing.JPanel;

/**
 * MultiplePanel - JPanel extension to display the file selector necessary for running the Solchecker
 */
public class MultiplePanel extends JPanel{
	private static final long serialVersionUID = 1L;
	
	private FileChoicePanel batchChoicePanel;
	
	/**
	 * MultiplePanel - Creation and Initialization of panel
	 */
	public MultiplePanel(){
		// Initialization of the panel
		super();		
		this.setLayout(new BorderLayout());
		
		// Initialization of the batchChoicePanel
		batchChoicePanel = new FileChoicePanel("Batch", "xml", true);
		this.add(batchChoicePanel, BorderLayout.CENTER);
	}
}

package GUI;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

/**
 * IndividualPanel - JPanel extension to display the different files selector necessary for running the Solchecker
 */
public class IndividualPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	
	private FileChoicePanel solutionChoicePanel;
	private FileChoicePanel instanceChoicePanel;
	private FileChoicePanel solcheckerChoicePanel;
	
	/**
	 * IndividualPanel - Creation and initialization of IndividualPanel
	 */
	public IndividualPanel(){
		// Initialization of the panel
		super();
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		// Initialization of the instanceChoicePanel
		instanceChoicePanel = new FileChoicePanel("Instance", "xml", false);
		this.add(instanceChoicePanel);

		// Initialization of the solutionChoicePanel
		solutionChoicePanel = new FileChoicePanel("Solution", "xml", false);
		this.add(solutionChoicePanel);	

		// Initialization of the solcheckerChoicePanel
		solcheckerChoicePanel = new FileChoicePanel("Solchecker", "jar", true);		
		this.add(solcheckerChoicePanel);
		
		this.add(Box.createVerticalGlue());
	}
}

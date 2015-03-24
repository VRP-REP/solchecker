package org.vrprep.solchecker.GUI;

import java.io.File;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

/**
 * IndividualPanel - JPanel extension to display the different files selector
 * necessary for running the Solchecker
 */
public class IndividualPanel extends JPanel {

    private FileChoicePanel solutionChoicePanel;
    private FileChoicePanel instanceChoicePanel;

    /**
     * IndividualPanel - Creation and initialization of IndividualPanel
     */
    public IndividualPanel() {
        // Initialization of the panel
        super();
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Initialization of the instanceChoicePanel
        instanceChoicePanel = new FileChoicePanel("Instance", null, false);
        this.add(instanceChoicePanel);

        // Initialization of the solutionChoicePanel
        solutionChoicePanel = new FileChoicePanel("Solution", null, false);
        this.add(solutionChoicePanel);

        this.add(Box.createVerticalGlue());
    }
    
    public File getSolutionFile(){
        return solutionChoicePanel.getFile();
    }
    
    public File getInstanceFile(){
        return instanceChoicePanel.getFile();
    }
}

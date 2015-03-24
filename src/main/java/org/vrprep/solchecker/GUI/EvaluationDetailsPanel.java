package org.vrprep.solchecker.GUI;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.vrprep.solchecker.framework.ConstraintEvaluation;
import org.vrprep.solchecker.framework.Evaluation;
import org.vrprep.solchecker.framework.MetricEvaluation;
import org.vrprep.solchecker.framework.ObjectiveEvaluation;

/**
 *
 */
public class EvaluationDetailsPanel extends JPanel {

    public EvaluationDetailsPanel(ConstraintEvaluation evaluation) {
        super();
        
        //
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        
        // Information
        JPanel details = displayDetails(evaluation);

        // Display Feasability
        JPanel feasibility = new JPanel();
        feasibility.setLayout(new BoxLayout(feasibility, BoxLayout.X_AXIS));
        feasibility.setAlignmentX(LEFT_ALIGNMENT);
        details.add(feasibility);
        
        JLabel feasibilityLabel = new JLabel("Feasible : ");
        feasibilityLabel.setAlignmentX(LEFT_ALIGNMENT);
        feasibility.add(feasibilityLabel);
        
        JLabel feasible = new JLabel(Boolean.toString(evaluation.isFeasible()));
        feasible.setAlignmentX(LEFT_ALIGNMENT);
        feasible.setForeground( evaluation.isFeasible()? Color.GREEN : Color.RED );
        feasibility.add(feasible);
        
        // Display Messages
        displayMessages(evaluation);
    }

    public EvaluationDetailsPanel(ObjectiveEvaluation evaluation) {
        super();
        
        //
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        
        // Information
        JPanel details = displayDetails(evaluation);

        // Display Value
        JLabel value = new JLabel("Value : " + evaluation.getValue());
        details.add(value);
        
        // Display Messages
        displayMessages(evaluation);
    }

    public EvaluationDetailsPanel(MetricEvaluation evaluation) {
        super();
        
        //
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        
        displayDetails(evaluation);
        displayMessages(evaluation);
    }
    
    public JPanel displayDetails(Evaluation evaluation){
        JPanel details = new JPanel();
        details.setLayout(new BoxLayout(details, BoxLayout.PAGE_AXIS));
        details.setAlignmentX(LEFT_ALIGNMENT);
        details.setMinimumSize(new Dimension(400, 70));
        details.setMaximumSize(new Dimension(400, 70));
        details.setBorder(BorderFactory.createTitledBorder(BorderFactory
                .createMatteBorder(1, 1, 1, 1, new Color(0, 125, 204)), "Details"));
        this.add(details);
        
        // Display Name
        JLabel name = new JLabel("Name : " + evaluation.getName());
        name.setAlignmentX(LEFT_ALIGNMENT);
        details.add(name);
        
        return details;
    }
    
    public void displayMessages(Evaluation evaluation){
        JPanel messages = new JPanel();
        messages.setLayout(new BoxLayout(messages, BoxLayout.Y_AXIS));
        messages.setAlignmentX(LEFT_ALIGNMENT);
        messages.setMinimumSize(new Dimension(500, 320));
        messages.setMaximumSize(new Dimension(500, 320));
        messages.setBorder(BorderFactory.createTitledBorder(BorderFactory
                .createMatteBorder(1, 1, 1, 1, new Color(0, 125, 204)), "Messages"));
        this.add(messages);
        
        // Display messages
        for(String message : evaluation.getMessages()){
            messages.add(new JLabel(message));
        }
    }
}

package org.vrprep.solchecker.GUI;

import java.awt.CardLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 */
public class PluginGenerationFrame extends JFrame implements ActionListener {
    private final MenuBar menuBar;
    private final Button constraintButton;
    private final Button functionButton;
    private final Button metricButton;
    
    private final CardLayout cardLayout;
    private final JPanel choicePanel;
    private final PluginGenerationChoicePanel constraintPanel;
    private final PluginGenerationChoicePanel functionPanel;
    private final PluginGenerationChoicePanel metricPanel;
   
    private final Button generateButton;
    
    public PluginGenerationFrame(){
        super("VRP-REP Generate Solchecker");

        // Initialization of the frame
        setLayout(new GridBagLayout());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 500);
        setResizable(false);
        
        // 
        GridBagConstraints position = new GridBagConstraints();
        position.fill = GridBagConstraints.HORIZONTAL;

        // Change favicon
        URL iconURL = this.getClass().getResource("/favicon.png");
        if (iconURL != null) {
            ImageIcon icon = new ImageIcon(iconURL);
            this.setIconImage(icon.getImage());
        }

        // Initialization of the menuBar
        menuBar = new MenuBar();
        this.setJMenuBar(menuBar);
        
        constraintButton = new Button("Constraint");
        constraintButton.addActionListener(this);
        position.gridheight = 1;
        position.gridwidth = 1;
        position.gridx = 0;
        position.gridy = 0;
        this.add(constraintButton, position);
        
        functionButton = new Button("Objective function");
        functionButton.addActionListener(this);
        position.gridheight = 1;
        position.gridwidth = 1;
        position.gridx = 1;
        position.gridy = 0;
        this.add(functionButton, position);
        
        metricButton = new Button("Metric");
        metricButton.addActionListener(this);
        position.gridheight = 1;
        position.gridwidth = 1;
        position.gridx = 2;
        position.gridy = 0;
        this.add(metricButton, position);
        
        cardLayout = new CardLayout();
        choicePanel = new JPanel(cardLayout);
        constraintPanel = new PluginGenerationChoicePanel();
        choicePanel.add(constraintPanel, "constraintPanel");
        functionPanel = new PluginGenerationChoicePanel();
        choicePanel.add(functionPanel, "functionPanel");
        metricPanel = new PluginGenerationChoicePanel();
        choicePanel.add(metricPanel, "metricPanel");
        position.gridheight = 2;
        position.gridwidth = 3;
        position.gridx = 0;
        position.gridy = 1;
        this.add(choicePanel, position);

        cardLayout.show(choicePanel, "individualPanel");
        
        generateButton = new Button("Generate");
        position.gridheight = 1;
        position.gridwidth = 1;
        position.gridx = 0;
        position.gridy = 3;
        this.add(generateButton, position);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == constraintButton) {
            // Show constrintPanel
            cardLayout.show(choicePanel, "constraintPanel");
        } else if (e.getSource() == functionButton) {
            // Show constrintPanel
            cardLayout.show(choicePanel, "functionPanel");
        } else {
            // Show functionPanel
            cardLayout.show(choicePanel, "metricPanel");
        }
    }
}

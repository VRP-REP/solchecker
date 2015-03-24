package org.vrprep.solchecker.GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.JList;
import javax.swing.JPanel;

/**
 *
 */
public class PluginGenerationChoicePanel extends JPanel implements ActionListener{
    private JList availableList;
    private JList selectedList;
    
    private Button addButton;
    private Button deleteButton;
    
    public PluginGenerationChoicePanel(){
        // Initialization of the panel
        this.setLayout(new GridBagLayout());
        this.setSize(200, 200);
        
        // 
        GridBagConstraints position = new GridBagConstraints();
        position.fill = GridBagConstraints.HORIZONTAL;
        
        Vector<String> availableElement = new Vector<String>();
        availableElement.add("element 1");
        availableElement.add("element 2");
        availableList = new JList(availableElement);
        availableList.setPreferredSize(new Dimension(150, 150));
        availableList.setBorder(BorderFactory.createTitledBorder(BorderFactory
                .createMatteBorder(1, 1, 1, 1, new Color(0, 125, 204)), "Available"));
        position.gridheight = 2;
        position.gridwidth = 1;
        position.gridx = 0;
        position.gridy = 0;
        this.add(availableList, position);
        
        addButton = new Button("Add >");
        addButton.addActionListener(this);
        position.gridheight = 1;
        position.gridwidth = 1;
        position.gridx = 1;
        position.gridy = 0;
        this.add(addButton, position);
        deleteButton = new Button("< Delete");
        deleteButton.addActionListener(this);
        position.gridheight = 1;
        position.gridwidth = 1;
        position.gridx = 1;
        position.gridy = 1;
        this.add(deleteButton, position);
        
        selectedList = new JList();
        selectedList.setPreferredSize(new Dimension(150, 150));
        selectedList.setBorder(BorderFactory.createTitledBorder(BorderFactory
                .createMatteBorder(1, 1, 1, 1, new Color(0, 125, 204)), "Selected"));
        position.gridheight = 2;
        position.gridwidth = 1;
        position.gridx = 2;
        position.gridy = 0;
        this.add(selectedList, position);
    }
    
    public void addElementToSelectedList(){
        availableList.getSelectedIndex();
    }
    
    public void deleteElementToSelectedList(){
        
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            addElementToSelectedList();
        } else if (e.getSource() == deleteButton) {
            deleteElementToSelectedList();
        }
    }
}

package org.vrprep.solchecker.GUI;

import org.vrprep.solchecker.tools.SolcheckerInformation;
import org.vrprep.solchecker.tools.Tools;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 */
public class PluginFrame extends JFrame implements ActionListener{
    private MenuBar menuBar;
    
    private JTable pluginTable;
    
    private Button addNewPluginButton;

    public PluginFrame() {
        super("VRP-REP SolChecker - Plugin Manager");
        
        // Initialization of the frame
        setLayout(new GridBagLayout());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(450, 250);
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

        // Adding the menu bar
        menuBar = new MenuBar();
        this.setJMenuBar(menuBar);

        //
        addNewPluginButton = new Button("Add new plugin");
        addNewPluginButton.addActionListener(this);
        position.weighty = 0.5;
        position.gridx = 0;
        position.gridy = 0;
        this.add(addNewPluginButton, position);
        
        // Initialization of the table with installed plugin        
        pluginTable = new JTable();
        updatePluginTable();
        pluginTable.setPreferredScrollableViewportSize(new Dimension(400, 100));
        pluginTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        JScrollPane notInstalledPluginPanel = new JScrollPane(pluginTable);

        position.weighty = 0.5;
        position.gridx = 0;
        position.gridy = 1;
        position.gridheight = 5;
        position.gridwidth = 5;
        this.add(notInstalledPluginPanel, position);
    }

    public void updatePluginTable(){
        //
        ArrayList<SolcheckerInformation> solcheckerInformationList = Tools.extractInformationFromPluginFile(this.getClass().getResource("/plugin.xml").getFile());
        
        //
        Vector<String> title = new Vector<String>();
        title.add("ArtifactID");
        title.add("GroupID");
        title.add("Version");
        
        //
        Vector<Vector> solcheckerList = new Vector<Vector>();
        for(SolcheckerInformation solcheckerInformation : solcheckerInformationList){
            Vector<String> solchecker = new Vector<String>();
            solchecker.add(solcheckerInformation.getArtifactID());
            solchecker.add(solcheckerInformation.getGroupID());
            solchecker.add(solcheckerInformation.getVersion());
            solcheckerList.add(solchecker);
        }
        
        //
        pluginTable.setModel(new DefaultTableModel(solcheckerList, title));
    }
    
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == addNewPluginButton){
            new PluginToInstallDialog();
            updatePluginTable();
            AppLauncher.solcheckerFrame.updateSolcheckerChoicePanel();
        }
    }
}

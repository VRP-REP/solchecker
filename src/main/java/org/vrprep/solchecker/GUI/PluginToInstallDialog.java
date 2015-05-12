package org.vrprep.solchecker.GUI;

import org.vrprep.solchecker.tools.SolcheckerInformation;
import org.vrprep.solchecker.tools.Tools;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import org.apache.commons.lang.SystemUtils;

/**
 *
 */
public class PluginToInstallDialog extends JDialog implements ActionListener {
    private final Vector<String> columnNames;
    private final DefaultTableModel notInstalledPluginModel;
    private final PluginToInstallTable notInstalledPluginTable;
    private final JScrollPane notInstalledPluginPanel;

    private final Button installPluginButton;
    private final Button cancelButton;

    /**
     *
     */
    public PluginToInstallDialog() {
        setTitle("VRP-REP SolChecker - Add new plugin");

        // Initialization of the dialog
        setLayout(new GridBagLayout());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(550, 200);
        setResizable(false);
        setModal(true);

        // Initialization of grid bag constraints
        GridBagConstraints position = new GridBagConstraints();
        position.fill = GridBagConstraints.HORIZONTAL;

        // Change favicon
        URL iconURL = this.getClass().getResource("/favicon.png");
        if (iconURL != null) {
            ImageIcon icon = new ImageIcon(iconURL);
            this.setIconImage(icon.getImage());
        }

        // Initialization of the table with not installed plugin
        columnNames = new Vector<String>();
        columnNames.add("Path");
        columnNames.add("ArtifactID");
        columnNames.add("GroupID");
        columnNames.add("Version");
        columnNames.add("To Install");

        notInstalledPluginModel = new DefaultTableModel(notInstalledPluginData(), columnNames);
        notInstalledPluginTable = new PluginToInstallTable(notInstalledPluginModel);
        notInstalledPluginPanel = new JScrollPane(notInstalledPluginTable);

        //
        notInstalledPluginTable.getColumn("Path").setMinWidth(0);
        notInstalledPluginTable.getColumn("Path").setMaxWidth(0);

        position.gridheight = 3;
        position.gridwidth = 3;
        position.weighty = 0.5;
        position.gridx = 0;
        position.gridy = 0;
        this.add(notInstalledPluginPanel, position);

        //        
        installPluginButton = new Button("Install");
        installPluginButton.addActionListener(this);

        position.gridheight = 1;
        position.gridwidth = 1;
        position.weighty = 0.5;
        position.gridx = 0;
        position.gridy = 5;
        this.add(installPluginButton, position);

        //        
        cancelButton = new Button("Cancel");
        cancelButton.addActionListener(this);

        position.gridheight = 1;
        position.gridwidth = 1;
        position.weighty = 0.5;
        position.gridx = 1;
        position.gridy = 5;
        this.add(cancelButton, position);

        // Display the dialog
        setVisible(true);
    }

    /**
     *
     * @return
     */
    public Vector<Vector> notInstalledPluginData() {
        List<SolcheckerInformation> solcheckerInformationList = Tools.extractSolcheckerInformationFromPluginFile(this.getClass().getResource("/plugin.xml").getFile());
        List<String> pluginJarList = Tools.extractJARFromFolder(this.getClass().getResource("/plugin").getFile());

        Vector<Vector> pluginList = new Vector<Vector>();

        for (String pluginJarName : pluginJarList) {
            Vector<String> solcheckerInformation = Tools.extractInformationFromJAR(this.getClass().getResource("/plugin/" + pluginJarName).getFile());

            if (solcheckerInformation != null) {
                if (solcheckerInformation.size() == 4) {
                    boolean alreadyInstalled = false;

                    for (SolcheckerInformation solcheckerInstalled : solcheckerInformationList) {
                        if (solcheckerInstalled.compareTo(solcheckerInformation.get(1), solcheckerInformation.get(2), solcheckerInformation.get(3))) {
                            alreadyInstalled = true;
                        }
                    }

                    if (!alreadyInstalled) {
                        pluginList.add(solcheckerInformation);
                    }
                }
            }
        }

        return pluginList;
    }

    /**
     *
     */
    public void pluginInstallation() {
        TableModel model = notInstalledPluginTable.getModel();

        for (int i = 0; i < model.getRowCount(); ++i) {
            Boolean toInstall = (model.getValueAt(i, 4) != null) ? (Boolean) model.getValueAt(i, 4) : false;
            if (toInstall) {
                String commande = "";
                
                if(SystemUtils.IS_OS_WINDOWS){
                    commande = "cmd.exe" + " /C" + " mvn" + " install:install-file" + " -Dfile=" + model.getValueAt(i, 0) + " -DgroupId=" + model.getValueAt(i, 2) + " -DartifactId=" + model.getValueAt(i, 1) + " -Dversion=" + model.getValueAt(i, 3) + " -Dpackaging=jar";
                } else {
                    commande = "mvn" + " install:install-file" + " -Dfile=" + model.getValueAt(i, 0) + " -DgroupId=" + model.getValueAt(i, 2) + " -DartifactId=" + model.getValueAt(i, 1) + " -Dversion=" + model.getValueAt(i, 3) + " -Dpackaging=jar";
                }
                
                // Installation du plugin dans le projet
                boolean installationOk = Tools.executeCommande(commande);

                // Si l'installation a réussi
                if (installationOk) {
                    // Ajout de la dépendance dans le pom.xml
                    String path = new File("").getAbsolutePath() + "\\pom.xml";
                    Tools.addDependencyToPomFile(path, model.getValueAt(i, 1).toString(), model.getValueAt(i, 2).toString(), model.getValueAt(i, 3).toString());

                    // Récupération des informations sur le solchecker
                    SolcheckerInformation solcheckerInformation = Tools.extractSolcheckerInformationFromJAR(model.getValueAt(i, 0).toString());

                    // Update plugin.xml 
                    Tools.addSolcheckerInformationToPluginFile(this.getClass().getResource("/plugin.xml").getFile(), solcheckerInformation);
                }
            }
        }
    }

    /**
     *
     * @param e
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == installPluginButton) {
            System.out.println("Installation ...");
            pluginInstallation();
            
            JOptionPane.showMessageDialog(null, "VRP-REP Application needs to be restarted to take changes into consideration.", "VRP-REP Restart", JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        } else if (e.getSource() == cancelButton) {
            this.dispose();
        }
    }
}

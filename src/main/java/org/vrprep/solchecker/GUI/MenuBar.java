package org.vrprep.solchecker.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 *
 */
public class MenuBar extends JMenuBar implements ActionListener {

    private static final long serialVersionUID = 1L;

    private JMenu fileMenu = new JMenu("File");
    private JMenuItem exitMenuItem = new JMenuItem("Exit");

    private JMenu solcheckerMenu = new JMenu("SolChecker");
    private JMenuItem solcheckerMenuItem = new JMenuItem("Go to");

    private JMenu pluginMenu = new JMenu("Plugin");
    private JMenuItem managePluginMenuItem = new JMenuItem("Manage");
    private JMenuItem generatePluginMenuItem = new JMenuItem("Generate");

    private JMenu helpMenu = new JMenu("Help ?");
    private JMenuItem aboutMenuItem = new JMenuItem("About");

    /**
     * MenuBar - Creation and initialization of MenuBar
     */
    public MenuBar() {
        this.add(fileMenu);
        fileMenu.add(exitMenuItem);
        exitMenuItem.addActionListener(this);

        this.add(solcheckerMenu);
        solcheckerMenu.add(solcheckerMenuItem);
        solcheckerMenuItem.addActionListener(this);

        this.add(pluginMenu);
        pluginMenu.add(managePluginMenuItem);
        managePluginMenuItem.addActionListener(this);
        pluginMenu.add(generatePluginMenuItem);
        generatePluginMenuItem.addActionListener(this);

        this.add(helpMenu);
        helpMenu.add(aboutMenuItem);
        aboutMenuItem.addActionListener(this);
    }

    public void actionPerformed(ActionEvent arg0) {

        if (arg0.getSource() == exitMenuItem) {
            SwingUtilities.getWindowAncestor(this).dispose();
        } else if (arg0.getSource() == solcheckerMenuItem) {
            //new SolcheckerFrame();
            AppLauncher.solcheckerFrame.setVisible(true);
        } else if (arg0.getSource() == managePluginMenuItem) {
            //new PluginFrame();
            AppLauncher.pluginFrame.setVisible(true);
        } else if (arg0.getSource() == generatePluginMenuItem) {
            AppLauncher.pluginGenerationFrame.setVisible(true);
        } else if (arg0.getSource() == aboutMenuItem) {
            JOptionPane.showMessageDialog(null, "For more information,\nplease go to http://vrp-rep.org", "VRP-REP", JOptionPane.INFORMATION_MESSAGE);
        }
    }

}

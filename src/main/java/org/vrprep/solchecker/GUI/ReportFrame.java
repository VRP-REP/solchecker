package org.vrprep.solchecker.GUI;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import org.vrprep.solchecker.framework.*;

/**
 *
 */
public class ReportFrame extends JFrame implements ActionListener {

    private List<Report> reportsList;

    private JComboBox reportsCombobox;
    private JTree reportTree = new JTree();
    private CardLayout cardLayout = new CardLayout();
    private JPanel detailsPanel = new JPanel(cardLayout);
    private Button exportXMLButton;

    /**
     *
     * @param reportsList
     */
    public ReportFrame(List<Report> reportsList) {
        super("VRP-REP SolChecker - Report");

        // Initialization of the frame
        setLayout(new GridBagLayout());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(700, 500);
        setResizable(false);

        // Change favicon
        URL iconURL = this.getClass().getResource("/favicon.png");
        if (iconURL != null) {
            ImageIcon icon = new ImageIcon(iconURL);
            this.setIconImage(icon.getImage());
        }

        // 
        GridBagConstraints position = new GridBagConstraints();
        position.fill = GridBagConstraints.HORIZONTAL;

        //
        if (reportsList.isEmpty()) {
            this.add(new JLabel("Sorry, any report for this run ..."));
        } else {
            this.reportsList = reportsList;

            // Initialization of the combobox containing all the report
            reportsCombobox = new JComboBox(this.reportsList.toArray());
            reportsCombobox.setPreferredSize(new Dimension(200, 25));
            reportsCombobox.addActionListener(this);
            position.gridx = 0;
            position.gridy = 0;
            position.gridheight = 1;
            position.gridwidth = 1;
            this.add(reportsCombobox, position);

            // 
            Report courantReport = getReport(reportsCombobox.getSelectedItem().toString());
            reportTree.setModel(new DefaultTreeModel(buildReportTree(courantReport)));
            reportTree.setPreferredSize(new Dimension(200, 350));
            reportTree.addTreeSelectionListener(new TreeSelectionListener() {
                public void valueChanged(TreeSelectionEvent e) {
                    DefaultMutableTreeNode node = (DefaultMutableTreeNode) reportTree.getLastSelectedPathComponent();

                    if (node != null) {
                        if (node.getLevel() == 2) {
                            cardLayout.show(detailsPanel, node.getUserObject().toString());
                        }
                    }
                }
            });
            position.gridx = 0;
            position.gridy = 1;
            position.gridheight = 2;
            position.gridwidth = 1;
            this.add(reportTree, position);

            // 
            detailsPanel.setPreferredSize(new Dimension(400, 400));
            position.gridx = 1;
            position.gridy = 0;
            position.gridheight = 3;
            position.gridwidth = 3;
            this.add(detailsPanel, position);

            //
            exportXMLButton = new Button("Export to XML");
            exportXMLButton.addActionListener(this);
            position.gridx = 0;
            position.gridy = 3;
            position.gridheight = 1;
            position.gridwidth = 1;
            this.add(exportXMLButton, position);
        }
    }

    /**
     *
     * @param report
     * @return
     */
    public DefaultMutableTreeNode buildReportTree(Report report) {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode();

        DefaultMutableTreeNode objectives = new DefaultMutableTreeNode("Objectives", true);
        root.add(objectives);

        DefaultMutableTreeNode constraints = new DefaultMutableTreeNode("Constraints", true);
        root.add(constraints);

        DefaultMutableTreeNode metrics = new DefaultMutableTreeNode("Metrics", true);
        root.add(metrics);

        for (Evaluation evaluation : report.getEvaluations()) {
            DefaultMutableTreeNode evaluationNode = new DefaultMutableTreeNode(evaluation.getName());
            if (evaluation.getClass().isInstance(new ObjectiveEvaluation(null))) {
                objectives.add(evaluationNode);
                detailsPanel.add(new EvaluationDetailsPanel((ObjectiveEvaluation) evaluation), evaluation.getName());
            } else if (evaluation.getClass().isInstance(new ConstraintEvaluation(null))) {
                constraints.add(evaluationNode);
                detailsPanel.add(new EvaluationDetailsPanel((ConstraintEvaluation) evaluation), evaluation.getName());
            } else if (evaluation.getClass().isInstance(new MetricEvaluation(null))) {
                metrics.add(evaluationNode);
                detailsPanel.add(new EvaluationDetailsPanel((MetricEvaluation) evaluation), evaluation.getName());
            }
        }

        return root;
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == exportXMLButton) { // Export XML
            Report report = getReport(reportsCombobox.getSelectedItem().toString());

            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Choose report path");
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int isOneFolderChoose = fileChooser.showOpenDialog(this);

            if (isOneFolderChoose == JFileChooser.APPROVE_OPTION) {
                Date currentDate = new Date();
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm");
                File exportFile = new File(fileChooser.getSelectedFile().getAbsolutePath() + "\\" + dateFormat.format(currentDate) + "_report.xml");
                report.exportToXML(exportFile);
            }
        } else if (e.getSource() == reportsCombobox) {
            // Mise à jour de Jtree
            Report report = getReport(reportsCombobox.getSelectedItem().toString());
            reportTree.setModel(new DefaultTreeModel(buildReportTree(report)));
        }
    }

    public Report getReport(String reportName) {
        for (Report report : reportsList) {
            if (report.toString().equalsIgnoreCase(reportName)) {
                return report;
            }
        }

        return null;
    }
}

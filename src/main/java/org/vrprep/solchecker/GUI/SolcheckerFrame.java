package org.vrprep.solchecker.GUI;

import org.vrprep.solchecker.tools.RunnableInformation;
import org.vrprep.solchecker.tools.Tools;
import org.vrprep.solchecker.framework.SolChecker;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import org.vrprep.solchecker.framework.Report;

/**
 * SolcheckerFrame - JFrame extension to display
 */
public class SolcheckerFrame extends JFrame implements ActionListener {

    private final MenuBar menuBar;
    private final ModeChoicePanel modeChoicePanel;
    private final SolcheckerChoicePanel solcheckerPanel;
    private final Button runButton;

    /**
     * SolcheckerFrame - Creation and initialization of SolcheckerFrame
     */
    public SolcheckerFrame() {
        super("VRP-REP SolChecker");

        // Initialization of the frame
        setLayout(new GridBagLayout());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(350, 350);
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

        // Initialization of the modeChoicePanel
        modeChoicePanel = new ModeChoicePanel();
        position.gridx = 0;
        position.gridy = 0;
        this.add(modeChoicePanel, position);

        // 
        solcheckerPanel = new SolcheckerChoicePanel();
        position.gridx = 0;
        position.gridy = 1;
        this.add(solcheckerPanel, position);

        // Initialization of the runButton   
        runButton = new Button("Run");
        runButton.addActionListener(this);
        position.gridx = 0;
        position.gridy = 2;
        this.add(runButton, position);
    }

    /**
     *
     */
    public void updateSolcheckerChoicePanel() {
        solcheckerPanel.updateSolcheckerList();
        solcheckerPanel.updateVariantCombobox();
        solcheckerPanel.updateDatasetCombobox();
        solcheckerPanel.updateSolchekerCombobox();
    }
    
    /**
     *
     * @param runElement
     * @return
     */
    public Report runSingle(RunnableInformation runElement) {
        try {
            File configurationFile = new File(this.getClass().getResource("").getPath() + "\\temp.xml");
            Element configurationElement = solcheckerPanel.getSolcheckerInformation().getConfigurations(solcheckerPanel.getVariantSelected()).toXML();
            Document configurationDocument = new Document(configurationElement);
            XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
            sortie.output(configurationDocument, new FileOutputStream(configurationFile));

            //
            String solcheckerClass = solcheckerPanel.getSolcheckerSelectedClass();
            Class myClass = Class.forName(solcheckerClass);
            SolChecker solchecker = (SolChecker) myClass.getDeclaredConstructor(File.class).newInstance(configurationFile);

            // Verify if solutionFile and instanceFile are selected
            if (runElement.getSolutionFile() == null || runElement.getInstanceFile() == null) {
                System.out.println("No File Selected");
                return null;
            } else {
                return solchecker.run(runElement.getSolutionFile(), runElement.getInstanceFile());
            }

        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    /**
     *
     * @param runList
     * @return
     */
    public List<Report> runMultiple(List<RunnableInformation> runList) {
        System.out.println("-- RUN Multiple --");

        List<Report> reportList = new ArrayList<Report>();

        for (RunnableInformation runElement : runList) {
            Report report = runSingle(runElement);
            if(report != null) reportList.add(report);
        }

        return reportList;
    }

    /**
     *
     * @param e
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == runButton) {
            List<Report> reportList = new ArrayList<Report>();

            if (modeChoicePanel.individualSolcheckerIsSelected() == true) {
                Report report = runSingle(new RunnableInformation(modeChoicePanel.getSolutionFile(), modeChoicePanel.getInstanceFile()));
                if(report != null) reportList.add(report);
            } else {
                List<RunnableInformation> runList = Tools.extractRunnableInformationFromBatchFile(modeChoicePanel.getBatchFile());
                if(runList != null) reportList = runMultiple(runList);
            }
            
            ReportFrame reportFrame = new ReportFrame(reportList);
            reportFrame.setVisible(true);
        }
    }
}

package org.vrprep.solchecker.GUI;

import org.vrprep.solchecker.tools.SolcheckerInformation;
import org.vrprep.solchecker.tools.Tools;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 */
public class SolcheckerChoicePanel extends JPanel implements ActionListener {

    private final JLabel variantLabel;
    private final JLabel datasetLabel;
    private final JLabel solcheckerLabel;

    private final JComboBox variantComboBox;
    private final JComboBox datasetComboBox;
    private final JComboBox solcheckerComboBox;

    private ArrayList<SolcheckerInformation> solcheckerInformationList;

    /**
     *
     */
    public SolcheckerChoicePanel() {
        // Initialization of the panel
        this.setLayout(new GridLayout(3, 2));
        this.setBorder(BorderFactory.createTitledBorder(BorderFactory
                .createMatteBorder(1, 1, 1, 1, new Color(0, 125, 204)), "Solchecker"));

        solcheckerInformationList = Tools.extractInformationFromPluginFile(this.getClass().getResource("/plugin.xml").getFile());

        // Initialisation of the variant label and combobox
        variantLabel = new JLabel("Variant :");
        this.add(variantLabel);

        variantComboBox = new JComboBox<String>();
        updateVariantCombobox();
        variantComboBox.addActionListener(this);
        this.add(variantComboBox);

        // Initialisation of the dataset label and combobox
        datasetLabel = new JLabel("Dataset :");
        this.add(datasetLabel);

        datasetComboBox = new JComboBox<String>();
        updateDatasetCombobox();
        datasetComboBox.addActionListener(this);
        this.add(datasetComboBox);

        // Initialisation of the solchecker label and combobox
        solcheckerLabel = new JLabel("Solchecker :");
        this.add(solcheckerLabel);

        solcheckerComboBox = new JComboBox<String>();
        updateSolchekerCombobox();
        solcheckerComboBox.addActionListener(this);
        this.add(solcheckerComboBox);
    }

    /**
     *
     */
    public void updateSolcheckerList() {
        solcheckerInformationList = Tools.extractInformationFromPluginFile(this.getClass().getResource("/plugin.xml").getFile());
    }

    /**
     *
     */
    public void updateVariantCombobox() {
        HashSet<String> variantSet = new HashSet<String>();
        for (SolcheckerInformation solcheckerInformation : solcheckerInformationList) {
            variantSet.addAll(solcheckerInformation.getVariants());
        }

        // Sort
        List sortVariant = new ArrayList(variantSet);
        Collections.sort(sortVariant);

        variantComboBox.setModel(new DefaultComboBoxModel(new Vector(sortVariant)));
    }

    /**
     *
     */
    public void updateDatasetCombobox() {
        if (variantComboBox.getSelectedItem() != null) {
            String variantSelected = variantComboBox.getSelectedItem().toString();

            HashSet<String> datasetSet = new HashSet<String>();
            for (SolcheckerInformation solcheckerInformation : solcheckerInformationList) {
                datasetSet.addAll(solcheckerInformation.getDatasets(variantSelected));
            }

            // Sort
            List sortDataset = new ArrayList(datasetSet);
            Collections.sort(sortDataset);

            datasetComboBox.setModel(new DefaultComboBoxModel(new Vector(sortDataset)));
        }
    }

    /**
     *
     */
    public void updateSolchekerCombobox() {
        if (variantComboBox.getSelectedItem() != null) {
            if (datasetComboBox.getSelectedItem() != null) {
                String variantSelected = variantComboBox.getSelectedItem().toString();
                String datasetSelected = datasetComboBox.getSelectedItem().toString();

                HashSet<String> solcheckerSet = new HashSet<String>();
                for (SolcheckerInformation solcheckerInformation : solcheckerInformationList) {
                    if (solcheckerInformation.hasVariantDatasetCombinaison(variantSelected, datasetSelected)) {
                        solcheckerSet.add(solcheckerInformation.getArtifactID());
                    }
                }

                // Sort
                List sortSolchecker = new ArrayList(solcheckerSet);
                Collections.sort(sortSolchecker);

                solcheckerComboBox.setModel(new DefaultComboBoxModel(new Vector(sortSolchecker)));
            }
        }
    }
    
    /**
     * 
     * @return 
     */
    public String getSolcheckerSelectedPath() {
        if (solcheckerComboBox.getSelectedItem() != null) {
            String selectedSolchecker = solcheckerComboBox.getSelectedItem().toString();
            for (SolcheckerInformation solchecker : solcheckerInformationList) {
                if (solchecker.getArtifactID().compareTo(selectedSolchecker) == 0) {
                    if(solchecker.getJarPath().startsWith("/")){
                        return solchecker.getJarPath().substring(1);
                    } else {
                        return solchecker.getJarPath();
                    }
                }
            }
        }
        return null;
    }
    
    /**
     * 
     * @return 
     */
    public String getSolcheckerSelectedClass() {
        if (solcheckerComboBox.getSelectedItem() != null) {
            String selectedSolchecker = solcheckerComboBox.getSelectedItem().toString();
            for (SolcheckerInformation solchecker : solcheckerInformationList) {
                if (solchecker.getArtifactID().compareTo(selectedSolchecker) == 0) {
                    return solchecker.getSolcheckerClassName();
                }
            }
        }
        return null;
    }
    
    public SolcheckerInformation getSolcheckerInformation(){
        if(solcheckerComboBox.getSelectedItem() != null){
            String selectedSolchecker = solcheckerComboBox.getSelectedItem().toString();
            for (SolcheckerInformation solchecker : solcheckerInformationList) {
                if (solchecker.getArtifactID().compareTo(selectedSolchecker) == 0) {
                    return solchecker;
                }
            }
        }
        
        return null;
    }
    
    public String getVariantSelected(){
        if(variantComboBox.getSelectedItem() != null){
            return variantComboBox.getSelectedItem().toString();
        }
        
        return null;
    }

    /**
     *
     * @param e
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == variantComboBox) {
            updateDatasetCombobox();
            updateSolchekerCombobox();
        } else if (e.getSource() == datasetComboBox) {
            updateSolchekerCombobox();
        }
    }
}

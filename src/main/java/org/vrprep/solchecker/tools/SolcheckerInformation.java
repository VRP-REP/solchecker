package org.vrprep.solchecker.tools;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Vector;
import org.jdom.Element;

/**
 *
 */
public class SolcheckerInformation {

    private String jarPath;
    private String solcheckerClassName;
    
    private String artifactID;
    private String groupID;
    private String version;

    HashSet<String> variants;
    HashMap<String, HashSet<String>> variantDatasets;
    HashMap<String, SolcheckerConfiguration> variantConfiguration;

    public SolcheckerInformation() {
        this.jarPath = null;
        this.solcheckerClassName = null;
        this.artifactID = null;
        this.groupID = null;
        this.version = null;

        this.variants = new HashSet<String>();
        this.variantDatasets = new HashMap<String, HashSet<String>>();
        this.variantConfiguration = new HashMap<String, SolcheckerConfiguration>();
    }

    public SolcheckerInformation(String path, String solcheckerClass, String artifactID, String groupID, String version) {
        this.jarPath = path;
        this.solcheckerClassName = solcheckerClass;
        this.artifactID = artifactID;
        this.groupID = groupID;
        this.version = version;

        this.variants = new HashSet<String>();
        this.variantDatasets = new HashMap<String, HashSet<String>>();
        this.variantConfiguration = new HashMap<String, SolcheckerConfiguration>();
    }

    public SolcheckerInformation(String path, Element solcheckerElement) {
        this.jarPath = path;
        this.solcheckerClassName = solcheckerElement.getChild("solchecker-class-name").getText();
        
        this.artifactID = (solcheckerElement.getChild("artifactID") != null)?solcheckerElement.getChild("artifactID").getText():null;
        this.groupID = (solcheckerElement.getChild("groupId") != null)?solcheckerElement.getChild("groupId").getText():null;
        this.version = (solcheckerElement.getChild("version") != null)?solcheckerElement.getChild("version").getText():null;

        this.variants = new HashSet<String>();
        this.variantDatasets = new HashMap<String, HashSet<String>>();
        this.variantConfiguration = new HashMap<String, SolcheckerConfiguration>();

        // 
        for (Object variant : solcheckerElement.getChild("variants").getChildren("variant")) {
            Element variantElement = (Element) variant;
            
            String variantName = variantElement.getAttributeValue("name");
            this.addVariant(variantName);

            // Récupération des datasets de la variante
            for (Object dataset : variantElement.getChild("datasets").getChildren("dataset")) {
                Element datasetElement = (Element) dataset;
                this.addDataset(variantName, datasetElement.getText());
            }

            // Récupération de la configuration de la variante
            Element configurationElement = variantElement.getChild("configuration");
            SolcheckerConfiguration solcheckerConfiguration = new SolcheckerConfiguration(configurationElement);

            this.addConfiguration(variantName, solcheckerConfiguration);
        }
    }

    public String getJarPath() {
        return jarPath;
    }

    public void setJarPath(String path) {
        this.jarPath = path;
    }

    public String getSolcheckerClassName() {
        return solcheckerClassName;
    }

    public void setSolcheckerClassName(String solcheckerClass) {
        this.solcheckerClassName = solcheckerClass;
    }

    public String getArtifactID() {
        return artifactID;
    }

    public void setArtifactID(String artifactID) {
        this.artifactID = artifactID;
    }

    public String getGroupID() {
        return groupID;
    }

    public void setGroupID(String groupID) {
        this.groupID = groupID;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public HashSet<String> getVariants() {
        return variants;
    }

    public void addVariant(String variant) {
        this.variants.add(variant);
    }

    public void setVariants(HashSet<String> variants) {
        this.variants = variants;
    }

    public HashSet<String> getDatasets(String variant) {
        return variantDatasets.get(variant);
    }

    public void addDataset(String variant, String dataset) {
        if (this.variantDatasets.get(variant) == null) {
            this.variantDatasets.put(variant, new HashSet<String>());
        }

        this.variantDatasets.get(variant).add(dataset);
    }

    public void addDatasets(String variant, HashSet<String> variantDatasets) {
        this.variantDatasets.put(variant, variantDatasets);
    }

    public void setVariantDatasets(HashMap<String, HashSet<String>> variantDatasets) {
        this.variantDatasets = variantDatasets;
    }

    public SolcheckerConfiguration getConfigurations(String variant) {
        return variantConfiguration.get(variant);
    }

    public void addConfiguration(String variant, SolcheckerConfiguration variantConfiguration) {
        this.variantConfiguration.put(variant, variantConfiguration);
    }

    public void setVariantConfiguration(HashMap<String, SolcheckerConfiguration> variantConfiguration) {
        this.variantConfiguration = variantConfiguration;
    }

    /**
     *
     * @param variantName
     * @param datasetName
     * @return
     */
    public boolean hasVariantDatasetCombinaison(String variantName, String datasetName) {        
        if (variants.contains(variantName)) {
            for(String dataset : variantDatasets.get(variantName)){
                if(dataset.compareToIgnoreCase(datasetName) == 0){
                    return true;
                }
            }
        }
        return false;
    }

    public boolean compareTo(Vector<String> solcheckerInformation) {
        if (solcheckerInformation.size() == 4) {
            if ((solcheckerInformation.get(1).compareTo(artifactID) == 0)
                    && (solcheckerInformation.get(2).compareTo(groupID) == 0)
                    && (solcheckerInformation.get(3).compareTo(version) == 0)) {
                return true;
            }
        }

        return false;
    }

    public Element toXML() {
        Element solchecker = new Element("solchecker");

        Element jarPathElement = new Element("jar-path");
        jarPathElement.setText(getJarPath());
        solchecker.addContent(jarPathElement);

        Element classPathElement = new Element("solchecker-class-name");
        classPathElement.setText(getSolcheckerClassName());
        solchecker.addContent(classPathElement);

        Element artifactIdElement = new Element("artifactID");
        artifactIdElement.setText(getArtifactID());
        solchecker.addContent(artifactIdElement);

        Element groupIdElement = new Element("groupID");
        groupIdElement.setText(getGroupID());
        solchecker.addContent(groupIdElement);

        Element versionElement = new Element("version");
        versionElement.setText(getVersion());
        solchecker.addContent(versionElement);

        Element variantsElement = new Element("variants");
        solchecker.addContent(variantsElement);

        for (String variantName : getVariants()) {
            Element variantElement = new Element("variant");
            variantElement.setAttribute("name", variantName);
            variantsElement.addContent(variantElement);

            Element datasetsElement = new Element("datasets");
            for (String datasetName : getDatasets(variantName)) {
                Element datasetElement = new Element("dataset");
                datasetElement.setText(datasetName);
                datasetsElement.addContent(datasetElement);
            }
            variantElement.addContent(datasetsElement);

            Element configurationElement = this.getConfigurations(variantName).toXML();
            variantElement.addContent(configurationElement);
        }

        return solchecker;
    }
}

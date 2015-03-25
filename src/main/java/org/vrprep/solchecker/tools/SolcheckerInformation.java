package org.vrprep.solchecker.tools;

import java.util.HashMap;
import java.util.HashSet;
import org.jdom.Element;

/**
 * SolcheckerInformation class allows to contain the various specific information of
 * the solchecker (jar path, solchecker class name, artifactid, groupid, version,
 * list and configuration of variants and datasets).
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

    /**
     * Permits to initialize the SolcheckerInformation object with path, 
     * solchecker class, artifactId, groupId and version passed as parameter.
     * @param path Path to the solchecker archive.
     * @param solcheckerClass Name of the class implementing {@link SolChecker}.
     * @param artifactID ArtifactId of the solchecker.
     * @param groupID GroupId of the solchecker.
     * @param version Version of the solchecker.
     */
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

    /**
     * Permits to initialize the SolcheckerInformation object from a 
     * solchecker Element and the solchecker path.
     * @param path Path to solchecker archive.
     * @param solcheckerElement JDom Element containing artifactId, groupId, 
     * version, variants, datasets and configuration.
     */
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
        if(solcheckerElement.getChild("variants") != null){
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
     * Verifies whether the variant dataset combinaison exists in the solchecker.
     * @param variantName Name of the desired variant.
     * @param datasetName Name of the desired dataset.
     * @return TRUE if the variant dataset combinaison exists, FALSE otherwise.
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

    /**
     * Compares two solcheckers. The comparison is based on artifactId, groupId 
     * and version.
     * @param artifactId ArtifactId to compare.
     * @param groupId GroupId to compare.
     * @param version Version to compare.
     * @return TRUE if the two solcheckers are identical, FALSE otherwise.
     */
    public boolean compareTo(String artifactId, String groupId, String version) {
        return (artifactId.compareTo(this.artifactID) == 0)
                && (groupId.compareTo(this.groupID) == 0)
                && (version.compareTo(this.version) == 0);
    }

    /**
     * Permits to return a JDom Element relating to class' content.
     * @return JDom Element containing all the solchecker information.
     */
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

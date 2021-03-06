package org.vrprep.solchecker.tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import java.util.jar.JarFile;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.Namespace;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

/**
 * Tools class allows to contain the various functions for read and extract 
 * information from files.
 */
public class Tools {
    /**
     * Adds the solchecker informations to the plugin file.
     * @param pluginPath Path to the plugin file.
     * @param solcheckerInformation Solchecker information to add.
     */
    public static void addSolcheckerInformationToPluginFile(
            String pluginPath, 
            SolcheckerInformation solcheckerInformation) {
        try {
            // Creation of the root element and the document         
            Element root;
            Document doc;

            // Verify if pluginFile exist
            // If not, initialization of pluginFile structure
            File pluginFile = new File(pluginPath);
            if (!pluginFile.exists()) {
                root = new Element("solcheckers");
                doc = new Document(root);
            } else {
                SAXBuilder sxb = new SAXBuilder();
                doc = sxb.build(pluginFile);
                root = doc.getRootElement();
            }

            // Conversion of solchecker in xml 
            Element newSolchecker = solcheckerInformation.toXML();
            root.addContent(newSolchecker);

            // Save plugin file modifications
            XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
            sortie.output(doc, new FileOutputStream(pluginPath));

        } catch (Exception e) {
            System.out.println(e);
        } 
    }

    /**
     * Adds the dependency to the pom file.
     * @param pomPath Path to the pom file.
     * @param artifactId ArtifectId of the dependence to add.
     * @param groupId GroupId of the dependence to add.
     * @param version Version of the dependence to add.
     */
    public static void addDependencyToPomFile(
            String pomPath, 
            String artifactId, 
            String groupId, 
            String version) {
        try {
            // Open pom file
            SAXBuilder sxb = new SAXBuilder();
            Document doc = sxb.build(new File(pomPath));

            // Initialization of the root element
            Element root = doc.getRootElement();
            Element dependencies = root.getChild("dependencies", root.getNamespace());

            // Verify if the dependency is not already in dependencies
            boolean dependencyExist = false;
            List dependenciesChildren = dependencies.getChildren();
            for (Object dependency : dependenciesChildren) {
                Element dependencyElement = (Element) dependency;

                Element groupIdElement = dependencyElement.getChild("groupId", dependencyElement.getNamespace());
                Element artifactIdElement = dependencyElement.getChild("artifactId", dependencyElement.getNamespace());
                Element versionElement = dependencyElement.getChild("version", dependencyElement.getNamespace());

                if((groupIdElement != null) && (artifactIdElement != null) && (versionElement != null)){
                    if ((groupIdElement.getText().compareTo(groupId) == 0)
                            && (artifactIdElement.getText().compareTo(artifactId) == 0)
                            && (versionElement.getText().compareTo(version) == 0)) {
                        dependencyExist = true;
                    }
                }
            }

            if (!dependencyExist) {
                // Add new dependency on the structure
                Element newDependency = new Element("dependency", root.getNamespace());
                dependencies.addContent(newDependency);

                Element groupID = new Element("groupId", root.getNamespace());
                groupID.setText(groupId);
                newDependency.addContent(groupID);

                Element artifactID = new Element("artifactId", root.getNamespace());
                artifactID.setText(artifactId);
                newDependency.addContent(artifactID);

                Element vers = new Element("version", root.getNamespace());
                vers.setText(version);
                newDependency.addContent(vers);

                // Save modification of plugin file
                XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
                sortie.output(doc, new FileOutputStream(pomPath));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * Extracts runnable information from batch file.
     * @param batchFile Batch File containing runnable information (instance 
     * and solution file).
     * @return List of extracted runnable information.
     */
    public static List<RunnableInformation> extractRunnableInformationFromBatchFile(
            File batchFile) {
        try {
            // Verify that the batch file exists
            if (batchFile == null) {
                return null;
            }

            // Open batch file
            SAXBuilder sxb = new SAXBuilder();
            Document doc = sxb.build(batchFile);

            // Initialization of the root element
            Element root = doc.getRootElement();
            List runList = root.getChildren("run");

            // Creation of the runnable information list
            List runnableInformationList = new ArrayList<RunnableInformation>();

            // Packing of the run list
            Iterator i = runList.iterator();
            while (i.hasNext()) {
                Element run = (Element) i.next();

                runnableInformationList.add(new RunnableInformation(
                        run.getChild("solution").getText(),
                        run.getChild("instance").getText()));
            }

            return runnableInformationList;

        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    /**
     * Extracts solchecker information from plugin file.
     * @param pluginPath Path to the plugin file.
     * @return List of extracted solchecker information.
     */
    public static List<SolcheckerInformation> extractSolcheckerInformationFromPluginFile(
            String pluginPath) {
        try {
            // Open plugin file
            SAXBuilder sxb = new SAXBuilder();
            Document doc = sxb.build(new File(pluginPath));

            // Initialization of the root element
            Element root = doc.getRootElement();
            List solcheckerList = root.getChildren("solchecker");

            // Creation of the solchecker information list
            List solcheckerInformationList = new ArrayList<SolcheckerInformation>();

            // Packing of the solchecker list
            Iterator solcheckerListIterator = solcheckerList.iterator();
            while (solcheckerListIterator.hasNext()) {
                Element solchecker = (Element) solcheckerListIterator.next();

                SolcheckerInformation solcheckerInformation = new SolcheckerInformation(pluginPath, solchecker);
                solcheckerInformationList.add(solcheckerInformation);
            }

            return solcheckerInformationList;

        } catch (Exception e) {
            System.out.println(e);
            return null;
        } 
    }

    /**
     * Extracts the name of the jar archives in the folder.
     * @param folderPath Path to the folder to explore.
     * @return List of extracted archive name.
     */
    public static List<String> extractJARFromFolder(
            String folderPath) {
        List<String> jarFiles = new ArrayList<String>();

        String[] folder = new java.io.File(folderPath).list();
        for (String file : folder) {
            if (file.endsWith(".jar")) {
                jarFiles.add(file);
            }
        }

        return jarFiles;
    }

    /**
     * Extracts solchecker jar path, artifactId, groupId and version from the 
     * jar archive.
     * @param jarPath Path to the jar archive.
     * @return List containing jar path, artifactId, groupId and version of the 
     * solchecker.
     */
    public static Vector<String> extractInformationFromJAR(
            String jarPath) {
        try {
            SAXBuilder sxb = new SAXBuilder();
            JarFile jarFile = new JarFile(jarPath);

            // pom path recovery  
            if (jarFile.getEntry("configuration.xml") == null) {
                return null;
            }
            InputStream informationFile = jarFile.getInputStream(jarFile.getEntry("configuration.xml"));
            Document docInfo = sxb.build(informationFile);
            Element rootInfo = docInfo.getRootElement();

            String pomPath = rootInfo.getChildText("solchecker-pom-path");

            // Open pom.xml       
            InputStream pomFile = jarFile.getInputStream(jarFile.getEntry(pomPath));

            // Initialization of the root element and the document
            Document doc = sxb.build(pomFile);
            Element root = doc.getRootElement();
            Namespace namespace = root.getNamespace();

            // Creation of the solcheckerInformation vector
            Vector<String> solcheckerInformation = new Vector<String>();
            solcheckerInformation.add(jarPath);
            solcheckerInformation.add(root.getChildText("artifactId", namespace));
            solcheckerInformation.add(root.getChildText("groupId", namespace));
            solcheckerInformation.add(root.getChildText("version", namespace));

            return solcheckerInformation;

        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    /**
     * Extracts solchecker information from the jar archive.
     * @param jarPath Path to the jar archive.
     * @return Instance of {@link SolcheckerInformation} containing all the 
     * information read on the jar archive.
     */
    public static SolcheckerInformation extractSolcheckerInformationFromJAR(
            String jarPath) {
        try {
            // Recovery archive
            SAXBuilder sxb = new SAXBuilder();
            JarFile jarFile = new JarFile(jarPath);

            // Recovery configuration file in archive
            if (jarFile.getEntry("configuration.xml") == null) {
                return null;
            }
            InputStream configurationFile = jarFile.getInputStream(jarFile.getEntry("configuration.xml"));
            Document docInfo = sxb.build(configurationFile);
            Element solcheckerElement = docInfo.getRootElement();

            SolcheckerInformation solcheckerInformation = new SolcheckerInformation(jarPath, solcheckerElement);

            // Recovery pom.xml file
            String pomPath = solcheckerElement.getChildText("solchecker-pom-path");
            InputStream pomFile = jarFile.getInputStream(jarFile.getEntry(pomPath));
            Document docPom = sxb.build(pomFile);
            Element rootPom = docPom.getRootElement();
            Namespace namespace = rootPom.getNamespace();

            // Recovery plugin information (artifactId, groupId & version)
            solcheckerInformation.setArtifactID(rootPom.getChildText("artifactId", namespace));
            solcheckerInformation.setGroupID(rootPom.getChildText("groupId", namespace));
            solcheckerInformation.setVersion(rootPom.getChildText("version", namespace));

            return solcheckerInformation;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    /**
     * Executes the commande in parameter.
     * @param commande Commande to execute.
     * @return TRUE if the command was executed, FALSE otherwise.
     */
    public static boolean executeCommande(String commande) {
        try {
            boolean executionOk = true;
            String line = "";

            Process p = Runtime.getRuntime().exec(commande);
            BufferedReader output = new BufferedReader(new InputStreamReader(p.getInputStream()));
            BufferedReader error = new BufferedReader(new InputStreamReader(p.getErrorStream()));

            while ((line = output.readLine()) != null) {
                System.out.println(line);
            }

            while ((line = error.readLine()) != null) {
                System.out.println(line);
                executionOk = false;
            }

            p.waitFor();

            return executionOk;

        } catch (Exception e) {
            System.out.println(e);
            return false;
        } 
    }
}

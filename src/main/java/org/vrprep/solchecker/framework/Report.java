package org.vrprep.solchecker.framework;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

/**
 * Defines the report object. A report is the list of evaluation result.
 */
public final class Report {
    /**
     * List of evaluations to be made to build report.
     */
    private List<Evaluation> evaluations = new ArrayList<Evaluation>();
    
    private String instanceName = "";
    private String solutionName = "";
    
    /**
     * Build a report with the name of the solution and the instance.
     * @param solutionName Name of the solution
     * @param instanceName Name of the instance
     */
    public Report(String solutionName, String instanceName){
        this.solutionName = solutionName;
        this.instanceName = instanceName;
    }

    public void addEvaluation(Evaluation e) {
        this.evaluations.add(e);
    }

    public List<Evaluation> getEvaluations() {
        return evaluations;
    }
    
    public void setInstanceName(String name){
        this.instanceName = name;
    }
    
    public String getInstanceName(){
        return this.instanceName;
    }
    
    public void setSolutionName(String name){
        this.solutionName = name;
    }
    
    public String getSolutionName(){
        return this.solutionName;
    }
    
    /**
     * Exports the content of evaluations to xml file.
     * @param f Output file.
     */
    public void exportToXML(File f){
        try {
            Element root = new Element("report");
            root.setAttribute("instance", instanceName);
            root.setAttribute("solution", solutionName);
            Document doc = new Document(root);
            
            for(Evaluation evaluation : evaluations){
                root.addContent(evaluation.getXML());
            }
            
            XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
            sortie.output(doc, new FileOutputStream(f));
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    
    /**
     * Transforms the report to String.
     * @return The concatenation of the instance name and the solution name.
     */
    public String toString(){
        return instanceName + " - " + solutionName;
    }
}

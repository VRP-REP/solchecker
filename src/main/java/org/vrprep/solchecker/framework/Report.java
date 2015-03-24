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

    public void addEvaluation(Evaluation e) {
        this.evaluations.add(e);
    }

    public List<Evaluation> getEvaluations() {
        return evaluations;
    }
    
    /**
     * Exports the content of evaluations in xml file.
     * @param f the output file
     */
    public void exportToXML(File f){
        try {
            Element root = new Element("report");
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
}

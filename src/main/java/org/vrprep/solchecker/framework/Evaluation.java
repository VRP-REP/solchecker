package org.vrprep.solchecker.framework;

import java.util.ArrayList;
import java.util.List;
import org.jdom.*;

/**
 * Defines the interface for evaluations. An evaluation is the result of the
 * evaluation of a constraint, an objective, or any other metric.
 */
public abstract class Evaluation {
    /**
     * Stores the messages send by the constraint evaluator
     */
    private List<String> messages;

    /**
     * The name of the evaluated element
     */
    private String name;

    public Evaluation(String name) {
        this.name = name;
        this.messages = new ArrayList<String>();
    }

    public List<String> getMessages() {
        return messages;
    }

    /**
     * Adds a new message to the evaluation
     * @param message Message to add.
     */
    public void logMessage(String message) {
        if (this.messages == null) {
            this.messages = new ArrayList<String>();
        }
        this.messages.add(message);
    }

    public String getName() {
        return name;
    }

    /**
     * Returns the xml element relating to the evaluation.
     * @return Element containing all the information of the evaluation.
     */
    public final Element getXML() {
        Element result = this.toXML();
        result.setAttribute("name", this.name);
        for (String s : messages) {
            Element e = new Element("message");
            e.setText(s);
            result.addContent(e);
        }
        return result;
    }

    /**
     * Builds an xml version of the evaluation.
     * @return Element containing all the information of the evaluation.
     */
    protected abstract Element toXML();
}

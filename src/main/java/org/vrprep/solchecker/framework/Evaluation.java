package org.vrprep.solchecker.framework;

import java.util.ArrayList;
import java.util.List;
import org.jdom.*;

/**
 * Defines the interface for evaluations. An evaluation is the result of the
 * evaluation of a constraint, an objective, or any other metric.
 *
 * @author Jorge E. Mendoza (dev@jorge-mendoza.com)
 * @version %I%, %G%
 * @since Feb 5, 2015
 */
public abstract class Evaluation {

    /**
     * stores the messages send by the constraint evaluator
     */
    private List<String> messages;

    /**
     * the name of the evaluated element
     */
    private String name;

    public Evaluation(String name) {
        this.name = name;
        this.messages = new ArrayList<String>();
    }

    public List<String> getMessages() {
        return messages;
    }

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
     *
     * @return xml element
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
     *
     * @return xml element
     */
    protected abstract Element toXML();
}

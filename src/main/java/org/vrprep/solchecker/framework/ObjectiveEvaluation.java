package org.vrprep.solchecker.framework;

import org.jdom.Element;

/**
 * Defines the result of a objective function evaluation.
 *
 * @author Jorge E. Mendoza (dev@jorge-mendoza.com)
 * @version %I%, %G%
 * @since Feb 5, 2015
 *
 */
public class ObjectiveEvaluation extends Evaluation {

    /**
     * the result of the objective function.
     */
    private double value;

    public ObjectiveEvaluation(String name) {
        super(name);
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    /**
     * Builds an xml version of the objective function evaluation.
     *
     * @return xml element
     */
    @Override
    protected Element toXML() {
        Element e = new Element("objective_evaluation");
        Element v = new Element("value");
        v.setText(String.valueOf(this.value));
        e.addContent(v);
        return e;
    }
}

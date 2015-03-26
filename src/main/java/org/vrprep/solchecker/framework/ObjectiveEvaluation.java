package org.vrprep.solchecker.framework;

import org.jdom.Element;

/**
 * Defines the result of a objective function evaluation.
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
    
    @Override
    protected Element toXML() {
        Element e = new Element("objective_evaluation");
        Element v = new Element("value");
        v.setText(String.valueOf(this.value));
        e.addContent(v);
        return e;
    }
}

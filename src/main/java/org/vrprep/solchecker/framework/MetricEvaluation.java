package org.vrprep.solchecker.framework;

import org.jdom.Element;

/**
 * Defines the result of a metric evaluation
 */
public class MetricEvaluation extends Evaluation {
    
    private double value;

    public MetricEvaluation(String name) {
        super(name);
    }
    
    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    /**
     * Builds an xml version of the metric evaluation.
     *
     * @return xml element
     */
    @Override
    protected Element toXML() {
        Element e = new Element("metric_evaluation");
        Element v = new Element("value");
        v.setText(String.valueOf(this.value));
        e.addContent(v);
        return e;
    }
}
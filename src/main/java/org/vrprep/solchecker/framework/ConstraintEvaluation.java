package org.vrprep.solchecker.framework;

import org.jdom.Element;

/**
 * Defines the result of a constraint evaluation.
 */
public class ConstraintEvaluation extends Evaluation {
    /**
     * TRUE if the constraint is satisfied, false otherwise
     */
    private boolean feasible;

    public ConstraintEvaluation(String name) {
        super(name);
    }

    public boolean isFeasible() {
        return feasible;
    }

    public void setFeasible(boolean feasible) {
        this.feasible = feasible;
    }

    /**
     * Builds an xml version of the constraint evaluation.
     * @return xml element
     */
    @Override
    protected Element toXML() {
        Element e = new Element("constraint_evaluation");
        Element f = new Element("feasible");
        f.setText(feasible == true ? "true" : "false");
        e.addContent(f);
        return e;
    }
}

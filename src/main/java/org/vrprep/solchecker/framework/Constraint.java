package org.vrprep.solchecker.framework;

/**
 * Defines the interface for constraints.
 */
public interface Constraint {

    /**
     * Allows to evaluate the feasability of a constraint.
     * @param s Solution to evaluate.
     * @param i Instance reffered by the solution.
     * @return ConstraintEvaluation object containing the feasability of the
     * constraint and log messages.
     */
    public ConstraintEvaluation evaluate(Solution s, Instance i);
}

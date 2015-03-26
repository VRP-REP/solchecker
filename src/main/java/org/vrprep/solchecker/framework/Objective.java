package org.vrprep.solchecker.framework;

/**
 * Defines the interface for objectives functions.
 */
public interface Objective {
    /**
     * Allows to evaluate the value of an objective function.
     * @param s Solution to evaluate.
     * @param i Instance reffered by the solution.
     * @return ObjectiveEvaluation object containing the value of the 
     * objective function and log messages.
     */
    public ObjectiveEvaluation evaluate(Solution s, Instance i);

}

package org.vrprep.solchecker.framework;

/**
 * Defines the interface for objectives functions.
 */
public interface Objective {

    /**
     * Allows to evaluate the value of an objective function.
     * @param s : the solution to evaluate.
     * @param i : the instance reffered by the solution.
     * @return : ObjectiveEvaluation object containing the value of the 
     * objective function and log messages.
     */
    public ObjectiveEvaluation evaluate(Solution s, Instance i);

}

package org.vrprep.solchecker.framework;

/**
 * Defines the interface for metrics.
 */
public interface Metric {
    /**
     * Allows to evaluate a metrics.
     * @param s Solution to evaluate.
     * @param i Instance reffered by the solution.
     * @return Evaluation object containing log messages.
     */
    public Evaluation evaluate(Solution s, Instance i);

}

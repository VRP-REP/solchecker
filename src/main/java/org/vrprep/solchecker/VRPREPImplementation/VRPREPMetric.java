package org.vrprep.solchecker.VRPREPImplementation;

import org.vrprep.solchecker.framework.Metric;
import org.vrprep.solchecker.framework.MetricEvaluation;
import org.vrprep.solchecker.framework.Instance;
import org.vrprep.solchecker.framework.Solution;

/**
 * Implementation of Metric for VRP-REP SolChecker.
 */
public abstract class VRPREPMetric implements Metric{

    /**
     * Allows to evaluate the feasability of a metric.
     * @param s Solution to evaluate.
     * @param i Instance reffered by the solution.
     * @return MetricEvaluation object containing log messages.
     */
    public final MetricEvaluation evaluate(Solution s, Instance i) {
        this.setup(s, i);
        return this.checkMetric();
    }
    
    /**
     * Permits to extract useful informations from solution and instance. This 
     * information will be used to check the metric.
     * @param s Solution to evaluate.
     * @param i Instance reffered by the solution.
     */
    public abstract void setup(Solution s, Instance i);
    
    /**
     * Checks if the constraint is respected and built the resultant 
     * {@link MetricEvaluation}.
     * @return MetricEvaluation object containing log messages.
     */
    public abstract MetricEvaluation checkMetric();
    
}

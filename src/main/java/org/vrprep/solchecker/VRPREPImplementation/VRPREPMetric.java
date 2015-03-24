package org.vrprep.solchecker.VRPREPImplementation;

import org.vrprep.solchecker.framework.Metric;
import org.vrprep.solchecker.framework.MetricEvaluation;
import org.vrprep.solchecker.framework.Instance;
import org.vrprep.solchecker.framework.Solution;

/**
 * 
 */
public abstract class VRPREPMetric implements Metric{

    public final MetricEvaluation evaluate(Solution s, Instance i) {
        this.setup(s, i);
        return this.checkMetric();
    }
    
    public abstract void setup(Solution s, Instance i);
    
    public abstract MetricEvaluation checkMetric();
    
}

package org.vrprep.solchecker.VRPREPImplementation;

import org.vrprep.solchecker.framework.Objective;
import org.vrprep.solchecker.framework.ObjectiveEvaluation;
import org.vrprep.solchecker.framework.Instance;
import org.vrprep.solchecker.framework.Solution;

/**
 * Implementation of Objective for VRP-REP SolChecker.
 */
public abstract class VRPREPObjective implements Objective{

    /**
     * Allows to calculate the value of an objective.
     * @param s Solution to evaluate.
     * @param i Instance reffered by the solution.
     * @return ObjectiveEvaluation object containing the value of the
     * objective and log messages.
     */
    public final ObjectiveEvaluation evaluate(Solution s, Instance i) {
        this.setup(s, i);
        return this.checkObjective();
    }
    
    /**
     * Permits to extract useful informations from solution and instance. This 
     * information will be used to calculate the objective.
     * @param s Solution to evaluate.
     * @param i Instance reffered by the solution.
     */
    public abstract void setup(Solution s, Instance i);
    
    /**
     * Calculate the value of the objective and build the resultant evaluation 
     * {@link ObjectiveEvaluation}.
     * @return ObjectiveEvaluation object containing the value of the
     * objective and log messages.
     */
    public abstract ObjectiveEvaluation checkObjective();
    
}

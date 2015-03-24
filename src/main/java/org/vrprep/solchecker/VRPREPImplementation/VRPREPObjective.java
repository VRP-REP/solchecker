package org.vrprep.solchecker.VRPREPImplementation;

import org.vrprep.solchecker.framework.Objective;
import org.vrprep.solchecker.framework.ObjectiveEvaluation;
import org.vrprep.solchecker.framework.Instance;
import org.vrprep.solchecker.framework.Solution;

/**
 * 
 */
public abstract class VRPREPObjective implements Objective{

    public final ObjectiveEvaluation evaluate(Solution s, Instance i) {
        this.setup(s, i);
        return this.checkObjective();
    }
    
    public abstract void setup(Solution s, Instance i);
    
    public abstract ObjectiveEvaluation checkObjective();
    
}

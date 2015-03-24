package org.vrprep.solchecker.VRPREPImplementation;

import org.vrprep.solchecker.framework.Constraint;
import org.vrprep.solchecker.framework.ConstraintEvaluation;
import org.vrprep.solchecker.framework.Instance;
import org.vrprep.solchecker.framework.Solution;

/**
 * Implementation of Constraint for VRP-REP SolChecker
 */
public abstract class VRPREPConstraint implements Constraint{

    /**
     * Allows to evaluate the feasability of a constraint.
     * @param s Solution to evaluate.
     * @param i Instance reffered by the solution.
     * @return ConstraintEvaluation object containing the feasability of the
     * constraint and log messages.
     */
    public final ConstraintEvaluation evaluate(Solution s, Instance i) {
        this.setup(s, i);
        return this.checkConstraint();
    }
    
    /**
     * Permits to extract useful informations from solution and instance. This 
     * information will be used to check the constraint.
     * @param s Solution to evaluate.
     * @param i Instance reffered by the solution.
     */
    public abstract void setup(Solution s, Instance i);
    
    /**
     * Checks if the constraint is respected and built the resultant 
     * {@link ConstraintEvaluation}.
     * @return ConstraintEvaluation object containing the feasability of the
     * constraint and log messages.
     */
    public abstract ConstraintEvaluation checkConstraint();
    
}

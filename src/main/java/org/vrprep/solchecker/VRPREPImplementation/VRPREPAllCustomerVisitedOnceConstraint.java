package org.vrprep.solchecker.VRPREPImplementation;

import java.util.List;
import org.jdom.Element;
import org.vrprep.solchecker.framework.ConstraintEvaluation;
import org.vrprep.solchecker.framework.Instance;
import org.vrprep.solchecker.framework.Solution;
import org.vrprep.solchecker.tools.StopChecker;

/**
 * Implementation of the constraint "all customer visited only once time"
 */
public class VRPREPAllCustomerVisitedOnceConstraint extends VRPREPConstraint {

    protected List<List<Integer>> routesList;
    protected int[] customersList;
    protected List<Integer> depots;

    @Override
    public void setup(Solution s, Instance i) {
        VRPREPInstance instance = (VRPREPInstance) i;
        VRPREPSolution solution = (VRPREPSolution) s;

        // Recovery of the routes list
        routesList = solution.getRoutesList();
        
        // Recovery of the customers list
        customersList = new int[instance.getNodes().size()];
        
        int cpt = 0;
        for(Object node : instance.getNodes()){
            Element nodeElement = (Element) node;
            customersList[cpt] = Integer.parseInt(nodeElement.getAttribute("id").getValue());
            ++cpt;
        }
        
        // Default depot identifier (idDepot = 0)
        depots.add(0);
    }

    @Override
    public ConstraintEvaluation checkConstraint() {
        ConstraintEvaluation constraintEvaluation = new ConstraintEvaluation("all_customers_visited");
        constraintEvaluation.setFeasible(StopChecker.checkStops(1, 1, customersList, routesList, depots));

        return constraintEvaluation;
    }
}
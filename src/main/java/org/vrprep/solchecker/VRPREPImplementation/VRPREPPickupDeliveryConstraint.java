package org.vrprep.solchecker.VRPREPImplementation;

import java.util.ArrayList;
import java.util.HashMap;
import org.vrprep.solchecker.framework.ConstraintEvaluation;
import org.vrprep.solchecker.framework.Instance;
import org.vrprep.solchecker.framework.Solution;
import org.vrprep.solchecker.tools.PickupDeliveryChecker;

/**
 * 
 */
public class VRPREPPickupDeliveryConstraint extends VRPREPConstraint{
    private ArrayList<ArrayList<Integer>> routes;
    private HashMap<Integer, ArrayList<Integer>> pickupDeliveryList;

    @Override
    public void setup(Solution s, Instance i) {
        VRPREPInstance instance = (VRPREPInstance) i;
        VRPREPSolution solution = (VRPREPSolution) s;
        
        // Routes
        routes = solution.getRoutesList();
        
        // PickUp Delivery
        
    }

    @Override
    public ConstraintEvaluation checkConstraint() {
        ConstraintEvaluation constraintEvaluation = new ConstraintEvaluation("pickup_delivery_constraint");
        
        for(ArrayList<Integer> route : routes){
            boolean feasability = PickupDeliveryChecker.checkPickupDelivery(route, pickupDeliveryList);
            
            if(!feasability){
                constraintEvaluation.logMessage("Route " + routes.indexOf(route) + " has a pickup-delivery problem");
                constraintEvaluation.setFeasible(false);
            }
        }
        
        return constraintEvaluation;
    }
}

package org.vrprep.solchecker.VRPREPImplementation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jdom.Element;
import org.vrprep.solchecker.framework.ConstraintEvaluation;
import org.vrprep.solchecker.framework.Instance;
import org.vrprep.solchecker.framework.Solution;
import org.vrprep.solchecker.tools.CapacityChecker;

/**
 * Implementation of the constraint "the total customer requests shall not 
 * exceed the total capacity of the vehicle"
 */
public class VRPREPCapacityConstraint extends VRPREPConstraint{
    protected List<List<Integer>> routes;
    protected Map<Integer, Double> demands;
    protected double capacity;

    @Override
    public void setup(Solution s, Instance i) {
        VRPREPInstance instance = (VRPREPInstance) i;
        VRPREPSolution solution = (VRPREPSolution) s;
        
        routes = solution.getRoutesList();
        
        // Demands
        demands = new HashMap<Integer, Double>();
        
        for(Object request : instance.getRequests().getChildren()){
            Element requestElement = (Element) request;
            
            int numNode = Integer.parseInt(requestElement.getAttribute("node").getValue());
            double quantity = Double.valueOf(requestElement.getChildText("quantity"));
            
            demands.put(numNode, quantity);
        }
        
        // Capacity
        capacity = Double.valueOf(instance.getFleet().getChild("vehicle_profile").getChildText("capacity"));
    }

    @Override
    public ConstraintEvaluation checkConstraint() {
        ConstraintEvaluation constraintEvaluation = new ConstraintEvaluation("capacity_constraint");
        
        for(List<Integer> route : routes){
            boolean feasability = CapacityChecker.checkCapacity(route, demands, capacity);
            
            if(!feasability){
                constraintEvaluation.logMessage("Route " + routes.indexOf(route) + " is not feasible");
                constraintEvaluation.setFeasible(false);
            }
        }
        
        return constraintEvaluation;
    }
    
}

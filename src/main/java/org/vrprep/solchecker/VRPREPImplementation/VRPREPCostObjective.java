package org.vrprep.solchecker.VRPREPImplementation;

import java.util.ArrayList;
import java.util.HashMap;
import org.jdom.Element;
import org.vrprep.solchecker.framework.Instance;
import org.vrprep.solchecker.framework.ObjectiveEvaluation;
import org.vrprep.solchecker.framework.Solution;
import org.vrprep.solchecker.tools.DistanceCalculator;

/**
 * Implementation of the constraint "all customer visited only once time"
 */
public class VRPREPCostObjective extends VRPREPObjective {

    private ArrayList<ArrayList<Integer>> routesList;
    private HashMap<Integer, Double[]> customersCordinatesList;

    @Override
    public void setup(Solution s, Instance i) {
        VRPREPInstance instance = (VRPREPInstance) i;
        VRPREPSolution solution = (VRPREPSolution) s;

        // Recovery of the routes list
        routesList = solution.getRoutesList();
        
        // Recovery of the customers cordinates list  
        customersCordinatesList = new HashMap<Integer, Double[]>();
        for(Object node : instance.getNodes()){
            Element nodeElement = (Element) node;
            
            int nodeId = Integer.parseInt(nodeElement.getAttribute("id").getValue());
            
            int nbCordiantes = nodeElement.getChildren().size();
            Double [] nodeCordiantes = new Double[nbCordiantes];
            nodeCordiantes[0] = Double.parseDouble(nodeElement.getChildText("cx"));
            nodeCordiantes[1] = Double.parseDouble(nodeElement.getChildText("cy"));
            if(nbCordiantes > 2)
                nodeCordiantes[2] = Double.parseDouble(nodeElement.getChildText("cz"));
            
            customersCordinatesList.put(nodeId, nodeCordiantes);
        }
    }

    @Override
    public ObjectiveEvaluation checkObjective() {
        ObjectiveEvaluation objectiveEvaluation = new ObjectiveEvaluation("cost_function");
        
        double cost = 0;
        for(ArrayList<Integer> route : routesList){
            cost += DistanceCalculator.euclideanDistance(route, customersCordinatesList);
        }
        
        objectiveEvaluation.setValue(cost);

        return objectiveEvaluation;
    }
}
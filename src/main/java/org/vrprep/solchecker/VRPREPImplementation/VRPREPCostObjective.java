package org.vrprep.solchecker.VRPREPImplementation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jdom.Element;
import org.vrprep.solchecker.framework.Instance;
import org.vrprep.solchecker.framework.ObjectiveEvaluation;
import org.vrprep.solchecker.framework.Solution;
import org.vrprep.solchecker.tools.DistanceCalculator;

/**
 * Implementation of the objective "calculate the total cost of the solution"
 */
public class VRPREPCostObjective extends VRPREPObjective {

    private List<List<Integer>> routesList;
    private Map<Integer, Double[]> customersCordinatesList;

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
            
            if(nodeElement.getAttribute("id") != null 
                    && nodeElement.getChildren().size() > 0
                    && nodeElement.getChild("cx") != null
                    && nodeElement.getChild("cy") != null){
                
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
    }

    @Override
    public ObjectiveEvaluation checkObjective() {
        ObjectiveEvaluation objectiveEvaluation = new ObjectiveEvaluation("cost_function");
        
        double cost = 0;
        for(List<Integer> route : routesList){
            cost += DistanceCalculator.euclideanDistanceOnRoute(route, customersCordinatesList);
        }
        
        objectiveEvaluation.setValue(cost);

        return objectiveEvaluation;
    }
}
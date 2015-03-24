package org.vrprep.solchecker.tools;

import java.util.ArrayList;

public class StartEndDepotChecker {
    
    /**
     * Checks if all routes start and end at the depot.
     * @param routes List of routes. Each route contains all the customers' identifiers visited during the route
     * @param depotId Identifier of the depot
     * @return TRUE if all routes start and end at the depot
     * FALSE otherwise
     */
    public static boolean checkStartEndDepot(ArrayList<ArrayList<Integer>> routes, int depotId){
        for(ArrayList<Integer> route : routes){
            if(route.get(0) != depotId || route.get(route.size() - 1) != depotId){
                return false;
            }
        }
        
        return true;
    }
}

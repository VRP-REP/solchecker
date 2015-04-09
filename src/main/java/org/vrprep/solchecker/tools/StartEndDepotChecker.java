package org.vrprep.solchecker.tools;

import java.util.List;

/**
 * StartEndDepotChecker class provides various functions for verify that the route 
 * starts and finishes with depot.
 */
public class StartEndDepotChecker {    
    /**
     * Checks if all routes start and end at the depot.
     * @param routes List of routes. Each route contains all the customers' identifiers visited during the route
     * @param idDepot Identifier of the depot
     * @return TRUE if all routes start and end at the depot
     * FALSE otherwise
     */
    public static boolean checkStartEndDepot(List<List<Integer>> routes, int idDepot){
        for(List<Integer> route : routes){
            if(route.get(0) != idDepot || route.get(route.size() - 1) != idDepot){
                return false;
            }
        }
        
        return true;
    }
}

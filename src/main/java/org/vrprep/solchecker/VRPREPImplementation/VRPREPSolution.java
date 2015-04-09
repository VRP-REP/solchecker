package org.vrprep.solchecker.VRPREPImplementation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.vrprep.solchecker.framework.Solution;

/**
 * Implementation of Solution for VRP-REP Solchecker. VRPREPSolution used to 
 * model a basic solution containing a map of routes.
 */
public class VRPREPSolution implements Solution{
    private final Map<Integer, List<Integer>> routes;
    
    public VRPREPSolution(){
        routes = new HashMap<Integer, List<Integer>>();
    }
    
    /**
     * Adds a route to the map.
     * @param idRoute Route identifier.
     * @param customers List of customers for the route.
     */
    public void addRoute(int idRoute, List<Integer> customers){
        routes.put(idRoute, customers);
    }
    
    /**
     * Returns the map of routes.
     * @return the map of routes.
     */
    public Map<Integer, List<Integer>> getRoutes(){
        return routes;
    }
    
    /**
     * Returns the list of routes.
     * @return the list of routes.
     */
    public List<List<Integer>> getRoutesList(){
        List<List<Integer>> routesList = new ArrayList<List<Integer>>();
        for(Integer numRoute : routes.keySet()){
            routesList.add(routes.get(numRoute));
        }
        return routesList;
    }
    
    /**
     * Returns the list of customers for the route.
     * @param idRoute Route identifier.
     * @return if the route exists, then the list of customers for the route, 
     * otherwise, null.
     */
    public List<Integer> getCustomers(int idRoute){
        return routes.get(idRoute);
    }

    /**
     * Returns the first customer for the route.
     * @param idRoute Route identifier.
     * @return if the route exists, then the first customer on the map, 
     * otherwise, -1.
     */
    public int getFirstCustomer(int idRoute) {
        if(routes.get(idRoute) != null)
            return routes.get(idRoute).get(0);
        else
            return -1;
    }    
    
    /**
     * Returns the last customer for the route.
     * @param idRoute Route identifier.
     * @return if the route exists, then the last customer on the map, 
     * otherwise, -1.
     */
    public int getLastCustomer(int idRoute){
        if(routes.get(idRoute) != null)
            return routes.get(idRoute).get(routes.get(idRoute).size() - 1);
        else
            return -1;
    }
}

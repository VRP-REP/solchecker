package org.vrprep.solchecker.VRPREPImplementation;

import java.util.ArrayList;
import java.util.HashMap;
import org.vrprep.solchecker.framework.Solution;

/**
 * 
 */
public class VRPREPSolution implements Solution{
    private HashMap<Integer, ArrayList<Integer>> routes;
    
    public VRPREPSolution(){
        routes = new HashMap<Integer, ArrayList<Integer>>();
    }
    
    public void addRoute(int numRoute, ArrayList<Integer> customers){
        routes.put(numRoute, customers);
    }
    
    public HashMap<Integer, ArrayList<Integer>> getRoutes(){
        return routes;
    }
    
    public ArrayList<ArrayList<Integer>> getRoutesList(){
        ArrayList<ArrayList<Integer>> routesList = new ArrayList<ArrayList<Integer>>();
        for(Integer numRoute : routes.keySet()){
            routesList.add(routes.get(numRoute));
        }
        
        return routesList;
    }
    
    public ArrayList<Integer> getCustomers(int numRoute){
        return routes.get(numRoute);
    }

    public int getFirstCustomer(int numRoute) {
        return routes.get(numRoute).get(0);
    }    
    
    public int getLastCustomer(int numRoute){
        return routes.get(numRoute).get(routes.get(numRoute).size() - 1);
    }
}

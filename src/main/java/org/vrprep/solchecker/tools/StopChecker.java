package org.vrprep.solchecker.tools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * StopChecker class provides various functions for verify the number of visits
 * for each customer.
 */
public class StopChecker {
    /**
     * Checks if all clients contained in stopID were visited a number of times 
     * between min and max.
     * @param min Minimum number of visits
     * @param max Maximum number of visits
     * @param stopID List of customers' identifiers which is desired to check the number of visits
     * @param routes List of routes. Each route contains all the customers' identifiers visited during the route
     * @param depotList List of depot identifiers
     * @return TRUE if all customers were visited a good number of times (between min and max), 
     * FALSE otherwise
     */
    public static boolean checkStops(int min, int max, int [] stopID, List<List<Integer>> routes, List<Integer> depotList){
        HashMap<Integer, Integer> stopVisits = new HashMap<Integer, Integer>();
        for(int i = 0; i < stopID.length; ++i){
            stopVisits.put(stopID[i], 0);
        }
        
        for(List<Integer> route : routes){
            for(Integer customer : route){
                if(!depotList.contains(customer)) stopVisits.put(customer, stopVisits.get(customer) + 1);
            }
        }
        
        for(Integer customer : stopVisits.keySet()){
            int nbCustomerVisit = stopVisits.get(customer);
            if(nbCustomerVisit < min || nbCustomerVisit > max){
                return false;
            }
        }
        
        return true;
    }
    
    /**
     * Checks if all clients contained in stopID were visited a number of times 
     * between min and max.
     * @param min Minimum number of visits
     * @param max Maximum number of visits
     * @param stopID List of customers' identifiers which is desired to check the number of visits
     * @param routes List of routes. Each route contains all the customers' identifiers visited during the route
     * @param idDepot Depot identifier
     * @return TRUE if all customers were visited a good number of times (between min and max), 
     * FALSE otherwise
     */
    public static boolean checkStops(int min, int max, int [] stopID, List<List<Integer>> routes, int idDepot){
        List<Integer> depotList = new ArrayList<Integer>();
        depotList.add(idDepot);
        
        return checkStops(min, max, stopID, routes, depotList);
    }
    
    /**
     * Checks if all clients (whose identifier is between 1 and n) were visited 
     * a number of times between min and max.
     * @param min Minimum number of visits
     * @param max Maximum number of visits
     * @param n Total number of customers
     * @param routes List of routes. Each route contains all the customers' identifiers visited during the route
     * @return TRUE if all customers were visited a good number of times (between min and max), 
     * FALSE otherwise
     */
    public static boolean checkStops(int min, int max, int n, List<List<Integer>> routes){
        int [] stopID = new int[n];
        for(int i = 0; i < n; ++i){
            stopID[i] = i + 1;
        }
        
        return checkStops(min, max, stopID, routes, 0);
    }
}

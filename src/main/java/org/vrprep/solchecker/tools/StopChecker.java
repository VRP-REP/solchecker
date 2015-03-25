package org.vrprep.solchecker.tools;

import java.util.ArrayList;
import java.util.HashMap;

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
     * @return TRUE if all customers were visited a good number of times (between min and max), 
     * FALSE otherwise
     */
    public static boolean checkStops(int min, int max, int [] stopID, ArrayList<ArrayList<Integer>> routes){
        HashMap<Integer, Integer> stopVisits = new HashMap<Integer, Integer>();
        for(int i = 0; i < stopID.length; ++i){
            stopVisits.put(stopID[i], 0);
        }
        
        for(ArrayList<Integer> route : routes){
            for(Integer customer : route){
                stopVisits.put(customer, stopVisits.get(customer) + 1);
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
     * Checks if all clients (whose identifier is between 1 and n) were visited 
     * a number of times between min and max.
     * @param min Minimum number of visits
     * @param max Maximum number of visits
     * @param n Total number of customers
     * @param routes List of routes. Each route contains all the customers' identifiers visited during the route
     * @return TRUE if all customers were visited a good number of times (between min and max), 
     * FALSE otherwise
     */
    public static boolean checkStops(int min, int max, int n, ArrayList<ArrayList<Integer>> routes){
        int [] stopID = new int[n];
        for(int i = 0; i < n; ++i){
            stopID[i] = i + 1;
        }
        
        return checkStops(min, max, stopID, routes);
    }
}

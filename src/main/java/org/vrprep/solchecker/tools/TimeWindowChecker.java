package org.vrprep.solchecker.tools;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * TimeWindowChecker class provides various functions for verify the respects of 
 * the time windows.
 */
public class TimeWindowChecker {    
    /**
     * Checks if each customer on the route is visited during its time window.
     * @param startTime Start time of the route 
     * @param route List of the customers' identifiant visited during the route
     * @param timeWindow Map containing for each customer its time window
     * @param distance Map containing for each customer its distance with others customers
     * @param serviceTime Map containing for each customer its service time
     * @return TRUE if each customer on the route is visited during its time window,
     * FALSE otherwise.
     */
    public static boolean checkTimeWindow(
            int startTime,
            List<Integer> route, 
            Map<Integer, Integer[]> timeWindow,
            Map<Integer, Map<Integer, Integer>> distance,
            Map<Integer, Integer> serviceTime){
        
        int time = startTime;
        
        for(int i = 0; i < route.size(); ++i){
            Integer [] customerTimeWindow = timeWindow.get(route.get(i));
            
            if(customerTimeWindow[0] <= time && time <= customerTimeWindow[1]){
                time += serviceTime.get(route.get(i));
                
                if(i < route.size() - 1){
                    time += distance.get(i).get(route.get(i+1));
                }
            } else {
                return false;
            }
        }
        
        return true;
    }
    
    /**
     * Checks if each customer on the route is visited during its time window.
     * @param startTime Start time of the route 
     * @param route List of the customers' identifiant visited during the route
     * @param timeWindow Map containing for each customer its time window
     * @param distance Distance between each customer
     * @param serviceTime Time for customer service
     * @return TRUE if each customer on the route is visited during its time window,
     * FALSE otherwise.
     */
    public static boolean checkTimeWindowWithConstantDistanceAndServiceTime(
            int startTime,
            List<Integer> route, 
            Map<Integer, Integer[]> timeWindow,
            int distance,
            int serviceTime){
        
        Map<Integer, Integer> serviceTimeMap = new HashMap<Integer, Integer>();
        
        for(Integer customer : route){
            serviceTimeMap.put(customer, serviceTime);
        }
        
        Map<Integer, Map<Integer, Integer>> distanceMap = new HashMap<Integer, Map<Integer, Integer>>();
        
        for(Integer customer : route){
            Map<Integer, Integer> customerDistanceMap = new HashMap<Integer, Integer>();
            
            for(Integer otherCustomer : route){
                customerDistanceMap.put(otherCustomer, distance);
            }
            
            distanceMap.put(customer, customerDistanceMap);
        }
        
        return checkTimeWindow(startTime, route, timeWindow, distanceMap, serviceTimeMap);
    }
    
    /**
     * Checks if each customer on the route is visited during its time window.
     * @param startTime Start time of the route 
     * @param route List of the customers' identifiant visited during the route
     * @param timeWindow Map containing for each customer its time window
     * @param distance Distance between each customer
     * @param serviceTime Map containing for each customer its service time
     * @return TRUE if each customer on the route is visited during its time window,
     * FALSE otherwise.
     */
    public static boolean checkTimeWindowWithConstantDistance(
            int startTime,
            List<Integer> route, 
            Map<Integer, Integer[]> timeWindow,
            int distance,
            Map<Integer, Integer> serviceTime){
        
        Map<Integer, Map<Integer, Integer>> distanceMap = new HashMap<Integer, Map<Integer, Integer>>();
        
        for(Integer customer : route){
            Map<Integer, Integer> customerDistanceMap = new HashMap<Integer, Integer>();
            
            for(Integer otherCustomer : route){
                customerDistanceMap.put(otherCustomer, distance);
            }
            
            distanceMap.put(customer, customerDistanceMap);
        }
        
        return checkTimeWindow(startTime, route, timeWindow, null, serviceTime);
    }
    
    /**
     * Checks if each customer on the route is visited during its time window.
     * @param startTime Start time of the route 
     * @param route List of the customers' identifiant visited during the route
     * @param timeWindow Map containing for each customer its time window
     * @param distance Map containing for each customer its distance with others customers
     * @param serviceTime Time for customer service
     * @return TRUE if each customer on the route is visited during its time window,
     * FALSE otherwise.
     */
    public static boolean checkTimeWindowWithConstantServiceTime(
            int startTime,
            List<Integer> route, 
            Map<Integer, Integer[]> timeWindow,
            Map<Integer, Map<Integer, Integer>> distance,
            int serviceTime){
        
        Map<Integer, Integer> serviceTimeMap = new HashMap<Integer, Integer>();
        
        for(Integer customer : route){
            serviceTimeMap.put(customer, serviceTime);
        }
        
        return checkTimeWindow(startTime, route, timeWindow, distance, serviceTimeMap);
    }
}

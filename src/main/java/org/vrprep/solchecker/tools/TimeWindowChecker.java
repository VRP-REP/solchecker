package org.vrprep.solchecker.tools;

import java.util.ArrayList;
import java.util.HashMap;

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
            ArrayList<Integer> route, 
            HashMap<Integer, Integer[]> timeWindow,
            HashMap<Integer, HashMap<Integer, Integer>> distance,
            HashMap<Integer, Integer> serviceTime){
        
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
            ArrayList<Integer> route, 
            HashMap<Integer, Integer[]> timeWindow,
            int distance,
            int serviceTime){
        
        HashMap<Integer, Integer> serviceTimeMap = new HashMap<Integer, Integer>();
        
        for(Integer customer : route){
            serviceTimeMap.put(customer, serviceTime);
        }
        
        HashMap<Integer, HashMap<Integer, Integer>> distanceMap = new HashMap<Integer, HashMap<Integer, Integer>>();
        
        for(Integer customer : route){
            HashMap<Integer, Integer> customerDistanceMap = new HashMap<Integer, Integer>();
            
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
            ArrayList<Integer> route, 
            HashMap<Integer, Integer[]> timeWindow,
            int distance,
            HashMap<Integer, Integer> serviceTime){
        
        HashMap<Integer, HashMap<Integer, Integer>> distanceMap = new HashMap<Integer, HashMap<Integer, Integer>>();
        
        for(Integer customer : route){
            HashMap<Integer, Integer> customerDistanceMap = new HashMap<Integer, Integer>();
            
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
            ArrayList<Integer> route, 
            HashMap<Integer, Integer[]> timeWindow,
            HashMap<Integer, HashMap<Integer, Integer>> distance,
            int serviceTime){
        
        HashMap<Integer, Integer> serviceTimeMap = new HashMap<Integer, Integer>();
        
        for(Integer customer : route){
            serviceTimeMap.put(customer, serviceTime);
        }
        
        return checkTimeWindow(startTime, route, timeWindow, distance, serviceTimeMap);
    }
}

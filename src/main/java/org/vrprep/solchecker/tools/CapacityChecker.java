package org.vrprep.solchecker.tools;

import java.util.ArrayList;
import java.util.HashMap;

public class CapacityChecker {    
    /**
     * Checks whether the total customer demand for the route doesn't exceed 
     * the maximum capacity.
     * @param route List of the customers' identifiant visited during the route
     * @param demands Map containing for each customer its request/demand
     * @param capacity The maximum capacity available for the route
     * @return TRUE if the total customer demand for the route doesn't exceed the maximum capacity
     * FALSE otherwise
     */
    public static boolean checkCapacity(
            ArrayList<Integer> route, 
            HashMap<Integer, Double> demands, 
            double capacity){
        
        double routeDemand = 0;
        
        for(int customer : route){
            routeDemand += (demands.get(customer) != null)? demands.get(customer) : 0;
            
            if(routeDemand > capacity){
                return false;
            }
        }        
        
        return true;
    }
    
    /**
     * 
     * @param route
     * @param demands
     * @param compartmentsCapacity
     * @return TRUE
     * FALSE otherwise
     */
    public static boolean checkCompartmentCapacity(
            ArrayList<Integer> route, 
            HashMap<Integer, ArrayList<Double []>> demands,
            HashMap<Integer, Double> compartmentsCapacity){
        
        HashMap<Integer, Double> compartmentsDemands = new HashMap<Integer, Double>();
        
        for(int customer : route){            
            for(Double [] customerInformation : demands.get(customer)){
                int compartment = customerInformation[0].intValue();
                double customerDemand = customerInformation[1];
                double compartmentMaxCapacity = compartmentsCapacity.get(compartment);
                
                if(compartmentsDemands.get(compartment) == null){
                    if(customerDemand <= compartmentMaxCapacity){
                        compartmentsDemands.put(compartment, customerDemand);
                    } else {
                        return false;
                    }
                } else {
                    double compartmentCapacityUsued = compartmentsDemands.get(compartment);
                    
                    if((customerDemand + compartmentCapacityUsued) <= compartmentMaxCapacity){
                        compartmentsDemands.put(compartment, customerDemand + compartmentCapacityUsued);
                    } else {
                        return false;
                    }
                }
            }            
        }
        
        return true;
    }
}

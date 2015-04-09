package org.vrprep.solchecker.tools;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * CapacityChecker class provides various functions for verify the vehicule capacity.
 */
public class CapacityChecker {    
    /**
     * Checks whether the total customer demand for the route doesn't exceed 
     * the maximum capacity.
     * @param route List of the customers' identifier visited during the route.
     * @param demands Map containing for each customer its request/demand.
     * @param capacity The maximum capacity available for the route.
     * @return TRUE if the total customer demand for the route doesn't exceed 
     * the maximum capacity, FALSE otherwise.
     */
    public static boolean checkCapacity(
            List<Integer> route, 
            Map<Integer, Double> demands, 
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
     * Checks whether the total demand for each compartment of the vehicle 
     * doesn't exceed the maximum capacity of compartment.
     * @param route List of the customers' identifier.
     * @param demands Map containing for each customer its request/demand.
     * @param compartmentsCapacity Map containing for each compartment its 
     * maximum capacity.
     * @return TRUE if the total demand for each compartment of the vehicle 
     * doesn't exceed the maximum capacity, FALSE otherwise.
     */
    public static boolean checkCompartmentCapacity(
            List<Integer> route, 
            Map<Integer, List<Double []>> demands,
            Map<Integer, Double> compartmentsCapacity){
        
        Map<Integer, Double> compartmentsDemands = new HashMap<Integer, Double>();
        
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

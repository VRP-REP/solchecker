package org.vrprep.solchecker.tools;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * VehiculeTypeChecker class provides various functions for verify the type of 
 * vehicule on the route.
 */
public class VehiculeTypeChecker {    
    /**
     * Checks if all the customer visited during the route are visited by the 
     * right vehicule type.
     * @param vehiculeType Identifier of the vehicule's type
     * @param route List of the customers' identifiant visited during the route
     * @param customerNeed Map containing for each customer its vehicule type identifier
     * @return TRUE if all the customer visited during the route are visited by 
     * the right vehicule type, FALSE otherwise.
     */
    public static boolean checkVehiculeType(
            int vehiculeType, 
            ArrayList<Integer> route, 
            HashMap<Integer, Integer> customerNeed){
        
        for(Integer customer : route){
            if(customerNeed.get(customer) != vehiculeType){
                return false;
            }
        }
        
        return true;
    }
}

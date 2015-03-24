package org.vrprep.solchecker.tools;

import java.util.ArrayList;
import java.util.HashMap;

public class PickupDeliveryChecker {    
    /**
     * Checks if all the pickup are before the delivery on the route.
     * @param route List of the customers' identifiant visited during the route
     * @param pickupDeliveryList Map containing for each pickup its delivery(ies)
     * @return TRUE if all the pickup are before the delivery on the route,
     * FALSE otherwise.
     */
    public static boolean checkPickupDelivery(
            ArrayList<Integer> route, 
            HashMap<Integer, ArrayList<Integer>> pickupDeliveryList){
        for(Integer pickup : pickupDeliveryList.keySet()){
            int pickupIndex = route.indexOf(pickup);
            
            for(Integer delivery : pickupDeliveryList.get(pickup)){
                int deliveryIndex = route.indexOf(delivery);
                
                if(pickupIndex > deliveryIndex){
                    return false;
                }
            }
        }
        
        return true;
    }
}

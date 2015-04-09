package org.vrprep.solchecker.tools;

import java.util.List;
import java.util.Map;

/**
 * PickupDeliveryChecker class provides various functions for verify the pickup 
 * and delivery constraint.
 */
public class PickupDeliveryChecker {    
    /**
     * Checks if all the pickup are before the delivery on the route.
     * @param route List of the customers' identifiant visited during the route
     * @param pickupDeliveryList Map containing for each pickup its delivery(ies)
     * @return TRUE if all the pickup are before the delivery on the route,
     * FALSE otherwise.
     */
    public static boolean checkPickupDelivery(
            List<Integer> route, 
            Map<Integer, List<Integer>> pickupDeliveryList){
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

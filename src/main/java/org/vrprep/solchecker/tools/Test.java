package org.vrprep.solchecker.tools;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 
 */
public class Test {
    public static void main(String[] args){ 
        ArrayList<Integer> route = new ArrayList<Integer>();
        HashMap<Integer, ArrayList<Double []>> demands = new HashMap<Integer, ArrayList<Double[]>>();
        HashMap<Integer, Double> compartmentsCapacity = new HashMap<Integer, Double>();
        
        route.add(1);
        route.add(2);
        route.add(3);
        route.add(4);
        route.add(5);
        
        for(int customer : route){
            ArrayList<Double[]> customerDemand = new ArrayList<Double[]>();
            Double[] demand1 = new Double[2];
            demand1[0] = 1.0;
            demand1[1] = 10.0;
            customerDemand.add(demand1);
            
            Double[] demand2 = new Double[2];
            demand2[0] = 2.0;
            demand2[1] = 10.0;
            customerDemand.add(demand2);
            
            demands.put(customer, customerDemand);
        }
        
        compartmentsCapacity.put(1, 50.0);
        compartmentsCapacity.put(2, 50.0);
        
        System.out.println(CapacityChecker.checkCompartmentCapacity(route, demands, compartmentsCapacity));
    }
}

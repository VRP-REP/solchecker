package org.vrprep.solchecker.tools;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * DistanceCalculator class provides various functions for calculating distance.
 */
public class DistanceCalculator {
    
    /**
     * Calculates the euclidean distance between two points a and b.
     * @param a Cordinates of the first point {x, y, ...}
     * @param b Cordinates of the second point {x, y, ...}
     * @return Euclidean distance between a and b.
     */
    public static double euclideanDistance(Double [] a, Double [] b){
        int dimension = Math.min(a.length, b.length);
        double distance = 0;
        
        for(int i = 0; i < dimension; ++i){
            distance += Math.pow(a[i] - b[i], 2);
        }
        
        distance = Math.sqrt(distance);
        
        return distance;
    }
    
    /**
     * Calculates the euclidean distance between two points a and b.
     * @param a Cordinates of the first point {x, y, ...}
     * @param b Cordinates of the second point {x, y, ...}
     * @param nbDecimal
     * @param roundingMode
     * @return Euclidean distance between a and b.
     */
    public static double euclideanDistanceRound(Double [] a, Double [] b, int nbDecimal, int roundingMode){
        int dimension = Math.min(a.length, b.length);
        double distance = 0;
        
        for(int i = 0; i < dimension; ++i){
            distance += Math.pow(a[i] - b[i], 2);
        } 
        
        distance = Math.sqrt(distance);
        
        BigDecimal bigDecimal = new BigDecimal(distance);
        bigDecimal = bigDecimal.setScale(nbDecimal, roundingMode);
        distance = bigDecimal.doubleValue();
        
        return distance;
    }
    
    /**
     * Calculates the euclidean distance traveled by the route. 
     * @param route List of customers visited during the route
     * @param cordinates Map containing for each customer its cordinates
     * @return Euclidean distance traveled by the route
     */
    public static double euclideanDistanceOnRoute(List<Integer> route, Map<Integer, Double[]> cordinates){
        double distance = 0;
        
        for(int i = 0; i < (route.size() - 1); ++i){
            int a = route.get(i);
            int b = route.get(i+1);
            distance += euclideanDistance(cordinates.get(a), cordinates.get(b));
        }
        
        return distance;
    }
    
    /**
     * Calculates the euclidean distance traveled by the route. 
     * @param route List of customers visited during the route
     * @param cordinates Map containing for each customer its cordinates
     * @param nbDecimal
     * @param roundingMode
     * @return Euclidean distance traveled by the route
     */
    public static double euclideanDistanceRoundOnRoute(
            List<Integer> route, 
            Map<Integer, Double[]> cordinates, 
            int nbDecimal, 
            int roundingMode){
        
        double distance = 0;
        
        for(int i = 0; i < (route.size() - 1); ++i){
            int a = route.get(i);
            int b = route.get(i+1);
            distance += euclideanDistanceRound(cordinates.get(a), cordinates.get(b), nbDecimal, roundingMode);
        }
        
        return distance;
    }
}
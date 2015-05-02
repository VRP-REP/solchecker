package org.vrprep.solchecker.VRPREPImplementation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import org.vrprep.solchecker.framework.Solution;
import org.vrprep.solchecker.framework.SolutionBuilder;

/**
 * Implementation of SolutionBuilder for VRP-REP SolChecker to build an instance 
 * of {@link VRPREPSolution}.
 */
public class VRPREPSolutionBuilder implements SolutionBuilder {

    /**
     * Build a solution from a file. The file must be formatted like 
     * "http://www.coin-or.org/SYMPHONY/branchandcut/VRP/data/" solution.
     * @param f File containing all routes.
     * @return If the file is correct, then an instance of {@link VRPREPSolution} 
     * containing all the routes read on the file, otherwise, null.
     */
    public Solution buildSolution(File f) {
        try {
            VRPREPSolution solution = new VRPREPSolution();

            BufferedReader brs = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
            String solutionLine = "";

            while ((solutionLine = brs.readLine()) != null) {
                if (solutionLine.startsWith("Route")) {
                    ArrayList<Integer> customersList = new ArrayList<Integer>();
                    String[] route = solutionLine.split(" ");
                    Integer idRoute = Integer.parseInt(route[1].split("#:")[0]);

                    for (int k = 2; k < route.length; ++k) {
                        customersList.add(Integer.parseInt(route[k]));
                    }

                    solution.addRoute(idRoute, customersList);
                }
            }

            return solution;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        } 
    }

}

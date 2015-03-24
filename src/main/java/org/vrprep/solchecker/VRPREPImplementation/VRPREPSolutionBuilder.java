package org.vrprep.solchecker.VRPREPImplementation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import org.vrprep.solchecker.framework.Solution;
import org.vrprep.solchecker.framework.SolutionBuilder;

/**
 *
 */
public class VRPREPSolutionBuilder implements SolutionBuilder {

    public Solution buildSolution(File f) {
        try {
            VRPREPSolution solution = new VRPREPSolution();

            BufferedReader brs = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
            String solutionLine = "";

            while ((solutionLine = brs.readLine()) != null) {
                if (solutionLine.startsWith("Route")) {
                    ArrayList<Integer> customersList = new ArrayList<Integer>();
                    String[] route = solutionLine.split(" ");
                    Integer routeNum = Integer.parseInt(route[1].substring(1, 2));

                    for (int k = 2; k < route.length; ++k) {
                        customersList.add(Integer.parseInt(route[k]));
                    }

                    solution.addRoute(routeNum, customersList);
                }
            }

            return solution;
        } catch (IOException e) {
            System.out.println(e);
            return null;
        } catch (NumberFormatException e) {
            System.out.println(e);
            return null;
        }
    }

}

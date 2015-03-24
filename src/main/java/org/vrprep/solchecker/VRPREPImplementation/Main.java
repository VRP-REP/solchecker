package org.vrprep.solchecker.VRPREPImplementation;

import java.io.File;
import org.vrprep.solchecker.framework.Report;

/**
 *
 */
public class Main {
    public static void main(String[] args){
        File instance = new File("D:\\documents\\Polytech\\5A\\PFE\\Instances\\augerat-1995-set-a\\A-n32-k05.xml");
        File solution = new File("C:\\Users\\OrdiCentre\\Desktop\\A-n32-k5.opt");
        File config = new File("C:\\Users\\OrdiCentre\\Desktop\\config-VRPREP-solchecker.xml");
        File result = new File("C:\\Users\\OrdiCentre\\Desktop\\result-implementation-VRPREP.xml");        
        
        VRPREPSolChecker solchecker = new VRPREPSolChecker(config);
        Report report = solchecker.run(solution, instance);
        report.exportToXML(result);
    }
}

package org.vrprep.solchecker.VRPREPImplementation;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.vrprep.solchecker.framework.Constraint;
import org.vrprep.solchecker.framework.Instance;
import org.vrprep.solchecker.framework.Metric;
import org.vrprep.solchecker.framework.Objective;
import org.vrprep.solchecker.framework.Report;
import org.vrprep.solchecker.framework.SolChecker;
import org.vrprep.solchecker.framework.Solution;

/**
 * Defines the VRP-REP SolChecker.
 */
public class VRPREPSolChecker extends SolChecker {

    protected VRPREPInstanceBuilder ib;
    protected VRPREPSolutionBuilder sb;
    protected List<Constraint> constraints;
    protected List<Objective> objectives;
    protected List<Metric> metrics;

    public VRPREPSolChecker(File config) {
        super(config);
    }

    public void addConstraint(Constraint c) {
        this.constraints.add(c);
    }

    public void addObjective(Objective c) {
        this.objectives.add(c);
    }

    public void addMetric(Metric c) {
        this.metrics.add(c);
    }

    /**
     * Checks the solution with the instance. All the evalution (constraints,
     * objectives functions and other metrics) are evaluated.
     * @param s Solution file
     * @param i Instance file
     * @return Report of the checking
     */
    @Override
    public Report run(File s, File i) {
        //Set up
        Instance instance = ib.buildInstance(i);
        Solution solution = sb.buildSolution(s);
        Report report = new Report();

        //Evaluate objectives
        for (Objective of : objectives) {
            report.addEvaluation(of.evaluate(solution, instance));
        }

        //Evaluate constraints
        for (Constraint c : constraints) {
            report.addEvaluation(c.evaluate(solution, instance));
        }

        //Evaluate other metrics
        for (Metric m : metrics) {
            report.addEvaluation(m.evaluate(solution, instance));
        }

        //Output
        return report;
    }

    /**
     * Configurates the solution checker using the XML configuration file. The
     * XML file should follow the rules stated in vrp-rep_solchecker_config.xsd
     * schema.
     * @param config XML document holding the solution checker's
     * configuration
     */
    public void configurate(File config) {

        this.ib = new VRPREPInstanceBuilder();
        this.sb = new VRPREPSolutionBuilder();
        this.constraints = new ArrayList<Constraint>();
        this.objectives = new ArrayList<Objective>();
        this.metrics = new ArrayList<Metric>();

        try {
            SAXBuilder sxb = new SAXBuilder();
            Document doc = sxb.build(config);

            Element root = doc.getRootElement();
            List constraintsList = root.getChild("constraints").getChildren();
            List objectivesList = root.getChild("objectives").getChildren();
            List metricsList = root.getChild("metrics").getChildren();

            // Read constraints
            for (Object constraintObject : constraintsList) {
                Element constraintElement = (Element) constraintObject;
                if (constraintElement.getText() != null) {
                    Class constraintClass = Class.forName(constraintElement.getText());
                    Constraint constraint = (Constraint) constraintClass.newInstance();
                    this.addConstraint(constraint);
                }
            }

            // Read objectives
            for (Object objectiveObject : objectivesList) {
                Element objectiveElement = (Element) objectiveObject;
                if (objectiveElement.getText() != null) {
                    Class objectiveClass = Class.forName(objectiveElement.getText());
                    Objective objective = (Objective) objectiveClass.newInstance();
                    this.addObjective(objective);
                }
            }

            // Read metrics
            for (Object metricObject : metricsList) {
                Element metricElement = (Element) metricObject;
                if (metricElement.getText() != null) {
                    Class metricClass = Class.forName(metricElement.getText());
                    Metric metric = (Metric) metricClass.newInstance();
                    this.addMetric(metric);
                }
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

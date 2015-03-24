package org.vrprep.solchecker.tools;

import java.util.HashSet;
import org.jdom.Element;

/**
 *
 */
public class SolcheckerConfiguration {

    private HashSet<String> objectives;
    private HashSet<String> constraints;
    private HashSet<String> metrics;

    public SolcheckerConfiguration() {
        objectives = new HashSet<String>();
        constraints = new HashSet<String>();
        metrics = new HashSet<String>();
    }
  
    public SolcheckerConfiguration(Element configurationElement) {
        objectives = new HashSet<String>();
        constraints = new HashSet<String>();
        metrics = new HashSet<String>();

        if (configurationElement != null) {
            for (Object objective : configurationElement.getChild("objectives").getChildren("objective")) {
                Element objectiveElement = (Element) objective;
                this.addObjective(objectiveElement.getText());
            }

            for (Object constraint : configurationElement.getChild("constraints").getChildren("constraint")) {
                Element constraintElement = (Element) constraint;
                this.addConstraint(constraintElement.getText());
            }

            for (Object metric : configurationElement.getChild("metrics").getChildren("metric")) {
                Element metricElement = (Element) metric;
                this.addMetric(metricElement.getText());
            }
        }
    }

    public HashSet<String> getObjectives() {
        return objectives;
    }

    public void addObjective(String objective) {
        objectives.add(objective);
    }

    public void setObjectives(HashSet<String> objectives) {
        this.objectives = objectives;
    }

    public HashSet<String> getConstraints() {
        return constraints;
    }

    public void addConstraint(String constraint) {
        constraints.add(constraint);
    }

    public void setConstraints(HashSet<String> constraints) {
        this.constraints = constraints;
    }

    public HashSet<String> getMetrics() {
        return metrics;
    }

    public void addMetric(String metric) {
        metrics.add(metric);
    }

    public void setMetrics(HashSet<String> metrics) {
        this.metrics = metrics;
    }

    public Element toXML() {
        Element configurationElement = new Element("configuration");

        Element objectivesElement = new Element("objectives");
        for (String objectiveName : getObjectives()) {
            Element objective = new Element("objective");
            objective.setText(objectiveName);
            objectivesElement.addContent(objective);
        }
        configurationElement.addContent(objectivesElement);

        Element constraintsElement = new Element("constraints");
        for (String constraintName : getConstraints()) {
            Element constraintElement = new Element("constraint");
            constraintElement.setText(constraintName);
            constraintsElement.addContent(constraintElement);
        }
        configurationElement.addContent(constraintsElement);

        Element metricsElement = new Element("metrics");
        for (String metricName : getMetrics()) {
            Element metricElement = new Element("metric");
            metricElement.setText(metricName);
            metricsElement.addContent(metricElement);
        }
        configurationElement.addContent(metricsElement);

        return configurationElement;
    }
}

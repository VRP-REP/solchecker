/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.vrprep.solchecker.tools;

import java.io.File;

/**
 *
 */
public class RunnableInformation {
    private File solutionFile;
    private File instanceFile;

    public RunnableInformation(File solutionFile, File instanceFile) {
        this.instanceFile = instanceFile;
        this.solutionFile = solutionFile;
    }
    
    public RunnableInformation(String solutionFile, String instanceFile) {
        this.instanceFile = new File(instanceFile);
        this.solutionFile = new File(solutionFile);
    }

    public File getInstanceFile() {
        return instanceFile;
    }

    public void setInstanceFile(File instanceFile) {
        this.instanceFile = instanceFile;
    }

    public File getSolutionFile() {
        return solutionFile;
    }

    public void setSolutionFile(File solutionFile) {
        this.solutionFile = solutionFile;
    }
}

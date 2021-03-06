package org.vrprep.solchecker.framework;

import java.io.File;

/**
 * Declares the interface for solution builders. An solution builder is an
 * object capable of reading a VRP solution from a file and build its memory
 * image as an solution of {@link Solution}.
 */
public interface SolutionBuilder {
    /**
     * Build a solution from a solution file.
     * @param f Solution file.
     * @return {@link Solution} relating to the instance file.
     */
    public Solution buildSolution(File f);

}

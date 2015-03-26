package org.vrprep.solchecker.framework;

import java.io.File;

/**
 * Declares the interface for instance builders. An instance builder is an
 * object capable of reading a VRP instance from a file and build its memory
 * image as an instance of {@link Instance}.
 */
public interface InstanceBuilder {
    /**
     * Build an instance from an instance file.
     * @param f Instance file
     * @return {@link Instance} relating to the instance file.
     */
    public Instance buildInstance(File f);

}

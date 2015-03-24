package org.vrprep.solchecker.framework;

import java.io.File;

/**
 * Declares the interface for instance builders. An instance builder is an
 * object capable of reading a VRP instance from a file and build its memory
 * image as an instance of {@link Instance}.
 *
 * @author Jorge E. Mendoza (dev@jorge-mendoza.com)
 * @version %I%, %G%
 * @since Feb 5, 2015
 *
 */
public interface InstanceBuilder {

    /**
     * Build an instance from a file.
     * @param f an instance file
     * @return the instance relating to the instance file
     */
    public Instance buildInstance(File f);

}

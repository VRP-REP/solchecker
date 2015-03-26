package org.vrprep.solchecker.framework;

import java.io.File;

/**
 * Defines the interface for solcheckers. A solchecker permits to verify the 
 * solution of an instance.
 */
public abstract class SolChecker {
    /**
     * Permits to initialize the SolChecker object from a 
     * configuration file. Configuration is done by calling the configurate 
     * method.
     * @param config File containing the solchecker's configuration.
     */
    public SolChecker(File config){
        this.configurate(config);
    }
    
    /**
     * Configures the solchecker with the configuration file. This method is 
     * used by the constructor and must be implemented in all classes extending 
     * SolChecker.
     * @param config File containing the solchecker's configuration.
     */
    public abstract void configurate(File config);

    /**
     * Checks the solution with the instance.
     * @param s Solution file.
     * @param i Instance file.
     * @return Report of the checking.
     */
    public abstract Report run(File s, File i);

}

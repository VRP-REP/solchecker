package org.vrprep.solchecker.framework;

import java.io.File;

/**
 * Defines the interface for solcheckers. A solchecker permits to verify the 
 * solution of an instance.
 */
public abstract class SolChecker {
    
    /**
     * Construction du SolChecker à partir de la méthode configurate.
     * @param config 
     */
    public SolChecker(File config){
        this.configurate(config);
    }
    
    /**
     * Methode appelée par le constructeur pour configurer le solchecker. Toutes les classes etendant Solchceker
     * devront implementer cette méthode.
     * @param config 
     */
    public abstract void configurate(File config);

    /**
     * Checks the solution with the instance.
     * @param s a solution file
     * @param i an instance file
     * @return the report of the checking
     */
    public abstract Report run(File s, File i);

}

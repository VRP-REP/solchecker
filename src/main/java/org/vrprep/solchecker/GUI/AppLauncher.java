package org.vrprep.solchecker.GUI;

import java.util.ArrayList;
import java.util.List;
import javax.swing.SwingUtilities;
import org.vrprep.solchecker.framework.ConstraintEvaluation;
import org.vrprep.solchecker.framework.Report;

/**
 *
 * @author OrdiCentre
 */
public class AppLauncher {

    public static SolcheckerFrame solcheckerFrame = new SolcheckerFrame();
    public static PluginFrame pluginFrame = new PluginFrame();
    public static PluginGenerationFrame pluginGenerationFrame = new PluginGenerationFrame();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                solcheckerFrame.setVisible(true);
            }
        });
    }
}

package org.vrprep.solchecker.VRPREPImplementation;

import java.io.File;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.vrprep.solchecker.framework.Instance;
import org.vrprep.solchecker.framework.InstanceBuilder;

/**
 *
 */
public class VRPREPInstanceBuilder implements InstanceBuilder {

    public Instance buildInstance(File f) {
        try {
            SAXBuilder sxb = new SAXBuilder();
            Document document = sxb.build(f);
            Element root = document.getRootElement();
            
            VRPREPInstance instance = new VRPREPInstance(root);
            return instance;
            
        } catch (Exception e) {
            return null;
        }
    }

}

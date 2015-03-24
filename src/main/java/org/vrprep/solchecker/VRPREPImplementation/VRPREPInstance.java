package org.vrprep.solchecker.VRPREPImplementation;

import java.util.List;
import org.jdom.Element;
import org.vrprep.solchecker.framework.Instance;

/**
 *
 */
public class VRPREPInstance implements Instance{
    
    private final Element root;

    public VRPREPInstance(final Element root) {
        this.root = root;
    }
    
    public Element getRoot(){
        return root;
    }
    
    public Element getInfo(){
        return root.getChild("info");
    }
    
    public Element getNetwork(){
        return root.getChild("network");
    }
    
    public List getNodes(){
        return this.getNetwork().getChild("nodes").getChildren();
    }
    
    public Element getRequests(){
        return root.getChild("requests");
    }
    
    public Element getFleet(){
        return root.getChild("fleet");
    }
}

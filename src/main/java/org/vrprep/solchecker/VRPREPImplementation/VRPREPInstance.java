package org.vrprep.solchecker.VRPREPImplementation;

import java.util.List;
import org.jdom.Element;
import org.vrprep.solchecker.framework.Instance;

/**
 * Implementation of VRPREPInstance. VRPREPInstance used to model a basic 
 * instance following the VRPREP schema (xsd).
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
        if(this.getNetwork() != null){
            if(this.getNetwork().getChild("nodes") != null){
                return this.getNetwork().getChild("nodes").getChildren();
            }
        }
        return null;
    }
    
    public Element getRequests(){
        return root.getChild("requests");
    }
    
    public Element getFleet(){
        return root.getChild("fleet");
    }
}

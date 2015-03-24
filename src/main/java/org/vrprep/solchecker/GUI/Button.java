package org.vrprep.solchecker.GUI;

import java.awt.Color;
import javax.swing.JButton;

/**
 *
 */
public class Button extends JButton{
    public Button(){
        super();
        
        setBackground(new Color(0, 125, 204));
        setForeground(new Color(255, 255, 255));
    }
    
    public Button(String name){
        super(name);
        
        setBackground(new Color(0, 125, 204));
        setForeground(new Color(255, 255, 255));
    }
}

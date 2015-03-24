/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.vrprep.solchecker.GUI;

import java.awt.Dimension;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author OrdiCentre
 */
public class PluginToInstallTable extends JTable {

    PluginToInstallTable(DefaultTableModel model) {
        super(model);
        setPreferredScrollableViewportSize(new Dimension(500, 100));
        setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
    }

    @Override
    public Class getColumnClass(int column) {
        switch (column) {
            case 0:
                return String.class;
            case 1:
                return String.class;
            case 2:
                return String.class;
            case 3:
                return String.class;
            case 4:
                return Boolean.class;
            default:
                return String.class;
        }
    }
}
